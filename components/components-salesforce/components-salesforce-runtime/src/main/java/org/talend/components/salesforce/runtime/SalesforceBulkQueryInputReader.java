// ============================================================================
//
// Copyright (C) 2006-2017 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.components.salesforce.runtime;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.avro.generic.IndexedRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.salesforce.soql.SoqlQuery;
import org.talend.components.salesforce.tsalesforceinput.TSalesforceInputProperties;

import com.sforce.async.AsyncApiException;
import com.sforce.ws.ConnectionException;

public class SalesforceBulkQueryInputReader extends SalesforceReader<IndexedRecord> {

    private static final Logger LOG = LoggerFactory.getLogger(SalesforceBulkQueryInputReader.class);

    protected SalesforceBulkRuntime bulkRuntime;

    protected BulkResultSet bulkResultSet;

    protected BulkResult currentRecord;

    public SalesforceBulkQueryInputReader(RuntimeContainer container, SalesforceSource source, TSalesforceInputProperties props) {
        super(container, source);
        properties = props;
        this.container = container;
    }
    
    @Override
    public boolean start() throws IOException {
        try {
            if (bulkRuntime == null) {
                bulkRuntime = new SalesforceBulkRuntime(((SalesforceSource) getCurrentSource()).connect(container).bulkConnection);
            }
            executeSalesforceBulkQuery();
            bulkResultSet = bulkRuntime.getQueryResultSet(bulkRuntime.nextResultId());
            currentRecord = bulkResultSet.next();
            boolean start = currentRecord != null;
            if (start) {
                dataCount++;
            }
            return start;
        } catch (ConnectionException | AsyncApiException e) {
            // Wrap the exception in an IOException.
            throw new IOException(e);
        }
    }

    @Override
    public boolean advance() throws IOException {
        currentRecord = bulkResultSet.next();
        if (currentRecord == null) {
            String resultId = bulkRuntime.nextResultId();
            if (resultId != null) {
                try {
                    // Get a new result set
                    bulkResultSet = bulkRuntime.getQueryResultSet(resultId);
                    currentRecord = bulkResultSet.next();
                    boolean advance = currentRecord != null;
                    if (advance) {
                        // New result set available to retrieve
                        dataCount++;
                    }
                    return advance;
                } catch (AsyncApiException | ConnectionException e) {
                    throw new IOException(e);
                }
            } else {
                return false;
            }
        }
        dataCount++;
        return true;
    }

    public BulkResult getCurrentRecord() throws NoSuchElementException {
        return currentRecord;
    }

    protected void executeSalesforceBulkQuery() throws IOException, ConnectionException {
        String queryText = getQueryString(properties);
        bulkRuntime = new SalesforceBulkRuntime(((SalesforceSource) getCurrentSource()).connect(container).bulkConnection);
        try {
            bulkRuntime.doBulkQuery(getModuleName(), queryText, 30);
        } catch (AsyncApiException | InterruptedException | ConnectionException e) {
            throw new IOException(e);
        }
    }

    @Override
    public IndexedRecord getCurrent() {
        try {
            return ((BulkResultAdapterFactory) getFactory()).convertToAvro(getCurrentRecord());
        } catch (IOException e) {
            throw new ComponentException(e);
        }
    }
    
    private String getModuleName() {
        TSalesforceInputProperties inProperties = (TSalesforceInputProperties) properties;
        if (inProperties.manualQuery.getValue()) {
            SoqlQuery query = SoqlQuery.getInstance();
            query.init(inProperties.query.getValue());
            return query.getDrivingEntityName();
        } else {
            return properties.module.moduleName.getValue();
        }
    }
}
