// ============================================================================
//
// Copyright (C) 2006-2017 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.components.netsuite.v2014_2.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.talend.components.netsuite.client.model.beans.Beans.getProperty;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.AbstractNetSuiteTestBase;
import org.talend.components.netsuite.NetSuiteWebServiceTestFixture;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.model.SearchRecordTypeDesc;
import org.talend.components.netsuite.client.search.SearchCondition;
import org.talend.components.netsuite.client.search.SearchResultSet;
import org.talend.daikon.NamedThing;

import com.netsuite.webservices.v2014_2.lists.accounting.types.AccountType;
import com.netsuite.webservices.v2014_2.platform.core.Record;

/**
 *
 */
public class NetSuiteClientServiceIT extends AbstractNetSuiteTestBase {
    protected static NetSuiteWebServiceTestFixture webServiceTestFixture;

    @BeforeClass
    public static void classSetUp() throws Exception {
        webServiceTestFixture = new NetSuiteWebServiceTestFixture(
                NetSuiteClientFactoryImpl.INSTANCE, "2014_2");
        classScopedTestFixtures.add(webServiceTestFixture);
        setUpClassScopedTestFixtures();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        tearDownClassScopedTestFixtures();
    }

    @Test
    public void testConnectAndLogin() throws Exception {
        NetSuiteClientService<?> connection = webServiceTestFixture.getClientService();
        connection.login();

        SearchResultSet<Record> rs = connection.newSearch()
                .target("Account")
                .condition(new SearchCondition("Type", "List.anyOf", Arrays.asList("Bank")))
                .search();

        int count = 10;
        int retrievedCount = 0;
        while (rs.next() && count-- > 0) {
            Record record = rs.get();

            assertEquals(AccountType.BANK, getProperty(record, "acctType"));

            retrievedCount++;
        }
        assertTrue(retrievedCount > 1);
    }

    @Test
    public void testGetSearchableTypes() throws Exception {
        NetSuiteClientService<?> connection = webServiceTestFixture.getClientService();
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
}