package org.talend.components.netsuite.runtime;

import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public interface RuntimeService {

    SchemaService getSchemaService(NetSuiteConnectionProperties properties);

    ValidationResult validateConnection(NetSuiteConnectionProperties properties);
}
