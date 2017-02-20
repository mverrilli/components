package org.talend.components.netsuite.client;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.talend.components.netsuite.client.common.SearchResultSet;
import org.talend.components.netsuite.client.model.SearchRecordInfo;
import org.talend.components.netsuite.client.common.NsSearchResult;
import org.talend.components.netsuite.client.v2016_2.NetSuiteClientServiceImpl;

import com.netsuite.webservices.v2016_2.lists.accounting.Account;
import com.netsuite.webservices.v2016_2.lists.accounting.AccountSearch;
import com.netsuite.webservices.v2016_2.platform.core.Record;
import com.netsuite.webservices.v2016_2.platform.core.RecordList;
import com.netsuite.webservices.v2016_2.platform.core.SearchResult;
import com.netsuite.webservices.v2016_2.platform.core.Status;
import com.netsuite.webservices.v2016_2.platform.messages.SearchMoreWithIdResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SearchResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
public class SearchResultSetTest {

    @Test
    public void testPaging() throws Exception {
        NetSuiteClientService conn = mock(NetSuiteClientService.class);

        List<Record> page1 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            page1.add(new Account());
        }

        List<Record> page2 = new ArrayList<>();
        for (int i = 0; i < 750; i++) {
            page2.add(new Account());
        }

        SearchResult result1 = new SearchResult();
        Status status = new Status();
        status.setIsSuccess(true);
        result1.setStatus(status);
        result1.setSearchId("abc123");
        result1.setPageIndex(1);
        result1.setTotalRecords(page1.size() + page2.size());
        result1.setTotalPages(2);
        result1.setRecordList(new RecordList());
        result1.getRecordList().getRecord().addAll(page1);

        SearchResult result2 = new SearchResult();
        result2.setStatus(status);
        result2.setSearchId(result1.getSearchId());
        result2.setPageIndex(2);
        result2.setTotalRecords(result1.getTotalRecords());
        result2.setTotalPages(result1.getTotalPages());
        result2.setRecordList(new RecordList());
        result2.getRecordList().getRecord().addAll(page2);

        SearchResponse response1 = new SearchResponse();
        response1.setSearchResult(result1);

        SearchMoreWithIdResponse response2 = new SearchMoreWithIdResponse();
        response2.setSearchResult(result2);

        AccountSearch nsSearchRecord1 = new AccountSearch();
        NsSearchResult nsSearchResult1 = NetSuiteClientServiceImpl.toNsSearchResult(result1);
        NsSearchResult nsSearchResult2 = NetSuiteClientServiceImpl.toNsSearchResult(result2);

        when(conn.search(eq(nsSearchRecord1))).thenReturn(nsSearchResult1);
        when(conn.searchMoreWithId(eq("abc123"), eq(2))).thenReturn(nsSearchResult2);

        NetSuiteClientService clientService = NetSuiteClientService.create("2016.2");
        SearchRecordInfo searchInfo = clientService.getSearchRecordInfo("Account");

        SearchResultSet<Record> resultSet = new SearchResultSet<>(conn, searchInfo, nsSearchResult1);

        List<Object> recordList = new ArrayList<>();
        while (resultSet.next()) {
            Object record = resultSet.get();
            assertNotNull(record);
            recordList.add(record);
        }

        assertEquals(page1.size() + page2.size(), recordList.size());
    }

}
