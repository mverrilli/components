package org.talend.components.netsuite;

import org.talend.components.api.component.AbstractComponentDefinition;
import org.talend.components.api.component.runtime.DependenciesReader;
import org.talend.components.api.component.runtime.ExecutionEngine;
import org.talend.components.api.component.runtime.SimpleRuntimeInfo;
import org.talend.components.api.component.runtime.SourceOrSink;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.daikon.properties.property.Property;
import org.talend.daikon.runtime.RuntimeInfo;

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

    public static RuntimeInfo getCommonRuntimeInfo(ClassLoader classLoader, Class<? extends SourceOrSink> clazz) {
        return getCommonRuntimeInfo(classLoader, clazz.getCanonicalName());
    }

    public static RuntimeInfo getCommonRuntimeInfo(ClassLoader classLoader, String className) {
        return new SimpleRuntimeInfo(classLoader, DependenciesReader.computeDependenciesFilePath(
                "org.talend.components", "components-netsuite"), className);
    }
}
