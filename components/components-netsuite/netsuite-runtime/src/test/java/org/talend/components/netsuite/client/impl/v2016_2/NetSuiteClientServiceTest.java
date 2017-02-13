package org.talend.components.netsuite.client.impl.v2016_2;

import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.headers.Header;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteFactory;
import org.talend.components.netsuite.client.NetSuiteCredentials;
import org.talend.components.netsuite.client.impl.MessageContextHolder;
import org.talend.components.netsuite.test.AssertMatcher;

import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.Status;
import com.netsuite.webservices.v2016_2.platform.messages.LoginRequest;
import com.netsuite.webservices.v2016_2.platform.messages.LoginResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SessionResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.talend.components.netsuite.client.impl.v2016_2.NetSuiteWebServiceMockTestFixture.getHeader;

/**
 *
 */
public class NetSuiteClientServiceTest {

    private static NetSuiteWebServiceMockTestFixture webServiceTestFixture;

    @BeforeClass
    public static void classSetUp() throws Exception {
        webServiceTestFixture = new NetSuiteWebServiceMockTestFixture();
        webServiceTestFixture.setUp();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        if (webServiceTestFixture != null) {
            webServiceTestFixture.tearDown();
        }
    }

    @Test
    /**
     * TODO Verify headers (applicationInfo etc.)
     */
    public void testConnectAndLogin() throws Exception {
        final NetSuiteCredentials credentials = webServiceTestFixture.getCredentials();
        final NetSuitePortType port = webServiceTestFixture.getPortMock();

        SessionResponse sessionResponse = new SessionResponse();
        Status status = new Status();
        status.setIsSuccess(true);
        sessionResponse.setStatus(status);
        LoginResponse response = new LoginResponse();
        response.setSessionResponse(sessionResponse);

        when(port.login(argThat(new AssertMatcher<LoginRequest>() {
            @Override protected void doAssert(LoginRequest target) throws Exception {
                assertEquals(credentials.getEmail(), target.getPassport().getEmail());
                assertEquals(credentials.getPassword(), target.getPassport().getPassword());
                assertEquals(credentials.getRoleId(), target.getPassport().getRole().getInternalId());
                assertEquals(credentials.getAccount(), target.getPassport().getAccount());

                MessageContext messageContext = MessageContextHolder.get();
                assertNotNull(messageContext);
                List<Header> headers = (List<Header>) messageContext.get(Header.HEADER_LIST);
                assertNotNull(headers);
                Header appInfoHeader = getHeader(headers, new QName(
                        NetSuiteClientServiceImpl.NS_URI_PLATFORM_MESSAGES, "applicationInfo"));
                assertNotNull(appInfoHeader);
            }
        }))).thenReturn(response);

        NetSuiteClientService clientService = NetSuiteClientService.getClientService("2016.2");
        clientService.setEndpointUrl(webServiceTestFixture.getEndpointAddress().toString());
        clientService.setCredentials(credentials);

        clientService.login();

        verify(port, times(1))
                .login(any(LoginRequest.class));
    }

}