package org.talend.components.salesforce.runtime.dataprep;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.junit.Assert;
import org.junit.Test;
import org.talend.components.salesforce.dataset.SalesforceDatasetProperties;
import org.talend.components.salesforce.datastore.SalesforceDatastoreDefinition;
import org.talend.components.salesforce.datastore.SalesforceDatastoreProperties;
import org.talend.daikon.java8.Consumer;

public class SalesforceDatasetRuntimeTestIT {

    @Test
    public void testGetSchemaForModule() {
        SalesforceDatasetProperties dataset = createDatasetPropertiesForModule();

        SalesforceDatasetRuntime runtime = new SalesforceDatasetRuntime();
        runtime.initialize(null, dataset);
        Schema schema = runtime.getSchema();

        Assert.assertNotNull(schema);
        Assert.assertTrue("empty schema", schema.getFields().size() > 0);
    }

    @Test
    public void testGetSchemaForQuery() {
        SalesforceDatasetProperties dataset = createDatasetPropertiesForQuery();

        SalesforceDatasetRuntime runtime = new SalesforceDatasetRuntime();
        runtime.initialize(null, dataset);
        Schema schema = runtime.getSchema();

        Assert.assertNotNull(schema);
        Assert.assertTrue("empty schema", schema.getFields().size() > 0);
    }

    @Test
    public void testGetSampleForModule() {
        SalesforceDatasetProperties dataset = createDatasetPropertiesForModule();
        getSampleAction(dataset);
    }

    @Test
    public void testGetSampleForQuery() {
        SalesforceDatasetProperties dataset = createDatasetPropertiesForQuery();
        getSampleAction(dataset);
    }

    private void getSampleAction(SalesforceDatasetProperties dataset) {
        SalesforceDatasetRuntime runtime = new SalesforceDatasetRuntime();
        runtime.initialize(null, dataset);
        final IndexedRecord[] record = new IndexedRecord[1];
        Consumer<IndexedRecord> storeTheRecords = new Consumer<IndexedRecord>() {

            @Override
            public void accept(IndexedRecord data) {
                record[0] = data;
            }
        };

        runtime.getSample(1, storeTheRecords);
        Assert.assertTrue("empty result", record.length > 0);
    }

    private SalesforceDatasetProperties createDatasetPropertiesForModule() {
        SalesforceDatastoreDefinition def = new SalesforceDatastoreDefinition();
        SalesforceDatastoreProperties datastore = new SalesforceDatastoreProperties("datastore");

        CommonTestUtils.setValueForDatastoreProperties(datastore);

        SalesforceDatasetProperties dataset = (SalesforceDatasetProperties) def.createDatasetProperties(datastore);
        dataset.moduleName.setValue("Account");

        return dataset;
    }

    private SalesforceDatasetProperties createDatasetPropertiesForQuery() {
        SalesforceDatastoreDefinition def = new SalesforceDatastoreDefinition();
        SalesforceDatastoreProperties datastore = new SalesforceDatastoreProperties("datastore");

        CommonTestUtils.setValueForDatastoreProperties(datastore);

        SalesforceDatasetProperties dataset = (SalesforceDatasetProperties) def.createDatasetProperties(datastore);
        dataset.query.setValue("SELECT Id, Name FROM Account");

        return dataset;
    }
}
