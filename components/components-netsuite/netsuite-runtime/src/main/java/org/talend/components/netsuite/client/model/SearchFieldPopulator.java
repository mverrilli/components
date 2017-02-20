package org.talend.components.netsuite.client.model;

import java.util.List;

import org.talend.components.netsuite.beans.BeanInfo;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.beans.BeanManager;

import static org.talend.components.netsuite.client.NetSuiteFactory.setBeanProperty;

/**
 *
 */
public abstract class SearchFieldPopulator<T> {
    protected RuntimeModel runtimeInfoSet;
    protected String fieldType;
    protected Class<T> fieldClass;

    public SearchFieldPopulator(RuntimeModel runtimeInfoSet, String fieldType, Class<T> fieldClass) {
        this.runtimeInfoSet = runtimeInfoSet;
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

    protected T createField(String internalId) throws NetSuiteException {
        try {
            BeanInfo fieldTypeMetaData = BeanManager.getBeanInfo(fieldClass);
            T searchField = fieldClass.newInstance();
            if (fieldTypeMetaData.getProperty("internalId") != null && internalId != null) {
                setBeanProperty(searchField, "internalId", internalId);
            }
            return searchField;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

}
