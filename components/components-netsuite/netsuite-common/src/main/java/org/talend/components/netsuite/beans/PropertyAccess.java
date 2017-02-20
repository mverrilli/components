package org.talend.components.netsuite.beans;

/**
 *
 */
public interface PropertyAccess {

    Object get(String name);

    void set(String name, Object value);
}
