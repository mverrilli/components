package org.talend.components.netsuite.v2016_2.input;

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
import org.talend.components.netsuite.NetSuiteDataSetRuntimeImpl;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.client.model.customfield.CustomFieldRefType;
import org.talend.components.netsuite.client.search.SearchResultSet;
import org.talend.components.netsuite.input.NsObjectInputTransducer;
import org.talend.components.netsuite.v2016_2.NetSuiteMockTestBase;

import com.netsuite.webservices.v2016_2.platform.core.Record;
import com.netsuite.webservices.v2016_2.platform.core.types.RecordType;
import com.netsuite.webservices.v2016_2.setup.customization.TransactionBodyCustomField;
import com.netsuite.webservices.v2016_2.setup.customization.types.CustomizationFieldType;
import com.netsuite.webservices.v2016_2.transactions.sales.Opportunity;

/**
 *
 */
public class NsObjectInputTransducerTest extends NetSuiteMockTestBase {

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
    }

    @Override @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testBasic() throws Exception {
        NetSuiteClientService connection = webServiceMockTestFixture.getClientService();
        connection.login();

        TypeDesc typeDesc = connection.getTypeInfo("Opportunity");

        final Map<String, CustomFieldSpec> customFieldSpecs = createCustomFieldSpecs();
        mockCustomizationRequestResults(customFieldSpecs);

        final List<Opportunity> recordList = makeNsObjects(
                new RecordComposer<>(Opportunity.class, customFieldSpecs), 10);

        Schema schema = NetSuiteDataSetRuntimeImpl.inferSchemaForType(typeDesc.getTypeName(), typeDesc.getFields());

        NsObjectInputTransducer transducer = new NsObjectInputTransducer(connection, schema, typeDesc.getTypeName());

        for (Record record : recordList) {
            IndexedRecord indexedRecord = transducer.read(record);
            assertIndexedRecord(typeDesc, indexedRecord);
        }
    }

    @Test
    public void testNonRecordObjects() throws Exception {
        NetSuiteClientService connection = webServiceMockTestFixture.getClientService();
        connection.login();

        Collection<String> typeNames = Arrays.asList("RecordRef", "CustomizationRef");

        for (String typeName : typeNames) {
            TypeDesc typeDesc = connection.getTypeInfo(typeName);

            final List<?> nsObjects = makeNsObjects(new SimpleObjectComposer<>(typeDesc.getTypeClass()), 10);

            Schema schema = NetSuiteDataSetRuntimeImpl.inferSchemaForType(typeDesc.getTypeName(), typeDesc.getFields());

            NsObjectInputTransducer transducer = new NsObjectInputTransducer(connection, schema, typeDesc.getTypeName());

            for (Object record : nsObjects) {
                IndexedRecord indexedRecord = transducer.read(record);
                assertIndexedRecord(typeDesc, indexedRecord);
            }
        }
    }

    @Test
    public void testDynamicSchemaWithCustomFields() throws Exception {
        NetSuiteClientService connection = webServiceMockTestFixture.getClientService();
        connection.login();

        TypeDesc basicTypeDesc = connection.getTypeInfo("Opportunity");

        final Map<String, CustomFieldSpec> customFieldSpecs = createCustomFieldSpecs();
        mockCustomizationRequestResults(customFieldSpecs);

        final List<Opportunity> recordList = makeNsObjects(
                new RecordComposer<>(Opportunity.class, customFieldSpecs), 10);
        mockSearchRequestResults(recordList, 100);

        TypeDesc typeDesc = connection.getTypeInfo(basicTypeDesc.getTypeName());

        Schema schema = getDynamicSchema();

        NsObjectInputTransducer transducer = new NsObjectInputTransducer(connection, schema, typeDesc.getTypeName());

        SearchResultSet<Record> rs = connection.newSearch()
                .target(basicTypeDesc.getTypeName())
                .search();

        while (rs.next()) {
            Record record = rs.get();

            IndexedRecord indexedRecord = transducer.read(record);
            assertIndexedRecord(typeDesc, indexedRecord);
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
