package org.talend.components.netsuite.output;

import java.util.Map;

import org.talend.components.api.component.runtime.Result;
import org.talend.components.api.component.runtime.WriteOperation;
import org.talend.components.api.component.runtime.Writer;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.netsuite.NetSuiteSink;

/**
 *
 */
public class NetSuiteWriteOperation implements WriteOperation<Result> {

    private final NetSuiteSink sink;
    private RuntimeContainer runtimeContainer;
    private NetSuiteOutputProperties properties;

    public NetSuiteWriteOperation(NetSuiteSink sink, NetSuiteOutputProperties properties) {
        this.sink = sink;
        this.properties = properties;
    }

    @Override
    public void initialize(RuntimeContainer adaptor) {
        this.runtimeContainer = adaptor;
    }

    @Override
    public Map<String, Object> finalize(Iterable<Result> writerResults, RuntimeContainer adaptor) {
        return Result.accumulateAndReturnMap(writerResults);
    }

    @Override
    public Writer<Result> createWriter(RuntimeContainer adaptor) {
        return new NetSuiteOutputWriter(this);
    }

    @Override
    public NetSuiteSink getSink() {
        return sink;
    }

    public RuntimeContainer getRuntimeContainer() {
        return runtimeContainer;
    }

    public NetSuiteOutputProperties getProperties() {
        return properties;
    }
}
