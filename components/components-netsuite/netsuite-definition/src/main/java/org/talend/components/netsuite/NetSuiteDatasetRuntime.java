package org.talend.components.netsuite;

import java.util.List;

import org.apache.avro.Schema;
import org.talend.components.netsuite.schema.SearchInfo;
import org.talend.daikon.NamedThing;

/**
 *
 */
public interface NetSuiteDatasetRuntime {

    List<NamedThing> getRecordTypes();

    List<NamedThing> getSearchableTypes();

    Schema getSchema(String typeName);

    SearchInfo getSearchInfo(String typeName);

    Schema getSchemaForUpdate(String typeName);

    Schema getSchemaForDelete(String typeName);

    List<String> getSearchFieldOperators();

}
