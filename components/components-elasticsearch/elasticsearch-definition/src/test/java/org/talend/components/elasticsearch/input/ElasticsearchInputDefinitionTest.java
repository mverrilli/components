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

package org.talend.components.elasticsearch.input;

import org.junit.Ignore;
import org.junit.Test;
import org.talend.components.api.component.ConnectorTopology;
import org.talend.components.api.component.runtime.ExecutionEngine;
import org.talend.daikon.runtime.RuntimeInfo;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElasticsearchInputDefinitionTest {
    private final ElasticsearchInputDefinition inputDefinition = new ElasticsearchInputDefinition();

    /**
     * Check {@link ElasticsearchInputDefinition#getRuntimeInfo(ComponentProperties, ConnectorTopology)} returns RuntimeInfo,
     * which runtime class name is "org.talend.components.elasticsearch.runtime_2_4.ElasticsearchInputRuntime"
     */
    @Test
    @Ignore("This can't work unless the runtime jar is already installed in maven!")
    public void testRuntimeInfo() {
        RuntimeInfo runtimeInfo = inputDefinition.getRuntimeInfo(ExecutionEngine.BEAM, null, null);
        assertEquals("org.talend.components.elasticsearch.runtime_2_4.ElasticsearchInputRuntime", runtimeInfo.getRuntimeClassName());

    }

    /**
     * Check {@link ElasticsearchInputDefinition#getPropertyClass()} returns class, which canonical name is
     * "org.talend.components.elasticsearch.Elasticsearch.input.ElasticsearchInputProperties"
     */
    @Test
    public void testGetPropertyClass() {
        Class<?> propertyClass = inputDefinition.getPropertyClass();
        String canonicalName = propertyClass.getCanonicalName();
        assertThat(canonicalName, equalTo("org.talend.components.elasticsearch.input.ElasticsearchInputProperties"));
    }

    /**
     * Check {@link ElasticsearchInputDefinition} returns "ElasticsearchInput"
     */
    @Test
    public void testGetName(){
        String componentName = inputDefinition.getName();
        assertEquals(componentName, "ElasticsearchInput");
    }

    /**
     * Check {@link ElasticsearchInputDefinition#getSupportedConnectorTopologies()} returns ConnectorTopology.OUTGOING
     */
    @Test
    public void testGetSupportedConnectorTopologies(){
        Set<ConnectorTopology> test = inputDefinition.getSupportedConnectorTopologies();
        assertTrue(test.contains(ConnectorTopology.OUTGOING));
    }
}
