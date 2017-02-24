package org.talend.components.netsuite.client.query;

import static org.talend.components.netsuite.client.model.BeanUtils.getProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.setProperty;
import static org.talend.components.netsuite.client.model.BeanUtils.toInitialLower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.talend.components.netsuite.beans.BeanInfo;
import org.talend.components.netsuite.beans.BeanManager;
import org.talend.components.netsuite.beans.PropertyInfo;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.common.NsCustomizationRef;
import org.talend.components.netsuite.client.common.NsSearchResult;
import org.talend.components.netsuite.client.model.CustomRecordTypeInfo;
import org.talend.components.netsuite.client.model.RecordTypeInfo;
import org.talend.components.netsuite.client.model.SearchRecordTypeDesc;
import org.talend.components.netsuite.client.model.search.SearchFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchFieldOperatorType;
import org.talend.components.netsuite.client.model.search.SearchFieldType;

/**
 *
 */
public class SearchQuery<SearchT, RecT> {

    protected NetSuiteClientService clientService;

    protected String recordTypeName;
    protected RecordTypeInfo recordTypeInfo;
    protected SearchRecordTypeDesc searchRecordInfo;

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

        recordTypeInfo = clientService.getRecordType(recordTypeName);
        searchRecordInfo = clientService.getSearchRecordType(recordTypeName);

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
                    setProperty(searchAdvanced, "savedSearchId", savedSearchId);
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

        String fieldName = toInitialLower(condition.getFieldName());
        PropertyInfo propertyInfo = searchMetaData.getProperty(fieldName);

        SearchFieldOperatorType.QualifiedName operatorQName =
                new SearchFieldOperatorType.QualifiedName(condition.getOperatorName());

        if (propertyInfo != null) {
            Object searchField = processConditionForSearchRecord(searchBasic, condition);
            setProperty(searchBasic, fieldName, searchField);

        } else {
            String dataType = operatorQName.getDataType();
            String searchFieldType = null;
            if ("String".equals(dataType)) {
                searchFieldType = SearchFieldType.CUSTOM_STRING.getFieldTypeName();
            } else if ("Boolean".equals(dataType)) {
                searchFieldType = SearchFieldType.CUSTOM_BOOLEAN.getFieldTypeName();
            } else if ("Numeric".equals(dataType)) {
                searchFieldType = SearchFieldType.CUSTOM_LONG.getFieldTypeName();
            } else if ("Double".equals(dataType)) {
                searchFieldType = SearchFieldType.CUSTOM_DOUBLE.getFieldTypeName();
            } else if ("Date".equals(dataType) || "PredefinedDate".equals(dataType)) {
                searchFieldType = SearchFieldType.CUSTOM_DATE.getFieldTypeName();
            } else if ("List".equals(dataType)) {
                searchFieldType = SearchFieldType.CUSTOM_MULTI_SELECT.getFieldTypeName();
            } else {
                throw new NetSuiteException("Invalid data type: " + searchFieldType);
            }

            Object searchField = processCondition(searchFieldType, condition);
            customFieldList.add(searchField);
        }

        return this;
    }

    private Object processConditionForSearchRecord(Object searchRecord, SearchCondition condition) throws NetSuiteException {
        String fieldName = toInitialLower(condition.getFieldName());
        BeanInfo beanInfo = BeanManager.getBeanInfo(searchRecord.getClass());
        Class<?> searchFieldClass = beanInfo.getProperty(fieldName).getWriteType();
        Object searchField = processCondition(searchFieldClass.getSimpleName(), condition);
        return searchField;
    }

    private Object processCondition(String fieldType, SearchCondition condition) throws NetSuiteException {
        try {
            String searchFieldName = toInitialLower(condition.getFieldName());
            String searchOperator = condition.getOperatorName();
            List<String> searchValue = condition.getValues();

            SearchFieldAdapter<?> fieldPopulator = clientService.getSearchFieldPopulator(fieldType);
            Object searchField = fieldPopulator.populate(searchFieldName, searchOperator, searchValue);

            return searchField;
        } catch (IllegalArgumentException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    public SearchT toNativeQuery() throws NetSuiteException {
        initSearch();

        if (searchRecordInfo.getType().equals("transaction")) {
            SearchFieldAdapter<?> populator = clientService.getSearchFieldPopulator(
                    SearchFieldType.SELECT.getFieldTypeName());
            Object searchTypeField = populator.populate(
                    "List.anyOf", Arrays.asList(recordTypeInfo.getRecordType().getType()));
            setProperty(searchBasic, "type", searchTypeField);

        } else if (searchRecordInfo.getType().equals("customRecord")) {
            CustomRecordTypeInfo customRecordTypeInfo = (CustomRecordTypeInfo) recordTypeInfo;
            NsCustomizationRef customizationRef = customRecordTypeInfo.getCustomizationRef();

            Object recType = clientService.createType("CustomizationRef");
            setProperty(recType, "scriptId", customizationRef.getScriptId());
            setProperty(recType, "internalId", customizationRef.getInternalId());

            setProperty(searchBasic, "recType", recType);
        }


        if (!customFieldList.isEmpty()) {
            Object customFieldListWrapper = clientService.createType("SearchCustomFieldList");
            List<Object> customFields = (List<Object>) getProperty(customFieldListWrapper, "customField");
            for (Object customField : customFieldList) {
                customFields.add(customField);
            }
            setProperty(searchBasic, "customFieldList", customFieldListWrapper);
        }

        SearchT searchRecord;
        if (searchRecordInfo.getSearchClass() != null) {
            setProperty(search, "basic", searchBasic);
            searchRecord = search;
            if (searchAdvanced != null) {
                setProperty(searchAdvanced, "condition", search);
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

}
