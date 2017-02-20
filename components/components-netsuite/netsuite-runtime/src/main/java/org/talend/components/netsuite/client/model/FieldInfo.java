package org.talend.components.netsuite.client.model;

/**
 *
 */
public class FieldInfo {
    private String name;
    private Class valueType;
    private boolean key;
    private boolean nullable;

    public FieldInfo(String name, Class valueType, boolean key, boolean nullable) {
        this.name = name;
        this.valueType = valueType;
        this.key = key;
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public Class getValueType() {
        return valueType;
    }

    public int getLength() {
        return 0;
    }

    public boolean isKey() {
        return key;
    }

    public boolean isNullable() {
        return nullable;
    }
}