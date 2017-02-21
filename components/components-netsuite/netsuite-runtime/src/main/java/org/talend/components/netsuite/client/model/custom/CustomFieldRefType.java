package org.talend.components.netsuite.client.model.custom;

/**
 *
 */
public enum CustomFieldRefType {
    BOOLEAN("BooleanCustomFieldRef"),
    DOUBLE("DoubleCustomFieldRef"),
    LONG("LongCustomFieldRef"),
    STRING("StringCustomFieldRef"),
    DATE("DateCustomFieldRef"),
    SELECT("SelectCustomFieldRef"),
    MULTI_SELECT("MultiSelectCustomFieldRef");

    private String typeName;

    CustomFieldRefType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
