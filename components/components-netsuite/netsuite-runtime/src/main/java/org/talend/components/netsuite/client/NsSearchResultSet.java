package org.talend.components.netsuite.client;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.talend.components.netsuite.client.metadata.NsSearchDef;

/**
 *
 */
public class NsSearchResultSet<RecT> extends ResultSet<RecT> {

    private NetSuiteConnection conn;
    private NsSearchDef searchInfo;
    private String searchId;
    private NsSearchResult result;
    private List<RecT> recordList;
    private Iterator<RecT> recordIterator;
    private RecT current;

    public NsSearchResultSet(NetSuiteConnection conn,
            NsSearchDef searchInfo, NsSearchResult result) {

        this.conn = conn;
        this.searchInfo = searchInfo;
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
    public RecT get() throws NetSuiteException {
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

    protected List<RecT> getMoreRecords() throws NetSuiteException {
        if (searchId != null) {
            int nextPageIndex = result.getPageIndex().intValue() + 1;
            result = conn.searchMoreWithId(searchId, nextPageIndex);
            if (result.isSuccess()) {
                return filterRecordList();
            }
        }
        return Collections.emptyList();
    }

    protected List<RecT> filterRecordList() {
        List<RecT> recordList = result.getRecordList();
        if (recordList == null) {
            recordList = Collections.emptyList();
        }
        if (!recordList.isEmpty()) {
            if (searchInfo.isItemSearch()) {
                Iterator<RecT> recordIterator = recordList.iterator();
                while (recordIterator.hasNext()) {
                    RecT record = recordIterator.next();
                    if (!record.getClass().equals(searchInfo.getRecordClass())) {
                        recordIterator.remove();
                    }
                }
            }
        }
        return recordList;
    }

}
