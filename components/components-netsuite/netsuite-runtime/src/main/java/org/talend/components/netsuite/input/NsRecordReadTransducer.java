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
import org.talend.daikon.avro.converter.AvroConverter;

import static org.talend.components.netsuite.client.NetSuiteFactory.getBeanProperty;
import static org.talend.components.netsuite.client.NetSuiteFactory.toInitialLower;

/**
 *
 */
public class NsRecordReadTransducer {
    private Schema schema;
    private NetSuiteClientService clientService;

    protected transient Map<String, AvroConverter> fieldConverters;

    private transient AvroConverter[] fieldConverter;
    private transient TypeInfo typeInfo;
    private transient List<String> recordFieldNames;

    public NsRecordReadTransducer(NetSuiteClientService clientService) {
        this.clientService = clientService;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public IndexedRecord read(Object data) {
        initMapping(schema);

        Schema recordSchema = schema;
        GenericRecord indexedRecord = new GenericData.Record(recordSchema);

        for (String fieldName : recordFieldNames) {
            Schema.Field field = recordSchema.getField(fieldName);
            String propertyName = toInitialLower(fieldName);

            int fieldIndex = field.pos();
            Object value = getBeanProperty(data, propertyName);

            Object mappedValue = null;
            AvroConverter converter = fieldConverter[fieldIndex];
            if (converter != null) {
                mappedValue = converter.convertToAvro(value);
            }

            indexedRecord.put(fieldName, mappedValue);
        }

        return indexedRecord;
    }

    protected void initMapping(Schema schema) {
        if (typeInfo == null) {
            typeInfo = clientService.getTypeInfo(schema.getName());
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
    }
}
