package org.talend.components.netsuite.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.talend.components.api.component.ComponentDefinition;
import org.talend.components.netsuite.NetSuiteMockTestBase;
import org.talend.components.netsuite.NetSuiteComponentMockTestFixture;
import org.talend.components.netsuite.NetSuiteSource;
import org.talend.components.netsuite.RuntimeService;
import org.talend.components.netsuite.RuntimeServiceImpl;
import org.talend.components.netsuite.SchemaService;
import org.talend.components.netsuite.beans.Mapper;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.model.BeanUtils;
import org.talend.components.netsuite.client.model.FieldDesc;
import org.talend.components.netsuite.client.model.TypeDesc;

import com.netsuite.webservices.v2016_2.lists.accounting.Account;
import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.SearchResult;
import com.netsuite.webservices.v2016_2.platform.messages.SearchMoreWithIdRequest;
import com.netsuite.webservices.v2016_2.platform.messages.SearchMoreWithIdResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SearchRequest;
import com.netsuite.webservices.v2016_2.platform.messages.SearchResponse;

/**
 *
 */
public class NetSuiteSearchInputReaderTest extends NetSuiteMockTestBase {
    private static NetSuiteComponentMockTestFixture mockTestFixture;

    @BeforeClass
    public static void classSetUp() throws Exception {
        mockTestFixture = new NetSuiteComponentMockTestFixture();
        classScopedTestFixtures.add(mockTestFixture);
        setUpClassScopedTestFixtures();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        tearDownClassScopedTestFixtures();
    }

    @Test
    public void testInput() throws Exception {
        final NetSuitePortType port = mockTestFixture.getPortMock();

        NetSuiteInputProperties properties = new NetSuiteInputProperties("NetSuite");
        properties.init();
        properties.connection.copyValuesFrom(mockTestFixture.getConnectionProperties());
        properties.module.moduleName.setValue("Account");

        RuntimeService runtimeService = new RuntimeServiceImpl();
        SchemaService schemaService = runtimeService.getSchemaService(properties.getConnectionProperties());

        Schema schema = schemaService.getSchema(properties.module.moduleName.getValue());

        properties.module.main.schema.setValue(schema);

        NetSuiteSource source = new NetSuiteSource();
        source.initialize(mockTestFixture.getRuntimeContainer(), properties);

        final List<SearchResult> pageResults = makeRecordPages(Account.class, 150, 100);
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

        NetSuiteClientService clientService = source.getClientService();
        TypeDesc entityInfo = clientService.getTypeInfo(Account.class);

        NetSuiteSearchInputReader reader = (NetSuiteSearchInputReader) source.createReader(
                mockTestFixture.getRuntimeContainer());

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
                FieldDesc fieldDesc = entityInfo.getField(field.name());
                Class<?> datumClass = fieldDesc.getValueType();

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
                    Mapper<String, Enum> enumAccessor = BeanUtils.getEnumFromStringMapper((Class<Enum>) datumClass);
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

}
