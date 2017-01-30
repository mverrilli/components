package org.talend.components.netsuite;

import java.io.IOException;
import java.util.List;

import org.apache.avro.Schema;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.daikon.NamedThing;

/**
 *
 */
public class NetSuiteSchemaProviderImpl implements NetSuiteSchemaProvider {

    public List<NamedThing> getSchemaNames(RuntimeContainer container,
            NetSuiteProvideConnectionProperties properties) throws IOException {
        NetSuiteSourceOrSink ss = new NetSuiteSourceOrSink();
        ss.initialize(container, (ComponentProperties) properties);
        try {
            ss.connect(container);
            return ss.getSchemaNames(container);
        } catch (IOException | NetSuiteException ex) {
            throw new ComponentException(ex);
        }
    }

    public Schema getSchema(RuntimeContainer container,
            NetSuiteProvideConnectionProperties properties, String module) throws IOException {

        NetSuiteSourceOrSink ss = new NetSuiteSourceOrSink();
        ss.initialize(container, (ComponentProperties) properties);
        try {
            NetSuiteConnection conn = ss.connect(container);
            return ss.getSchema(conn, module);
        } catch (NetSuiteException ex) {
            throw new ComponentException(ex);
        }
    }

}
