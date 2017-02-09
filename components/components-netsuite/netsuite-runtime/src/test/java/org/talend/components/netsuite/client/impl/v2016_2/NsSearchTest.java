package org.talend.components.netsuite.client.impl.v2016_2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteFactory;
import org.talend.components.netsuite.client.NsSearch;

import com.netsuite.webservices.v2016_2.lists.accounting.AccountSearch;
import com.netsuite.webservices.v2016_2.platform.common.AccountSearchBasic;
import com.netsuite.webservices.v2016_2.platform.core.Record;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanField;
import com.netsuite.webservices.v2016_2.platform.core.SearchCustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.SearchDoubleField;
import com.netsuite.webservices.v2016_2.platform.core.SearchEnumMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchLongCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecord;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringField;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchDoubleFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchEnumMultiSelectFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchLongFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchStringFieldOperator;

/**
 *
 */
public class NsSearchTest {

    @Test
    public void testSearchBuilding() throws Exception {
        NetSuiteConnection conn = NetSuiteFactory.getConnection("2016.2");

        NsSearch<Record, SearchRecord> s1 = conn.newSearch();
        s1.entity("Account");
        s1.criteria("type", "List.anyOf", null, Arrays.asList("Bank"));
        s1.criteria("balance", "Double.greaterThanOrEqualTo", null, Arrays.asList("10000.0"));
        s1.criteria("legalName", "String.contains", null, Arrays.asList("Acme"));
        s1.criteria("isInactive", "Boolean", null, Arrays.asList("true"));

        s1.criteria("customBooleanField1", "Boolean", "Boolean", Arrays.asList("true"));
        s1.criteria("customStringField1", "String.doesNotContain", "String", Arrays.asList("Foo"));
        s1.criteria("customLongField1", "Numeric.lessThan", "Long", Arrays.asList("100"));

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

}
