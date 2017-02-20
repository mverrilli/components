package org.talend.components.netsuite.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.talend.components.netsuite.beans.PropertyAccessor;
import org.talend.components.netsuite.beans.EnumAccessor;

import com.netsuite.webservices.v2016_2.lists.accounting.Account;
import com.netsuite.webservices.v2016_2.lists.accounting.types.AccountType;

/**
 *
 */
public class NetSuiteFactoryOptimizedAccessorsTest {

    @Test
    public void testOptimizedEnumAccessor() throws Exception {
        EnumAccessor accessor = NetSuiteFactory.getEnumAccessor(AccountType.class);
        assertTrue(accessor instanceof NetSuiteFactory.OptimizedEnumAccessor);
    }

    @Test
    public void testOptimizedPropertyAccessor() throws Exception {
        PropertyAccessor accessor = NetSuiteFactory.getPropertyAccessor(Account.class);
        assertTrue(accessor instanceof NetSuiteFactory.OptimizedPropertyAccessor);
    }
}
