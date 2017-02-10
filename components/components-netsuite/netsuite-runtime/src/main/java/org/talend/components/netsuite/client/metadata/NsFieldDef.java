package org.talend.components.netsuite.client.metadata;

import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.schema.NsField;

/**
 *
 */
public class NsFieldDef implements NsField {
    private String name;
    private Class valueType;
    private boolean key;
    private boolean nullable;
    private PropertyInfo propertyInfo;

    public NsFieldDef(String name, Class valueType, boolean key, boolean nullable, PropertyInfo propertyInfo) {
        this.name = name;
        this.valueType = valueType;
        this.key = key;
        this.nullable = nullable;
        this.propertyInfo = propertyInfo;
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

    public PropertyInfo getPropertyInfo() {
        return propertyInfo;
    }
}
