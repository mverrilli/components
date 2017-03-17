// ============================================================================
//
// Copyright (C) 2006-2017 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.components.hadoopcluster.configuration.input;

import java.util.Collections;
import java.util.Set;

import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;
import org.talend.components.common.FixedConnectorsComponentProperties;
import org.talend.components.common.SchemaProperties;
import org.talend.components.common.SslProperties;
import org.talend.components.common.UserPasswordProperties;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.property.Property;
import org.talend.daikon.properties.property.PropertyFactory;

public class HadoopClusterConfigurationInputProperties extends FixedConnectorsComponentProperties {

    public Property<ClusterManagerType> clusterManagerType = PropertyFactory.newEnum("clusterManagerType",
            ClusterManagerType.class);

    public Property<String> url = PropertyFactory.newString("url", "");

    public UserPasswordProperties basicAuth = new UserPasswordProperties("basicAuth", true);

    public SslProperties ssl = new SslProperties("ssl", SslProperties.FormType.TRUST_ONLY);

    public SchemaProperties schema = new SchemaProperties("schema");

    public HadoopClusterConfigurationBlackListTableProperties blackList = new
            HadoopClusterConfigurationBlackListTableProperties("blackList");

    protected transient PropertyPathConnector MAIN_CONNECTOR = new PropertyPathConnector(Connector.MAIN_NAME, "schema");

    public HadoopClusterConfigurationInputProperties(String name) {
        super(name);
    }

    @Override
    public void setupProperties() {
        schema.schema.setValue(HadoopClusterConfiguration.schema);
    }

    @Override
    public void setupLayout() {
        super.setupLayout();
        Form mainForm = new Form(this, Form.MAIN);
        mainForm.addRow(clusterManagerType);
        mainForm.addRow(url);
        mainForm.addRow(basicAuth);
        mainForm.addRow(ssl);
        mainForm.addRow(schema);
        mainForm.addRow(blackList);
    }

    @Override
    protected Set<PropertyPathConnector> getAllSchemaPropertiesConnectors(boolean isOutputConnection) {
        if (isOutputConnection) {
            return Collections.singleton(MAIN_CONNECTOR);
        } else {
            return Collections.EMPTY_SET;
        }
    }

    public enum ClusterManagerType {
        CLOUDERA_MANAGER,
        AMBARI;
    }
}
