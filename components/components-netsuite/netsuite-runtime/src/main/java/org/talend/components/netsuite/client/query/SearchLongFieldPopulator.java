package org.talend.components.netsuite.client.query;

import java.util.List;

import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NsObject;

/**
 *
 */
public class SearchLongFieldPopulator<T> extends SearchFieldPopulator<T> {

    public SearchLongFieldPopulator(NetSuiteClientService clientService, String fieldType, Class<T> fieldClass) {
        super(clientService, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        NsObject<T> nsObject = fieldObject != null ? NsObject.wrap(fieldObject) : createField(internalId);
        if (values != null && values.size() != 0) {
            nsObject.set("searchValue", Long.valueOf(Long.parseLong(values.get(0))));
            if (values.size() > 1) {
                nsObject.set("searchValue2", Long.valueOf(Long.parseLong(values.get(1))));
            }
            nsObject.set("operator", clientService.getSearchFieldOperatorByName(fieldType, operatorName));
        }
        return nsObject.getTarget();
    }
}
