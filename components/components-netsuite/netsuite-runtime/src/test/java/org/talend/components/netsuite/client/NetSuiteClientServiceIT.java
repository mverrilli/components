package org.talend.components.netsuite.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.NetSuiteTestBase;
import org.talend.components.netsuite.client.model.CustomFieldDesc;
import org.talend.components.netsuite.client.model.RecordTypeDesc;
import org.talend.components.netsuite.client.model.SearchRecordTypeDesc;
import org.talend.components.netsuite.client.query.SearchCondition;
import org.talend.components.netsuite.client.query.SearchResultSet;
import org.talend.components.netsuite.client.v2016_2.RecordTypeEnum;
import org.talend.daikon.NamedThing;

import com.netsuite.webservices.v2016_2.lists.accounting.types.AccountType;
import com.netsuite.webservices.v2016_2.platform.core.Record;

/**
 *
 */
public class NetSuiteClientServiceIT extends NetSuiteTestBase {
    protected static NetSuiteWebServiceTestFixture webServiceTestFixture;

    @BeforeClass
    public static void classSetUp() throws Exception {
        webServiceTestFixture = new NetSuiteWebServiceTestFixture();
        classScopedTestFixtures.add(webServiceTestFixture);
        setUpClassScopedTestFixtures();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        tearDownClassScopedTestFixtures();
    }

    @Test
    public void testConnectAndLogin() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        connection.login();

        SearchResultSet<Record> rs = connection.newSearch()
                .target("Account")
                .condition(new SearchCondition("Type", "List.anyOf", Arrays.asList("Bank")))
                .search();

        int count = 0;
        while (rs.next()) {
            Record record = rs.get();

            assertEquals(AccountType.BANK, getProperty(record, "acctType"));

            count++;
        }
        System.out.println("Retrieved records: " + count);
        assertTrue(count > 1);
    }

    @Test
    public void testGetSearches() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        connection.login();

        Collection<NamedThing> searches = connection.getSearchableTypes();

        for (NamedThing search : searches) {
            assertNotNull(search);
            assertNotNull(search.getName());
            assertNotNull(search.getDisplayName());

            SearchRecordTypeDesc searchRecordInfo = connection.getSearchRecordType(search.getName());
            assertNotNull("Search record def found: " + search.getName(), searchRecordInfo);
        }
    }

    @Test
    public void testLoadCustomizations() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        for (RecordTypeDesc recordType : Arrays.asList(RecordTypeEnum.OPPORTUNITY, RecordTypeEnum.CALENDAR_EVENT)) {
            long start = System.currentTimeMillis();
            Map<String, CustomFieldDesc> customFieldMap = connection.getRecordCustomFields(recordType);
            System.out.println(customFieldMap);
            long end = System.currentTimeMillis();
            System.out.println(">>>: " + (end - start));
        }
    }
}