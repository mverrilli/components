package org.talend.components.netsuite.client.model;

import java.util.Collection;

import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.custom.CustomFieldRefType;
import org.talend.components.netsuite.client.model.search.SearchFieldAdapter;
import org.talend.components.netsuite.client.model.search.SearchFieldOperatorType;
import org.talend.components.netsuite.client.model.search.SearchRecordTypeEx;

/**
 *
 */
public interface MetaData {

    TypeInfo getTypeInfo(String typeName);

    TypeInfo getTypeInfo(Class<?> clazz);

    Collection<RecordTypeEx> getRecordTypes();

    boolean isRecord(String typeName);

    RecordTypeEx getRecordType(String recordType);

    SearchRecordTypeEx getSearchRecordType(RecordTypeEx recordType);

    SearchRecordTypeEx getSearchRecordType(String searchRecordType);

    Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName);

    Collection<SearchFieldOperatorType.QualifiedName> getSearchOperatorNames();

    Class<?> getSearchFieldClass(String searchFieldType);

    SearchFieldAdapter<?> getSearchFieldPopulator(String fieldType);

    CustomFieldRefType getCustomFieldRefType(String recordType, String customFieldType, Object customField);

    <T> T createType(String typeName) throws NetSuiteException;
}
