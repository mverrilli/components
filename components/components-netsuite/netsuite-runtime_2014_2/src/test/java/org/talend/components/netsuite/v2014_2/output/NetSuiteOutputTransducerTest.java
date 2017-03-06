package org.talend.components.netsuite.v2014_2.output;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.NetSuiteDataSetRuntime;
import org.talend.components.netsuite.NetSuiteRuntime;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.output.NsObjectOutputTransducer;
import org.talend.components.netsuite.v2014_2.NetSuiteRuntimeImpl;

import com.netsuite.webservices.v2014_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2014_2.platform.core.RecordRef;
import com.netsuite.webservices.v2014_2.platform.core.types.RecordType;
import com.netsuite.webservices.v2014_2.transactions.sales.Opportunity;

/**
 *
 */
public class NetSuiteOutputTransducerTest extends NetSuiteOutputMockTestBase {
    protected NetSuitePortType port;
    protected NetSuiteClientService clientService;

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

        port = webServiceMockTestFixture.getPortMock();
        clientService = webServiceMockTestFixture.getClientService();
    }

    @Override @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testBasic() throws Exception {

        NetSuiteRuntime netSuiteRuntime = new NetSuiteRuntimeImpl();
        NetSuiteDataSetRuntime dataSetRuntime = netSuiteRuntime.getDataSet(mockTestFixture.getConnectionProperties());

        TypeDesc typeDesc = clientService.getTypeInfo("Opportunity");

        Schema schema = dataSetRuntime.getSchema(typeDesc.getTypeName());

        NsObjectOutputTransducer transducer = new NsObjectOutputTransducer(
                webServiceMockTestFixture.getClientService(), typeDesc.getTypeName());

        List<IndexedRecord> indexedRecordList = makeIndexedRecords(clientService, schema,
                new SimpleObjectComposer<>(Opportunity.class), 10);

        for (IndexedRecord indexedRecord : indexedRecordList) {
            Opportunity record = (Opportunity) transducer.write(indexedRecord);
            assertNsObject(typeDesc, record);
        }
    }

    @Test
    public void testNonRecordObjects() throws Exception {

        NetSuiteRuntime netSuiteRuntime = new NetSuiteRuntimeImpl();
        NetSuiteDataSetRuntime dataSetRuntime = netSuiteRuntime.getDataSet(mockTestFixture.getConnectionProperties());

        Collection<String> typeNames = Arrays.asList("RecordRef");

        for (String typeName : typeNames) {
            TypeDesc typeDesc = clientService.getTypeInfo(typeName);

            Schema schema = dataSetRuntime.getSchema(typeDesc.getTypeName());

            NsObjectOutputTransducer transducer = new NsObjectOutputTransducer(webServiceMockTestFixture.getClientService(),
                    typeDesc.getTypeName());

            List<IndexedRecord> indexedRecordList = makeIndexedRecords(clientService, schema,
                    new SimpleObjectComposer<>(typeDesc.getTypeClass()), 10);

            for (IndexedRecord indexedRecord : indexedRecordList) {
                Object nsObject = transducer.write(indexedRecord);
                assertNsObject(typeDesc, nsObject);
            }
        }
    }

    @Test
    public void testRecordRef() throws Exception {

        NetSuiteRuntime netSuiteRuntime = new NetSuiteRuntimeImpl();
        NetSuiteDataSetRuntime dataSetRuntime = netSuiteRuntime.getDataSet(mockTestFixture.getConnectionProperties());

        TypeDesc typeDesc = clientService.getTypeInfo("RecordRef");
        TypeDesc referencedTypeDesc = clientService.getTypeInfo("Opportunity");

        Schema schema = dataSetRuntime.getSchema(typeDesc.getTypeName());

        NsObjectOutputTransducer transducer = new NsObjectOutputTransducer(webServiceMockTestFixture.getClientService(),
                referencedTypeDesc.getTypeName(), true);

        List<IndexedRecord> indexedRecordList = makeIndexedRecords(clientService, schema,
                new SimpleObjectComposer<>(typeDesc.getTypeClass()), 10);

        for (IndexedRecord indexedRecord : indexedRecordList) {
            Object nsObject = transducer.write(indexedRecord);
            assertNsObject(typeDesc, nsObject);

            RecordRef ref = (RecordRef) nsObject;
            assertEquals(RecordType.OPPORTUNITY, ref.getType());
        }
    }

}
