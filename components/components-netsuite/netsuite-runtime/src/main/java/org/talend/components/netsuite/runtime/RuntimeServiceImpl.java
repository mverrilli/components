package org.talend.components.netsuite.runtime;

import org.talend.components.netsuite.NetSuiteEndpoint;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public class RuntimeServiceImpl implements RuntimeService {

    @Override
    public SchemaService getSchemaService(NetSuiteConnectionProperties properties) {
        return new NetSuiteEndpoint(properties);
    }

    @Override
    public ValidationResult validateConnection(NetSuiteConnectionProperties properties) {
        try {
            NetSuiteEndpoint endpoint = new NetSuiteEndpoint(properties);
            endpoint.connect();
            return ValidationResult.OK;
        } catch (NetSuiteException e) {
            ValidationResult result = new ValidationResult();
            result.setStatus(ValidationResult.Result.ERROR);
            result.setMessage(e.getMessage());
            return result;
        }
    }
}
