package org.talend.components.netsuite.v2014_2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.talend.components.netsuite.client.model.beans.Beans.getProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.model.CustomFieldDesc;
import org.talend.components.netsuite.client.model.FieldDesc;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.client.model.TypeUtils;
import org.talend.components.netsuite.client.model.beans.BeanInfo;
import org.talend.components.netsuite.client.model.beans.Beans;
import org.talend.components.netsuite.client.model.beans.PropertyInfo;
import org.talend.components.netsuite.client.model.customfield.CustomFieldRefType;
import org.talend.components.netsuite.util.Mapper;

import com.netsuite.webservices.v2014_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2014_2.platform.core.BaseRef;
import com.netsuite.webservices.v2014_2.platform.core.CustomFieldList;
import com.netsuite.webservices.v2014_2.platform.core.CustomFieldRef;
import com.netsuite.webservices.v2014_2.platform.core.CustomizationRef;
import com.netsuite.webservices.v2014_2.platform.core.CustomizationRefList;
import com.netsuite.webservices.v2014_2.platform.core.CustomizationType;
import com.netsuite.webservices.v2014_2.platform.core.GetCustomizationIdResult;
import com.netsuite.webservices.v2014_2.platform.core.Record;
import com.netsuite.webservices.v2014_2.platform.core.SearchResult;
import com.netsuite.webservices.v2014_2.platform.core.types.RecordType;
import com.netsuite.webservices.v2014_2.platform.messages.GetCustomizationIdRequest;
import com.netsuite.webservices.v2014_2.platform.messages.GetCustomizationIdResponse;
import com.netsuite.webservices.v2014_2.platform.messages.GetListRequest;
import com.netsuite.webservices.v2014_2.platform.messages.GetListResponse;
import com.netsuite.webservices.v2014_2.platform.messages.ReadResponse;
import com.netsuite.webservices.v2014_2.platform.messages.ReadResponseList;
import com.netsuite.webservices.v2014_2.platform.messages.SearchMoreWithIdRequest;
import com.netsuite.webservices.v2014_2.platform.messages.SearchMoreWithIdResponse;
import com.netsuite.webservices.v2014_2.platform.messages.SearchRequest;
import com.netsuite.webservices.v2014_2.platform.messages.SearchResponse;
import com.netsuite.webservices.v2014_2.setup.customization.CustomFieldType;
import com.netsuite.webservices.v2014_2.setup.customization.types.CustomizationFieldType;

/**
 *
 */
public abstract class NetSuiteMockTestBase extends NetSuiteTestBase {
    protected static NetSuiteWebServiceMockTestFixture webServiceMockTestFixture;
    protected NetSuiteComponentMockTestFixture mockTestFixture;

    protected static void installWebServiceTestFixture() throws Exception {
        webServiceMockTestFixture = new NetSuiteWebServiceMockTestFixture();
        classScopedTestFixtures.add(webServiceMockTestFixture);
    }

    protected void installMockTestFixture() throws Exception {
        mockTestFixture = new NetSuiteComponentMockTestFixture(webServiceMockTestFixture);
        mockTestFixture.setReinstall(true);
        testFixtures.add(mockTestFixture);
    }

    protected void assertIndexedRecord(TypeDesc typeDesc, IndexedRecord indexedRecord) throws Exception {
        assertNotNull(indexedRecord);

        Schema recordSchema = indexedRecord.getSchema();
        assertEquals(typeDesc.getFields().size(), recordSchema.getFields().size());

        for (FieldDesc fieldDesc : typeDesc.getFields()) {
            String fieldName = fieldDesc.getName();
            Schema.Field field = recordSchema.getField(fieldName);
            assertNotNull(field);

            Object value = indexedRecord.get(field.pos());

            if (fieldDesc instanceof CustomFieldDesc) {
                CustomFieldDesc customFieldDesc = fieldDesc.asCustom();
                switch (customFieldDesc.getCustomFieldType()) {
                case BOOLEAN:
                case LONG:
                case DOUBLE:
                case STRING:
                case DATE:
                    assertNotNull(value);
                    break;
                }
            } else {
                Class<?> datumClass = fieldDesc.getValueType();

                if (datumClass == Boolean.class ||
                        datumClass == Long.class ||
                        datumClass == Double.class ||
                        datumClass == String.class ||
                        datumClass == XMLGregorianCalendar.class) {
                    assertNotNull(value);
                } else if (datumClass.isEnum()) {
                    assertNotNull(value);
                    Mapper<String, Enum> enumAccessor = Beans.getEnumFromStringMapper((Class<Enum>) datumClass);
                    Enum modelValue = enumAccessor.map((String) value);
                    assertNotNull(modelValue);
                }
            }
        }
    }

    protected void assertNsObject(TypeDesc typeDesc, Object nsObject) throws Exception {
        assertNotNull(nsObject);
        assertEquals(typeDesc.getTypeClass(), nsObject.getClass());

        for (FieldDesc fieldDesc : typeDesc.getFields()) {
            String fieldName = fieldDesc.getName();
            String propertyName = fieldDesc.getInternalName();

            Object value = getProperty(nsObject, propertyName);

            if (fieldDesc instanceof CustomFieldDesc) {
                CustomFieldDesc customFieldDesc = fieldDesc.asCustom();
                switch (customFieldDesc.getCustomFieldType()) {
                case BOOLEAN:
                case LONG:
                case DOUBLE:
                case STRING:
                case DATE:
                    assertNotNull(value);
                    break;
                }
            } else {
                Class<?> datumClass = fieldDesc.getValueType();

                if (datumClass == Boolean.class ||
                        datumClass == Long.class ||
                        datumClass == Double.class ||
                        datumClass == String.class ||
                        datumClass == XMLGregorianCalendar.class) {
                    assertNotNull(value);
                } else if (datumClass.isEnum()) {
                    assertNotNull(value);
                }
            }
        }
    }

    protected <T extends Record> void mockSearchRequestResults(List<T> recordList, int pageSize) throws Exception {
        final NetSuitePortType port = webServiceMockTestFixture.getPortMock();

        final List<SearchResult> pageResults = makeRecordPages(recordList, pageSize);
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
    }

    protected void mockCustomizationRequestResults(final Map<String, CustomFieldSpec> customFieldSpecs) throws Exception {
        final NetSuitePortType port = webServiceMockTestFixture.getPortMock();

        when(port.getCustomizationId(any(GetCustomizationIdRequest.class))).then(new Answer<GetCustomizationIdResponse>() {
            @Override public GetCustomizationIdResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                GetCustomizationIdRequest request = (GetCustomizationIdRequest) invocationOnMock.getArguments()[0];
                CustomizationType customizationType = request.getCustomizationType();

                GetCustomizationIdResult result = new GetCustomizationIdResult();
                result.setCustomizationRefList(new CustomizationRefList());
                result.setStatus(createSuccessStatus());

                Map<String, CustomizationRef> customizationRefMap = createCustomFieldCustomizationRefs(customFieldSpecs);
                for (String scriptId : customFieldSpecs.keySet()) {
                    RecordType recordType = RecordType.fromValue(customizationType.getGetCustomizationType().value());
                    CustomizationRef customizationRef = customizationRefMap.get(scriptId);
                    if (recordType == customizationRef.getType()) {
                        result.getCustomizationRefList().getCustomizationRef().add(customizationRef);
                    }
                }

                result.setTotalRecords(result.getCustomizationRefList().getCustomizationRef().size());

                GetCustomizationIdResponse response = new GetCustomizationIdResponse();
                response.setGetCustomizationIdResult(result);
                return response;
            }
        });

        when(port.getList(any(GetListRequest.class))).then(new Answer<GetListResponse>() {
            @Override public GetListResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                GetListRequest request = (GetListRequest) invocationOnMock.getArguments()[0];

                ReadResponseList readResponseList = new ReadResponseList();
                readResponseList.setStatus(createSuccessStatus());

                Map<String, CustomFieldType> customFieldTypeMap = createCustomFieldTypes(customFieldSpecs);
                for (BaseRef ref : request.getBaseRef()) {
                    if (ref instanceof CustomizationRef) {
                        CustomizationRef customizationRef = (CustomizationRef) ref;
                        if (customFieldTypeMap.containsKey(customizationRef.getScriptId())) {
                            CustomFieldType fieldType = customFieldTypeMap.get(customizationRef.getScriptId());

                            ReadResponse readResponse = new ReadResponse();
                            readResponse.setRecord(fieldType);
                            readResponse.setStatus(createSuccessStatus());
                            readResponseList.getReadResponse().add(readResponse);
                        }
                    }
                }

                GetListResponse response = new GetListResponse();
                response.setReadResponseList(readResponseList);
                return response;
            }
        });
    }

    protected class RecordComposer<T extends Record> extends SimpleObjectComposer<T> {
        Map<String, CustomFieldSpec> customFieldSpecs;

        public RecordComposer(Class<T> clazz, Map<String, CustomFieldSpec> customFieldSpecs) {
            super(clazz);
            this.customFieldSpecs = customFieldSpecs;
        }

        @Override
        public T composeObject() throws Exception {
            T record = super.composeObject();

            Map<String, CustomFieldRef> customFields = createCustomFieldRefs(customFieldSpecs);
            Beans.setProperty(record, "customFieldList", new CustomFieldList());
            List<CustomFieldRef> customFieldList =
                    (List<CustomFieldRef>) getProperty(record, "customFieldList.customField");
            customFieldList.addAll(customFields.values());

            return record;
        }
    }

    protected Map<String, CustomFieldRef> createCustomFieldRefs(
            Map<String, CustomFieldSpec> customFieldSpecs) throws Exception {

        NetSuiteClientService clientService = webServiceMockTestFixture.getClientService();

        Map<String, CustomFieldRef> map = new HashMap<>();
        for (CustomFieldSpec spec : customFieldSpecs.values()) {
            CustomFieldRef fieldRef = clientService.getBasicMetaData().createInstance(
                    spec.getFieldRefType().getTypeName());

            fieldRef.setScriptId(spec.getScriptId());
            fieldRef.setInternalId(spec.getInternalId());

            BeanInfo beanInfo = Beans.getBeanInfo(fieldRef.getClass());
            PropertyInfo valuePropInfo = beanInfo.getProperty("value");

            Object value = composeValue(valuePropInfo.getWriteType());
            if (value != null) {
                Beans.setProperty(fieldRef, "value", value);
            }

            map.put(fieldRef.getScriptId(), fieldRef);
        }

        return map;
    }

    protected Map<String, CustomizationRef> createCustomFieldCustomizationRefs(
            Map<String, CustomFieldSpec> customFieldSpecs) throws Exception {

        Map<String, CustomizationRef> map = new HashMap<>();
        for (CustomFieldSpec spec : customFieldSpecs.values()) {
            CustomizationRef ref = new CustomizationRef();

            ref.setScriptId(spec.getScriptId());
            ref.setInternalId(spec.getInternalId());
            ref.setType(spec.getRecordType());

            map.put(ref.getScriptId(), ref);
        }

        return map;
    }

    protected Map<String, CustomFieldType> createCustomFieldTypes(
            Map<String, CustomFieldSpec> customFieldSpecs) throws Exception {

        Map<String, CustomFieldType> customFieldTypeMap = new HashMap<>();
        for (CustomFieldSpec spec : customFieldSpecs.values()) {
            CustomFieldType fieldRecord = (CustomFieldType) spec.getFieldTypeClass().newInstance();

            Beans.setProperty(fieldRecord, "internalId", spec.getInternalId());
            fieldRecord.setScriptId(spec.getScriptId());
            fieldRecord.setFieldType(spec.getFieldType());

            for (String appliesTo : spec.getAppliesTo()) {
                Beans.setProperty(fieldRecord, appliesTo, Boolean.TRUE);
            }

            customFieldTypeMap.put(fieldRecord.getScriptId(), fieldRecord);
        }

        return customFieldTypeMap;
    }

    public static class CustomFieldSpec {
        protected Class<?> fieldTypeClass;
        protected String scriptId;
        protected String internalId;
        protected RecordType recordType;
        protected CustomizationFieldType fieldType;
        protected CustomFieldRefType fieldRefType;
        protected Collection<String> appliesTo;

        public CustomFieldSpec() {
        }

        public CustomFieldSpec(String scriptId, String internalId, RecordType recordType, Class<?> fieldTypeClass,
                CustomizationFieldType fieldType, CustomFieldRefType fieldRefType, Collection<String> appliesTo) {
            this.setFieldTypeClass(fieldTypeClass);
            this.setScriptId(scriptId);
            this.setInternalId(internalId);
            this.setRecordType(recordType);
            this.setFieldType(fieldType);
            this.setFieldRefType(fieldRefType);
            this.setAppliesTo(appliesTo);
        }

        public Class<?> getFieldTypeClass() {
            return fieldTypeClass;
        }

        public void setFieldTypeClass(Class<?> fieldTypeClass) {
            this.fieldTypeClass = fieldTypeClass;
        }

        public String getScriptId() {
            return scriptId;
        }

        public void setScriptId(String scriptId) {
            this.scriptId = scriptId;
        }

        public String getInternalId() {
            return internalId;
        }

        public void setInternalId(String internalId) {
            this.internalId = internalId;
        }

        public RecordType getRecordType() {
            return recordType;
        }

        public void setRecordType(RecordType recordType) {
            this.recordType = recordType;
        }

        public CustomizationFieldType getFieldType() {
            return fieldType;
        }

        public void setFieldType(CustomizationFieldType fieldType) {
            this.fieldType = fieldType;
        }

        public CustomFieldRefType getFieldRefType() {
            return fieldRefType;
        }

        public void setFieldRefType(CustomFieldRefType fieldRefType) {
            this.fieldRefType = fieldRefType;
        }

        public Collection<String> getAppliesTo() {
            return appliesTo;
        }

        public void setAppliesTo(Collection<String> appliesTo) {
            this.appliesTo = appliesTo;
        }
    }

}
