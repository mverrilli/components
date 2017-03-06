package org.talend.components.netsuite.v2016_2;

import org.talend.components.netsuite.NetSuiteSource;
import org.talend.components.netsuite.v2016_2.client.NetSuiteClientFactoryImpl;

/**
 *
 */
public class NetSuiteSourceImpl extends NetSuiteSource {

    public NetSuiteSourceImpl() {
        clientFactory = NetSuiteClientFactoryImpl.INSTANCE;
    }
}
