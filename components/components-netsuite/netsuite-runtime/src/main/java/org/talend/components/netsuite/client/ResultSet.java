package org.talend.components.netsuite.client;

/**
 *
 */
public abstract class ResultSet<T> {

    public abstract boolean next() throws NetSuiteException;

    public abstract T get() throws NetSuiteException;
}
