package org.talend.components.netsuite.client.tools;

import java.net.URL;
import java.util.List;

import org.apache.cxf.databinding.DataBinding;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.service.model.ServiceInfo;

/**
 * Created by ihor.istomin on 2/17/2017.
 */
public class DynaClient {

    public static void main(String...args) throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        dcf.setSimpleBindingEnabled(true);
        dcf.setSchemaCompilerOptions(new String[]{"-Xbg"});

        String apiVersion = "2016.2";

        URL url = DynaClient.class.getResource(
                "/wsdl/" + apiVersion + "/netsuite.wsdl");

        Client client = dcf.createClient(url, Thread.currentThread().getContextClassLoader());
        Endpoint endpoint = client.getEndpoint();
        List<ServiceInfo> serviceInfos = endpoint.getService().getServiceInfos();
        DataBinding dataBinding = endpoint.getService().getDataBinding();
        EndpointInfo endpointInfo = endpoint.getEndpointInfo();

        System.out.println(client);

        Class<?> clazz = Thread.currentThread().getContextClassLoader()
                .loadClass("com.netsuite.webservices.documents.filecabinet_2016_2.types.FileAttachFrom");

        System.out.println(clazz);

    }
}
