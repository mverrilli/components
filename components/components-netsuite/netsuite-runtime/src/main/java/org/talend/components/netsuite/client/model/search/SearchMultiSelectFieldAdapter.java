package org.talend.components.netsuite.client.model.search;

import java.util.List;

import org.talend.components.netsuite.client.model.MetaData;

import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;

/**
 *
 */
public class SearchMultiSelectFieldAdapter<T> extends SearchFieldAdapter<T> {

    public SearchMultiSelectFieldAdapter(MetaData metaData, String fieldType, Class<T> fieldClass) {
        super(metaData, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        T nsObject = fieldObject != null ? fieldObject : createField(internalId);

        List<Object> searchValue = (List<Object>) getProperty(nsObject, "searchValue");
        for (int i = 0; i < values.size(); i++) {
            Object item = metaData.createType("ListOrRecordRef");
            setProperty(item, "name", values.get(i));
            setProperty(item, "internalId", values.get(i));
            searchValue.add(item);
        }

        setProperty(nsObject, "operator", metaData.getSearchFieldOperatorByName(fieldType, operatorName));

        return nsObject;
    }
}
