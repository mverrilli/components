package org.talend.components.netsuite.client.model.beans;

/**
 *
 */
public interface EnumAccessor {

    String getStringValue(Enum enumValue);

    Enum getEnumValue(String value);
}
