package org.talend.components.netsuite.client.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.common.NsSearchResult;
import org.talend.components.netsuite.client.common.ResultSet;
import org.talend.components.netsuite.client.model.search.SearchRecordInfo;

/**
 *
 */
public class SearchResultSet<R> extends ResultSet<R> {

    private NetSuiteClientService clientService;
    private SearchRecordInfo searchRecordInfo;
    private String searchId;
    private NsSearchResult result;
    private List<R> recordList;
    private Iterator<R> recordIterator;
    private R current;

    public SearchResultSet(NetSuiteClientService clientService,
            SearchRecordInfo searchRecordInfo, NsSearchResult result) {

        this.clientService = clientService;
        this.searchRecordInfo = searchRecordInfo;
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
    public R get() throws NetSuiteException {
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

    protected List<R> getMoreRecords() throws NetSuiteException {
        if (searchId != null) {
            int nextPageIndex = result.getPageIndex().intValue() + 1;
            result = clientService.searchMoreWithId(searchId, nextPageIndex);
            if (result.isSuccess()) {
                return filterRecordList();
            }
        }
        return Collections.emptyList();
    }

    protected List<R> filterRecordList() {
        List<R> recordList = result.getRecordList();
        List<R> list = null;
        if (recordList == null) {
            list = Collections.emptyList();
        }
        if (!recordList.isEmpty()) {
            list = new ArrayList<>(recordList.size());
            Iterator<R> recordIterator = recordList.iterator();
            while (recordIterator.hasNext()) {
                R record = recordIterator.next();
                list.add(record);
            }
        }
        return list;
    }

}
