package org.talend.components.netsuite.client;

import java.util.List;

/**
 *
 */
public class NsSearchResult<RecordT> {
    protected NsStatus status;
    protected Integer totalRecords;
    protected Integer pageSize;
    protected Integer totalPages;
    protected Integer pageIndex;
    protected String searchId;
    protected List<RecordT> recordList;

    public NsStatus getStatus() {
        return status;
    }

    public void setStatus(NsStatus status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return status.isSuccess();
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

    public List<RecordT> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordT> value) {
        this.recordList = value;
    }

}
