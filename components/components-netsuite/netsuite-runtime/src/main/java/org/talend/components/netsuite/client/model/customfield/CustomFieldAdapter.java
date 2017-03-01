package org.talend.components.netsuite.client.model.customfield;

import org.talend.components.netsuite.client.model.BasicRecordType;

import static org.talend.components.netsuite.client.model.BeanUtils.getEnumAccessor;
import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;

/**
 *
 */
public abstract class CustomFieldAdapter<T> {
    protected BasicRecordType type;

    public CustomFieldAdapter(BasicRecordType type) {
        this.type = type;
    }

    public BasicRecordType getType() {
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
