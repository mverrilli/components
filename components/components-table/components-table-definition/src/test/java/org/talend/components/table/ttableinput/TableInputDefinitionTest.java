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
package org.talend.components.table.ttableinput;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.talend.components.api.component.ComponentDefinition.RETURN_ERROR_MESSAGE_PROP;
import static org.talend.components.api.component.ComponentDefinition.RETURN_TOTAL_RECORD_COUNT_PROP;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.talend.components.api.component.ConnectorTopology;
import org.talend.components.api.component.runtime.ExecutionEngine;
import org.talend.daikon.exception.TalendRuntimeException;
import org.talend.daikon.properties.property.Property;
import org.talend.daikon.runtime.RuntimeInfo;

public class TableInputDefinitionTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void testGetFamilies() {
		TableInputDefinition definition = new TableInputDefinition();
		String[] actual = definition.getFamilies();

		assertThat(Arrays.asList(actual), contains("File/Input"));
	}

	@Test
	public void testGetPropertyClass() {
		TableInputDefinition definition = new TableInputDefinition();
		Class<?> propertyClass = definition.getPropertyClass();
		String canonicalName = propertyClass.getCanonicalName();

		assertThat(canonicalName, equalTo("org.talend.components.table.ttableinput.TableInputProperties"));
	}

	@Test
	public void testGetReturnProperties() {
		TableInputDefinition definition = new TableInputDefinition();
		Property[] returnProperties = definition.getReturnProperties();
		List<Property> propertyList = Arrays.asList(returnProperties);

		assertThat(propertyList, hasSize(2));
		assertTrue(propertyList.contains(RETURN_TOTAL_RECORD_COUNT_PROP));
		assertTrue(propertyList.contains(RETURN_ERROR_MESSAGE_PROP));
	}
	
	@Test
	public void testGetRuntimeInfo() {
		TableInputDefinition definition = new TableInputDefinition();
		RuntimeInfo runtimeInfo = definition.getRuntimeInfo(ExecutionEngine.DI, null, ConnectorTopology.OUTGOING);
		String runtimeClassName = runtimeInfo.getRuntimeClassName();
		assertThat(runtimeClassName, equalTo("org.talend.components.table.runtime.reader.TableInputSource"));
	}

	@Test
	public void testGetRuntimeInfoWrongEngine() {
		TableInputDefinition definition = new TableInputDefinition();
		thrown.expect(TalendRuntimeException.class);
		thrown.expectMessage("WRONG_EXECUTION_ENGINE:{component=TableInput, requested=DI_SPARK_STREAMING, available=[DI, BEAM]}");
		definition.getRuntimeInfo(ExecutionEngine.DI_SPARK_STREAMING, null, ConnectorTopology.OUTGOING);
	}

	@Test
	public void testGetRuntimeInfoWrongTopology() {
		TableInputDefinition definition = new TableInputDefinition();
		thrown.expect(TalendRuntimeException.class);
		thrown.expectMessage("WRONG_CONNECTOR:{component=TableInput}");
		definition.getRuntimeInfo(ExecutionEngine.DI, null, ConnectorTopology.INCOMING);
	}
	
	@Test
	public void testGetSupportedConnectorTopologies() {
		TableInputDefinition definition = new TableInputDefinition();
		Set<ConnectorTopology> connectorTopologies = definition.getSupportedConnectorTopologies();
		
		assertThat(connectorTopologies, contains(ConnectorTopology.OUTGOING));
		assertThat(connectorTopologies, not((contains(ConnectorTopology.INCOMING,ConnectorTopology.NONE,ConnectorTopology.INCOMING_AND_OUTGOING))));
	}
	
}