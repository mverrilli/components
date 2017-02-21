package org.talend.components.netsuite.client.model.custom;

import org.talend.components.netsuite.client.model.MetaDataProvider;

import static org.talend.components.netsuite.client.model.BeanUtils.getEnumAccessor;
import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;

/**
 *
 */
public abstract class CustomFieldAdapter<T> {
    protected MetaDataProvider metaDataProvider;
    protected String type;

    public CustomFieldAdapter(MetaDataProvider metaDataProvider, String type) {
        this.metaDataProvider = metaDataProvider;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract boolean appliesTo(String recordTypeName, T field);

    public abstract CustomFieldRefType apply(T field);

    protected CustomFieldRefType getFieldType(T field) {
        Enum<?> fieldTypeEnumValue = (Enum<?>) getProperty(field, "fieldType");
        String fieldTypeName = getEnumAccessor(fieldTypeEnumValue.getClass()).mapToString(fieldTypeEnumValue);
        return metaDataProvider.getCustomFieldRefType(fieldTypeName);
    }
}
