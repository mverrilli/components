package org.talend.components.netsuite.client.model.customfield;

import static org.talend.components.netsuite.client.model.BeanUtils.getEnumAccessor;
import static org.talend.components.netsuite.client.model.BeanUtils.getSimpleProperty;

import org.talend.components.netsuite.client.model.BasicRecordType;

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
        Enum<?> fieldTypeEnumValue = (Enum<?>) getSimpleProperty(field, "fieldType");
        String fieldTypeName = getEnumAccessor(fieldTypeEnumValue.getClass()).mapToString(fieldTypeEnumValue);
        return CustomFieldRefType.getByCustomizationTypeOrDefault(fieldTypeName);
    }
}
