package org.talend.components.netsuite.client.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.components.netsuite.beans.BeanInfo;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.common.SearchResultSet;
import org.talend.components.netsuite.client.metadata.RecordTypeInfo;
import org.talend.components.netsuite.client.metadata.SearchFieldOperatorTypeInfo;
import org.talend.components.netsuite.client.metadata.SearchRecordInfo;
import org.talend.components.netsuite.client.common.NsSearchResult;
import org.talend.components.netsuite.beans.PropertyInfo;
import org.talend.components.netsuite.beans.BeanManager;

import static org.talend.components.netsuite.client.NetSuiteFactory.getBeanProperty;
import static org.talend.components.netsuite.client.NetSuiteFactory.getPropertyAccessor;
import static org.talend.components.netsuite.client.NetSuiteFactory.setBeanProperty;

/**
 *
 */
public class SearchQuery<SearchT, RecT> {

    protected NetSuiteClientService clientService;

    protected Map<String, SearchFieldPopulator<?>> searchFieldPopulatorMap = new HashMap<>();

    protected String recordTypeName;
    protected RecordTypeInfo recordTypeInfo;
    protected SearchRecordInfo searchRecordInfo;

    protected SearchT search;             // search class' instance
    protected SearchT searchBasic;        // search basic class' instance
    protected SearchT searchAdvanced;     // search advanced class' instance

    protected String savedSearchId;

    protected List<Object> customFieldList = new ArrayList<>();

    public SearchQuery(NetSuiteClientService clientService) throws NetSuiteException {
        this.clientService = clientService;
    }

    public SearchQuery target(final String recordTypeName) throws NetSuiteException {
        this.recordTypeName = recordTypeName;

        recordTypeInfo = clientService.getRecordTypeInfo(recordTypeName);
        searchRecordInfo = clientService.getSearchRecordInfo(recordTypeName);

        // search not found or not supported
        if (searchRecordInfo == null) {
            throw new IllegalArgumentException("Search entity not found: " + this.recordTypeName);
        }

        return this;
    }

    public SearchQuery savedSearchId(String savedSearchId) throws NetSuiteException {
        this.savedSearchId = savedSearchId;
        return this;
    }

    private void initSearch() throws NetSuiteException {
        if (searchBasic != null) {
            return;
        }
        try {
            // get a search class instance
            if (searchRecordInfo.getSearchClass() != null) {
                search = (SearchT) searchRecordInfo.getSearchClass().newInstance();
            }

            // get a advanced search class instance and set 'savedSearchId' into it
            searchAdvanced = null;
            if (savedSearchId != null && savedSearchId.length() > 0) {
                if (searchRecordInfo.getSearchAdvancedClass() != null) {
                    searchAdvanced = (SearchT) searchRecordInfo.getSearchAdvancedClass().newInstance();
                    getPropertyAccessor(searchAdvanced).set(searchAdvanced, "savedSearchId", savedSearchId);
                } else {
                    throw new NetSuiteException("Advanced search not available: " + recordTypeName);
                }
            }

            // basic search class not found or supported
            if (searchRecordInfo.getSearchBasicClass() == null) {
                throw new IllegalArgumentException("Search basic class not found: " + recordTypeName);
            }

            // get a basic search class instance
            searchBasic = (SearchT) searchRecordInfo.getSearchBasicClass().newInstance();

        } catch (InstantiationException | IllegalAccessException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    public SearchQuery condition(SearchCondition condition)
            throws NetSuiteException {

        initSearch();

        BeanInfo searchMetaData = BeanManager.getBeanInfo(searchRecordInfo.getSearchBasicClass());

        PropertyInfo fieldMetaData = searchMetaData.getProperty(condition.getFieldName());

        SearchFieldOperatorTypeInfo.QualifiedName operatorQName =
                new SearchFieldOperatorTypeInfo.QualifiedName(condition.getOperatorName());

        if (fieldMetaData != null) {
            Object searchField = processConditionForSearchRecord(searchBasic, condition);
            setBeanProperty(searchBasic, condition.getFieldName(), searchField);

        } else {
            String dataType = operatorQName.getDataType();
            String searchFieldType = null;
            if ("String".equals(dataType)) {
                searchFieldType = "SearchStringCustomField";
            } else if ("Boolean".equals(dataType)) {
                searchFieldType = "SearchBooleanCustomField";
            } else if ("Numeric".equals(dataType)) {
                searchFieldType = "SearchLongCustomField";
            } else if ("Double".equals(dataType)) {
                searchFieldType = "SearchDoubleCustomField";
            } else if ("Date".equals(dataType) || "PredefinedDate".equals(dataType)) {
                searchFieldType = "SearchDateCustomField";
            } else if ("List".equals(dataType)) {
                searchFieldType = "SearchMultiSelectCustomField";
            } else {
                throw new NetSuiteException("Invalid data type: " + searchFieldType);
            }

            Object criteria = processCondition(searchFieldType, condition);
            customFieldList.add(criteria);
        }

        return this;
    }

    private Object processConditionForSearchRecord(Object searchRecord, SearchCondition condition) throws NetSuiteException {
        BeanInfo searchMetaData = BeanManager.getBeanInfo(searchRecord.getClass());
        Class<?> searchFieldClass = searchMetaData.getProperty(condition.getFieldName()).getWriteType();
        Object criteria = processCondition(searchFieldClass.getSimpleName(), condition);
        return criteria;
    }

    private Object processCondition(String fieldType, SearchCondition condition) throws NetSuiteException {
        try {
            String searchFieldName = condition.getFieldName();
            String searchOperator = condition.getOperatorName();
            List<String> searchValue = condition.getValues();

            SearchFieldPopulator<?> fieldPopulator = getFieldPopulator(fieldType);
            Object searchField = fieldPopulator.populate(searchFieldName, searchOperator, searchValue);

            return searchField;
        } catch (IllegalArgumentException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    public SearchT toNativeQuery() throws NetSuiteException {
        initSearch();

        if (searchRecordInfo.getSearchRecordType().equals("transaction")) {
            SearchFieldPopulator<?> populator = getFieldPopulator("SearchEnumMultiSelectField");
            Object searchTypeField = populator.populate(
                    "List.anyOf", Arrays.asList(recordTypeInfo.getRecordType()));
            setBeanProperty(searchBasic, "type", searchTypeField);
        }

        if (!customFieldList.isEmpty()) {
            Object customFieldList = clientService.createType("SearchCustomFieldList");
            List<Object> list = (List<Object>) getBeanProperty(customFieldList, "customField");
            for (Object customCriteria : this.customFieldList) {
                list.add(customCriteria);
            }
            setBeanProperty(searchBasic, "customFieldList", customFieldList);
        }

        SearchT searchRecord;
        if (searchRecordInfo.getSearchClass() != null) {
            setBeanProperty(search, "basic", searchBasic);
            searchRecord = search;
            if (searchAdvanced != null) {
                setBeanProperty(searchAdvanced, "condition", search);
                searchRecord = searchAdvanced;
            }
        } else {
            searchRecord = searchBasic;
        }

        return searchRecord;
    }

    public SearchResultSet<RecT> search() throws NetSuiteException {
        Object searchRecord = toNativeQuery();
        NsSearchResult result = clientService.search(searchRecord);
        SearchResultSet<RecT> resultSet = new SearchResultSet<>(clientService, searchRecordInfo, result);
        return resultSet;
    }

    private SearchFieldPopulator<?> getFieldPopulator(String fieldType) {
        return createFieldPopulator(fieldType);
    }

    private SearchFieldPopulator<?> createFieldPopulator(String fieldType) {
        SearchFieldPopulator<?> fieldPopulator = searchFieldPopulatorMap.get(fieldType);
        if (fieldPopulator == null) {
            Class<?> fieldClass = clientService.getSearchFieldClass(fieldType);
            if ("SearchBooleanField".equals(fieldType) || "SearchBooleanCustomField".equals(fieldType)) {
                fieldPopulator = new SearchBooleanFieldPopulator<>(clientService, fieldType, fieldClass);
            } else if ("SearchStringField".equals(fieldType) || "SearchStringCustomField".equals(fieldType)) {
                fieldPopulator = new SearchStringFieldPopulator<>(clientService, fieldType, fieldClass);
            } else if ("SearchLongField".equals(fieldType) || "SearchLongCustomField".equals(fieldType)) {
                fieldPopulator = new SearchLongFieldPopulator<>(clientService, fieldType, fieldClass);
            } else if ("SearchDoubleField".equals(fieldType) || "SearchDoubleCustomField".equals(fieldType)) {
                fieldPopulator = new SearchDoubleFieldPopulator<>(clientService, fieldType, fieldClass);
            } else if ("SearchDateField".equals(fieldType) || "SearchDateCustomField".equals(fieldType)) {
                fieldPopulator = new SearchDateFieldPopulator<>(clientService, fieldType, fieldClass);
            } else if ("SearchMultiSelectField".equals(fieldType) || "SearchMultiSelectCustomField".equals(fieldType)) {
                fieldPopulator = new SearchMultiSelectFieldPopulator<>(clientService, fieldType, fieldClass);
            } else if ("SearchEnumMultiSelectField".equals(fieldType) || "SearchEnumMultiSelectCustomField".equals(fieldType)) {
                fieldPopulator = new SearchEnumMultiSelectFieldPopulator<>(clientService, fieldType, fieldClass);
            } else {
                throw new IllegalArgumentException("Invalid search field type: " + fieldType);
            }
            searchFieldPopulatorMap.put(fieldType, fieldPopulator);
        }
        return fieldPopulator;
    }
}
