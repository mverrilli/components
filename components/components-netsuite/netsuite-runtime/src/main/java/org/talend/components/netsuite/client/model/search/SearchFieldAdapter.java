package org.talend.components.netsuite.client.model.search;

import java.util.List;

import org.talend.components.netsuite.beans.BeanInfo;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.beans.BeanManager;
import org.talend.components.netsuite.client.model.MetaData;

import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;

/**
 *
 */
public abstract class SearchFieldAdapter<T> {
    protected MetaData metaData;
    protected String fieldType;
    protected Class<T> fieldClass;

    public SearchFieldAdapter(MetaData metaData, String fieldType, Class<T> fieldClass) {
        this.metaData = metaData;
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
                setProperty(searchField, "internalId", internalId);
            }
            return searchField;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

}
