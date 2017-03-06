package org.talend.components.netsuite.client;

/**
 *
 */
public interface NetSuiteClientFactory {

    NetSuiteClientService createClient() throws NetSuiteException;

}
