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

package org.talend.components.netsuite.output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.talend.components.netsuite.NetSuiteDatasetRuntime;
import org.talend.components.netsuite.NetSuitePropertiesTestBase;
import org.talend.components.netsuite.NetSuiteRuntime;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.presentation.Form;

/**
 *
 */
public class NetSuiteOutputPropertiesTest extends NetSuitePropertiesTestBase {
    private DatasetMockTestFixture datasetMockTestFixture;
    private TestDataset dataset;
    private NetSuiteRuntime runtime;
    private NetSuiteDatasetRuntime datasetRuntime;

    private NetSuiteOutputProperties properties;

    @Before
    public void setUp() throws Exception {
        datasetMockTestFixture = new DatasetMockTestFixture(createTestDataset1());
        datasetMockTestFixture.setUp();
        dataset = datasetMockTestFixture.getTestDataset();
        runtime = datasetMockTestFixture.getRuntime();
        datasetRuntime = datasetMockTestFixture.getDatasetRuntime();

        properties = new NetSuiteOutputProperties("test");

        when(runtime.getDatasetRuntime(eq(properties.connection))).thenReturn(datasetRuntime);
    }

    @After
    public void tearDown() throws Exception {
        datasetMockTestFixture.tearDown();
    }

    @Test
    public void testSetup() {
        properties.init();

        assertEquals(OutputAction.ADD, properties.module.action.getValue());

        assertNotNull(properties.getConnectionProperties());
        assertEquals(1, properties.getAllSchemaPropertiesConnectors(false).size());
        assertEquals(0, properties.getAllSchemaPropertiesConnectors(true).size());
    }

    @Test
    public void testSetupLayout() {
        properties.init();

        Form mainForm = properties.getForm(Form.MAIN);
        assertNotNull(mainForm);
    }

    @Test
    public void testRefreshLayout() {
        properties.init();

        properties.refreshLayout(properties.getForm(Form.MAIN));
    }

    @Test
    public void testSelectTargetForUpdate() throws Exception {
        properties.init();

        // Before module select

        ValidationResult validationResult1 = properties.module.beforeModuleName();

        verify(datasetRuntime, times(1)).getRecordTypes();

        assertNotNull(validationResult1);
        assertEquals(ValidationResult.OK.getStatus(), validationResult1.getStatus());

        assertEquals(dataset.getRecordTypes().size(),
                properties.module.moduleName.getPossibleValues().size());

        // Select module

        properties.module.moduleName.setValue("Account");

        // After module select

        ValidationResult validationResult2 = properties.module.afterModuleName();

        verify(datasetRuntime, times(1)).getSchemaForUpdate(eq("Account"));

        assertNotNull(validationResult2);
        assertEquals(ValidationResult.OK.getStatus(), validationResult2.getStatus());

        // Select action

        properties.module.action.setValue(OutputAction.UPDATE);

        // After action select

        ValidationResult validationResult3 = properties.module.afterAction();

        verify(datasetRuntime, times(2)).getSchemaForUpdate(eq("Account"));

        assertNotNull(validationResult3);
        assertEquals(ValidationResult.OK.getStatus(), validationResult3.getStatus());
    }

    @Test
    public void testSelectTargetForDelete() throws Exception {
        properties.init();

        // Before module select

        ValidationResult validationResult1 = properties.module.beforeModuleName();

        verify(datasetRuntime, times(1)).getRecordTypes();

        assertNotNull(validationResult1);
        assertEquals(ValidationResult.OK.getStatus(), validationResult1.getStatus());

        assertEquals(dataset.getRecordTypes().size(),
                properties.module.moduleName.getPossibleValues().size());

        // Select module

        properties.module.moduleName.setValue("Account");

        // After module select

        ValidationResult validationResult2 = properties.module.afterModuleName();

        verify(datasetRuntime, times(1)).getSchemaForUpdate(eq("Account"));

        assertNotNull(validationResult2);
        assertEquals(ValidationResult.OK.getStatus(), validationResult2.getStatus());

        // Select action

        properties.module.action.setValue(OutputAction.DELETE);

        // After action select

        ValidationResult validationResult3 = properties.module.afterAction();

        verify(datasetRuntime, times(1)).getSchemaForDelete(eq("Account"));

        assertNotNull(validationResult3);
        assertEquals(ValidationResult.OK.getStatus(), validationResult3.getStatus());
    }
}
