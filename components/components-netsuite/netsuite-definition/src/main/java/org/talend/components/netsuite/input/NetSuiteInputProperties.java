package org.talend.components.netsuite.input;

import static org.talend.components.netsuite.NetSuiteComponentDefinition.withDatasetRuntime;
import static org.talend.daikon.properties.presentation.Widget.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;
import org.talend.components.common.FixedConnectorsComponentProperties;
import org.talend.components.netsuite.NetSuiteModuleProperties;
import org.talend.components.netsuite.NetSuiteProvideConnectionProperties;
import org.talend.components.netsuite.NetSuiteDatasetRuntime;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.components.netsuite.schema.SearchFieldInfo;
import org.talend.components.netsuite.schema.SearchInfo;
import org.talend.daikon.java8.Function;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class NetSuiteInputProperties extends FixedConnectorsComponentProperties
        implements NetSuiteProvideConnectionProperties {

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
        for (Form childForm : searchConditionTable.getForms()) {
            searchConditionTable.refreshLayout(childForm);
        }
    }

    protected List<String> getSearchFieldOperators() {
        return withDatasetRuntime(this, new Function<NetSuiteDatasetRuntime, List<String>>() {
            @Override public List<String> apply(NetSuiteDatasetRuntime dataSetRuntime) {
                return dataSetRuntime.getSearchFieldOperators();
            }
        });
    }

    public class ModuleProperties extends NetSuiteModuleProperties {

        public ModuleProperties(String name) {
            super(name);
        }

        @Override
        public ValidationResult afterModuleName() throws Exception {
            ValidationResult validationResult = super.afterModuleName();

            SearchInfo searchSchema = getSearchInfo(moduleName.getValue());
            List<String> fieldNames = new ArrayList<>(searchSchema.getFields().size());
            for (SearchFieldInfo field : searchSchema.getFields()) {
                fieldNames.add(field.getName());
            }

            searchConditionTable.field.setPossibleValues(fieldNames);
            searchConditionTable.field.setValue(new ArrayList<String>());

            searchConditionTable.operator.setPossibleValues(getSearchFieldOperators());
            searchConditionTable.operator.setValue(new ArrayList<String>());

            searchConditionTable.value1.setValue(new ArrayList<String>());
            searchConditionTable.value2.setValue(new ArrayList<String>());

            return validationResult;
        }
    }

    @Override
    protected Set<PropertyPathConnector> getAllSchemaPropertiesConnectors(boolean isOutputConnection) {
        if (isOutputConnection) {
            return Collections.singleton(MAIN_CONNECTOR);
        }
        return Collections.emptySet();
    }
}
