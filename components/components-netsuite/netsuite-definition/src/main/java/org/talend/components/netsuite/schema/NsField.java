package org.talend.components.netsuite.schema;

/**
 *
 */
public class NsField {
    private String name;
    private Class<?> valueType;

    public NsField(String name, Class<?> valueType) {
        this.name = name;
        this.valueType = valueType;
    }

    public String getName() {
        return name;
    }

    public Class<?> getValueType() {
        return valueType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NsField{");
        sb.append("name='").append(name).append('\'');
        sb.append(", valueType=").append(valueType);
        sb.append('}');
        return sb.toString();
    }
}
