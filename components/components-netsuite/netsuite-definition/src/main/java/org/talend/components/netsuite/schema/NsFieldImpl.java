package org.talend.components.netsuite.schema;

/**
 *
 */
public class NsFieldImpl implements NsField {
    private String name;
    private Class<?> valueType;

    public NsFieldImpl(String name, Class<?> valueType) {
        this.name = name;
        this.valueType = valueType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<?> getValueType() {
        return valueType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NsFieldImpl{");
        sb.append("name='").append(name).append('\'');
        sb.append(", valueType=").append(valueType);
        sb.append('}');
        return sb.toString();
    }
}
