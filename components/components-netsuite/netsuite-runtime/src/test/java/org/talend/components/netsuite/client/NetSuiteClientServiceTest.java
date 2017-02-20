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
import org.talend.components.netsuite.client.model.RecordTypeInfo;
import org.talend.components.netsuite.client.model.SearchRecordInfo;
import org.talend.components.netsuite.client.v2016_2.NetSuiteClientServiceImpl;
import org.talend.components.netsuite.test.AssertMatcher;

import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.Status;
import com.netsuite.webservices.v2016_2.platform.core.types.RecordType;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchRecordType;
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
import static org.talend.components.netsuite.client.NetSuiteFactory.toInitialUpper;

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
                        .getHeader(headers, new QName(NetSuiteClientServiceImpl.NS_URI_PLATFORM_MESSAGES, "applicationInfo"));
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

        Set<String> searchRecordTypeNameSet = new HashSet<>();
        for (SearchRecordType searchRecordType : searchRecordTypeSet) {
            searchRecordTypeNameSet.add(toInitialUpper(searchRecordType.value()));
        }
        searchRecordTypeNameSet.add("Address");
        searchRecordTypeNameSet.add("InventoryDetail");
        searchRecordTypeNameSet.add("TimeEntry");

        for (String searchRecordType : searchRecordTypeNameSet) {
            SearchRecordInfo searchRecordInfo = clientService.getSearchRecordInfo(searchRecordType);
            assertNotNull("Search record def found: " + searchRecordType, searchRecordInfo);
        }

        Set<RecordType> recordTypeSet = new HashSet<>(Arrays.asList(RecordType.values()));
        recordTypeSet.remove(RecordType.CRM_CUSTOM_FIELD);
        recordTypeSet.remove(RecordType.ENTITY_CUSTOM_FIELD);
        recordTypeSet.remove(RecordType.ITEM_CUSTOM_FIELD);
        recordTypeSet.remove(RecordType.ITEM_OPTION_CUSTOM_FIELD);
        recordTypeSet.remove(RecordType.ITEM_NUMBER_CUSTOM_FIELD);
        recordTypeSet.remove(RecordType.CUSTOM_TRANSACTION_TYPE);
        recordTypeSet.remove(RecordType.CUSTOM_TRANSACTION);
        recordTypeSet.remove(RecordType.CUSTOM_RECORD);
        recordTypeSet.remove(RecordType.CUSTOM_RECORD_CUSTOM_FIELD);
        recordTypeSet.remove(RecordType.TRANSACTION_BODY_CUSTOM_FIELD);
        recordTypeSet.remove(RecordType.TRANSACTION_COLUMN_CUSTOM_FIELD);
        recordTypeSet.remove(RecordType.OTHER_CUSTOM_FIELD);

        Set<String> recordTypeNameSet = new HashSet<>();
        for (RecordType recordType : recordTypeSet) {
            recordTypeNameSet.add(toInitialUpper(recordType.value()));
        }
        recordTypeNameSet.add("Address");
        recordTypeNameSet.add("InventoryDetail");
        recordTypeNameSet.add("TimeEntry");

        for (String recordType : recordTypeNameSet) {
            RecordTypeInfo recordTypeInfo = clientService.getRecordTypeInfo(recordType);
            assertNotNull("Record type def found: " + recordType, recordTypeInfo);
        }
    }

}