package org.talend.components.netsuite.client;

/**
 *
 */
public interface NetSuiteClientFactory<T> {

    NetSuiteClientService<T> createClient() throws NetSuiteException;

}
