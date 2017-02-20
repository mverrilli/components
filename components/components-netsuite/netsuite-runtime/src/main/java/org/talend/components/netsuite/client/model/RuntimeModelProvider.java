package org.talend.components.netsuite.client.model;

import java.util.Collection;

import org.talend.components.netsuite.client.NetSuiteException;

/**
 *
 */
public interface RuntimeModelProvider {

    Class<?> getTypeClass(String typeName);

    TypeInfo getTypeInfo(String typeName);

    TypeInfo getTypeInfo(Class<?> clazz);

    Collection<String> getRecordTypes();

    RecordTypeInfo getRecordTypeInfo(String recordType);

    SearchRecordInfo getSearchRecordTypeInfoByRecordType(String recordType);

    SearchRecordInfo getSearchRecordInfo(String searchRecordType);

    Class<?> getSearchFieldClass(String searchFieldType);

    Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName);

    Collection<SearchFieldOperatorTypeInfo.QualifiedName> getSearchOperatorNames();

    SearchFieldPopulator<?> getSearchFieldPopulator(String fieldType);

    <T> T createType(String typeName) throws NetSuiteException;
}
