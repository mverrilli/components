package org.talend.components.netsuite.v2016_2;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.talend.components.netsuite.AbstractNetSuiteComponentMockTestFixture;
import org.talend.components.netsuite.NetSuiteWebServiceMockTestFixture;

import com.netsuite.webservices.v2016_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2016_2.platform.core.Status;
import com.netsuite.webservices.v2016_2.platform.messages.LoginRequest;
import com.netsuite.webservices.v2016_2.platform.messages.LoginResponse;
import com.netsuite.webservices.v2016_2.platform.messages.SessionResponse;

/**
 *
 */
public class NetSuiteComponentMockTestFixture
        extends AbstractNetSuiteComponentMockTestFixture<NetSuitePortType> {

    public NetSuiteComponentMockTestFixture(
            NetSuiteWebServiceMockTestFixture<NetSuitePortType, ?> webServiceMockTestFixture) {
        super(webServiceMockTestFixture);
    }

    @Override
    protected void mockLoginResponse(NetSuitePortType port) throws Exception {
        SessionResponse sessionResponse = new SessionResponse();
        Status status = new Status();
        status.setIsSuccess(true);
        sessionResponse.setStatus(status);
        LoginResponse response = new LoginResponse();
        response.setSessionResponse(sessionResponse);

        when(port.login(any(LoginRequest.class))).thenReturn(response);
    }

}
