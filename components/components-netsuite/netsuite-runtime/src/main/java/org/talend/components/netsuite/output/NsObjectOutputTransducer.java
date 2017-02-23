package org.talend.components.netsuite.output;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.NsObjectTransducer;
import org.talend.components.netsuite.beans.BeanInfo;
import org.talend.components.netsuite.beans.BeanManager;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.FieldDesc;
import org.talend.components.netsuite.client.model.TypeDesc;

import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;

/**
 *
 */
public class NsObjectOutputTransducer extends NsObjectTransducer {
    private String typeName;

    public NsObjectOutputTransducer(NetSuiteClientService clientService, String typeName) {
        super(clientService);

        this.typeName = typeName;
    }

    public Object write(IndexedRecord indexedRecord) {
        Schema schema = indexedRecord.getSchema();

        try {
            Object nsObject = clientService.createType(typeName);

            TypeDesc typeDesc = clientService.getTypeInfo(typeName);
            BeanInfo beanInfo = BeanManager.getBeanInfo(typeDesc.getTypeClass());
            Map<String, FieldDesc> fieldMap = typeDesc.getFieldMap();

            Set<String> nullFieldNames = new HashSet<>();

            for (Schema.Field field : schema.getFields()) {
                String fieldName = field.name();
                FieldDesc fieldDesc = fieldMap.get(fieldName);

                Object value = indexedRecord.get(field.pos());
                Object result = writeField(nsObject, fieldDesc, value);
                if (result == null) {
                    nullFieldNames.add(fieldName);
                }
            }

            if (!nullFieldNames.isEmpty() && beanInfo.getProperty("nullFieldList") != null) {
                Object nullFieldListWrapper = getProperty(nsObject, "nullFieldList");
                if (nullFieldListWrapper == null) {
                    nullFieldListWrapper = clientService.createType("NullField");
                    setProperty(nsObject, "nullFieldList", nullFieldListWrapper);
                }
                List<String> nullFields = (List<String>) getProperty(nullFieldListWrapper, "name");
                nullFields.addAll(nullFieldNames);
            }

            return nsObject;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }
}
