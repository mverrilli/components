package org.talend.components.netsuite;

import org.talend.components.api.component.AbstractComponentDefinition;
import org.talend.components.api.component.runtime.DependenciesReader;
import org.talend.components.api.component.runtime.ExecutionEngine;
import org.talend.components.api.component.runtime.JarRuntimeInfo;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.daikon.java8.Function;
import org.talend.daikon.properties.property.Property;
import org.talend.daikon.runtime.RuntimeInfo;
import org.talend.daikon.runtime.RuntimeUtil;
import org.talend.daikon.sandbox.SandboxedInstance;

/**
 *
 */
public abstract class TNetSuiteComponentDefinition extends AbstractComponentDefinition {

    public static final String MAVEN_GROUP_ID = "org.talend.components";
    public static final String MAVEN_ARTIFACT_ID = "netsuite-runtime";

    public static final String SOURCE_OR_SINK_CLASS =
            "org.talend.components.netsuite.NetSuiteSourceOrSink";
    public static final String SOURCE_CLASS =
            "org.talend.components.netsuite.NetSuiteSource";
    public static final String SINK_CLASS =
            "org.talend.components.netsuite.NetSuiteSink";

    public static final String RUNTIME_CLASS =
            "org.talend.components.netsuite.NetSuiteRuntimeImpl";

    protected TNetSuiteComponentDefinition(String componentName, ExecutionEngine engine1, ExecutionEngine... engines) {
        super(componentName, engine1, engines);
    }

    @Override
    public String[] getFamilies() {
        return new String[] { "Business/NetSuite", "Cloud/NetSuite" };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends ComponentProperties>[] getNestedCompatibleComponentPropertiesClass() {
        return new Class[] { NetSuiteConnectionProperties.class };
    }

    @Override
    // Most of the components are on the input side, so put this here, the output definition will override this
    public Property[] getReturnProperties() {
        return new Property[] { RETURN_ERROR_MESSAGE_PROP, RETURN_TOTAL_RECORD_COUNT_PROP };
    }

    public static <R> R withSchemaService(final Function<SchemaService, R> func,
            final NetSuiteProvideConnectionProperties properties) {
        return withRuntime(new Function<NetSuiteRuntime, R>() {
            @Override public R apply(NetSuiteRuntime netSuiteRuntime) {
                SchemaService schemaService = netSuiteRuntime.getSchemaService(properties.getConnectionProperties());
                return func.apply(schemaService);
            }
        });
    }

    public static <R> R withRuntime(final Function<NetSuiteRuntime, R> func) {
        RuntimeInfo runtimeInfo = getRuntimeInfo(RUNTIME_CLASS);
        try (SandboxedInstance sandboxI = RuntimeUtil.createRuntimeClass(runtimeInfo,
                TNetSuiteComponentDefinition.class.getClassLoader())) {
            NetSuiteRuntime netSuiteRuntime = (NetSuiteRuntime) sandboxI.getInstance();
            return func.apply(netSuiteRuntime);
        }
    }

    public static RuntimeInfo getRuntimeInfo(String runtimeClassName) {
        return new JarRuntimeInfo("mvn:" + MAVEN_GROUP_ID + "/" + MAVEN_ARTIFACT_ID,
                DependenciesReader.computeDependenciesFilePath(MAVEN_GROUP_ID, MAVEN_ARTIFACT_ID),
                runtimeClassName);
    }
}
