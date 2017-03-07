package org.talend.components.netsuite.output;

import static org.talend.components.netsuite.client.model.BeanUtils.getSimpleProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.setSimpleProperty;

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
import org.talend.components.netsuite.client.model.RefType;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.client.model.TypeUtils;

/**
 *
 */
public class NsObjectOutputTransducer extends NsObjectTransducer {
    protected String typeName;
    protected boolean reference;

    protected TypeDesc typeDesc;
    protected Map<String, FieldDesc> fieldMap;
    protected BeanInfo beanInfo;

    protected RefType refType;

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
            refType = RefType.RECORD_REF;
            typeDesc = clientService.getTypeInfo(refType.getTypeName());
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
            Object nsObject = TypeUtils.createInstance(
                    clientService.getBasicMetaData(), typeDesc.getTypeName());

            Set<String> nullFieldNames = new HashSet<>();

            for (Schema.Field field : schema.getFields()) {
                String fieldName = field.name();
                FieldDesc fieldDesc = fieldMap.get(fieldName);

                if (fieldDesc == null) {
                    continue;
                }

                Object value = indexedRecord.get(field.pos());
                Object result = writeField(nsObject, fieldDesc, value);
                if (result == null) {
                    nullFieldNames.add(fieldName);
                }
            }

            if (!nullFieldNames.isEmpty() && beanInfo.getProperty("nullFieldList") != null) {
                Object nullFieldListWrapper = getSimpleProperty(nsObject, "nullFieldList");
                if (nullFieldListWrapper == null) {
                    nullFieldListWrapper = TypeUtils.createInstance(
                            clientService.getBasicMetaData(),"NullField");
                    setSimpleProperty(nsObject, "nullFieldList", nullFieldListWrapper);
                }
                List<String> nullFields = (List<String>) getSimpleProperty(nullFieldListWrapper, "name");
                nullFields.addAll(nullFieldNames);
            }

            if (reference) {
                if (refType == RefType.RECORD_REF) {
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
