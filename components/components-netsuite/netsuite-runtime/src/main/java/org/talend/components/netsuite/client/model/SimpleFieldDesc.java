package org.talend.components.netsuite.client.model;

/**
 *
 */
public class SimpleFieldDesc extends FieldDesc {
    protected String propertyName;

    public SimpleFieldDesc() {
    }

    public SimpleFieldDesc(String name, Class valueType, boolean key, boolean nullable) {
        this.name = name;
        this.valueType = valueType;
        this.key = key;
        this.nullable = nullable;
    }

    public String getInternalName() {
        return propertyName != null ? propertyName : super.getInternalName();
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

}
