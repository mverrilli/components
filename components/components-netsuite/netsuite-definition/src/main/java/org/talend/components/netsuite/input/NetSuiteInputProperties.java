package org.talend.components.netsuite.input;

import org.talend.components.netsuite.NetSuiteConnectionProperties;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class NetSuiteInputProperties extends NetSuiteConnectionProperties {

    public NetSuiteInputProperties(@JsonProperty("name") String name) {
        super(name);
    }

}
