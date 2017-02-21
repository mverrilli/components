package org.talend.components.netsuite.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.IndexedRecord;
import org.talend.components.netsuite.NetSuiteAvroRegistry;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.model.TypeInfo;
import org.talend.components.netsuite.client.model.FieldInfo;
import org.talend.daikon.avro.AvroUtils;
import org.talend.daikon.avro.converter.AvroConverter;

import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.toInitialLower;

/**
 *
 */
public class NsRecordReadTransducer {
    private NetSuiteClientService clientService;
    private Schema schema;
    private Schema runtimeSchema;

    protected transient Map<String, AvroConverter> fieldConverters;

    private transient AvroConverter[] fieldConverter;
    private transient TypeInfo typeInfo;
    private transient List<String> recordFieldNames;

    public NsRecordReadTransducer(NetSuiteClientService clientService, Schema schema) {
        this.clientService = clientService;
        this.schema = schema;
    }

    public Schema getSchema() {
        return schema;
    }

    public IndexedRecord read(Object data) {
        prepare(data);

        GenericRecord indexedRecord = new GenericData.Record(runtimeSchema);

        for (String fieldName : recordFieldNames) {
            Schema.Field field = runtimeSchema.getField(fieldName);
            String propertyName = toInitialLower(fieldName);

            int fieldIndex = field.pos();
            Object value = getProperty(data, propertyName);

            Object mappedValue = null;
            AvroConverter converter = fieldConverter[fieldIndex];
            if (converter != null) {
                mappedValue = converter.convertToAvro(value);
            }

            indexedRecord.put(fieldName, mappedValue);
        }

        return indexedRecord;
    }

    protected void prepare(Object nsObject) {
        if (runtimeSchema != null) {
            return;
        }

        if (AvroUtils.isIncludeAllFields(schema)) {
            typeInfo = clientService.getTypeInfo(nsObject.getClass());
            runtimeSchema = getDynamicSchema(nsObject, typeInfo.getTypeName(), schema);
        } else {
            typeInfo = clientService.getTypeInfo(schema.getName());
            runtimeSchema = schema;
        }

        recordFieldNames = new ArrayList<>(typeInfo.getFieldMap().keySet());

        fieldConverters = new HashMap<>(schema.getFields().size());
        for (Schema.Field field : schema.getFields()) {
            String fieldName = field.name();
            FieldInfo fieldInfo = typeInfo.getField(fieldName);
            fieldConverters.put(fieldName, NetSuiteAvroRegistry.getInstance()
                    .getConverter(field, fieldInfo.getValueType()));
        }

        fieldConverter = new AvroConverter[schema.getFields().size()];
        for (Schema.Field field : schema.getFields()) {
            String fieldName = field.name();
            fieldConverter[field.pos()] = fieldConverters.get(fieldName);
        }
    }

    protected Schema getDynamicSchema(Object nsObject, String schemaName, Schema designSchema) {
        TypeInfo typeInfo = clientService.getTypeInfo(nsObject.getClass());

        List<String> fieldNames = new ArrayList<>();
        for (FieldInfo fieldInfo : typeInfo.getFields()) {
            if (fieldInfo.getName().equals("customFieldList")) {
                continue;
            }
            fieldNames.add(fieldInfo.getName());
        }
        List<?> customFieldList = (List<?>) getProperty(nsObject, "customFieldList.customField");
        for (Object customField : customFieldList) {
            String fieldName = (String) getProperty(customField, "scriptId");
            fieldNames.add(fieldName);
        }

//        String dynamicPosProp = designSchema.getProp(DiSchemaConstants.TALEND6_DYNAMIC_COLUMN_POSITION);
//        List<Schema.Field> fields = new ArrayList<>();
//        if (dynamicPosProp != null) {
//            int dynPos = Integer.parseInt(dynamicPosProp);
//            int dynamicColumnSize = columnsName.length - designSchema.getFields().size();
//            String defaultValue = null;
//            if (designSchema.getFields().size() > 0) {
//                for (Schema.Field field : designSchema.getFields()) {
//                    // Dynamic column is first or middle column in design schema
//                    if (dynPos == field.pos()) {
//                        for (int i = 0; i < dynamicColumnSize; i++) {
//                            // Add dynamic schema fields
//                            fields.add(getDefaultField(columnsName[i + dynPos], defaultValue));
//                        }
//                    }
//                    // Add fields of design schema
//                    Schema.Field avroField = new Schema.Field(field.name(), field.schema(), null, field.defaultVal());
//                    Map<String, Object> fieldProps = field.getObjectProps();
//                    for (String propName : fieldProps.keySet()) {
//                        Object propValue = fieldProps.get(propName);
//                        if (propValue != null) {
//                            avroField.addProp(propName, propValue);
//                        }
//                    }
//                    fields.add(avroField);
//                    // Dynamic column is last column in design schema
//                    if (field.pos() == (designSchema.getFields().size() - 1) && dynPos == (field.pos() + 1)) {
//                        for (int i = 0; i < dynamicColumnSize; i++) {
//                            // Add dynamic schema fields
//                            fields.add(getDefaultField(columnsName[i + dynPos], defaultValue));
//                        }
//                    }
//                }
//            } else {
//                // All fields are included in dynamic schema
//                for (String columnName : columnsName) {
//                    fields.add(getDefaultField(columnName, defaultValue));
//                }
//            }
//        }
//        Schema schema = Schema.createRecord(schemaName, null, null, false, fields);
//        return schema;
        return null;
    }
}
