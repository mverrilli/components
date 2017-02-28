package org.talend.components.netsuite.test;

import java.util.Arrays;
import java.util.Properties;

import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteCredentials;
import org.talend.components.netsuite.test.TestFixture;
import org.talend.components.netsuite.test.TestUtils;

import static org.talend.components.netsuite.client.NetSuiteClientService.MESSAGE_LOGGING_ENABLED_PROPERTY_NAME;

/**
 *
 */
public class NetSuiteWebServiceTestFixture implements TestFixture {

    protected Properties properties;
    protected NetSuiteCredentials credentials;
    protected NetSuiteClientService clientService;

    @Override
    public void setUp() throws Exception {
        System.setProperty("com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");

        properties = TestUtils.loadProperties(System.getProperties(), Arrays.asList(
                "netsuite.endpoint.url",
                "netsuite.endpoint.2016_2.url",
                "netsuite.endpoint.2014_2.url",
                "netsuite.email", "netsuite.password",
                "netsuite.account", "netsuite.roleId",
                "netsuite.applicationId"
        ));

        credentials = NetSuiteCredentials.loadFromProperties(properties, "netsuite.");

        clientService = NetSuiteClientService.create("2016.2");
        clientService.setEndpointUrl(getEndpointUrl());
        clientService.setCredentials(credentials);

        boolean messageLoggingEnabled = Boolean.valueOf(
                System.getProperty(MESSAGE_LOGGING_ENABLED_PROPERTY_NAME, "false"));
        clientService.setMessageLoggingEnabled(messageLoggingEnabled);
    }

    @Override
    public void tearDown() throws Exception {

    }

    public Properties getProperties() {
        return properties;
    }

    public NetSuiteCredentials getCredentials() {
        return credentials;
    }

    public String getEndpointUrl() {
        return properties.getProperty("netsuite.endpoint.2016_2.url",
                properties.getProperty("netsuite.endpoint.url"));
    }

    public NetSuiteClientService getClientService() {
        return clientService;
    }
}
