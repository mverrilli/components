package org.talend.components.netsuite.client.impl.v2016_2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteFactory;
import org.talend.components.netsuite.client.NsSearch;

import com.netsuite.webservices.v2016_2.lists.accounting.AccountSearch;
import com.netsuite.webservices.v2016_2.platform.common.AccountSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.TransactionSearchBasic;
import com.netsuite.webservices.v2016_2.platform.core.Record;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanField;
import com.netsuite.webservices.v2016_2.platform.core.SearchCustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.SearchDateCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDateField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDoubleField;
import com.netsuite.webservices.v2016_2.platform.core.SearchEnumMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchLongCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecord;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringField;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchDate;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchDoubleFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchEnumMultiSelectFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchLongFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchStringFieldOperator;
import com.netsuite.webservices.v2016_2.transactions.sales.TransactionSearch;

/**
 *
 */
public class NsSearchTest {

    @Test
    public void testBasic() throws Exception {
        NetSuiteConnection conn = NetSuiteFactory.getConnection("2016.2");

        NsSearch<Record, SearchRecord> s1 = conn.newSearch();
        s1.entity("Account");
        s1.criteria("type", "List.anyOf", Arrays.asList("Bank"));
        s1.criteria("balance", "Double.greaterThanOrEqualTo", Arrays.asList("10000.0"));
        s1.criteria("legalName", "String.contains", Arrays.asList("Acme"));
        s1.criteria("isInactive", "Boolean", Arrays.asList("true"));

        s1.criteria("customBooleanField1", "Boolean", Arrays.asList("true"));
        s1.criteria("customStringField1", "String.doesNotContain", Arrays.asList("Foo"));
        s1.criteria("customLongField1", "Numeric.lessThan", Arrays.asList("100"));

        SearchRecord sr1 = s1.build();
        assertNotNull(sr1);
        assertEquals(AccountSearch.class, sr1.getClass());

        AccountSearch search = (AccountSearch) sr1;
        assertNotNull(search.getBasic());

        AccountSearchBasic searchBasic = search.getBasic();
        assertNotNull(searchBasic.getBalance());

        SearchEnumMultiSelectField typeField = searchBasic.getType();
        assertEquals(SearchEnumMultiSelectFieldOperator.ANY_OF, typeField.getOperator());
        assertEquals(Arrays.asList("Bank"), typeField.getSearchValue());

        SearchDoubleField balanceField = searchBasic.getBalance();
        assertEquals(SearchDoubleFieldOperator.GREATER_THAN_OR_EQUAL_TO, balanceField.getOperator());
        assertEquals(Double.valueOf(10000.0), balanceField.getSearchValue());

        SearchBooleanField isInactiveField = searchBasic.getIsInactive();
        assertEquals(Boolean.TRUE, isInactiveField.getSearchValue());

        SearchStringField legalNameField = searchBasic.getLegalName();
        assertEquals(SearchStringFieldOperator.CONTAINS, legalNameField.getOperator());
        assertEquals("Acme", legalNameField.getSearchValue());

        SearchCustomFieldList customFieldList = searchBasic.getCustomFieldList();
        assertNotNull(customFieldList);
        assertNotNull(customFieldList.getCustomField());
        assertEquals(3, customFieldList.getCustomField().size());

        SearchBooleanCustomField customBooleanField1 = (SearchBooleanCustomField) customFieldList.getCustomField().get(0);
        assertEquals(Boolean.TRUE, customBooleanField1.getSearchValue());

        SearchStringCustomField customStringField1 = (SearchStringCustomField) customFieldList.getCustomField().get(1);
        assertEquals(SearchStringFieldOperator.DOES_NOT_CONTAIN, customStringField1.getOperator());
        assertEquals("Foo", customStringField1.getSearchValue());

        SearchLongCustomField customLongField1 = (SearchLongCustomField) customFieldList.getCustomField().get(2);
        assertEquals(SearchLongFieldOperator.LESS_THAN, customLongField1.getOperator());
        assertEquals(Long.valueOf(100), customLongField1.getSearchValue());
    }

    @Test
    public void testSearchDateFieldWithPredefinedDate() throws Exception {
        NetSuiteConnection conn = NetSuiteFactory.getConnection("2016.2");

        NsSearch<Record, SearchRecord> s1 = conn.newSearch();
        s1.entity("Check");
        s1.criteria("tranDate", "PredefinedDate.lastBusinessWeek", null);
        s1.criteria("customDateField1", "PredefinedDate.lastBusinessWeek", null);

        SearchRecord sr1 = s1.build();
        assertNotNull(sr1);
        assertEquals(TransactionSearch.class, sr1.getClass());

        TransactionSearch search = (TransactionSearch) sr1;
        assertNotNull(search.getBasic());

        TransactionSearchBasic searchBasic = search.getBasic();
        assertNotNull(searchBasic.getTranDate());

        SearchDateField field1 = searchBasic.getTranDate();
        assertNull(field1.getOperator());
        assertNull(field1.getSearchValue());
        assertNull(field1.getSearchValue2());
        assertNotNull(field1.getPredefinedSearchValue());
        assertEquals(SearchDate.LAST_BUSINESS_WEEK, field1.getPredefinedSearchValue());

        SearchCustomFieldList customFieldList = searchBasic.getCustomFieldList();
        assertNotNull(customFieldList);
        assertNotNull(customFieldList.getCustomField());
        assertEquals(1, customFieldList.getCustomField().size());

        SearchDateCustomField customField1 = (SearchDateCustomField) customFieldList.getCustomField().get(0);
        assertNull(customField1.getOperator());
        assertNull(customField1.getSearchValue());
        assertNull(customField1.getSearchValue2());
        assertNotNull(customField1.getPredefinedSearchValue());
        assertEquals(SearchDate.LAST_BUSINESS_WEEK, customField1.getPredefinedSearchValue());
    }
}
