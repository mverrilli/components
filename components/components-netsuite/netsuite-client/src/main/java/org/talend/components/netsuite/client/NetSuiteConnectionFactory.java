package org.talend.components.netsuite.client;

/**
 *
 */
public abstract class NetSuiteConnectionFactory {

    private static boolean messageLoggingEnabled;

    public static boolean isMessageLoggingEnabled() {
        return messageLoggingEnabled;
    }

    public static void setMessageLoggingEnabled(boolean messageLoggingEnabled) {
        NetSuiteConnectionFactory.messageLoggingEnabled = messageLoggingEnabled;
    }

    public static NetSuiteConnection getConnection(String apiVersion) throws NetSuiteException {
        NetSuiteConnection connection;
        if (apiVersion.equals("2016.2")) {
            connection = new org.talend.components.netsuite.client.impl.v2016_2.NetSuiteConnectionImpl();
        } else {
            throw new IllegalArgumentException("Invalid api version: " + apiVersion);
        }
        connection.setMessageLoggingEnabled(messageLoggingEnabled);
        return connection;
    }
}
