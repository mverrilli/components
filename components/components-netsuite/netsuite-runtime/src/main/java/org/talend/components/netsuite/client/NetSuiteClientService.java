package org.talend.components.netsuite.client;

import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.toInitialUpper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.netsuite.client.common.NsCustomizationRef;
import org.talend.components.netsuite.client.common.NsPreferences;
import org.talend.components.netsuite.client.common.NsSearchPreferences;
import org.talend.components.netsuite.client.common.NsSearchResult;
import org.talend.components.netsuite.client.common.NsWriteResponse;
import org.talend.components.netsuite.client.model.FieldInfo;
import org.talend.components.netsuite.client.model.MetaData;
import org.talend.components.netsuite.client.model.RecordTypeEx;
import org.talend.components.netsuite.client.model.RecordTypeInfo;
import org.talend.components.netsuite.client.model.SearchRecordTypeEx;
import org.talend.components.netsuite.client.model.TypeInfo;
import org.talend.components.netsuite.client.model.customfield.CustomFieldInfo;
import org.talend.components.netsuite.client.model.customfield.CustomFieldRefType;
import org.talend.components.netsuite.client.model.search.SearchFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchFieldOperatorType;
import org.talend.components.netsuite.client.query.SearchQuery;
import org.talend.daikon.NamedThing;
import org.talend.daikon.SimpleNamedThing;

/**
 *
 */
public abstract class NetSuiteClientService<PortT> {

    protected transient final Logger logger = LoggerFactory.getLogger(getClass());

    public static final int DEFAULT_PAGE_SIZE = 100;

    public static final String MESSAGE_LOGGING_ENABLED_PROPERTY_NAME =
            "org.talend.components.netsuite.client.messageLoggingEnabled";

    protected String endpointUrl;

    protected NetSuiteCredentials credentials;

    protected NsSearchPreferences searchPreferences;
    protected NsPreferences preferences;

    protected ReentrantLock lock = new ReentrantLock();

    protected boolean messageLoggingEnabled = false;

    protected int retryCount = 3;
    protected int retriesBeforeLogin = 2;
    protected int retryInterval = 5;

    protected int searchPageSize = DEFAULT_PAGE_SIZE;
    protected boolean bodyFieldsOnly = true;
    protected boolean returnSearchColumns = false;

    protected boolean treatWarningsAsErrors = false;
    protected boolean disableMandatoryCustomFieldValidation = false;

    protected boolean useRequestLevelCredentials = false;

    protected boolean loggedIn = false;

    protected PortT port;

    protected MetaData metaData;

    protected static final List<String> fieldCustomizationTypes = Collections.unmodifiableList(Arrays.asList(
            "crmCustomField",
            "entityCustomField",
            "itemCustomField",
            "itemNumberCustomField",
            "itemOptionCustomField",
            "otherCustomField",
            "transactionBodyCustomField"
    ));

    protected NetSuiteClientService() {
        super();
    }

    public static NetSuiteClientService create(String apiVersion) throws NetSuiteException {
        if ("2016.2".equals(apiVersion)) {
            return new org.talend.components.netsuite.client.v2016_2.NetSuiteClientServiceImpl();
        }
        throw new NetSuiteException("Invalid api version: " + apiVersion);
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public NetSuiteCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(NetSuiteCredentials credentials) {
        this.credentials = credentials;
    }

    public int getSearchPageSize() {
        return searchPageSize;
    }

    public void setSearchPageSize(int searchPageSize) {
        this.searchPageSize = searchPageSize;
    }

    public boolean isBodyFieldsOnly() {
        return bodyFieldsOnly;
    }

    public void setBodyFieldsOnly(boolean bodyFieldsOnly) {
        this.bodyFieldsOnly = bodyFieldsOnly;
    }

    public boolean isReturnSearchColumns() {
        return returnSearchColumns;
    }

    public void setReturnSearchColumns(boolean returnSearchColumns) {
        this.returnSearchColumns = returnSearchColumns;
    }

    public boolean isTreatWarningsAsErrors() {
        return treatWarningsAsErrors;
    }

    public void setTreatWarningsAsErrors(boolean treatWarningsAsErrors) {
        this.treatWarningsAsErrors = treatWarningsAsErrors;
    }

    public boolean isDisableMandatoryCustomFieldValidation() {
        return disableMandatoryCustomFieldValidation;
    }

    public void setDisableMandatoryCustomFieldValidation(boolean disableMandatoryCustomFieldValidation) {
        this.disableMandatoryCustomFieldValidation = disableMandatoryCustomFieldValidation;
    }

    public boolean isUseRequestLevelCredentials() {
        return useRequestLevelCredentials;
    }

    public void setUseRequestLevelCredentials(boolean useRequestLevelCredentials) {
        this.useRequestLevelCredentials = useRequestLevelCredentials;
    }

    public void login() throws NetSuiteException {
        lock.lock();
        try {
            relogin();
        } finally {
            lock.unlock();
        }
    }

    public SearchQuery newSearch() throws NetSuiteException {
        return new SearchQuery(this);
    }

    public abstract <RecT, SearchT> NsSearchResult<RecT> search(final SearchT searchRecord)
            throws NetSuiteException;

    public abstract <RecT> NsSearchResult<RecT> searchMore(final int pageIndex)
            throws NetSuiteException;

    public abstract <RecT> NsSearchResult<RecT> searchMoreWithId(final String searchId, final int pageIndex)
            throws NetSuiteException;

    public abstract <RecT> NsSearchResult<RecT> searchNext()
            throws NetSuiteException;

    public abstract <RecT, RefT> NsWriteResponse<RefT> add(final RecT record)
            throws NetSuiteException;

    public abstract <RecT, RefT> List<NsWriteResponse<RefT>> addList(final List<RecT> records)
            throws NetSuiteException;

    public abstract <RecT, RefT> NsWriteResponse<RefT> update(final RecT record)
            throws NetSuiteException;

    public abstract <RecT, RefT> List<NsWriteResponse<RefT>> updateList(final List<RecT> records)
            throws NetSuiteException;

    public abstract <RecT, RefT> NsWriteResponse<RefT> upsert(final RecT record)
            throws NetSuiteException;

    public abstract <RecT, RefT> List<NsWriteResponse<RefT>> upsertList(final List<RecT> records)
            throws NetSuiteException;

    public abstract <RefT> NsWriteResponse<RefT> delete(final RefT ref)
            throws NetSuiteException;

    public abstract <RefT> List<NsWriteResponse<RefT>> deleteList(final List<RefT> refs)
            throws NetSuiteException;

    public <R> R execute(PortOperation<R, PortT> op) throws NetSuiteException {
        if (useRequestLevelCredentials) {
            return executeUsingRequestLevelCredentials(op);
        } else {
            return executeUsingLogin(op);
        }
    }

    /////////////////////////////////////////////////////////////
    // Meta data
    /////////////////////////////////////////////////////////////

    protected Map<String, RecordTypeInfo> customRecordTypeMap = new HashMap<>();
    protected boolean recordTypeCustomizationsLoaded = false;

    protected Map<String, List<Object>> customFieldMap = new HashMap<>();
    protected Map<RecordTypeEx, Map<String, CustomFieldInfo>> recordCustomFieldMap = new HashMap<>();
    protected boolean fieldCustomizationsLoaded = false;

    public Collection<RecordTypeInfo> getRecordTypes() {
        List<RecordTypeInfo> recordTypes = new ArrayList<>();

        Collection<RecordTypeEx> standardRecordTypes = metaData.getRecordTypes();
        for (RecordTypeEx recordType : standardRecordTypes) {
            recordTypes.add(new RecordTypeInfo(recordType));
        }

        loadRecordTypeCustomizations();

        for (RecordTypeInfo recordTypeInfo : customRecordTypeMap.values()) {
            recordTypes.add(recordTypeInfo);
        }

        return recordTypes;
    }

    public Collection<NamedThing> getSearchableTypes() throws NetSuiteException {
        List<NamedThing> searches = new ArrayList<>(256);

        Collection<RecordTypeInfo> recordTypes = getRecordTypes();
        for (RecordTypeInfo recordTypeInfo : customRecordTypeMap.values()) {
            recordTypes.add(recordTypeInfo);
        }

        for (RecordTypeInfo recordTypeInfo : recordTypes) {
            SearchRecordTypeEx searchRecordType = metaData.getSearchRecordType(recordTypeInfo.getRecordType());
            if (searchRecordType != null) {
                searches.add(new SimpleNamedThing(recordTypeInfo.getName(), recordTypeInfo.getName()));
            }
        }

        return searches;
    }

    public TypeInfo getTypeInfo(String typeName) {
        return metaData.getTypeInfo(typeName);
    }

    public TypeInfo getTypeInfo(Class<?> clazz) {
        return metaData.getTypeInfo(clazz);
    }

    public TypeInfo getTypeInfo(String typeName, boolean includeCustomFields) {
        RecordTypeInfo recordTypeInfo = getRecordType(typeName);
        if (recordTypeInfo != null) {
            TypeInfo typeInfo = metaData.getTypeInfo(typeName);
            List<FieldInfo> fieldInfoList = typeInfo.getFields();

            List<FieldInfo> resultFieldInfoList = new ArrayList<>(fieldInfoList.size() + 10);
            for (FieldInfo fieldInfo : fieldInfoList) {
                String fieldName = fieldInfo.getName();
                if (includeCustomFields && fieldName.equals("customFieldList")) {
                    continue;
                }
            }

            Map<String, CustomFieldInfo> customFieldMap = getRecordCustomFields(recordTypeInfo.getRecordType());
            for (CustomFieldInfo fieldInfo : customFieldMap.values()) {
                resultFieldInfoList.add(fieldInfo);
            }

            return new TypeInfo(typeInfo.getTypeName(), typeInfo.getTypeClass(), resultFieldInfoList);
        } else {
            return metaData.getTypeInfo(typeName);
        }
    }

    public boolean isRecord(String typeName) {
        return metaData.isRecord(typeName);
    }

    public RecordTypeInfo getRecordType(String typeName) {
        RecordTypeEx recordType = metaData.getRecordType(typeName);
        if (recordType != null) {
            return new RecordTypeInfo(recordType);
        }

        loadRecordTypeCustomizations();

        RecordTypeInfo recordTypeInfo = customRecordTypeMap.get(typeName);
        return recordTypeInfo;
    }

    public SearchRecordTypeEx getSearchRecordType(String recordTypeName) {
        SearchRecordTypeEx searchRecordType = metaData.getSearchRecordType(recordTypeName);
        if (searchRecordType == null) {
            RecordTypeEx recordType = metaData.getRecordType(recordTypeName);
            if (recordType != null) {
                searchRecordType = metaData.getSearchRecordType(recordType.getSearchRecordType());
            }
        }
        return searchRecordType;
    }

    public Collection<SearchFieldOperatorType.QualifiedName> getSearchOperatorNames() {
        return metaData.getSearchOperatorNames();
    }

    public SearchFieldAdapter<?> getSearchFieldPopulator(String fieldType) {
        return metaData.getSearchFieldPopulator(fieldType);
    }

    protected Map<String, CustomFieldInfo> getRecordCustomFields(RecordTypeEx recordType) throws NetSuiteException {
        try {
            lock.lock();

            loadFieldCustomizations();

            Map<String, CustomFieldInfo> recordCustomFields = recordCustomFieldMap.get(recordType.getType());
            if (recordCustomFields == null) {
                recordCustomFields = new HashMap<>();

                for (String customizationType : fieldCustomizationTypes) {
                    List<Object> fieldCustomizationList = customFieldMap.get(customizationType);

                    for (Object customField : fieldCustomizationList) {

                        CustomFieldRefType customFieldRefType = metaData
                                .getCustomFieldRefType(recordType.getType(), customizationType, customField);

                        if (customFieldRefType != null) {
                            CustomFieldInfo customFieldInfo = new CustomFieldInfo();

                            String scriptId = (String) getProperty(customField, "scriptId");
                            String internalId = (String) getProperty(customField, "internalId");
                            String label = (String) getProperty(customField, "label");

                            NsCustomizationRef customizationRef = new NsCustomizationRef();
                            customizationRef.setScriptId(scriptId);
                            customizationRef.setInternalId(internalId);
                            customizationRef.setType(customizationType);
                            customizationRef.setName(label);

                            customFieldInfo.setCustomizationRef(customizationRef);
                            customFieldInfo.setName(customizationRef.getScriptId());
                            customFieldInfo.setCustomFieldType(customFieldRefType);

                            recordCustomFields.put(customFieldInfo.getName(), customFieldInfo);
                        }
                    }
                }

                recordCustomFieldMap.put(recordType, recordCustomFields);
            }

            return recordCustomFields;

        } finally {
            lock.unlock();
        }
    }

    protected void loadRecordTypeCustomizations() throws NetSuiteException {
        try {
            lock.lock();
            if (!recordTypeCustomizationsLoaded) {
                List<NsCustomizationRef> customRecordTypes = loadCustomizationIds("customRecordType");

                for (NsCustomizationRef customizationRef : customRecordTypes) {
                    String recordType = customizationRef.getType();
                    RecordTypeEx recordTypeInfo = metaData.getRecordType(toInitialUpper(recordType));
                    RecordTypeInfo customRecordTypeInfo = new RecordTypeInfo(
                            customizationRef.getScriptId(), recordTypeInfo);
                    customRecordTypeMap.put(customRecordTypeInfo.getName(), customRecordTypeInfo);
                }

                recordTypeCustomizationsLoaded = true;
            }
        } finally {
            lock.unlock();
        }
    }

    protected void loadFieldCustomizations() throws NetSuiteException {
        try {
            lock.lock();

            if (!fieldCustomizationsLoaded) {
                Map<String, List<NsCustomizationRef>> fieldCustomizationRefs = new HashMap<>(32);
                for (String customizationType : fieldCustomizationTypes) {
                    List<NsCustomizationRef> customizationRefs = loadCustomizationIds(customizationType);
                    fieldCustomizationRefs.put(customizationType, customizationRefs);
                }

                for (String customizationType : fieldCustomizationTypes) {
                    List<NsCustomizationRef> customizationRefs = fieldCustomizationRefs.get(customizationType);
                    List<Object> fieldCustomizationList = loadCustomizations(customizationRefs);
                    customFieldMap.put(customizationType, fieldCustomizationList);
                }

                fieldCustomizationsLoaded= true;
            }
        } finally {
            lock.unlock();
        }
    }

    protected abstract List<NsCustomizationRef> loadCustomizationIds(final String type) throws NetSuiteException;

    protected abstract <T> List<T> loadCustomizations(final List<NsCustomizationRef> nsCustomizationRefs)
            throws NetSuiteException;

    /////////////////////////////////////////////////////////////
    // Internal functionality
    /////////////////////////////////////////////////////////////

    protected <R> R executeUsingLogin(PortOperation<R, PortT> op) throws NetSuiteException {
        lock.lock();
        try {
            login(false);

            try {
                return op.execute(port);
            } catch (Exception e) {
                throw new NetSuiteException(e.getMessage(), e);
            }
        } finally {
            lock.unlock();
        }
    }

    private <R> R executeUsingRequestLevelCredentials(PortOperation<R, PortT> op) throws NetSuiteException {
        lock.lock();
        try {
            relogin();

            try {
                return op.execute(port);
            } catch (Exception e) {
                throw new NetSuiteException(e.getMessage(), e);
            }
        } finally {
            lock.unlock();
        }
    }

    protected void setHeader(PortT port, Header header) {
        BindingProvider provider = (BindingProvider) port;
        Map<String, Object> requestContext = provider.getRequestContext();
        List<Header> list = (List<Header>) requestContext.get(Header.HEADER_LIST);
        if (list == null) {
            list = new ArrayList<>();
            requestContext.put(Header.HEADER_LIST, list);
        }
        removeHeader(list, header.getName());
        list.add(header);
    }

    protected void removeHeader(QName name) {
        removeHeader(port, name);
    }

    protected void removeHeader(PortT port, QName name) {
        BindingProvider provider = (BindingProvider) port;
        Map<String, Object> requestContext = provider.getRequestContext();
        List<Header> list = (List<Header>) requestContext.get(Header.HEADER_LIST);
        removeHeader(list, name);
    }

    private void removeHeader(List<Header> list, QName name) {
        if (list != null) {
            Iterator<Header> headerIterator = list.iterator();
            while (headerIterator.hasNext()) {
                Header header = headerIterator.next();
                if (header.getName().equals(name)) {
                    headerIterator.remove();
                }
            }
        }
    }

    private void relogin() throws NetSuiteException {
        login(true);
    }

    private void login(boolean relogin) throws NetSuiteException {
        if (relogin) {
            loggedIn = false;
        }
        if (loggedIn) {
            return;
        }

        if (port != null) {
            try {
                doLogout();
            } catch (Exception e) {
            }
        }

        doLogin();

        NsSearchPreferences searchPreferences = new NsSearchPreferences();
        searchPreferences.setPageSize(searchPageSize);
        searchPreferences.setBodyFieldsOnly(Boolean.valueOf(bodyFieldsOnly));
        searchPreferences.setReturnSearchColumns(Boolean.valueOf(returnSearchColumns));

        this.searchPreferences = searchPreferences;

        NsPreferences preferences = new NsPreferences();
        preferences.setDisableMandatoryCustomFieldValidation(disableMandatoryCustomFieldValidation);
        preferences.setWarningAsError(treatWarningsAsErrors);

        this.preferences = preferences;

        setPreferences(port, preferences, searchPreferences);

        loggedIn = true;
    }

    protected abstract void doLogout() throws NetSuiteException;

    protected abstract void doLogin() throws NetSuiteException;

    public int getRetryCount() {
        return retryCount;
    }

    /**
     * Sets the number of retry attempts made when an operation fails.
     */
    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getRetryInterval() {
        return retryInterval;
    }

    /**
     * Sets the length of time (in seconds) that a session will sleep before attempting the retry of a failed operation.
     */
    public void setRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
    }

    public boolean isMessageLoggingEnabled() {
        return messageLoggingEnabled;
    }

    public void setMessageLoggingEnabled(boolean messageLoggingEnabled) {
        this.messageLoggingEnabled = messageLoggingEnabled;
    }


    protected void waitForRetryInterval() {
        try {
            Thread.sleep(getRetryInterval() * 1000);
        } catch (InterruptedException e) {

        }
    }

    protected void setPreferences(PortT port,
            NsPreferences nsPreferences, NsSearchPreferences nsSearchPreferences) throws NetSuiteException {

        Object searchPreferences = createNativeSearchPreferences(nsSearchPreferences);
        Object preferences = createNativePreferences(nsPreferences);
        try {
            Header searchPreferencesHeader = new Header(
                    new QName(getPlatformMessageNamespaceUri(), "searchPreferences"),
                    searchPreferences, new JAXBDataBinding(searchPreferences.getClass()));

            Header preferencesHeader = new Header(
                    new QName(getPlatformMessageNamespaceUri(), "preferences"),
                    preferences, new JAXBDataBinding(preferences.getClass()));

            setHeader(port, preferencesHeader);
            setHeader(port, searchPreferencesHeader);

        } catch (JAXBException e) {
            throw new NetSuiteException("XML binding error", e);
        }
    }

    protected void setLoginHeaders(PortT port) throws NetSuiteException {
        if (credentials.getApplicationId() != null) {
            Object applicationInfo = createNativeApplicationInfo(credentials);
            try {
                if (applicationInfo != null) {
                    Header appInfoHeader = new Header(
                            new QName(getPlatformMessageNamespaceUri(), "applicationInfo"),
                            applicationInfo, new JAXBDataBinding(applicationInfo.getClass()));
                    setHeader(port, appInfoHeader);
                }
            } catch (JAXBException e) {
                throw new NetSuiteException("XML binding error", e);
            }
        }
    }

    protected void remoteLoginHeaders(PortT port) throws NetSuiteException {
        removeHeader(port, new QName(getPlatformMessageNamespaceUri(), "applicationInfo"));
    }

    protected abstract String getPlatformMessageNamespaceUri();

    protected abstract <T> T createNativePreferences(NsPreferences nsPreferences);

    protected abstract <T> T createNativeSearchPreferences(NsSearchPreferences nsSearchPreferences);

    protected abstract <T> T createNativeApplicationInfo(NetSuiteCredentials nsCredentials);

    protected abstract <T> T createNativePassport(NetSuiteCredentials nsCredentials);

    protected abstract PortT getNetSuitePort(String defaultEndpointUrl, String account) throws NetSuiteException;

    public interface PortOperation<R, PortT> {
        R execute(PortT port) throws Exception;
    }

    public <T> T createType(String typeName) throws NetSuiteException {
        return metaData.createType(typeName);
    }

    protected <T> T createInstance(Class<T> clazz) throws NetSuiteException {
        try {
            T target = clazz.cast(clazz.newInstance());
            return target;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new NetSuiteException("Failed to instantiate object: " + clazz, e);
        }
    }

}
