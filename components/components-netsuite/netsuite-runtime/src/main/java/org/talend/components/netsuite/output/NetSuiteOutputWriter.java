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
import org.talend.components.netsuite.client.NsWriteResponse;
import org.talend.components.netsuite.client.model.TypeDesc;

/**
 * TODO Implement bulk Add/Update/Upsert/Delete
 */
public class NetSuiteOutputWriter implements WriterWithFeedback<Result, IndexedRecord, IndexedRecord> {

    protected final NetSuiteWriteOperation writeOperation;

    protected final List<IndexedRecord> successfulWrites = new ArrayList<>();

    protected final List<IndexedRecord> rejectedWrites = new ArrayList<>();

    protected NetSuiteClientService clientService;
    protected NetSuiteOutputProperties.OutputAction action;

    protected TypeDesc typeDesc;
    protected NsObjectOutputTransducer transducer;

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
            clientService = writeOperation.getSink().getClientService();
            action = writeOperation.getProperties().action.getValue();

            String typeName = writeOperation.getProperties().module.moduleName.getValue();
            typeDesc = clientService.getTypeInfo(typeName);

            if (action == NetSuiteOutputProperties.OutputAction.DELETE) {
                transducer = new NsObjectOutputTransducer(clientService, typeDesc.getTypeName(), true);
            } else {
                transducer = new NsObjectOutputTransducer(clientService, typeDesc.getTypeName());
            }

        } catch (NetSuiteException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(Object object) throws IOException {
        IndexedRecord record = (IndexedRecord) object;
        try {
            NsWriteResponse<?> writeResponse;
            if (action == NetSuiteOutputProperties.OutputAction.ADD) {
                writeResponse = clientService.add(transduceRecord(record));
            } else if (action == NetSuiteOutputProperties.OutputAction.UPDATE) {
                writeResponse = clientService.update(transduceRecord(record));
            } else if (action == NetSuiteOutputProperties.OutputAction.UPSERT) {
                writeResponse = clientService.upsert(transduceRecord(record));
            } else if (action == NetSuiteOutputProperties.OutputAction.DELETE) {
                writeResponse = clientService.delete(transduceRecord(record));
            } else {
                throw new NetSuiteException("Output operation not implemented: " + action);
            }
            if (writeResponse.getStatus().isSuccess()) {
                successfulWrites.add(record);
            } else {
                rejectedWrites.add(record);
            }
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
