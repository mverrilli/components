// ============================================================================
//
// Copyright (C) 2006-2017 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.components.netsuite;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.talend.components.api.component.AbstractComponentDefinition;
import org.talend.components.api.component.runtime.DependenciesReader;
import org.talend.components.api.component.runtime.ExecutionEngine;
import org.talend.components.api.component.runtime.JarRuntimeInfo;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.daikon.java8.Function;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.property.Property;
import org.talend.daikon.runtime.RuntimeInfo;
import org.talend.daikon.runtime.RuntimeUtil;
import org.talend.daikon.sandbox.SandboxedInstance;

/**
 *
 */
public abstract class NetSuiteComponentDefinition extends AbstractComponentDefinition {

    public static final String MAVEN_GROUP_ID = "org.talend.components";

    public static final String MAVEN_ARTIFACT_ID = "netsuite-runtime_${version}";

    public static final String SOURCE_OR_SINK_CLASS =
            "org.talend.components.netsuite.v${version}.NetSuiteSourceOrSinkImpl";

    public static final String SOURCE_CLASS =
            "org.talend.components.netsuite.v${version}.NetSuiteSourceImpl";

    public static final String SINK_CLASS =
            "org.talend.components.netsuite.v${version}.NetSuiteSinkImpl";

    public static final String RUNTIME_CLASS =
            "org.talend.components.netsuite.v${version}.NetSuiteRuntimeImpl";

    protected static RuntimeInvoker runtimeInvoker = new SandboxRuntimeInvoker();

    protected NetSuiteComponentDefinition(String componentName, ExecutionEngine engine1, ExecutionEngine... engines) {
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

    public static <R> R withDatasetRuntime(final NetSuiteProvideConnectionProperties properties,
            final Function<NetSuiteDatasetRuntime, R> func) {
        return withRuntime(properties, new Function<NetSuiteRuntime, R>() {
            @Override public R apply(NetSuiteRuntime runtime) {
                NetSuiteConnectionProperties connectionProperties = properties.getConnectionProperties();
                NetSuiteDatasetRuntime dataSetRuntime = runtime.getDatasetRuntime(connectionProperties);
                return func.apply(dataSetRuntime);
            }
        });
    }

    public static <R> R withRuntime(final NetSuiteProvideConnectionProperties properties,
            final Function<NetSuiteRuntime, R> func) {

        NetSuiteConnectionProperties connectionProperties = properties.getConnectionProperties();
        return runtimeInvoker.invokeRuntime(connectionProperties.getDesignTimeContext(),
                properties.getConnectionProperties(), func);
    }

    public static RuntimeInfo getRuntimeInfo(final NetSuiteProvideConnectionProperties properties,
            final String runtimeClassName) {

        NetSuiteConnectionProperties connectionProperties = properties.getConnectionProperties();

        String endpointUrl = connectionProperties.endpoint.getStringValue();
        String apiVersion = detectApiVersion(endpointUrl);

        String artifactId = MAVEN_ARTIFACT_ID.replace("${version}", apiVersion);
        String className = runtimeClassName.replace("${version}", apiVersion);

        return new JarRuntimeInfo("mvn:" + MAVEN_GROUP_ID + "/" + artifactId,
                DependenciesReader.computeDependenciesFilePath(MAVEN_GROUP_ID, artifactId),
                className);
    }

    public static RuntimeInvoker getRuntimeInvoker() {
        return runtimeInvoker;
    }

    public static void setRuntimeInvoker(RuntimeInvoker runtimeInvoker) {
        if (runtimeInvoker == null) {
            throw new IllegalArgumentException("Runtime invoker can't be null");
        }
        NetSuiteComponentDefinition.runtimeInvoker = runtimeInvoker;
    }

    public static String detectApiVersion(String nsEndpointUrl) {
        URI uri = URI.create(nsEndpointUrl);
        if (uri.getPath().endsWith("NetSuitePort_2016_2")) {
            return "2016_2";
        }
        if (uri.getPath().endsWith("NetSuitePort_2014_2")) {
            return "2014_2";
        }
        throw new ComponentException(new ValidationResult()
                .setStatus(ValidationResult.Result.ERROR)
                .setMessage("Failed to detect NetSuite API version: " + nsEndpointUrl));
    }

    public static class DesignTimeContext implements NetSuiteRuntime.Context {
        protected Map<String, Object> attributes = new HashMap<>();

        @Override
        public boolean isCachingEnabled() {
            return true;
        }

        @Override
        public Object getAttribute(String key) {
            return attributes.get(key);
        }

        @Override
        public void setAttribute(String key, Object value) {
            attributes.put(key, value);
        }
    }

    public interface RuntimeInvoker {

        <R> R invokeRuntime(NetSuiteRuntime.Context context,
                NetSuiteConnectionProperties properties,
                Function<NetSuiteRuntime, R> func);
    }

    public static class SandboxRuntimeInvoker implements RuntimeInvoker {

        @Override
        public <R> R invokeRuntime(final NetSuiteRuntime.Context context,
                final NetSuiteConnectionProperties properties,
                final Function<NetSuiteRuntime, R> func) {

            RuntimeInfo runtimeInfo = getRuntimeInfo(properties, RUNTIME_CLASS);
            try (SandboxedInstance sandboxI = RuntimeUtil.createRuntimeClass(runtimeInfo,
                    NetSuiteComponentDefinition.class.getClassLoader())) {
                NetSuiteRuntime runtime = (NetSuiteRuntime) sandboxI.getInstance();
                runtime.setContext(context);
                return func.apply(runtime);
            }
        }
    }
}
