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

    public NetSuiteClientFactory getClientFactory() {
        return clientFactory;
    }

    public void setClientFactory(NetSuiteClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public NetSuiteDatasetRuntime getDatasetRuntime(NetSuiteRuntime.Context context,
            NetSuiteConnectionProperties properties) {
        try {
            NetSuiteEndpoint endpoint = getEndpoint(context, properties);
            return new NetSuiteDatasetRuntimeImpl(endpoint.getClientService());
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public ValidationResult validateConnection(NetSuiteRuntime.Context context,
            NetSuiteConnectionProperties properties) {
        try {
            NetSuiteEndpoint endpoint = getEndpoint(context, properties);
            endpoint.connect();
            return ValidationResult.OK;
        } catch (NetSuiteException e) {
            ValidationResult result = new ValidationResult();
            result.setStatus(ValidationResult.Result.ERROR);
            result.setMessage(e.getMessage());
            return result;
        }
    }

    protected NetSuiteEndpoint getEndpoint(final NetSuiteRuntime.Context context,
            final NetSuiteConnectionProperties properties) throws NetSuiteException {

        NetSuiteEndpoint.ConnectionConfig connectionConfig = NetSuiteEndpoint.createConnectionConfig(properties);

        NetSuiteEndpoint endpoint = null;
        if (context.isCachingEnabled()) {
            NetSuiteEndpoint.ConnectionConfig cachedConnectionConfig =
                    (NetSuiteEndpoint.ConnectionConfig) context.getAttribute(NetSuiteEndpoint.ConnectionConfig.class.getName());
            if (cachedConnectionConfig != null && connectionConfig.equals(cachedConnectionConfig)) {
                endpoint = (NetSuiteEndpoint) context.getAttribute(NetSuiteEndpoint.class.getName());
            }
        }
        if (endpoint == null) {
            endpoint = new NetSuiteEndpoint(clientFactory, NetSuiteEndpoint.createConnectionConfig(properties));
            if (context.isCachingEnabled()) {
                context.setAttribute(NetSuiteEndpoint.class.getName(), endpoint);
                context.setAttribute(NetSuiteEndpoint.ConnectionConfig.class.getName(), endpoint.getConnectionConfig());
            }
        }

        return endpoint;
    }
}
