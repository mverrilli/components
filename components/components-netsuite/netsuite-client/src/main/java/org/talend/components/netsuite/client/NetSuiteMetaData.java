package org.talend.components.netsuite.client;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.talend.components.netsuite.BeanMetaData;
import org.talend.components.netsuite.PropertyMetaData;

/**
 *
 */
public abstract class NetSuiteMetaData {

    protected Map<String, Entity> typeMap;
    protected Map<String, Class<?>[]> searchMap;
    protected Map<String, Class<?>> searchFieldMap;
    protected Map<String, Class<?>> searchFieldOperatorMap;

    protected void initMetaData(
            Class<?>[][] searchTable,
            Class<?>[] searchFieldTable,
            Class<?>[] searchFieldOperatorTable) {

        typeMap = new HashMap<>(searchTable.length);

        searchMap = new HashMap<>(searchTable.length);

        for (Class<?>[] entry : searchTable) {
            Class<?> typeClass = entry[0];
            String typeName = typeClass.getSimpleName();

            registerType(typeClass, typeName);

            Map<String, Field> fields = new HashMap<>();
            BeanMetaData beanMetaData = BeanMetaData.forClass(typeClass);
            Collection<PropertyMetaData> descriptors = beanMetaData.getProperties();
            for (PropertyMetaData descriptor : descriptors) {
                String fieldName = descriptor.getName();
                Class fieldValueType = descriptor.getReadType();
                if ((fieldName.equals("class") && fieldValueType == Class.class) ||
                        (fieldName.equals("nullFieldList") && fieldValueType.getSimpleName().equals("NullField"))) {
                    continue;
                }
                Field field = new Field(fieldName, fieldValueType);
                fields.put(field.getName(), field);
            }

            Entity entity = new Entity(typeName, typeClass, Collections.unmodifiableMap(fields));
            typeMap.put(typeName, entity);

            Class<?> searchClass = entry[1];
            Class<?> searchBasicClass = entry[2];
            Class<?> searchAdvancedClass = entry[3];
            if (searchMap.containsKey(typeName)) {
                throw new IllegalArgumentException(
                        "Search entry already registered: " + typeName + ", search classes to register are "
                                + searchClass + ", " + searchBasicClass + ", " + searchAdvancedClass);
            }
            searchMap.put(typeName, new Class<?>[]{
                    searchClass,
                    searchBasicClass,
                    searchAdvancedClass
            });
        }

        searchFieldMap = new HashMap<>(searchFieldTable.length);
        for (Class<?> entry : searchFieldTable) {
            searchFieldMap.put(entry.getSimpleName(), entry);
        }

        searchFieldOperatorMap = new HashMap<>(searchFieldOperatorTable.length);
        for (Class<?> entry : searchFieldOperatorTable) {
            searchFieldOperatorMap.put(entry.getSimpleName(), entry);
        }
    }

    protected void registerType(Class<?> typeClass, String typeName) {
        String typeNameToRegister = typeName != null ? typeName : typeClass.getSimpleName();
        if (typeMap.containsKey(typeNameToRegister)) {
            throw new IllegalArgumentException("Type already registered: " + typeNameToRegister +
                    ", class to register is " + typeClass +
                    ", registered class is " + typeMap.get(typeNameToRegister));
        }
    }

    public abstract Collection<String> getTransactionTypes();

    public abstract Collection<String> getItemTypes();

    public Class<?> getEntityClass(String typeName) {
        Entity entity = typeMap.get(typeName);
        return entity != null ? entity.getEntityType() : null;
    }

    public Class<?> getSearchClass(String typeName) {
        return getSearchClass(typeName, 0);
    }

    public Class<?> getSearchBasicClass(String typeName) {
        return getSearchClass(typeName, 1);
    }

    public Class<?> getSearchAdvancedClass(String typeName) {
        return getSearchClass(typeName, 2);
    }

    private Class<?> getSearchClass(String typeName, int key) {
        Class<?>[] entry = searchMap.get(typeName);
        return entry[key];
    }

    public Class<?> getSearchFieldClass(String searchFieldTypeName) {
        return searchFieldMap.get(searchFieldTypeName);
    }

    public Class<?> getSearchFieldOperatorClass(String searchFieldOperatorTypeName) {
        return searchFieldOperatorMap.get(searchFieldOperatorTypeName);
    }

    public abstract Class<?> getListOrRecordRefClass();

    public static String toInitialUpper(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    public static String toInitialLower(String value) {
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }

    public static String toNetSuiteType(String value) {
        return "_" + toInitialLower(value);
    }

    public static class Entity {
        private String name;
        private Class<?> entityType;
        private Map<String, NetSuiteMetaData.Field> fields;

        public Entity(String name, Class<?> entityType, Map<String, NetSuiteMetaData.Field> fields) {
            this.name = name;
            this.entityType = entityType;
            this.fields = fields;
        }

        public String getName() {
            return name;
        }

        public Class<?> getEntityType() {
            return entityType;
        }

        public Map<String, NetSuiteMetaData.Field> getFields() {
            return fields;
        }
    }

    public static class Field {
        private String name;
        private Class valueType;

        public Field(String name, Class valueType) {
            this.name = name;
            this.valueType = valueType;
        }

        public String getName() {
            return name;
        }

        public Class getValueType() {
            return valueType;
        }
    }
}
