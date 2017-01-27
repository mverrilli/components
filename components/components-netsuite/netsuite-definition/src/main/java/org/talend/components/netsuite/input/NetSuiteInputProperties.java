package org.talend.components.netsuite.input;

import org.talend.components.common.SchemaProperties;
import org.talend.components.netsuite.NetSuiteConnectionProperties;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class NetSuiteInputProperties extends NetSuiteConnectionProperties {

    public SchemaProperties main = new SchemaProperties("main") {
        public void afterSchema() {
        }
    };

    public NetSuiteInputProperties(@JsonProperty("name") String name) {
        super(name);
    }

}
