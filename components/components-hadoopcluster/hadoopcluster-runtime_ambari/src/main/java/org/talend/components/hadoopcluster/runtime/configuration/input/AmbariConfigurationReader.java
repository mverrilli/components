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

package org.talend.components.hadoopcluster.runtime.configuration.input;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.TrustManager;

import org.talend.components.api.component.runtime.BoundedReader;
import org.talend.components.api.component.runtime.BoundedSource;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.hadoopcluster.configuration.input.HadoopClusterConfigurationInputProperties;
import org.talend.components.hadoopcluster.runtime.configuration.HadoopAmbariConfigurator;

public class AmbariConfigurationReader extends HadoopClusterConfigurationReader {

    public AmbariConfigurationReader(BoundedSource source, HadoopClusterConfigurationInputProperties properties) {
        super(source, properties);
    }

    @Override
    void initBuilder() {
        builder = new HadoopAmbariConfigurator.Builder();
    }

    public static void main(String[] args) throws IOException {
        HadoopClusterConfigurationInputProperties properties = new
                HadoopClusterConfigurationInputProperties("properties");
        properties.init();
        properties.clusterManagerType.setValue(HadoopClusterConfigurationInputProperties
                .ClusterManagerType.AMBARI);
        properties.url.setValue("http://tal-qa139.talend.lan:8080");
        properties.basicAuth.useAuth.setValue(true);
        properties.basicAuth.userId.setValue("admin");
        properties.basicAuth.password.setValue("admin");

        AmbariConfigurationSource source = new AmbariConfigurationSource();
        source.initialize(null, properties);
        BoundedReader reader = source.createReader(null);
        for (boolean available = reader.start(); available; available = reader.advance()) {
            Object current = reader.getCurrent();
            System.out.println(current);
        }
    }

}
