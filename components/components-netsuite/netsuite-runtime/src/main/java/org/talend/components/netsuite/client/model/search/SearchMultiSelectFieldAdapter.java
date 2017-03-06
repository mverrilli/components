package org.talend.components.netsuite.client.model.search;

import static org.talend.components.netsuite.client.model.BeanUtils.getSimpleProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;

import java.util.List;

import org.talend.components.netsuite.client.model.BasicMetaData;
import org.talend.components.netsuite.client.model.TypeUtils;

/**
 *
 */
public class SearchMultiSelectFieldAdapter<T> extends SearchFieldAdapter<T> {

    public SearchMultiSelectFieldAdapter(BasicMetaData metaData, SearchFieldType fieldType, Class<T> fieldClass) {
        super(metaData, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        T nsObject = fieldObject != null ? fieldObject : createField(internalId);

        List<Object> searchValue = (List<Object>) getSimpleProperty(nsObject, "searchValue");
        for (int i = 0; i < values.size(); i++) {
            Object item = TypeUtils.createInstance(metaData,"ListOrRecordRef");
            setProperty(item, "name", values.get(i));
            setProperty(item, "internalId", values.get(i));
            searchValue.add(item);
        }

        setProperty(nsObject, "operator", metaData.getSearchFieldOperatorByName(
                fieldType.getFieldTypeName(), operatorName));

        return nsObject;
    }
}
