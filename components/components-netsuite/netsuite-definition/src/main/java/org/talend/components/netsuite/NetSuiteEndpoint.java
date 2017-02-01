package org.talend.components.netsuite;

import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.commons.lang3.StringUtils;
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteFactory;
import org.talend.components.netsuite.client.NetSuiteCredentials;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.daikon.NamedThing;
import org.talend.daikon.SimpleNamedThing;

/**
 *
 */
public class NetSuiteEndpoint {

    private NetSuiteProvideConnectionProperties properties;

    public NetSuiteEndpoint(NetSuiteProvideConnectionProperties properties) {
        this.properties = properties;
    }

    public NetSuiteConnection connect() throws NetSuiteException {
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
        if (StringUtils.isEmpty(connProps.applicationId.getValue())) {
            throw new NetSuiteException("Invalid application ID");
        }

        String endpointUrl = StringUtils.strip(connProps.endpoint.getStringValue(), "\"");
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

        NetSuiteConnection conn = connect(endpointUrl, credentials);
        return conn;
    }

    protected NetSuiteConnection connect(String endpointUrl, NetSuiteCredentials credentials)
            throws NetSuiteException {

        NetSuiteConnection conn = NetSuiteFactory.getConnection(NetSuiteConnectionProperties.API_VERSION);
        conn.setEndpointUrl(endpointUrl);
        conn.setCredentials(credentials);
        return conn;
    }

    public List<NamedThing> getSchemaNames() throws NetSuiteException {
        NetSuiteConnection connection = connect();
        NetSuiteMetaData metaData = connection.getMetaData();

        List<NamedThing> schemaNames = new ArrayList<>();
        for (String typeName : metaData.getTransactionTypes()) {
            schemaNames.add(new SimpleNamedThing(typeName));
        }
        return schemaNames;
    }

    public Schema getSchema(String module) throws NetSuiteException {
        NetSuiteConnection connection = connect();

        NetSuiteMetaData metaData = connection.getMetaData();
        NetSuiteMetaData.EntityInfo entityInfo = metaData.getEntity(module);

        Schema schema = NetSuiteSchemaManager.getInstance().inferSchemaForEntity(entityInfo);
        return schema;
    }

    public NetSuiteMetaData getMetaData() throws NetSuiteException {
        return NetSuiteFactory.getMetaData(NetSuiteConnectionProperties.API_VERSION);
    }
}
