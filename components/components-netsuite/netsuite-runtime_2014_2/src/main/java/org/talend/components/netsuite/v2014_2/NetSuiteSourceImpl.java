package org.talend.components.netsuite.v2014_2;

import org.talend.components.netsuite.NetSuiteSource;
import org.talend.components.netsuite.v2014_2.client.NetSuiteClientFactoryImpl;

/**
 *
 */
public class NetSuiteSourceImpl extends NetSuiteSource {

    public NetSuiteSourceImpl() {
        clientFactory = NetSuiteClientFactoryImpl.INSTANCE;
    }
}
