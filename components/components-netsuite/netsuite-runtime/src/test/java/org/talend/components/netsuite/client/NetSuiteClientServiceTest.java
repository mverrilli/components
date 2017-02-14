package org.talend.components.netsuite.client;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.headers.Header;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.client.metadata.SearchRecordDef;
import org.talend.components.netsuite.test.AssertMatcher;

import com.netsuite.webservices.platform.NetSuitePortType;
import com.netsuite.webservices.platform.core.Status;
import com.netsuite.webservices.platform.core.types.SearchRecordType;
import com.netsuite.webservices.platform.messages.LoginRequest;
import com.netsuite.webservices.platform.messages.LoginResponse;
import com.netsuite.webservices.platform.messages.SessionResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.talend.components.netsuite.client.NetSuiteClientService.toInitialUpper;

/**
 *
 */
public class NetSuiteClientServiceTest {

    private static NetSuiteWebServiceMockTestFixture webServiceTestFixture;

    @BeforeClass public static void classSetUp() throws Exception {
        webServiceTestFixture = new NetSuiteWebServiceMockTestFixture();
        webServiceTestFixture.setUp();
    }

    @AfterClass public static void classTearDown() throws Exception {
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
                Header appInfoHeader = NetSuiteWebServiceMockTestFixture
                        .getHeader(headers, new QName(NetSuiteClientService.NS_URI_PLATFORM_MESSAGES, "applicationInfo"));
                assertNotNull(appInfoHeader);
            }
        }))).thenReturn(response);

        NetSuiteClientService clientService = webServiceTestFixture.getClientService();

        clientService.login();

        verify(port, times(1)).login(any(LoginRequest.class));
    }

    @Test
    public void testStandardMetaData() throws Exception {
        NetSuiteClientService clientService = webServiceTestFixture.getClientService();

        Set<SearchRecordType> searchRecordTypeSet = new HashSet<>(Arrays.asList(SearchRecordType.values()));
        searchRecordTypeSet.remove(SearchRecordType.ACCOUNTING_TRANSACTION);
        searchRecordTypeSet.remove(SearchRecordType.TRANSACTION);
        searchRecordTypeSet.remove(SearchRecordType.ITEM);
        searchRecordTypeSet.remove(SearchRecordType.CUSTOM_LIST);
        searchRecordTypeSet.remove(SearchRecordType.CUSTOM_RECORD);

        for (SearchRecordType searchRecordType : searchRecordTypeSet) {
            SearchRecordDef searchRecordDef = clientService.getSearchRecordDef(toInitialUpper(searchRecordType.value()));
            assertNotNull("Search record def found: " + searchRecordType.value(), searchRecordDef);
        }

    }

}