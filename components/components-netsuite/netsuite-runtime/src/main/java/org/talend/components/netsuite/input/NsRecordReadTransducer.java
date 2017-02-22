package org.talend.components.netsuite.input;

import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.toInitialLower;

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
import org.talend.components.netsuite.client.model.FieldInfo;
import org.talend.components.netsuite.client.model.TypeInfo;
import org.talend.components.netsuite.runtime.SchemaServiceImpl;
import org.talend.daikon.avro.AvroUtils;
import org.talend.daikon.avro.converter.AvroConverter;
import org.talend.daikon.di.DiSchemaConstants;

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
        String typeName = designSchema.getName();
        TypeInfo typeInfo = clientService.getTypeInfo(typeName, true);
        Map<String, FieldInfo> fieldMap = typeInfo.getFieldMap();

        String dynamicPosProp = designSchema.getProp(DiSchemaConstants.TALEND6_DYNAMIC_COLUMN_POSITION);
        List<Schema.Field> fields = new ArrayList<>();

        if (dynamicPosProp != null) {
            List<FieldInfo> designFields = new ArrayList<>(designSchema.getFields().size());
            for (Schema.Field field : designSchema.getFields()) {
                String fieldName = field.name();
                FieldInfo fieldInfo = fieldMap.get(fieldName);
                designFields.add(fieldInfo);
            }

            int dynPos = Integer.parseInt(dynamicPosProp);
            int dynamicColumnSize = fieldMap.size() - designSchema.getFields().size();

            if (designSchema.getFields().size() > 0) {
                for (Schema.Field field : designSchema.getFields()) {
                    // Dynamic column is first or middle column in design schema
                    if (dynPos == field.pos()) {
                        for (int i = 0; i < dynamicColumnSize; i++) {
                            // Add dynamic schema fields
                            FieldInfo fieldInfo = designFields.get(i + dynPos);
                            fields.add(createField(fieldInfo));
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
                            FieldInfo fieldInfo = designFields.get(i + dynPos);
                            fields.add(createField(fieldInfo));
                        }
                    }
                }
            } else {
                // All fields are included in dynamic schema
                for (String fieldName : fieldMap.keySet()) {
                    FieldInfo fieldInfo = fieldMap.get(fieldName);
                    fields.add(createField(fieldInfo));
                }
            }
        }

        Schema schema = Schema.createRecord(schemaName, null, null, false, fields);
        return schema;
//        return null;
    }

    private Schema.Field createField(FieldInfo fieldInfo) {
        Schema avroFieldType = SchemaServiceImpl.inferSchemaForField(fieldInfo);
        Schema.Field avroField = new Schema.Field(fieldInfo.getName(), avroFieldType, null, null);
        return avroField;
    }
}
