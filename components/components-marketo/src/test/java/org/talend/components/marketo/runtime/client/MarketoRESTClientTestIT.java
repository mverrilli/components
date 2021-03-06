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
package org.talend.components.marketo.runtime.client;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.IncludeExcludeFieldsSOAP.ChangeDataValue;
import static org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.InputOperation.getLead;
import static org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.InputOperation.getLeadActivity;
import static org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.InputOperation.getLeadChanges;
import static org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.InputOperation.getMultipleLeads;
import static org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.LeadKeyTypeREST.email;
import static org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.LeadKeyTypeREST.linkedInId;
import static org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.LeadSelector.StaticListSelector;
import static org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.ListParam.STATIC_LIST_ID;
import static org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.ListParam.STATIC_LIST_NAME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.IndexedRecord;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.marketo.MarketoConstants;
import org.talend.components.marketo.runtime.MarketoSource;
import org.talend.components.marketo.runtime.client.rest.type.SyncStatus;
import org.talend.components.marketo.runtime.client.type.ListOperationParameters;
import org.talend.components.marketo.runtime.client.type.MarketoError;
import org.talend.components.marketo.runtime.client.type.MarketoRecordResult;
import org.talend.components.marketo.runtime.client.type.MarketoSyncResult;
import org.talend.components.marketo.tmarketoconnection.TMarketoConnectionProperties.APIMode;
import org.talend.components.marketo.tmarketoinput.TMarketoInputProperties;
import org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.IncludeExcludeFieldsREST;
import org.talend.components.marketo.tmarketoinput.TMarketoInputProperties.IncludeExcludeFieldsSOAP;
import org.talend.components.marketo.tmarketolistoperation.TMarketoListOperationProperties;
import org.talend.components.marketo.tmarketooutput.TMarketoOutputProperties;
import org.talend.components.marketo.tmarketooutput.TMarketoOutputProperties.OperationType;
import org.talend.components.marketo.tmarketooutput.TMarketoOutputProperties.OutputOperation;
import org.talend.components.marketo.tmarketooutput.TMarketoOutputProperties.RESTLookupFields;

public class MarketoRESTClientTestIT extends MarketoClientTestIT {

    MarketoRESTClient client;

    TMarketoInputProperties iprops;

    TMarketoListOperationProperties listProperties;

    TMarketoOutputProperties outProperties;

    private transient static final Logger LOG = LoggerFactory.getLogger(MarketoRESTClientTestIT.class);

    @Before
    public void setUp() throws Exception {
        iprops = new TMarketoInputProperties("test");
        iprops.connection.setupProperties();
        iprops.connection.endpoint.setValue(ENDPOINT_REST);
        iprops.connection.clientAccessId.setValue(USERID_REST);
        iprops.connection.secretKey.setValue(SECRETKEY_REST);
        iprops.connection.apiMode.setValue(APIMode.REST);
        iprops.schemaInput.setupProperties();
        iprops.mappingInput.setupProperties();
        iprops.setupProperties();
        iprops.includeTypes.setupProperties();
        iprops.includeTypes.type.setValue(new ArrayList<String>());
        iprops.excludeTypes.setupProperties();
        iprops.excludeTypes.type.setValue(new ArrayList<String>());
        iprops.connection.setupLayout();
        iprops.schemaInput.setupLayout();
        iprops.setupLayout();

        //
        listProperties = new TMarketoListOperationProperties("test");
        listProperties.connection.setupProperties();
        listProperties.connection.endpoint.setValue(ENDPOINT_REST);
        listProperties.connection.clientAccessId.setValue(USERID_REST);
        listProperties.connection.secretKey.setValue(SECRETKEY_REST);
        listProperties.connection.apiMode.setValue(APIMode.REST);
        listProperties.schemaInput.setupProperties();
        listProperties.setupProperties();
        listProperties.connection.setupLayout();
        listProperties.schemaInput.setupLayout();
        listProperties.setupLayout();
        //
        outProperties = new TMarketoOutputProperties("test");
        outProperties.connection.setupProperties();
        outProperties.connection.endpoint.setValue(ENDPOINT_REST);
        outProperties.connection.clientAccessId.setValue(USERID_REST);
        outProperties.connection.secretKey.setValue(SECRETKEY_REST);
        outProperties.connection.apiMode.setValue(APIMode.REST);
        outProperties.schemaInput.setupProperties();
        outProperties.setupProperties();
        outProperties.connection.setupLayout();
        outProperties.schemaInput.setupLayout();
        outProperties.setupLayout();
    }

    @Test
    public void testIsAccessTokenExpired() throws Exception {
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        assertFalse(((MarketoRESTClient) client).isAccessTokenExpired(null));
        MarketoError err = new MarketoError("REST", "200", "dfddf");
        assertFalse(((MarketoRESTClient) client).isAccessTokenExpired(Arrays.asList(err)));
        err.setCode("602");
        assertTrue(((MarketoRESTClient) client).isAccessTokenExpired(Arrays.asList(err)));
    }

    @Test(expected = IOException.class)
    public void testBadConnectionString() throws Exception {
        iprops.connection.endpoint.setValue("htps://marketo.com/rest/v1");
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        fail("Shouldn't be here");
    }

    @Test(expected = IOException.class)
    public void testBadURIConnectionString() throws Exception {
        iprops.connection.endpoint.setValue("htps:marketo.comrestv1");
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        fail("Shouldn't be here");
    }
    /*
     *
     * ************************ getLead ************************
     *
     */

    @Test
    public void testGetLead() throws Exception {
        iprops.inputOperation.setValue(getLead);
        iprops.leadKeyTypeREST.setValue(email);
        iprops.afterInputOperation();
        //
        String email = EMAIL_UNDX00;
        iprops.leadKeyValue.setValue(email);
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        assertTrue(((MarketoRESTClient) client).isAvailable());
        //
        MarketoRecordResult result = client.getLead(iprops, null);
        LOG.debug("{}", result);
        List<IndexedRecord> records = result.getRecords();
        assertNotEquals(emptyList(), records);
        IndexedRecord record = records.get(0);
        assertNotNull(record);
        assertNotNull(record.get(0));
        assertEquals(email, record.get(1));
    }

    @Test
    public void testGetLeadMany() throws Exception {
        iprops.inputOperation.setValue(getLead);
        iprops.leadKeyTypeREST.setValue(linkedInId);
        iprops.afterInputOperation();
        iprops.leadKeyValue.setValue(COMMON_LINKEDIN_ID.toString());
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getLead(iprops, null);
        LOG.debug("{}", result);
        List<IndexedRecord> records = result.getRecords();
        int count = 0;
        assertNotEquals(emptyList(), records);
        for (IndexedRecord record : records) {
            count++;
            assertNotNull(record);
            assertNotNull(record.get(0));// id
            assertTrue(record.get(1).toString().startsWith(EMAIL_PREFIX));
        }
        assertEquals(50, count);
    }

    @Test
    public void testGetLeadFail() throws Exception {
        iprops.inputOperation.setValue(getLead);
        iprops.leadKeyTypeREST.setValue(email);
        iprops.afterInputOperation();
        //
        iprops.leadKeyValue.setValue(EMAIL_INEXISTANT);
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getLead(iprops, null);
        LOG.debug("{}", result);
        List<IndexedRecord> records = result.getRecords();
        assertEquals(emptyList(), records);
    }

    @Test(expected = IOException.class)
    public void testGetLeadFailWrongHost() throws Exception {
        iprops.inputOperation.setValue(getLead);
        iprops.leadKeyTypeREST.setValue(email);
        iprops.connection.endpoint.setValue(ENDPOINT_URL_INEXISTANT);
        iprops.afterInputOperation();
        //
        iprops.leadKeyValue.setValue(EMAIL_INEXISTANT);
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = null;
        client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getLead(iprops, null);
        LOG.debug("{}", result);
        List<IndexedRecord> records = result.getRecords();
        assertEquals(emptyList(), records);
    }

    @Test
    public void testGetLeadSchema() throws Exception {
        iprops.inputOperation.setValue(getLead);
        iprops.leadKeyTypeREST.setValue(email);
        iprops.afterInputOperation();
        String email = EMAIL_LEAD_MANY_INFOS;
        iprops.leadKeyValue.setValue(email);
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getLead(iprops, null);
        LOG.debug("{}", result);
        List<IndexedRecord> records = result.getRecords();
        assertNotEquals(emptyList(), records);
        IndexedRecord record = records.get(0);
        assertNotNull(record);
        Schema s = record.getSchema();
        assertEquals(s, MarketoConstants.getRESTSchemaForGetLeadOrGetMultipleLeads());
        LOG.debug("record = {}.", record);
        assertEquals("int", s.getField("id").schema().getTypes().get(0).getName());
        assertEquals("string", s.getField("email").schema().getTypes().get(0).getName());
        assertEquals(email, record.get(1));
        assertEquals("string", s.getField("email").schema().getTypes().get(0).getName());
        assertEquals("string", s.getField("firstName").schema().getTypes().get(0).getName());
    }

    /*
     *
     * ************************ getMultipleLeads ************************
     *
     */
    @Test
    public void testGetMultipleLeadsLeadKey() throws Exception {
        iprops.inputOperation.setValue(getMultipleLeads);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(100);
        iprops.leadKeyTypeREST.setValue(email);
        iprops.leadKeyValues.setValue("undx00@undx.net,undx10@undx.net,undx20@undx.net,undx30@undx.net");
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getMultipleLeads(iprops, null);
        LOG.debug("{}", result);
        List<IndexedRecord> records = result.getRecords();
        assertEquals(4, records.size());
    }

    @Test
    public void testGetMultipleLeadsLeadKeyFail() throws Exception {
        iprops.inputOperation.setValue(getMultipleLeads);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(100);
        iprops.leadKeyTypeREST.setValue(email);
        iprops.leadKeyValues.setValue("i-dont-exist@mail.com,bad-email@dot.net");
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        MarketoRecordResult result = client.getMultipleLeads(iprops, null);
        LOG.debug("{}", result);
        assertTrue(result.isSuccess()); // but no leads
        assertEquals(0, result.getRecordCount());
        assertEquals(0, result.getRemainCount());
    }

    @Test
    public void testGetMultipleLeadsListName() throws Exception {
        iprops.inputOperation.setValue(getMultipleLeads);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(4); // we have at least 5 members with createDatasets.
        iprops.leadSelectorREST.setValue(StaticListSelector);
        iprops.listParam.setValue(STATIC_LIST_NAME);
        iprops.listParamValue.setValue(UNDX_TEST_LIST_SMALL);
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getMultipleLeads(iprops, null);
        LOG.debug("{}", result);
        assertTrue(result.isSuccess());
        assertTrue(result.getRecordCount() > 3);
        assertTrue(result.getRemainCount() > 0);
        assertNotNull(result.getStreamPosition());
    }

    @Test
    public void testGetMultipleLeadsListNamePagination() throws Exception {
        iprops.inputOperation.setValue(getMultipleLeads);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(104);
        iprops.leadSelectorREST.setValue(StaticListSelector);
        iprops.listParam.setValue(STATIC_LIST_NAME);
        iprops.listParamValue.setValue(UNDX_TEST_LIST_SMALL);
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getMultipleLeads(iprops, null);
        int counted = result.getRecordCount();
        assertTrue(result.getRecordCount() > 0);
        while (result.getRemainCount() > 0) {
            result = client.getMultipleLeads(iprops, result.getStreamPosition());
            assertNotNull(result.getRecords().get(0).get(0));
            LOG.debug("{}", result);
            counted += result.getRecordCount();
        }
        LOG.debug(result.getRecords().get(0).getSchema().toString());
        assertEquals("int", result.getRecords().get(0).getSchema().getField("id").schema().getTypes().get(0).getName());
        assertTrue(counted > 4);
    }

    @Test
    public void testGetMultipleLeadsListNameFail() throws Exception {
        iprops.inputOperation.setValue(getMultipleLeads);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(200);
        //
        iprops.leadSelectorREST.setValue(StaticListSelector);
        iprops.listParam.setValue(STATIC_LIST_NAME);
        iprops.listParamValue.setValue("undx_test_list******");
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getMultipleLeads(iprops, null);
        LOG.debug("{}", result);
        assertFalse(result.isSuccess());
        assertNotNull(result.getErrors());
        assertEquals(0, result.getRecordCount());
        assertEquals(0, result.getRemainCount());
        assertEquals(emptyList(), client.getMultipleLeads(iprops, null).getRecords());
    }

    @Test
    public void testGetMultipleLeadsListId() throws Exception {
        iprops.inputOperation.setValue(getMultipleLeads);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(10);
        //
        iprops.leadSelectorREST.setValue(StaticListSelector);
        iprops.listParam.setValue(STATIC_LIST_ID);
        iprops.listParamValue.setValue(UNDX_TEST_LIST_SMALL_ID.toString());
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getMultipleLeads(iprops, null);
        LOG.debug("{}", result);
        assertTrue(result.isSuccess());
        assertNull(result.getErrors());
        assertNotEquals(0, result.getRecordCount());
        assertNotEquals(0, result.getRemainCount());
        assertTrue(result.getRecordCount() > 4);
    }

    @Test
    public void testGetMultipleLeadsListIdFail() throws Exception {
        iprops.inputOperation.setValue(getMultipleLeads);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(200);
        //
        iprops.leadSelectorREST.setValue(StaticListSelector);
        iprops.listParam.setValue(STATIC_LIST_ID);
        iprops.listParamValue.setValue("-666");
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getMultipleLeads(iprops, null);
        LOG.debug("{}", result);
        assertFalse(result.isSuccess());
        assertNotNull(result.getErrors());
        assertEquals(0, result.getRecordCount());
        assertEquals(0, result.getRemainCount());
        assertFalse(result.getRecordCount() > 0);
        assertEquals(emptyList(), result.getRecords());
    }

    /*
     *
     * ************************ getLeadActivity ************************
     *
     */
    @Test
    public void testGetLeadActivity() throws Exception {
        iprops.inputOperation.setValue(getLeadActivity);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(50);
        iprops.sinceDateTime.setValue(DATE_OLDEST_CREATE);
        //
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getLeadActivity(iprops, null);
        LOG.debug("{}", result);
        assertTrue(result.isSuccess());
        assertTrue(result.getRecordCount() > 0);
    }

    @Test
    public void testGetLeadActivityPagination() throws Exception {
        iprops.inputOperation.setValue(getLeadActivity);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(300);
        iprops.sinceDateTime.setValue(DATE_LATEST_UPDATE);
        //
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getLeadActivity(iprops, null);
        int counted = result.getRecordCount();
        while (result.getRemainCount() > 0) {
            result = client.getLeadActivity(iprops, result.getStreamPosition());
            counted += result.getRecordCount();
        }
        assertTrue(counted >= iprops.batchSize.getValue());
    }

    @Test
    public void testGetLeadActivityIncludeFilter() throws Exception {
        iprops.inputOperation.setValue(getLeadActivity);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(300);
        iprops.includeTypes.type.getValue().add(IncludeExcludeFieldsREST.NewLead.toString());
        iprops.includeTypes.type.getValue().add(IncludeExcludeFieldsREST.ChangeDataValue.toString());
        iprops.sinceDateTime.setValue(DATE_OLDEST_CREATE);
        //
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getLeadActivity(iprops, null);
        LOG.debug("{}", result);
        List<IndexedRecord> activities = result.getRecords();
        assertTrue(activities.size() > 0);
        for (IndexedRecord r : activities) {
            LOG.debug("r=" + r);
            assertTrue("New Lead".equals(r.get(4)) || "Change Data Value".equals(r.get(4)));
        }
    }

    @Test
    public void testGetLeadActivityExcludeFilter() throws Exception {
        iprops.inputOperation.setValue(getLeadActivity);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(300);
        iprops.excludeTypes.type.getValue().add(IncludeExcludeFieldsSOAP.VisitWebpage.toString());
        iprops.excludeTypes.type.getValue().add(ChangeDataValue.toString());
        iprops.sinceDateTime.setValue(DATE_OLDEST_CREATE);
        //
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getLeadActivity(iprops, null);
        LOG.debug("{}", result);
        List<IndexedRecord> activities = result.getRecords();
        assertTrue(activities.size() > 0);
        for (IndexedRecord r : activities) {
            assertTrue(!"Visit Webpage".equals(r.get(4)) && !"Change Data Value".equals(r.get(4)));
        }
    }
    /*
     *
     * ************************ getLeadChanges ************************
     *
     */

    @Test
    public void testGetLeadsChanges() throws Exception {
        iprops.inputOperation.setValue(getLeadChanges);
        iprops.afterInputOperation();
        iprops.batchSize.setValue(100);
        iprops.sinceDateTime.setValue(DATE_OLDEST_CREATE);
        iprops.fieldList.setValue("id,email,firstName,lastName,company");
        //
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getLeadChanges(iprops, null);
        LOG.debug("{}", result);
        List<IndexedRecord> changes = result.getRecords();
        assertTrue(changes.size() > 0);
        assertTrue(result.getRemainCount() > 0);
    }

    @Test
    public void testGetLeadsChangesPagination() throws Exception {
        iprops.afterInputOperation();
        iprops.batchSize.setValue(300);
        iprops.sinceDateTime.setValue(DATE_OLDEST_CREATE);
        iprops.fieldList.setValue("id,email,firstName,lastName,company");
        //
        MarketoSource source = new MarketoSource();
        source.initialize(null, iprops);
        MarketoClientService client = source.getClientService(null);
        //
        MarketoRecordResult result = client.getLeadChanges(iprops, null);
        LOG.debug("{}", result);
        List<IndexedRecord> changes = null;
        int counted = 0;
        result = client.getLeadChanges(iprops, null);
        counted = result.getRecordCount();
        while (result.getRemainCount() > 0) {
            result = client.getLeadChanges(iprops, result.getStreamPosition());
            counted += result.getRecordCount();
            changes = result.getRecords();
        }
        assertTrue(iprops.batchSize.getValue() < counted);
    }

    /*
     * 
     * 
     * ListOperations
     *
     */
    @Test
    public void testAddToList() throws Exception {
        MarketoSource source = new MarketoSource();
        source.initialize(null, listProperties);
        MarketoClientService client = source.getClientService(null);
        //
        ListOperationParameters parms = new ListOperationParameters();
        parms.setApiMode(APIMode.REST.name());
        parms.setListId(UNDX_TEST_LIST_SMALL_ID);
        parms.setLeadIds(new Integer[] { createdLeads.get(10) });
        //
        // first make sure to remove lead
        MarketoSyncResult result = client.removeFromList(parms);
        LOG.debug("result = {}.", result);
        List<SyncStatus> changes = result.getRecords();
        assertTrue(changes.size() > 0);
        for (SyncStatus r : changes) {
            assertNotNull(r);
            assertNotNull(r.getId());
            LOG.debug("r = {}.", r);
        }
        // then add it
        result = client.addToList(parms);
        LOG.debug("result = {}.", result);
        changes = result.getRecords();
        assertTrue(changes.size() > 0);
        for (SyncStatus r : changes) {
            assertNotNull(r);
            assertNotNull(r.getId());
            assertEquals("added", r.getStatus());
            LOG.debug("r = {}.", r);
        }
    }

    @Test
    public void testIsMemberOfList() throws Exception {
        MarketoSource source = new MarketoSource();
        source.initialize(null, listProperties);
        MarketoClientService client = source.getClientService(null);
        //
        ListOperationParameters parms = new ListOperationParameters();
        parms.setApiMode(APIMode.REST.name());
        parms.setListId(UNDX_TEST_LIST_SMALL_ID);
        parms.setLeadIds(new Integer[] { createdLeads.get(0), createdLeads.get(1), createdLeads.get(2) });
        //
        MarketoSyncResult result = client.isMemberOfList(parms);
        LOG.debug("result = {}.", result);
        List<SyncStatus> changes = result.getRecords();
        assertTrue(changes.size() > 0);
        for (SyncStatus r : changes) {
            assertNotNull(r);
            assertNotNull(r.getId());
            assertEquals("memberof", r.getStatus());
            LOG.debug("r = {}.", r);
        }
    }

    @Test
    public void testRemoveFromList() throws Exception {
        MarketoSource source = new MarketoSource();
        source.initialize(null, listProperties);
        MarketoClientService client = source.getClientService(null);
        //
        ListOperationParameters parms = new ListOperationParameters();
        parms.setApiMode(APIMode.REST.name());
        parms.setListId(UNDX_TEST_LIST_SMALL_ID);
        parms.setLeadIds(new Integer[] { createdLeads.get(20) });
        //
        // first subscribe lead
        MarketoSyncResult result = client.addToList(parms);
        LOG.debug("result = {}.", result);
        List<SyncStatus> changes = result.getRecords();
        assertTrue(changes.size() > 0);
        for (SyncStatus r : changes) {
            assertNotNull(r);
            assertNotNull(r.getId());
            LOG.debug("r = {}.", r);
        }
        // then remove it
        result = client.removeFromList(parms);
        LOG.debug("result = {}.", result);
        changes = result.getRecords();
        assertTrue(changes.size() > 0);
        for (SyncStatus r : changes) {
            assertNotNull(r);
            assertNotNull(r.getId());
            assertEquals("removed", r.getStatus());
            LOG.debug("r = {}.", r);
        }
    }
    /*
     *
     *
     * syncLeads
     *
     */

    @Test
    public void testSyncLead() throws Exception {
        outProperties.outputOperation.setValue(OutputOperation.syncLead);
        outProperties.operationType.setValue(OperationType.createOrUpdate);
        outProperties.lookupField.setValue(RESTLookupFields.email);
        outProperties.deDupeEnabled.setValue(false);
        outProperties.afterApiMode();
        outProperties.updateSchemaRelated();
        MarketoSource source = new MarketoSource();
        source.initialize(null, outProperties);
        MarketoClientService client = source.getClientService(null);
        //
        // test attributes
        List<Field> fields = new ArrayList<>();
        Field field = new Schema.Field("accountType", Schema.create(Schema.Type.STRING), null, (Object) null);
        fields.add(field);
        Schema s = outProperties.newSchema(outProperties.schemaInput.schema.getValue(), "leadAttribute", fields);
        IndexedRecord record = new GenericData.Record(s);
        record.put(0, null);
        record.put(1, "undx71@undx.net");
        record.put(2, "ForeignPersonSysId");
        record.put(3, "SFDC");// CUSTOM, SFDC, NETSUITE;
        record.put(4, "My firstName");
        List<IndexedRecord> leads = Arrays.asList(record);
        outProperties.schemaInput.schema.setValue(s);
        outProperties.updateMappings();
        ///
        MarketoSyncResult result = client.syncLead(outProperties, record);
        LOG.debug("result = {}.", result);
        List<SyncStatus> changes = result.getRecords();
        assertTrue(changes.size() > 0);
        for (SyncStatus r : changes) {
            assertNotNull(r);
            assertNotNull(r.getId());
            LOG.debug("r = {}.", r);
        }
    }

    /*
     *
     * management func
     *
     */
    @Test
    public void testDeleteLeads() throws Exception {
        MarketoSource source = new MarketoSource();
        source.initialize(null, outProperties);
        MarketoRESTClient client = (MarketoRESTClient) source.getClientService(null);
        //
        Integer[] ids = { 0, 1, 2, 2 };
        MarketoSyncResult result = client.deleteLeads(ids);
        LOG.debug("result = {}.", result);
        List<SyncStatus> changes = result.getRecords();
        assertTrue(changes.size() > 0);
        for (SyncStatus r : changes) {
            assertNotNull(r);
            assertNotNull(r.getId());
            assertEquals("skipped", r.getStatus());
            LOG.debug("r = {}.", r);
        }
    }

}
