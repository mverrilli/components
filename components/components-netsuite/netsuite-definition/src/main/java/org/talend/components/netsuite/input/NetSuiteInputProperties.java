package org.talend.components.netsuite.input;

import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;
import org.talend.components.api.properties.ComponentPropertiesImpl;
import org.talend.components.netsuite.NetSuiteConnectionProperties;
import org.talend.components.netsuite.NetSuiteDefinition;
import org.talend.components.netsuite.NetSuiteMetaDataService;
import org.talend.components.netsuite.NetSuiteModuleProperties;
import org.talend.components.netsuite.NetSuiteProvideConnectionProperties;
import org.talend.daikon.java8.Function;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;
import org.talend.daikon.properties.property.Property;

import com.fasterxml.jackson.annotation.JsonProperty;

import static org.talend.daikon.properties.presentation.Widget.widget;

/**
 *
 */
public class NetSuiteInputProperties extends ComponentPropertiesImpl implements NetSuiteProvideConnectionProperties {

    public NetSuiteConnectionProperties connection = new NetSuiteConnectionProperties("connection");

    public NetSuiteModuleProperties module;

    public SearchConditionTable searchConditionTable;

    protected transient PropertyPathConnector MAIN_CONNECTOR =
            new PropertyPathConnector(Connector.MAIN_NAME, "module.main");

    public NetSuiteInputProperties(@JsonProperty("name") String name) {
        super(name);
    }

    @Override
    public void setupProperties() {
        super.setupProperties();

        module = new ModuleProperties("module");
        module.connection = connection;

        searchConditionTable = new SearchConditionTable("searchConditionTable");
    }

    @Override
    public void setupLayout() {
        super.setupLayout();

        Form mainForm = new Form(this, Form.MAIN);
        mainForm.addRow(connection.getForm(Form.REFERENCE));
        mainForm.addRow(module.getForm(Form.REFERENCE));
        mainForm.addRow(widget(searchConditionTable).setWidgetType(Widget.TABLE_WIDGET_TYPE));

        Form advancedForm = new Form(this, Form.ADVANCED);
//        advancedForm.addRow(connection.getForm(Form.ADVANCED));
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
        for (Form childForm : module.getForms()) {
            module.refreshLayout(childForm);
        }
        for (Form childForm : searchConditionTable.getForms()) {
            searchConditionTable.refreshLayout(childForm);
        }
    }

    protected List<String> getSearchFieldNames(final String typeName) {
        return NetSuiteDefinition.withMetaDataService(new Function<NetSuiteMetaDataService, List<String>>() {
            @Override public List<String> apply(NetSuiteMetaDataService metaData) {
                return metaData.getSearchFieldNames(typeName);
            }
        }, this);
    }

    protected List<String> getSearchFieldOperators() {
        return NetSuiteDefinition.withMetaDataService(new Function<NetSuiteMetaDataService, List<String>>() {
            @Override public List<String> apply(NetSuiteMetaDataService metaData) {
                return metaData.getSearchFieldOperators();
            }
        }, this);
    }

    protected List<String> getFieldNames(Property schema) {
        Schema s = (Schema) schema.getValue();
        List<String> fieldNames = new ArrayList<>();
        for (Schema.Field f : s.getFields()) {
            fieldNames.add(f.name());
        }
        return fieldNames;
    }

    public class ModuleProperties extends NetSuiteModuleProperties {

        public ModuleProperties(String name) {
            super(name);
        }

        @Override
        public ValidationResult afterModuleName() throws Exception {
            ValidationResult validationResult = super.afterModuleName();

            List<String> fieldNames = getSearchFieldNames(moduleName.getValue());
            searchConditionTable.field.setPossibleValues(fieldNames);

            searchConditionTable.operator.setPossibleValues(getSearchFieldOperators());

            return validationResult;
        }
    }

}
