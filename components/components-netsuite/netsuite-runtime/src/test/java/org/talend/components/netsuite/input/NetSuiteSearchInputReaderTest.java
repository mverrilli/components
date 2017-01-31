package org.talend.components.netsuite.input;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.netsuite.NetSuiteEndpoint;
import org.talend.components.netsuite.NetSuiteSource;
import org.talend.components.netsuite.client.impl.v2016_2.NetSuiteWebServiceMockTestFixture;

import com.netsuite.webservices.v2016_2.lists.accounting.Account;
import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.RecordList;
import com.netsuite.webservices.v2016_2.platform.core.SearchResult;
import com.netsuite.webservices.v2016_2.platform.core.Status;
import com.netsuite.webservices.v2016_2.platform.messages.LoginRequest;
import com.netsuite.webservices.v2016_2.platform.messages.LoginResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SearchMoreWithIdRequest;
import com.netsuite.webservices.v2016_2.platform.messages.SearchMoreWithIdResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SearchRequest;
import com.netsuite.webservices.v2016_2.platform.messages.SearchResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SessionResponse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
public class NetSuiteSearchInputReaderTest {

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
    public void testInput() throws Exception {
        NetSuitePortType port = mock(NetSuitePortType.class);
        webServiceTestFixture.getPortImpl().setPort(port);

        SessionResponse sessionResponse = new SessionResponse();
        Status status = new Status();
        status.setIsSuccess(true);
        sessionResponse.setStatus(status);
        LoginResponse response = new LoginResponse();
        response.setSessionResponse(sessionResponse);

        when(port.login(any(LoginRequest.class))).thenReturn(response);

        RuntimeContainer container = mock(RuntimeContainer.class);

        NetSuiteInputProperties properties = new NetSuiteInputProperties("NetSuite");
        properties.init();
        properties.connection.endpoint.setValue(webServiceTestFixture.getEndpointAddress().toString());
        properties.connection.email.setValue("test@test.com");
        properties.connection.password.setValue("123");
        properties.connection.role.setValue(3);
        properties.connection.account.setValue("test");
        properties.connection.applicationId.setValue("00000000-0000-0000-0000-000000000000");
        properties.module.moduleName.setValue("Account");

        NetSuiteEndpoint endpoint = new NetSuiteEndpoint(properties);
        Schema schema = endpoint.getSchema(properties.module.moduleName.getValue());
        properties.module.main.schema.setValue(schema);

        NetSuiteSource source = new NetSuiteSource();
        source.initialize(container, properties);

        final List<SearchResult> pageResults = makeRecordPages(150, 100);
        when(port.search(any(SearchRequest.class))).then(new Answer<SearchResponse>() {
            @Override public SearchResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                SearchResponse response = new SearchResponse();
                response.setSearchResult(pageResults.get(0));
                return response;
            }
        });
        when(port.searchMoreWithId(any(SearchMoreWithIdRequest.class))).then(new Answer<SearchMoreWithIdResponse>() {
            @Override public SearchMoreWithIdResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                SearchMoreWithIdRequest request = (SearchMoreWithIdRequest) invocationOnMock.getArguments()[0];
                SearchMoreWithIdResponse response = new SearchMoreWithIdResponse();
                response.setSearchResult(pageResults.get(request.getPageIndex()));
                return response;
            }
        });

        NetSuiteSearchInputReader reader = (NetSuiteSearchInputReader) source.createReader(container);

        boolean started = reader.start();
        assertTrue(started);

        IndexedRecord record = reader.getCurrent();
        assertNotNull(record);

        List<Schema.Field> fields = record.getSchema().getFields();
        for (int i = 0; i < fields.size(); i++) {
            Object value = record.get(i);
            System.out.println(fields.get(i) + ": " + value);
        }
    }

    private List<SearchResult> makeRecordPages(int count, int pageSize) {
        int totalPages = count / pageSize;
        if (count % pageSize != 0) {
            totalPages += 1;
        }

        String searchId = UUID.randomUUID().toString();

        Status status = new Status();
        status.setIsSuccess(true);

        List<SearchResult> pageResults = new ArrayList<>();
        SearchResult result = null;
        while (count > 0) {
            Account record = new Account();

            if (result == null) {
                result = new SearchResult();
                result.setSearchId(searchId);
                result.setTotalPages(totalPages);
                result.setTotalRecords(count);
                result.setPageIndex(pageResults.size());
                result.setPageSize(pageSize);
                result.setStatus(status);
            }

            if (result.getRecordList() == null) {
                result.setRecordList(new RecordList());
            }
            result.getRecordList().getRecord().add(record);

            if (result.getRecordList().getRecord().size() == pageSize) {
                pageResults.add(result);
                result = null;
            }

            count--;
        }

        if (result != null) {
            pageResults.add(result);
        }

        return pageResults;
    }

}
