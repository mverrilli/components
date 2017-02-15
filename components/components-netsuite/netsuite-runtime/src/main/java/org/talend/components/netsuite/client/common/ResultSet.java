package org.talend.components.netsuite.client.common;

import org.talend.components.netsuite.client.NetSuiteException;

/**
 *
 */
public abstract class ResultSet<T> {

    public abstract boolean next() throws NetSuiteException;

    public abstract T get() throws NetSuiteException;
}
