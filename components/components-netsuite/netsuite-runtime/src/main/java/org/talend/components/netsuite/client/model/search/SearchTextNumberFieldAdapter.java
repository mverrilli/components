package org.talend.components.netsuite.client.model.search;

import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;

import java.util.List;

import org.talend.components.netsuite.client.model.BasicMetaData;

/**
 *
 */
public class SearchTextNumberFieldAdapter<T> extends SearchFieldAdapter<T> {

    public SearchTextNumberFieldAdapter(BasicMetaData metaData, SearchFieldType fieldType, Class<T> fieldClass) {
        super(metaData, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        T nsObject = fieldObject != null ? fieldObject : createField(internalId);

        if (values != null && values.size() != 0) {
            setProperty(nsObject, "searchValue", values.get(0));
            if (values.size() > 1) {
                setProperty(nsObject, "searchValue2", values.get(1));
            }
            setProperty(nsObject, "operator", metaData.getSearchFieldOperatorByName(fieldType.getFieldTypeName(), operatorName));
        }

        return nsObject;
    }
}
