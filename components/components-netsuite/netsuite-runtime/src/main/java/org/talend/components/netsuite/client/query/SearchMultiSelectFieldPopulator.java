package org.talend.components.netsuite.client.query;

import java.util.List;

import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NsObject;

/**
 *
 */
public class SearchMultiSelectFieldPopulator<T> extends SearchFieldPopulator<T> {

    public SearchMultiSelectFieldPopulator(NetSuiteClientService clientService, String fieldType, Class<T> fieldClass) {
        super(clientService, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        NsObject<T> nsObject = fieldObject != null ? NsObject.wrap(fieldObject) : createField(internalId);

        List<Object> searchValue = (List<Object>) nsObject.get("searchValue");
        for (int i = 0; i < values.size(); i++) {
            NsObject<Object> item = NsObject.wrap(clientService.createType("ListOrRecordRef"));
            item.set("name", values.get(i));
            item.set("internalId", values.get(i));
            item.set("externalId", null);
            item.set("type", null);
            searchValue.add(item.getTarget());
        }

        nsObject.set("operator", clientService.getSearchFieldOperatorByName(fieldType, operatorName));

        return nsObject.getTarget();
    }
}
