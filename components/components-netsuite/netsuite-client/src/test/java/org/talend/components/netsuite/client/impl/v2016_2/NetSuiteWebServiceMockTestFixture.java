package org.talend.components.netsuite.client.impl.v2016_2;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

import org.apache.cxf.headers.Header;
import org.talend.components.netsuite.client.NetSuiteFactory;

import com.netsuite.webservices.v2016_2.platform.NetSuiteService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class NetSuiteWebServiceMockTestFixture {

    private NetSuitePortTypeImpl portImpl;
    private Endpoint endpoint;
    private NetSuiteService service;

    public void setUp() throws Exception {
        System.setProperty("com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");

        NetSuiteFactory.setMessageLoggingEnabled(true);

        URL endpointAddress = new URL("http://localhost:8088/NetSuitePort_2016_2");

        portImpl = new NetSuitePortTypeImpl();
        portImpl.setEndpointAddress(endpointAddress);

        // Publish the SOAP Web Service
        endpoint = Endpoint.publish(endpointAddress.toString(), portImpl);
        assertTrue(endpoint.isPublished());
        assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());

        URL wsdlLocation = new URL(endpointAddress.toString().concat("?wsdl"));
        service = new NetSuiteService(wsdlLocation, NetSuiteService.SERVICE);
    }

    public NetSuitePortTypeImpl getPortImpl() {
        return portImpl;
    }

    public URL getEndpointAddress()  {
        return portImpl.getEndpointAddress();
    }

    public void tearDown() throws Exception {
        // Unpublish the SOAP Web Service
        if (endpoint != null) {
            endpoint.stop();
            assertFalse(endpoint.isPublished());
        }

        service = null;
        portImpl = null;
    }

    public static Header getHeader(List<Header> headers, QName name) {
        for (Header header : headers) {
            if (name.equals(header.getName())) {
                return header;
            }
        }
        return null;
    }

}
