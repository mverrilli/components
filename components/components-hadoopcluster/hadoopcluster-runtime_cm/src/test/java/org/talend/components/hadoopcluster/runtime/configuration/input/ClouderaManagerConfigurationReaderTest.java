package org.talend.components.hadoopcluster.runtime.configuration.input;

import static org.junit.Assert.*;

import org.junit.Test;
import org.talend.components.api.component.runtime.BoundedReader;
import org.talend.components.hadoopcluster.configuration.input
        .HadoopClusterConfigurationInputProperties;

import java.io.IOException;

public class ClouderaManagerConfigurationReaderTest {

    @Test
    public void basicTest() throws IOException {
        HadoopClusterConfigurationInputProperties properties = new
                HadoopClusterConfigurationInputProperties("properties");
        properties.init();
        properties.clusterManagerType.setValue(HadoopClusterConfigurationInputProperties
                .ClusterManagerType.CLOUDERA_MANAGER);
        properties.url.setValue("http://192.168.31.52:7180/");
        properties.basicAuth.useAuth.setValue(true);
        properties.basicAuth.userId.setValue("admin");
        properties.basicAuth.password.setValue("admin");

        ClouderaManagerConfigurationSource source = new ClouderaManagerConfigurationSource();
        source.initialize(null, properties);
        BoundedReader reader = source.createReader(null);
        for (boolean available = reader.start(); available; available = reader.advance()) {
            Object current = reader.getCurrent();
            System.out.println(current);
        }

    }
}
