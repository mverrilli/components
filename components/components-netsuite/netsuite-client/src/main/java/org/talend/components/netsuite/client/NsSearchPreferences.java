package org.talend.components.netsuite.client;

/**
 *
 */
public class NsSearchPreferences {

    protected Boolean bodyFieldsOnly;
    protected Boolean returnSearchColumns;
    protected Integer pageSize;

    public Boolean getBodyFieldsOnly() {
        return bodyFieldsOnly;
    }

    public void setBodyFieldsOnly(Boolean value) {
        this.bodyFieldsOnly = value;
    }

    public Boolean getReturnSearchColumns() {
        return returnSearchColumns;
    }

    public void setReturnSearchColumns(Boolean value) {
        this.returnSearchColumns = value;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer value) {
        this.pageSize = value;
    }

}
