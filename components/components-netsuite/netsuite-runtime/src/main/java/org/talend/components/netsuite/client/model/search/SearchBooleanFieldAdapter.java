package org.talend.components.netsuite.client.model.search;

import java.util.List;

import org.talend.components.netsuite.client.model.MetaData;

import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;

/**
 *
 */
public class SearchBooleanFieldAdapter<T> extends SearchFieldAdapter<T> {

    public SearchBooleanFieldAdapter(MetaData metaData, String fieldType, Class<T> fieldClass) {
        super(metaData, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        T nsObject = fieldObject != null ? fieldObject : createField(internalId);
        if (values != null && values.size() != 0) {
            setProperty(nsObject, "searchValue", Boolean.valueOf(values.get(0).toLowerCase()));
        }
        return nsObject;
    }
}
