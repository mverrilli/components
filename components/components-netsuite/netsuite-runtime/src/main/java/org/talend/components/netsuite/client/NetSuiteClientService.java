package org.talend.components.netsuite.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.talend.components.netsuite.client.model.custom.CustomFieldInfo;
import org.talend.components.netsuite.client.model.FieldInfo;
import org.talend.components.netsuite.client.model.RecordTypeInfo;
import org.talend.components.netsuite.client.model.MetaDataProvider;
import org.talend.components.netsuite.client.model.custom.CustomFieldRefType;
import org.talend.components.netsuite.client.model.search.SearchFieldOperatorTypeInfo;
import org.talend.components.netsuite.client.model.search.SearchFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchRecordInfo;
import org.talend.components.netsuite.client.model.TypeInfo;
import org.talend.components.netsuite.client.query.SearchQuery;
import org.talend.daikon.NamedThing;
import org.talend.daikon.SimpleNamedThing;

import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.toInitialUpper;

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

    protected MetaDataProvider metaDataProvider;

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
    protected Map<String, Map<String, CustomFieldInfo>> recordCustomFieldMap = new HashMap<>();

    public Collection<NamedThing> getRecordTypes() {
        List<NamedThing> recordTypes = new ArrayList<>();

        Collection<String> standardRecordTypes = metaDataProvider.getRecordTypes();
        for (String recordType : standardRecordTypes) {
            recordTypes.add(new SimpleNamedThing(recordType, recordType));
        }

        for (RecordTypeInfo recordTypeInfo : customRecordTypeMap.values()) {
            recordTypes.add(new SimpleNamedThing(recordTypeInfo.getName(), recordTypeInfo.getName()));
        }

        return recordTypes;
    }

    public Collection<NamedThing> getSearches() throws NetSuiteException {
        List<NamedThing> searches = new ArrayList<>(256);

        Collection<String> recordTypes = metaDataProvider.getRecordTypes();
        for (String recordTypeName : recordTypes) {
            RecordTypeInfo def = metaDataProvider.getRecordTypeInfo(recordTypeName);
            SearchRecordInfo searchRecordInfo = metaDataProvider.getSearchRecordInfo(recordTypeName);
            if (searchRecordInfo != null) {
                String name = def.getName();
                searches.add(new SimpleNamedThing(name, name));
            }
        }

        return searches;
    }

    public TypeInfo getTypeInfo(String typeName) {
        return metaDataProvider.getTypeInfo(typeName);
    }

    public TypeInfo getTypeInfo(Class<?> clazz) {
        return metaDataProvider.getTypeInfo(clazz);
    }

    public TypeInfo getTypeInfo(String typeName, boolean includeCustomFields) {
        TypeInfo typeInfo = metaDataProvider.getTypeInfo(typeName);
        List<FieldInfo> fieldInfoList = typeInfo.getFields();

        List<FieldInfo> resultFieldInfoList = new ArrayList<>(fieldInfoList.size() + 10);
        for (FieldInfo fieldInfo : fieldInfoList) {
            String fieldName = fieldInfo.getName();
            if (includeCustomFields && fieldName.equals("customFieldList")) {
                continue;
            }
        }

        if (metaDataProvider.isRecord(typeName)) {
            Collection<CustomFieldInfo> customFieldInfoList = getCustomFieldsForRecordType(typeName);
            System.out.println(customFieldInfoList);
        }

        return new TypeInfo(typeInfo.getTypeName(), typeInfo.getTypeClass(), resultFieldInfoList);
    }

    public boolean isRecord(String typeName) {
        return metaDataProvider.isRecord(typeName);
    }

    public RecordTypeInfo getRecordTypeInfo(String typeName) {
        return metaDataProvider.getRecordTypeInfo(typeName);
    }

    public SearchRecordInfo getSearchRecordInfo(String recordType) {
        SearchRecordInfo searchRecordInfo = metaDataProvider.getSearchRecordTypeInfoByRecordType(recordType);
        if (searchRecordInfo == null) {
            searchRecordInfo = metaDataProvider.getSearchRecordInfo(recordType);
        }
        return searchRecordInfo;
    }

    public Class<?> getSearchFieldClass(String searchFieldType) {
        return metaDataProvider.getSearchFieldClass(searchFieldType);
    }

    public Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName) {
        return metaDataProvider.getSearchFieldOperatorByName(searchFieldType, searchFieldOperatorName);
    }

    public Collection<SearchFieldOperatorTypeInfo.QualifiedName> getSearchOperatorNames() {
        return metaDataProvider.getSearchOperatorNames();
    }

    public SearchFieldAdapter<?> getSearchFieldPopulator(String fieldType) {
        return metaDataProvider.getSearchFieldPopulator(fieldType);
    }

    public void updateCustomMetaData() throws NetSuiteException {
        List<NsCustomizationRef> customRecordTypes = loadCustomizationIds("customRecordType");

        for (NsCustomizationRef customizationRef : customRecordTypes) {
            String recordType = customizationRef.getType();
            RecordTypeInfo recordTypeInfo = metaDataProvider.getRecordTypeInfo(toInitialUpper(recordType));
            RecordTypeInfo customRecordTypeInfo = new RecordTypeInfo(
                    customizationRef.getScriptId(),
                    recordTypeInfo.getRecordType(),
                    recordTypeInfo.getRecordClass());
            customRecordTypeMap.put(customRecordTypeInfo.getName(), customRecordTypeInfo);
        }

        List<String> fieldCustomizationTypes = Arrays.asList(
                "crmCustomField",
                "entityCustomField",
                "itemCustomField",
                "itemNumberCustomField",
                "itemOptionCustomField",
                "otherCustomField",
                "transactionBodyCustomField"
        );

        for (String customizationType : fieldCustomizationTypes) {
            List<NsCustomizationRef> fieldCustomizationRefs = loadCustomizationIds(customizationType);
            List<Object> fieldCustomizations = loadCustomizations(fieldCustomizationRefs);

            Collection<String> recordTypes = metaDataProvider.getRecordTypes();

            for (Object customField : fieldCustomizations) {
                for (String recordTypeName : recordTypes) {
                    RecordTypeInfo recordTypeInfo = metaDataProvider.getRecordTypeInfo(recordTypeName);

                    Map<String, CustomFieldInfo> recordCustomFields =
                            recordCustomFieldMap.get(recordTypeInfo.getRecordType());
                    if (recordCustomFields == null) {
                        recordCustomFields = new HashMap<>();
                        recordCustomFieldMap.put(recordTypeInfo.getRecordType(), recordCustomFields);
                    }

                    CustomFieldRefType customFieldRefType = metaDataProvider.getCustomFieldRefType(
                            recordTypeInfo.getRecordType(), customizationType, customField);

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
        }

    }

    protected List<NsCustomizationRef> loadCustomizationIds(final Collection<String> types) throws NetSuiteException {
        List<NsCustomizationRef> customizationRefs = new ArrayList<>();
        for (String type : types) {
            customizationRefs.addAll(loadCustomizationIds(type));
        }
        return customizationRefs;
    }

    protected abstract List<NsCustomizationRef> loadCustomizationIds(final String type) throws NetSuiteException;

    protected abstract <T> List<T> loadCustomizations(final List<NsCustomizationRef> nsCustomizationRefs)
            throws NetSuiteException;

    protected abstract Collection<CustomFieldInfo> getCustomFieldsForRecordType(final String recordType)
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
        return metaDataProvider.createType(typeName);
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
