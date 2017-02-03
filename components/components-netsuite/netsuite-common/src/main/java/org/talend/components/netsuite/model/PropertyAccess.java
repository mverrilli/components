package org.talend.components.netsuite.model;

/**
 *
 */
public interface PropertyAccess {

    Object get(String name);

    void set(String name, Object value);
}
