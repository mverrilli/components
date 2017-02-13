package org.talend.components.netsuite.client;

import com.netsuite.webservices.platform.core.BaseRef;
import com.netsuite.webservices.platform.messages.WriteResponse;

public class WriteResponseEx {
    private WriteResponse wrapped;

    public WriteResponseEx(WriteResponse wrapped) {
        this.wrapped = wrapped;
    }

    public boolean isSuccess() {
        return wrapped.getStatus() != null ? wrapped.getStatus().getIsSuccess() : false;
    }

    public BaseRef getBaseRef() {
        return wrapped.getBaseRef();
    }

    public WriteResponse getWrapped() {
        return wrapped;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WriteResponseEx{");
        sb.append("wrapped=").append(wrapped);
        sb.append('}');
        return sb.toString();
    }
}
