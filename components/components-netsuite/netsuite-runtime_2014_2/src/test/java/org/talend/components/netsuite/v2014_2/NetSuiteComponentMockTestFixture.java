package org.talend.components.netsuite.v2014_2;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.talend.components.api.container.DefaultComponentRuntimeContainerImpl;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.components.netsuite.test.TestFixture;

import com.netsuite.webservices.v2014_2.platform.NetSuitePortType;
import com.netsuite.webservices.v2014_2.platform.core.Status;
import com.netsuite.webservices.v2014_2.platform.messages.LoginRequest;
import com.netsuite.webservices.v2014_2.platform.messages.LoginResponse;
import com.netsuite.webservices.v2014_2.platform.messages.SessionResponse;

/**
 *
 */
public class NetSuiteComponentMockTestFixture implements TestFixture {
    protected NetSuiteWebServiceMockTestFixture webServiceMockTestFixture;
    protected boolean reinstall;
    protected RuntimeContainer runtimeContainer;
    protected NetSuiteConnectionProperties connectionProperties;

    public NetSuiteComponentMockTestFixture(NetSuiteWebServiceMockTestFixture webServiceMockTestFixture) {
        this.webServiceMockTestFixture = webServiceMockTestFixture;
    }

    public boolean isReinstall() {
        return reinstall;
    }

    public void setReinstall(boolean reinstall) {
        this.reinstall = reinstall;
    }

    @Override
    public void setUp() throws Exception {
        if (reinstall) {
            webServiceMockTestFixture.reinstall();
        }

        final NetSuitePortType port = webServiceMockTestFixture.getPortMock();

        SessionResponse sessionResponse = new SessionResponse();
        Status status = new Status();
        status.setIsSuccess(true);
        sessionResponse.setStatus(status);
        LoginResponse response = new LoginResponse();
        response.setSessionResponse(sessionResponse);

        when(port.login(any(LoginRequest.class))).thenReturn(response);

        runtimeContainer = spy(new DefaultComponentRuntimeContainerImpl());

        connectionProperties = new NetSuiteConnectionProperties("test");
        connectionProperties.init();
        connectionProperties.endpoint.setValue(webServiceMockTestFixture.getEndpointAddress().toString());
        connectionProperties.email.setValue("test@test.com");
        connectionProperties.password.setValue("123");
        connectionProperties.role.setValue(3);
        connectionProperties.account.setValue("test");
        connectionProperties.applicationId.setValue("00000000-0000-0000-0000-000000000000");
    }

    @Override
    public void tearDown() throws Exception {
        // do nothing
    }

    public RuntimeContainer getRuntimeContainer() {
        return runtimeContainer;
    }

    public NetSuiteConnectionProperties getConnectionProperties() {
        return connectionProperties;
    }
}
