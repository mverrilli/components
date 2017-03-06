package org.talend.components.netsuite;

import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.client.NetSuiteClientFactory;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public abstract class AbstractNetSuiteRuntime implements NetSuiteRuntime {
    protected NetSuiteClientFactory clientFactory;

    @Override
    public NetSuiteDataSetRuntime getDataSet(NetSuiteConnectionProperties properties) {
        try {
            NetSuiteEndpoint endpoint = getEndpoint(properties);
            return new NetSuiteDataSetRuntimeImpl(endpoint.connect());
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public ValidationResult validateConnection(NetSuiteConnectionProperties properties) {
        try {
            NetSuiteEndpoint endpoint = getEndpoint(properties);
            endpoint.connect();
            return ValidationResult.OK;
        } catch (NetSuiteException e) {
            ValidationResult result = new ValidationResult();
            result.setStatus(ValidationResult.Result.ERROR);
            result.setMessage(e.getMessage());
            return result;
        }
    }

    protected NetSuiteEndpoint getEndpoint(NetSuiteConnectionProperties properties) throws NetSuiteException {
        NetSuiteEndpoint endpoint = new NetSuiteEndpoint(clientFactory, NetSuiteEndpoint.createConnectionConfig(properties));
        return endpoint;
    }
}
