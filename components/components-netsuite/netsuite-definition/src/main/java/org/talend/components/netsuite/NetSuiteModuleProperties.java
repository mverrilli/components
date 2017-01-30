package org.talend.components.netsuite;

import java.util.List;

import org.talend.components.api.component.ISchemaListener;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.api.properties.ComponentPropertiesImpl;
import org.talend.components.common.SchemaProperties;
import org.talend.daikon.NamedThing;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;
import org.talend.daikon.properties.property.StringProperty;

import static org.talend.daikon.properties.presentation.Widget.widget;
import static org.talend.daikon.properties.property.PropertyFactory.newString;

/**
 *
 */
public class NetSuiteModuleProperties extends ComponentPropertiesImpl implements NetSuiteProvideConnectionProperties {

    public NetSuiteConnectionProperties connection = new NetSuiteConnectionProperties("connection");

    public StringProperty moduleName = newString("moduleName"); //$NON-NLS-1$

    public ISchemaListener schemaListener;

    public SchemaProperties main = new SchemaProperties("main") {

        public void afterSchema() {
            if (schemaListener != null) {
                schemaListener.afterSchema();
            }
        }
    };

    public NetSuiteModuleProperties(String name) {
        super(name);
    }

    @Override
    public void setupProperties() {
        super.setupProperties();
    }

    @Override
    public void setupLayout() {
        super.setupLayout();

        Form moduleForm = Form.create(this, Form.MAIN);
        moduleForm.addRow(widget(moduleName)
                .setWidgetType(Widget.NAME_SELECTION_AREA_WIDGET_TYPE));
        refreshLayout(moduleForm);

        Form moduleRefForm = Form.create(this, Form.REFERENCE);
        moduleRefForm.addRow(widget(moduleName)
                .setWidgetType(Widget.NAME_SELECTION_REFERENCE_WIDGET_TYPE)
                .setLongRunning(true));

        moduleRefForm.addRow(main.getForm(Form.REFERENCE));
        refreshLayout(moduleRefForm);
    }

    public void setSchemaListener(ISchemaListener schemaListener) {
        this.schemaListener = schemaListener;
    }

    // consider beforeActivate and beforeRender (change after to afterActivate)

    public ValidationResult beforeModuleName() throws Exception {
        try {
            List<NamedThing> moduleNames = NetSuiteDesignSchemaManager.getInstance()
                    .getSchemaNames(connection);
            moduleName.setPossibleNamedThingValues(moduleNames);
        } catch (ComponentException ex) {
            return ex.getValidationResult();
        }
        return ValidationResult.OK;
    }

    public ValidationResult afterModuleName() throws Exception {
        try {
            main.schema.setValue(NetSuiteDesignSchemaManager.getInstance()
                    .getSchema(connection, moduleName.getStringValue()));
        } catch (ComponentException ex) {
            return ex.getValidationResult();
        }
        return ValidationResult.OK;
    }

    @Override
    public NetSuiteConnectionProperties getConnectionProperties() {
        return connection;
    }

}
