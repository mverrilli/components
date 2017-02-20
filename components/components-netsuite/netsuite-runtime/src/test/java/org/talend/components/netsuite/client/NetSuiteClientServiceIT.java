package org.talend.components.netsuite.client;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.client.common.SearchResultSet;
import org.talend.components.netsuite.client.model.SearchRecordInfo;
import org.talend.components.netsuite.client.query.SearchCondition;
import org.talend.daikon.NamedThing;

import com.netsuite.webservices.v2016_2.lists.accounting.types.AccountType;
import com.netsuite.webservices.v2016_2.platform.core.Record;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.talend.components.netsuite.client.NetSuiteFactory.getBeanProperty;

/**
 *
 */
public class NetSuiteClientServiceIT {

    protected static NetSuiteWebServiceTestFixture webServiceTestFixture;

    @BeforeClass
    public static void classSetUp() throws Exception {
        webServiceTestFixture = new NetSuiteWebServiceTestFixture();
        webServiceTestFixture.setUp();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        webServiceTestFixture.tearDown();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConnectAndLogin() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        connection.login();

        SearchResultSet<Record> rs = connection.newSearch()
                .target("Account")
                .condition(new SearchCondition("type", "List.anyOf", Arrays.asList("Bank")))
                .search();

        int count = 0;
        while (rs.next()) {
            Record record = rs.get();

            assertEquals(AccountType.BANK, getBeanProperty(record, "acctType"));

            count++;
        }
        System.out.println("Retrieved records: " + count);
        assertTrue(count > 1);
    }

    @Test
    public void testGetSearches() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        connection.login();

        Collection<NamedThing> searches = connection.getSearches();

        for (NamedThing search : searches) {
            assertNotNull(search);
            assertNotNull(search.getName());
            assertNotNull(search.getDisplayName());

            SearchRecordInfo searchRecordInfo = connection.getSearchRecordInfo(search.getName());
            assertNotNull("Search record def found: " + search.getName(), searchRecordInfo);
        }
    }

    @Test
    public void testLoadCustomizations() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        Collection<NamedThing> recordTypes = connection.getRecordTypes();
        for (NamedThing recordType : recordTypes) {
            System.out.println(recordType);
        }
    }
}