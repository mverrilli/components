package org.talend.components.netsuite.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.talend.components.netsuite.client.schema.NsSearchDef;
import org.talend.components.netsuite.model.Mapper;

import com.netsuite.webservices.platform.core.Record;

/**
 *
 */
public class SearchResultSet<R> extends ResultSet<R> {

    private NetSuiteClientService clientService;
    private NsSearchDef searchInfo;
    private String searchId;
    private SearchResultEx result;
    private List<R> recordList;
    private Iterator<R> recordIterator;
    private R current;
    private Mapper<Record, R> recordMapper;

    public SearchResultSet(NetSuiteClientService clientService,
            NsSearchDef searchInfo, SearchResultEx result, Mapper<Record, R> recordMapper) {

        this.clientService = clientService;
        this.searchInfo = searchInfo;
        this.result = result;
        this.recordMapper = recordMapper;

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
        List<Record> recordList = result.getRecordList();
        List<R> list = null;
        if (recordList == null) {
            list = Collections.emptyList();
        }
        if (!recordList.isEmpty()) {
            list = new ArrayList<>(recordList.size());
            Iterator<Record> recordIterator = recordList.iterator();
            while (recordIterator.hasNext()) {
                Record record = recordIterator.next();
                list.add(recordMapper.map(record));
            }
        }
        return list;
    }

    public static class IdentityMapper implements Mapper<Record, Record> {
        @Override public Record map(Record input) {
            return input;
        }
    }

}
