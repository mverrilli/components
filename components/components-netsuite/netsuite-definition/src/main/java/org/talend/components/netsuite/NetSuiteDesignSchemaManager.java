package org.talend.components.netsuite;

import java.io.IOException;
import java.util.List;

import org.apache.avro.Schema;
import org.talend.components.api.exception.ComponentException;
import org.talend.daikon.NamedThing;
import org.talend.daikon.java8.Function;
import org.talend.daikon.runtime.RuntimeInfo;
import org.talend.daikon.runtime.RuntimeUtil;
import org.talend.daikon.sandbox.SandboxedInstance;

/**
 *
 */
public class NetSuiteDesignSchemaManager {

    private static final NetSuiteDesignSchemaManager instance = new NetSuiteDesignSchemaManager();

    public static NetSuiteDesignSchemaManager getInstance() {
        return instance;
    }

    public List<NamedThing> getSchemaNames(
            final NetSuiteProvideConnectionProperties properties) throws IOException {

        return callSchemaProvider(new Function<NetSuiteSchemaProvider, List<NamedThing>>() {
            @Override public List<NamedThing> apply(NetSuiteSchemaProvider schemaProvider) {
                try {
                    return schemaProvider.getSchemaNames(null, properties);
                } catch (IOException e) {
                    throw new ComponentException(e);
                }
            }
        });
    }

    public Schema getSchema(
            final NetSuiteProvideConnectionProperties properties,
            final String module) throws IOException {

        return callSchemaProvider(new Function<NetSuiteSchemaProvider, Schema>() {
            @Override public Schema apply(NetSuiteSchemaProvider schemaProvider) {
                try {
                    return schemaProvider.getSchema(null, properties, module);
                } catch (IOException e) {
                    throw new ComponentException(e);
                }
            }
        });
    }

    protected <R> R callSchemaProvider(Function<NetSuiteSchemaProvider, R> op) throws IOException {
        ClassLoader classLoader = NetSuiteDefinition.class.getClassLoader();
        RuntimeInfo runtimeInfo = NetSuiteDefinition.getCommonRuntimeInfo(classLoader,
                "org.talend.components.netsuite.NetSuiteSchemaProviderImpl");
        try (SandboxedInstance sandboxedInstance = RuntimeUtil.createRuntimeClassWithCurrentJVMProperties(runtimeInfo,
                classLoader)) {
            NetSuiteSchemaProvider dsp = (NetSuiteSchemaProvider) sandboxedInstance.getInstance();
            return op.apply(dsp);
        }
    }

}
