package org.talend.components.netsuite.client.model;

import java.util.Collection;

import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.custom.CustomFieldRefType;
import org.talend.components.netsuite.client.model.search.SearchFieldOperatorTypeInfo;
import org.talend.components.netsuite.client.model.search.SearchFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchRecordInfo;

/**
 *
 */
public interface MetaDataProvider {

    Class<?> getTypeClass(String typeName);

    TypeInfo getTypeInfo(String typeName);

    TypeInfo getTypeInfo(Class<?> clazz);

    Collection<String> getRecordTypes();

    boolean isRecord(String typeName);

    RecordTypeInfo getRecordTypeInfo(String recordType);

    SearchRecordInfo getSearchRecordTypeInfoByRecordType(String recordType);

    SearchRecordInfo getSearchRecordInfo(String searchRecordType);

    Class<?> getSearchFieldClass(String searchFieldType);

    Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName);

    Collection<SearchFieldOperatorTypeInfo.QualifiedName> getSearchOperatorNames();

    SearchFieldAdapter<?> getSearchFieldPopulator(String fieldType);

    CustomFieldRefType getCustomFieldRefType(String customizationType);

    CustomFieldRefType getCustomFieldRefType(String recordType, String customFieldType, Object customField);

    <T> T createType(String typeName) throws NetSuiteException;
}
