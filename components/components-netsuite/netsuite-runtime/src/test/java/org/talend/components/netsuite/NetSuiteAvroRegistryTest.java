package org.talend.components.netsuite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
        assertUnionType(f.schema(), Arrays.asList(Schema.Type.STRING, Schema.Type.NULL));
        assertThat(f.schema().getObjectProps().keySet(), empty());

        fieldInfo = entityInfo.getField("acctName");
        f = s.getField(fieldInfo.getName());
        assertUnionType(f.schema(), Arrays.asList(Schema.Type.STRING, Schema.Type.NULL));
        assertThat(f.schema().getObjectProps().keySet(), empty());

        fieldInfo = entityInfo.getField("inventory");
        f = s.getField(fieldInfo.getName());
        assertUnionType(f.schema(), Arrays.asList(Schema.Type.BOOLEAN, Schema.Type.NULL));
        assertThat(f.schema().getObjectProps().keySet(), empty());

        fieldInfo = entityInfo.getField("tranDate");
        f = s.getField(fieldInfo.getName());
        assertUnionType(f.schema(), Arrays.asList(Schema.Type.STRING, Schema.Type.NULL));
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
        assertEquals(AccountType.ACCOUNTS_PAYABLE.value(),
                converter1.convertToAvro(AccountType.ACCOUNTS_PAYABLE));
        assertEquals(AccountType.ACCOUNTS_PAYABLE,
                converter1.convertToDatum(AccountType.ACCOUNTS_PAYABLE.value()));

        fieldInfo = entityInfo.getField("generalRate");
        f = s.getField(fieldInfo.getName());
        AvroConverter<Enum<ConsolidatedRate>, String> converter2 =
                (AvroConverter<Enum<ConsolidatedRate>, String>) registry.getConverter(f, fieldInfo.getValueType());
        assertEquals(ConsolidatedRate.class, converter2.getDatumClass());
        assertEquals(ConsolidatedRate.HISTORICAL.value(),
                converter2.convertToAvro(ConsolidatedRate.HISTORICAL));
        assertEquals(ConsolidatedRate.HISTORICAL,
                converter2.convertToDatum(ConsolidatedRate.HISTORICAL.value()));
    }

    @Test
    public void testXMLGregorianCalendarConverter() throws Exception {
        NetSuiteMetaData.EntityInfo entityInfo = metaData.getEntity("Account");

        Schema s = registry.inferSchema(entityInfo);

        DateTimeZone tz1 = DateTimeZone.forID("EET");

        MutableDateTime dateTime1 = new MutableDateTime(tz1);
        dateTime1.setDate(System.currentTimeMillis());

        XMLGregorianCalendar xmlCalendar1 = registry.getDatatypeFactory().newXMLGregorianCalendar();
        xmlCalendar1.setYear(dateTime1.getYear());
        xmlCalendar1.setMonth(dateTime1.getMonthOfYear());
        xmlCalendar1.setDay(dateTime1.getDayOfMonth());
        xmlCalendar1.setHour(dateTime1.getHourOfDay());
        xmlCalendar1.setMinute(dateTime1.getMinuteOfHour());
        xmlCalendar1.setSecond(dateTime1.getSecondOfMinute());
        xmlCalendar1.setMillisecond(0);
        xmlCalendar1.setTimezone(tz1.toTimeZone().getRawOffset() / 60000);

        NetSuiteMetaData.FieldInfo fieldInfo = entityInfo.getField("tranDate");
        Schema.Field f = s.getField(fieldInfo.getName());

        DateTimeFormatter dateFormat = DateTimeFormat
                .forPattern(f.getProp(SchemaConstants.TALEND_COLUMN_PATTERN))
                .withZone(tz1);

        AvroConverter<XMLGregorianCalendar, String> converter1 =
                (AvroConverter<XMLGregorianCalendar, String>) registry.getConverter(f, fieldInfo.getValueType());
        assertEquals(XMLGregorianCalendar.class, converter1.getDatumClass());
        String controlValue1 = dateFormat.print(dateTime1);
        System.out.println(controlValue1);
        assertEquals(controlValue1,
                converter1.convertToAvro(xmlCalendar1));
        assertEquals(xmlCalendar1,
                converter1.convertToDatum(controlValue1));
    }

    private static void assertUnionType(Schema schema, List<Schema.Type> types) {
        assertThat(schema.getType(), is(Schema.Type.UNION));
        List<Schema> members = schema.getTypes();
        List<Schema.Type> memberTypes = new ArrayList<>(members.size());
        for (Schema member : members) {
            memberTypes.add(member.getType());
        }
        assertThat(types, is(memberTypes));
    }
}
