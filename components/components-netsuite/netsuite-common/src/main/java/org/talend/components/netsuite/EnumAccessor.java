package org.talend.components.netsuite;

/**
 *
 */
public interface EnumAccessor {

    String mapToString(Enum enumValue);

    Enum mapFromString(String value);
}
