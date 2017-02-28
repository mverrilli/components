package org.talend.components.netsuite;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteCredentials;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;

/**
 *
 */
public class NetSuiteEndpoint {

    private ConnectionConfig connectionConfig;
    private NetSuiteClientService clientService;

    public NetSuiteEndpoint(ConnectionConfig connectionConfig) {
        this.connectionConfig = connectionConfig;
    }

    public static ConnectionConfig createConnectionConfig(
            NetSuiteConnectionProperties properties) throws NetSuiteException {

        NetSuiteConnectionProperties connProps = properties.getConnectionProperties();

        if (StringUtils.isEmpty(connProps.endpoint.getValue())) {
            throw new NetSuiteException("Invalid endpoint URL");
        }
        if (StringUtils.isEmpty(connProps.email.getValue())) {
            throw new NetSuiteException("Invalid email");
        }
        if (StringUtils.isEmpty(connProps.password.getValue())) {
            throw new NetSuiteException("Invalid password");
        }
        if (StringUtils.isEmpty(connProps.account.getValue())) {
            throw new NetSuiteException("Invalid account");
        }
        //        if (StringUtils.isEmpty(connProps.applicationId.getValue())) {
        //            throw new NetSuiteException("Invalid application ID");
        //        }

        String endpointUrl = StringUtils.strip(connProps.endpoint.getStringValue(), "\"");
        String apiVersion = StringUtils.strip(connProps.apiVersion.getStringValue(), "\"");
        String email = StringUtils.strip(connProps.email.getStringValue(), "\"");
        String password = StringUtils.strip(connProps.password.getStringValue(), "\"");
        Integer roleId = connProps.role.getValue();
        String account = StringUtils.strip(connProps.account.getStringValue(), "\"");
        String applicationId = StringUtils.strip(connProps.applicationId.getStringValue(), "\"");

        NetSuiteCredentials credentials = new NetSuiteCredentials();
        credentials.setEmail(email);
        credentials.setPassword(password);
        credentials.setRoleId(roleId.toString());
        credentials.setAccount(account);
        credentials.setApplicationId(applicationId);

        try {
            return new ConnectionConfig("2016.2", new URL(endpointUrl), credentials);
        } catch (MalformedURLException e) {
            throw new NetSuiteException("Invalid endpoint URL: " + endpointUrl);
        }
    }

    public NetSuiteClientService connect() throws NetSuiteException {
        clientService = connect(connectionConfig);

        return clientService;
    }

    public NetSuiteClientService getClientService() throws NetSuiteException {
        if (clientService == null) {
            clientService = connect();
        }
        return clientService;
    }

    protected NetSuiteClientService connect(ConnectionConfig connectionConfig)
            throws NetSuiteException {

        NetSuiteClientService clientService = NetSuiteClientService.create(connectionConfig.getApiVersion());
        clientService.setEndpointUrl(connectionConfig.getEndpointUrl().toString());
        clientService.setCredentials(connectionConfig.getCredentials());

        clientService.login();

        return clientService;
    }

    public static class ConnectionConfig {
        private String apiVersion;
        private URL endpointUrl;
        private NetSuiteCredentials credentials;

        public ConnectionConfig() {
        }

        public ConnectionConfig(String apiVersion, URL endpointUrl, NetSuiteCredentials credentials) {
            this.apiVersion = apiVersion;
            this.endpointUrl = endpointUrl;
            this.credentials = credentials;
        }

        public String getApiVersion() {
            return apiVersion;
        }

        public void setApiVersion(String apiVersion) {
            this.apiVersion = apiVersion;
        }

        public URL getEndpointUrl() {
            return endpointUrl;
        }

        public void setEndpointUrl(URL endpointUrl) {
            this.endpointUrl = endpointUrl;
        }

        public NetSuiteCredentials getCredentials() {
            return credentials;
        }

        public void setCredentials(NetSuiteCredentials credentials) {
            this.credentials = credentials;
        }
    }
}
