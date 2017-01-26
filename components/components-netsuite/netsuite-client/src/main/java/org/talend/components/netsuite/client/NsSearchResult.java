package org.talend.components.netsuite.client;

import java.util.List;

import org.talend.components.netsuite.client.NsObject;

/**
 *
 */
public class NsSearchResult {
    protected boolean success;
    protected Integer totalRecords;
    protected Integer pageSize;
    protected Integer totalPages;
    protected Integer pageIndex;
    protected String searchId;
    protected List<NsObject> recordList;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer value) {
        this.totalRecords = value;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer value) {
        this.pageSize = value;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer value) {
        this.totalPages = value;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer value) {
        this.pageIndex = value;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String value) {
        this.searchId = value;
    }

    public List<NsObject> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<NsObject> value) {
        this.recordList = value;
    }

}
