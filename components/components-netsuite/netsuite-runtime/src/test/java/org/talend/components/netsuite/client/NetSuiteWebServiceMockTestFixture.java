package org.talend.components.netsuite.client;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

import org.apache.cxf.headers.Header;
import org.talend.components.netsuite.test.TestFixture;

import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.NetSuiteService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.talend.components.netsuite.client.NetSuiteClientService.MESSAGE_LOGGING_ENABLED_PROPERTY_NAME;

/**
 *
 */
public class NetSuiteWebServiceMockTestFixture implements TestFixture {

    private Endpoint endpoint;
    private NetSuiteService service;
    private NetSuiteCredentials credentials;
    private NetSuitePortTypeMockAdapter portMockAdapter;
    private NetSuitePortType portMock;
    private NetSuiteClientService clientService;

    @Override
    public void setUp() throws Exception {
        System.setProperty("com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");

        URL endpointAddress = new URL("http://localhost:8088/NetSuitePort_2016_2");

        portMockAdapter = new NetSuitePortTypeMockAdapter();
        portMockAdapter.setEndpointAddress(endpointAddress);

        // Publish the SOAP Web Service
        endpoint = Endpoint.publish(endpointAddress.toString(), portMockAdapter);
        assertTrue(endpoint.isPublished());
        assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());

        URL wsdlLocation = new URL(endpointAddress.toString().concat("?wsdl"));
        service = new NetSuiteService(wsdlLocation, NetSuiteService.SERVICE);

        credentials = new NetSuiteCredentials(
                "test@test.com", "12345", "test", "3");
        credentials.setApplicationId("00000000-0000-0000-0000-000000000000");

        portMock = mock(NetSuitePortType.class);
        portMockAdapter.setPort(portMock);

        clientService = NetSuiteClientService.create("2016.2");
        clientService.setEndpointUrl(endpointAddress.toString());
        clientService.setCredentials(credentials);

        boolean messageLoggingEnabled = Boolean.valueOf(
                System.getProperty(MESSAGE_LOGGING_ENABLED_PROPERTY_NAME, "false"));
        clientService.setMessageLoggingEnabled(messageLoggingEnabled);

    }

    @Override
    public void tearDown() throws Exception {
        // Unpublish the SOAP Web Service
        if (endpoint != null) {
            endpoint.stop();
            assertFalse(endpoint.isPublished());
        }

        service = null;
        portMockAdapter = null;
    }

    public NetSuiteCredentials getCredentials() {
        return credentials;
    }

    public NetSuitePortTypeMockAdapter getPortMockAdapter() {
        return portMockAdapter;
    }

    public NetSuitePortType getPortMock() {
        return portMock;
    }

    public URL getEndpointAddress()  {
        return portMockAdapter.getEndpointAddress();
    }

    public NetSuiteClientService getClientService() {
        return clientService;
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
