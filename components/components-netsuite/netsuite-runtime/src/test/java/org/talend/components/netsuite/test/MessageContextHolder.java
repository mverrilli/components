package org.talend.components.netsuite.test;

import javax.xml.ws.handler.MessageContext;

/**
 *
 */
public class MessageContextHolder {

    private static ThreadLocal<MessageContext> holder = new ThreadLocal<>();

    public static void set(MessageContext messageContext) {
        holder.set(messageContext);
    }

    public static MessageContext get() {
        return holder.get();
    }

    public static void remove() {
        holder.remove();
    }
}
