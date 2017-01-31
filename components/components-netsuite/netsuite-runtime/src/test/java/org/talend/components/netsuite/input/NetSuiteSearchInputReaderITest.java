package org.talend.components.netsuite.input;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.netsuite.NetSuiteEndpoint;
import org.talend.components.netsuite.NetSuiteSource;
import org.talend.components.netsuite.client.impl.v2016_2.NetSuiteWebServiceTestFixture;

/**
 *
 */
public class NetSuiteSearchInputReaderITest {

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
    public void testInput() throws Exception {
        RuntimeContainer container = mock(RuntimeContainer.class);

        NetSuiteInputProperties properties = new NetSuiteInputProperties("NetSuite");
        properties.init();
        properties.connection.endpoint.setValue(webServiceTestFixture.getEndpointUrl());
        properties.connection.email.setValue(webServiceTestFixture.getCredentials().getEmail());
        properties.connection.password.setValue(webServiceTestFixture.getCredentials().getPassword());
        properties.connection.account.setValue(webServiceTestFixture.getCredentials().getAccount());
        properties.connection.role.setValue(Integer.valueOf(webServiceTestFixture.getCredentials().getRoleId()));
        properties.connection.applicationId.setValue(webServiceTestFixture.getCredentials().getApplicationId());
        properties.module.moduleName.setValue("Account");

        NetSuiteEndpoint endpoint = new NetSuiteEndpoint(properties);
        Schema schema = endpoint.getSchema(properties.module.moduleName.getValue());
        properties.module.main.schema.setValue(schema);

        NetSuiteSource source = new NetSuiteSource();
        source.initialize(container, properties);

        NetSuiteSearchInputReader reader = (NetSuiteSearchInputReader) source.createReader(container);

        boolean started = reader.start();
        assertTrue(started);

        IndexedRecord record = reader.getCurrent();
        assertNotNull(record);

        List<Schema.Field> fields = record.getSchema().getFields();
        for (int i = 0; i < fields.size(); i++) {
            Object value = record.get(i);
            System.out.println(fields.get(i) + ": " + value);
        }
    }

}
