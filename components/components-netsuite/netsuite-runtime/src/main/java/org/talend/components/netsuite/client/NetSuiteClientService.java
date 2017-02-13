package org.talend.components.netsuite.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.headers.Header;
import org.talend.components.netsuite.client.schema.NsFieldDef;
import org.talend.components.netsuite.client.schema.NsSearchDef;
import org.talend.components.netsuite.client.schema.NsSearchFieldOperatorTypeDef;
import org.talend.components.netsuite.client.schema.NsTypeDef;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.model.TypeManager;

/**
 *
 */
public abstract class NetSuiteClientService<P> {

    public static final int DEFAULT_PAGE_SIZE = 100;

    /////////////////////////////////////////////////
    // Meta data
    /////////////////////////////////////////////////

    protected Map<String, NsTypeDef> typeMap = new HashMap<>();
    protected Map<String, NsSearchDef> searchMap = new HashMap<>();
    protected Map<String, Class<?>> searchFieldMap = new HashMap<>();
    protected Map<String, NsSearchFieldOperatorTypeDef> searchFieldOperatorTypeMap = new HashMap<>();
    protected Map<String, String> searchFieldOperatorMap = new HashMap<>();

    protected String endpointUrl;

    protected NetSuiteCredentials credentials;

    protected NsSearchPreferences searchPreferences;
    protected NsPreferences preferences;

    protected ReentrantLock lock = new ReentrantLock();

    private int retryCount = 3;
    private int retriesBeforeLogin = 2;
    private int retryInterval = 5;

    private int searchPageSize = DEFAULT_PAGE_SIZE;
    private boolean bodyFieldsOnly = true;

    private boolean treatWarningsAsErrors = false;
    private boolean disableMandatoryCustomFieldValidation = false;

    private boolean useRequestLevelCredentials = false;

    private boolean loggedIn = false;

    private boolean messageLoggingEnabled = false;

    protected P port;

    public static <P> NetSuiteClientService<P> getClientService(String apiVersion) throws NetSuiteException {
        NetSuiteClientService connection;
        if (apiVersion.equals("2016.2")) {
            connection = new org.talend.components.netsuite.client.impl.v2016_2.NetSuiteClientServiceImpl();
        } else {
            throw new IllegalArgumentException("Invalid api version: " + apiVersion);
        }
        return connection;
    }

    protected NetSuiteClientService() {
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

    public abstract NetSuiteMetaData getMetaData() throws NetSuiteException;

    public void login() throws NetSuiteException {
        lock.lock();
        try {
            relogin();
        } finally {
            lock.unlock();
        }
    }

    public NsSearch newSearch() throws NetSuiteException {
        return new NsSearch(this);
    }

    public abstract <RecT, SearchRecT> NsSearchResult<RecT> search(SearchRecT searchRecord) throws NetSuiteException;

    public abstract <RecT> NsSearchResult<RecT> searchMore(int pageIndex) throws NetSuiteException;

    public abstract <RecT> NsSearchResult<RecT> searchMoreWithId(String searchId, int pageIndex) throws NetSuiteException;

    public abstract <RecT> NsSearchResult<RecT> searchNext() throws NetSuiteException;

    public abstract <RecT, RefT> NsWriteResponse<RefT> add(RecT record) throws NetSuiteException;

    public abstract <RecT, RefT> NsWriteResponseList<RefT> addList(List<RecT> records) throws NetSuiteException;

    public abstract <RecT, RefT> NsWriteResponse<RefT> update(RecT record) throws NetSuiteException;

    public abstract <RecT, RefT> NsWriteResponseList<RefT> updateList(List<RecT> records) throws NetSuiteException;

    public abstract <RecT, RefT> NsWriteResponse<RefT> upsert(RecT record) throws NetSuiteException;

    public abstract <RecT, RefT> NsWriteResponseList<RefT> upsertList(List<RecT> records) throws NetSuiteException;

    public abstract <RefT> NsWriteResponse<RefT> delete(RefT ref) throws NetSuiteException;

    public abstract <RefT> NsWriteResponseList<RefT> deleteList(List<RefT> refList) throws NetSuiteException;

    public <R> R execute(PortOperation<R, P> op) throws NetSuiteException {
        if (useRequestLevelCredentials) {
            return executeUsingRequestLevelCredentials(op);
        } else {
            return executeUsingLogin(op);
        }
    }

    protected <R> R executeUsingLogin(PortOperation<R, P> op) throws NetSuiteException {
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

    private <R> R executeUsingRequestLevelCredentials(PortOperation<R, P> op) throws NetSuiteException {
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

    protected abstract void setPreferences(P port,
            NsPreferences preferences, NsSearchPreferences searchPreferences) throws NetSuiteException;

    protected void setHeader(Header header) {
        setHeader(port, header);
    }

    protected void setHeader(P port, Header header) {
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

    protected void removeHeader(P port, QName name) {
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

        this.searchPreferences = searchPreferences;

        NsPreferences preferences = new NsPreferences();
        preferences.setDisableMandatoryCustomFieldValidation(disableMandatoryCustomFieldValidation);
        preferences.setWarningAsError(treatWarningsAsErrors);

        this.preferences = preferences;

        setPreferences(port, preferences, searchPreferences);

        loggedIn = true;
    }

    protected abstract void doLogin() throws NetSuiteException;

    protected abstract void doLogout() throws NetSuiteException;

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

//    private boolean errorCanBeWorkedAround (Throwable t) {
//        if (t instanceof InvalidSessionFault ||
//                t instanceof RemoteException ||
//                t instanceof SOAPFaultException ||
//                t instanceof SocketException)
//            return true;
//
//        return false;
//    }

    public interface PortOperation<R, P> {
        R execute(P port) throws Exception;
    }

    /////////////////////////////////////////////////
    // Meta data
    /////////////////////////////////////////////////

    protected static abstract class NetSuiteMetaDataBase implements NetSuiteMetaData {

        protected Map<String, NsTypeDef> typeMap = new HashMap<>();
        protected Map<String, NsSearchDef> searchMap = new HashMap<>();
        protected Map<String, Class<?>> searchFieldMap = new HashMap<>();
        protected Map<String, NsSearchFieldOperatorTypeDef> searchFieldOperatorTypeMap = new HashMap<>();
        protected Map<String, String> searchFieldOperatorMap = new HashMap<>();

        public NetSuiteMetaDataBase() {
        }

        public NetSuiteMetaDataBase(NetSuiteMetaDataBase master) {
            typeMap.putAll(master.typeMap);
            searchMap.putAll(master.searchMap);
            searchFieldMap.putAll(master.searchFieldMap);
            searchFieldOperatorTypeMap.putAll(master.searchFieldOperatorTypeMap);
            searchFieldOperatorMap.putAll(master.searchFieldOperatorMap);
        }

        protected void registerType(Class<?> typeClass, String typeName) {
            String typeNameToRegister = typeName != null ? typeName : typeClass.getSimpleName();
            if (typeMap.containsKey(typeNameToRegister)) {
                NsTypeDef entityInfo = typeMap.get(typeNameToRegister);
                if (entityInfo.getTypeClass() == typeClass) {
                    return;
                } else {
                    throw new IllegalArgumentException("Type already registered: " +
                            typeNameToRegister + ", class to register is " +
                            typeClass + ", registered class is " +
                            typeMap.get(typeNameToRegister));
                }
            }

            TypeInfo beanInfo = TypeManager.forClass(typeClass);
            List<PropertyInfo> propertyInfos = beanInfo.getProperties();
            List<NsFieldDef> fields = new ArrayList<>(propertyInfos.size());
            for (PropertyInfo propertyInfo : propertyInfos) {
                String fieldName = propertyInfo.getName();
                Class fieldValueType = propertyInfo.getReadType();
                if ((fieldName.equals("class") && fieldValueType == Class.class) ||
                        (fieldName.equals("nullFieldList") && fieldValueType.getSimpleName().equals("NullField"))) {
                    continue;
                }
                boolean isKeyField = isKeyField(typeClass, propertyInfo);
                NsFieldDef fieldInfo = new NsFieldDef(fieldName, fieldValueType, isKeyField, true);
                fields.add(fieldInfo);
            }

            NsTypeDef entityInfo = new NsTypeDef(typeName, typeClass, fields);
            typeMap.put(typeNameToRegister, entityInfo);
        }

        protected void registerSearchDefs(NsSearchDef[] searchTable) {
            for (NsSearchDef entry : searchTable) {
                String typeName = entry.getRecordTypeName();

                registerType(entry.getRecordClass(), typeName);
                registerType(entry.getSearchClass(), null);
                registerType(entry.getSearchBasicClass(), null);
                registerType(entry.getSearchAdvancedClass(), null);

                if (searchMap.containsKey(typeName)) {
                    throw new IllegalArgumentException(
                            "Search entry already registered: "
                                    + typeName + ", search classes to register are "
                                    + entry.getSearchClass() + ", "
                                    + entry.getSearchBasicClass() + ", "
                                    + entry.getSearchAdvancedClass());
                }
                searchMap.put(typeName, entry);
            }
        }

        protected void registerSearchFieldDefs(Class<?>[] searchFieldTable) {
            searchFieldMap = new HashMap<>(searchFieldTable.length);
            for (Class<?> entry : searchFieldTable) {
                searchFieldMap.put(entry.getSimpleName(), entry);
            }
        }

        protected void registerSearchFieldOperatorTypeDefs(NsSearchFieldOperatorTypeDef[] searchFieldOperatorTable) {
            searchFieldOperatorTypeMap = new HashMap<>(searchFieldOperatorTable.length);
            for (NsSearchFieldOperatorTypeDef info : searchFieldOperatorTable) {
                searchFieldOperatorTypeMap.put(info.getTypeName(), info);
            }

            searchFieldOperatorMap.put("SearchMultiSelectField", "SearchMultiSelectFieldOperator");
            searchFieldOperatorMap.put("SearchMultiSelectCustomField", "SearchMultiSelectFieldOperator");
            searchFieldOperatorMap.put("SearchEnumMultiSelectField", "SearchEnumMultiSelectFieldOperator");
            searchFieldOperatorMap.put("SearchEnumMultiSelectCustomField", "SearchEnumMultiSelectFieldOperator");
        }

        public abstract Collection<String> getTransactionTypes();

        public abstract Collection<String> getItemTypes();

        public NsTypeDef getTypeDef(String typeName) {
            return typeMap.get(typeName);
        }

        public NsTypeDef getTypeDef(Class<?> clazz) {
            return typeMap.get(clazz.getSimpleName());
        }

        public Collection<String> getRecordTypes() {
            return Collections.unmodifiableSet(searchMap.keySet());
        }

        public NsSearchDef getSearchDef(String typeName) {
            return searchMap.get(typeName);
        }

        public Class<?> getSearchFieldClass(String searchFieldType) {
            return searchFieldMap.get(searchFieldType);
        }

        public Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName) {
            NsSearchFieldOperatorTypeDef.QualifiedName operatorQName =
                    new NsSearchFieldOperatorTypeDef.QualifiedName(searchFieldOperatorName);
            String searchFieldOperatorType = searchFieldOperatorMap.get(searchFieldType);
            if (searchFieldOperatorType != null) {
                NsSearchFieldOperatorTypeDef def = searchFieldOperatorTypeMap.get(searchFieldOperatorType);
                return def.getOperator(searchFieldOperatorName);
            }
            for (NsSearchFieldOperatorTypeDef def : searchFieldOperatorTypeMap.values()) {
                if (def.hasOperatorName(operatorQName)) {
                    return def.getOperator(searchFieldOperatorName);
                }
            }
            return null;
        }

        public Collection<NsSearchFieldOperatorTypeDef.QualifiedName> getSearchOperatorNames() {
            Set<NsSearchFieldOperatorTypeDef.QualifiedName> names = new HashSet<>();
            for (NsSearchFieldOperatorTypeDef info : searchFieldOperatorTypeMap.values()) {
                names.addAll(info.getOperatorNames());
            }
            return Collections.unmodifiableSet(names);
        }

        public abstract <T> T createListOrRecordRef() throws NetSuiteException;

        public abstract <T> T createRecordRef() throws NetSuiteException;

        public <T> T createType(String typeName) throws NetSuiteException {
            NsTypeDef typeDef = getTypeDef(typeName);
            if (typeDef == null) {
                throw new NetSuiteException("Unknown type: " + typeName);
            }
            return (T) createInstance(typeDef.getTypeClass());
        }

        protected <T> T createInstance(Class<T> clazz) throws NetSuiteException {
            try {
                T target = clazz.cast(clazz.newInstance());
                return target;
            } catch (IllegalAccessException | InstantiationException e) {
                throw new NetSuiteException("Failed to instantiate object: " + clazz, e);
            }
        }

        protected abstract boolean isKeyField(Class<?> entityClass, PropertyInfo propertyInfo);

//        public static String toInitialUpper(String value) {
//            return value.substring(0, 1).toUpperCase() + value.substring(1);
//        }
//
//        public static String toInitialLower(String value) {
//            return value.substring(0, 1).toLowerCase() + value.substring(1);
//        }
//
//        public static String toNetSuiteType(String value) {
//            return "_" + toInitialLower(value);
//        }

    }

    public static String toInitialUpper(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    public static String toInitialLower(String value) {
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }

    public static String toNetSuiteType(String value) {
        return "_" + toInitialLower(value);
    }
}
