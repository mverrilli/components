package org.talend.components.netsuite.v2016_2;

import org.talend.components.netsuite.AbstractNetSuiteRuntime;
import org.talend.components.netsuite.v2016_2.client.NetSuiteClientFactoryImpl;

/**
 *
 */
public class NetSuiteRuntimeImpl extends AbstractNetSuiteRuntime {

    public NetSuiteRuntimeImpl() {
        clientFactory = NetSuiteClientFactoryImpl.INSTANCE;
    }
}
