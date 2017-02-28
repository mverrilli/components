package org.talend.components.netsuite;

import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public class NetSuiteRuntimeImpl implements NetSuiteRuntime {

    @Override
    public SchemaService getSchemaService(NetSuiteConnectionProperties properties) {
        try {
            NetSuiteEndpoint endpoint = getEndpoint(properties);
            return new SchemaServiceImpl(endpoint.connect());
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
        System.setProperty("com.sun.xml.bind.v2.runtime.JAXBContextImpl.fastBoot", "true");
        System.setProperty("com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");

        NetSuiteEndpoint endpoint = new NetSuiteEndpoint(NetSuiteEndpoint.createConnectionConfig(properties));
        return endpoint;
    }
}
