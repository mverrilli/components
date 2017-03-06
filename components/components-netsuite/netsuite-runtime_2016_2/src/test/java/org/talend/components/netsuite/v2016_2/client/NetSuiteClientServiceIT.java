package org.talend.components.netsuite.v2016_2.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.v2016_2.NetSuiteTestBase;
import org.talend.components.netsuite.client.model.RecordTypeDesc;
import org.talend.components.netsuite.client.model.RecordTypeInfo;
import org.talend.components.netsuite.client.model.SearchRecordTypeDesc;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.client.search.SearchCondition;
import org.talend.components.netsuite.client.search.SearchQuery;
import org.talend.components.netsuite.client.search.SearchResultSet;
import org.talend.components.netsuite.v2016_2.client.model.RecordTypeEnum;
import org.talend.components.netsuite.v2016_2.NetSuiteWebServiceTestFixture;
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

        int count = 10;
        int retrievedCount = 0;
        while (rs.next() && count-- > 0) {
            Record record = rs.get();

            assertEquals(AccountType.BANK, getProperty(record, "acctType"));

            retrievedCount++;
        }
//        System.out.println("Retrieved records: " + retrievedCount);
        assertTrue(retrievedCount > 1);
    }

    @Test
    public void testSearchCustomRecord() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();
        connection.login();

        Collection<RecordTypeInfo> recordTypes = connection.getRecordTypes();
        RecordTypeInfo recordType = getCustomRecordType(recordTypes, "customrecord_campaign_revenue");

        SearchQuery searchQuery = connection.newSearch();
        searchQuery.target(recordType.getName());

        SearchResultSet<Record> rs = searchQuery.search();
        int count = 10;
        while (rs.next() && count-- > 0) {
            Record record = rs.get();
            assertNotNull(record);
//            System.out.println(record);
        }
    }

    @Test
    public void testGetSearchableTypes() throws Exception {
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
    public void testLoadCustomRecordTypes() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();
        connection.login();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Collection<RecordTypeInfo> recordTypes = connection.getRecordTypes();
        stopWatch.stop();
//        System.out.println("Total time: " + stopWatch);

        for (RecordTypeInfo recordType : recordTypes) {
            System.out.println(recordType.getName());
        }
    }

    @Test
    public void testLoadCustomRecordCustomFields() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();
        connection.login();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        RecordTypeInfo recordType = connection.getRecordType("customrecord_campaign_revenue");

        TypeDesc typeDesc = connection.getTypeInfo(recordType.getName());
        System.out.println(typeDesc);

        stopWatch.stop();
//        System.out.println("Total time: " + stopWatch);
    }

    @Test
    public void testLoadAllCustomizations() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();
        connection.login();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (RecordTypeDesc recordType : Arrays.asList(RecordTypeEnum.OPPORTUNITY, RecordTypeEnum.CALENDAR_EVENT)) {
            TypeDesc typeDesc = connection.getTypeInfo(recordType.getTypeName());
            System.out.println(typeDesc);
        }
        stopWatch.stop();
//        System.out.println("Total time: " + stopWatch);
    }

    protected RecordTypeInfo getCustomRecordType(Collection<RecordTypeInfo> recordTypes, String name) {
        for (RecordTypeInfo recordType : recordTypes) {
            if (recordType.getName().equals(name)) {
                return recordType;
            }
        }
        return null;
    }
}