package org.talend.components.netsuite.v2014_2;

import org.talend.components.netsuite.NetSuiteSink;
import org.talend.components.netsuite.v2014_2.client.NetSuiteClientFactoryImpl;

/**
 *
 */
public class NetSuiteSinkImpl extends NetSuiteSink {

    public NetSuiteSinkImpl() {
        clientFactory = NetSuiteClientFactoryImpl.INSTANCE;
    }
}
