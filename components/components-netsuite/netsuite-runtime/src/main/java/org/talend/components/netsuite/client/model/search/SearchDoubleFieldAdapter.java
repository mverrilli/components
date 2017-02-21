package org.talend.components.netsuite.client.model.search;

import java.util.List;

import org.talend.components.netsuite.client.model.MetaDataProvider;

import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;

/**
 *
 */
public class SearchDoubleFieldAdapter<T> extends SearchFieldAdapter<T> {

    public SearchDoubleFieldAdapter(MetaDataProvider metaDataProvider, String fieldType, Class<T> fieldClass) {
        super(metaDataProvider, fieldType, fieldClass);
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        T nsObject = fieldObject != null ? fieldObject : createField(internalId);

        if (values != null && values.size() != 0) {
            setProperty(nsObject, "searchValue", Double.valueOf(Double.parseDouble(values.get(0))));
            if (values.size() > 1) {
                setProperty(nsObject, "searchValue2", Double.valueOf(Double.parseDouble(values.get(1))));
            }
            setProperty(nsObject, "operator", metaDataProvider.getSearchFieldOperatorByName(fieldType, operatorName));
        }

        return nsObject;
    }
}
