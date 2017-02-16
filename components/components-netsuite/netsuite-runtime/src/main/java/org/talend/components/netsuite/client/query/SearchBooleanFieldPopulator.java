package org.talend.components.netsuite.client.query;

import java.util.List;

import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NsObject;

/**
 *
 */
public class SearchBooleanFieldPopulator<T> extends SearchFieldPopulator<T> {

    public SearchBooleanFieldPopulator(NetSuiteClientService clientService, String fieldType, Class<T> fieldClass) {
        super(clientService, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        NsObject<T> nsObject = fieldObject != null ? NsObject.wrap(fieldObject) : createField(internalId);
        if (values != null && values.size() != 0) {
            nsObject.set("searchValue", Boolean.valueOf(values.get(0).toLowerCase()));
        }
        return nsObject.getTarget();
    }
}
