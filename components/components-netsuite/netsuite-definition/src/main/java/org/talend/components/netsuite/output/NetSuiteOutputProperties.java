package org.talend.components.netsuite.output;

import static org.talend.components.netsuite.util.ComponentExceptions.exceptionToValidationResult;
import static org.talend.daikon.properties.presentation.Widget.widget;
import static org.talend.daikon.properties.property.PropertyFactory.newEnum;

import java.util.HashSet;
import java.util.Set;

import org.apache.avro.Schema;
import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;
import org.talend.components.common.FixedConnectorsComponentProperties;
import org.talend.components.netsuite.NetSuiteComponentDefinition;
import org.talend.components.netsuite.NetSuiteDatasetRuntime;
import org.talend.components.netsuite.NetSuiteModuleProperties;
import org.talend.components.netsuite.NetSuiteProvideConnectionProperties;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.daikon.java8.Function;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;
import org.talend.daikon.properties.property.Property;

import com.fasterxml.jackson.annotation.JsonProperty;

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

        module = new ModuleProperties("module");
        module.connection = connection;

        action.setValue(OutputAction.ADD);
    }

    @Override
    public void setupLayout() {
        super.setupLayout();

        Form mainForm = new Form(this, Form.MAIN);
        mainForm.addRow(connection.getForm(Form.REFERENCE));
        mainForm.addRow(module.getForm(Form.REFERENCE));
        mainForm.addRow(widget(action)
                .setWidgetType(Widget.ENUMERATION_WIDGET_TYPE)
                .setLongRunning(true));
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

    public ValidationResult afterAction() {
        ValidationResult validationResult = new ValidationResult();

        try {
            setupSchema();
            validationResult.setStatus(ValidationResult.Result.OK);
        } catch (Exception e) {
            return exceptionToValidationResult(e);
        }

        refreshLayout(getForm(Form.MAIN));
        refreshLayout(getForm(Form.ADVANCED));

        return validationResult;
    }

    protected void setupSchema() {
        switch (action.getValue()) {
        case ADD:
        case UPDATE:
        case UPSERT:
            setupSchemaForUpdate();
            break;
        case DELETE:
            setupSchemaForDelete();
            break;
        }
    }

    protected void setupSchemaForUpdate() {
        Schema schema = getSchemaForUpdate(module.moduleName.getStringValue());
        module.main.schema.setValue(schema);
    }

    protected void setupSchemaForDelete() {
        Schema schema = getSchemaForDelete(module.moduleName.getStringValue());
        module.main.schema.setValue(schema);
    }

    protected Schema getSchemaForUpdate(final String typeName) {
        return NetSuiteComponentDefinition.withDatasetRuntime(this, new Function<NetSuiteDatasetRuntime, Schema>() {
            @Override public Schema apply(NetSuiteDatasetRuntime dataSetRuntime) {
                return dataSetRuntime.getSchemaForUpdate(typeName);
            }
        });
    }

    protected Schema getSchemaForDelete(final String typeName) {
        return NetSuiteComponentDefinition.withDatasetRuntime(this, new Function<NetSuiteDatasetRuntime, Schema>() {
            @Override public Schema apply(NetSuiteDatasetRuntime dataSetRuntime) {
                return dataSetRuntime.getSchemaForDelete(typeName);
            }
        });
    }

    @Override
    protected Set<PropertyPathConnector> getAllSchemaPropertiesConnectors(boolean isOutputConnection) {
        Set<PropertyPathConnector> connectors = new HashSet<>();
        if (isOutputConnection) {
            connectors.add(MAIN_CONNECTOR);
        }
        return connectors;
    }

    public class ModuleProperties extends NetSuiteModuleProperties {

        public ModuleProperties(String name) {
            super(name);
        }

        @Override
        public ValidationResult afterModuleName() throws Exception {
            ValidationResult validationResult = new ValidationResult();

            try {
                setupSchema();
                validationResult.setStatus(ValidationResult.Result.OK);
            } catch (Exception e) {
                return exceptionToValidationResult(e);
            }

            return validationResult;
        }
    }

}
