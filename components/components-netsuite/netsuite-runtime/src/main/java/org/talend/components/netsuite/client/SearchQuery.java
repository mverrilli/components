package org.talend.components.netsuite.client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.talend.components.netsuite.client.metadata.RecordTypeDef;
import org.talend.components.netsuite.client.metadata.SearchFieldOperatorTypeDef;
import org.talend.components.netsuite.client.metadata.SearchRecordDef;
import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeManager;

import com.netsuite.webservices.platform.core.ListOrRecordRef;

import static org.talend.components.netsuite.client.NetSuiteFactory.getPropertyAccessor;

/**
 *
 */
public class SearchQuery {

    protected NetSuiteClientService clientService;

    protected String entityTypeName;
    protected RecordTypeDef recordTypeDef;
    protected SearchRecordDef searchRecordDef;

    protected Object search;             // search class' instance
    protected Object searchBasic;        // search basic class' instance
    protected Object searchAdvanced;     // search advanced class' instance

    protected String savedSearchId;

    protected List<Object> customCriteriaList = new ArrayList<>();

    public SearchQuery(NetSuiteClientService clientService) throws NetSuiteException {
        this.clientService = clientService;
    }

    public SearchQuery entity(final String typeName) throws NetSuiteException {
        entityTypeName = typeName;
        recordTypeDef = clientService.getRecordTypeDef(typeName);
        searchRecordDef = clientService.getSearchRecordDef(typeName);

        // search not found or not supported
        if (searchRecordDef == null) {
            throw new IllegalArgumentException("Search entity not found: " + entityTypeName);
        }

        return this;
    }

    public SearchQuery savedSearchId(String savedSearchId) throws NetSuiteException {
        this.savedSearchId = savedSearchId;
        return this;
    }

    private void initSearch() throws NetSuiteException {
        if (search != null) {
            return;
        }
        try {
            // get a search class instance
            search = searchRecordDef.getSearchClass().newInstance();

            // get a advanced search class instance and set 'savedSearchId' into it
            searchAdvanced = null;
            if (savedSearchId != null && savedSearchId.length() > 0) {
                searchAdvanced = searchRecordDef.getSearchAdvancedClass().newInstance();
                getPropertyAccessor(searchAdvanced).set(searchAdvanced, "savedSearchId", savedSearchId);
            }

            // basic search class not found or supported
            if (searchRecordDef.getSearchBasicClass() == null) {
                throw new IllegalArgumentException("Search basic class not found: " + entityTypeName);
            }

            // get a basic search class instance
            searchBasic = searchRecordDef.getSearchBasicClass().newInstance();

        } catch (InstantiationException | IllegalAccessException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    public SearchQuery criteria(String searchFieldName, String searchOperator, List<String> searchValue)
            throws NetSuiteException {

        initSearch();

        TypeInfo searchMetaData = TypeManager.forClass(searchRecordDef.getSearchBasicClass());

        PropertyInfo fieldMetaData = searchMetaData.getProperty(searchFieldName);

        SearchFieldOperatorTypeDef.QualifiedName operatorQName =
                new SearchFieldOperatorTypeDef.QualifiedName(searchOperator);

        if (fieldMetaData != null) {

            NsObject<?> criteria = createCriteria(searchBasic,
                    searchFieldName, searchOperator, searchValue);

            NsObject.wrap(searchBasic).set(searchFieldName, criteria);

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

            Class<?> fieldClass = clientService.getSearchFieldClass(searchFieldType);
            NsObject criteria = createCriteria(fieldClass, searchFieldName, searchOperator, searchValue);
            customCriteriaList.add(criteria);
        }

        return this;
    }

    private NsObject<?> createCriteria(Class<?> searchFieldClass, String internalId)
            throws NetSuiteException {
        try {
            TypeInfo fieldTypeMetaData = TypeManager.forClass(searchFieldClass);
            NsObject searchField = NsObject.wrap(searchFieldClass.newInstance());
            if (fieldTypeMetaData.getProperty("internalId") != null && internalId != null) {
                searchField.set("internalId", internalId);
            }
            return searchField;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    private NsObject createCriteria(Object searchRecord,
            String searchFieldName, String searchOperator, List<String> searchValue) throws NetSuiteException {

        TypeInfo searchMetaData = TypeManager.forClass(searchRecord.getClass());
        Class<?> searchFieldClass = searchMetaData.getProperty(searchFieldName).getWriteType();

        NsObject criteria = createCriteria(searchFieldClass, searchFieldName, searchOperator, searchValue);

        return criteria;
    }

    private NsObject createCriteria(Class<?> searchFieldClass, String searchFieldName,
            String searchOperator, List<String> searchValue) throws NetSuiteException {
        try {
            NsObject searchField;

            String fieldType = searchFieldClass.getSimpleName();

            if (fieldType.equals("SearchStringField") || fieldType.equals("SearchStringCustomField")) {

                NsObject searchArgumentType = createCriteria(searchFieldClass, searchFieldName);
                if (searchValue != null && searchValue.size() != 0) {
                    searchArgumentType.set("searchValue", searchValue.get(0));
                }
                searchArgumentType.set("operator", clientService.getSearchFieldOperatorByName(fieldType, searchOperator));
                searchField = searchArgumentType;

            } else if (fieldType.equals("SearchLongField") || fieldType.equals("SearchLongCustomField")) {

                NsObject searchArgumentType = createCriteria(searchFieldClass, searchFieldName);
                if (searchValue != null && searchValue.size() != 0) {
                    searchArgumentType.set("searchValue", Long.valueOf(Long.parseLong(searchValue.get(0))));
                    if (searchValue.size() > 1) {
                        searchArgumentType.set("searchValue2", Long.valueOf(Long.parseLong(searchValue.get(1))));
                    }
                }
                searchArgumentType.set("operator", clientService.getSearchFieldOperatorByName(fieldType, searchOperator));
                searchField = searchArgumentType;

            } else if (fieldType.equals("SearchDateField") || fieldType.equals("SearchDateCustomField")) {

                NsObject searchArgumentType = createCriteria(searchFieldClass, searchFieldName);

                SearchFieldOperatorTypeDef.QualifiedName operatorQName =
                        new SearchFieldOperatorTypeDef.QualifiedName(searchOperator);

                if (operatorQName.getDataType().equals("PredefinedDate")) {

                    searchArgumentType.set("predefinedSearchValue",
                            clientService.getSearchFieldOperatorByName(fieldType, searchOperator));

                } else {
                    if (searchValue != null && searchValue.size() != 0) {
                        Calendar calValue = Calendar.getInstance();

                        String dateFormat = "yyyy-MM-dd";
                        String timeFormat = "HH:mm:ss";

                        String format = dateFormat + " " + timeFormat;

                        if (searchValue.get(0).length() == dateFormat.length()) {
                            format = dateFormat;
                        }

                        if (searchValue.get(0).length() == timeFormat.length()) {
                            searchValue.set(0, new SimpleDateFormat(dateFormat)
                                    .format(calValue.getTime()) + " " + searchValue.get(0));
                        }

                        DateFormat df = new SimpleDateFormat(format);

                        try {
                            calValue.setTime(df.parse(searchValue.get(0)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        XMLGregorianCalendar xts = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                        xts.setYear(calValue.get(Calendar.YEAR));
                        xts.setMonth(calValue.get(Calendar.MONTH) + 1);
                        xts.setDay(calValue.get(Calendar.DAY_OF_MONTH));
                        xts.setHour(calValue.get(Calendar.HOUR_OF_DAY));
                        xts.setMinute(calValue.get(Calendar.MINUTE));
                        xts.setSecond(calValue.get(Calendar.SECOND));
                        xts.setMillisecond(calValue.get(Calendar.MILLISECOND));
                        xts.setTimezone(calValue.get(Calendar.ZONE_OFFSET) / 60000);

                        searchArgumentType.set("searchValue", xts);

                        if (searchValue.size() > 1) {
                            try {
                                calValue.setTime(df.parse(searchValue.get(1)));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            XMLGregorianCalendar xts2 = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                            xts2.setYear(calValue.get(Calendar.YEAR));
                            xts2.setMonth(calValue.get(Calendar.MONTH) + 1);
                            xts2.setDay(calValue.get(Calendar.DAY_OF_MONTH));
                            xts2.setHour(calValue.get(Calendar.HOUR_OF_DAY));
                            xts2.setMinute(calValue.get(Calendar.MINUTE));
                            xts2.setSecond(calValue.get(Calendar.SECOND));
                            xts2.setMillisecond(calValue.get(Calendar.MILLISECOND));
                            xts2.setTimezone(calValue.get(Calendar.ZONE_OFFSET) / 60000);

                            searchArgumentType.set("searchValue2", xts2);
                        }
                    }

                    searchArgumentType.set("operator",
                            clientService.getSearchFieldOperatorByName(fieldType, searchOperator));
                }

                searchField = searchArgumentType;

            } else if (fieldType.equals("SearchBooleanField") || fieldType.equals("SearchBooleanCustomField")) {

                NsObject searchArgumentType = createCriteria(searchFieldClass, searchFieldName);
                searchArgumentType.set("searchValue", Boolean.valueOf(searchValue.get(0)));
                searchField = searchArgumentType;

            } else if (fieldType.equals("SearchDoubleField") || fieldType.equals("SearchDoubleCustomField")) {

                NsObject searchArgumentType = createCriteria(searchFieldClass, searchFieldName);
                if (searchValue != null && searchValue.size() != 0) {
                    searchArgumentType.set("searchValue", Double.valueOf(Double.parseDouble(searchValue.get(0))));
                    if (searchValue.size() > 1) {
                        searchArgumentType.set("searchValue2", Double.valueOf(Double.parseDouble(searchValue.get(1))));
                    }
                }
                searchArgumentType.set("operator", clientService.getSearchFieldOperatorByName(fieldType, searchOperator));
                searchField = searchArgumentType;

            } else if (fieldType.equals("SearchMultiSelectField") || fieldType.equals("SearchMultiSelectCustomField")) {

                NsObject searchArgumentType = createCriteria(searchFieldClass, searchFieldName);

                List<Object> values = (List<Object>) searchArgumentType.get("searchValue");
                for (int i = 0; i < searchValue.size(); i++) {
                    NsObject item = NsObject.wrap(new ListOrRecordRef());
                    item.set("name", searchValue.get(i));
                    item.set("internalId", searchValue.get(i));
                    item.set("externalId", null);
                    item.set("type", null);
                    values.add(item);
                }

                searchArgumentType.set("operator", clientService.getSearchFieldOperatorByName(fieldType, searchOperator));
                searchField = searchArgumentType;

            } else if (fieldType.equals("SearchEnumMultiSelectField") || fieldType.equals("SearchEnumMultiSelectCustomField")) {

                NsObject searchArgumentType = createCriteria(searchFieldClass, searchFieldName);
                List<String> searchValues = (List<String>) searchArgumentType.get("searchValue");
                searchValues.addAll(searchValue);
                searchArgumentType.set("operator", clientService.getSearchFieldOperatorByName(fieldType, searchOperator));
                searchField = searchArgumentType;

            } else {
                throw new IllegalArgumentException("Unsupported search field type: " + fieldType);
            }

            return searchField;
        } catch (DatatypeConfigurationException | IllegalArgumentException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    public Object toNativeQuery() throws NetSuiteException {
        initSearch();

//        Collection<String> transactionTypes = clientService.getTransactionTypes();

        if (searchRecordDef.getSearchRecordType().equals("transaction")) {

            Class<?> fieldClass = clientService.getSearchFieldClass("SearchEnumMultiSelectField");
            NsObject searchTypeField = createCriteria(fieldClass, null);
            List<String> searchValues = (List<String>) searchTypeField.get("searchValue");
            searchValues.add(recordTypeDef.getRecordType());
            searchTypeField.set("operator", clientService.getSearchFieldOperatorByName(
                    "SearchEnumMultiSelectField", "List.anyOf"));
            NsObject.wrap(searchBasic).set("type", searchTypeField.getTarget());
        }

        if (!customCriteriaList.isEmpty()) {
            Class<?> fieldClass = clientService.getSearchFieldClass("SearchCustomFieldList");
            NsObject customFieldList = createCriteria(fieldClass, null);
            List<Object> list = (List<Object>) customFieldList.get("customField");
            for (Object customCriteria : customCriteriaList) {
                list.add(NsObject.unwrap(customCriteria));
            }
            NsObject.wrap(searchBasic).set("customFieldList", customFieldList.getTarget());
        }

        NsObject.wrap(search).set("basic", searchBasic);

        Object searchRecord = search;
        if (searchAdvanced != null) {
            NsObject.wrap(searchAdvanced).set("criteria", search);
            searchRecord = searchAdvanced;
        }

        return searchRecord;
    }

    public SearchResultSet<?> search() throws NetSuiteException {
        Object searchRecord = toNativeQuery();
        SearchResultEx result = clientService.search(searchRecord);
        SearchResultSet<?> resultSet = new SearchResultSet<>(clientService, searchRecordDef, result,
                new SearchResultSet.IdentityMapper());
        return resultSet;
    }
}
