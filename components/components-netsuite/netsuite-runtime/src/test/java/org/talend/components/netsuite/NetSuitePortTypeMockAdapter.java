package org.talend.components.netsuite;

import java.net.URL;

/**
 *
 */
public interface NetSuitePortTypeMockAdapter<PortT> {

    URL getEndpointAddress();

    void setEndpointAddress(URL endpointAddress);

    PortT getPort();

    void setPort(PortT port);

}
