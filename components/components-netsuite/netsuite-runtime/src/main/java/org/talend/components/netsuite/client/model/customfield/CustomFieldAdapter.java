package org.talend.components.netsuite.client.model.customfield;

import org.talend.components.netsuite.client.model.MetaData;

import static org.talend.components.netsuite.client.model.BeanUtils.getEnumAccessor;
import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;

/**
 *
 */
public abstract class CustomFieldAdapter<T> {
    protected MetaData metaData;
    protected String type;

    public CustomFieldAdapter(String type) {
        this.metaData = metaData;
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
        return CustomFieldRefType.getByCustomizationTypeOrDefault(fieldTypeName);
    }
}