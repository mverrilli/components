package org.talend.components.netsuite.client.model;

import static org.talend.components.netsuite.client.model.BeanUtils.toInitialUpper;
import static org.talend.components.netsuite.client.model.ClassUtils.collectXmlTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.netsuite.beans.BeanInfo;
import org.talend.components.netsuite.beans.BeanManager;
import org.talend.components.netsuite.beans.PropertyInfo;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.customfield.CrmCustomFieldAdapter;
import org.talend.components.netsuite.client.model.customfield.CustomFieldAdapter;
import org.talend.components.netsuite.client.model.customfield.CustomFieldRefType;
import org.talend.components.netsuite.client.model.customfield.DefaultCustomFieldAdapter;
import org.talend.components.netsuite.client.model.customfield.EntityCustomFieldAdapter;
import org.talend.components.netsuite.client.model.customfield.ItemCustomFieldAdapter;
import org.talend.components.netsuite.client.model.customfield.ItemOptionCustomFieldAdapter;
import org.talend.components.netsuite.client.model.customfield.TransactionBodyCustomFieldAdapter;
import org.talend.components.netsuite.client.model.customfield.TransactionColumnCustomFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchBooleanFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchDateFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchDoubleFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchEnumMultiSelectFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchFieldOperatorType;
import org.talend.components.netsuite.client.model.search.SearchFieldType;
import org.talend.components.netsuite.client.model.search.SearchLongFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchMultiSelectFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchStringFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchTextNumberFieldAdapter;

/**
 *
 */
public abstract class AbstractMetaData implements MetaData {
    protected transient final Logger logger = LoggerFactory.getLogger(getClass());

    protected Map<String, Class<?>> typeMap = new HashMap<>();

    protected Map<String, Class<?>> searchFieldMap = new HashMap<>();
    protected Map<String, SearchFieldOperatorType> searchFieldOperatorTypeMap = new HashMap<>();
    protected Map<String, String> searchFieldOperatorMap = new HashMap<>();

    protected Map<String, SearchFieldAdapter<?>> searchFieldPopulatorMap = new HashMap<>();

    protected Map<String, CustomFieldAdapter<?>> customFieldAdapterMap = new HashMap<>();

    protected void registerTypes(Class<?> baseClass) {
        Set<Class<?>> classes = new HashSet<>();
        collectXmlTypes(baseClass, baseClass, classes);
        for (Class<?> clazz : classes) {
            registerType(clazz, null);
        }
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

    protected void registerSearchFields(Collection<Class<?>> searchFieldClasses) {
        for (Class<?> entry : searchFieldClasses) {
            String searchFieldTypeName = entry.getSimpleName();

            searchFieldMap.put(searchFieldTypeName, entry);

            registerSearchFieldPopulator(searchFieldTypeName);
        }
    }

    protected void registerSearchFieldOperatorTypes(Collection<Pair<String, Class<?>>> searchFieldOperatorTypes) {
        Collection<SearchFieldOperatorType> searchFieldOperatorTypeList = new ArrayList<>();

        for (Pair<String, Class<?>> spec : searchFieldOperatorTypes) {
            String dataType = spec.getLeft();
            Class<?> clazz = spec.getRight();
            SearchFieldOperatorType operatorTypeInfo = SearchFieldOperatorType.createForEnum(dataType, clazz);
            searchFieldOperatorTypeList.add(operatorTypeInfo);
        }

        searchFieldOperatorTypeList.add(
                // Boolean (Synthetic)
                new SearchFieldOperatorType("Boolean",
                        SearchFieldOperatorType.SearchBooleanFieldOperator.class, null, null)
        );

        for (SearchFieldOperatorType info : searchFieldOperatorTypeList) {
            searchFieldOperatorTypeMap.put(info.getTypeName(), info);
        }

        searchFieldOperatorMap.put(SearchFieldType.MULTI_SELECT.getFieldTypeName(), "SearchMultiSelectFieldOperator");
        searchFieldOperatorMap.put(SearchFieldType.CUSTOM_MULTI_SELECT.getFieldTypeName(), "SearchMultiSelectFieldOperator");
        searchFieldOperatorMap.put(SearchFieldType.SELECT.getFieldTypeName(), "SearchEnumMultiSelectFieldOperator");
        searchFieldOperatorMap.put(SearchFieldType.CUSTOM_SELECT.getFieldTypeName(), "SearchEnumMultiSelectFieldOperator");
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

    protected void registerCustomFieldAdapters() {
        registerCustomFieldAdapter(new CrmCustomFieldAdapter<>());
        registerCustomFieldAdapter(new EntityCustomFieldAdapter<>());
        registerCustomFieldAdapter(new ItemCustomFieldAdapter<>());
        registerCustomFieldAdapter(new ItemOptionCustomFieldAdapter<>());
        registerCustomFieldAdapter(new TransactionBodyCustomFieldAdapter<>());
        registerCustomFieldAdapter(new TransactionColumnCustomFieldAdapter<>());
        registerCustomFieldAdapter(new DefaultCustomFieldAdapter<>("customList"));
        registerCustomFieldAdapter(new DefaultCustomFieldAdapter<>("customRecord"));
        registerCustomFieldAdapter(new DefaultCustomFieldAdapter<>("customRecordType"));
        registerCustomFieldAdapter(new DefaultCustomFieldAdapter<>("otherCustomField"));
        registerCustomFieldAdapter(new DefaultCustomFieldAdapter<>("itemNumberCustomField"));
    }

    protected void registerCustomFieldAdapter(CustomFieldAdapter<?> adapter) {
        customFieldAdapterMap.put(adapter.getType(), adapter);
    }

    protected Class<?> getTypeClass(String typeName) {
        Class<?> clazz = typeMap.get(typeName);
        if (clazz != null) {
            return clazz;
        }
        RecordTypeDesc recordType = getRecordType(typeName);
        if (recordType != null) {
            return recordType.getRecordClass();
        }
        return null;
    }

    @Override
    public TypeDesc getTypeInfo(String typeName) {
        Class<?> clazz = getTypeClass(typeName);
//        if (clazz == null) {
//            RecordTypeDesc recordType = getRecordType(typeName);
//            if (recordType != null) {
//                clazz = recordType.getRecordClass();
//            }
//        }
        return clazz != null ? getTypeInfo(clazz) : null;
    }

    @Override
    public TypeDesc getTypeInfo(Class<?> clazz) {
        BeanInfo beanInfo = BeanManager.getBeanInfo(clazz);
        List<PropertyInfo> propertyInfos = beanInfo.getProperties();

        List<FieldDesc> fields = new ArrayList<>(propertyInfos.size());

        for (PropertyInfo propertyInfo : propertyInfos) {
            String fieldName = toInitialUpper(propertyInfo.getName());

            Class fieldValueType = propertyInfo.getReadType();
            if ((fieldName.equals("class") && fieldValueType == Class.class) ||
                    (fieldName.equals("nullFieldList") && fieldValueType.getSimpleName().equals("NullField"))) {
                continue;
            }

            boolean isKeyField = isKeyField(clazz, propertyInfo);
            SimpleFieldDesc fieldDesc = new SimpleFieldDesc(fieldName, fieldValueType, isKeyField, true);
            fieldDesc.setPropertyName(propertyInfo.getName());
            fields.add(fieldDesc);
        }

        return new TypeDesc(clazz.getSimpleName(), clazz, fields);
    }

    @Override public boolean isRecord(String typeName) {
        return getRecordType(typeName) != null;
    }

    @Override public SearchRecordTypeDesc getSearchRecordType(RecordTypeDesc recordType) {
        SearchRecordTypeDesc searchRecordType = getSearchRecordType(recordType.getSearchRecordType());
        return searchRecordType;
    }

    @Override public Class<?> getSearchFieldClass(String searchFieldType) {
        return searchFieldMap.get(searchFieldType);
    }

    @Override public Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName) {
        SearchFieldOperatorType.QualifiedName operatorQName =
                new SearchFieldOperatorType.QualifiedName(searchFieldOperatorName);
        String searchFieldOperatorType = searchFieldOperatorMap.get(searchFieldType);
        if (searchFieldOperatorType != null) {
            SearchFieldOperatorType def = searchFieldOperatorTypeMap.get(searchFieldOperatorType);
            return def.getOperator(searchFieldOperatorName);
        }
        for (SearchFieldOperatorType def : searchFieldOperatorTypeMap.values()) {
            if (def.hasOperatorName(operatorQName)) {
                return def.getOperator(searchFieldOperatorName);
            }
        }
        return null;
    }

    @Override public Collection<SearchFieldOperatorType.QualifiedName> getSearchOperatorNames() {
        Set<SearchFieldOperatorType.QualifiedName> names = new HashSet<>();
        for (SearchFieldOperatorType info : searchFieldOperatorTypeMap.values()) {
            names.addAll(info.getOperatorNames());
        }
        return Collections.unmodifiableSet(names);
    }

    @Override public SearchFieldAdapter<?> getSearchFieldPopulator(String fieldType) {
        return searchFieldPopulatorMap.get(fieldType);
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
        if (propertyInfo.getName().equals("internalId")
                || propertyInfo.getName().equals("externalId")
                || propertyInfo.getName().equals("scriptId")) {
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
