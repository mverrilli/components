package org.talend.components.netsuite.output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.talend.components.api.component.runtime.Result;
import org.talend.components.netsuite.NetSuiteSink;
import org.talend.components.netsuite.RuntimeService;
import org.talend.components.netsuite.RuntimeServiceImpl;
import org.talend.components.netsuite.SchemaService;
import org.talend.components.netsuite.client.NetSuiteClientService;

import com.netsuite.webservices.v2016_2.lists.accounting.Account;
import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.RecordRef;
import com.netsuite.webservices.v2016_2.platform.core.types.RecordType;
import com.netsuite.webservices.v2016_2.platform.messages.UpdateRequest;
import com.netsuite.webservices.v2016_2.platform.messages.UpdateResponse;
import com.netsuite.webservices.v2016_2.platform.messages.WriteResponse;

/**
 *
 */
public class NetSuiteOutputWriterTest extends NetSuiteOutputMockTestBase {
    protected NetSuiteOutputProperties properties;

    @BeforeClass
    public static void classSetUp() throws Exception {
        installWebServiceTestFixture();
        setUpClassScopedTestFixtures();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        tearDownClassScopedTestFixtures();
    }

    @Override @Before
    public void setUp() throws Exception {
        installMockTestFixture();
        super.setUp();

        properties = new NetSuiteOutputProperties("test");
        properties.init();
        properties.connection.copyValuesFrom(mockTestFixture.getConnectionProperties());
    }

    @Override @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testUpdate() throws Exception {
        final NetSuitePortType port = webServiceMockTestFixture.getPortMock();

        final List<Account> updatedRecordList = new ArrayList<>();
        when(port.update(any(UpdateRequest.class))).then(new Answer<UpdateResponse>() {
            @Override public UpdateResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                UpdateRequest request = (UpdateRequest) invocationOnMock.getArguments()[0];
                Account record = (Account) request.getRecord();
                assertNotNull(record);
                assertNotNull(record.getInternalId());

                RecordRef recordRef = new RecordRef();
                recordRef.setInternalId(record.getInternalId());
                recordRef.setType(RecordType.ACCOUNT);

                updatedRecordList.add(record);

                UpdateResponse response = new UpdateResponse();
                WriteResponse writeResponse = new WriteResponse();
                writeResponse.setStatus(createSuccessStatus());
                writeResponse.setBaseRef(recordRef);
                response.setWriteResponse(writeResponse);
                return response;
            }
        });

        properties.module.moduleName.setValue("Account");
        properties.action.setValue(NetSuiteOutputProperties.OutputAction.UPDATE);

        RuntimeService runtimeService = new RuntimeServiceImpl();
        SchemaService schemaService = runtimeService.getSchemaService(properties.getConnectionProperties());

        Schema schema = schemaService.getSchema(properties.module.moduleName.getValue());

        properties.module.main.schema.setValue(schema);

        NetSuiteSink sink = new NetSuiteSink();
        sink.initialize(mockTestFixture.getRuntimeContainer(), properties);

        NetSuiteClientService clientService = sink.getClientService();

        NetSuiteWriteOperation writeOperation = (NetSuiteWriteOperation) sink.createWriteOperation();
        NetSuiteOutputWriter writer = (NetSuiteOutputWriter) writeOperation.createWriter(
                mockTestFixture.getRuntimeContainer());
        writer.open(UUID.randomUUID().toString());

        List<IndexedRecord> indexedRecordList = makeIndexedRecords(clientService, schema,
                new SimpleObjectComposer<>(Account.class), 100);

        for (IndexedRecord record : indexedRecordList) {
            writer.write(record);
        }

        Result writerResult = writer.close();
        assertNotNull(writerResult);
        assertEquals(indexedRecordList.size(), writerResult.totalCount);

        verify(port, times(indexedRecordList.size())).update(any(UpdateRequest.class));
        assertEquals(indexedRecordList.size(), updatedRecordList.size());
    }
}
