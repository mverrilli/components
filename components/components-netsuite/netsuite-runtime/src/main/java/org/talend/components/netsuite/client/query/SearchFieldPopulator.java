package org.talend.components.netsuite.client.query;

import java.util.List;

import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.NsObject;
import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.model.TypeManager;

/**
 *
 */
public abstract class SearchFieldPopulator<T> {
    protected NetSuiteClientService clientService;
    protected String fieldType;
    protected Class<T> fieldClass;

    public SearchFieldPopulator(NetSuiteClientService clientService, String fieldType, Class<T> fieldClass) {
        this.clientService = clientService;
        this.fieldType = fieldType;
        this.fieldClass = fieldClass;
    }

    public T populate(String operatorName, List<String> values) {
        return populate(null, null, operatorName, values);
    }

    public T populate(String internalId, String operatorName, List<String> values) {
        return populate(null, internalId, operatorName, values);
    }

    public abstract T populate(T fieldObject, String internalId, String operatorName, List<String> values);

    protected NsObject<T> createField(String internalId) throws NetSuiteException {
        try {
            TypeInfo fieldTypeMetaData = TypeManager.forClass(fieldClass);
            NsObject<T> searchField = NsObject.wrap(fieldClass.newInstance());
            if (fieldTypeMetaData.getProperty("internalId") != null && internalId != null) {
                searchField.set("internalId", internalId);
            }
            return searchField;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

}
