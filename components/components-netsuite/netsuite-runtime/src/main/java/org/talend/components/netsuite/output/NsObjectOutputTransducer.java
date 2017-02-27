package org.talend.components.netsuite.output;

import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;

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
import org.talend.components.netsuite.client.model.RecordTypeDesc;
import org.talend.components.netsuite.client.model.RecordTypeInfo;
import org.talend.components.netsuite.client.model.TypeDesc;

/**
 *
 */
public class NsObjectOutputTransducer extends NsObjectTransducer {
    protected String typeName;
    protected boolean reference;

    protected TypeDesc typeDesc;
    protected Map<String, FieldDesc> fieldMap;
    protected BeanInfo beanInfo;

    protected String referencedTypeName;
    protected RecordTypeInfo referencedRecordTypeInfo;

    public NsObjectOutputTransducer(NetSuiteClientService clientService, String typeName) {
        this(clientService, typeName, false);
    }

    public NsObjectOutputTransducer(NetSuiteClientService clientService, String typeName, boolean reference) {
        super(clientService);

        this.typeName = typeName;
        this.reference = reference;
    }

    protected void prepare() {
        if (typeDesc != null) {
            return;
        }

        if (reference) {
            referencedTypeName = typeName;
            referencedRecordTypeInfo = clientService.getRecordType(referencedTypeName);
            typeDesc = clientService.getTypeInfo("RecordRef");
        } else {
            typeDesc = clientService.getTypeInfo(typeName);
        }

        beanInfo = BeanManager.getBeanInfo(typeDesc.getTypeClass());
        fieldMap = typeDesc.getFieldMap();
    }

    public Object write(IndexedRecord indexedRecord) {
        prepare();

        Schema schema = indexedRecord.getSchema();

        try {
            Object nsObject = clientService.createType(typeDesc.getTypeName());

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

            if (reference) {
                if ("RecordRef".equals(typeDesc.getTypeName())) {
                    FieldDesc recTypeFieldDesc = typeDesc.getField("Type");
                    RecordTypeDesc recordTypeDesc = referencedRecordTypeInfo.getRecordType();
                    writeField(nsObject, recTypeFieldDesc, recordTypeDesc.getType());
                }
            }

            return nsObject;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }
}
