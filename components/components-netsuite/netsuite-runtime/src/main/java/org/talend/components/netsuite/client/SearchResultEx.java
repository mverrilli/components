package org.talend.components.netsuite.client;

import java.util.Collections;
import java.util.List;

import com.netsuite.webservices.platform.core.Record;
import com.netsuite.webservices.platform.core.SearchResult;

/**
 *
 */
public class SearchResultEx {
    protected SearchResult wrapped;

    public SearchResultEx(SearchResult wrapped) {
        this.wrapped = wrapped;
    }

    public boolean isSuccess() {
        return wrapped.getStatus().getIsSuccess();
    }

    public Integer getTotalRecords() {
        return wrapped.getTotalRecords();
    }

    public Integer getPageSize() {
        return wrapped.getPageSize();
    }

    public Integer getTotalPages() {
        return wrapped.getTotalPages();
    }

    public Integer getPageIndex() {
        return wrapped.getPageIndex();
    }

    public String getSearchId() {
        return wrapped.getSearchId();
    }

    public List<Record> getRecordList() {
        return wrapped.getRecordList() != null ? wrapped.getRecordList().getRecord() : Collections.<Record>emptyList();
    }

}
