package org.talend.components.netsuite.client.query;

import java.util.List;

import org.talend.components.netsuite.client.NetSuiteClientService;

import static org.talend.components.netsuite.client.NetSuiteFactory.getBeanProperty;
import static org.talend.components.netsuite.client.NetSuiteFactory.setBeanProperty;

/**
 *
 */
public class SearchEnumMultiSelectFieldPopulator<T> extends SearchFieldPopulator<T> {

    public SearchEnumMultiSelectFieldPopulator(NetSuiteClientService clientService, String fieldType, Class<T> fieldClass) {
        super(clientService, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        T nsObject = fieldObject != null ? fieldObject : createField(internalId);

        List<String> searchValue = (List<String>) getBeanProperty(nsObject, "searchValue");
        searchValue.addAll(values);
        setBeanProperty(nsObject, "operator", clientService.getSearchFieldOperatorByName(fieldType, operatorName));

        return nsObject;
    }
}
