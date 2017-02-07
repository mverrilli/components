package org.talend.components.netsuite;

/**
 *
 */
public interface NetSuiteMetaDataServiceProvider<T extends NetSuiteMetaDataService> {

    T getMetaDataService(NetSuiteConnectionProperties properties);

}
