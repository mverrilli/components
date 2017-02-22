package org.talend.components.netsuite.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.netsuite.NetSuiteSource;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteWebServiceTestFixture;
import org.talend.components.netsuite.client.model.TypeInfo;
import org.talend.components.netsuite.client.query.SearchCondition;
import org.talend.components.netsuite.client.query.SearchResultSet;
import org.talend.components.netsuite.runtime.RuntimeServiceImpl;
import org.talend.components.netsuite.runtime.SchemaService;
import org.talend.components.netsuite.runtime.SchemaServiceImpl;

import com.netsuite.webservices.v2016_2.lists.accounting.types.AccountType;
import com.netsuite.webservices.v2016_2.platform.core.Record;

/**
 *
 */
public class NsRecordReadTransducerIT {

    private static NetSuiteWebServiceTestFixture webServiceTestFixture;

    @BeforeClass
    public static void classSetUp() throws Exception {
        webServiceTestFixture = new NetSuiteWebServiceTestFixture();
        webServiceTestFixture.setUp();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        if (webServiceTestFixture != null) {
            webServiceTestFixture.tearDown();
        }
    }

    @Test
    public void testBasic() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        connection.login();

        TypeInfo typeInfo = connection.getTypeInfo("Opportunity");
        Schema schema = SchemaServiceImpl.inferSchemaForRecord(typeInfo.getTypeName(), typeInfo.getFields());

        NsRecordReadTransducer transducer = new NsRecordReadTransducer(connection, schema);

        SearchResultSet<Record> rs = connection.newSearch()
                .target(typeInfo.getTypeName())
                .search();

        if (!rs.next()) {
            throw new IllegalStateException("Not records");
        }

        Record record = rs.get();

        IndexedRecord indexedRecord = transducer.read(record);
        System.out.println(indexedRecord);
    }

    private static Schema.Field getFieldByName(List<Schema.Field> fields, String name) {
        for (Schema.Field field : fields) {
            if (field.name().equals(name)) {
                return field;
            }
        }
        return null;
    }

}
