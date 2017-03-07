package org.talend.components.netsuite.client.model;

/**
 *
 */
public enum RefType {
    RECORD_REF("RecordRef"),
    CUSTOMIZATION_REF("CustomizationRef");

    private String typeName;

    RefType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static RefType getByTypeName(String typeName) {
        for (RefType value : values()) {
            if (value.typeName.equals(typeName)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid type name: " + typeName);
    }
}
