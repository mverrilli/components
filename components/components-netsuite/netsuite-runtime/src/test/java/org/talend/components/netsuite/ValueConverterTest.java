package org.talend.components.netsuite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.model.FieldDesc;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.input.NsObjectInputTransducer;

import com.netsuite.webservices.v2016_2.lists.accounting.types.AccountType;
import com.netsuite.webservices.v2016_2.lists.accounting.types.ConsolidatedRate;

/**
 *
 */
public class ValueConverterTest extends NetSuiteMockTestBase {

    private NetSuiteClientService clientService = NetSuiteClientService.create("2016.2");

    @Test
    public void testEnumConverter() throws Exception {
        TypeDesc typeDesc = clientService.getBasicTypeInfo("Account");

        Schema s = SchemaServiceImpl.inferSchemaForRecord(typeDesc.getTypeName(), typeDesc.getFields());

        NsObjectInputTransducer transducer = new NsObjectInputTransducer(clientService, s);

        FieldDesc fieldDesc = typeDesc.getField("AcctType");
        NsObjectTransducer.ValueConverter<Enum<AccountType>, String> converter1 =
                (NsObjectTransducer.ValueConverter<Enum<AccountType>, String>) transducer.getValueConverter(fieldDesc);
        assertEquals(AccountType.ACCOUNTS_PAYABLE.value(),
                converter1.convertInput(AccountType.ACCOUNTS_PAYABLE));
        assertEquals(AccountType.ACCOUNTS_PAYABLE,
                converter1.convertOutput(AccountType.ACCOUNTS_PAYABLE.value()));

        fieldDesc = typeDesc.getField("GeneralRate");
        assertNotNull(fieldDesc);
        NsObjectTransducer.ValueConverter<Enum<ConsolidatedRate>, String> converter2 =
                (NsObjectTransducer.ValueConverter<Enum<ConsolidatedRate>, String>) transducer.getValueConverter(fieldDesc);
        assertEquals(ConsolidatedRate.HISTORICAL.value(),
                converter2.convertInput(ConsolidatedRate.HISTORICAL));
        assertEquals(ConsolidatedRate.HISTORICAL,
                converter2.convertOutput(ConsolidatedRate.HISTORICAL.value()));
    }

    @Test
    public void testXMLGregorianCalendarConverter() throws Exception {
        TypeDesc typeDesc = clientService.getBasicTypeInfo("Account");

        Schema s = SchemaServiceImpl.inferSchemaForRecord(typeDesc.getTypeName(), typeDesc.getFields());

        DateTimeZone tz1 = DateTimeZone.forID("EET");

        MutableDateTime dateTime1 = new MutableDateTime(tz1);
        dateTime1.setDate(System.currentTimeMillis());
        Long controlValue1 = dateTime1.getMillis();

        XMLGregorianCalendar xmlCalendar1 = datatypeFactory.newXMLGregorianCalendar();
        xmlCalendar1.setYear(dateTime1.getYear());
        xmlCalendar1.setMonth(dateTime1.getMonthOfYear());
        xmlCalendar1.setDay(dateTime1.getDayOfMonth());
        xmlCalendar1.setHour(dateTime1.getHourOfDay());
        xmlCalendar1.setMinute(dateTime1.getMinuteOfHour());
        xmlCalendar1.setSecond(dateTime1.getSecondOfMinute());
        xmlCalendar1.setMillisecond(dateTime1.getMillisOfSecond());
        xmlCalendar1.setTimezone(tz1.toTimeZone().getRawOffset() / 60000);

        FieldDesc fieldInfo = typeDesc.getField("TranDate");
        Schema.Field f = s.getField(fieldInfo.getName());
        assertNotNull(f);

        NsObjectInputTransducer transducer = new NsObjectInputTransducer(clientService, s);

        NsObjectTransducer.ValueConverter<XMLGregorianCalendar, Long> converter1 =
                (NsObjectTransducer.ValueConverter<XMLGregorianCalendar, Long>) transducer.getValueConverter(fieldInfo);
        System.out.println(controlValue1);
        assertEquals(controlValue1,
                converter1.convertInput(xmlCalendar1));
        assertEquals(xmlCalendar1,
                converter1.convertOutput(controlValue1));
    }
}
