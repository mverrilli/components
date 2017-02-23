package org.talend.components.netsuite;

import static org.talend.components.netsuite.client.model.BeanUtils.getEnumAccessor;
import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.beans.BeanInfo;
import org.talend.components.netsuite.beans.BeanManager;
import org.talend.components.netsuite.beans.EnumAccessor;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.model.FieldDesc;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.client.model.CustomFieldDesc;
import org.talend.components.netsuite.client.model.customfield.CustomFieldRefType;
import org.talend.daikon.di.DiSchemaConstants;

/**
 *
 */
public abstract class NsObjectTransducer {
    protected NetSuiteClientService clientService;

    protected final DatatypeFactory datatypeFactory;

    public NsObjectTransducer(NetSuiteClientService clientService) {
        this.clientService = clientService;

        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new ComponentException(e);
        }
    }

    public NetSuiteClientService getClientService() {
        return clientService;
    }

    public DatatypeFactory getDatatypeFactory() {
        return datatypeFactory;
    }

    protected Schema getDynamicSchema(TypeDesc typeDesc, Schema designSchema, String targetSchemaName) {
        Map<String, FieldDesc> fieldMap = typeDesc.getFieldMap();

        String dynamicPosProp = designSchema.getProp(DiSchemaConstants.TALEND6_DYNAMIC_COLUMN_POSITION);
        List<Schema.Field> fields = new ArrayList<>();

        if (dynamicPosProp != null) {
            List<FieldDesc> designFields = new ArrayList<>(designSchema.getFields().size());
            for (Schema.Field field : designSchema.getFields()) {
                String fieldName = field.name();
                FieldDesc fieldDesc = fieldMap.get(fieldName);
                designFields.add(fieldDesc);
            }

            int dynPos = Integer.parseInt(dynamicPosProp);
            int dynamicColumnSize = fieldMap.size() - designSchema.getFields().size();

            if (designSchema.getFields().size() > 0) {
                for (Schema.Field field : designSchema.getFields()) {
                    // Dynamic column is first or middle column in design schema
                    if (dynPos == field.pos()) {
                        for (int i = 0; i < dynamicColumnSize; i++) {
                            // Add dynamic schema fields
                            FieldDesc fieldDesc = designFields.get(i + dynPos);
                            fields.add(createSchemaField(fieldDesc));
                        }
                    }

                    // Add fields of design schema
                    Schema.Field avroField = new Schema.Field(field.name(), field.schema(), null, field.defaultVal());
                    Map<String, Object> fieldProps = field.getObjectProps();
                    for (String propName : fieldProps.keySet()) {
                        Object propValue = fieldProps.get(propName);
                        if (propValue != null) {
                            avroField.addProp(propName, propValue);
                        }
                    }

                    fields.add(avroField);

                    // Dynamic column is last column in design schema
                    if (field.pos() == (designSchema.getFields().size() - 1) && dynPos == (field.pos() + 1)) {
                        for (int i = 0; i < dynamicColumnSize; i++) {
                            // Add dynamic schema fields
                            FieldDesc fieldDesc = designFields.get(i + dynPos);
                            fields.add(createSchemaField(fieldDesc));
                        }
                    }
                }
            } else {
                // All fields are included in dynamic schema
                for (String fieldName : fieldMap.keySet()) {
                    FieldDesc fieldDesc = fieldMap.get(fieldName);
                    fields.add(createSchemaField(fieldDesc));
                }
            }
        } else {
            // All fields are included in dynamic schema
            for (String fieldName : fieldMap.keySet()) {
                FieldDesc fieldDesc = fieldMap.get(fieldName);
                fields.add(createSchemaField(fieldDesc));
            }
        }

        Schema schema = Schema.createRecord(targetSchemaName, null, null, false, fields);
        return schema;
    }

    protected Schema.Field createSchemaField(FieldDesc fieldDesc) {
        Schema avroFieldType = SchemaServiceImpl.inferSchemaForField(fieldDesc);
        Schema.Field avroField = new Schema.Field(fieldDesc.getName(), avroFieldType, null, null);
        return avroField;
    }

    protected Map<String, Object> getMapView(Object nsObject, Schema schema, TypeDesc typeDesc) {
        BeanInfo beanInfo = BeanManager.getBeanInfo(typeDesc.getTypeClass());

        Map<String, Object> valueMap = new HashMap<>();

        Map<String, FieldDesc> fieldMap = typeDesc.getFieldMap();

        Map<String, CustomFieldDesc> customFieldMap = new HashMap<>();
        for (Schema.Field field : schema.getFields()) {
            String fieldName = field.name();
            FieldDesc fieldDesc = fieldMap.get(fieldName);

            if (fieldDesc instanceof CustomFieldDesc) {
                customFieldMap.put(fieldName, (CustomFieldDesc) fieldDesc);
            } else {
                Object value = getProperty(nsObject, fieldDesc.getInternalName());
                valueMap.put(fieldName, value);
            }
        }

        if (!customFieldMap.isEmpty() &&
                beanInfo.getProperty("customFieldList") != null) {
            List<?> customFieldList = (List<?>) getProperty(nsObject, "customFieldList.customField");
            if (customFieldList != null && !customFieldList.isEmpty()) {
                for (Object customField : customFieldList) {
                    String scriptId = (String) getProperty(customField, "scriptId");
                    CustomFieldDesc customFieldInfo = customFieldMap.get(scriptId);
                    String fieldName = customFieldInfo.getName();
                    if (customFieldInfo != null) {
                        valueMap.put(fieldName, customField);
                    }
                }
            }
        }

        return valueMap;
    }

    protected Object readField(Map<String, Object> valueMap, FieldDesc fieldDesc) {
        String fieldName = fieldDesc.getName();
        ValueConverter valueConverter = getValueConverter(fieldDesc);
        if (fieldDesc instanceof CustomFieldDesc) {
            Object customField = valueMap.get(fieldName);
            if (customField != null) {
                Object value = getProperty(customField, "value");
                return valueConverter.convertInput(value);
            }
            return null;
        } else {
            Object value = valueMap.get(fieldName);
            return valueConverter.convertInput(value);
        }
    }

    protected Object writeField(Object nsObject, FieldDesc fieldDesc, Object value) {
        ValueConverter valueConverter = getValueConverter(fieldDesc);
        if (fieldDesc instanceof CustomFieldDesc) {
            CustomFieldDesc customFieldInfo = fieldDesc.asCustom();
            Object targetValue = valueConverter.convertOutput(value);

            if (targetValue != null) {
                CustomFieldRefType customFieldRefType = customFieldInfo.getCustomFieldType();

                Object customFieldListWrapper = getProperty(nsObject, "customFieldList");
                if (customFieldListWrapper == null) {
                    customFieldListWrapper = clientService.createType("CustomFieldList");
                    setProperty(nsObject, "customFieldList", customFieldListWrapper);
                }
                List<Object> customFieldList = (List<Object>) getProperty(customFieldListWrapper, "customField");

                Object customField = clientService.createType(customFieldRefType.getTypeName());
                setProperty(customField, "scriptId", customFieldInfo.getCustomizationRef().getScriptId());
                setProperty(customField, "internalId", customFieldInfo.getCustomizationRef().getInternalId());

                setProperty(customField, "value", targetValue);

                customFieldList.add(customField);

                return targetValue;
            }

        } else {
            Object targetValue = valueConverter.convertOutput(value);

            if (targetValue != null) {
                setProperty(nsObject, fieldDesc.getInternalName(), targetValue);
                return targetValue;
            }
        }

        return null;
    }

    protected ValueConverter<?, ?> getValueConverter(FieldDesc fieldDesc) {
        // TODO Improve performance

        if (fieldDesc instanceof CustomFieldDesc) {
            CustomFieldDesc customFieldInfo = (CustomFieldDesc) fieldDesc;
            CustomFieldRefType customFieldRefType = customFieldInfo.getCustomFieldType();

            if (customFieldRefType == CustomFieldRefType.BOOLEAN ||
                    customFieldRefType == CustomFieldRefType.LONG ||
                    customFieldRefType == CustomFieldRefType.DOUBLE ||
                    customFieldRefType == CustomFieldRefType.STRING) {
                return new IdentityValueConverter<>();
            } else if (customFieldRefType == CustomFieldRefType.DATE) {
                return new XMLGregorianCalendarValueConverter(datatypeFactory);
            }

        } else {
            Class<?> fieldType = fieldDesc.getValueType();

            if (fieldType == Boolean.TYPE || fieldType == Boolean.class ||
                    fieldType == Integer.TYPE || fieldType == Integer.class ||
                    fieldType == Long.TYPE || fieldType == Long.class ||
                    fieldType == Double.TYPE || fieldType == Double.class ||
                    fieldType == String.class) {
                return new IdentityValueConverter<>();
            } else if (fieldType == XMLGregorianCalendar.class) {
                return new XMLGregorianCalendarValueConverter(datatypeFactory);
            } else if (fieldType.isEnum()) {
                Class<Enum> enumClass = (Class<Enum>) fieldType;
                return new EnumValueConverter<>(enumClass, getEnumAccessor(enumClass));
            }
        }

        return new NullValueConverter<>();
    }

    public interface ValueConverter<T, U> {

        U convertInput(T value);

        T convertOutput(U value);
    }

    public static class NullValueConverter<T> implements ValueConverter<T, T> {

        @Override
        public T convertInput(T value) {
            return null;
        }

        @Override
        public T convertOutput(T value) {
            return null;
        }
    }

    public static class IdentityValueConverter<T> implements ValueConverter<T, T> {

        @Override
        public T convertInput(T value) {
            return value;
        }

        @Override
        public T convertOutput(T value) {
            return value;
        }
    }

    public static class EnumValueConverter<T extends Enum<T>> implements ValueConverter<T, String> {

        private final Class<T> clazz;
        private final EnumAccessor enumAccessor;

        public EnumValueConverter(Class<T> clazz, EnumAccessor enumAccessor) {
            this.clazz = clazz;
            this.enumAccessor = enumAccessor;
        }

        @Override
        public T convertOutput(String value) {
            if (value == null) {
                return null;
            }
            try {
                return (T) enumAccessor.mapFromString(value);
            } catch (IllegalArgumentException ex) {
                // Fallback to .valueOf(String)
                return Enum.valueOf(clazz, value);
            }
        }

        @Override
        public String convertInput(Enum enumValue) {
            if (enumValue == null) {
                return null;
            }
            try {
                return enumAccessor.mapToString(enumValue);
            } catch (IllegalArgumentException ex) {
                // Fallback to .name()
                return enumValue.name();
            }
        }
    }

    public static class XMLGregorianCalendarValueConverter implements ValueConverter<XMLGregorianCalendar, Long> {
        private DatatypeFactory datatypeFactory;

        public XMLGregorianCalendarValueConverter(DatatypeFactory datatypeFactory) {
            this.datatypeFactory = datatypeFactory;
        }

        @Override
        public XMLGregorianCalendar convertOutput(Long timestamp) {
            if (timestamp == null) {
                return null;
            }

            MutableDateTime dateTime = new MutableDateTime();
            dateTime.setMillis(timestamp);

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
        public Long convertInput(XMLGregorianCalendar xts) {
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

                return dateTime.getMillis();
            } catch (IllegalArgumentException e) {
                throw new ComponentException(e);
            }
        }
    }

}

