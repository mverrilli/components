package org.talend.components.netsuite.client;

import java.util.Collection;

import org.talend.components.netsuite.client.schema.NsTypeDef;
import org.talend.components.netsuite.client.schema.NsSearchDef;
import org.talend.components.netsuite.client.schema.NsSearchFieldOperatorTypeDef;

/**
 *
 */
public interface NetSuiteMetaData {

    Collection<String> getTransactionTypes();

    Collection<String> getItemTypes();

    NsTypeDef getTypeDef(String typeName);

    NsTypeDef getTypeDef(Class<?> clazz);

    Collection<String> getRecordTypes();

    NsSearchDef getSearchDef(String typeName);

    Class<?> getSearchFieldClass(String searchFieldType);

    Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName);

    Collection<NsSearchFieldOperatorTypeDef.QualifiedName> getSearchOperatorNames();

    <T> T createListOrRecordRef() throws NetSuiteException;

    <T> T createRecordRef() throws NetSuiteException;

    <T> T createType(String typeName) throws NetSuiteException;
}
