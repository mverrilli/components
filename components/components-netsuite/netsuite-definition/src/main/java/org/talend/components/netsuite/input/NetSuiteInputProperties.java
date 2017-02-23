package org.talend.components.netsuite.input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;
import org.talend.components.common.FixedConnectorsComponentProperties;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.components.netsuite.NetSuiteModuleProperties;
import org.talend.components.netsuite.NetSuiteProvideConnectionProperties;
import org.talend.components.netsuite.schema.NsField;
import org.talend.components.netsuite.schema.NsSchema;
import org.talend.components.netsuite.SchemaService;
import org.talend.daikon.java8.Function;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;

import com.fasterxml.jackson.annotation.JsonProperty;

import static org.talend.components.netsuite.TNetSuiteComponentDefinition.withSchemaService;
import static org.talend.daikon.properties.presentation.Widget.widget;

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

    protected NsSchema getSchemaForSearch(final String typeName) {
        return withSchemaService(new Function<SchemaService, NsSchema>() {
            @Override public NsSchema apply(SchemaService schemaService) {
                return schemaService.getSchemaForSearch(typeName);
            }
        }, this);
    }

    protected List<String> getSearchFieldOperators() {
        return withSchemaService(new Function<SchemaService, List<String>>() {
            @Override public List<String> apply(SchemaService schemaService) {
                return schemaService.getSearchFieldOperators();
            }
        }, this);
    }

    public class ModuleProperties extends NetSuiteModuleProperties {

        public ModuleProperties(String name) {
            super(name);
        }

        @Override
        public ValidationResult afterModuleName() throws Exception {
            ValidationResult validationResult = super.afterModuleName();

            NsSchema searchSchema = getSchemaForSearch(moduleName.getValue());
            List<String> fieldNames = new ArrayList<>(searchSchema.getFields().size());
            for (NsField field : searchSchema.getFields()) {
                fieldNames.add(field.getName());
            }
            searchConditionTable.field.setPossibleValues(fieldNames);

            searchConditionTable.operator.setPossibleValues(getSearchFieldOperators());

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
