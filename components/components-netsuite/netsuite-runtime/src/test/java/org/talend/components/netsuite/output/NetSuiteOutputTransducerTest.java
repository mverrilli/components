package org.talend.components.netsuite.output;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.RuntimeService;
import org.talend.components.netsuite.RuntimeServiceImpl;
import org.talend.components.netsuite.SchemaService;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.client.model.customfield.CustomFieldRefType;

import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.RecordRef;
import com.netsuite.webservices.v2016_2.platform.core.types.RecordType;
import com.netsuite.webservices.v2016_2.setup.customization.TransactionBodyCustomField;
import com.netsuite.webservices.v2016_2.setup.customization.types.CustomizationFieldType;
import com.netsuite.webservices.v2016_2.transactions.sales.Opportunity;

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

        RuntimeService runtimeService = new RuntimeServiceImpl();
        SchemaService schemaService = runtimeService.getSchemaService(mockTestFixture.getConnectionProperties());

        TypeDesc typeDesc = clientService.getTypeInfo("Opportunity");

        Schema schema = schemaService.getSchema(typeDesc.getTypeName());

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

        RuntimeService runtimeService = new RuntimeServiceImpl();
        SchemaService schemaService = runtimeService.getSchemaService(mockTestFixture.getConnectionProperties());

        TypeDesc typeDesc = clientService.getTypeInfo("RecordRef");

        Schema schema = schemaService.getSchema(typeDesc.getTypeName());

        NsObjectOutputTransducer transducer = new NsObjectOutputTransducer(
                webServiceMockTestFixture.getClientService(), typeDesc.getTypeName());

        List<IndexedRecord> indexedRecordList = makeIndexedRecords(clientService, schema,
                new SimpleObjectComposer<>(RecordRef.class), 10);

        for (IndexedRecord indexedRecord : indexedRecordList) {
            RecordRef record = (RecordRef) transducer.write(indexedRecord);
            assertNsObject(typeDesc, record);
        }
    }

    @Test
    public void testCustomFields() throws Exception {

        RuntimeService runtimeService = new RuntimeServiceImpl();
        SchemaService schemaService = runtimeService.getSchemaService(mockTestFixture.getConnectionProperties());

        TypeDesc typeDesc = clientService.getTypeInfo("Opportunity");

        final Map<String, CustomFieldSpec> customFieldSpecs = createCustomFieldSpecs();
        mockCustomizationRequestResults(customFieldSpecs);

        final List<Opportunity> recordList = makeNsObjects(
                new RecordComposer<>(Opportunity.class, customFieldSpecs), 10);
        mockSearchRequestResults(recordList, 100);

        TypeDesc customizedTypeDesc = clientService.getCustomizedTypeInfo(typeDesc.getTypeName());

        Schema schema = schemaService.getSchema(customizedTypeDesc.getTypeName());

        NsObjectOutputTransducer transducer = new NsObjectOutputTransducer(
                webServiceMockTestFixture.getClientService(), typeDesc.getTypeName());

        List<IndexedRecord> indexedRecordList = makeIndexedRecords(clientService, schema,
                new RecordComposer<>(Opportunity.class, customFieldSpecs), 10);

        for (IndexedRecord indexedRecord : indexedRecordList) {
            Opportunity record = (Opportunity) transducer.write(indexedRecord);
            assertNsObject(typeDesc, record);
        }
    }

    protected Map<String, CustomFieldSpec> createCustomFieldSpecs() {
        CustomFieldSpec customBodyField1 = new CustomFieldSpec("custbody_field1", "1001",
                RecordType.TRANSACTION_BODY_CUSTOM_FIELD, TransactionBodyCustomField.class,
                CustomizationFieldType.CHECK_BOX, CustomFieldRefType.BOOLEAN,
                Arrays.asList("bodyOpportunity")
        );

        CustomFieldSpec customBodyField2 = new CustomFieldSpec("custbody_field2", "1002",
                RecordType.TRANSACTION_BODY_CUSTOM_FIELD, TransactionBodyCustomField.class,
                CustomizationFieldType.FREE_FORM_TEXT, CustomFieldRefType.STRING,
                Arrays.asList("bodyOpportunity")
        );

        CustomFieldSpec customBodyField3 = new CustomFieldSpec("custbody_field3", "1003",
                RecordType.TRANSACTION_BODY_CUSTOM_FIELD, TransactionBodyCustomField.class,
                CustomizationFieldType.DATETIME, CustomFieldRefType.DATE,
                Arrays.asList("bodyOpportunity")
        );

        Collection<CustomFieldSpec> specs = Arrays.asList(customBodyField1, customBodyField2, customBodyField3);
        Map<String, CustomFieldSpec> specMap = new HashMap<>();
        for (CustomFieldSpec spec : specs) {
            specMap.put(spec.getScriptId(), spec);
        }
        return specMap;
    }
}
