package org.talend.components.netsuite.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.headers.Header;

/**
 *
 */
public abstract class NetSuiteConnection<P> {

    public static final int DEFAULT_PAGE_SIZE = 100;

    protected String endpointUrl;

    protected NetSuiteCredentials credentials;

    protected NsSearchPreferences searchPreferences;
    protected NsPreferences preferences;

    private ReentrantLock lock = new ReentrantLock();

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

    protected NetSuiteConnection() {
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

    public abstract NsSearchResult search(NsSearchRecord searchRecord) throws NetSuiteException;

    public abstract NsSearchResult searchMore(int pageIndex) throws NetSuiteException;

    public abstract NsSearchResult searchMoreWithId(String searchId, int pageIndex) throws NetSuiteException;

    public abstract NsSearchResult searchNext() throws NetSuiteException;

    public abstract NsWriteResponse add(NsObject record) throws NetSuiteException;

    public abstract NsWriteResponseList addList(List<NsObject> records) throws NetSuiteException;

    public abstract NsWriteResponse update(NsObject record) throws NetSuiteException;

    public abstract NsWriteResponseList updateList(List<NsObject> records) throws NetSuiteException;

    public abstract NsWriteResponse upsert(NsObject record) throws NetSuiteException;

    public abstract NsWriteResponseList upsertList(List<NsObject> records) throws NetSuiteException;

    public abstract NsWriteResponse delete(NsRef ref) throws NetSuiteException;

    public abstract NsWriteResponseList deleteList(List<NsRef> refList) throws NetSuiteException;

//    public GetSelectValueResult getSelectValue(final GetSelectValueRequest request) throws NetSuiteException {
//        return execute(new PortOp<GetSelectValueResult>() {
//            @Override public GetSelectValueResult execute(NetSuitePortType port) throws Exception {
//                return port.getSelectValue(request).getGetSelectValueResult();
//            }
//        });
//    }
//
//    public GetCustomizationIdResult getCustomizationId(final GetCustomizationIdRequest request) throws NetSuiteException {
//        return execute(new PortOp<GetCustomizationIdResult>() {
//            @Override public GetCustomizationIdResult execute(NetSuitePortType port) throws Exception {
//                return port.getCustomizationId(request).getGetCustomizationIdResult();
//            }
//        });
//    }
//
//    public GetSavedSearchResult getSavedSearch(final GetSavedSearchRecord record) throws NetSuiteException {
//        return execute(new PortOp<GetSavedSearchResult>() {
//            @Override public GetSavedSearchResult execute(NetSuitePortType port) throws Exception {
//                GetSavedSearchRequest request = new GetSavedSearchRequest();
//                request.setRecord(record);
//                return port.getSavedSearch(request).getGetSavedSearchResult();
//            }
//        });
//    }
//
//    public GetItemAvailabilityResult getItemAvailability(final ItemAvailabilityFilter filter) throws NetSuiteException {
//        return execute(new PortOp<GetItemAvailabilityResult>() {
//            @Override public GetItemAvailabilityResult execute(NetSuitePortType port) throws Exception {
//                GetItemAvailabilityRequest request = new GetItemAvailabilityRequest();
//                request.setItemAvailabilityFilter(filter);
//                return port.getItemAvailability(request).getGetItemAvailabilityResult();
//            }
//        });
//    }

    protected <R> R execute(PortOperation<R, P> op) throws NetSuiteException {
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

    protected interface PortOperation<R, P> {
        R execute(P port) throws Exception;
    }
}
