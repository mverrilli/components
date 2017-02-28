package org.talend.components.netsuite.input;

import java.util.EnumSet;
import java.util.Set;

import org.talend.components.api.component.ConnectorTopology;
import org.talend.components.api.component.runtime.ExecutionEngine;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.netsuite.connection.NetSuiteConnectionDefinition;
import org.talend.components.netsuite.NetSuiteComponentDefinition;
import org.talend.components.netsuite.NetSuiteModuleProperties;
import org.talend.daikon.runtime.RuntimeInfo;

/**
 *
 */
public class NetSuiteInputDefinition extends NetSuiteComponentDefinition {

    public static final String COMPONENT_NAME = "tNetSuiteInput_DEV"; //$NON-NLS-1$

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
        assertEngineCompatibility(engine);
        if (connectorTopology != null && connectorTopology != ConnectorTopology.NONE) {
            assertConnectorTopologyCompatibility(connectorTopology);
            return getRuntimeInfo(NetSuiteConnectionDefinition.SOURCE_CLASS);
        }
        return null;
    }

    @Override
    public Set<ConnectorTopology> getSupportedConnectorTopologies() {
        return EnumSet.of(ConnectorTopology.OUTGOING);
    }

    public Class<? extends ComponentProperties>[] getNestedCompatibleComponentPropertiesClass() {
        return concatPropertiesClasses(super.getNestedCompatibleComponentPropertiesClass(),
                new Class[] { NetSuiteModuleProperties.class });
    }

    @Override
    public Class<? extends ComponentProperties> getPropertyClass() {
        return NetSuiteInputProperties.class;
    }

}
