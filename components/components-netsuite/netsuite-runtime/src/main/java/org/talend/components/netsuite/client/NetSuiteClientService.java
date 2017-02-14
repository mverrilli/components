package org.talend.components.netsuite.client;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.netsuite.client.metadata.TypeDef;
import org.talend.components.netsuite.client.metadata.RecordTypeDef;
import org.talend.components.netsuite.client.metadata.SearchFieldOperatorTypeDef;
import org.talend.components.netsuite.client.metadata.SearchRecordDef;
import org.talend.components.netsuite.client.metadata.StandardMetaData;
import org.talend.components.netsuite.client.search.SearchQuery;

import com.netsuite.webservices.platform.ExceededRequestSizeFault;
import com.netsuite.webservices.platform.InsufficientPermissionFault;
import com.netsuite.webservices.platform.InvalidCredentialsFault;
import com.netsuite.webservices.platform.InvalidSessionFault;
import com.netsuite.webservices.platform.NetSuitePortType;
import com.netsuite.webservices.platform.NetSuiteService;
import com.netsuite.webservices.platform.UnexpectedErrorFault;
import com.netsuite.webservices.platform.core.BaseRef;
import com.netsuite.webservices.platform.core.DataCenterUrls;
import com.netsuite.webservices.platform.core.Passport;
import com.netsuite.webservices.platform.core.Record;
import com.netsuite.webservices.platform.core.RecordRef;
import com.netsuite.webservices.platform.core.SearchRecord;
import com.netsuite.webservices.platform.core.SearchResult;
import com.netsuite.webservices.platform.core.Status;
import com.netsuite.webservices.platform.messages.AddListRequest;
import com.netsuite.webservices.platform.messages.AddRequest;
import com.netsuite.webservices.platform.messages.ApplicationInfo;
import com.netsuite.webservices.platform.messages.DeleteListRequest;
import com.netsuite.webservices.platform.messages.DeleteRequest;
import com.netsuite.webservices.platform.messages.GetDataCenterUrlsRequest;
import com.netsuite.webservices.platform.messages.GetDataCenterUrlsResponse;
import com.netsuite.webservices.platform.messages.LoginRequest;
import com.netsuite.webservices.platform.messages.LoginResponse;
import com.netsuite.webservices.platform.messages.LogoutRequest;
import com.netsuite.webservices.platform.messages.Preferences;
import com.netsuite.webservices.platform.messages.SearchMoreRequest;
import com.netsuite.webservices.platform.messages.SearchMoreWithIdRequest;
import com.netsuite.webservices.platform.messages.SearchNextRequest;
import com.netsuite.webservices.platform.messages.SearchPreferences;
import com.netsuite.webservices.platform.messages.SearchRequest;
import com.netsuite.webservices.platform.messages.SessionResponse;
import com.netsuite.webservices.platform.messages.UpdateListRequest;
import com.netsuite.webservices.platform.messages.UpdateRequest;
import com.netsuite.webservices.platform.messages.UpsertListRequest;
import com.netsuite.webservices.platform.messages.UpsertRequest;
import com.netsuite.webservices.platform.messages.WriteResponse;
import com.netsuite.webservices.platform.messages.WriteResponseList;

/**
 *
 */
public class NetSuiteClientService {

    private transient static final Logger LOG = LoggerFactory.getLogger(NetSuiteClientService.class);

    public static final int DEFAULT_PAGE_SIZE = 100;

    public static final String MESSAGE_LOGGING_ENABLED_PROPERTY_NAME =
            "org.talend.components.netsuite.client.messageLoggingEnabled";

    public static final String DEFAULT_ENDPOINT_URL =
            "https://webservices.netsuite.com/services/NetSuitePort_2016_2";

    public static final String NS_URI_PLATFORM_MESSAGES =
            "urn:messages_2016_2.platform.webservices.netsuite.com";

    private String endpointUrl;

    private NetSuiteCredentials credentials;

    private SearchPreferencesEx searchPreferences;
    private PreferencesEx preferences;

    private ReentrantLock lock = new ReentrantLock();

    private boolean messageLoggingEnabled = false;

    private int retryCount = 3;
    private int retriesBeforeLogin = 2;
    private int retryInterval = 5;

    private int searchPageSize = DEFAULT_PAGE_SIZE;
    private boolean bodyFieldsOnly = true;

    private boolean treatWarningsAsErrors = false;
    private boolean disableMandatoryCustomFieldValidation = false;

    private boolean useRequestLevelCredentials = false;

    private boolean loggedIn = false;

    private NetSuitePortType port;

    public NetSuiteClientService() {
        super();
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

    public SearchResultEx search(final SearchRecord searchRecord) throws NetSuiteException {
        return execute(new PortOperation<SearchResultEx>() {
            @Override public SearchResultEx execute(NetSuitePortType port) throws Exception {
                SearchRequest request = new SearchRequest();
                SearchRecord r = NsObject.unwrap(searchRecord, SearchRecord.class);
                request.setSearchRecord(r);

                SearchResult result = port.search(request).getSearchResult();
                return new SearchResultEx(result);
            }
        });
    }

    public SearchResultEx searchMore(final int pageIndex) throws NetSuiteException {
        return execute(new PortOperation<SearchResultEx>() {
            @Override public SearchResultEx execute(NetSuitePortType port) throws Exception {
                SearchMoreRequest request = new SearchMoreRequest();
                request.setPageIndex(pageIndex);

                SearchResult result = port.searchMore(request).getSearchResult();
                return new SearchResultEx(result);
            }
        });
    }

    public SearchResultEx searchMoreWithId(
            final String searchId, final int pageIndex) throws NetSuiteException {
        return execute(new PortOperation<SearchResultEx>() {
            @Override public SearchResultEx execute(NetSuitePortType port) throws Exception {
                SearchMoreWithIdRequest request = new SearchMoreWithIdRequest();
                request.setSearchId(searchId);
                request.setPageIndex(pageIndex);

                SearchResult result = port.searchMoreWithId(request).getSearchResult();
                return new SearchResultEx(result);
            }
        });
    }

    public SearchResultEx searchNext() throws NetSuiteException {
        return execute(new PortOperation<SearchResultEx>() {
            @Override public SearchResultEx execute(NetSuitePortType port) throws Exception {
                SearchNextRequest request = new SearchNextRequest();
                SearchResult result = port.searchNext(request).getSearchResult();
                return new SearchResultEx(result);
            }
        });
    }

    public WriteResponseEx add(final Record record) throws NetSuiteException {
        if (record == null) {
            return new WriteResponseEx(new WriteResponse());
        }
        return execute(new PortOperation<WriteResponseEx>() {
            @Override public WriteResponseEx execute(NetSuitePortType port) throws Exception {
                AddRequest request = new AddRequest();
                request.setRecord(record);

                WriteResponse response = port.add(request).getWriteResponse();
                return new WriteResponseEx(response);
            }
        });
    }

    public List<WriteResponseEx> addList(final List<Record> records) throws NetSuiteException {
        if (records == null || records.isEmpty()) {
            return Collections.emptyList();
        }
        return execute(new PortOperation<List<WriteResponseEx>>() {
            @Override public List<WriteResponseEx> execute(NetSuitePortType port) throws Exception {
                AddListRequest request = new AddListRequest();
                request.getRecord().addAll(records);

                WriteResponseList writeResponseList = port.addList(request).getWriteResponseList();
                return toWriteResponseExList(writeResponseList);
            }
        });
    }

    public WriteResponseEx update(final Record record) throws NetSuiteException {
        if (record == null) {
            return new WriteResponseEx(new WriteResponse());
        }
        return execute(new PortOperation<WriteResponseEx>() {
            @Override public WriteResponseEx execute(NetSuitePortType port) throws Exception {
                UpdateRequest request = new UpdateRequest();
                request.setRecord(record);

                WriteResponse response = port.update(request).getWriteResponse();
                return new WriteResponseEx(response);
            }
        });
    }

    public List<WriteResponseEx> updateList(final List<Record> records) throws NetSuiteException {
        if (records == null || records.isEmpty()) {
            return Collections.emptyList();
        }
        return execute(new PortOperation<List<WriteResponseEx>>() {
            @Override public List<WriteResponseEx> execute(NetSuitePortType port) throws Exception {
                UpdateListRequest request = new UpdateListRequest();
                request.getRecord().addAll(records);

                WriteResponseList writeResponseList = port.updateList(request).getWriteResponseList();
                return toWriteResponseExList(writeResponseList);
            }
        });
    }

    public WriteResponseEx upsert(final Record record) throws NetSuiteException {
        if (record == null) {
            return new WriteResponseEx(new WriteResponse());
        }
        return execute(new PortOperation<WriteResponseEx>() {
            @Override public WriteResponseEx execute(NetSuitePortType port) throws Exception {
                UpsertRequest request = new UpsertRequest();
                request.setRecord(record);

                WriteResponse response = port.upsert(request).getWriteResponse();
                return new WriteResponseEx(response);
            }
        });
    }

    public List<WriteResponseEx> upsertList(final List<Record> records) throws NetSuiteException {
        if (records == null || records.isEmpty()) {
            return Collections.emptyList();
        }
        return execute(new PortOperation<List<WriteResponseEx>>() {
            @Override public List<WriteResponseEx> execute(NetSuitePortType port) throws Exception {
                UpsertListRequest request = new UpsertListRequest();
                request.getRecord().addAll(records);

                WriteResponseList writeResponseList = port.upsertList(request).getWriteResponseList();
                return toWriteResponseExList(writeResponseList);
            }
        });
    }

    public WriteResponseEx delete(final BaseRef ref) throws NetSuiteException {
        if (ref == null) {
            return new WriteResponseEx(new WriteResponse());
        }
        return execute(new PortOperation<WriteResponseEx>() {
            @Override public WriteResponseEx execute(NetSuitePortType port) throws Exception {
                DeleteRequest request = new DeleteRequest();
                BaseRef baseRef = NsObject.unwrap(ref, BaseRef.class);
                request.setBaseRef(baseRef);

                WriteResponse writeResponse = port.delete(request).getWriteResponse();
                return new WriteResponseEx(writeResponse);
            }
        });
    }

    public List<WriteResponseEx> deleteList(final List<BaseRef> refs) throws NetSuiteException {
        if (refs == null || refs.isEmpty()) {
            return Collections.emptyList();
        }
        return execute(new PortOperation<List<WriteResponseEx>>() {
            @Override public List<WriteResponseEx> execute(NetSuitePortType port) throws Exception {
                DeleteListRequest request = new DeleteListRequest();
                request.getBaseRef().addAll(refs);

                WriteResponseList writeResponseList = port.deleteList(request).getWriteResponseList();
                return toWriteResponseExList(writeResponseList);
            }
        });
    }

    public <R> R execute(PortOperation<R> op) throws NetSuiteException {
        if (useRequestLevelCredentials) {
            return executeUsingRequestLevelCredentials(op);
        } else {
            return executeUsingLogin(op);
        }
    }

    protected <R> R executeUsingLogin(PortOperation<R> op) throws NetSuiteException {
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

    private <R> R executeUsingRequestLevelCredentials(PortOperation<R> op) throws NetSuiteException {
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

    protected void setHeader(NetSuitePortType port, Header header) {
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

    protected void removeHeader(NetSuitePortType port, QName name) {
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

        SearchPreferencesEx searchPreferences = new SearchPreferencesEx();
        searchPreferences.setPageSize(searchPageSize);
        searchPreferences.setBodyFieldsOnly(Boolean.valueOf(bodyFieldsOnly));

        this.searchPreferences = searchPreferences;

        PreferencesEx preferences = new PreferencesEx();
        preferences.setDisableMandatoryCustomFieldValidation(disableMandatoryCustomFieldValidation);
        preferences.setWarningAsError(treatWarningsAsErrors);

        this.preferences = preferences;

        setPreferences(port, preferences, searchPreferences);

        loggedIn = true;
    }

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

    protected void setPreferences(NetSuitePortType port,
            PreferencesEx nsPreferences, SearchPreferencesEx nsSearchPreferences) throws NetSuiteException {

        SearchPreferences searchPreferences = new SearchPreferences();
        Preferences preferences = new Preferences();
        try {
            BeanUtils.copyProperties(searchPreferences, nsSearchPreferences);
            BeanUtils.copyProperties(preferences, nsPreferences);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }

        try {
            Header searchPreferencesHeader = new Header(new QName(NS_URI_PLATFORM_MESSAGES, "searchPreferences"),
                    searchPreferences, new JAXBDataBinding(searchPreferences.getClass()));

            Header preferencesHeader = new Header(new QName(NS_URI_PLATFORM_MESSAGES, "preferences"),
                    preferences, new JAXBDataBinding(preferences.getClass()));

            setHeader(port, preferencesHeader);
            setHeader(port, searchPreferencesHeader);

        } catch (JAXBException e) {
            throw new NetSuiteException("XML binding error", e);
        }
    }

    protected void setLoginHeaders(NetSuitePortType port) throws NetSuiteException {
        if (credentials.getApplicationId() != null) {
            ApplicationInfo applicationInfo = new ApplicationInfo();
            applicationInfo.setApplicationId(credentials.getApplicationId());

            try {
                if (applicationInfo != null) {
                    Header appInfoHeader = new Header(new QName(NS_URI_PLATFORM_MESSAGES, "applicationInfo"),
                            applicationInfo, new JAXBDataBinding(applicationInfo.getClass()));
                    setHeader(port, appInfoHeader);
                }
            } catch (JAXBException e) {
                throw new NetSuiteException("XML binding error", e);
            }
        }
    }

    protected void remoteLoginHeaders(NetSuitePortType port) throws NetSuiteException {
        removeHeader(port, new QName(NS_URI_PLATFORM_MESSAGES, "applicationInfo"));
    }

    protected void doLogout() throws NetSuiteException {
        try {
            LogoutRequest request = new LogoutRequest();
            port.logout(request);
        } catch (Exception e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    protected void doLogin() throws NetSuiteException {
        port = getNetSuitePort(endpointUrl, credentials.getAccount());

        setLoginHeaders(port);

        PortOperation<SessionResponse> loginOp;
        if (!credentials.isUseSsoLogin()) {
            final Passport passport = createPassport();
            loginOp = new PortOperation<SessionResponse>() {
                @Override public SessionResponse execute(NetSuitePortType port) throws Exception {
                    LoginRequest request = new LoginRequest();
                    request.setPassport(passport);
                    LoginResponse response = port.login(request);
                    return response.getSessionResponse();
                }
            };
        } else {
            throw new NetSuiteException("SSO login not supported");
        }

        Status status = null;
        SessionResponse sessionResponse;
        String exceptionMessage = null;
        for (int i = 0; i < getRetryCount(); i++) {
            try {
                sessionResponse = loginOp.execute(port);
                status = sessionResponse.getStatus();

            } catch (InvalidCredentialsFault f) {
                throw new NetSuiteException(f.getFaultInfo().getMessage());
            } catch (UnexpectedErrorFault f) {
                exceptionMessage = f.getFaultInfo().getMessage();
            } catch (Exception e) {
                exceptionMessage = e.getMessage();
            }

            if (status != null) {
                break;
            }

            if (i != getRetryCount() - 1) {
                waitForRetryInterval();
            }
        }

        if (status == null || !status.getIsSuccess()) {
            String message = "Login Failed:";
            if (status != null && status.getStatusDetail() != null && status.getStatusDetail().size() > 0) {
                message = message + " " + status.getStatusDetail().get(0).getCode();
                message = message + " " + status.getStatusDetail().get(0).getMessage();
            } else if (exceptionMessage != null) {
                message = message + " " + exceptionMessage;
            }

            throw new NetSuiteException(message);
        }

        remoteLoginHeaders(port);
    }

    private Passport createPassport() {
        RecordRef roleRecord = new RecordRef();
        roleRecord.setInternalId(credentials.getRoleId());

        final Passport passport = new Passport();
        passport.setEmail(credentials.getEmail());
        passport.setPassword(credentials.getPassword());
        passport.setRole(roleRecord);
        passport.setAccount(credentials.getAccount());

        return passport;
    }

    private NetSuitePortType getNetSuitePort(String defaultEndpointUrl, String account) throws NetSuiteException {
        try {
            URL wsdlLocationUrl = this.getClass().getResource("/wsdl/2016.2/netsuite.wsdl");

            NetSuiteService service = new NetSuiteService(wsdlLocationUrl, NetSuiteService.SERVICE);

            List<WebServiceFeature> features = new ArrayList<>(2);
            if (isMessageLoggingEnabled()) {
                features.add(new LoggingFeature());
            }
            NetSuitePortType port = service.getNetSuitePort(
                    features.toArray(new WebServiceFeature[features.size()]));

            BindingProvider provider = (BindingProvider) port;
            Map<String, Object> requestContext = provider.getRequestContext();
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, defaultEndpointUrl);

            GetDataCenterUrlsRequest dataCenterRequest = new GetDataCenterUrlsRequest();
            dataCenterRequest.setAccount(account);
            DataCenterUrls urls = null;
            GetDataCenterUrlsResponse response = port.getDataCenterUrls(dataCenterRequest);
            if (response != null && response.getGetDataCenterUrlsResult() != null) {
                urls = response.getGetDataCenterUrlsResult().getDataCenterUrls();
            }
            if (urls == null) {
                throw new NetSuiteException("Can't get a correct webservice domain! "
                        + "Please check your configuration or try to run again.");
            }

            String wsDomain = urls.getWebservicesDomain();
            String endpointUrl = wsDomain.concat(new URL(defaultEndpointUrl).getPath());

            requestContext.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            return port;
        } catch (WebServiceException | MalformedURLException | InsufficientPermissionFault | InvalidCredentialsFault | InvalidSessionFault |
                UnexpectedErrorFault | ExceededRequestSizeFault e) {
            throw new NetSuiteException("Failed to get NetSuite port due to error", e);
        }
    }

    private boolean errorCanBeWorkedAround (Throwable t) {
        if (t instanceof InvalidSessionFault ||
                t instanceof RemoteException ||
                t instanceof SOAPFaultException ||
                t instanceof SocketException)
            return true;

        return false;
    }

    protected List<WriteResponseEx> toWriteResponseExList(WriteResponseList writeResponseList) {
        List<WriteResponseEx> nsWriteResponses = new ArrayList<>(writeResponseList.getWriteResponse().size());
        for (WriteResponse writeResponse : writeResponseList.getWriteResponse()) {
            nsWriteResponses.add(new WriteResponseEx(writeResponse));
        }
        return nsWriteResponses;
    }

    public interface PortOperation<R> {
        R execute(NetSuitePortType port) throws Exception;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Meta Data
    ///////////////////////////////////////////////////////////////////////////

    private static StandardMetaData standardMetaData = new StandardMetaData();

    public Collection<String> getRecordTypes() {
        return standardMetaData.getRecordTypes();
    }

    public Collection<String> getTransactionTypes() {
        return standardMetaData.getTransactionTypes();
    }

    public Collection<String> getItemTypes() {
        return standardMetaData.getItemTypes();
    }

    public TypeDef getTypeDef(String typeName) {
        return standardMetaData.getTypeDef(typeName);
    }

    public TypeDef getTypeDef(Class<?> clazz) {
        return standardMetaData.getTypeDef(clazz);
    }

    public RecordTypeDef getRecordTypeDef(String typeName) {
        return standardMetaData.getRecordTypeDef(typeName);
    }

    public SearchRecordDef getSearchRecordDef(String typeName) {
        return standardMetaData.getSearchRecordDef(typeName);
    }

    public Class<?> getSearchFieldClass(String searchFieldType) {
        return standardMetaData.getSearchFieldClass(searchFieldType);
    }

    public Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName) {
        return standardMetaData.getSearchFieldOperatorByName(searchFieldType, searchFieldOperatorName);
    }

    public Collection<SearchFieldOperatorTypeDef.QualifiedName> getSearchOperatorNames() {
        return standardMetaData.getSearchOperatorNames();
    }

    public <T> T createType(String typeName) throws NetSuiteException {
        TypeDef typeDef = getTypeDef(typeName);
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

    protected void updateCustomMetaData() throws NetSuiteException {

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

    public static void main(String... args) throws Exception {
        new NetSuiteClientService();
    }
}
