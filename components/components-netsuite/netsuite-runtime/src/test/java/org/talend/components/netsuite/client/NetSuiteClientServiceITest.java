package org.talend.components.netsuite.client;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.client.search.SearchResultSet;

import com.netsuite.webservices.lists.accounting.types.AccountType;
import com.netsuite.webservices.platform.core.Record;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class NetSuiteClientServiceITest {

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
                .entity("Account")
                .criteria("type", "List.anyOf", Arrays.asList("Bank"))
                .search();

        int count = 0;
        while (rs.next()) {
            Record record = rs.get();
            NsObject nsRecord = NsObject.wrap(record);

            assertEquals(AccountType.BANK, nsRecord.get("acctType"));

            count++;
        }
        System.out.println("Retrieved records: " + count);
        assertTrue(count > 1);
    }

}