package org.talend.components.netsuite.client.model.search;

import static org.talend.components.netsuite.client.model.beans.Beans.getSimpleProperty;
import static org.talend.components.netsuite.client.model.beans.Beans.setProperty;

import java.util.List;

import org.talend.components.netsuite.client.model.BasicMetaData;

/**
 *
 */
public class SearchEnumMultiSelectFieldAdapter<T> extends SearchFieldAdapter<T> {

    public SearchEnumMultiSelectFieldAdapter(BasicMetaData metaData, SearchFieldType fieldType, Class<T> fieldClass) {
        super(metaData, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        T nsObject = fieldObject != null ? fieldObject : createField(internalId);

        List<String> searchValue = (List<String>) getSimpleProperty(nsObject, "searchValue");
        searchValue.addAll(values);
        setProperty(nsObject, "operator", metaData.getSearchFieldOperatorByName(fieldType.getFieldTypeName(), operatorName));

        return nsObject;
    }
}
