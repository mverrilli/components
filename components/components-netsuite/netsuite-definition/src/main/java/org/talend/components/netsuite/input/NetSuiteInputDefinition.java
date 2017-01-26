package org.talend.components.netsuite.input;

import java.util.EnumSet;
import java.util.Set;

import org.talend.components.api.component.ConnectorTopology;
import org.talend.components.api.component.runtime.ExecutionEngine;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.netsuite.NetSuiteDefinition;
import org.talend.daikon.runtime.RuntimeInfo;

/**
 *
 */
public class NetSuiteInputDefinition extends NetSuiteDefinition {

    public static final String COMPONENT_NAME = "tNetSuiteInput_POC"; //$NON-NLS-1$

    public NetSuiteInputDefinition() {
        super(COMPONENT_NAME, ExecutionEngine.DI, ExecutionEngine.BEAM);
    }

    @Override
    public boolean isStartable() {
        return true;
    }

    @Override
    public RuntimeInfo getRuntimeInfo(ExecutionEngine engine, ComponentProperties properties,
            ConnectorTopology connectorTopology) {
        if (connectorTopology == ConnectorTopology.OUTGOING) {
            return getCommonRuntimeInfo(this.getClass().getClassLoader(),
                    "org.talend.components.netsuite.input.NetSuiteSource");
        } else {
            return null;
        }
    }

    @Override
    public Set<ConnectorTopology> getSupportedConnectorTopologies() {
        return EnumSet.of(ConnectorTopology.OUTGOING);
    }

    @Override
    public Class<? extends ComponentProperties> getPropertyClass() {
        return NetSuiteInputProperties.class;
    }
}
