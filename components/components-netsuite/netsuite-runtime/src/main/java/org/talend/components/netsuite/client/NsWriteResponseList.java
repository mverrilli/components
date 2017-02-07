package org.talend.components.netsuite.client;

import java.util.List;

/**
 *
 */
public class NsWriteResponseList extends NsObject {
    private List<NsWriteResponse> responses;

    public NsWriteResponseList(Object instance) {
        super(instance);
    }

    public List<NsWriteResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<NsWriteResponse> responses) {
        this.responses = responses;
    }
}
