package org.talend.components.netsuite;

import java.io.IOException;
import java.util.List;

import org.apache.avro.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.api.component.runtime.SourceOrSink;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.components.netsuite.runtime.SchemaService;
import org.talend.components.netsuite.runtime.SchemaServiceImpl;
import org.talend.daikon.NamedThing;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public class NetSuiteSourceOrSink implements SourceOrSink {

    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    protected NetSuiteProvideConnectionProperties properties;

    protected transient NetSuiteEndpoint endpoint;

    @Override
    public ValidationResult initialize(RuntimeContainer container, ComponentProperties properties) {
        this.properties = (NetSuiteProvideConnectionProperties) properties;
        this.endpoint = new NetSuiteEndpoint(getConnectionProperties());
        return ValidationResult.OK;
    }

    @Override
    public ValidationResult validate(RuntimeContainer container) {
        ValidationResult result = new ValidationResult();
        try {
            connect(container);
        } catch (NetSuiteException e) {
            throw new ComponentException(exceptionToValidationResult(e));
        }
        return result;
    }

    @Override
    public List<NamedThing> getSchemaNames(RuntimeContainer container) throws IOException {
        try {
            SchemaService schemaService = new SchemaServiceImpl(endpoint.getConnection());
            return schemaService.getSchemaNames();
        } catch (NetSuiteException e) {
            throw new IOException(e);
        }
    }

    @Override
    public Schema getEndpointSchema(RuntimeContainer container, String schemaName) throws IOException {
        try {
            SchemaService schemaService = new SchemaServiceImpl(endpoint.getConnection());
            return schemaService.getSchema(schemaName);
        } catch (NetSuiteException e) {
            throw new IOException(e);
        }
    }

    public NetSuiteConnectionProperties getConnectionProperties() {
    return properties.getConnectionProperties();
}

    public NetSuiteProvideConnectionProperties getProperties() {
        return properties;
    }

    public NetSuiteClientService connect(RuntimeContainer container) throws NetSuiteException {
        return endpoint.connect();
    }

    public NetSuiteClientService getConnection() throws NetSuiteException {
        return endpoint.getConnection();
    }

    protected static ValidationResult exceptionToValidationResult(Exception ex) {
        ValidationResult vr = new ValidationResult();
        // FIXME - do a better job here
        vr.setMessage(ex.getMessage());
        vr.setStatus(ValidationResult.Result.ERROR);
        return vr;
    }

}
