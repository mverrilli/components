package org.talend.components.netsuite.client.query;

import java.util.List;

import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NsObject;

/**
 *
 */
public class SearchEnumMultiSelectFieldPopulator<T> extends SearchFieldPopulator<T> {

    public SearchEnumMultiSelectFieldPopulator(NetSuiteClientService clientService, String fieldType, Class<T> fieldClass) {
        super(clientService, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        NsObject<T> nsObject = fieldObject != null ? NsObject.wrap(fieldObject) : createField(internalId);

        List<String> searchValue = (List<String>) nsObject.get("searchValue");
        searchValue.addAll(values);
        nsObject.set("operator", clientService.getSearchFieldOperatorByName(fieldType, operatorName));

        return nsObject.getTarget();
    }
}
