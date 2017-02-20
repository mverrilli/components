package org.talend.components.netsuite.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.talend.components.api.component.ComponentDefinition;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.netsuite.NetSuiteAvroRegistry;
import org.talend.components.netsuite.NetSuiteSource;
import org.talend.components.netsuite.beans.BeanInfo;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteFactory;
import org.talend.components.netsuite.client.model.TypeInfo;
import org.talend.components.netsuite.client.model.FieldInfo;
import org.talend.components.netsuite.beans.PropertyAccessor;
import org.talend.components.netsuite.client.NetSuiteWebServiceMockTestFixture;
import org.talend.components.netsuite.beans.Mapper;
import org.talend.components.netsuite.beans.PropertyInfo;
import org.talend.components.netsuite.beans.BeanManager;
import org.talend.components.netsuite.runtime.RuntimeService;
import org.talend.components.netsuite.runtime.RuntimeServiceImpl;
import org.talend.components.netsuite.runtime.SchemaService;

import com.netsuite.webservices.v2016_2.lists.accounting.Account;
import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.RecordList;
import com.netsuite.webservices.v2016_2.platform.core.SearchResult;
import com.netsuite.webservices.v2016_2.platform.core.Status;
import com.netsuite.webservices.v2016_2.platform.messages.LoginRequest;
import com.netsuite.webservices.v2016_2.platform.messages.LoginResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SearchMoreWithIdRequest;
import com.netsuite.webservices.v2016_2.platform.messages.SearchMoreWithIdResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SearchRequest;
import com.netsuite.webservices.v2016_2.platform.messages.SearchResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SessionResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
public class NetSuiteSearchInputReaderTest {

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
    public void testInput() throws Exception {
        final NetSuitePortType port = webServiceTestFixture.getPortMock();

        SessionResponse sessionResponse = new SessionResponse();
        Status status = new Status();
        status.setIsSuccess(true);
        sessionResponse.setStatus(status);
        LoginResponse response = new LoginResponse();
        response.setSessionResponse(sessionResponse);

        when(port.login(any(LoginRequest.class))).thenReturn(response);

        RuntimeContainer container = mock(RuntimeContainer.class);

        NetSuiteInputProperties properties = new NetSuiteInputProperties("NetSuite");
        properties.init();
        properties.connection.endpoint.setValue(webServiceTestFixture.getEndpointAddress().toString());
        properties.connection.email.setValue("test@test.com");
        properties.connection.password.setValue("123");
        properties.connection.role.setValue(3);
        properties.connection.account.setValue("test");
        properties.connection.applicationId.setValue("00000000-0000-0000-0000-000000000000");
        properties.module.moduleName.setValue("Account");

        RuntimeService runtimeService = new RuntimeServiceImpl();
        SchemaService schemaService = runtimeService.getSchemaService(properties.getConnectionProperties());

        Schema schema = schemaService.getSchema(properties.module.moduleName.getValue());

        properties.module.main.schema.setValue(schema);

        NetSuiteSource source = new NetSuiteSource();
        source.initialize(container, properties);

        final List<SearchResult> pageResults = makeRecordPages(150, 100);
        when(port.search(any(SearchRequest.class))).then(new Answer<SearchResponse>() {
            @Override public SearchResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                SearchResponse response = new SearchResponse();
                response.setSearchResult(pageResults.get(0));
                return response;
            }
        });
        when(port.searchMoreWithId(any(SearchMoreWithIdRequest.class))).then(new Answer<SearchMoreWithIdResponse>() {
            @Override public SearchMoreWithIdResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                SearchMoreWithIdRequest request = (SearchMoreWithIdRequest) invocationOnMock.getArguments()[0];
                SearchMoreWithIdResponse response = new SearchMoreWithIdResponse();
                response.setSearchResult(pageResults.get(request.getPageIndex() - 1));
                return response;
            }
        });

        NetSuiteClientService clientService = source.getConnection();
        TypeInfo entityInfo = clientService.getTypeInfo(Account.class);

        NetSuiteSearchInputReader reader = (NetSuiteSearchInputReader) source.createReader(container);

        boolean started = reader.start();
        assertTrue(started);

        IndexedRecord record = reader.getCurrent();
        assertNotNull(record);

        while (reader.advance()) {
            record = reader.getCurrent();
            assertNotNull(record);

            List<Schema.Field> fields = record.getSchema().getFields();
            for (int i = 0; i < fields.size(); i++) {
                Schema.Field field = fields.get(i);
                FieldInfo fieldInfo = entityInfo.getField(field.name());
                Class<?> datumClass = fieldInfo.getValueType();

                Object value = record.get(i);

                if (datumClass == Boolean.class) {
                    assertNotNull(value);
                }
                if (datumClass == Integer.class) {
                    assertNotNull(value);
                }
                if (datumClass == Long.class) {
                    assertNotNull(value);
                }
                if (datumClass == Double.class) {
                    assertNotNull(value);
                }
                if (datumClass == String.class) {
                    assertNotNull(value);
                }
                if (datumClass == XMLGregorianCalendar.class) {
                    assertNotNull(value);
                }
                if (datumClass.isEnum()) {
                    assertNotNull(value);
                    Mapper<String, Enum> enumAccessor = NetSuiteFactory.getEnumFromStringMapper((Class<Enum>) datumClass);
                    Enum modelValue = enumAccessor.map((String) value);
                    assertNotNull(modelValue);
                }
//                System.out.println(fields.get(i) + ": " + value);
            }
        }

        Map<String, Object> readerResult = reader.getReturnValues();
        assertNotNull(readerResult);

        assertEquals(150, readerResult.get(ComponentDefinition.RETURN_TOTAL_RECORD_COUNT));
    }

    private List<SearchResult> makeRecordPages(int count, int pageSize) throws Exception {
        int totalPages = count / pageSize;
        if (count % pageSize != 0) {
            totalPages += 1;
        }

        String searchId = UUID.randomUUID().toString();

        Status status = new Status();
        status.setIsSuccess(true);

        List<SearchResult> pageResults = new ArrayList<>();
        SearchResult result = null;
        while (count > 0) {
            Account record = composeObject(Account.class);

            if (result == null) {
                result = new SearchResult();
                result.setSearchId(searchId);
                result.setTotalPages(totalPages);
                result.setTotalRecords(count);
                result.setPageIndex(pageResults.size() + 1);
                result.setPageSize(pageSize);
                result.setStatus(status);
            }

            if (result.getRecordList() == null) {
                result.setRecordList(new RecordList());
            }
            result.getRecordList().getRecord().add(record);

            if (result.getRecordList().getRecord().size() == pageSize) {
                pageResults.add(result);
                result = null;
            }

            count--;
        }

        if (result != null) {
            pageResults.add(result);
        }

        return pageResults;
    }

    private <T> T composeObject(Class<T> clazz) throws Exception {
        BeanInfo beanInfo = BeanManager.getBeanInfo(clazz);
        List<PropertyInfo> propertyInfoList = beanInfo.getProperties();

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
