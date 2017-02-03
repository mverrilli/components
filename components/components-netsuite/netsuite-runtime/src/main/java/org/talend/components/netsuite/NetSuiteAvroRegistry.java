package org.talend.components.netsuite;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.model.Mapper;
import org.talend.components.netsuite.client.NetSuiteFactory;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.daikon.avro.AvroRegistry;
import org.talend.daikon.avro.SchemaConstants;
import org.talend.daikon.avro.converter.AvroConverter;
import org.talend.daikon.java8.SerializableFunction;

/**
 *
 */
public class NetSuiteAvroRegistry extends AvroRegistry {

    public static final String FAMILY_NAME = "NetSuite"; //$NON-NLS-1$

    private static final NetSuiteAvroRegistry instance = new NetSuiteAvroRegistry();

    private final DatatypeFactory datatypeFactory;

    private NetSuiteAvroRegistry() {

        // Ensure that we know how to get Schemas for these objects.
        registerSchemaInferrer(NetSuiteMetaData.EntityInfo.class, new SerializableFunction<NetSuiteMetaData.EntityInfo, Schema>() {
            /** Default serial version UID. */
            private static final long serialVersionUID = 1L;

            @Override
            public Schema apply(NetSuiteMetaData.EntityInfo t) {
                return NetSuiteSchemaManager.getInstance().inferSchemaForEntity(t);
            }
        });

        registerSchemaInferrer(NetSuiteMetaData.FieldInfo.class, new SerializableFunction<NetSuiteMetaData.FieldInfo, Schema>() {
            /** Default serial version UID. */
            private static final long serialVersionUID = 1L;

            @Override
            public Schema apply(NetSuiteMetaData.FieldInfo t) {
                return NetSuiteSchemaManager.getInstance().inferSchemaForField(t);
            }
        });

        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new ComponentException(e);
        }
    }

    public static NetSuiteAvroRegistry getInstance() {
        return instance;
    }

    public DatatypeFactory getDatatypeFactory() {
        return datatypeFactory;
    }

    public AvroConverter<?, ?> getConverter(Schema.Field field, Class<?> datumClass) {
        if (datumClass.isEnum()) {
            Class<Enum> enumClass = (Class<Enum>) datumClass;
            return new EnumToStringConverter<>(field, enumClass,
                    NetSuiteFactory.getEnumToStringMapper(enumClass),
                    NetSuiteFactory.getEnumFromStringMapper(enumClass));
        }
        if (datumClass == XMLGregorianCalendar.class) {
            return new XMLGregorianCalendarToStringConverter(field, datatypeFactory);
        }
        return super.getConverter(datumClass);
    }

    /**
     * @return The family that uses the specific objects that this converter knows how to translate.
     */
    public String getFamilyName() {
        return FAMILY_NAME;
    }

    public static class EnumToStringConverter<T extends Enum<T>> implements AvroConverter<T, String> {

        private final Schema.Field field;
        private final Class<T> clazz;
        private final Mapper<Enum, String> enumToStringMapper;
        private final Mapper<String, Enum> enumFromStringMapper;

        public EnumToStringConverter(Schema.Field field, Class<T> clazz,
                Mapper<Enum, String> enumToStringMapper, Mapper<String, Enum> enumFromStringMapper) {
            this.field = field;
            this.clazz = clazz;
            this.enumToStringMapper = enumToStringMapper;
            this.enumFromStringMapper = enumFromStringMapper;
        }

        @Override
        public Schema getSchema() {
            return field.schema();
        }

        @Override
        public Class<T> getDatumClass() {
            return clazz;
        }

        @Override
        public T convertToDatum(String value) {
            return value == null ? null : (T) enumFromStringMapper.map(value);
        }

        @Override
        public String convertToAvro(Enum anEnum) {
            if (anEnum == null) {
                return null;
            }
            return enumToStringMapper.map(anEnum);
        }
    }

    public static class XMLGregorianCalendarToStringConverter implements AvroConverter<XMLGregorianCalendar, String> {

        private final Schema.Field field;
        private final DateTimeFormatter format;

        private DatatypeFactory datatypeFactory;

        public XMLGregorianCalendarToStringConverter(Schema.Field field, DatatypeFactory datatypeFactory) {
            this.field = field;
            this.datatypeFactory = datatypeFactory;

            format = DateTimeFormat.forPattern(field.getProp(SchemaConstants.TALEND_COLUMN_PATTERN));
        }

        @Override
        public Schema getSchema() {
            return field.schema();
        }

        @Override
        public Class<XMLGregorianCalendar> getDatumClass() {
            return XMLGregorianCalendar.class;
        }

        @Override
        public XMLGregorianCalendar convertToDatum(String s) {
            if (s == null) {
                return null;
            }

            DateTime dateTime;
            try {
                dateTime = format.parseDateTime(s);
            } catch (IllegalArgumentException e) {
                throw new ComponentException(e);
            }

            XMLGregorianCalendar xts = datatypeFactory.newXMLGregorianCalendar();
            xts.setYear(dateTime.getYear());
            xts.setMonth(dateTime.getMonthOfYear());
            xts.setDay(dateTime.getDayOfMonth());
            xts.setHour(dateTime.getHourOfDay());
            xts.setMinute(dateTime.getMinuteOfHour());
            xts.setSecond(dateTime.getSecondOfMinute());
            xts.setMillisecond(dateTime.getMillisOfSecond());
            xts.setTimezone(dateTime.getZone().toTimeZone().getRawOffset() / 60000);

            return xts;
        }

        @Override
        public String convertToAvro(XMLGregorianCalendar xts) {
            if (xts == null) {
                return null;
            }

            MutableDateTime dateTime = new MutableDateTime();
            try {
                dateTime.setYear(xts.getYear());
                dateTime.setMonthOfYear(xts.getMonth());
                dateTime.setDayOfMonth(xts.getDay());
                dateTime.setHourOfDay(xts.getHour());
                dateTime.setMinuteOfHour(xts.getMinute());
                dateTime.setSecondOfMinute(xts.getSecond());
                dateTime.setMillisOfSecond(xts.getMillisecond());

                DateTimeZone tz = DateTimeZone.forOffsetMillis(xts.getTimezone() * 60000);
                if (tz != null) {
                    dateTime.setZoneRetainFields(tz);
                }

                return format.print(dateTime);
            } catch (IllegalArgumentException e) {
                throw new ComponentException(e);
            }
        }
    }
}
