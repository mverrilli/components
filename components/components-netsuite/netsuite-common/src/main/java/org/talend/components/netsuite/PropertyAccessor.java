package org.talend.components.netsuite;

/**
 *
 */
public interface PropertyAccessor {

    Object get(String name);

    void set(String name, Object value);
}
