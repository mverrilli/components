package org.talend.components.netsuite.model;

/**
 *
 */
public interface PropertyAccessor<T> {

    Object get(T target, String name);

    void set(T target, String name, Object value);
}
