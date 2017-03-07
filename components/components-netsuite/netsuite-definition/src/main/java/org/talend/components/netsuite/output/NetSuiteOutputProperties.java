package org.talend.components.netsuite.output;

import java.util.HashSet;
import java.util.Set;

import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;
import org.talend.components.common.FixedConnectorsComponentProperties;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.components.netsuite.NetSuiteComponentDefinition;
import org.talend.components.netsuite.NetSuiteModuleProperties;
import org.talend.components.netsuite.NetSuiteProvideConnectionProperties;
import org.talend.components.netsuite.schema.NsSchema;
import org.talend.components.netsuite.NetSuiteDatasetRuntime;
import org.talend.daikon.java8.Function;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.property.Property;

import com.fasterxml.jackson.annotation.JsonProperty;

import static org.talend.daikon.properties.property.PropertyFactory.newEnum;

/**
 *
 */
public class NetSuiteOutputProperties extends FixedConnectorsComponentProperties
        implements NetSuiteProvideConnectionProperties {

    public enum OutputAction {
        ADD, UPDATE, UPSERT, DELETE
    }

    public NetSuiteConnectionProperties connection = new NetSuiteConnectionProperties("connection");

    public NetSuiteModuleProperties module;

    public Property<OutputAction> action = newEnum("action", OutputAction.class);

    protected transient PropertyPathConnector MAIN_CONNECTOR =
            new PropertyPathConnector(Connector.MAIN_NAME, "module.main");

    public NetSuiteOutputProperties(@JsonProperty("name") String name) {
        super(name);
    }

    @Override
    public void setupProperties() {
        super.setupProperties();

        module = new NetSuiteModuleProperties("module");
        module.connection = connection;

        action.setValue(OutputAction.ADD);
    }

    @Override
    public void setupLayout() {
        super.setupLayout();

        Form mainForm = new Form(this, Form.MAIN);
        mainForm.addRow(connection.getForm(Form.REFERENCE));
        mainForm.addRow(module.getForm(Form.REFERENCE));
        mainForm.addRow(action);
    }

    @Override
    public NetSuiteConnectionProperties getConnectionProperties() {
        return connection.getConnectionProperties();
    }

    @Override
    public void refreshLayout(Form form) {
        super.refreshLayout(form);

        for (Form childForm : connection.getForms()) {
            connection.refreshLayout(childForm);
        }
        for (Form childForm : module.getForms()) {
            module.refreshLayout(childForm);
        }
    }

    public void afterAction() {
        refreshLayout(getForm(Form.MAIN));
        refreshLayout(getForm(Form.ADVANCED));
    }

    protected NsSchema getSchemaForUpdate(final String typeName) {
        return NetSuiteComponentDefinition.withDataSetRuntime(this, new Function<NetSuiteDatasetRuntime, NsSchema>() {
            @Override public NsSchema apply(NetSuiteDatasetRuntime dataSetRuntime) {
                return dataSetRuntime.getSchemaForSearch(typeName);
            }
        });
    }

    protected NsSchema getSchemaForDelete(final String typeName) {
        return NetSuiteComponentDefinition.withDataSetRuntime(this, new Function<NetSuiteDatasetRuntime, NsSchema>() {
            @Override public NsSchema apply(NetSuiteDatasetRuntime dataSetRuntime) {
                return dataSetRuntime.getSchemaForSearch(typeName);
            }
        });
    }

    @Override
    protected Set<PropertyPathConnector> getAllSchemaPropertiesConnectors(boolean isOutputConnection) {
        HashSet<PropertyPathConnector> connectors = new HashSet<>();
        if (isOutputConnection) {
            connectors.add(MAIN_CONNECTOR);
        }
        return connectors;
    }

}
