package org.talend.components.netsuite.output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.IndexedRecord;
import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.talend.components.api.component.runtime.Result;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.netsuite.NetSuiteAvroRegistry;
import org.talend.components.netsuite.NetSuiteSink;
import org.talend.components.netsuite.NsObjectIndexedRecordConverter;
import org.talend.components.netsuite.client.NetSuiteFactory;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.components.netsuite.client.NsObject;
import org.talend.components.netsuite.client.PropertyAccessor;
import org.talend.components.netsuite.client.impl.v2016_2.NetSuiteWebServiceMockTestFixture;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.model.TypeManager;
import org.talend.components.netsuite.runtime.RuntimeService;
import org.talend.components.netsuite.runtime.RuntimeServiceImpl;
import org.talend.components.netsuite.runtime.SchemaService;

import com.netsuite.webservices.v2016_2.lists.accounting.Account;
import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.RecordRef;
import com.netsuite.webservices.v2016_2.platform.core.Status;
import com.netsuite.webservices.v2016_2.platform.core.types.RecordType;
import com.netsuite.webservices.v2016_2.platform.messages.LoginRequest;
import com.netsuite.webservices.v2016_2.platform.messages.LoginResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SessionResponse;
import com.netsuite.webservices.v2016_2.platform.messages.UpdateRequest;
import com.netsuite.webservices.v2016_2.platform.messages.UpdateResponse;
import com.netsuite.webservices.v2016_2.platform.messages.WriteResponse;

/**
 *
 */
public class NetSuiteSearchOutputWriterTest {

    private static NetSuiteWebServiceMockTestFixture webServiceTestFixture;

    private static Random rnd = new Random(System.currentTimeMillis());

    @BeforeClass
    public static void classSetUp() throws Exception {
        webServiceTestFixture = new NetSuiteWebServiceMockTestFixture();
        webServiceTestFixture.setUp();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        if (webServiceTestFixture != null) {
            webServiceTestFixture.tearDown();
        }
    }

    @Test
    public void testUpdate() throws Exception {
        NetSuitePortType port = mock(NetSuitePortType.class);
        webServiceTestFixture.getPortImpl().setPort(port);

        final Status statusSuccess = new Status();
        statusSuccess.setIsSuccess(true);

        SessionResponse sessionResponse = new SessionResponse();
        sessionResponse.setStatus(statusSuccess);
        LoginResponse response = new LoginResponse();
        response.setSessionResponse(sessionResponse);

        when(port.login(any(LoginRequest.class))).thenReturn(response);

        final List<Account> updatedRecordList = new ArrayList<>();
        when(port.update(any(UpdateRequest.class))).then(new Answer<UpdateResponse>() {
            @Override public UpdateResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                UpdateRequest request = (UpdateRequest) invocationOnMock.getArguments()[0];
                Account record = (Account) request.getRecord();
                assertNotNull(record);
                assertNotNull(record.getInternalId());

                RecordRef recordRef = new RecordRef();
                recordRef.setInternalId(record.getInternalId());
                recordRef.setType(RecordType.ACCOUNT);

                updatedRecordList.add(record);

                UpdateResponse response = new UpdateResponse();
                WriteResponse writeResponse = new WriteResponse();
                writeResponse.setStatus(statusSuccess);
                writeResponse.setBaseRef(recordRef);
                response.setWriteResponse(writeResponse);
                return response;
            }
        });

        RuntimeContainer container = mock(RuntimeContainer.class);

        NetSuiteOutputProperties properties = new NetSuiteOutputProperties("NetSuite");
        properties.init();
        properties.connection.endpoint.setValue(webServiceTestFixture.getEndpointAddress().toString());
        properties.connection.email.setValue("test@test.com");
        properties.connection.password.setValue("123");
        properties.connection.role.setValue(3);
        properties.connection.account.setValue("test");
        properties.connection.applicationId.setValue("00000000-0000-0000-0000-000000000000");
        properties.module.moduleName.setValue("Account");
        properties.action.setValue(NetSuiteOutputProperties.OutputAction.UPDATE);

        RuntimeService runtimeService = new RuntimeServiceImpl();
        SchemaService schemaService = runtimeService.getSchemaService(properties.getConnectionProperties());

        Schema schema = schemaService.getSchema(properties.module.moduleName.getValue());

        properties.module.main.schema.setValue(schema);

        NetSuiteSink sink = new NetSuiteSink();
        sink.initialize(container, properties);

        NetSuiteMetaData metaData = sink.getConnection().getMetaData();

        NetSuiteWriteOperation writeOperation = (NetSuiteWriteOperation) sink.createWriteOperation();
        NetSuiteOutputWriter writer = (NetSuiteOutputWriter) writeOperation.createWriter(container);
        writer.open(UUID.randomUUID().toString());

        List<IndexedRecord> recordList = makeRecords(metaData, schema, 150);
        for (IndexedRecord record : recordList) {
            writer.write(record);
        }

        Result writerResult = writer.close();
        assertNotNull(writerResult);
        assertEquals(recordList.size(), writerResult.totalCount);

        verify(port, times(recordList.size())).update(any(UpdateRequest.class));
        assertEquals(recordList.size(), updatedRecordList.size());
    }

    private List<IndexedRecord> makeRecords(NetSuiteMetaData metaData, Schema schema, int count) throws Exception {
        NsObjectIndexedRecordConverter converter = new NsObjectIndexedRecordConverter(metaData);
        converter.setSchema(schema);

        List<IndexedRecord> recordList = new ArrayList<>();

        while (count > 0) {
            Account nsRecord = composeObject(Account.class);

            IndexedRecord convertedRecord = converter.convertToAvro(NsObject.wrap(nsRecord));

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

    private <T> T composeObject(Class<T> clazz) throws Exception {
        TypeInfo typeInfo = TypeManager.forClass(clazz);
        List<PropertyInfo> propertyInfoList = typeInfo.getProperties();

        T obj = clazz.newInstance();

        PropertyAccessor accessor = NetSuiteFactory.getPropertyAccessor(clazz);

        for (PropertyInfo propertyInfo : propertyInfoList) {
            if (propertyInfo.getWriteType() != null) {
                Object value = composeValue(propertyInfo.getWriteType());
                accessor.set(obj, propertyInfo.getName(), value);
            }
        }

        return obj;
    }

    private static Object composeValue(Class<?> clazz) throws Exception {
        if (clazz == Boolean.class) {
            return Boolean.valueOf(rnd.nextBoolean());
        }
        if (clazz == Long.class) {
            return Long.valueOf(rnd.nextLong());
        }
        if (clazz == Double.class) {
            return Double.valueOf(rnd.nextLong());
        }
        if (clazz == Integer.class) {
            return Integer.valueOf(rnd.nextInt());
        }
        if (clazz == String.class) {
            int len = 10 + rnd.nextInt(100);
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                sb.append((char) (32 + rnd.nextInt(127 - 32)));
            }
            return sb.toString();
        }
        if (clazz == XMLGregorianCalendar.class) {
            return composeDateTime();
        }
        if (clazz.isEnum()) {
            Object[] values = clazz.getEnumConstants();
            return values[rnd.nextInt(values.length)];
        }
        return null;
    }

    private static XMLGregorianCalendar composeDateTime() throws Exception {
        DateTime dateTime = DateTime.now();

        XMLGregorianCalendar xts = NetSuiteAvroRegistry.getInstance().getDatatypeFactory().newXMLGregorianCalendar();
        xts.setYear(dateTime.getYear());
        xts.setMonth(dateTime.getMonthOfYear());
        xts.setDay(dateTime.getDayOfMonth());
        xts.setHour(dateTime.getHourOfDay());
        xts.setMinute(dateTime.getMinuteOfHour());
        xts.setSecond(dateTime.getSecondOfMinute());
        xts.setMillisecond(dateTime.getMillisOfSecond());
        xts.setTimezone(dateTime.getZone().toTimeZone().getRawOffset() / 60000);

        return xts;

    }

}
