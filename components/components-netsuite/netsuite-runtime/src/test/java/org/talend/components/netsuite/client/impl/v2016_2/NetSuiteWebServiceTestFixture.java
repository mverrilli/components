package org.talend.components.netsuite.client.impl.v2016_2;

import java.util.Properties;

import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteFactory;
import org.talend.components.netsuite.client.NetSuiteCredentials;
import org.talend.components.netsuite.test.TestUtils;

/**
 *
 */
public class NetSuiteWebServiceTestFixture<P> {

    protected Properties properties;
    protected NetSuiteCredentials credentials;
    protected NetSuiteClientService<P> clientService;

    public void setUp() throws Exception {
        System.setProperty("com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");

        String configurationLocation = System.getProperty(
                "netsuite.configuration.location", "/sandbox.properties");
        properties = TestUtils.loadPropertiesFromLocation(configurationLocation);

        credentials = NetSuiteCredentials.loadFromProperties(properties, "credentials.");

        clientService = NetSuiteClientService.getClientService("2016.2");
        clientService.setEndpointUrl(getEndpointUrl());
        clientService.setCredentials(credentials);
        clientService.setMessageLoggingEnabled(true);
    }

    public void tearDown() throws Exception {

    }

    public Properties getProperties() {
        return properties;
    }

    public NetSuiteCredentials getCredentials() {
        return credentials;
    }

    public String getEndpointUrl() {
        return properties.getProperty("endpoint.url");
    }

    public NetSuiteClientService<P> getClientService() {
        return clientService;
    }
}
