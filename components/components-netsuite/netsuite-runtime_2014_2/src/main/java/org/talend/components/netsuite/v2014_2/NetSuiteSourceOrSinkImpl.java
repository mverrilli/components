package org.talend.components.netsuite.v2014_2;

import org.talend.components.netsuite.NetSuiteSourceOrSink;
import org.talend.components.netsuite.v2014_2.client.NetSuiteClientFactoryImpl;

/**
 *
 */
public class NetSuiteSourceOrSinkImpl extends NetSuiteSourceOrSink {

    public NetSuiteSourceOrSinkImpl() {
        clientFactory = NetSuiteClientFactoryImpl.INSTANCE;
    }
}
