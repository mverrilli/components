package org.talend.components.netsuite.output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.component.runtime.Result;
import org.talend.components.api.component.runtime.WriteOperation;
import org.talend.components.api.component.runtime.WriterWithFeedback;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;

/**
 * TODO Implement bulk Add/Update/Upsert/Delete
 */
public class NetSuiteOutputWriter implements WriterWithFeedback<Result, IndexedRecord, IndexedRecord> {

    protected final NetSuiteWriteOperation writeOperation;

    protected final List<IndexedRecord> successfulWrites = new ArrayList<>();

    protected final List<IndexedRecord> rejectedWrites = new ArrayList<>();

    protected NetSuiteClientService clientService;
    protected NetSuiteOutputProperties.OutputAction action;
    protected NsRecordWriteTransducer transducer;
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
            clientService = writeOperation.getSink().connect(writeOperation.getRuntimeContainer());
            action = writeOperation.getProperties().action.getValue();
            if (action == NetSuiteOutputProperties.OutputAction.ADD
                    || action == NetSuiteOutputProperties.OutputAction.UPDATE
                    || action == NetSuiteOutputProperties.OutputAction.UPSERT) {

                String typeName = writeOperation.getProperties().module.moduleName.getValue();
                transducer = new NsRecordWriteTransducer(clientService, typeName);

            } else if (action == NetSuiteOutputProperties.OutputAction.DELETE) {

                String typeName = writeOperation.getProperties().module.moduleName.getValue();
                transducer = new NsRecordWriteTransducer(clientService, typeName);
            }
        } catch (NetSuiteException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(Object object) throws IOException {
        IndexedRecord record = (IndexedRecord) object;
        try {
            if (action == NetSuiteOutputProperties.OutputAction.ADD) {
                clientService.add(transduceRecord(record));
            } else if (action == NetSuiteOutputProperties.OutputAction.UPDATE) {
                clientService.update(transduceRecord(record));
            } else if (action == NetSuiteOutputProperties.OutputAction.UPSERT) {
                clientService.upsert(transduceRecord(record));
            } else if (action == NetSuiteOutputProperties.OutputAction.DELETE) {
                clientService.delete(transduceRecord(record));
            }
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

    protected Object transduceRecord(IndexedRecord record) throws IOException {
        return transducer.write(record);
    }

}
