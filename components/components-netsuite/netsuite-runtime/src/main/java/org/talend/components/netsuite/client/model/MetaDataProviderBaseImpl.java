package org.talend.components.netsuite.client.model;

import static org.talend.components.netsuite.client.model.BeanUtils.getEnumFromStringMapper;
import static org.talend.components.netsuite.client.model.BeanUtils.getEnumToStringMapper;
import static org.talend.components.netsuite.client.model.BeanUtils.toInitialLower;
import static org.talend.components.netsuite.client.model.BeanUtils.toInitialUpper;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.netsuite.beans.BeanInfo;
import org.talend.components.netsuite.beans.BeanManager;
import org.talend.components.netsuite.beans.EnumAccessor;
import org.talend.components.netsuite.beans.Mapper;
import org.talend.components.netsuite.beans.PropertyInfo;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.custom.CrmCustomFieldAdapter;
import org.talend.components.netsuite.client.model.custom.CustomFieldAdapter;
import org.talend.components.netsuite.client.model.custom.CustomFieldRefType;
import org.talend.components.netsuite.client.model.custom.DefaultCustomFieldAdapter;
import org.talend.components.netsuite.client.model.custom.EntityCustomFieldAdapter;
import org.talend.components.netsuite.client.model.custom.ItemCustomFieldAdapter;
import org.talend.components.netsuite.client.model.custom.ItemOptionCustomFieldAdapter;
import org.talend.components.netsuite.client.model.custom.TransactionBodyCustomFieldAdapter;
import org.talend.components.netsuite.client.model.custom.TransactionColumnCustomFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchBooleanFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchDateFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchDoubleFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchEnumMultiSelectFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchFieldOperatorTypeInfo;
import org.talend.components.netsuite.client.model.search.SearchFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchLongFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchMultiSelectFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchRecordInfo;
import org.talend.components.netsuite.client.model.search.SearchStringFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchTextNumberFieldAdapter;

/**
 *
 */
public abstract class MetaDataProviderBaseImpl implements MetaDataProvider {
    protected transient final Logger logger = LoggerFactory.getLogger(getClass());

    protected Class<?> recordBaseClass;
    protected Class<? extends Enum> recordTypeEnumClass;
    protected EnumAccessor recordTypeEnumAccessor;

    protected Set<Class<?>> searchRecordBaseClasses = new HashSet<>();
    protected Class<? extends Enum> searchRecordTypeEnumClass;
    protected EnumAccessor searchRecordTypeEnumAccessor;

    protected Class<?> recordRefClass;

    protected Set<String> standardEntityTypes = new HashSet<>();
    protected Set<String> standardTransactionTypes = new HashSet<>();
    protected Set<String> standardItemTypes = new HashSet<>();

    protected Map<String, Class<?>> typeMap = new HashMap<>();

    protected Map<String, RecordTypeInfo> recordTypeMap = new HashMap<>();

    protected Set<Class<?>> searchFieldClasses = new HashSet<>();

    protected Map<String, SearchRecordInfo> searchRecordMap = new HashMap<>();
    protected Map<String, String> recordSearchTypeMap = new HashMap<>();
    protected Map<String, Class<?>> searchFieldMap = new HashMap<>();
    protected Map<String, SearchFieldOperatorTypeInfo> searchFieldOperatorTypeMap = new HashMap<>();
    protected Map<String, String> searchFieldOperatorMap = new HashMap<>();

    protected Set<Pair<String, Class<?>>> searchFieldOperatorTypes = new HashSet<>();

    protected Set<String> unspecifiedRecordTypes = new HashSet<>();
    protected Set<String> unspecifiedRecordTypeNames = new HashSet<>();
    protected Set<String> excludedRecordTypeNames = new HashSet<>();

    protected Set<String> excludedSearchRecordTypeNames = new HashSet<>();
    protected Set<String> unspecifiedSearchRecordTypes = new HashSet<>();

    protected Map<String, SearchFieldAdapter<?>> searchFieldPopulatorMap = new HashMap<>();

    protected Map<String, CustomFieldRefType> customFieldRefTypeMap = new HashMap<>();
    protected Map<String, CustomFieldAdapter<?>> customFieldAdapterMap = new HashMap<>();

    protected void setRecordBaseClass(Class<?> recordBaseClass) {
        this.recordBaseClass = recordBaseClass;
    }

    protected void setRecordTypeEnumClass(Class<? extends Enum> recordTypeEnumClass) {
        this.recordTypeEnumClass = recordTypeEnumClass;
        this.recordTypeEnumAccessor = BeanUtils.getEnumAccessor(recordTypeEnumClass);
    }

    protected void setSearchRecordBaseClasses(Collection<Class<?>> searchRecordBaseClasses) {
        this.searchRecordBaseClasses.addAll(searchRecordBaseClasses);
    }

    protected void setSearchRecordTypeEnumClass(Class<? extends Enum> searchRecordTypeEnumClass) {
        this.searchRecordTypeEnumClass = searchRecordTypeEnumClass;
        this.searchRecordTypeEnumAccessor = BeanUtils.getEnumAccessor(searchRecordTypeEnumClass);
    }

    protected void setRecordRefClass(Class<?> recordRefClass) {
        this.recordRefClass = recordRefClass;
    }

    protected void registerType(Class<?> typeClass, String typeName) {
        String typeNameToRegister = typeName != null ? typeName : typeClass.getSimpleName();
        if (typeMap.containsKey(typeNameToRegister)) {
            Class<?> clazz = typeMap.get(typeNameToRegister);
            if (clazz == typeClass) {
                return;
            } else {
                throw new IllegalArgumentException("Type already registered: " +
                        typeNameToRegister + ", class to register is " +
                        typeClass + ", registered class is " +
                        typeMap.get(typeNameToRegister));
            }
        }
        typeMap.put(typeNameToRegister, typeClass);
    }

    protected void registerRecordTypes() {

        XmlSeeAlso xmlSeeAlso = recordBaseClass.getAnnotation(XmlSeeAlso.class);
        Collection<Class<?>> recordClasses = new HashSet<>(Arrays.<Class<?>>asList(xmlSeeAlso.value()));

        Set<String> unresolvedTypeNames = new HashSet<>();

        for (Class<?> clazz : recordClasses) {
            if (clazz == recordBaseClass
                    || !recordBaseClass.isAssignableFrom(clazz)
                    || Modifier.isAbstract(clazz.getModifiers())) {
                continue;
            }

            String recordTypeClassSimpleName = clazz.getSimpleName();
            if (!excludedRecordTypeNames.contains(recordTypeClassSimpleName)) {
                String recordTypeName = null;
                if (unspecifiedRecordTypeNames.contains(recordTypeClassSimpleName)) {
                    recordTypeName = toInitialLower(recordTypeClassSimpleName);
                } else {
                    try {
                        Enum<?> recordType = recordTypeEnumAccessor.mapFromString(
                                toInitialLower(recordTypeClassSimpleName));
                        recordTypeName = recordTypeEnumAccessor.mapToString(recordType);
                    } catch (IllegalArgumentException e) {
                        unresolvedTypeNames.add(recordTypeClassSimpleName);
                    }
                }
                if (recordTypeName != null) {
                    RecordTypeInfo def = new RecordTypeInfo(recordTypeName, clazz);
                    if (!recordTypeMap.containsKey(recordTypeName)) {
                        recordTypeMap.put(toInitialUpper(recordTypeName), def);
                    } else {
                        throw new IllegalArgumentException("Record type already registered: " + recordTypeClassSimpleName);
                    }
                }
                registerType(clazz, recordTypeClassSimpleName);
            }
        }

        if (!unresolvedTypeNames.isEmpty()) {
            throw new IllegalStateException("Unresolved record types detected: " + unresolvedTypeNames);
        }
    }

    protected void registerRecordSearchTypeMapping() {
        for (Enum<?> recordType : recordTypeEnumClass.getEnumConstants()) {
            String recordTypeName = recordTypeEnumAccessor.mapToString(recordType);
            String recordTypeNameCapitalized = toInitialUpper(recordTypeName);
            if (standardEntityTypes.contains(recordTypeNameCapitalized)) {
                try {
                    Enum<?> searchRecordType = searchRecordTypeEnumAccessor.mapFromString(recordTypeName);
                    String searchRecordTypeName = searchRecordTypeEnumAccessor.mapToString(searchRecordType);
                    recordSearchTypeMap.put(recordTypeName, searchRecordTypeName);
                } catch (IllegalArgumentException e) {
                    logger.error("Invalid entity record type: '" + recordTypeName + "'");
                }
            } else if (standardTransactionTypes.contains(recordTypeNameCapitalized)) {
                recordSearchTypeMap.put(recordTypeName, "transaction");
            } else if (standardItemTypes.contains(recordTypeNameCapitalized)) {
                recordSearchTypeMap.put(recordTypeName, "item");
            } else {
                logger.error("Search record type not found for '" + recordTypeName + "'");
            }
        }

        for (String recordTypeName : unspecifiedRecordTypeNames) {
            recordSearchTypeMap.put(toInitialLower(recordTypeName), toInitialLower(recordTypeName));
        }
    }

    protected void registerSearchRecords() {

        Collection<Class<?>> searchRecordClasses = new HashSet<>();

        for (Class<?> searchRecordBaseClass : searchRecordBaseClasses) {
            XmlSeeAlso xmlSeeAlso = searchRecordBaseClass.getAnnotation(XmlSeeAlso.class);
            for (Class<?> clazz : xmlSeeAlso.value()) {
                if (clazz == searchRecordBaseClass
                        || !searchRecordBaseClass.isAssignableFrom(clazz)
                        || Modifier.isAbstract(clazz.getModifiers())) {
                    continue;
                }
                searchRecordClasses.add(clazz);
            }
        }

        Set<String> searchRecordTypeSet = new HashSet<>();
        for (Enum value : searchRecordTypeEnumClass.getEnumConstants()) {
            String searchRecordTypeName = searchRecordTypeEnumAccessor.mapToString(value);
            searchRecordTypeSet.add(searchRecordTypeName);
        }
        searchRecordTypeSet.addAll(unspecifiedSearchRecordTypes);

        Map<String, Class<?>> searchRecordMap = new HashMap<>();
        for (Class<?> clazz : searchRecordClasses) {
            String searchRecordTypeName = clazz.getSimpleName();
            if (!excludedSearchRecordTypeNames.contains(searchRecordTypeName)) {
                if (searchRecordMap.containsKey(searchRecordTypeName)) {
                    throw new IllegalStateException("Search record class already registered: " + searchRecordTypeName + ", " + clazz);
                }
                searchRecordMap.put(searchRecordTypeName, clazz);
            }
        }

        List<SearchRecordInfo> searchRecordInfoList = new ArrayList<>();
        Set<Class<?>> unresolvedSearchRecords = new HashSet<>(searchRecordMap.values());

        for (String searchRecordType : searchRecordTypeSet) {
            String searchRecordTypeName = toInitialUpper(searchRecordType);

            Class<?> searchClass;
            Class<?> searchBasicClass;
            Class<?> searchAdvancedClass;

            searchClass = searchRecordMap.get(searchRecordTypeName + "Search");
            unresolvedSearchRecords.remove(searchClass);

            searchBasicClass = searchRecordMap.get(searchRecordTypeName + "SearchBasic");
            if (searchBasicClass == null) {
                throw new IllegalStateException("Search basic class not found: " + searchRecordType + ", " + searchRecordTypeName);
            } else {
                unresolvedSearchRecords.remove(searchBasicClass);
            }

            searchAdvancedClass = searchRecordMap.get(searchRecordTypeName + "SearchAdvanced");
            unresolvedSearchRecords.remove(searchAdvancedClass);

            SearchRecordInfo searchRecordInfo = new SearchRecordInfo(searchRecordType, searchClass, searchBasicClass,
                    searchAdvancedClass);

            searchRecordInfoList.add(searchRecordInfo);
        }

        if (!unresolvedSearchRecords.isEmpty()) {
            throw new IllegalStateException("Unresolved search record types detected: " + unresolvedSearchRecords);
        }

        registerSearchRecords(searchRecordInfoList);
    }

    protected void registerSearchRecords(Collection<SearchRecordInfo> searchRecordInfos) {
        for (SearchRecordInfo def : searchRecordInfos) {

            // For some record types main search record not available
            if (def.getSearchClass() != null) {
                registerType(def.getSearchClass(), null);
            }

            // Basic must be present
            registerType(def.getSearchBasicClass(), null);

            // For some record types advanced search record not available
            if (def.getSearchAdvancedClass() != null) {
                registerType(def.getSearchAdvancedClass(), null);
            }

            if (searchRecordMap.containsKey(def.getSearchRecordType())) {
                throw new IllegalArgumentException(
                        "Search record def already registered: "
                                + def.getSearchRecordType() + ", search classes to register are "
                                + def.getSearchClass() + ", "
                                + def.getSearchBasicClass() + ", "
                                + def.getSearchAdvancedClass());
            }
            searchRecordMap.put(def.getSearchRecordType(), def);
        }
    }

    protected void registerSearchFields() {
        for (Class<?> entry : searchFieldClasses) {
            String searchFieldTypeName = entry.getSimpleName();

            searchFieldMap.put(searchFieldTypeName, entry);

            registerSearchFieldPopulator(searchFieldTypeName);
        }
    }

    protected void registerSearchFieldOperatorTypes() {
        Collection<SearchFieldOperatorTypeInfo> searchFieldOperatorTypeInfoList = new ArrayList<>();

        for (Pair<String, Class<?>> spec : searchFieldOperatorTypes) {
            String dataType = spec.getLeft();
            Class<?> clazz = spec.getRight();
            SearchFieldOperatorTypeInfo operatorTypeInfo = createSearchFieldOperatorTypeInfo(dataType, clazz);
            searchFieldOperatorTypeInfoList.add(operatorTypeInfo);
        }

        searchFieldOperatorTypeInfoList.add(
                // Boolean (Synthetic)
                new SearchFieldOperatorTypeInfo("Boolean",
                        SearchFieldOperatorTypeInfo.SearchBooleanFieldOperator.class, null, null)
        );

        for (SearchFieldOperatorTypeInfo info : searchFieldOperatorTypeInfoList) {
            searchFieldOperatorTypeMap.put(info.getTypeName(), info);
        }

        searchFieldOperatorMap.put("SearchMultiSelectField", "SearchMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchMultiSelectCustomField", "SearchMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchEnumMultiSelectField", "SearchEnumMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchEnumMultiSelectCustomField", "SearchEnumMultiSelectFieldOperator");
    }

    protected SearchFieldAdapter<?> registerSearchFieldPopulator(String fieldType) {
        SearchFieldAdapter<?> fieldPopulator;
        Class<?> fieldClass = getSearchFieldClass(fieldType);
        if ("SearchBooleanField".equals(fieldType) || "SearchBooleanCustomField".equals(fieldType)) {
            fieldPopulator = new SearchBooleanFieldAdapter<>(this, fieldType, fieldClass);
        } else if ("SearchStringField".equals(fieldType) || "SearchStringCustomField".equals(fieldType)) {
            fieldPopulator = new SearchStringFieldAdapter<>(this, fieldType, fieldClass);
        } else if ("SearchTextNumberField".equals(fieldType)) {
            fieldPopulator = new SearchTextNumberFieldAdapter<>(this, fieldType, fieldClass);
        } else if ("SearchLongField".equals(fieldType) || "SearchLongCustomField".equals(fieldType)) {
            fieldPopulator = new SearchLongFieldAdapter<>(this, fieldType, fieldClass);
        } else if ("SearchDoubleField".equals(fieldType) || "SearchDoubleCustomField".equals(fieldType)) {
            fieldPopulator = new SearchDoubleFieldAdapter<>(this, fieldType, fieldClass);
        } else if ("SearchDateField".equals(fieldType) || "SearchDateCustomField".equals(fieldType)) {
            fieldPopulator = new SearchDateFieldAdapter<>(this, fieldType, fieldClass);
        } else if ("SearchMultiSelectField".equals(fieldType) || "SearchMultiSelectCustomField".equals(fieldType)) {
            fieldPopulator = new SearchMultiSelectFieldAdapter<>(this, fieldType, fieldClass);
        } else if ("SearchEnumMultiSelectField".equals(fieldType) || "SearchEnumMultiSelectCustomField".equals(fieldType)) {
            fieldPopulator = new SearchEnumMultiSelectFieldAdapter<>(this, fieldType, fieldClass);
        } else {
            throw new IllegalArgumentException("Invalid search field type: " + fieldType);
        }
        searchFieldPopulatorMap.put(fieldType, fieldPopulator);
        return fieldPopulator;
    }

    protected void registerCustomFieldRefTypes() {
        customFieldRefTypeMap.put("_checkBox", CustomFieldRefType.BOOLEAN);
        customFieldRefTypeMap.put("_currency", CustomFieldRefType.DOUBLE);
        customFieldRefTypeMap.put("_date", CustomFieldRefType.DATE);
        customFieldRefTypeMap.put("_datetime", CustomFieldRefType.DATE);
        customFieldRefTypeMap.put("_decimalNumber", CustomFieldRefType.LONG);
        customFieldRefTypeMap.put("_document", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_eMailAddress", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_freeFormText", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_help", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_hyperlink", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_image", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_inlineHTML", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_integerNumber", CustomFieldRefType.LONG);
        customFieldRefTypeMap.put("_listRecord", CustomFieldRefType.SELECT);
        customFieldRefTypeMap.put("_longText", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_multipleSelect", CustomFieldRefType.MULTI_SELECT);
        customFieldRefTypeMap.put("_password", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_percent", CustomFieldRefType.DOUBLE);
        customFieldRefTypeMap.put("_phoneNumber", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_richText", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_textArea", CustomFieldRefType.STRING);
        customFieldRefTypeMap.put("_timeOfDay", CustomFieldRefType.DATE);
        customFieldRefTypeMap.put("DEFAULT", CustomFieldRefType.STRING);
    }

    protected void registerCustomFieldAdapters() {
        registerCustomFieldAdapter(new CrmCustomFieldAdapter<>(this));
        registerCustomFieldAdapter(new EntityCustomFieldAdapter<>(this));
        registerCustomFieldAdapter(new ItemCustomFieldAdapter<>(this));
        registerCustomFieldAdapter(new ItemOptionCustomFieldAdapter<>(this));
        registerCustomFieldAdapter(new TransactionBodyCustomFieldAdapter<>(this));
        registerCustomFieldAdapter(new TransactionColumnCustomFieldAdapter<>(this));
        registerCustomFieldAdapter(new DefaultCustomFieldAdapter<>(this, "customList"));
        registerCustomFieldAdapter(new DefaultCustomFieldAdapter<>(this, "customRecord"));
        registerCustomFieldAdapter(new DefaultCustomFieldAdapter<>(this, "customRecordType"));
        registerCustomFieldAdapter(new DefaultCustomFieldAdapter<>(this, "otherCustomField"));
        registerCustomFieldAdapter(new DefaultCustomFieldAdapter<>(this, "itemNumberCustomField"));
    }

    protected void registerCustomFieldAdapter(CustomFieldAdapter<?> adapter) {
        customFieldAdapterMap.put(adapter.getType(), adapter);
    }

    protected void buildModel() throws NetSuiteException {
        registerRecordTypes();
        registerSearchRecords();
        registerRecordSearchTypeMapping();
        registerSearchFields();
        registerSearchFieldOperatorTypes();
        registerCustomFieldRefTypes();
        registerCustomFieldAdapters();
    }

    public static <T> SearchFieldOperatorTypeInfo<T> createSearchFieldOperatorTypeInfo(
            String dataType, Class<T> clazz) {
        return new SearchFieldOperatorTypeInfo<>(dataType, clazz,
                (Mapper<T, String>) getEnumToStringMapper((Class<Enum>) clazz),
                (Mapper<String, T>) getEnumFromStringMapper((Class<Enum>) clazz));
    }

    @Override public Class<?> getTypeClass(String typeName) {
        return typeMap.get(typeName);
    }

    @Override public TypeInfo getTypeInfo(String typeName) {
        Class<?> clazz = getTypeClass(typeName);
        return clazz != null ? getTypeInfo(clazz) : null;
    }

    @Override public TypeInfo getTypeInfo(Class<?> clazz) {
        BeanInfo beanInfo = BeanManager.getBeanInfo(clazz);
        List<PropertyInfo> propertyInfos = beanInfo.getProperties();
        List<FieldInfo> fields = new ArrayList<>(propertyInfos.size());
        for (PropertyInfo propertyInfo : propertyInfos) {
            String fieldName = propertyInfo.getName();
            Class fieldValueType = propertyInfo.getReadType();
            if ((fieldName.equals("class") && fieldValueType == Class.class) ||
                    (fieldName.equals("nullFieldList") && fieldValueType.getSimpleName().equals("NullField"))) {
                continue;
            }
            boolean isKeyField = isKeyField(clazz, propertyInfo);
            FieldInfo fieldInfo = new FieldInfo(fieldName, fieldValueType, isKeyField, true);
            fields.add(fieldInfo);
        }

        return new TypeInfo(clazz.getSimpleName(), clazz, fields);
    }

    @Override public Collection<String> getRecordTypes() {
        return Collections.unmodifiableCollection(recordTypeMap.keySet());
    }

    @Override public boolean isRecord(String typeName) {
        return recordTypeMap.containsKey(typeName);
    }

    @Override public RecordTypeInfo getRecordTypeInfo(String recordType) {
        return recordTypeMap.get(recordType);
    }

    @Override public SearchRecordInfo getSearchRecordTypeInfoByRecordType(String recordType) {
        RecordTypeInfo recordTypeInfo = getRecordTypeInfo(recordType);
        if (recordTypeInfo != null) {
            String searchRecordType = recordSearchTypeMap.get(recordTypeInfo.getRecordType());
            return searchRecordMap.get(searchRecordType);
        }
        return null;
    }

    @Override public SearchRecordInfo getSearchRecordInfo(String searchRecordType) {
        return searchRecordMap.get(searchRecordType);
    }

    @Override public Class<?> getSearchFieldClass(String searchFieldType) {
        return searchFieldMap.get(searchFieldType);
    }

    @Override public Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName) {
        SearchFieldOperatorTypeInfo.QualifiedName operatorQName =
                new SearchFieldOperatorTypeInfo.QualifiedName(searchFieldOperatorName);
        String searchFieldOperatorType = searchFieldOperatorMap.get(searchFieldType);
        if (searchFieldOperatorType != null) {
            SearchFieldOperatorTypeInfo def = searchFieldOperatorTypeMap.get(searchFieldOperatorType);
            return def.getOperator(searchFieldOperatorName);
        }
        for (SearchFieldOperatorTypeInfo def : searchFieldOperatorTypeMap.values()) {
            if (def.hasOperatorName(operatorQName)) {
                return def.getOperator(searchFieldOperatorName);
            }
        }
        return null;
    }

    @Override public Collection<SearchFieldOperatorTypeInfo.QualifiedName> getSearchOperatorNames() {
        Set<SearchFieldOperatorTypeInfo.QualifiedName> names = new HashSet<>();
        for (SearchFieldOperatorTypeInfo info : searchFieldOperatorTypeMap.values()) {
            names.addAll(info.getOperatorNames());
        }
        return Collections.unmodifiableSet(names);
    }

    @Override public SearchFieldAdapter<?> getSearchFieldPopulator(String fieldType) {
        return searchFieldPopulatorMap.get(fieldType);
    }

    @Override
    public CustomFieldRefType getCustomFieldRefType(String customizationType) {
        return customFieldRefTypeMap.get(customizationType);
    }

    @Override
    public CustomFieldRefType getCustomFieldRefType(String recordType, String customFieldType, Object customField) {
        CustomFieldAdapter customFieldAdapter = customFieldAdapterMap.get(customFieldType);
        if (customFieldAdapter.appliesTo(recordType, customField)) {
            return customFieldAdapter.apply(customField);
        }
        return null;
    }

    protected boolean isKeyField(Class<?> entityClass, PropertyInfo propertyInfo) {
        if (recordBaseClass.isAssignableFrom(entityClass) &&
                (propertyInfo.getName().equals("internalId") || propertyInfo.getName().equals("externalId"))) {
            return true;
        }
        if (recordRefClass.isAssignableFrom(entityClass) &&
                (propertyInfo.getName().equals("internalId") || propertyInfo.getName().equals("externalId"))) {
            return true;
        }
        return false;
    }

    @Override public <T> T createType(String typeName) throws NetSuiteException {
        Class<?> clazz = getTypeClass(typeName);
        if (clazz == null) {
            throw new NetSuiteException("Unknown type: " + typeName);
        }
        return (T) createInstance(clazz);
    }

    protected <T> T createInstance(Class<T> clazz) throws NetSuiteException {
        try {
            T target = clazz.cast(clazz.newInstance());
            return target;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new NetSuiteException("Failed to instantiate object: " + clazz, e);
        }
    }
}
