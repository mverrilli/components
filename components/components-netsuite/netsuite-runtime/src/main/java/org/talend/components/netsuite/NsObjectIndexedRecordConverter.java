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
import org.talend.components.netsuite.client.NsObject;
import org.talend.components.netsuite.client.metadata.TypeDef;
import org.talend.components.netsuite.client.metadata.FieldDef;
import org.talend.daikon.avro.converter.AvroConverter;
import org.talend.daikon.avro.converter.IndexedRecordConverter;

/**
 *
 */
public class NsObjectIndexedRecordConverter implements IndexedRecordConverter<NsObject, IndexedRecord> {

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
    public Class<NsObject> getDatumClass() {
        return NsObject.class;
    }

    @Override
    public NsObject convertToDatum(IndexedRecord indexedRecord) {
        Schema schema = indexedRecord.getSchema();

        String typeName = schema.getName();
        try {
            Object object = clientService.createType(typeName);
            NsObject nsObject = NsObject.wrap(object);

            TypeDef typeDef = clientService.getTypeDef(typeName);

            List<String> nullFieldList = new ArrayList<>();

            for (Schema.Field field : schema.getFields()) {
                FieldDef fieldDef = typeDef.getField(field.name());
                AvroConverter converter = NetSuiteAvroRegistry.getInstance()
                        .getConverter(field, fieldDef.getValueType());

                if (converter != null) {
                    Object value = indexedRecord.get(field.pos());
                    Object nsValue = converter.convertToDatum(value);
                    if (nsValue != null) {
                        nsObject.set(field.name(), nsValue);
                    } else {
                        nullFieldList.add(fieldDef.getName());
                    }
                }
            }

            if (!nullFieldList.isEmpty()) {
                // TODO Handle null fields
            }

            return nsObject;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
//        throw new UnmodifiableAdapterException();
    }

    @Override
    public IndexedRecord convertToAvro(NsObject data) {
        Schema schema = getSchema();
        initMapping(schema);

        return new NsObjectIndexedRecord(data);
    }

    protected void initMapping(Schema schema) {
        if (names == null) {
            TypeDef typeDef = clientService.getTypeDef(getSchema().getName());
            fieldConverters = new HashMap<>(schema.getFields().size());
            for (Schema.Field field : schema.getFields()) {
                String fieldName = field.name();
                FieldDef fieldDef = typeDef.getField(fieldName);
                fieldConverters.put(fieldName, NetSuiteAvroRegistry.getInstance()
                        .getConverter(field, fieldDef.getValueType()));
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
        private NsObject record;

        NsObjectIndexedRecord(NsObject record) {
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
            Object value = record.get(names[i]);
            AvroConverter converter = fieldConverter[i];
            if (converter == null) {
                return null;
            }
            return converter.convertToAvro(value);
        }
    }
}
