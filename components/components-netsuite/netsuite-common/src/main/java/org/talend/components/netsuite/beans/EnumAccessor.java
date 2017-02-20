package org.talend.components.netsuite.beans;

/**
 *
 */
public interface EnumAccessor {

    String mapToString(Enum enumValue);

    Enum mapFromString(String value);
}
