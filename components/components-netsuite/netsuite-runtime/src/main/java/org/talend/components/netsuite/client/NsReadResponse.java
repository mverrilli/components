package org.talend.components.netsuite.client;

public class NsReadResponse<RecT> {
    private NsStatus status;
    private RecT record;

    public NsReadResponse() {
    }

    public NsReadResponse(NsStatus status, RecT record) {
        this.status = status;
        this.record = record;
    }

    public NsStatus getStatus() {
        return status;
    }

    public RecT getRecord() {
        return record;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NsReadResponse{");
        sb.append("status=").append(status);
        sb.append(", record=").append(record);
        sb.append('}');
        return sb.toString();
    }
}
