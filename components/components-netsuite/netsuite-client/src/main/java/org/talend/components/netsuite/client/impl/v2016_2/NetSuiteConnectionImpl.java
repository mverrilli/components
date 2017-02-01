package org.talend.components.netsuite.client.impl.v2016_2;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.components.netsuite.client.NsObject;
import org.talend.components.netsuite.client.NsWriteResponseList;
import org.talend.components.netsuite.client.NsPreferences;
import org.talend.components.netsuite.client.NsRef;
import org.talend.components.netsuite.client.NsSearchPreferences;
import org.talend.components.netsuite.client.NsSearchRecord;
import org.talend.components.netsuite.client.NsSearchResult;
import org.talend.components.netsuite.client.NsWriteResponse;

import com.netsuite.webservices.v2016_2.platform.ExceededRequestSizeFault;
import com.netsuite.webservices.v2016_2.platform.InsufficientPermissionFault;
import com.netsuite.webservices.v2016_2.platform.InvalidCredentialsFault;
import com.netsuite.webservices.v2016_2.platform.InvalidSessionFault;
import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.NetSuiteService;
import com.netsuite.webservices.v2016_2.platform.UnexpectedErrorFault;
import com.netsuite.webservices.v2016_2.platform.core.BaseRef;
import com.netsuite.webservices.v2016_2.platform.core.DataCenterUrls;
import com.netsuite.webservices.v2016_2.platform.core.Passport;
import com.netsuite.webservices.v2016_2.platform.core.Record;
import com.netsuite.webservices.v2016_2.platform.core.RecordRef;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecord;
import com.netsuite.webservices.v2016_2.platform.core.SearchResult;
import com.netsuite.webservices.v2016_2.platform.core.Status;
import com.netsuite.webservices.v2016_2.platform.messages.AddListRequest;
import com.netsuite.webservices.v2016_2.platform.messages.AddRequest;
import com.netsuite.webservices.v2016_2.platform.messages.ApplicationInfo;
import com.netsuite.webservices.v2016_2.platform.messages.DeleteListRequest;
import com.netsuite.webservices.v2016_2.platform.messages.DeleteRequest;
import com.netsuite.webservices.v2016_2.platform.messages.GetDataCenterUrlsRequest;
import com.netsuite.webservices.v2016_2.platform.messages.GetDataCenterUrlsResponse;
import com.netsuite.webservices.v2016_2.platform.messages.LoginRequest;
import com.netsuite.webservices.v2016_2.platform.messages.LoginResponse;
import com.netsuite.webservices.v2016_2.platform.messages.LogoutRequest;
import com.netsuite.webservices.v2016_2.platform.messages.Preferences;
import com.netsuite.webservices.v2016_2.platform.messages.SearchMoreRequest;
import com.netsuite.webservices.v2016_2.platform.messages.SearchMoreWithIdRequest;
import com.netsuite.webservices.v2016_2.platform.messages.SearchNextRequest;
import com.netsuite.webservices.v2016_2.platform.messages.SearchPreferences;
import com.netsuite.webservices.v2016_2.platform.messages.SearchRequest;
import com.netsuite.webservices.v2016_2.platform.messages.SessionResponse;
import com.netsuite.webservices.v2016_2.platform.messages.UpdateListRequest;
import com.netsuite.webservices.v2016_2.platform.messages.UpdateRequest;
import com.netsuite.webservices.v2016_2.platform.messages.UpsertListRequest;
import com.netsuite.webservices.v2016_2.platform.messages.UpsertRequest;
import com.netsuite.webservices.v2016_2.platform.messages.WriteResponse;
import com.netsuite.webservices.v2016_2.platform.messages.WriteResponseList;

import static org.talend.components.netsuite.client.NsObject.asNsObject;

/**
 *
 */
public class NetSuiteConnectionImpl extends NetSuiteConnection<NetSuitePortType> {

    public static final String DEFAULT_ENDPOINT_URL =
            "https://webservices.netsuite.com/services/NetSuitePort_2016_2";

    public static final String NS_URI_PLATFORM_MESSAGES =
            "urn:messages_2016_2.platform.webservices.netsuite.com";

    private final static NetSuiteMetaDataImpl metaData = NetSuiteMetaDataImpl.getInstance();

    public NetSuiteConnectionImpl() {
    }

    public NetSuiteMetaData getMetaData() throws NetSuiteException {
        return metaData;
    }

    public NsSearchResult search(final NsSearchRecord searchRecord) throws NetSuiteException {
        return execute(new PortOperation<NsSearchResult, NetSuitePortType>() {
            @Override public NsSearchResult execute(NetSuitePortType port) throws Exception {
                SearchRequest request = new SearchRequest();
                request.setSearchRecord((SearchRecord) searchRecord.getTarget());

                SearchResult result = port.search(request).getSearchResult();
                return toNsSearchResult(result);
            }
        });
    }

    public NsSearchResult searchMore(final int pageIndex) throws NetSuiteException {
        return execute(new PortOperation<NsSearchResult, NetSuitePortType>() {
            @Override public NsSearchResult execute(NetSuitePortType port) throws Exception {
                SearchMoreRequest request = new SearchMoreRequest();
                request.setPageIndex(pageIndex);

                SearchResult result = port.searchMore(request).getSearchResult();
                return toNsSearchResult(result);
            }
        });
    }

    public NsSearchResult searchMoreWithId(final String searchId, final int pageIndex) throws NetSuiteException {
        return execute(new PortOperation<NsSearchResult, NetSuitePortType>() {
            @Override public NsSearchResult execute(NetSuitePortType port) throws Exception {
                SearchMoreWithIdRequest request = new SearchMoreWithIdRequest();
                request.setSearchId(searchId);
                request.setPageIndex(pageIndex);

                SearchResult result = port.searchMoreWithId(request).getSearchResult();
                return toNsSearchResult(result);
            }
        });
    }

    public NsSearchResult searchNext() throws NetSuiteException {
        return execute(new PortOperation<NsSearchResult, NetSuitePortType>() {
            @Override public NsSearchResult execute(NetSuitePortType port) throws Exception {
                SearchNextRequest request = new SearchNextRequest();
                SearchResult result = port.searchNext(request).getSearchResult();
                return toNsSearchResult(result);
            }
        });
    }

    public NsWriteResponse add(final NsObject record) throws NetSuiteException {
        if (record == null) {
            return new NsWriteResponse(new WriteResponse());
        }
        return execute(new PortOperation<NsWriteResponse, NetSuitePortType>() {
            @Override public NsWriteResponse execute(NetSuitePortType port) throws Exception {
                AddRequest request = new AddRequest();
                Record r = (Record) record.getTarget();
                request.setRecord(r);

                WriteResponse response = port.add(request).getWriteResponse();
                NsWriteResponse nsResponse = new NsWriteResponse(response);
                return nsResponse;
            }
        });
    }

    public NsWriteResponseList addList(final List<NsObject> records) throws NetSuiteException {
        if (records == null || records.isEmpty()) {
            NsWriteResponseList responseList = new NsWriteResponseList(new WriteResponseList());
            responseList.setResponses(Collections.<NsWriteResponse>emptyList());
            return responseList;
        }
        return execute(new PortOperation<NsWriteResponseList, NetSuitePortType>() {
            @Override public NsWriteResponseList execute(NetSuitePortType port) throws Exception {
                AddListRequest request = new AddListRequest();
                List<Record> recordList = toRecordList(records);
                request.getRecord().addAll(recordList);

                WriteResponseList writeResponseList = port.addList(request).getWriteResponseList();
                return toNsWriteResponseList(writeResponseList);
            }
        });
    }

    public NsWriteResponse update(final NsObject record) throws NetSuiteException {
        if (record == null) {
            return new NsWriteResponse(new WriteResponse());
        }
        return execute(new PortOperation<NsWriteResponse, NetSuitePortType>() {
            @Override public NsWriteResponse execute(NetSuitePortType port) throws Exception {
                UpdateRequest request = new UpdateRequest();
                Record r = (Record) record.getTarget();
                request.setRecord(r);

                WriteResponse response = port.update(request).getWriteResponse();
                NsWriteResponse nsResponse = new NsWriteResponse(response);
                return nsResponse;
            }
        });
    }

    public NsWriteResponseList updateList(final List<NsObject> records) throws NetSuiteException {
        if (records == null || records.isEmpty()) {
            NsWriteResponseList responseList = new NsWriteResponseList(new WriteResponseList());
            responseList.setResponses(Collections.<NsWriteResponse>emptyList());
            return responseList;
        }
        return execute(new PortOperation<NsWriteResponseList, NetSuitePortType>() {
            @Override public NsWriteResponseList execute(NetSuitePortType port) throws Exception {
                UpdateListRequest request = new UpdateListRequest();
                List<Record> recordList = toRecordList(records);
                request.getRecord().addAll(recordList);

                WriteResponseList writeResponseList = port.updateList(request).getWriteResponseList();
                return toNsWriteResponseList(writeResponseList);
            }
        });
    }

    public NsWriteResponse upsert(final NsObject record) throws NetSuiteException {
        if (record == null) {
            return new NsWriteResponse(new WriteResponse());
        }
        return execute(new PortOperation<NsWriteResponse, NetSuitePortType>() {
            @Override public NsWriteResponse execute(NetSuitePortType port) throws Exception {
                UpsertRequest request = new UpsertRequest();
                Record r = (Record) record.getTarget();
                request.setRecord(r);

                WriteResponse response = port.upsert(request).getWriteResponse();
                NsWriteResponse nsResponse = new NsWriteResponse(response);
                return nsResponse;
            }
        });
    }

    public NsWriteResponseList upsertList(final List<NsObject> records) throws NetSuiteException {
        if (records == null || records.isEmpty()) {
            NsWriteResponseList responseList = new NsWriteResponseList(new WriteResponseList());
            responseList.setResponses(Collections.<NsWriteResponse>emptyList());
            return responseList;
        }
        return execute(new PortOperation<NsWriteResponseList, NetSuitePortType>() {
            @Override public NsWriteResponseList execute(NetSuitePortType port) throws Exception {
                UpsertListRequest request = new UpsertListRequest();
                List<Record> recordList = toRecordList(records);
                request.getRecord().addAll(recordList);

                WriteResponseList writeResponseList = port.upsertList(request).getWriteResponseList();
                return toNsWriteResponseList(writeResponseList);
            }
        });
    }

    public NsWriteResponse delete(final NsRef ref) throws NetSuiteException {
        if (ref == null) {
            return new NsWriteResponse(new WriteResponse());
        }
        return execute(new PortOperation<NsWriteResponse, NetSuitePortType>() {
            @Override public NsWriteResponse execute(NetSuitePortType port) throws Exception {
                DeleteRequest request = new DeleteRequest();
                BaseRef baseRef = (BaseRef) ref.getTarget();
                request.setBaseRef(baseRef);

                WriteResponse writeResponse = port.delete(request).getWriteResponse();
                NsWriteResponse nsWriteResponse = new NsWriteResponse(writeResponse);
                return nsWriteResponse;
            }
        });
    }

    public NsWriteResponseList deleteList(final List<NsRef> refs) throws NetSuiteException {
        if (refs == null || refs.isEmpty()) {
            NsWriteResponseList responseList = new NsWriteResponseList(new WriteResponseList());
            responseList.setResponses(Collections.<NsWriteResponse>emptyList());
            return responseList;
        }
        return execute(new PortOperation<NsWriteResponseList, NetSuitePortType>() {
            @Override public NsWriteResponseList execute(NetSuitePortType port) throws Exception {
                DeleteListRequest request = new DeleteListRequest();
                List<BaseRef> baseRefList = toBaseRefList(refs);
                request.getBaseRef().addAll(baseRefList);

                WriteResponseList writeResponseList = port.deleteList(request).getWriteResponseList();
                return toNsWriteResponseList(writeResponseList);
            }
        });
    }

    protected NsSearchResult toNsSearchResult(SearchResult result) {
        NsSearchResult nsResult = new NsSearchResult();
        if (result.getStatus().getIsSuccess()) {
            nsResult.setSuccess(true);
        }
        nsResult.setSearchId(result.getSearchId());
        nsResult.setTotalPages(result.getTotalPages());
        nsResult.setTotalRecords(result.getTotalRecords());
        nsResult.setPageIndex(result.getPageIndex());
        nsResult.setPageSize(result.getPageSize());
        List<NsObject> nsRecordList = new ArrayList<>(result.getRecordList().getRecord().size());
        for (Record record : result.getRecordList().getRecord()) {
            NsObject nsRecord = asNsObject(record);
            nsRecordList.add(nsRecord);
        }
        nsResult.setRecordList(nsRecordList);
        return nsResult;
    }

    protected NsWriteResponseList toNsWriteResponseList(WriteResponseList writeResponseList) {
        List<NsWriteResponse> nsWriteResponses = new ArrayList<>(writeResponseList.getWriteResponse().size());
        for (WriteResponse writeResponse : writeResponseList.getWriteResponse()) {
            NsWriteResponse nsWriteResponse = new NsWriteResponse(writeResponse);
            nsWriteResponses.add(nsWriteResponse);
        }
        NsWriteResponseList nsWriteResponseList = new NsWriteResponseList(writeResponseList);
        nsWriteResponseList.setResponses(nsWriteResponses);
        return nsWriteResponseList;
    }

    protected List<Record> toRecordList(List<NsObject> nsRecordList) {
        List<Record> recordList = new ArrayList<>(nsRecordList.size());
        for (NsObject nsRecord : nsRecordList) {
            Record r = (Record) nsRecord.getTarget();
            recordList.add(r);
        }
        return recordList;
    }

    protected List<BaseRef> toBaseRefList(List<NsRef> nsRefList) {
        List<BaseRef> baseRefList = new ArrayList<>(nsRefList.size());
        for (NsRef nsRef : nsRefList) {
            BaseRef r = (BaseRef) nsRef.getTarget();
            baseRefList.add(r);
        }
        return baseRefList;
    }

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

    protected void setPreferences(NetSuitePortType port,
            NsPreferences nsPreferences, NsSearchPreferences nsSearchPreferences) throws NetSuiteException {

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

        PortOperation<SessionResponse, NetSuitePortType> loginOp;
        if (!credentials.isUseSsoLogin()) {
            final Passport passport = createPassport();
            loginOp = new PortOperation<SessionResponse, NetSuitePortType>() {
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
        } catch (WebServiceException | MalformedURLException |
                InsufficientPermissionFault | InvalidCredentialsFault | InvalidSessionFault |
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
}
