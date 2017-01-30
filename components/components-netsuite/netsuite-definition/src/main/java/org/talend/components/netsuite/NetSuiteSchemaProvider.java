package org.talend.components.netsuite;

import java.io.IOException;
import java.util.List;

import org.apache.avro.Schema;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.daikon.NamedThing;

/**
 *
 */
public interface NetSuiteSchemaProvider {

    List<NamedThing> getSchemaNames(RuntimeContainer container,
            NetSuiteProvideConnectionProperties properties) throws IOException;

    Schema getSchema(RuntimeContainer container,
            NetSuiteProvideConnectionProperties properties, String module) throws IOException;
}
