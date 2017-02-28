package org.talend.components.netsuite;

import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public interface NetSuiteRuntime {

    SchemaService getSchemaService(NetSuiteConnectionProperties properties);

    ValidationResult validateConnection(NetSuiteConnectionProperties properties);
}
