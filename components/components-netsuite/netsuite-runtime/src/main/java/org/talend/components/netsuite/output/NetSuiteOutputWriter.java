package org.talend.components.netsuite.output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.component.runtime.Result;
import org.talend.components.api.component.runtime.WriteOperation;
import org.talend.components.api.component.runtime.WriterWithFeedback;
import org.talend.components.netsuite.NsObjectIndexedRecordConverter;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.components.netsuite.client.NsObject;
import org.talend.daikon.avro.converter.IndexedRecordConverter;

/**
 *
 */
public class NetSuiteOutputWriter implements WriterWithFeedback<Result, IndexedRecord, IndexedRecord> {

    protected final NetSuiteWriteOperation writeOperation;

    protected final List<IndexedRecord> successfulWrites = new ArrayList<>();

    protected final List<IndexedRecord> rejectedWrites = new ArrayList<>();

    protected NetSuiteClientService connection;
    protected NetSuiteMetaData metaData;
    protected Operation<?> operation;
    protected IndexedRecordConverter<NsObject, IndexedRecord> converter;
    protected int dataCount = 0;

    public NetSuiteOutputWriter(NetSuiteWriteOperation writeOperation) {
        this.writeOperation = writeOperation;
    }

    @Override
    public Iterable<IndexedRecord> getSuccessfulWrites() {
        return successfulWrites;
    }

    @Override
    public Iterable<IndexedRecord> getRejectedWrites() {
        return rejectedWrites;
    }

    @Override
    public void open(String uId) throws IOException {
        try {
            connection = writeOperation.getSink().connect(writeOperation.getRuntimeContainer());
            metaData = connection.getMetaData();
            NetSuiteOutputProperties.OutputAction action = writeOperation.getProperties().action.getValue();
            if (action == NetSuiteOutputProperties.OutputAction.ADD) {
                operation = new AddOperation<>();
            } else if (action == NetSuiteOutputProperties.OutputAction.UPDATE) {
                operation = new UpdateOperation<>();
            } else if (action == NetSuiteOutputProperties.OutputAction.UPSERT) {
                operation = new UpsertOperation<>();
            } else if (action == NetSuiteOutputProperties.OutputAction.DELETE) {
                operation = new DeleteOperation<>();
            }
        } catch (NetSuiteException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(Object object) throws IOException {
        IndexedRecord record = (IndexedRecord) object;
        try {
            operation.write(record);
            successfulWrites.add(record);
        } catch (IOException e) {
            rejectedWrites.add(record);
        }
        dataCount++;
    }

    @Override
    public Result close() throws IOException {
        Result result = new Result();
        result.totalCount = dataCount;
        result.successCount = successfulWrites.size();
        result.rejectCount = rejectedWrites.size();
        return result;
    }

    @Override
    public WriteOperation<Result> getWriteOperation() {
        return writeOperation;
    }

    protected <T> T createObject(IndexedRecord record) throws IOException {
        NsObject<T> nsObject = getConverter().convertToDatum(record);
        return nsObject.getTarget();
    }

    protected IndexedRecordConverter<NsObject, IndexedRecord> getConverter() throws IOException {
        if (converter == null) {
            converter = new NsObjectIndexedRecordConverter(metaData);
        }
        return converter;
    }

    protected abstract class Operation<T> {

        void write(IndexedRecord record) throws IOException {
            T nsObject = createObject(record);
            try {
                doWrite(nsObject);
            } catch (NetSuiteException e) {
                throw new IOException(e);
            }
        }

        abstract void doWrite(T nsObject) throws NetSuiteException;
    }

    protected class AddOperation<RecT> extends Operation<RecT> {

        @Override
        void doWrite(RecT nsObject) throws NetSuiteException {
            connection.add(nsObject);
        }
    }

    protected class UpdateOperation<RecT> extends Operation<RecT> {

        @Override
        void doWrite(RecT nsObject) throws NetSuiteException {
            connection.update(nsObject);
        }
    }

    protected class UpsertOperation<RecT> extends Operation<RecT> {

        @Override
        void doWrite(RecT nsObject) throws NetSuiteException {
            connection.upsert(nsObject);
        }
    }

    protected class DeleteOperation<RefT> extends Operation<RefT> {

        @Override
        void doWrite(RefT nsRef) throws NetSuiteException {
            connection.delete(nsRef);
        }
    }
}
