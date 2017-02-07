package org.talend.components.netsuite;

/**
 *
 */
public class NetSuiteMetaDataServiceProviderImpl implements NetSuiteMetaDataServiceProvider {

    @Override
    public NetSuiteMetaDataService getMetaDataService(NetSuiteConnectionProperties properties) {
        return new NetSuiteEndpointService(properties);
    }
}
