package org.talend.components.netsuite.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.NetSuiteComponentMockTestFixture;
import org.talend.components.netsuite.NetSuiteMockTestBase;
import org.talend.components.netsuite.SchemaServiceImpl;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.model.FieldDesc;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.client.query.SearchResultSet;

import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.Record;
import com.netsuite.webservices.v2016_2.setup.customization.TransactionBodyCustomField;
import com.netsuite.webservices.v2016_2.setup.customization.types.CustomizationFieldType;

/**
 *
 */
public class NsObjectInputTransducerTest extends NetSuiteMockTestBase {
    private static NetSuiteComponentMockTestFixture mockTestFixture;

    @BeforeClass
    public static void classSetUp() throws Exception {
        mockTestFixture = new NetSuiteComponentMockTestFixture();
        classScopedTestFixtures.add(mockTestFixture);
        setUpClassScopedTestFixtures();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        tearDownClassScopedTestFixtures();
    }

    @Test
    public void testBasic() throws Exception {
        final NetSuitePortType port = mockTestFixture.getPortMock();

        NetSuiteClientService connection = mockTestFixture.getClientService();
        connection.login();

        TransactionBodyCustomField customBodyField1 = new TransactionBodyCustomField();
        customBodyField1.setScriptId("custbody_field1");
        customBodyField1.setInternalId("1001");
        customBodyField1.setBodyOpportunity(true);
        customBodyField1.setFieldType(CustomizationFieldType.CHECK_BOX);

        TransactionBodyCustomField customBodyField2 = new TransactionBodyCustomField();
        customBodyField2.setScriptId("custbody_field2");
        customBodyField2.setInternalId("1002");
        customBodyField2.setBodyOpportunity(true);
        customBodyField2.setFieldType(CustomizationFieldType.FREE_FORM_TEXT);

        TransactionBodyCustomField customBodyField3 = new TransactionBodyCustomField();
        customBodyField3.setScriptId("custbody_field3");
        customBodyField3.setInternalId("1003");
        customBodyField3.setBodyOpportunity(true);
        customBodyField3.setFieldType(CustomizationFieldType.DATETIME);

        TypeDesc typeDesc = connection.getTypeInfo("Opportunity");
        Schema schema = SchemaServiceImpl.inferSchemaForRecord(typeDesc.getTypeName(), typeDesc.getFields());

        NsObjectInputTransducer transducer = new NsObjectInputTransducer(connection, schema);

        SearchResultSet<Record> rs = connection.newSearch()
                .target(typeDesc.getTypeName())
                .search();

        if (!rs.next()) {
            throw new IllegalStateException("Not records");
        }

        TypeDesc customizedTypeDesc = connection.getCustomizedTypeInfo(typeDesc.getTypeName());

        Record record = rs.get();

        IndexedRecord indexedRecord = transducer.read(record);
        System.out.println(indexedRecord);

        Schema recordSchema = indexedRecord.getSchema();
        assertEquals(customizedTypeDesc.getFields().size(), recordSchema.getFields().size());

        for (FieldDesc fieldDesc : customizedTypeDesc.getFields()) {
            String fieldName = fieldDesc.getName();
            Schema.Field field = recordSchema.getField(fieldName);
            assertNotNull(field);

            Object value = indexedRecord.get(field.pos());
        }
    }

    @Test
    public void testIncludeAllFields() throws Exception {
        NetSuiteClientService connection = mockTestFixture.getClientService();
        connection.login();

        TypeDesc typeDesc = connection.getTypeInfo("Opportunity");
        Schema schema = getDynamicSchema();

        NsObjectInputTransducer transducer = new NsObjectInputTransducer(connection, schema);

        SearchResultSet<Record> rs = connection.newSearch()
                .target(typeDesc.getTypeName())
                .search();

        TypeDesc customizedTypeDesc = connection.getCustomizedTypeInfo(typeDesc.getTypeName());

        int count = 0;
        while (count++ < connection.getSearchPageSize() && rs.next()) {
            Record record = rs.get();
            IndexedRecord indexedRecord = transducer.read(record);
            System.out.println(indexedRecord);

            Schema recordSchema = indexedRecord.getSchema();
            assertEquals(customizedTypeDesc.getFields().size(), recordSchema.getFields().size());

            for (FieldDesc fieldDesc : customizedTypeDesc.getFields()) {
                String fieldName = fieldDesc.getName();
                Schema.Field field = recordSchema.getField(fieldName);
                assertNotNull(field);

                Object value = indexedRecord.get(field.pos());
            }
        }
        if (count == 0) {
            throw new IllegalStateException("Not records");
        }
    }

}
