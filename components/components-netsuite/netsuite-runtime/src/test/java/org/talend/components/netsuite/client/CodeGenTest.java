package org.talend.components.netsuite.client;

import java.util.Collection;

import org.apache.commons.beanutils.MethodUtils;
import org.junit.Test;
import org.talend.components.netsuite.model.EnumAccessor;
import org.talend.components.netsuite.model.PropertyAccess;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.model.TypeManager;

import com.netsuite.webservices.v2016_2.lists.accounting.types.AccountType;
import com.netsuite.webservices.v2016_2.lists.accounting.Account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class CodeGenTest {

    @Test
    public void testMetaData() throws Exception {
        TypeInfo md = TypeManager.forClass(Account.class);

        Collection<PropertyInfo> properties = md.getProperties();

        assertNotNull(properties);
        assertFalse(properties.isEmpty());

        for (PropertyInfo pmd : properties) {
            assertNotNull(pmd);
            assertNotNull(pmd.getName());
            assertNotNull(pmd.getReadType());
            assertNotNull(pmd.getReadMethodName());
            if (!pmd.getName().equals("class")) {
                assertNotNull(pmd.getWriteType());
                assertNotNull(pmd.getWriteMethodName());
            }
        }
    }

    @Test
    public void testPropertyAccess() throws Exception {
        Account account = new Account();
        account.setEliminate(true);
        account.setCurDocNum(123456789L);

        PropertyAccess bean = (PropertyAccess) account;

        assertEquals(Boolean.TRUE, bean.get("eliminate"));
        bean.set("eliminate", Boolean.FALSE);
        assertEquals(Boolean.FALSE, bean.get("eliminate"));

        assertEquals(Long.valueOf(123456789L), bean.get("curDocNum"));
    }

    @Test
    public void testEnumAccessor() throws Exception {
        EnumAccessor accessor = (EnumAccessor) MethodUtils.invokeExactStaticMethod(
                AccountType.class, "getEnumAccessor", null);

        String value1 = accessor.mapToString(AccountType.BANK);
        assertEquals(AccountType.BANK.value(), value1);

        Enum enumValue1 = accessor.mapFromString(AccountType.BANK.value());
        assertEquals(AccountType.BANK, enumValue1);
    }
}
