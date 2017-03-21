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

package org.talend.components.netsuite.connection;

import org.junit.Test;
import org.talend.daikon.properties.presentation.Form;

import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class NetSuiteConnectionPropertiesTest {

    @Test
    public void testSetup() {
        NetSuiteConnectionProperties properties = new NetSuiteConnectionProperties("test");
        properties.init();

        assertNotNull(properties.endpoint.getValue());
        assertNotNull(properties.email.getValue());
        assertNotNull(properties.role.getValue());
        assertNotNull(properties.account.getValue());
        assertNotNull(properties.applicationId.getValue());
    }

    @Test
    public void testSetupLayout() {
        NetSuiteConnectionProperties properties = new NetSuiteConnectionProperties("test");
        properties.init();

        Form mainForm = properties.getForm(Form.MAIN);
        assertNotNull(mainForm);

        Form refForm = properties.getForm(Form.REFERENCE);
        assertNotNull(refForm);
    }
}
