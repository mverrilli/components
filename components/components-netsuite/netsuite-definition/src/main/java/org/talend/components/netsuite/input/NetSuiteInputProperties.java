package org.talend.components.netsuite.input;

import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;
import org.talend.components.api.properties.ComponentPropertiesImpl;
import org.talend.components.common.SchemaProperties;
import org.talend.components.netsuite.NetSuiteConnectionProperties;
import org.talend.components.netsuite.NetSuiteModuleProperties;
import org.talend.components.netsuite.NetSuiteProvideConnectionProperties;
import org.talend.daikon.properties.presentation.Form;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class NetSuiteInputProperties extends ComponentPropertiesImpl implements NetSuiteProvideConnectionProperties {

    public NetSuiteConnectionProperties connection = new NetSuiteConnectionProperties("connection");

    public NetSuiteModuleProperties module;

    protected transient PropertyPathConnector MAIN_CONNECTOR =
            new PropertyPathConnector(Connector.MAIN_NAME, "module.main");

    public NetSuiteInputProperties(@JsonProperty("name") String name) {
        super(name);
    }

    @Override
    public void setupProperties() {
        super.setupProperties();

        module = new NetSuiteModuleProperties("module");
        module.connection = connection;
    }

    @Override
    public void setupLayout() {
        super.setupLayout();

        Form mainForm = new Form(this, Form.MAIN);
        mainForm.addRow(connection.getForm(Form.REFERENCE));
        mainForm.addRow(module.getForm(Form.REFERENCE));

        Form advancedForm = new Form(this, Form.ADVANCED);
        advancedForm.addRow(connection.getForm(Form.ADVANCED));
    }

    @Override
    public NetSuiteConnectionProperties getConnectionProperties() {
        return connection;
    }

    @Override
    public void refreshLayout(Form form) {
        super.refreshLayout(form);
        for (Form childForm : connection.getForms()) {
            connection.refreshLayout(childForm);
        }
    }

}
