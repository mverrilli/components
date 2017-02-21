package org.talend.components.netsuite.client.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.talend.components.netsuite.beans.PropertyAccessor;
import org.talend.components.netsuite.beans.EnumAccessor;
import org.talend.components.netsuite.client.model.BeanUtils;

import com.netsuite.webservices.v2016_2.lists.accounting.Account;
import com.netsuite.webservices.v2016_2.lists.accounting.types.AccountType;

/**
 *
 */
public class OptimizedAccessorsTest {

    @Test
    public void testOptimizedEnumAccessor() throws Exception {
        EnumAccessor accessor = BeanUtils.getEnumAccessor(AccountType.class);
        assertTrue(accessor instanceof BeanUtils.OptimizedEnumAccessor);
    }

    @Test
    public void testOptimizedPropertyAccessor() throws Exception {
        PropertyAccessor accessor = BeanUtils.getPropertyAccessor(Account.class);
        assertTrue(accessor instanceof BeanUtils.OptimizedPropertyAccessor);
    }
}
