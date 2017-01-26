package org.talend.components.netsuite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.api.component.runtime.SourceOrSink;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteCredentials;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.daikon.NamedThing;
import org.talend.daikon.SimpleNamedThing;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.runtime.RuntimeInfo;
import org.talend.daikon.runtime.RuntimeUtil;
import org.talend.daikon.sandbox.SandboxedInstance;

/**
 *
 */
public class NetSuiteSourceOrSink implements SourceOrSink {

    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    protected static final String API_VERSION = "2016.2";

    protected NetSuiteProvideConnectionProperties properties;

    @Override
    public ValidationResult initialize(RuntimeContainer container, ComponentProperties properties) {
        this.properties = (NetSuiteProvideConnectionProperties) properties;
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
            NetSuiteConnection conn = connect(container);
            NetSuiteMetaData metaData = conn.getMetaData();
            List<NamedThing> schemaNames = new ArrayList<>();
            for (String typeName : metaData.getTransactionTypes()) {
                schemaNames.add(new SimpleNamedThing(typeName));
            }
            return schemaNames;
        } catch (NetSuiteException e) {
            throw new IOException(e);
        }
    }

    public static List<NamedThing> getSchemaNames(RuntimeContainer container,
            NetSuiteProvideConnectionProperties properties) throws IOException {
        ClassLoader classLoader = NetSuiteDefinition.class.getClassLoader();
        RuntimeInfo runtimeInfo = NetSuiteDefinition.getCommonRuntimeInfo(classLoader, NetSuiteSourceOrSink.class);
        try (SandboxedInstance sandboxedInstance = RuntimeUtil.createRuntimeClassWithCurrentJVMProperties(runtimeInfo,
                classLoader)) {
            NetSuiteSourceOrSink ss = (NetSuiteSourceOrSink) sandboxedInstance.getInstance();
            ss.initialize(null, (ComponentProperties) properties);
            try {
                ss.connect(container);
                return ss.getSchemaNames(container);
            } catch (Exception ex) {
                throw new ComponentException(exceptionToValidationResult(ex));
            }
        }
    }

    public static Schema getSchema(RuntimeContainer container,
            NetSuiteProvideConnectionProperties properties, String module) throws IOException {
        ClassLoader classLoader = NetSuiteDefinition.class.getClassLoader();
        RuntimeInfo runtimeInfo = NetSuiteDefinition.getCommonRuntimeInfo(classLoader, NetSuiteSourceOrSink.class);
        try (SandboxedInstance sandboxedInstance = RuntimeUtil.createRuntimeClassWithCurrentJVMProperties(runtimeInfo,
                classLoader)) {
            NetSuiteSourceOrSink ss = (NetSuiteSourceOrSink) sandboxedInstance.getInstance();
            ss.initialize(null, (ComponentProperties) properties);
            try {
                NetSuiteConnection conn = ss.connect(container);
                return ss.getSchema(conn, module);
            } catch (NetSuiteException ex) {
                throw new ComponentException(exceptionToValidationResult(ex));
            }
        }
    }

    @Override
    public Schema getEndpointSchema(RuntimeContainer container, String schemaName) throws IOException {
        try {
            return getSchema(connect(container), schemaName);
        } catch (NetSuiteException e) {
            throw new IOException(e);
        }
    }

    protected Schema getSchema(NetSuiteConnection conn, String module) throws NetSuiteException {
        return null;
    }

    public NetSuiteConnectionProperties getConnectionProperties() {
    return properties.getConnectionProperties();
}

    protected NetSuiteConnection connect(RuntimeContainer container) throws NetSuiteException {
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

        String endpointUrl = StringUtils.strip(connProps.endpoint.getStringValue(), "\"");
        String email = StringUtils.strip(connProps.email.getStringValue(), "\"");
        String password = StringUtils.strip(connProps.password.getStringValue(), "\"");
        Integer roleId = connProps.role.getValue();
        String account = StringUtils.strip(connProps.account.getStringValue(), "\"");

        NetSuiteCredentials credentials = new NetSuiteCredentials(
                email, password, account, Integer.toString(roleId));
        NetSuiteConnection conn = connect(endpointUrl, credentials);
        return conn;
    }

    protected NetSuiteConnection connect(String endpointUrl, NetSuiteCredentials credentials)
            throws NetSuiteException {
        try {
            NetSuiteConnection conn = new NetSuiteConnection();
            conn.setEndpointUrl(new URL(endpointUrl));
            conn.setCredentials(credentials);
            conn.connect();
            return conn;
        } catch (MalformedURLException e) {
            throw new NetSuiteException("Invalid endpoint URL: " + endpointUrl, e);
        }
    }

    protected static ValidationResult exceptionToValidationResult(Exception ex) {
        ValidationResult vr = new ValidationResult();
        // FIXME - do a better job here
        vr.setMessage(ex.getMessage());
        vr.setStatus(ValidationResult.Result.ERROR);
        return vr;
    }

}
