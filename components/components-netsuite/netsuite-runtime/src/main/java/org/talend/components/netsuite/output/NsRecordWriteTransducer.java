package org.talend.components.netsuite.output;

import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;

import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.NetSuiteAvroRegistry;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.FieldInfo;
import org.talend.components.netsuite.client.model.TypeInfo;
import org.talend.daikon.avro.converter.AvroConverter;

/**
 *
 */
public class NsRecordWriteTransducer {
    private NetSuiteClientService clientService;
    private String typeName;

    public NsRecordWriteTransducer(NetSuiteClientService clientService, String typeName) {
        this.clientService = clientService;
        this.typeName = typeName;
    }

    public Object write(IndexedRecord indexedRecord) {
        Schema schema = indexedRecord.getSchema();

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
                        setProperty(object, field.name(), nsValue);
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
    }
}
