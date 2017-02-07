package org.talend.components.netsuite;

import java.util.EnumSet;
import java.util.Set;

import org.talend.components.api.component.ConnectorTopology;
import org.talend.components.api.component.runtime.ExecutionEngine;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.daikon.properties.property.Property;
import org.talend.daikon.runtime.RuntimeInfo;

/**
 *
 */
public class NetSuiteConnectionDefinition extends NetSuiteDefinition {

    public static final String COMPONENT_NAME = "NetSuiteConnection"; //$NON-NLS-1$

    public NetSuiteConnectionDefinition() {
        super(COMPONENT_NAME, ExecutionEngine.DI);
    }

    @Override
    public Class<? extends ComponentProperties> getPropertyClass() {
        return NetSuiteConnectionProperties.class;
    }

    @Override
    public Property[] getReturnProperties() {
        return new Property[] { RETURN_ERROR_MESSAGE_PROP };
    }

    @Override
    public boolean isStartable() {
        return true;
    }

    @Override
    public RuntimeInfo getRuntimeInfo(ExecutionEngine engine, ComponentProperties properties, ConnectorTopology componentType) {
        assertEngineCompatibility(engine);
        if (componentType == ConnectorTopology.NONE) {
            return getRuntimeInfo("org.talend.components.netsuite.NetSuiteSourceOrSink");
        } else {
            return null;
        }
    }

    @Override
    public Set<ConnectorTopology> getSupportedConnectorTopologies() {
        return EnumSet.of(ConnectorTopology.NONE);
    }

}

