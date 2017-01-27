package org.talend.components.netsuite.client;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class NsSearchResultSet extends ResultSet<NsObject> {

    private NetSuiteConnection conn;
    private Class<?> entityClass;
    private boolean itemSearch;
    private String searchId;
    private NsSearchResult result;
    private List<NsObject> recordList;
    private Iterator<NsObject> recordIterator;
    private NsObject current;

    public NsSearchResultSet(NetSuiteConnection conn,
            Class<?> entityClass, boolean itemSearch, NsSearchResult result) {
        this.conn = conn;
        this.entityClass = entityClass;
        this.itemSearch = itemSearch;
        this.result = result;

        searchId = result.getSearchId();
        recordList = filterRecordList();
        recordIterator = recordList.iterator();
    }

    @Override
    public boolean next() throws NetSuiteException {
        if (!recordIterator.hasNext() && hasMore()) {
            recordList = getMoreRecords();
            recordIterator = recordList.iterator();
        }
        if (recordIterator.hasNext()) {
            current = recordIterator.next();
            return true;
        }
        return false;
    }

    @Override
    public NsObject get() throws NetSuiteException {
        return current;
    }

    protected boolean hasMore() {
        if (this.result == null) {
            return false;
        }
        if (result.getPageIndex() == null) {
            return false;
        }
        if (result.getTotalPages() == null) {
            return false;
        }
        return result.getPageIndex().intValue() < result.getTotalPages().intValue();
    }

    protected List<NsObject> getMoreRecords() throws NetSuiteException {
        if (searchId != null) {
            int nextPageIndex = result.getPageIndex().intValue() + 1;
            result = conn.searchMoreWithId(searchId, nextPageIndex);
            if (result.isSuccess()) {
                return filterRecordList();
            }
        }
        return Collections.emptyList();
    }

    protected List<NsObject> filterRecordList() {
        List<NsObject> recordList = result.getRecordList();
        if (recordList == null) {
            recordList = Collections.emptyList();
        }
        if (!recordList.isEmpty()) {
            if (itemSearch) {
                Iterator<NsObject> recordIterator = recordList.iterator();
                while (recordIterator.hasNext()) {
                    NsObject record = recordIterator.next();
                    if (!record.getClass().equals(entityClass)) {
                        recordIterator.remove();
                    }
                }
            }
        }
        return recordList;
    }

}
