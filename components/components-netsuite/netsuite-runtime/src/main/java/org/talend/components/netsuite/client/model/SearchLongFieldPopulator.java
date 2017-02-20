package org.talend.components.netsuite.client.model;

import java.util.List;

import static org.talend.components.netsuite.client.NetSuiteFactory.setBeanProperty;

/**
 *
 */
public class SearchLongFieldPopulator<T> extends SearchFieldPopulator<T> {

    public SearchLongFieldPopulator(RuntimeModelProvider runtimeModelProvider, String fieldType, Class<T> fieldClass) {
        super(runtimeModelProvider, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        T nsObject = fieldObject != null ? fieldObject : createField(internalId);

        if (values != null && values.size() != 0) {
            setBeanProperty(nsObject, "searchValue", Long.valueOf(Long.parseLong(values.get(0))));
            if (values.size() > 1) {
                setBeanProperty(nsObject, "searchValue2", Long.valueOf(Long.parseLong(values.get(1))));
            }
            setBeanProperty(nsObject, "operator", runtimeModelProvider.getSearchFieldOperatorByName(fieldType, operatorName));
        }

        return nsObject;
    }
}
