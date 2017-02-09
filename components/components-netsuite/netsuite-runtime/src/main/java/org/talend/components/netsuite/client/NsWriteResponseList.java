package org.talend.components.netsuite.client;

import java.util.List;

/**
 *
 */
public class NsWriteResponseList<RefT> {
    private NsStatus status;
    private List<NsWriteResponse<RefT>> responses;

    public NsWriteResponseList() {
    }

    public NsWriteResponseList(NsStatus status, List<NsWriteResponse<RefT>> responses) {
        this.status = status;
        this.responses = responses;
    }

    public NsStatus getStatus() {
        return status;
    }

    public void setStatus(NsStatus status) {
        this.status = status;
    }

    public List<NsWriteResponse<RefT>> getResponses() {
        return responses;
    }

    public void setResponses(List<NsWriteResponse<RefT>> responses) {
        this.responses = responses;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NsWriteResponseList{");
        sb.append("status=").append(status);
        sb.append(", responses=").append(responses);
        sb.append('}');
        return sb.toString();
    }
}
