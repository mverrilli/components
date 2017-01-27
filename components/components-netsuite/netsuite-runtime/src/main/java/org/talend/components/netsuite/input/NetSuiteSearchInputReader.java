package org.talend.components.netsuite.input;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.component.runtime.AbstractBoundedReader;
import org.talend.components.api.component.runtime.Result;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.NetSuiteSource;
import org.talend.components.netsuite.NsObjectConverter;
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.components.netsuite.client.NsObject;
import org.talend.components.netsuite.client.NsSearch;
import org.talend.components.netsuite.client.ResultSet;
import org.talend.daikon.avro.AvroUtils;
import org.talend.daikon.avro.converter.IndexedRecordConverter;

/**
 *
 */
public class NetSuiteSearchInputReader extends AbstractBoundedReader<IndexedRecord> {

    private transient NetSuiteConnection connection;
    private transient NetSuiteMetaData metaData;

    private transient IndexedRecordConverter<NsObject, IndexedRecord> converter;

    protected transient Schema searchSchema;

    protected NetSuiteInputProperties properties;

    protected int dataCount;

    protected RuntimeContainer container;

    protected ResultSet<NsObject> resultSet;

    protected NsObject currentRecord;

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
        try {
            return convertRecord(currentRecord);
        } catch (IOException e) {
            throw new ComponentException(e);
        }
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

    public NsObject getCurrentRecord() throws NoSuchElementException {
        return currentRecord;
    }

    protected NetSuiteConnection getConnection() throws NetSuiteException {
        if (connection == null) {
            connection = ((NetSuiteSource) getCurrentSource()).connect(container);
            metaData = connection.getMetaData();
        }
        return connection;
    }

    protected ResultSet<NsObject> search() throws NetSuiteException {
        NetSuiteConnection conn = getConnection();
        NsSearch search = conn.newSearch();
        search.entity("Account");
        search.criterion("balance", "GREATER_THAN_OR_EQUAL_TO",
                Arrays.asList("10000.0"), "Double");
        ResultSet<NsObject> resultSet = search.search();
        return resultSet;
    }

    protected Schema getSchema() throws IOException {
        if (searchSchema == null) {
            searchSchema = properties.main.schema.getValue();
            if (AvroUtils.isIncludeAllFields(searchSchema)) {
                String moduleName = "Account";
                searchSchema = getCurrentSource().getEndpointSchema(container, moduleName);
            }
        }
        return searchSchema;
    }

    protected IndexedRecord convertRecord(NsObject record) throws IOException {
        return getConverter().convertToAvro(record);
    }

    protected IndexedRecordConverter<NsObject, IndexedRecord> getConverter() throws IOException {
        if (converter == null) {
            converter = new NsObjectConverter(metaData);
            converter.setSchema(getSchema());
        }
        return converter;
    }

}
