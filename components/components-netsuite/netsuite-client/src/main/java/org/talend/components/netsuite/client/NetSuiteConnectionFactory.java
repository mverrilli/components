package org.talend.components.netsuite.client;

/**
 *
 */
public abstract class NetSuiteConnectionFactory {

    public static NetSuiteConnection getConnection(String apiVersion) throws NetSuiteException {
        if (apiVersion.equals("2016.2")) {
            return new org.talend.components.netsuite.client.impl.v2016_2.NetSuiteConnectionImpl();
        }
        throw new IllegalArgumentException("Invalid api version: " + apiVersion);
    }
}
