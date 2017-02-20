package org.talend.components.netsuite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.TypeInfo;
import org.talend.components.netsuite.client.model.FieldInfo;
import org.talend.daikon.avro.converter.AvroConverter;
import org.talend.daikon.avro.converter.IndexedRecordConverter;

import static org.talend.components.netsuite.client.NetSuiteFactory.getBeanProperty;
import static org.talend.components.netsuite.client.NetSuiteFactory.setBeanProperty;

/**
 *
 */
public class NsObjectIndexedRecordConverter implements IndexedRecordConverter<Object, IndexedRecord> {

    private Schema schema;
    private NetSuiteClientService clientService;

    protected transient Map<String, AvroConverter> fieldConverters;

    private transient String names[];
    private transient AvroConverter[] fieldConverter;

    public NsObjectIndexedRecordConverter(NetSuiteClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public Schema getSchema() {
        return schema;
    }

    @Override
    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    @Override
    public Class<Object> getDatumClass() {
        return Object.class;
    }

    @Override
    public Object convertToDatum(IndexedRecord indexedRecord) {
        Schema schema = indexedRecord.getSchema();

        String typeName = schema.getName();
        try {
            Object object = clientService.createType(typeName);

            TypeInfo typeInfo = clientService.getTypeInfo(typeName);

            List<String> nullFieldList = new ArrayList<>();

            for (Schema.Field field : schema.getFields()) {
                FieldInfo fieldInfo = typeInfo.getField(field.name());
                AvroConverter converter = NetSuiteAvroRegistry.getInstance()
                        .getConverter(field, fieldInfo.getValueType());

                if (converter != null) {
                    Object value = indexedRecord.get(field.pos());
                    Object nsValue = converter.convertToDatum(value);
                    if (nsValue != null) {
                        setBeanProperty(object, field.name(), nsValue);
                    } else {
                        nullFieldList.add(fieldInfo.getName());
                    }
                }
            }

            if (!nullFieldList.isEmpty()) {
                // TODO Handle null fields
            }

            return object;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
//        throw new UnmodifiableAdapterException();
    }

    @Override
    public IndexedRecord convertToAvro(Object data) {
        Schema schema = getSchema();
        initMapping(schema);

        return new NsObjectIndexedRecord(data);
    }

    protected void initMapping(Schema schema) {
        if (names == null) {
            TypeInfo typeInfo = clientService.getTypeInfo(getSchema().getName());
            fieldConverters = new HashMap<>(schema.getFields().size());
            for (Schema.Field field : schema.getFields()) {
                String fieldName = field.name();
                FieldInfo fieldInfo = typeInfo.getField(fieldName);
                fieldConverters.put(fieldName, NetSuiteAvroRegistry.getInstance()
                        .getConverter(field, fieldInfo.getValueType()));
            }

            names = new String[getSchema().getFields().size()];
            fieldConverter = new AvroConverter[names.length];
            for (int j = 0; j < names.length; j++) {
                Schema.Field f = getSchema().getFields().get(j);
                String fieldName = f.name();
                names[j] = fieldName;
                fieldConverter[j] = fieldConverters.get(fieldName);
            }
        }
    }

    private class NsObjectIndexedRecord implements IndexedRecord {
        private Object record;

        NsObjectIndexedRecord(Object record) {
            this.record = record;
        }

        @Override
        public Schema getSchema() {
            return NsObjectIndexedRecordConverter.this.getSchema();
        }

        @Override
        public void put(int i, Object o) {
            throw new UnmodifiableAdapterException();
        }

        @Override
        public Object get(int i) {
            Object value = getBeanProperty(record, names[i]);
            AvroConverter converter = fieldConverter[i];
            if (converter == null) {
                return null;
            }
            return converter.convertToAvro(value);
        }
    }
}
