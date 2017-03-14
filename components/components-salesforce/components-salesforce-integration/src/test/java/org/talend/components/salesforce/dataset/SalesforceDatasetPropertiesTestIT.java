package org.talend.components.salesforce.dataset;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.talend.components.salesforce.dataset.SalesforceDatasetProperties.SourceType;
import org.talend.components.salesforce.datastore.SalesforceDatastoreProperties;
import org.talend.components.salesforce.integration.SalesforceTestBase;

public class SalesforceDatasetPropertiesTestIT extends SalesforceTestBase {

    @Test
    public void testAfterSourceType() {
        SalesforceDatasetProperties dataset = new SalesforceDatasetProperties("dataset");
        dataset.init();
        
        SalesforceDatastoreProperties datastore = new SalesforceDatastoreProperties("datastore");
        datastore.init();
        
        dataset.setDatastoreProperties(datastore);
        
        datastore.userId.setValue(userId);
        datastore.password.setValue(password);
        datastore.securityKey.setValue(securityKey);
        
        dataset.sourceType.setValue(SourceType.MODULE_SELECTION);
        
        List<String> modules = (List<String>)dataset.moduleName.getPossibleValues();
        Assert.assertTrue("the module list is not empty before calling 'afterSourceType' method, not right", modules==null || modules.isEmpty());
        
        try {
            dataset.afterSourceType();
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
        
        modules = (List<String>)dataset.moduleName.getPossibleValues();
        Assert.assertTrue("the module list is empty after calling 'afterSourceType' method, not right", modules!=null && !modules.isEmpty());
    }
    
}
