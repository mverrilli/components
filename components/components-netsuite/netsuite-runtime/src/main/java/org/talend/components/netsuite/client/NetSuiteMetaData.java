package org.talend.components.netsuite.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.talend.components.netsuite.client.metadata.NsTypeDef;
import org.talend.components.netsuite.client.metadata.NsFieldDef;
import org.talend.components.netsuite.client.metadata.NsSearchDef;
import org.talend.components.netsuite.client.metadata.NsSearchFieldOperatorTypeDef;
import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeManager;

/**
 *
 */
public abstract class NetSuiteMetaData {

    protected Map<String, NsTypeDef> typeMap = new HashMap<>();
    protected Map<String, NsSearchDef> searchMap = new HashMap<>();
    protected Map<String, Class<?>> searchFieldMap = new HashMap<>();
    protected Map<String, NsSearchFieldOperatorTypeDef> searchFieldOperatorTypeMap = new HashMap<>();
    protected Map<String, String> searchFieldOperatorMap = new HashMap<>();

    public NetSuiteMetaData() {
    }

    public NetSuiteMetaData(NetSuiteMetaData master) {
        typeMap.putAll(master.typeMap);
        searchMap.putAll(master.searchMap);
        searchFieldMap.putAll(master.searchFieldMap);
        searchFieldOperatorTypeMap.putAll(master.searchFieldOperatorTypeMap);
        searchFieldOperatorMap.putAll(master.searchFieldOperatorMap);
    }

    protected void registerType(Class<?> typeClass, String typeName) {
        String typeNameToRegister = typeName != null ? typeName : typeClass.getSimpleName();
        if (typeMap.containsKey(typeNameToRegister)) {
            NsTypeDef entityInfo = typeMap.get(typeNameToRegister);
            if (entityInfo.getTypeClass() == typeClass) {
                return;
            } else {
                throw new IllegalArgumentException("Type already registered: " +
                        typeNameToRegister + ", class to register is " +
                        typeClass + ", registered class is " +
                        typeMap.get(typeNameToRegister));
            }
        }

        TypeInfo beanInfo = TypeManager.forClass(typeClass);
        List<PropertyInfo> propertyInfos = beanInfo.getProperties();
        List<NsFieldDef> fields = new ArrayList<>(propertyInfos.size());
        for (PropertyInfo propertyInfo : propertyInfos) {
            String fieldName = propertyInfo.getName();
            Class fieldValueType = propertyInfo.getReadType();
            if ((fieldName.equals("class") && fieldValueType == Class.class) ||
                    (fieldName.equals("nullFieldList") && fieldValueType.getSimpleName().equals("NullField"))) {
                continue;
            }
            boolean isKeyField = isKeyField(typeClass, propertyInfo);
            NsFieldDef fieldInfo = new NsFieldDef(fieldName, fieldValueType, isKeyField, true, propertyInfo);
            fields.add(fieldInfo);
        }

        NsTypeDef entityInfo = new NsTypeDef(typeName, typeClass, fields, beanInfo);
        typeMap.put(typeNameToRegister, entityInfo);
    }

    protected void registerSearchDefs(NsSearchDef[] searchTable) {
        for (NsSearchDef entry : searchTable) {
            String typeName = entry.getRecordTypeName();

            registerType(entry.getRecordClass(), typeName);
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
    }

    protected void registerSearchFieldDefs(Class<?>[] searchFieldTable) {
        searchFieldMap = new HashMap<>(searchFieldTable.length);
        for (Class<?> entry : searchFieldTable) {
            searchFieldMap.put(entry.getSimpleName(), entry);
        }
    }

    protected void registerSearchFieldOperatorTypeDefs(NsSearchFieldOperatorTypeDef[] searchFieldOperatorTable) {
        searchFieldOperatorTypeMap = new HashMap<>(searchFieldOperatorTable.length);
        for (NsSearchFieldOperatorTypeDef info : searchFieldOperatorTable) {
            searchFieldOperatorTypeMap.put(info.getTypeName(), info);
        }

        searchFieldOperatorMap.put("SearchMultiSelectField", "SearchMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchMultiSelectCustomField", "SearchMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchEnumMultiSelectField", "SearchEnumMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchEnumMultiSelectCustomField", "SearchEnumMultiSelectFieldOperator");
    }

    public abstract Collection<String> getTransactionTypes();

    public abstract Collection<String> getItemTypes();

    public NsTypeDef getTypeDef(String typeName) {
        return typeMap.get(typeName);
    }

    public NsTypeDef getTypeDef(Class<?> clazz) {
        return typeMap.get(clazz.getSimpleName());
    }

    public Collection<String> getRecordTypes() {
        return Collections.unmodifiableSet(searchMap.keySet());
    }

    public NsSearchDef getSearchDef(String typeName) {
        return searchMap.get(typeName);
    }

    public Class<?> getSearchFieldClass(String searchFieldType) {
        return searchFieldMap.get(searchFieldType);
    }

    public Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName) {
        NsSearchFieldOperatorTypeDef.QualifiedName operatorQName =
                new NsSearchFieldOperatorTypeDef.QualifiedName(searchFieldOperatorName);
        String searchFieldOperatorType = searchFieldOperatorMap.get(searchFieldType);
        if (searchFieldOperatorType != null) {
            NsSearchFieldOperatorTypeDef def = searchFieldOperatorTypeMap.get(searchFieldOperatorType);
            return def.getOperator(searchFieldOperatorName);
        }
        for (NsSearchFieldOperatorTypeDef def : searchFieldOperatorTypeMap.values()) {
            if (def.hasOperatorName(operatorQName)) {
                return def.getOperator(searchFieldOperatorName);
            }
        }
        return null;
    }

    public Collection<NsSearchFieldOperatorTypeDef.QualifiedName> getSearchOperatorNames() {
        Set<NsSearchFieldOperatorTypeDef.QualifiedName> names = new HashSet<>();
        for (NsSearchFieldOperatorTypeDef info : searchFieldOperatorTypeMap.values()) {
            names.addAll(info.getOperatorNames());
        }
        return Collections.unmodifiableSet(names);
    }

    public abstract <T> T createListOrRecordRef() throws NetSuiteException;

    public abstract <T> T createRecordRef() throws NetSuiteException;

    public <T> T createType(String typeName) throws NetSuiteException {
        NsTypeDef typeDef = getTypeDef(typeName);
        if (typeDef == null) {
            throw new NetSuiteException("Unknown type: " + typeName);
        }
        return (T) createInstance(typeDef.getTypeClass());
    }

    protected <T> T createInstance(Class<T> clazz) throws NetSuiteException {
        try {
            T target = clazz.cast(clazz.newInstance());
            return target;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new NetSuiteException("Failed to instantiate object: " + clazz, e);
        }
    }

    protected abstract boolean isKeyField(Class<?> entityClass, PropertyInfo propertyInfo);

    public static String toInitialUpper(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    public static String toInitialLower(String value) {
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }

    public static String toNetSuiteType(String value) {
        return "_" + toInitialLower(value);
    }

}
