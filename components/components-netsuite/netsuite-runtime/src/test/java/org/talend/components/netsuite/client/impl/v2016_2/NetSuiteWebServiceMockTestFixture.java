package org.talend.components.netsuite.client.impl.v2016_2;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

import org.apache.cxf.headers.Header;
import org.talend.components.netsuite.client.NetSuiteCredentials;
import org.talend.components.netsuite.client.NetSuiteFactory;

import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.NetSuiteService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 *
 */
public class NetSuiteWebServiceMockTestFixture {

    private Endpoint endpoint;
    private NetSuiteService service;
    private NetSuiteCredentials credentials;
    private NetSuitePortTypeImpl portImpl;
    private NetSuitePortType portMock;

    public void setUp() throws Exception {
        System.setProperty("com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");

        URL endpointAddress = new URL("http://localhost:8088/NetSuitePort_2016_2");

        portImpl = new NetSuitePortTypeImpl();
        portImpl.setEndpointAddress(endpointAddress);

        // Publish the SOAP Web Service
        endpoint = Endpoint.publish(endpointAddress.toString(), portImpl);
        assertTrue(endpoint.isPublished());
        assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());

        URL wsdlLocation = new URL(endpointAddress.toString().concat("?wsdl"));
        service = new NetSuiteService(wsdlLocation, NetSuiteService.SERVICE);

        credentials = new NetSuiteCredentials(
                "test@test.com", "12345", "test", "3");
        credentials.setApplicationId("00000000-0000-0000-0000-000000000000");

        portMock = mock(NetSuitePortType.class);
        portImpl.setPort(portMock);
    }

    public NetSuiteCredentials getCredentials() {
        return credentials;
    }

    public NetSuitePortTypeImpl getPortImpl() {
        return portImpl;
    }

    public NetSuitePortType getPortMock() {
        return portMock;
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
