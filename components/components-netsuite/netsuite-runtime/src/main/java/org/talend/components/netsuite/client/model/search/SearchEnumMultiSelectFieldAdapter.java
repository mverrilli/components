package org.talend.components.netsuite.client.model.search;

import java.util.List;

import org.talend.components.netsuite.client.model.BasicMetaData;

import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;

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

        List<String> searchValue = (List<String>) getProperty(nsObject, "searchValue");
        searchValue.addAll(values);
        setProperty(nsObject, "operator", metaData.getSearchFieldOperatorByName(fieldType.getFieldTypeName(), operatorName));

        return nsObject;
    }
}
