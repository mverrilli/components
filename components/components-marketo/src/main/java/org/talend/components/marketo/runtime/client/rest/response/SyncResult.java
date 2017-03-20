package org.talend.components.marketo.runtime.client.rest.response;

import java.util.List;

import org.talend.components.marketo.runtime.client.rest.type.SyncStatus;

public class SyncResult extends PaginateResult {

    private List<SyncStatus> result;
    public List<SyncStatus> getResult() {
        return result;
    }
    public void setResult(List<SyncStatus> result) {
        this.result = result;
    }

}
