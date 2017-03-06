package org.talend.components.netsuite;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.api.component.runtime.BoundedReader;
import org.talend.components.api.component.runtime.BoundedSource;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.netsuite.input.NetSuiteInputProperties;
import org.talend.components.netsuite.input.NetSuiteSearchInputReader;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public abstract class NetSuiteSource extends NetSuiteSourceOrSink implements BoundedSource {

    private transient static final Logger LOG = LoggerFactory.getLogger(NetSuiteSource.class);

    @Override
    public long getEstimatedSizeBytes(RuntimeContainer adaptor) {
        return 0;
    }

    @Override
    public boolean producesSortedKeys(RuntimeContainer adaptor) {
        return false;
    }

    @Override
    public List<? extends BoundedSource> splitIntoBundles(long desiredBundleSizeBytes, RuntimeContainer container)
            throws Exception {
        List<BoundedSource> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public BoundedReader createReader(RuntimeContainer container) {
        if (properties instanceof NetSuiteInputProperties) {
            NetSuiteInputProperties nsProperties = (NetSuiteInputProperties) properties;
            return new NetSuiteSearchInputReader(container, this, nsProperties);
        }
        return null;
    }

    @Override
    public ValidationResult validate(RuntimeContainer container) {
        return super.validate(container);
    }
}
