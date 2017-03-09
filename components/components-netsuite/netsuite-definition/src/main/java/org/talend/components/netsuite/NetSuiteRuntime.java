package org.talend.components.netsuite;

import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public interface NetSuiteRuntime {

    NetSuiteDatasetRuntime getDatasetRuntime(Context context, NetSuiteConnectionProperties properties);

    ValidationResult validateConnection(Context context, NetSuiteConnectionProperties properties);

    interface Context {

        boolean isCachingEnabled();

        Object getAttribute(String key);

        void setAttribute(String key, Object value);
    }
}
