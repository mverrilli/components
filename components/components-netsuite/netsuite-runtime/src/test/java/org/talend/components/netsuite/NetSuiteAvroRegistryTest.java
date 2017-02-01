package org.talend.components.netsuite;

import org.apache.avro.Schema;
import org.junit.Test;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.components.netsuite.client.impl.v2016_2.NetSuiteMetaDataImpl;
import org.talend.daikon.avro.SchemaConstants;
import org.talend.daikon.avro.converter.AvroConverter;

import com.netsuite.webservices.v2016_2.lists.accounting.types.AccountType;
import com.netsuite.webservices.v2016_2.lists.accounting.types.ConsolidatedRate;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class NetSuiteAvroRegistryTest {

    private NetSuiteMetaData metaData = new NetSuiteMetaDataImpl();
    private NetSuiteAvroRegistry registry = NetSuiteAvroRegistry.getInstance();

    @Test
    public void testInferSchemaForEntity() throws Exception {
        NetSuiteMetaData.EntityInfo entityInfo = metaData.getEntity("Account");

        Schema s = registry.inferSchema(entityInfo);

        System.out.println(s);

        assertThat(s.getType(), is(Schema.Type.RECORD));
        assertThat(s.getName(), is("Account"));
        assertThat(s.getFields(), hasSize(entityInfo.getFields().size()));
        assertThat(s.getObjectProps().keySet(), empty());

        NetSuiteMetaData.FieldInfo fieldInfo = entityInfo.getField("acctType");
        Schema.Field f = s.getField(fieldInfo.getName());
        assertThat(f.schema().getType(), is(Schema.Type.STRING));
        assertThat(f.schema().getObjectProps().keySet(), empty());

        fieldInfo = entityInfo.getField("acctName");
        f = s.getField(fieldInfo.getName());
        assertThat(f.schema().getType(), is(Schema.Type.STRING));
        assertThat(f.schema().getObjectProps().keySet(), empty());

        fieldInfo = entityInfo.getField("inventory");
        f = s.getField(fieldInfo.getName());
        assertThat(f.schema().getType(), is(Schema.Type.BOOLEAN));
        assertThat(f.schema().getObjectProps().keySet(), empty());

        fieldInfo = entityInfo.getField("tranDate");
        f = s.getField(fieldInfo.getName());
        assertThat(f.schema().getType(), is(Schema.Type.STRING));
        assertThat(f.getObjectProps().keySet(), containsInAnyOrder(
                SchemaConstants.TALEND_COLUMN_PATTERN));
        assertThat(f.getProp(SchemaConstants.TALEND_COLUMN_PATTERN), is("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
    }

    @Test
    public void testEnumConverter() throws Exception {
        NetSuiteMetaData.EntityInfo entityInfo = metaData.getEntity("Account");

        Schema s = registry.inferSchema(entityInfo);

        NetSuiteMetaData.FieldInfo fieldInfo = entityInfo.getField("acctType");
        Schema.Field f = s.getField(fieldInfo.getName());
        AvroConverter<Enum<AccountType>, String> converter1 =
                (AvroConverter<Enum<AccountType>, String>) registry.getConverter(f, fieldInfo.getValueType());
        assertEquals(AccountType.class, converter1.getDatumClass());
        assertEquals(AccountType.ACCOUNTS_PAYABLE.name(),
                converter1.convertToAvro(AccountType.ACCOUNTS_PAYABLE));
        assertEquals(AccountType.ACCOUNTS_PAYABLE,
                converter1.convertToDatum(AccountType.ACCOUNTS_PAYABLE.name()));

        fieldInfo = entityInfo.getField("generalRate");
        f = s.getField(fieldInfo.getName());
        AvroConverter<Enum<ConsolidatedRate>, String> converter2 =
                (AvroConverter<Enum<ConsolidatedRate>, String>) registry.getConverter(f, fieldInfo.getValueType());
        assertEquals(ConsolidatedRate.class, converter2.getDatumClass());
        assertEquals(ConsolidatedRate.HISTORICAL.name(),
                converter2.convertToAvro(ConsolidatedRate.HISTORICAL));
        assertEquals(ConsolidatedRate.HISTORICAL,
                converter2.convertToDatum(ConsolidatedRate.HISTORICAL.name()));
    }
}
