package org.talend.components.netsuite.v2016_2;

import org.talend.components.netsuite.NetSuiteSourceOrSink;
import org.talend.components.netsuite.v2016_2.client.NetSuiteClientFactoryImpl;

/**
 *
 */
public class NetSuiteSourceOrSinkImpl extends NetSuiteSourceOrSink {

    public NetSuiteSourceOrSinkImpl() {
        clientFactory = NetSuiteClientFactoryImpl.INSTANCE;
    }
}
