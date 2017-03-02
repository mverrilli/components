#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
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
package ${package}.${componentPackage};

import java.util.EnumSet;
import java.util.Set;

import org.talend.components.api.component.AbstractComponentDefinition;
import org.talend.components.api.component.ConnectorTopology;
import org.talend.components.api.component.runtime.DependenciesReader;
import org.talend.components.api.component.runtime.ExecutionEngine;
import org.talend.components.api.component.runtime.SimpleRuntimeInfo;
import org.talend.components.api.properties.ComponentProperties;
import ${package}.runtime.reader.${componentName}Source;
import org.talend.daikon.properties.property.Property;
import org.talend.daikon.runtime.RuntimeInfo;

/**
 * The ${componentName}Definition acts as an entry point for all of services that 
 * a component provides to integrate with the Runtime Platform (at design-time) and other 
 * components (at run-time).
 */
public class ${componentName}Definition extends AbstractComponentDefinition {
    
    public static final String COMPONENT_NAME = "${componentName}"; //$NON-NLS-1$

    public ${componentName}Definition() {
        super(COMPONENT_NAME, ExecutionEngine.DI, ExecutionEngine.BEAM);
    }

    @Override
    public String[] getFamilies() {
        return new String[] { "File/Input" }; //$NON-NLS-1$
    }

    /**
     * Defines a list of Return Properties (a.k.a After Properties). 
     * These properties collect different metrics and information during component execution.
     * Values of these properties are returned after component finished his work.
     * Runtime Platform may use this method to retrieve a this list and show in UI
     * Here, it is defined 2 properties: <br>
     * 1) Error message
     * 2) Number of records processed
     * For Error message property no efforts are required from component developer to set its value. 
     * Runtime Platform will set its value by itself in case of Exception in runtime.
     * As for Number of records property see Reader implementation in runtime part
     */
	@Override
	public Property[] getReturnProperties() {
		return new Property[] { RETURN_TOTAL_RECORD_COUNT_PROP, RETURN_ERROR_MESSAGE_PROP };
	}
    
    @Override
    public Class<? extends ComponentProperties> getPropertyClass() {
        return ${componentName}Properties.class;
    }
    
    @Override
    public RuntimeInfo getRuntimeInfo(ExecutionEngine engine, ComponentProperties properties, ConnectorTopology connectorTopology) {
        assertEngineCompatibility(engine);
        assertConnectorTopologyCompatibility(connectorTopology);
        return new SimpleRuntimeInfo(this.getClass().getClassLoader(), DependenciesReader.computeDependenciesFilePath("${groupId}", "${artifactId}"), ${componentName}Source.class.getCanonicalName());
    }

    @Override
    public Set<ConnectorTopology> getSupportedConnectorTopologies() {
        return EnumSet.of(ConnectorTopology.OUTGOING);
    } 
    
}
