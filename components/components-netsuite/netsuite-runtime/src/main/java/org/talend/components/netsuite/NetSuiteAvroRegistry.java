package org.talend.components.netsuite;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.talend.components.api.exception.ComponentException;
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
        registerSchemaInferrer(NetSuiteMetaData.Entity.class, new SerializableFunction<NetSuiteMetaData.Entity, Schema>() {
            /** Default serial version UID. */
            private static final long serialVersionUID = 1L;

            @Override
            public Schema apply(NetSuiteMetaData.Entity t) {
                return NetSuiteSchemaManager.getInstance().inferSchemaForEntity(t);
            }
        });

        registerSchemaInferrer(NetSuiteMetaData.Field.class, new SerializableFunction<NetSuiteMetaData.Field, Schema>() {
            /** Default serial version UID. */
            private static final long serialVersionUID = 1L;

            @Override
            public Schema apply(NetSuiteMetaData.Field t) {
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

    public AvroConverter<?, ?> getConverter(Schema.Field field, Class<?> datumClass) {
        if (datumClass.isEnum()) {
            return new EnumToStringConverter<>(field, (Class<? extends Enum>) datumClass);
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

        public EnumToStringConverter(Schema.Field field, Class<T> clazz) {
            this.field = field;
            this.clazz = clazz;
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
            return value == null ? null : Enum.valueOf(clazz, value);
        }

        @Override
        public String convertToAvro(Enum anEnum) {
            if (anEnum == null) {
                return null;
            }
            return anEnum.name();
        }
    }

    public static class XMLGregorianCalendarToStringConverter implements AvroConverter<XMLGregorianCalendar, String> {

        private final Schema.Field field;
        private final DateFormat format;

        private DatatypeFactory datatypeFactory;

        public XMLGregorianCalendarToStringConverter(Schema.Field field, DatatypeFactory datatypeFactory) {
            this.field = field;
            this.datatypeFactory = datatypeFactory;

            format = new SimpleDateFormat(field.getProp(SchemaConstants.TALEND_COLUMN_PATTERN));
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

            Calendar calValue = Calendar.getInstance();
            try {
                calValue.setTime(format.parse(s));
            } catch (ParseException e) {
                throw new ComponentException(e);
            }

            XMLGregorianCalendar xts = datatypeFactory.newXMLGregorianCalendar();
            xts.setYear(calValue.get(Calendar.YEAR));
            xts.setMonth(calValue.get(Calendar.MONTH) + 1);
            xts.setDay(calValue.get(Calendar.DAY_OF_MONTH));
            xts.setHour(calValue.get(Calendar.HOUR_OF_DAY));
            xts.setMinute(calValue.get(Calendar.MINUTE));
            xts.setSecond(calValue.get(Calendar.SECOND));
            xts.setMillisecond(calValue.get(Calendar.MILLISECOND));
            xts.setTimezone(calValue.get(Calendar.ZONE_OFFSET) / 60000);

            return xts;
        }

        @Override
        public String convertToAvro(XMLGregorianCalendar xts) {
            if (xts == null) {
                return null;
            }

            Calendar calValue = Calendar.getInstance();
            try {
                calValue.set(Calendar.YEAR, xts.getYear());
                calValue.set(Calendar.MONTH, xts.getMonth() - 1);
                calValue.set(Calendar.DAY_OF_MONTH, xts.getDay());
                calValue.set(Calendar.HOUR_OF_DAY, xts.getHour());
                calValue.set(Calendar.MINUTE, xts.getMinute());
                calValue.set(Calendar.SECOND, xts.getSecond());
                calValue.set(Calendar.MILLISECOND, xts.getMillisecond());

                String[] tzIds = TimeZone.getAvailableIDs(xts.getTimezone() * 60000);
                if (tzIds.length > 0) {
                    TimeZone tz = TimeZone.getTimeZone(tzIds[0]);
                    calValue.setTimeZone(tz);
                }

                return format.format(calValue);
            } catch (IllegalArgumentException e) {
                throw new ComponentException(e);
            }
        }
    }
}
