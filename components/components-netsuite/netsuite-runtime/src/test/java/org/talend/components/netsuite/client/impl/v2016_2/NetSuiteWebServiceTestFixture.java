package org.talend.components.netsuite.client.impl.v2016_2;

import java.util.Properties;

import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteFactory;
import org.talend.components.netsuite.client.NetSuiteCredentials;
import org.talend.components.netsuite.test.TestUtils;

/**
 *
 */
public class NetSuiteWebServiceTestFixture<P> {

    protected Properties properties;
    protected NetSuiteCredentials credentials;
    protected NetSuiteConnection<P> connection;

    public void setUp() throws Exception {
        System.setProperty("com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");

        NetSuiteFactory.setMessageLoggingEnabled(true);

        String configurationLocation = System.getProperty(
                "netsuite.configuration.location", "/sandbox.properties");
        properties = TestUtils.loadPropertiesFromLocation(configurationLocation);

        credentials = NetSuiteCredentials.loadFromProperties(properties, "credentials.");

        connection = NetSuiteFactory.getConnection("2016.2");
        connection.setEndpointUrl(getEndpointUrl());
        connection.setCredentials(credentials);
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

    public NetSuiteConnection<P> getConnection() {
        return connection;
    }
}
