package org.talend.components.netsuite.model;

/**
 *
 */
public interface EnumAccessor {

    String mapToString(Enum enumValue);

    Enum mapFromString(String value);
}
