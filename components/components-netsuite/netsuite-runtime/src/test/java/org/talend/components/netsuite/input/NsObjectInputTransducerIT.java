package org.talend.components.netsuite.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.NetSuiteTestBase;
import org.talend.components.netsuite.SchemaServiceImpl;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.test.NetSuiteWebServiceTestFixture;
import org.talend.components.netsuite.client.model.FieldDesc;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.client.query.SearchResultSet;

import com.netsuite.webservices.v2016_2.platform.core.Record;

/**
 *
 */
public class NsObjectInputTransducerIT extends NetSuiteTestBase {
    private static NetSuiteWebServiceTestFixture webServiceTestFixture;

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
    public void testBasic() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();
        connection.login();

        TypeDesc typeDesc = connection.getTypeInfo("Opportunity");
        Schema schema = SchemaServiceImpl.inferSchemaForType(typeDesc.getTypeName(), typeDesc.getFields());

        NsObjectInputTransducer transducer = new NsObjectInputTransducer(connection, schema, typeDesc.getTypeName());

        SearchResultSet<Record> rs = connection.newSearch()
                .target(typeDesc.getTypeName())
                .search();

        if (!rs.next()) {
            throw new IllegalStateException("Not records");
        }

        Record record = rs.get();

        IndexedRecord indexedRecord = transducer.read(record);
        System.out.println(indexedRecord);
    }

    @Test
    public void testIncludeAllFields() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        connection.login();

        TypeDesc typeDesc = connection.getTypeInfo("Opportunity");
        Schema schema = getDynamicSchema();

        NsObjectInputTransducer transducer = new NsObjectInputTransducer(connection, schema, typeDesc.getTypeName());

        SearchResultSet<Record> rs = connection.newSearch()
                .target(typeDesc.getTypeName())
                .search();

        TypeDesc customizedTypeDesc = connection.getTypeInfo(typeDesc.getTypeName());

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
