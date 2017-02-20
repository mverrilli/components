package org.talend.components.netsuite.client.query;

import java.util.List;

import org.talend.components.netsuite.client.NetSuiteClientService;

import static org.talend.components.netsuite.client.NetSuiteFactory.setBeanProperty;

/**
 *
 */
public class SearchBooleanFieldPopulator<T> extends SearchFieldPopulator<T> {

    public SearchBooleanFieldPopulator(NetSuiteClientService clientService, String fieldType, Class<T> fieldClass) {
        super(clientService, fieldType, fieldClass);
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
