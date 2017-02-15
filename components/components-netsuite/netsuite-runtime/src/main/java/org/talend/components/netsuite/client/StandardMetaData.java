package org.talend.components.netsuite.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.netsuite.client.metadata.FieldDef;
import org.talend.components.netsuite.client.metadata.RecordTypeDef;
import org.talend.components.netsuite.client.metadata.SearchFieldOperatorTypeDef;
import org.talend.components.netsuite.client.metadata.SearchRecordDef;
import org.talend.components.netsuite.client.metadata.TypeDef;
import org.talend.components.netsuite.model.EnumAccessor;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.model.TypeManager;

import static org.talend.components.netsuite.client.NetSuiteClientService.toInitialUpper;

/**
 *
 */
public abstract class StandardMetaData {

    protected transient final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String SAVED_SEARCH_TYPE_ID = "-119";

    protected Set<String> standardEntityTypes = new HashSet<>();
    protected Set<String> standardTransactionTypes = new HashSet<>();
    protected Set<String> standardItemTypes = new HashSet<>();

    protected Map<String, Class<?>> typeMap = new HashMap<>();

    protected Map<String, RecordTypeDef> recordTypeDefMap = new HashMap<>();

    protected Map<String, SearchRecordDef> searchRecordDefMap = new HashMap<>();
    protected Map<String, String> recordSearchTypeMap = new HashMap<>();
    protected Map<String, Class<?>> searchFieldMap = new HashMap<>();
    protected Map<String, SearchFieldOperatorTypeDef> searchFieldOperatorTypeMap = new HashMap<>();
    protected Map<String, String> searchFieldOperatorMap = new HashMap<>();

    protected StandardMetaData() {
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

    protected void registerRecordSearchTypeMapping(Enum<?>[] recordTypes,
            EnumAccessor recordTypeEnumAccessor, EnumAccessor searchRecordTypeEnumAccessor) {

        for (Enum<?> recordType : recordTypes) {
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
    }

    protected void registerSearchRecordDefs(SearchRecordDef[] searchRecordDefs) {
        for (SearchRecordDef def : searchRecordDefs) {

            registerType(def.getSearchClass(), null);
            registerType(def.getSearchBasicClass(), null);
            registerType(def.getSearchAdvancedClass(), null);

            if (searchRecordDefMap.containsKey(def.getSearchRecordType())) {
                throw new IllegalArgumentException(
                        "Search record def already registered: "
                                + def.getSearchRecordType() + ", search classes to register are "
                                + def.getSearchClass() + ", "
                                + def.getSearchBasicClass() + ", "
                                + def.getSearchAdvancedClass());
            }
            searchRecordDefMap.put(def.getSearchRecordType(), def);
        }
    }

    protected void registerSearchFieldDefs(Class<?>[] searchFieldTable) {
        searchFieldMap = new HashMap<>(searchFieldTable.length);
        for (Class<?> entry : searchFieldTable) {
            searchFieldMap.put(entry.getSimpleName(), entry);
        }
    }

    protected void registerSearchFieldOperatorTypeDefs(SearchFieldOperatorTypeDef[] searchFieldOperatorTable) {
        searchFieldOperatorTypeMap = new HashMap<>(searchFieldOperatorTable.length);
        for (SearchFieldOperatorTypeDef info : searchFieldOperatorTable) {
            searchFieldOperatorTypeMap.put(info.getTypeName(), info);
        }

        searchFieldOperatorMap.put("SearchMultiSelectField", "SearchMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchMultiSelectCustomField", "SearchMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchEnumMultiSelectField", "SearchEnumMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchEnumMultiSelectCustomField", "SearchEnumMultiSelectFieldOperator");
    }

    public Collection<String> getTransactionTypes() {
        return Collections.unmodifiableCollection(standardTransactionTypes);
    }

    public Collection<String> getItemTypes() {
        return Collections.unmodifiableCollection(standardItemTypes);
    }

    public TypeDef getTypeDef(String typeName) {
        Class<?> clazz = typeMap.get(typeName);
        return clazz != null ? getTypeDef(clazz) : null;
    }

    public TypeDef getTypeDef(Class<?> clazz) {
        TypeInfo beanInfo = TypeManager.forClass(clazz);
        List<PropertyInfo> propertyInfos = beanInfo.getProperties();
        List<FieldDef> fields = new ArrayList<>(propertyInfos.size());
        for (PropertyInfo propertyInfo : propertyInfos) {
            String fieldName = propertyInfo.getName();
            Class fieldValueType = propertyInfo.getReadType();
            if ((fieldName.equals("class") && fieldValueType == Class.class) ||
                    (fieldName.equals("nullFieldList") && fieldValueType.getSimpleName().equals("NullField"))) {
                continue;
            }
            boolean isKeyField = isKeyField(clazz, propertyInfo);
            FieldDef fieldInfo = new FieldDef(fieldName, fieldValueType, isKeyField, true);
            fields.add(fieldInfo);
        }

        return new TypeDef(clazz.getSimpleName(), clazz, fields);
    }

    public Collection<String> getRecordTypes() {
        return getRecordTypes(false);
    }

    public Collection<String> getRecordTypes(boolean includeCustomizationTypes) {
        if (!includeCustomizationTypes) {
            Set<String> types = new HashSet<>();
            types.addAll(standardEntityTypes);
            types.addAll(standardTransactionTypes);
            types.addAll(standardItemTypes);
            return Collections.unmodifiableCollection(types);
        } else {
            return Collections.unmodifiableCollection(recordTypeDefMap.keySet());
        }
    }

    public RecordTypeDef getRecordTypeDef(String recordType) {
        return recordTypeDefMap.get(recordType);
    }

    public SearchRecordDef getSearchRecordDefByRecordType(String recordType) {
        RecordTypeDef recordTypeDef = getRecordTypeDef(recordType);
        if (recordTypeDef != null) {
            String searchRecordType = recordSearchTypeMap.get(recordTypeDef.getRecordType());
            return searchRecordDefMap.get(searchRecordType);
        }
        return null;
    }

    public SearchRecordDef getSearchRecordDef(String searchRecordType) {
        return searchRecordDefMap.get(searchRecordType);
    }

    public Class<?> getSearchFieldClass(String searchFieldType) {
        return searchFieldMap.get(searchFieldType);
    }

    public Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName) {
        SearchFieldOperatorTypeDef.QualifiedName operatorQName =
                new SearchFieldOperatorTypeDef.QualifiedName(searchFieldOperatorName);
        String searchFieldOperatorType = searchFieldOperatorMap.get(searchFieldType);
        if (searchFieldOperatorType != null) {
            SearchFieldOperatorTypeDef def = searchFieldOperatorTypeMap.get(searchFieldOperatorType);
            return def.getOperator(searchFieldOperatorName);
        }
        for (SearchFieldOperatorTypeDef def : searchFieldOperatorTypeMap.values()) {
            if (def.hasOperatorName(operatorQName)) {
                return def.getOperator(searchFieldOperatorName);
            }
        }
        return null;
    }

    public Collection<SearchFieldOperatorTypeDef.QualifiedName> getSearchOperatorNames() {
        Set<SearchFieldOperatorTypeDef.QualifiedName> names = new HashSet<>();
        for (SearchFieldOperatorTypeDef info : searchFieldOperatorTypeMap.values()) {
            names.addAll(info.getOperatorNames());
        }
        return Collections.unmodifiableSet(names);
    }

    protected abstract boolean isKeyField(Class<?> entityClass, PropertyInfo propertyInfo);
}
