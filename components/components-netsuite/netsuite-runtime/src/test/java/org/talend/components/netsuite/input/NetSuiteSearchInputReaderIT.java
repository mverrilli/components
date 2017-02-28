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
import org.talend.components.netsuite.NetSuiteTestBase;
import org.talend.components.netsuite.NetSuiteRuntimeImpl;
import org.talend.components.netsuite.SchemaService;
import org.talend.components.netsuite.test.NetSuiteWebServiceTestFixture;

import com.netsuite.webservices.v2016_2.lists.accounting.types.AccountType;

/**
 *
 */
public class NetSuiteSearchInputReaderIT extends NetSuiteTestBase {
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
    public void testInput() throws Exception {
        RuntimeContainer container = mock(RuntimeContainer.class);

        NetSuiteInputProperties properties = new NetSuiteInputProperties("test");
        properties.init();
        properties.connection.endpoint.setValue(webServiceTestFixture.getEndpointUrl());
        properties.connection.email.setValue(webServiceTestFixture.getCredentials().getEmail());
        properties.connection.password.setValue(webServiceTestFixture.getCredentials().getPassword());
        properties.connection.account.setValue(webServiceTestFixture.getCredentials().getAccount());
        properties.connection.role.setValue(Integer.valueOf(webServiceTestFixture.getCredentials().getRoleId()));
        properties.connection.applicationId.setValue(webServiceTestFixture.getCredentials().getApplicationId());
        properties.module.moduleName.setValue("Account");

        SchemaService schemaService = new NetSuiteRuntimeImpl()
                .getSchemaService(properties.getConnectionProperties());
        Schema schema = schemaService.getSchema(properties.module.moduleName.getValue());
        properties.module.main.schema.setValue(schema);

        properties.module.afterModuleName();
        properties.searchConditionTable.field.setValue(Arrays.asList("Type"));
        properties.searchConditionTable.operator.setValue(Arrays.asList("List.anyOf"));
        properties.searchConditionTable.value1.setValue(Arrays.asList("Bank"));
        properties.searchConditionTable.value2.setValue(Arrays.asList((String) null));

        NetSuiteSource source = new NetSuiteSource();
        source.initialize(container, properties);

        NetSuiteSearchInputReader reader = (NetSuiteSearchInputReader) source.createReader(container);

        boolean started = reader.start();
        assertTrue(started);

        IndexedRecord record = reader.getCurrent();
        assertNotNull(record);

        List<Schema.Field> fields = record.getSchema().getFields();
        for (int i = 0; i < fields.size(); i++) {
            Schema.Field typeField = getFieldByName(fields, "AcctType");
            Object value = record.get(typeField.pos());
            assertNotNull(value);
            assertEquals(AccountType.BANK.value(), value);

            System.out.println(fields.get(i) + ": " + record.get(i));
        }
    }

}
