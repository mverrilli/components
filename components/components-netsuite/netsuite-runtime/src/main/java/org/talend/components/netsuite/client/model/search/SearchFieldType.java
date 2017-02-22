package org.talend.components.netsuite.client.model.search;

/**
 *
 */
public enum SearchFieldType {
    BOOLEAN("SearchBooleanField"),
    DATE("SearchDateField"),
    DOUBLE("SearchDoubleField"),
    LONG("SearchLongField"),
    MULTI_SELECT("SearchMultiSelectField"),
    SELECT("SearchEnumMultiSelectField"),
    STRING("SearchStringField"),
    TEXT_NUMBER("SearchTextNumberField"),
    CUSTOM_BOOLEAN("SearchBooleanCustomField"),
    CUSTOM_DATE("SearchDateCustomField"),
    CUSTOM_DOUBLE("SearchDoubleCustomField"),
    CUSTOM_LONG("SearchLongCustomField"),
    CUSTOM_MULTI_SELECT("SearchMultiSelectCustomField"),
    CUSTOM_SELECT("SearchEnumMultiSelectCustomField"),
    CUSTOM_STRING("SearchStringCustomField");

    private final String fieldTypeName;

    SearchFieldType(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }

    public String getFieldTypeName() {
        return fieldTypeName;
    }
}
