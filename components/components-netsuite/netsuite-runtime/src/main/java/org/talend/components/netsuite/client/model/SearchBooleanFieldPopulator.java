package org.talend.components.netsuite.client.model;

import java.util.List;

import static org.talend.components.netsuite.client.NetSuiteFactory.setBeanProperty;

/**
 *
 */
public class SearchBooleanFieldPopulator<T> extends SearchFieldPopulator<T> {

    public SearchBooleanFieldPopulator(RuntimeModel runtimeInfoSet, String fieldType, Class<T> fieldClass) {
        super(runtimeInfoSet, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        T nsObject = fieldObject != null ? fieldObject : createField(internalId);
        if (values != null && values.size() != 0) {
            setBeanProperty(nsObject, "searchValue", Boolean.valueOf(values.get(0).toLowerCase()));
        }
        return nsObject;
    }
}
