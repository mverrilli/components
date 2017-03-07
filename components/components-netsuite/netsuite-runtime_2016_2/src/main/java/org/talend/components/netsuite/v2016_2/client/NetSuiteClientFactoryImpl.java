package org.talend.components.netsuite.v2016_2.client;

import org.talend.components.netsuite.client.NetSuiteClientFactory;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;

import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;

/**
 *
 */
public class NetSuiteClientFactoryImpl implements NetSuiteClientFactory<NetSuitePortType> {

    public static final NetSuiteClientFactoryImpl INSTANCE = new NetSuiteClientFactoryImpl();

    @Override
    public NetSuiteClientService<NetSuitePortType> createClient() throws NetSuiteException {
        return new NetSuiteClientServiceImpl();
    }
}
