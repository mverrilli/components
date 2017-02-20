package org.talend.components.netsuite.input;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.component.runtime.AbstractBoundedReader;
import org.talend.components.api.component.runtime.Result;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.netsuite.NetSuiteSource;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.query.SearchCondition;
import org.talend.components.netsuite.client.query.SearchQuery;
import org.talend.components.netsuite.client.common.ResultSet;
import org.talend.daikon.avro.AvroUtils;

/**
 *
 */
public class NetSuiteSearchInputReader extends AbstractBoundedReader<IndexedRecord> {

    private transient NetSuiteClientService clientService;

    private transient NsRecordReadTransducer transducer;

    protected transient Schema searchSchema;

    protected NetSuiteInputProperties properties;

    protected int dataCount;

    protected RuntimeContainer container;

    protected ResultSet<?> resultSet;

    protected Object currentRecord;
    protected IndexedRecord currentIndexedRecord;

    public NetSuiteSearchInputReader(RuntimeContainer container,
            NetSuiteSource source, NetSuiteInputProperties properties) {
        super(source);

        this.container = container;
        this.properties = properties;
    }

    @Override
    public boolean start() throws IOException {
        try {
            resultSet = search();
            return advance();
        } catch (NetSuiteException e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean advance() throws IOException {
        try {
            if (resultSet.next()) {
                currentRecord = resultSet.get();
                currentIndexedRecord = transduceRecord(currentRecord);
                dataCount++;
                return true;
            }
            return false;
        } catch (NetSuiteException e) {
            throw new IOException(e);
        }
    }

    @Override
    public IndexedRecord getCurrent() throws NoSuchElementException {
        return currentIndexedRecord;
    }

    @Override
    public void close() throws IOException {
        // Nothing to close
    }

    @Override
    public Map<String, Object> getReturnValues() {
        Result result = new Result();
        result.totalCount = dataCount;
        return result.toMap();
    }

    public Object getCurrentRecord() throws NoSuchElementException {
        return currentRecord;
    }

    protected NetSuiteClientService getClientService() throws NetSuiteException {
        if (clientService == null) {
            clientService = ((NetSuiteSource) getCurrentSource()).connect(container);
        }
        return clientService;
    }

    protected ResultSet<?> search() throws NetSuiteException {
        try {
            NetSuiteClientService clientService = getClientService();

            transducer = new NsRecordReadTransducer(clientService);
            transducer.setSchema(getSchema());

            SearchQuery search = clientService.newSearch();
            search.target(properties.module.moduleName.getValue());

            List<String> fieldNames = properties.searchConditionTable.field.getValue();
            if (fieldNames != null && !fieldNames.isEmpty()) {
                for (int i = 0; i < fieldNames.size(); i++) {
                    String fieldName = fieldNames.get(i);
                    String operator = properties.searchConditionTable.operator.getValue().get(i);
                    String value1 = properties.searchConditionTable.value1.getValue().get(i);
                    String value2 = properties.searchConditionTable.value2.getValue().get(i);
                    List<String> values = null;
                    if (value1 != null) {
                        values = value2 != null ? Arrays.asList(value1, value2) : Arrays.asList(value1);
                    }
                    search.condition(new SearchCondition(fieldName, operator, values));
                }
            }

            ResultSet<?> resultSet = search.search();
            return resultSet;
        } catch (IOException ex) {
            throw new NetSuiteException(ex.getMessage(), ex);
        }
    }

    protected Schema getSchema() throws IOException {
        if (searchSchema == null) {
            searchSchema = properties.module.main.schema.getValue();
            if (AvroUtils.isIncludeAllFields(searchSchema)) {
                String moduleName = properties.module.moduleName.getValue();
                searchSchema = getCurrentSource().getEndpointSchema(container, moduleName);
            }
        }
        return searchSchema;
    }

    protected IndexedRecord transduceRecord(Object record) throws IOException {
        return transducer.read(record);
    }
}
