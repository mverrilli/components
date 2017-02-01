package org.talend.components.netsuite.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.talend.components.netsuite.BeanMetaData;
import org.talend.components.netsuite.PropertyMetaData;

/**
 *
 */
public abstract class NetSuiteMetaData {

    protected Map<String, EntityInfo> typeMap;
    protected Map<String, SearchInfo> searchMap;
    protected Map<String, Class<?>> searchFieldMap;
    protected Map<String, SearchFieldOperatorTypeInfo> searchFieldOperatorTypeMap;
    protected Set<String> searchFieldOperatorNames;
    protected Map<String, String> searchFieldOperatorMappings;

    protected void initMetaData(
            SearchInfo[] searchTable,
            Class<?>[] searchFieldTable,
            SearchFieldOperatorTypeInfo[] searchFieldOperatorTable) {

        typeMap = new HashMap<>(searchTable.length);

        searchMap = new HashMap<>(searchTable.length);

        for (SearchInfo entry : searchTable) {
            String typeName = entry.getEntityTypeName();

            registerType(entry.getEntityClass(), typeName);
            registerType(entry.getSearchClass(), null);
            registerType(entry.getSearchBasicClass(), null);
            registerType(entry.getSearchAdvancedClass(), null);

            if (searchMap.containsKey(typeName)) {
                throw new IllegalArgumentException(
                        "Search entry already registered: "
                                + typeName + ", search classes to register are "
                                + entry.getSearchClass() + ", "
                                + entry.getSearchBasicClass() + ", "
                                + entry.getSearchAdvancedClass());
            }
            searchMap.put(typeName, entry);
        }

        searchFieldMap = new HashMap<>(searchFieldTable.length);
        for (Class<?> entry : searchFieldTable) {
            searchFieldMap.put(entry.getSimpleName(), entry);
        }

        searchFieldOperatorTypeMap = new HashMap<>(searchFieldOperatorTable.length);
        searchFieldOperatorNames = new HashSet<>();
        for (SearchFieldOperatorTypeInfo info : searchFieldOperatorTable) {
            searchFieldOperatorTypeMap.put(info.getTypeName(), info);

            List<String> operatorNames = info.getQualifiedNames();
            searchFieldOperatorNames.addAll(operatorNames);
        }

        searchFieldOperatorMappings = new HashMap<>();
        searchFieldOperatorMappings.put("SearchBooleanField", "SearchBooleanFieldOperator");
        searchFieldOperatorMappings.put("SearchStringField", "SearchStringFieldOperator");
        searchFieldOperatorMappings.put("SearchLongField", "SearchLongFieldOperator");
        searchFieldOperatorMappings.put("SearchDoubleField", "SearchDoubleFieldOperator");
        searchFieldOperatorMappings.put("SearchMultiSelectField", "SearchMultiSelectFieldOperator");
        searchFieldOperatorMappings.put("SearchEnumMultiSelectField", "SearchEnumMultiSelectFieldOperator");
        searchFieldOperatorMappings.put("SearchBooleanCustomField", "SearchBooleanFieldOperator");
        searchFieldOperatorMappings.put("SearchStringCustomField", "SearchStringFieldOperator");
        searchFieldOperatorMappings.put("SearchLongCustomField", "SearchLongFieldOperator");
        searchFieldOperatorMappings.put("SearchDoubleCustomField", "SearchDoubleFieldOperator");
        searchFieldOperatorMappings.put("SearchMultiSelectCustomField", "SearchMultiSelectFieldOperator");
        searchFieldOperatorMappings.put("SearchEnumMultiSelectCustomField", "SearchEnumMultiSelectFieldOperator");
    }

    protected void registerType(Class<?> typeClass, String typeName) {
        String typeNameToRegister = typeName != null ? typeName : typeClass.getSimpleName();
        if (typeMap.containsKey(typeNameToRegister)) {
            EntityInfo entityInfo = typeMap.get(typeNameToRegister);
            if (entityInfo.getEntityClass() == typeClass) {
                return;
            } else {
                throw new IllegalArgumentException("Type already registered: " + typeNameToRegister + ", class to register is " + typeClass
                        + ", registered class is " + typeMap.get(typeNameToRegister));
            }
        }

        Map<String, FieldInfo> fields = new HashMap<>();
        BeanMetaData beanInfo = BeanMetaData.forClass(typeClass);
        Collection<PropertyMetaData> propertyInfos = beanInfo.getProperties();
        for (PropertyMetaData propertyInfo : propertyInfos) {
            String fieldName = propertyInfo.getName();
            Class fieldValueType = propertyInfo.getReadType();
            if ((fieldName.equals("class") && fieldValueType == Class.class) ||
                    (fieldName.equals("nullFieldList") && fieldValueType.getSimpleName().equals("NullField"))) {
                continue;
            }
            boolean isKeyField = isKeyField(typeClass, propertyInfo);
            FieldInfo fieldInfo = new FieldInfo(fieldName, fieldValueType, isKeyField, true, propertyInfo);
            fields.put(fieldInfo.getName(), fieldInfo);
        }

        EntityInfo entityInfo = new EntityInfo(typeName, typeClass,
                Collections.unmodifiableMap(fields), beanInfo);
        typeMap.put(typeNameToRegister, entityInfo);
    }

    public abstract Collection<String> getTransactionTypes();

    public abstract Collection<String> getItemTypes();

    public EntityInfo getEntity(String typeName) {
        EntityInfo entityInfo = typeMap.get(typeName);
        return entityInfo;
    }

    public EntityInfo getEntity(Class<?> clazz) {
        EntityInfo entityInfo = typeMap.get(clazz.getSimpleName());
        return entityInfo;
    }

    public SearchInfo getSearchInfo(String typeName) {
        return searchMap.get(typeName);
    }

    public Class<?> getSearchFieldClass(String searchFieldType) {
        return searchFieldMap.get(searchFieldType);
    }

    public SearchFieldOperatorTypeInfo getSearchFieldOperatorInfo(String operatorType) {
        return searchFieldOperatorTypeMap.get(operatorType);
    }

    public SearchFieldOperatorTypeInfo getSearchFieldOperatorInfoByFieldType(String searchFieldType) {
        String operatorType = searchFieldOperatorMappings.get(searchFieldType);
        return searchFieldOperatorTypeMap.get(operatorType);
    }

    public Object getSearchFieldOperator(String searchFieldType, String searchFieldOperatorName) {
        SearchFieldOperatorTypeInfo operatorTypeInfo = getSearchFieldOperatorInfoByFieldType(searchFieldType);
        return operatorTypeInfo.getValue(searchFieldOperatorName);
    }

    public Collection<String> getSearchOperatorNames() {
        return Collections.unmodifiableSet(searchFieldOperatorNames);
    }

    public abstract Class<?> getListOrRecordRefClass();

    protected abstract boolean isKeyField(Class<?> entityClass, PropertyMetaData propertyInfo);

    public static String toInitialUpper(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    public static String toInitialLower(String value) {
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }

    public static String toNetSuiteType(String value) {
        return "_" + toInitialLower(value);
    }

    public static class EntityInfo {
        private String name;
        private Class<?> entityClass;
        private Map<String, FieldInfo> fields;
        private BeanMetaData beanInfo;

        public EntityInfo(String name, Class<?> entityClass, Map<String, FieldInfo> fields,
                BeanMetaData beanInfo) {
            this.name = name;
            this.entityClass = entityClass;
            this.fields = fields;
            this.beanInfo = beanInfo;
        }

        public String getName() {
            return name;
        }

        public Class<?> getEntityClass() {
            return entityClass;
        }

        public FieldInfo getField(String name) {
            return fields.get(name);
        }

        public Map<String, FieldInfo> getFields() {
            return fields;
        }

        public BeanMetaData getBeanInfo() {
            return beanInfo;
        }
    }

    public static class FieldInfo {
        private String name;
        private Class valueType;
        private boolean key;
        private boolean nullable;
        private PropertyMetaData propertyInfo;

        public FieldInfo(String name, Class valueType, boolean key, boolean nullable,
                PropertyMetaData propertyInfo) {
            this.name = name;
            this.valueType = valueType;
            this.key = key;
            this.nullable = nullable;
            this.propertyInfo = propertyInfo;
        }

        public String getName() {
            return name;
        }

        public Class getValueType() {
            return valueType;
        }

        public int getLength() {
            return 0;
        }

        public boolean isKey() {
            return key;
        }

        public boolean isNullable() {
            return nullable;
        }

        public PropertyMetaData getPropertyInfo() {
            return propertyInfo;
        }
    }

    public static class SearchInfo {
        private String entityTypeName;
        private Class<?> entityClass;
        private Class<?> searchClass;
        private Class<?> searchBasicClass;
        private Class<?> searchAdvancedClass;

        public SearchInfo(String entityTypeName, Class<?> entityClass,
                Class<?> searchClass, Class<?> searchBasicClass,
                Class<?> searchAdvancedClass) {
            this.entityTypeName = entityTypeName;
            this.entityClass = entityClass;
            this.searchClass = searchClass;
            this.searchBasicClass = searchBasicClass;
            this.searchAdvancedClass = searchAdvancedClass;
        }

        public SearchInfo(Class<?> entityClass,
                Class<?> searchClass, Class<?> searchBasicClass, Class<?> searchAdvancedClass) {
            this.entityClass = entityClass;
            this.entityTypeName = entityClass.getSimpleName();
            this.searchClass = searchClass;
            this.searchBasicClass = searchBasicClass;
            this.searchAdvancedClass = searchAdvancedClass;
        }

        public String getEntityTypeName() {
            return entityTypeName;
        }

        public Class<?> getEntityClass() {
            return entityClass;
        }

        public Class<?> getSearchClass() {
            return searchClass;
        }

        public Class<?> getSearchBasicClass() {
            return searchBasicClass;
        }

        public Class<?> getSearchAdvancedClass() {
            return searchAdvancedClass;
        }

        public boolean isItemSearch() {
            return entityTypeName.equals("ItemSearch");
        }
    }

    public static class SearchFieldOperatorTypeInfo<T> {
        private String dataType;
        private String typeName;
        private Class<T> operatorClass;
        private Mapper<T, String> mapper;
        private Mapper<String, T> reverseMapper;

        public SearchFieldOperatorTypeInfo(String dataType, Class<T> operatorClass,
                Mapper<T, String> mapper, Mapper<String, T> reverseMapper) {

            this.dataType = dataType;
            this.operatorClass = operatorClass;
            this.typeName = operatorClass.getSimpleName();
            this.mapper = mapper;
            this.reverseMapper = reverseMapper;
        }

        public String getTypeName() {
            return typeName;
        }

        public Class<T> getOperatorClass() {
            return operatorClass;
        }

        public Mapper<T, String> getMapper() {
            return mapper;
        }

        public Mapper<String, T> getReverseMapper() {
            return reverseMapper;
        }

        public String mapToString(T stringValue) {
            return mapper.map(stringValue);
        }

        public Object mapFromString(String stringValue) {
            return reverseMapper.map(stringValue);
        }

        public String getQualifiedName(Object value) {
            if (operatorClass == SearchBooleanFieldOperator.class) {
                return dataType;
            } else {
                return dataType + "." + mapToString((T) value);
            }
        }

        public Object getValue(String qualifiedName) {
            if (operatorClass == SearchBooleanFieldOperator.class) {
                if (!qualifiedName.equals(dataType)) {
                    throw new IllegalArgumentException("Invalid operator data type: "
                            + "'" + qualifiedName + "' != '" + dataType + "'");
                }
                return SearchBooleanFieldOperator.INSTANCE;
            } else {
                int i = qualifiedName.indexOf(".");
                if (i == -1) {
                    throw new IllegalArgumentException("Invalid operator name: " + qualifiedName);
                }
                String thatDataType = qualifiedName.substring(0, i);
                if (!thatDataType.equals(dataType)) {
                    throw new IllegalArgumentException("Invalid operator data type: "
                            + "'" + thatDataType + "' != '" + dataType + "'");
                }
                String thatValue = qualifiedName.substring(i + 1);
                return mapFromString(thatValue);
            }
        }

        public List<String> getQualifiedNames() {
            if (operatorClass.isEnum()) {
                Enum[] values = ((Class<? extends Enum>) getOperatorClass()).getEnumConstants();
                List<String> names = new ArrayList<>(values.length);
                for (Enum value : values) {
                    names.add(getQualifiedName(value));
                }
                return names;
            } else if (operatorClass == SearchBooleanFieldOperator.class) {
                return Arrays.asList(dataType);
            } else {
                throw new IllegalStateException("Unsupported operator type: " + operatorClass);
            }
        }

        public List<?> getValues() {
            if (operatorClass.isEnum()) {
                Enum[] values = ((Class<? extends Enum>) getOperatorClass()).getEnumConstants();
                return Arrays.asList(values);
            } else if (operatorClass == SearchBooleanFieldOperator.class) {
                return Arrays.asList(SearchBooleanFieldOperator.INSTANCE);
            } else {
                throw new IllegalStateException("Unsupported operator type: " + operatorClass);
            }
        }
    }

    public static class SearchBooleanFieldOperator {

        public static final SearchBooleanFieldOperator INSTANCE = new SearchBooleanFieldOperator();
    }

}
