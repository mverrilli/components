package org.talend.components.netsuite;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.daikon.avro.AvroRegistry;
import org.talend.daikon.avro.AvroUtils;
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
                return inferSchemaForEntity(t);
            }
        });

        registerSchemaInferrer(NetSuiteMetaData.Field.class, new SerializableFunction<NetSuiteMetaData.Field, Schema>() {
            /** Default serial version UID. */
            private static final long serialVersionUID = 1L;

            @Override
            public Schema apply(NetSuiteMetaData.Field t) {
                return inferSchemaForField(t);
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

    /**
     * Infers an Avro schema for the given NsObject. This can be an expensive operation so the schema
     * should be cached where possible. This is always an {@link Schema.Type#RECORD}.
     *
     * @param in the <code>NsObject</code> to analyse.
     * @return the schema for data given from the object.
     */
    private Schema inferSchemaForEntity(NetSuiteMetaData.Entity in) {
        List<Schema.Field> fields = new ArrayList<>();

        for (NetSuiteMetaData.Field field : in.getFields().values()) {

            Schema.Field avroField = new Schema.Field(field.getName(), inferSchema(field), null, (Object) null);
            // Add some Talend6 custom properties to the schema.
            Schema avroFieldSchema = avroField.schema();
            if (avroFieldSchema.getType() == Schema.Type.UNION) {
                for (Schema schema : avroFieldSchema.getTypes()) {
                    if (avroFieldSchema.getType() != Schema.Type.NULL) {
                        avroFieldSchema = schema;
                        break;
                    }
                }
            }

            if (AvroUtils.isSameType(avroFieldSchema, AvroUtils._string())) {
                if (field.getLength() != 0) {
                    avroField.addProp(SchemaConstants.TALEND_COLUMN_DB_LENGTH, String.valueOf(field.getLength()));
                }
            }

            Class<?> fieldType = field.getValueType();
            if (fieldType == XMLGregorianCalendar.class) {
                avroField.addProp(SchemaConstants.TALEND_COLUMN_PATTERN, "yyyy-MM-dd'T'HH:mm:ss'.000Z'");
            }

            if (avroField.defaultVal() != null) {
                avroField.addProp(SchemaConstants.TALEND_COLUMN_DEFAULT, String.valueOf(avroField.defaultVal()));
            }

            fields.add(avroField);
        }

        return Schema.createRecord(in.getName(), null, null, false, fields);
    }

    /**
     * Infers an Avro schema for the given Field. This can be an expensive operation so the schema should be
     * cached where possible. The return type will be the Avro Schema that can contain the field data without loss of
     * precision.
     *
     * @param field the Field to analyse.
     * @return the schema for data that the field describes.
     */
    private Schema inferSchemaForField(NetSuiteMetaData.Field field) {
        Schema base;

        Class<?> fieldType = field.getValueType();

        if (fieldType == Boolean.TYPE || fieldType == Boolean.class) {
            base = AvroUtils._boolean();
        } else if (fieldType == Integer.TYPE || fieldType == Integer.class) {
            base = AvroUtils._int();
        } else if (fieldType == Long.TYPE || fieldType == Long.class) {
            base = AvroUtils._long();
        } else if (fieldType == Float.TYPE || fieldType == Float.class) {
            base = AvroUtils._float();
        } else if (fieldType == Double.TYPE || fieldType == Double.class) {
            base = AvroUtils._double();
        } else if (fieldType == XMLGregorianCalendar.class) {
            base = AvroUtils._string();
        } else if (fieldType == String.class) {
            base = AvroUtils._string();
        } else if (fieldType.isEnum()) {
            base = AvroUtils._string();
        } else {
            base = AvroUtils._string();
        }

        base = field.isNullable() ? AvroUtils.wrapAsNullable(base) : base;

        return base;
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
