package org.talend.components.netsuite.client;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.netsuite.webservices.platform.NetSuitePortType;
import com.netsuite.webservices.platform.common.CustomRecordSearchBasic;
import com.netsuite.webservices.platform.core.CustomRecordRef;
import com.netsuite.webservices.platform.core.CustomizationRef;
import com.netsuite.webservices.platform.core.CustomizationType;
import com.netsuite.webservices.platform.core.GetCustomizationIdResult;
import com.netsuite.webservices.platform.core.GetSavedSearchRecord;
import com.netsuite.webservices.platform.core.GetSavedSearchResult;
import com.netsuite.webservices.platform.core.RecordRef;
import com.netsuite.webservices.platform.core.SearchResult;
import com.netsuite.webservices.platform.core.types.GetCustomizationType;
import com.netsuite.webservices.platform.core.types.SearchRecordType;
import com.netsuite.webservices.platform.messages.GetCustomizationIdRequest;
import com.netsuite.webservices.platform.messages.GetListRequest;
import com.netsuite.webservices.platform.messages.GetSavedSearchRequest;
import com.netsuite.webservices.platform.messages.ReadResponseList;
import com.netsuite.webservices.platform.messages.SearchRequest;
import com.netsuite.webservices.setup.customization.CustomRecordSearchAdvanced;

/**
 *
 */
@Ignore
public class NetSuiteGetCustomizationsIT {

    protected static NetSuiteWebServiceTestFixture webServiceTestFixture;

    @BeforeClass
    public static void classSetUp() throws Exception {
        webServiceTestFixture = new NetSuiteWebServiceTestFixture();
        webServiceTestFixture.setUp();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        webServiceTestFixture.tearDown();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUpdateCustomMetaData() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        connection.login();

        connection.updateCustomMetaData();
    }

    @Test
    public void testGetCustomizations() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        connection.login();

        final GetCustomizationType getCustomizationType = GetCustomizationType.CUSTOM_TRANSACTION_TYPE;

        GetCustomizationIdResult result = connection.execute(
                new NetSuiteClientService.PortOperation<GetCustomizationIdResult>() {
            @Override public GetCustomizationIdResult execute(NetSuitePortType port) throws Exception {
                final GetCustomizationIdRequest request = new GetCustomizationIdRequest();
                CustomizationType customizationType = new CustomizationType();
                customizationType.setGetCustomizationType(getCustomizationType);
                request.setCustomizationType(customizationType);
                return port.getCustomizationId(request).getGetCustomizationIdResult();
            }
        });

        for (final CustomizationRef ref : result.getCustomizationRefList().getCustomizationRef()) {
            System.out.println(ref.getScriptId() + ", " + ref.getInternalId() + ", " + ref.getExternalId() + ", " + ref.getName());
//            ReadResponseList result2 = clientService.execute(
//                    new NetSuiteClientService.PortOperation<ReadResponseList, NetSuitePortType>() {
//                        @Override public ReadResponseList execute(NetSuitePortType port) throws Exception {
//                            final GetListRequest request = new GetListRequest();
//                            request.getBaseRef().add(ref);
//                            return port.getList(request).getReadResponseList();
//                        }
//                    });
//            System.out.println(result2);
        }
    }

    @Test
    public void testGetSavedSearch() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        connection.login();

        SearchRecordType[] searchRecordTypes = new SearchRecordType[]{SearchRecordType.PHONE_CALL};

        final List<RecordRef> recordRefList = new ArrayList<>();

        String searchName = "AH - Bookings Report BVA";

        //        for (SearchRecordType searchRecordType : searchRecordTypes) {
//            System.out.println("" + searchRecordType.value());
            final GetSavedSearchRecord savedSearchRecord = new GetSavedSearchRecord();
            savedSearchRecord.setSearchType(SearchRecordType.CUSTOM_RECORD);

            GetSavedSearchResult result = connection.execute(new NetSuiteClientService.PortOperation<GetSavedSearchResult>() {

                @Override public GetSavedSearchResult execute(NetSuitePortType port) throws Exception {
                            final GetSavedSearchRequest request = new GetSavedSearchRequest();
                            request.setRecord(savedSearchRecord);
                            return port.getSavedSearch(request).getGetSavedSearchResult();
                        }
                    });

            for (final RecordRef ref : result.getRecordRefList().getRecordRef()) {
                if (ref instanceof CustomizationRef) {
                    CustomizationRef customizationRef = (CustomizationRef) ref;
                    System.out.println("Custom:    " + customizationRef.getScriptId() + ", " + ref.getInternalId() + ", " + ref.getExternalId() + ", " + ref.getName() + ", " + customizationRef.getType());
                } else {
                    System.out.println("    " + ref.getInternalId() + ", " + ref.getName());
                }

                if (searchName.equals(ref.getName())) {
                    recordRefList.add(ref);
                }

            }

//        }

        if (!recordRefList.isEmpty()) {
            final RecordRef ref = recordRefList.get(0);

            final CustomRecordSearchAdvanced searchAdvanced = new CustomRecordSearchAdvanced();
            CustomRecordSearchBasic searchBasic = new CustomRecordSearchBasic();
            searchAdvanced.setSavedSearchId(ref.getInternalId());
            savedSearchRecord.setSearchType(SearchRecordType.CUSTOM_RECORD);
            SearchResult searchResult = connection.execute(new NetSuiteClientService.PortOperation<SearchResult>() {

                @Override public SearchResult execute(NetSuitePortType port) throws Exception {
                            SearchRequest searchRequest = new SearchRequest();
                            searchRequest.setSearchRecord(searchAdvanced);
                            return port.search(searchRequest).getSearchResult();
                        }
                    });
            System.out.println(searchResult);

            ReadResponseList result2 = connection.execute(
                    new NetSuiteClientService.PortOperation<ReadResponseList>() {
                @Override public ReadResponseList execute(NetSuitePortType port) throws Exception {
                    final GetListRequest request = new GetListRequest();
                    if (ref instanceof CustomizationRef) {
                        CustomizationRef customizationRef = (CustomizationRef) ref;
                        CustomRecordRef recordRef = new CustomRecordRef();
//                        recordRef.setTypeId(StandardMetaData.SAVED_SEARCH_TYPE_ID);
//                        recordRef.setType(RecordType.CUSTOM_RECORD);
                        recordRef.setInternalId(customizationRef.getInternalId());
                        recordRef.setScriptId(customizationRef.getScriptId());
                        request.getBaseRef().add(recordRef);
                    }
                    return port.getList(request).getReadResponseList();
                }});
            System.out.println(result2);

        } else {
            System.out.println("Not found: " + searchName);
        }

//        ReadResponseList result2 = clientService.execute(
//                new NetSuiteClientService.PortOperation<ReadResponseList, NetSuitePortType>() {
//            @Override public ReadResponseList execute(NetSuitePortType port) throws Exception {
//                        final GetListRequest request = new GetListRequest();
//                        request.getBaseRef().addAll(recordRefList);
//                        return port.getList(request).getReadResponseList();
//                    }
//                });
//        for (ReadResponse readResponse : result2.getReadResponse()) {
//            System.out.println(readResponse.getRecord().getClass());
//        }

        System.out.println();

        //
//        for (final RecordRef ref : result.getRecordRefList().getRecordRef()) {
//            if (ref instanceof CustomizationRef) {
//                CustomizationRef customizationRef = (CustomizationRef) ref;
//                customizationRef.setType(RecordType.CUSTOM_RECORD);
//                System.out.println(customizationRef.getScriptId() + ", " + ref.getInternalId() + ", " + ref.getExternalId());
//                ReadResponseList result2 = clientService.execute(new NetSuiteClientService.PortOperation<ReadResponseList, NetSuitePortType>() {
//
//                    @Override public ReadResponseList execute(NetSuitePortType port) throws Exception {
//                                final GetListRequest request = new GetListRequest();
//                                request.getBaseRef().add(ref);
//                                return port.getList(request).getReadResponseList();
//                            }
//                        });
//                System.out.println(result2);
//            }
//        }
    }

    @Test
    public void testGetCustomRecordType() throws Exception {
        NetSuiteClientService connection = webServiceTestFixture.getClientService();

        connection.login();

        final GetCustomizationType getCustomizationType = GetCustomizationType.TRANSACTION_BODY_CUSTOM_FIELD;

        GetCustomizationIdResult result = connection.execute(
                new NetSuiteClientService.PortOperation<GetCustomizationIdResult>() {
                    @Override public GetCustomizationIdResult execute(NetSuitePortType port) throws Exception {
                        final GetCustomizationIdRequest request = new GetCustomizationIdRequest();
                        CustomizationType customizationType = new CustomizationType();
                        customizationType.setGetCustomizationType(getCustomizationType);
                        request.setCustomizationType(customizationType);
                        return port.getCustomizationId(request).getGetCustomizationIdResult();
                    }
                });

        for (final CustomizationRef ref : result.getCustomizationRefList().getCustomizationRef()) {
            System.out.println(ref.getScriptId() + ", " + ref.getInternalId() + ", " + ref.getExternalId() + ", " + ref.getName());
            //            ReadResponseList result2 = clientService.execute(
            //                    new NetSuiteClientService.PortOperation<ReadResponseList, NetSuitePortType>() {
            //                        @Override public ReadResponseList execute(NetSuitePortType port) throws Exception {
            //                            final GetListRequest request = new GetListRequest();
            //                            request.getBaseRef().add(ref);
            //                            return port.getList(request).getReadResponseList();
            //                        }
            //                    });
            //            System.out.println(result2);
        }
    }
}