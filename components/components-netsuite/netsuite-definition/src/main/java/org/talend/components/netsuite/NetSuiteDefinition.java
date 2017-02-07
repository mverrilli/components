package org.talend.components.netsuite;

import org.talend.components.api.component.AbstractComponentDefinition;
import org.talend.components.api.component.runtime.DependenciesReader;
import org.talend.components.api.component.runtime.ExecutionEngine;
import org.talend.components.api.component.runtime.JarRuntimeInfo;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.daikon.java8.Function;
import org.talend.daikon.properties.property.Property;
import org.talend.daikon.runtime.RuntimeInfo;
import org.talend.daikon.runtime.RuntimeUtil;
import org.talend.daikon.sandbox.SandboxedInstance;

/**
 *
 */
public abstract class NetSuiteDefinition extends AbstractComponentDefinition {

    protected NetSuiteDefinition(String componentName, ExecutionEngine engine1, ExecutionEngine... engines) {
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

    public static RuntimeInfo getRuntimeInfo(String className) {
        return new JarRuntimeInfo("mvn:org.talend.components/netsuite-runtime",
                DependenciesReader.computeDependenciesFilePath(
                        "org.talend.components", "netsuite-runtime"), className);
    }

    public static <R> R withMetaDataService(Function<NetSuiteMetaDataService, R> func,
            NetSuiteProvideConnectionProperties properties) {

        RuntimeInfo runtimeInfo = getRuntimeInfo(
                "org.talend.components.netsuite.NetSuiteMetaDataProviderImpl");

        try (SandboxedInstance sandboxI = RuntimeUtil.createRuntimeClassWithCurrentJVMProperties(runtimeInfo,
                NetSuiteDefinition.class.getClassLoader())) {
            NetSuiteMetaDataServiceProvider provider = (NetSuiteMetaDataServiceProvider) sandboxI.getInstance();
            NetSuiteMetaDataService service = provider.getMetaDataService(properties.getConnectionProperties());
            return func.apply(service);
        }
    }
}
