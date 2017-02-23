package org.talend.components.netsuite.client.model;

import java.util.Collection;

import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.customfield.CustomFieldRefType;
import org.talend.components.netsuite.client.model.search.SearchFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchFieldOperatorType;

/**
 *
 */
public interface MetaData {

    TypeDesc getTypeInfo(String typeName);

    TypeDesc getTypeInfo(Class<?> clazz);

    Collection<RecordTypeDesc> getRecordTypes();

    boolean isRecord(String typeName);

    RecordTypeDesc getRecordType(String recordType);

    SearchRecordTypeDesc getSearchRecordType(RecordTypeDesc recordType);

    SearchRecordTypeDesc getSearchRecordType(String searchRecordType);

    Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName);

    Collection<SearchFieldOperatorType.QualifiedName> getSearchOperatorNames();

    Class<?> getSearchFieldClass(String searchFieldType);

    SearchFieldAdapter<?> getSearchFieldPopulator(String fieldType);

    CustomFieldRefType getCustomFieldRefType(String recordType, String customFieldType, Object customField);

    <T> T createType(String typeName) throws NetSuiteException;
}
