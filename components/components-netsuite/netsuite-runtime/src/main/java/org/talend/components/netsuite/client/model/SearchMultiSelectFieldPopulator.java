package org.talend.components.netsuite.client.model;

import java.util.List;

import static org.talend.components.netsuite.client.NetSuiteFactory.getBeanProperty;
import static org.talend.components.netsuite.client.NetSuiteFactory.setBeanProperty;

/**
 *
 */
public class SearchMultiSelectFieldPopulator<T> extends SearchFieldPopulator<T> {

    public SearchMultiSelectFieldPopulator(RuntimeModelProvider runtimeModelProvider, String fieldType, Class<T> fieldClass) {
        super(runtimeModelProvider, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        T nsObject = fieldObject != null ? fieldObject : createField(internalId);

        List<Object> searchValue = (List<Object>) getBeanProperty(nsObject, "searchValue");
        for (int i = 0; i < values.size(); i++) {
            Object item = runtimeModelProvider.createType("ListOrRecordRef");
            setBeanProperty(item, "name", values.get(i));
            setBeanProperty(item, "internalId", values.get(i));
            setBeanProperty(item, "externalId", null);
            setBeanProperty(item, "type", null);
            searchValue.add(item);
        }

        setBeanProperty(nsObject, "operator", runtimeModelProvider.getSearchFieldOperatorByName(fieldType, operatorName));

        return nsObject;
    }
}
