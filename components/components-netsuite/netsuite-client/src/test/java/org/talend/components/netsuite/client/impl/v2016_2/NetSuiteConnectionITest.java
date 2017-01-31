package org.talend.components.netsuite.client.impl.v2016_2;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NsObject;
import org.talend.components.netsuite.client.NsSearchResultSet;

import com.netsuite.webservices.v2016_2.lists.accounting.types.AccountType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class NetSuiteConnectionITest {

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
        NetSuiteConnection connection = webServiceTestFixture.getConnection();

        connection.login();

        NsSearchResultSet rs = connection.newSearch()
                .entity("Account")
                .criterion("type", "String.ANY_OF", Arrays.asList("Bank"))
                .search();

        int count = 0;
        while (rs.next()) {
            NsObject record = rs.get();

            assertEquals(AccountType.BANK, record.get("acctType"));

            count++;
        }
        System.out.println("Retrieved records: " + count);
        assertTrue(count > 1);
    }

}