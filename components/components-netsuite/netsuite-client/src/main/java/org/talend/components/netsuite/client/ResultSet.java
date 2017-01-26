package org.talend.components.netsuite.client;

/**
 *
 */
public abstract class ResultSet<T> {

    public abstract boolean hasNext() throws NetSuiteException;

    public abstract T getNext() throws NetSuiteException;
}
