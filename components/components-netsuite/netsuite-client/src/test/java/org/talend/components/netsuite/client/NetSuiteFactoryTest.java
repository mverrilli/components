package org.talend.components.netsuite.client;

import org.junit.Test;

import com.netsuite.webservices.v2016_2.lists.accounting.Account;

import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class NetSuiteFactoryTest {

    @Test
    public void testGetMetaData() throws Exception {
        NetSuiteMetaData metaData = NetSuiteFactory.getMetaData("2016.2");
        assertNotNull(metaData.getEntity(Account.class));
    }

}
