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

import org.talend.components.netsuite.BeanMetaData;
import org.talend.components.netsuite.PropertyMetaData;

import static org.talend.components.netsuite.client.NsObject.asNsObject;

/**
 *
 */
public class NsSearch {

    protected NetSuiteConnection<?> connection;
    protected NetSuiteMetaData metaData;

    protected String entityTypeName;
    protected Class<?> entityClass;
    protected Class<?> searchClass;
    protected Class<?> searchBasicClass;
    protected Class<?> searchAdvancedClass;

    protected NsObject search;             // search class' instance
    protected NsObject searchBasic;        // search basic class' instance
    protected NsObject searchAdvanced;     // search advanced class' instance

    protected String savedSearchId;
    protected boolean isItemSearch;

    // no use for now
    private List<NsObject> customCriteriaList = new ArrayList<>();

    public NsSearch(NetSuiteConnection<?> connection) throws NetSuiteException {
        this.connection = connection;
        this.metaData = connection.getMetaData();
    }

    public NsSearch entity(final String typeName) throws NetSuiteException {
        entityTypeName = typeName;
        entityClass = metaData.getEntityClass(typeName);
        searchClass = metaData.getSearchClass(typeName);
        searchBasicClass = metaData.getSearchBasicClass(typeName);
        searchAdvancedClass = metaData.getSearchAdvancedClass(typeName);

        // if type is customRecord
        if (searchClass == null) {
            entityTypeName = "CustomRecord";
            searchClass = metaData.getSearchClass(entityTypeName);
            searchBasicClass = metaData.getSearchBasicClass(entityTypeName);
            searchAdvancedClass = metaData.getSearchAdvancedClass(entityTypeName);
        }

        // search class not found or supported
        if (searchClass == null) {
            throw new IllegalArgumentException("SearchClass not found: " + entityTypeName);
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
            search = asNsObject(searchClass.newInstance());

            // get a advanced search class instance and set 'savedSearchId' into it
            searchAdvanced = null;
            if (savedSearchId != null && savedSearchId.length() > 0) {
                searchAdvanced = asNsObject(searchAdvancedClass.newInstance());
                searchAdvanced.set("savedSearchId", savedSearchId);
            }

            // search class is itemSearch, it's special
            if (searchClass.getSimpleName().equals("ItemSearch")) {
                isItemSearch = true;
            }

            // basic search class not found or supported
            if (searchBasicClass == null) {
                throw new IllegalArgumentException("SearchBasicClass not found: " + entityTypeName);
            }

            // get a basic search class instance
            searchBasic = asNsObject(searchBasicClass.newInstance());

        } catch (InstantiationException | IllegalAccessException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    public void criterion(String searchFieldName, String searchOperator, List<String> searchValue, String forcedType)
            throws NetSuiteException {

        initSearch();

        BeanMetaData searchBasicMetaData = BeanMetaData.forClass(searchBasic.getClass());

        try {
            if ((searchValue.get(0) != null) && (searchFieldName != null)) {
                PropertyMetaData descriptor = searchBasicMetaData.getProperty(searchFieldName);

                if (descriptor != null) {
                    NsObject customCriteria;

                    if (forcedType.equals("String")) {

                        NsObject searchArgumentType = createSearchField(
                                "SearchStringCustomField", searchFieldName);
                        if (searchValue != null && searchValue.size() != 0) {
                            searchArgumentType.set("searchValue", searchValue.get(0));
                        }
                        searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                                "SearchStringFieldOperator", searchOperator));
                        customCriteria = searchArgumentType;

                    } else if (forcedType.equals("Long")) {

                        NsObject searchArgumentType = createSearchField(
                                "SearchLongCustomField", searchFieldName);
                        if (searchValue != null && searchValue.size() != 0) {
                            searchArgumentType.set("searchValue", Long.valueOf(Long.parseLong(searchValue.get(0))));
                            if (searchValue.size() > 1) {
                                searchArgumentType.set("searchValue2", Long.valueOf(Long.parseLong(searchValue.get(1))));
                            }
                        }
                        searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                                "SearchLongFieldOperator", searchOperator));
                        customCriteria = searchArgumentType;

                    } else if (forcedType.equals("Date")) {

                        NsObject searchArgumentType = createSearchField(
                                "SearchDateCustomField", searchFieldName);
                        if (searchValue != null && searchValue.size() != 0) {
                            Calendar calValue = Calendar.getInstance();
                            Calendar calValue2 = Calendar.getInstance();

                            String dateFormat = "yyyy-MM-dd";
                            String timeFormat = "HH:mm:ss";

                            String format = dateFormat + " " + timeFormat;
                            if (searchValue.get(0).length() == dateFormat.length()) {
                                format = dateFormat;
                            }

                            if (searchValue.get(0).length() == timeFormat.length()) {
                                searchValue.set(0, new SimpleDateFormat(dateFormat)
                                        .format(calValue.getTime()) + " " + searchValue.get(0));
                                if (searchValue.size() > 1) {
                                    searchValue.set(1, new SimpleDateFormat(dateFormat)
                                            .format(calValue.getTime()) + " " + searchValue.get(1));
                                }
                            }

                            DateFormat df = new SimpleDateFormat(format);

                            try {
                                calValue.setTime(df.parse(searchValue.get(0)));
                                if (searchValue.size() > 1) {
                                    calValue2.setTime(df.parse(searchValue.get(1)));
                                }
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

                        searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                                "SearchDateFieldOperator", searchOperator));
                        customCriteria = searchArgumentType;

                    } else if (forcedType.equals("Boolean")) {

                        NsObject searchArgumentType = createSearchField(
                                "SearchBooleanCustomField", searchFieldName);
                        searchArgumentType.set("searchValue", Boolean.valueOf(Boolean.parseBoolean(searchValue.get(0))));
                        customCriteria = searchArgumentType;

                    } else if (forcedType.equals("Double")) {

                        NsObject searchArgumentType = createSearchField(
                                "SearchDoubleCustomField", searchFieldName);
                        if (searchValue != null && searchValue.size() != 0) {
                            searchArgumentType.set("searchValue", Double.valueOf(Double.parseDouble(searchValue.get(0))));
                            if (searchValue.size() > 1) {
                                searchArgumentType.set("searchValue2", Double.valueOf(Double.parseDouble(searchValue.get(1))));
                            }
                        }
                        searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                                "SearchDoubleFieldOperator", searchOperator));
                        customCriteria = searchArgumentType;

                    } else if (forcedType.equals("List")) {

                        NsObject searchArgumentType = createSearchField(
                                "SearchMultiSelectCustomField", searchFieldName);
                        int len = searchValue.size();
                        List<Object> lr = (List<Object>) searchArgumentType.get("searchValue");
                        for (int i = 0; i < len; i++) {
                            NsObject item = createListOrRecordRef();
                            item.set("name", searchValue.get(i));
                            lr.add(item);
                        }
                        searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                                "SearchMultiSelectFieldOperator", searchOperator));
                        customCriteria = searchArgumentType;

                    } else {
                        throw new IllegalArgumentException("Unsupported search field type: " + forcedType);
                    }

                    customCriteriaList.add(customCriteria);

                } else {
                    searchBasic.set(searchFieldName, getSearchField(
                            searchBasic, searchValue, searchFieldName, searchOperator).getTarget());
                }
            }

        } catch (DatatypeConfigurationException | IllegalArgumentException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    private NsObject createSearchField(String searchFieldTypeName, String internalId) throws NetSuiteException {
        try {
            NsObject searchField = asNsObject(
                    metaData.getSearchFieldClass(searchFieldTypeName).newInstance());
            if (internalId != null) {
                searchField.set("internalId", internalId);
            }
            return searchField;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    private Enum getSearchFieldOperatorEnumValue(String searchFieldOperatorTypeName, String value)
            throws NetSuiteException {
        try {
            Class enumClass = metaData.getSearchFieldOperatorClass(searchFieldOperatorTypeName);
            Enum enumValue = Enum.valueOf(enumClass, value);
            return enumValue;
        } catch (IllegalArgumentException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    private NsObject createListOrRecordRef() throws NetSuiteException {
        try {
            NsObject obj = asNsObject(
                    metaData.getListOrRecordRefClass().newInstance());
            return obj;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    private NsObject getSearchField(NsObject search,
            List<String> searchValue, String searchFieldName, String searchOperator) throws NetSuiteException {
        try {
            BeanMetaData searchMetaData = BeanMetaData.forClass(search.getTarget().getClass());

            NsObject criteria;

            String searchType = searchMetaData.getProperty(searchFieldName)
                    .getWriteType().getSimpleName();

            if (searchType.equals("SearchStringField")) {

                NsObject searchArgumentType = createSearchField(
                        "SearchStringField", searchFieldName);
                if (searchValue != null && searchValue.size() != 0) {
                    searchArgumentType.set("searchValue", searchValue.get(0));
                }
                searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                        "SearchStringFieldOperator", searchOperator));
                criteria = searchArgumentType;

            } else if (searchType.equals("SearchLongField")) {

                NsObject searchArgumentType = createSearchField(
                        "SearchLongField", null);
                if (searchValue != null && searchValue.size() != 0) {
                    searchArgumentType.set("searchValue", Long.valueOf(Long.parseLong(searchValue.get(0))));
                    if (searchValue.size() > 1) {
                        searchArgumentType.set("searchValue2", Long.valueOf(Long.parseLong(searchValue.get(1))));
                    }
                }
                searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                        "SearchLongFieldOperator", searchOperator));
                criteria = searchArgumentType;

            } else if (searchType.equals("SearchDateField")) {

                NsObject searchArgumentType = createSearchField(
                        "SearchDateField", null);
                if (searchValue != null && searchValue.size() != 0) {
                    Calendar calValue = Calendar.getInstance();

                    String dateFormat = "yyyy-MM-dd";
                    String timeFormat = "HH:mm:ss";

                    String format = dateFormat + " " + timeFormat;

                    if (searchValue.get(0).length() == dateFormat.length()) {
                        format = dateFormat;
                    }

                    if (searchValue.get(0).length() == timeFormat.length()) {
                        searchValue.set(0, new SimpleDateFormat(dateFormat).format(calValue.getTime()) + " " + searchValue.get(0));
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

                searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                        "SearchDateFieldOperator", searchOperator));

                criteria = searchArgumentType;

            } else if (searchType.equals("SearchBooleanField")) {

                NsObject searchArgumentType = createSearchField(
                        "SearchBooleanField", null);
                searchArgumentType.set("searchValue", Boolean.valueOf(searchValue.get(0)));
                criteria = searchArgumentType;

            } else if (searchType.equals("SearchDoubleField")) {

                NsObject searchArgumentType = createSearchField(
                        "SearchDoubleField", null);
                BeanMetaData md = BeanMetaData.forClass(searchArgumentType.getTarget().getClass());
                if (searchValue != null && searchValue.size() != 0) {
                    searchArgumentType.set("searchValue", Double.valueOf(Double.parseDouble(searchValue.get(0))));
                    if (searchValue.size() > 1) {
                        searchArgumentType.set("searchValue2", Double.valueOf(Double.parseDouble(searchValue.get(1))));
                    }
                }
                searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                        "SearchDoubleFieldOperator", searchOperator));
                criteria = searchArgumentType;

            } else if (searchType.equals("SearchMultiSelectField")) {

                NsObject searchArgumentType = createSearchField(
                        "SearchMultiSelectField", null);

                List<Object> values = (List<Object>) searchArgumentType.get("searchValue");
                for (int i = 0; i < searchValue.size(); i++) {
                    NsObject item = createListOrRecordRef();
                    item.set("name", searchValue.get(i));
                    item.set("internalId", searchValue.get(i));
                    item.set("externalId", null);
                    item.set("type", null);
                    values.add(item);
                }

                searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                        "SearchMultiSelectFieldOperator", searchOperator));
                criteria = searchArgumentType;

            } else if (searchType.equals("SearchEnumMultiSelectField")) {

                NsObject searchArgumentType = createSearchField(
                        "SearchEnumMultiSelectField", null);
                List<String> searchValues = (List<String>) searchArgumentType.get("searchValue");
                searchValues.addAll(searchValue);
                searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                        "SearchEnumMultiSelectFieldOperator", searchOperator));
                criteria = searchArgumentType;

            } else if (searchType.equals("String[]")) {

                NsObject searchArgumentType = createSearchField(
                        "SearchEnumMultiSelectField", null);
                List<String> searchValues = (List<String>) searchArgumentType.get("searchValue");
                searchValues.addAll(searchValue);
                searchArgumentType.set("operator", getSearchFieldOperatorEnumValue(
                        "SearchEnumMultiSelectFieldOperator", searchOperator));
                criteria = searchArgumentType;

            } else {
                throw new IllegalArgumentException("Unsupported search field type: " + searchType);
            }

            return criteria;
        } catch (DatatypeConfigurationException | IllegalArgumentException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    public NsSearchRecord build() throws NetSuiteException {
        Collection<String> transactionTypes = metaData.getTransactionTypes();

        if (transactionTypes.contains(entityClass.getSimpleName())) {
            NsObject searchTypeField = createSearchField(
                    "SearchEnumMultiSelectField", null);
            List<String> searchValues = (List<String>) searchTypeField.get("searchValue");
            searchValues.add(NetSuiteMetaData.toInitialLower(entityClass.getSimpleName()));
            searchTypeField.set("operator", getSearchFieldOperatorEnumValue(
                    "SearchEnumMultiSelectFieldOperator", "ANY_OF"));
            search.set("type", searchTypeField.getTarget());
        }

        if (!customCriteriaList.isEmpty()) {
            NsObject customFieldList = createSearchField(
                    "SearchCustomFieldList", null);
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

        return new NsSearchRecord(s.getTarget());
    }

    public NsSearchResultSet search() throws NetSuiteException {
        NsSearchRecord searchRecord = build();
        NsSearchResult result = connection.search(searchRecord);
        NsSearchResultSet resultSet = new NsSearchResultSet(connection, entityClass, isItemSearch, result);
        return resultSet;
    }
}
