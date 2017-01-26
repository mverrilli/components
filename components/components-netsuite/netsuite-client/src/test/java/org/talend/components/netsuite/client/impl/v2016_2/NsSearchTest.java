package org.talend.components.netsuite.client.impl.v2016_2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.talend.components.netsuite.client.NsObject.asNsObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteConnectionFactory;
import org.talend.components.netsuite.client.NsObject;
import org.talend.components.netsuite.client.NsSearch;
import org.talend.components.netsuite.client.NsSearchRecord;
import org.talend.components.netsuite.client.NsSearchResult;

import com.netsuite.webservices.v2016_2.lists.accounting.AccountSearch;
import com.netsuite.webservices.v2016_2.platform.common.AccountSearchBasic;
import com.netsuite.webservices.v2016_2.platform.core.Record;
import com.netsuite.webservices.v2016_2.platform.core.SearchDoubleField;
import com.netsuite.webservices.v2016_2.platform.core.SearchResult;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchDoubleFieldOperator;

/**
 *
 */
public class NsSearchTest {

    @Test
    public void testSearchBuilding() throws Exception {
        NetSuiteConnection conn = NetSuiteConnectionFactory.getConnection("2016.2");

        NsSearch s1 = conn.newSearch();
        s1.entity("Account");
        s1.criterion("balance", "GREATER_THAN_OR_EQUAL_TO",
                Arrays.asList("10000.0"), "Double");

        NsSearchRecord sr1 = s1.build();
        assertNotNull(sr1);
        assertNotNull(sr1.getTarget());
        assertEquals(AccountSearch.class, sr1.getTarget().getClass());

        AccountSearch search = (AccountSearch) sr1.getTarget();
        assertNotNull(search.getBasic());

        AccountSearchBasic searchBasic = search.getBasic();
        assertNotNull(searchBasic.getBalance());

        SearchDoubleField balanceField = searchBasic.getBalance();
        assertEquals(SearchDoubleFieldOperator.GREATER_THAN_OR_EQUAL_TO, balanceField.getOperator());
        assertEquals(Double.valueOf(10000.0), balanceField.getSearchValue());
    }

    protected NsSearchResult toNsSearchResult(SearchResult result) {
        NsSearchResult nsResult = new NsSearchResult();
        if (result.getStatus().getIsSuccess()) {
            nsResult.setSuccess(true);
        }
        nsResult.setSearchId(result.getSearchId());
        nsResult.setTotalPages(result.getTotalPages());
        nsResult.setTotalRecords(result.getTotalRecords());
        nsResult.setPageIndex(result.getPageIndex());
        nsResult.setPageSize(result.getPageSize());
        List<NsObject> nsRecordList = new ArrayList<>(result.getRecordList().getRecord().size());
        for (Record record : result.getRecordList().getRecord()) {
            NsObject nsRecord = asNsObject(record);
            nsRecordList.add(nsRecord);
        }
        nsResult.setRecordList(nsRecordList);
        return nsResult;
    }


}
