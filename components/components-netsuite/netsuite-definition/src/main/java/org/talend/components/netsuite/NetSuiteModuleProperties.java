package org.talend.components.netsuite;

import static org.talend.components.netsuite.util.ComponentExceptions.exceptionToValidationResult;
import static org.talend.daikon.properties.presentation.Widget.widget;
import static org.talend.daikon.properties.property.PropertyFactory.newString;

import java.util.List;

import org.apache.avro.Schema;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.api.properties.ComponentPropertiesImpl;
import org.talend.components.common.SchemaProperties;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.components.netsuite.schema.SearchInfo;
import org.talend.daikon.NamedThing;
import org.talend.daikon.java8.Function;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;
import org.talend.daikon.properties.property.StringProperty;

/**
 *
 */
public class NetSuiteModuleProperties extends ComponentPropertiesImpl implements NetSuiteProvideConnectionProperties {

    public NetSuiteConnectionProperties connection = new NetSuiteConnectionProperties("connection");

    public StringProperty moduleName = newString("moduleName"); //$NON-NLS-1$

    public SchemaProperties main = new SchemaProperties("main");

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
        return NetSuiteComponentDefinition.withDatasetRuntime(this, new Function<NetSuiteDatasetRuntime, List<NamedThing>>() {
            @Override public List<NamedThing> apply(NetSuiteDatasetRuntime dataSetRuntime) {
                return dataSetRuntime.getSearchableTypes();
            }
        });
    }

    protected Schema getSchema(final String typeName) {
        return NetSuiteComponentDefinition.withDatasetRuntime(this, new Function<NetSuiteDatasetRuntime, Schema>() {
            @Override public Schema apply(NetSuiteDatasetRuntime dataSetRuntime) {
                return dataSetRuntime.getSchema(typeName);
            }
        });
    }

    protected SearchInfo getSearchInfo(final String typeName) {
        return NetSuiteComponentDefinition.withDatasetRuntime(this, new Function<NetSuiteDatasetRuntime, SearchInfo>() {
            @Override public SearchInfo apply(NetSuiteDatasetRuntime dataSetRuntime) {
                return dataSetRuntime.getSearchInfo(typeName);
            }
        });
    }

}
