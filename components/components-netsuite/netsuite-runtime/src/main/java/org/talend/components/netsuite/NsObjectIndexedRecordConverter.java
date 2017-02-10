package org.talend.components.netsuite;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.components.netsuite.client.metadata.NsTypeDef;
import org.talend.components.netsuite.client.metadata.NsFieldDef;
import org.talend.components.netsuite.client.NsObject;
import org.talend.daikon.avro.converter.AvroConverter;
import org.talend.daikon.avro.converter.IndexedRecordConverter;

/**
 *
 */
public class NsObjectIndexedRecordConverter implements IndexedRecordConverter<NsObject, IndexedRecord> {

    private Schema schema;
    private NetSuiteMetaData metaData;

    protected transient Map<String, AvroConverter> fieldConverters;

    private transient String names[];
    private transient AvroConverter[] fieldConverter;

    public NsObjectIndexedRecordConverter(NetSuiteMetaData metaData) {
        this.metaData = metaData;
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
        throw new UnmodifiableAdapterException();
    }

    @Override
    public IndexedRecord convertToAvro(NsObject data) {
        if (names == null) {
            Schema schema = getSchema();
            NsTypeDef entityInfo = metaData.getTypeDef(getSchema().getName());
            fieldConverters = new HashMap<>(schema.getFields().size());
            for (Schema.Field field : schema.getFields()) {
                String fieldName = field.name();
                NsFieldDef fieldInfo = entityInfo.getField(fieldName);
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
        return new NsObjectIndexedRecord(data);
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
