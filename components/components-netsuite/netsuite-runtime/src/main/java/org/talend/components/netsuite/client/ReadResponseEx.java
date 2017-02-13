package org.talend.components.netsuite.client;

import com.netsuite.webservices.platform.core.Record;
import com.netsuite.webservices.platform.messages.ReadResponse;

public class ReadResponseEx {
    private ReadResponse wrapped;

    public ReadResponseEx(ReadResponse wrapped) {
        this.wrapped = wrapped;
    }

    public boolean isSuccess() {
        return wrapped.getStatus() != null ? wrapped.getStatus().getIsSuccess() : false;
    }

    public Record getBaseRef() {
        return wrapped.getRecord();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WriteResponseEx{");
        sb.append("wrapped=").append(wrapped);
        sb.append('}');
        return sb.toString();
    }
}
