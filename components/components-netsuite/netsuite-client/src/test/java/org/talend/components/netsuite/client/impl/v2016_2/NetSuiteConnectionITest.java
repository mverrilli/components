package org.talend.components.netsuite.client.impl.v2016_2;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteConnectionFactory;
import org.talend.components.netsuite.client.NetSuiteCredentials;

import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.Status;
import com.netsuite.webservices.v2016_2.platform.messages.LoginRequest;
import com.netsuite.webservices.v2016_2.platform.messages.LoginResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SessionResponse;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
public class NetSuiteConnectionITest {

    private static NetSuiteWebServiceTestFixture webServiceTestFixture;

    @BeforeClass
    public static void classSetUp() throws Exception {
        webServiceTestFixture = new NetSuiteWebServiceTestFixture();
        webServiceTestFixture.setUp();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        if (webServiceTestFixture != null) {
            webServiceTestFixture.tearDown();
        }
    }

    @Test
    public void testConnectAndLogin() throws Exception {
        NetSuiteCredentials credentials = new NetSuiteCredentials(
                "test@test.com", "12345", "test", "3");

        NetSuitePortType port = mock(NetSuitePortType.class);
        webServiceTestFixture.getPortImpl().setPort(port);

        SessionResponse sessionResponse = new SessionResponse();
        Status status = new Status();
        status.setIsSuccess(true);
        sessionResponse.setStatus(status);
        LoginResponse response = new LoginResponse();
        response.setSessionResponse(sessionResponse);

        when(port.login(any(LoginRequest.class))).thenReturn(response);

        NetSuiteConnection conn = NetSuiteConnectionFactory.getConnection("2016.2");
        conn.setEndpointUrl(webServiceTestFixture.getEndpointAddress().toString());
        conn.setCredentials(credentials);

        conn.login();

        verify(port, times(1))
                .login(any(LoginRequest.class));
    }

}