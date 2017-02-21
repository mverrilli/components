package org.talend.components.netsuite.client.model;

/**
 *
 */
public class FieldInfo {
    private String name;
    private Class valueType;
    private boolean key;
    private boolean nullable;
    private int length;

    public FieldInfo() {
    }

    public FieldInfo(String name, Class valueType, boolean key, boolean nullable) {
        this.name = name;
        this.valueType = valueType;
        this.key = key;
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getValueType() {
        return valueType;
    }

    public void setValueType(Class valueType) {
        this.valueType = valueType;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
