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
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.daikon.NamedThing;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public class NetSuiteSourceOrSink implements SourceOrSink {

    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    protected NetSuiteProvideConnectionProperties properties;

    protected transient NetSuiteEndpointService endpoint;

    @Override
    public ValidationResult initialize(RuntimeContainer container, ComponentProperties properties) {
        this.properties = (NetSuiteProvideConnectionProperties) properties;
        this.endpoint = new NetSuiteEndpointService(getConnectionProperties());
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
        return endpoint.getSchemaNames();
    }

    @Override
    public Schema getEndpointSchema(RuntimeContainer container, String schemaName) throws IOException {
        return endpoint.getSchema(schemaName);
    }

    public NetSuiteConnectionProperties getConnectionProperties() {
    return properties.getConnectionProperties();
}

    public NetSuiteConnection connect(RuntimeContainer container) throws NetSuiteException {
        return endpoint.connect();
    }

    protected static ValidationResult exceptionToValidationResult(Exception ex) {
        ValidationResult vr = new ValidationResult();
        // FIXME - do a better job here
        vr.setMessage(ex.getMessage());
        vr.setStatus(ValidationResult.Result.ERROR);
        return vr;
    }

}
