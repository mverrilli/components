package org.talend.components.netsuite;

import org.apache.commons.lang3.StringUtils;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteCredentials;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;

/**
 *
 */
public class NetSuiteEndpoint {

    private NetSuiteConnectionProperties properties;
    private NetSuiteClientService connection;

    public NetSuiteEndpoint(NetSuiteConnectionProperties properties) {
        this.properties = properties;
    }

    public NetSuiteClientService connect() throws NetSuiteException {
        NetSuiteConnectionProperties connProps = properties.getConnectionProperties();

        if (StringUtils.isEmpty(connProps.endpoint.getValue())) {
            throw new NetSuiteException("Invalid endpoint URL");
        }
        if (StringUtils.isEmpty(connProps.email.getValue())) {
            throw new NetSuiteException("Invalid email");
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

        connection = connect(endpointUrl, credentials);
        return connection;
    }

    public NetSuiteClientService getConnection() throws NetSuiteException {
        if (connection == null) {
            connect();
        }
        return connection;
    }

    protected NetSuiteClientService connect(String endpointUrl, NetSuiteCredentials credentials)
            throws NetSuiteException {

        NetSuiteClientService clientService = NetSuiteClientService.create("2016.2");
        clientService.setEndpointUrl(endpointUrl);
        clientService.setCredentials(credentials);
        return clientService;
    }

}
