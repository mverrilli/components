package org.talend.components.netsuite;

import java.util.List;

import org.apache.avro.Schema;
import org.talend.daikon.NamedThing;

/**
 *
 */
public interface NetSuiteMetaDataService {

    List<NamedThing> getSchemaNames();

    Schema getSchema(String module);

    List<String> getSearchFieldNames(String typeName);

    List<String> getSearchFieldOperators();

}
