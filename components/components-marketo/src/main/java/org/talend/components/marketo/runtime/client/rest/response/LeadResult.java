package org.talend.components.marketo.runtime.client.rest.response;

import java.util.List;
import java.util.Map;

public class LeadResult extends PaginateResult {

    private List<Map<String, String>> result;
    public List<Map<String, String>> getResult() {
        return result;
    }
    public void setResult(List<Map<String, String>> result) {
        this.result = result;
    }
}
