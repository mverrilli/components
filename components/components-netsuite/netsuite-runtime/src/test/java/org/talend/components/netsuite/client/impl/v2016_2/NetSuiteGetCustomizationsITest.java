package org.talend.components.netsuite.client.impl.v2016_2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.components.netsuite.client.NetSuiteConnection;

import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.CustomizationRef;
import com.netsuite.webservices.v2016_2.platform.core.CustomizationType;
import com.netsuite.webservices.v2016_2.platform.core.GetCustomizationIdResult;
import com.netsuite.webservices.v2016_2.platform.core.types.GetCustomizationType;
import com.netsuite.webservices.v2016_2.platform.messages.GetCustomizationIdRequest;
import com.netsuite.webservices.v2016_2.platform.messages.GetListRequest;
import com.netsuite.webservices.v2016_2.platform.messages.ReadResponseList;

/**
 *
 */
public class NetSuiteGetCustomizationsITest {

    protected static NetSuiteWebServiceTestFixture<NetSuitePortType> webServiceTestFixture;

    @BeforeClass
    public static void classSetUp() throws Exception {
        webServiceTestFixture = new NetSuiteWebServiceTestFixture<>();
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
    public void testGetCustomizations() throws Exception {
        NetSuiteConnection<NetSuitePortType> connection = webServiceTestFixture.getConnection();

        connection.login();

        final GetCustomizationType getCustomizationType = GetCustomizationType.CUSTOM_LIST;

        GetCustomizationIdResult result = connection.execute(
                new NetSuiteConnection.PortOperation<GetCustomizationIdResult, NetSuitePortType>() {
            @Override public GetCustomizationIdResult execute(NetSuitePortType port) throws Exception {
                final GetCustomizationIdRequest request = new GetCustomizationIdRequest();
                request.setIncludeInactives(true);
                CustomizationType customizationType = new CustomizationType();
                customizationType.setGetCustomizationType(getCustomizationType);
                request.setCustomizationType(customizationType);
                return port.getCustomizationId(request).getGetCustomizationIdResult();
            }
        });

        for (final CustomizationRef ref : result.getCustomizationRefList().getCustomizationRef()) {
            System.out.println(ref.getScriptId() + ", " + ref.getInternalId() + ", " + ref.getExternalId());
            ReadResponseList result2 = connection.execute(
                    new NetSuiteConnection.PortOperation<ReadResponseList, NetSuitePortType>() {
                        @Override public ReadResponseList execute(NetSuitePortType port) throws Exception {
                            final GetListRequest request = new GetListRequest();
                            request.getBaseRef().add(ref);
                            return port.getList(request).getReadResponseList();
                        }
                    });
            System.out.println(result2);
        }
    }

}