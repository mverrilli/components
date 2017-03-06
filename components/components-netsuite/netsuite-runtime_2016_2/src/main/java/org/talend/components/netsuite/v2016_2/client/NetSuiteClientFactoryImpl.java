package org.talend.components.netsuite.v2016_2.client;

import org.talend.components.netsuite.client.NetSuiteClientFactory;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;

/**
 *
 */
public class NetSuiteClientFactoryImpl implements NetSuiteClientFactory {

    public static final NetSuiteClientFactoryImpl INSTANCE = new NetSuiteClientFactoryImpl();

    @Override
    public NetSuiteClientService createClient() throws NetSuiteException {
        return new NetSuiteClientServiceImpl();
    }
}
