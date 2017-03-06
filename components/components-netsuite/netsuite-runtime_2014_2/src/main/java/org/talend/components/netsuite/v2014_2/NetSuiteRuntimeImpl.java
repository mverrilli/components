package org.talend.components.netsuite.v2014_2;

import org.talend.components.netsuite.AbstractNetSuiteRuntime;
import org.talend.components.netsuite.v2014_2.client.NetSuiteClientFactoryImpl;

/**
 *
 */
public class NetSuiteRuntimeImpl extends AbstractNetSuiteRuntime {

    public NetSuiteRuntimeImpl() {
        clientFactory = NetSuiteClientFactoryImpl.INSTANCE;
    }
}
