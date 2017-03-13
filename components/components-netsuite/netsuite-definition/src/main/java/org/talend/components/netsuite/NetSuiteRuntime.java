package org.talend.components.netsuite;

import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public interface NetSuiteRuntime {

    void setContext(Context context);

    Context getContext();

    NetSuiteDatasetRuntime getDatasetRuntime(NetSuiteConnectionProperties properties);

    ValidationResult validateConnection(NetSuiteConnectionProperties properties);

    interface Context {

        boolean isCachingEnabled();

        Object getAttribute(String key);

        void setAttribute(String key, Object value);
    }
}
