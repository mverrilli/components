package org.talend.components.netsuite.client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.talend.components.netsuite.client.metadata.NsSearchDef;
import org.talend.components.netsuite.client.metadata.NsSearchFieldOperatorTypeDef;
import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeManager;

/**
 *
 */
public class NsSearch<RecT, SearchRecT> {

    protected NetSuiteConnection connection;
    protected NetSuiteMetaData metaData;

    protected String entityTypeName;
    protected NsSearchDef searchInfo;

    protected NsObject search;             // search class' instance
    protected NsObject searchBasic;        // search basic class' instance
    protected NsObject searchAdvanced;     // search advanced class' instance

    protected String savedSearchId;

    // no use for now
    private List<NsObject> customCriteriaList = new ArrayList<>();

    public NsSearch(NetSuiteConnection connection) throws NetSuiteException {
        this.connection = connection;
        this.metaData = connection.getMetaData();
    }

    public NsSearch entity(final String typeName) throws NetSuiteException {
        entityTypeName = typeName;
        searchInfo = metaData.getSearchDef(typeName);

        // search not found or not supported
        if (searchInfo == null) {
            throw new IllegalArgumentException("Search entity not found: " + entityTypeName);
        }

        return this;
    }

    public NsSearch savedSearchId(String savedSearchId) throws NetSuiteException {
        this.savedSearchId = savedSearchId;
        return this;
    }

    private void initSearch() throws NetSuiteException {
        if (search != null) {
            return;
        }
        try {
            // get a search class instance
            search = NsObject.wrap(searchInfo.getSearchClass().newInstance());

            // get a advanced search class instance and set 'savedSearchId' into it
            searchAdvanced = null;
            if (savedSearchId != null && savedSearchId.length() > 0) {
                searchAdvanced = NsObject.wrap(searchInfo.getSearchAdvancedClass().newInstance());
                searchAdvanced.set("savedSearchId", savedSearchId);
            }

            // basic search class not found or supported
            if (searchInfo.getSearchBasicClass() == null) {
                throw new IllegalArgumentException("Search basic class not found: " + entityTypeName);
            }

            // get a basic search class instance
            searchBasic = NsObject.wrap(searchInfo.getSearchBasicClass().newInstance());

        } catch (InstantiationException | IllegalAccessException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    public NsSearch criteria(String searchFieldName, String searchOperator, List<String> searchValue)
            throws NetSuiteException {

        initSearch();

        TypeInfo searchMetaData = TypeManager.forClass(searchInfo.getSearchBasicClass());

        PropertyInfo fieldMetaData = searchMetaData.getProperty(searchFieldName);

        NsSearchFieldOperatorTypeDef.QualifiedName operatorQName =
                new NsSearchFieldOperatorTypeDef.QualifiedName(searchOperator);

        if (fieldMetaData != null) {

            NsObject criteria = createCriteria(searchBasic,
                    searchFieldName, searchOperator, searchValue);

            searchBasic.set(searchFieldName, criteria);

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

            Class<?> fieldClass = metaData.getSearchFieldClass(searchFieldType);
            NsObject criteria = createCriteria(fieldClass, searchFieldName, searchOperator, searchValue);
            customCriteriaList.add(criteria);
        }

        return this;
    }

    private NsObject createCriteria(Class<?> searchFieldClass, String internalId)
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

    private NsObject createCriteria(NsObject search,
            String searchFieldName, String searchOperator, List<String> searchValue) throws NetSuiteException {

        TypeInfo searchMetaData = TypeManager.forClass(search.getTarget().getClass());
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
                searchArgumentType.set("operator", metaData.getSearchFieldOperatorByName(fieldType, searchOperator));
                searchField = searchArgumentType;

            } else if (fieldType.equals("SearchLongField") || fieldType.equals("SearchLongCustomField")) {

                NsObject searchArgumentType = createCriteria(searchFieldClass, searchFieldName);
                if (searchValue != null && searchValue.size() != 0) {
                    searchArgumentType.set("searchValue", Long.valueOf(Long.parseLong(searchValue.get(0))));
                    if (searchValue.size() > 1) {
                        searchArgumentType.set("searchValue2", Long.valueOf(Long.parseLong(searchValue.get(1))));
                    }
                }
                searchArgumentType.set("operator", metaData.getSearchFieldOperatorByName(fieldType, searchOperator));
                searchField = searchArgumentType;

            } else if (fieldType.equals("SearchDateField") || fieldType.equals("SearchDateCustomField")) {

                NsObject searchArgumentType = createCriteria(searchFieldClass, searchFieldName);

                NsSearchFieldOperatorTypeDef.QualifiedName operatorQName =
                        new NsSearchFieldOperatorTypeDef.QualifiedName(searchOperator);

                if (operatorQName.getDataType().equals("PredefinedDate")) {

                    searchArgumentType.set("predefinedSearchValue",
                            metaData.getSearchFieldOperatorByName(fieldType, searchOperator));

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
                            metaData.getSearchFieldOperatorByName(fieldType, searchOperator));
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
                searchArgumentType.set("operator", metaData.getSearchFieldOperatorByName(fieldType, searchOperator));
                searchField = searchArgumentType;

            } else if (fieldType.equals("SearchMultiSelectField") || fieldType.equals("SearchMultiSelectCustomField")) {

                NsObject searchArgumentType = createCriteria(searchFieldClass, searchFieldName);

                List<Object> values = (List<Object>) searchArgumentType.get("searchValue");
                for (int i = 0; i < searchValue.size(); i++) {
                    NsObject item = NsObject.wrap(metaData.createListOrRecordRef());
                    item.set("name", searchValue.get(i));
                    item.set("internalId", searchValue.get(i));
                    item.set("externalId", null);
                    item.set("type", null);
                    values.add(item);
                }

                searchArgumentType.set("operator", metaData.getSearchFieldOperatorByName(fieldType, searchOperator));
                searchField = searchArgumentType;

            } else if (fieldType.equals("SearchEnumMultiSelectField") || fieldType.equals("SearchEnumMultiSelectCustomField")) {

                NsObject searchArgumentType = createCriteria(searchFieldClass, searchFieldName);
                List<String> searchValues = (List<String>) searchArgumentType.get("searchValue");
                searchValues.addAll(searchValue);
                searchArgumentType.set("operator", metaData.getSearchFieldOperatorByName(fieldType, searchOperator));
                searchField = searchArgumentType;

            } else {
                throw new IllegalArgumentException("Unsupported search field type: " + fieldType);
            }

            return searchField;
        } catch (DatatypeConfigurationException | IllegalArgumentException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    public SearchRecT build() throws NetSuiteException {
        initSearch();

        Collection<String> transactionTypes = metaData.getTransactionTypes();

        if (transactionTypes.contains(searchInfo.getRecordClass().getSimpleName())) {
            Class<?> fieldClass = metaData.getSearchFieldClass("SearchEnumMultiSelectField");
            NsObject searchTypeField = createCriteria(fieldClass, null);
            List<String> searchValues = (List<String>) searchTypeField.get("searchValue");
            searchValues.add(NetSuiteMetaData.toInitialLower(searchInfo.getRecordClass().getSimpleName()));
            searchTypeField.set("operator", metaData.getSearchFieldOperatorByName(
                    "SearchEnumMultiSelectField", "List.anyOf"));
            searchBasic.set("type", searchTypeField.getTarget());
        }

        if (!customCriteriaList.isEmpty()) {
            Class<?> fieldClass = metaData.getSearchFieldClass("SearchCustomFieldList");
            NsObject customFieldList = createCriteria(fieldClass, null);
            List<Object> list = (List<Object>) customFieldList.get("customField");
            for (NsObject customCriteria : customCriteriaList) {
                list.add(customCriteria.getTarget());
            }
            searchBasic.set("customFieldList", customFieldList.getTarget());
        }

        search.set("basic", searchBasic.getTarget());

        NsObject s = search;
        if (searchAdvanced != null) {
            searchAdvanced.set("criteria", search);
            s = searchAdvanced;
        }

        return (SearchRecT) s.getTarget();
    }

    public NsSearchResultSet<RecT> search() throws NetSuiteException {
        SearchRecT searchRecord = build();
        NsSearchResult result = connection.search(searchRecord);
        NsSearchResultSet<RecT> resultSet = new NsSearchResultSet<>(connection, searchInfo, result);
        return resultSet;
    }
}
