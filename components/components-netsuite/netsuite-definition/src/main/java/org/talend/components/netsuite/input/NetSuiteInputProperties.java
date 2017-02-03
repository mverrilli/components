package org.talend.components.netsuite.input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.avro.Schema;
import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.api.properties.ComponentPropertiesImpl;
import org.talend.components.netsuite.NetSuiteConnectionProperties;
import org.talend.components.netsuite.NetSuiteModuleProperties;
import org.talend.components.netsuite.NetSuiteProvideConnectionProperties;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.NetSuiteFactory;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.property.Property;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class NetSuiteInputProperties extends ComponentPropertiesImpl implements NetSuiteProvideConnectionProperties {

    public NetSuiteConnectionProperties connection = new NetSuiteConnectionProperties("connection");

    public NetSuiteModuleProperties module;

    public NetSuiteSearchConditionTable searchConditionTable;

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

        searchConditionTable = new NetSuiteSearchConditionTable("searchConditionTable");
    }

    @Override
    public void setupLayout() {
        super.setupLayout();

        Form mainForm = new Form(this, Form.MAIN);
        mainForm.addRow(connection.getForm(Form.REFERENCE));
        mainForm.addRow(module.getForm(Form.REFERENCE));
        mainForm.addRow(searchConditionTable.getForm(Form.REFERENCE));

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

    protected List<String> getSearchFieldNames(String typeName) {
        try {
            NetSuiteMetaData metaData = NetSuiteFactory.getMetaData(NetSuiteConnectionProperties.API_VERSION);
            NetSuiteMetaData.SearchInfo searchInfo = metaData.getSearchInfo(typeName);
            NetSuiteMetaData.EntityInfo searchRecordInfo = metaData.getEntity(searchInfo.getSearchBasicClass());
            List<NetSuiteMetaData.FieldInfo> searchFieldInfos = searchRecordInfo.getFields();
            List<String> fieldNames = new ArrayList<>(searchFieldInfos.size());
            for (NetSuiteMetaData.FieldInfo fieldInfo : searchFieldInfos) {
                fieldNames.add(fieldInfo.getName());
            }
            return fieldNames;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
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

            try {
                NetSuiteMetaData metaData = NetSuiteFactory.getMetaData(NetSuiteConnectionProperties.API_VERSION);
                searchConditionTable.operator.setPossibleValues(metaData.getSearchOperatorNames());
            } catch (NetSuiteException e) {
                throw new ComponentException(e);
            }

            return validationResult;
        }
    }
}
