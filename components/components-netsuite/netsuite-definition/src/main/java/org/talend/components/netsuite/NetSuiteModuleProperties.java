package org.talend.components.netsuite;

import java.util.List;

import org.apache.avro.Schema;
import org.talend.components.api.component.ISchemaListener;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.api.properties.ComponentPropertiesImpl;
import org.talend.components.common.SchemaProperties;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.components.netsuite.schema.NsSchema;
import org.talend.daikon.NamedThing;
import org.talend.daikon.java8.Function;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;
import org.talend.daikon.properties.property.StringProperty;

import static org.talend.components.netsuite.util.ComponentExceptions.exceptionToValidationResult;
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

        Form mainForm = Form.create(this, Form.MAIN);
        mainForm.addRow(widget(moduleName)
                .setWidgetType(Widget.NAME_SELECTION_AREA_WIDGET_TYPE)
                .setLongRunning(true));
        refreshLayout(mainForm);

        Form refForm = Form.create(this, Form.REFERENCE);
        refForm.addRow(widget(moduleName)
                .setWidgetType(Widget.NAME_SELECTION_REFERENCE_WIDGET_TYPE)
                .setLongRunning(true));

        refForm.addRow(main.getForm(Form.REFERENCE));
        refreshLayout(refForm);
    }

    public void setSchemaListener(ISchemaListener schemaListener) {
        this.schemaListener = schemaListener;
    }

    // consider beforeActivate and beforeRender (change after to afterActivate)

    public ValidationResult beforeModuleName() throws Exception {
        try {
            List<NamedThing> searchableTypes = getSearchableTypes();
            moduleName.setPossibleNamedThingValues(searchableTypes);
        } catch (ComponentException ex) {
            return exceptionToValidationResult(ex);
        }
        return ValidationResult.OK;
    }

    public ValidationResult afterModuleName() throws Exception {
        try {
            Schema schema = getSchema(moduleName.getStringValue());
            main.schema.setValue(schema);
        } catch (ComponentException ex) {
            return exceptionToValidationResult(ex);
        }
        return ValidationResult.OK;
    }

    @Override
    public NetSuiteConnectionProperties getConnectionProperties() {
        return connection.getConnectionProperties();
    }

    protected List<NamedThing> getSearchableTypes() {
        return TNetSuiteComponentDefinition.withSchemaService(new Function<SchemaService, List<NamedThing>>() {
            @Override public List<NamedThing> apply(SchemaService schemaService) {
                return schemaService.getSearchableTypes();
            }
        }, this);
    }

    protected Schema getSchema(final String typeName) {
        return TNetSuiteComponentDefinition.withSchemaService(new Function<SchemaService, Schema>() {
            @Override public Schema apply(SchemaService schemaService) {
                return schemaService.getSchema(typeName);
            }
        }, this);
    }

    protected NsSchema getSchemaForSearch(final String typeName) {
        return TNetSuiteComponentDefinition.withSchemaService(new Function<SchemaService, NsSchema>() {
            @Override public NsSchema apply(SchemaService schemaService) {
                return schemaService.getSchemaForSearch(typeName);
            }
        }, this);
    }

}
