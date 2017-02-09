package org.talend.components.netsuite;

import org.talend.components.api.component.runtime.Sink;
import org.talend.components.api.component.runtime.WriteOperation;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.netsuite.output.NetSuiteOutputProperties;
import org.talend.components.netsuite.output.NetSuiteWriteOperation;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public class NetSuiteSink extends NetSuiteSourceOrSink implements Sink {

    @Override
    public WriteOperation<?> createWriteOperation() {
        return new NetSuiteWriteOperation(this, (NetSuiteOutputProperties) getProperties());
    }

    @Override
    public ValidationResult validate(RuntimeContainer container) {
        return super.validate(container);
    }
}
