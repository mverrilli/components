package org.talend.components.netsuite.v2016_2.output;

import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.IndexedRecord;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.v2016_2.NetSuiteMockTestBase;
import org.talend.components.netsuite.input.NsObjectInputTransducer;

/**
 *
 */
public abstract class NetSuiteOutputMockTestBase extends NetSuiteMockTestBase {

    protected <T> List<IndexedRecord> makeIndexedRecords(
            NetSuiteClientService clientService, Schema schema,
            ObjectComposer<T> objectComposer, int count) throws Exception {

        NsObjectInputTransducer transducer = new NsObjectInputTransducer(clientService, schema, schema.getName());

        List<IndexedRecord> recordList = new ArrayList<>();

        while (count > 0) {
            T nsRecord = objectComposer.composeObject();

            IndexedRecord convertedRecord = transducer.read(nsRecord);
            Schema recordSchema = convertedRecord.getSchema();

            GenericRecord record = new GenericData.Record(recordSchema);
            for (Schema.Field field : schema.getFields()) {
                Object value = convertedRecord.get(field.pos());
                record.put(field.pos(), value);
            }

            recordList.add(record);

            count--;
        }

        return recordList;
    }

}
