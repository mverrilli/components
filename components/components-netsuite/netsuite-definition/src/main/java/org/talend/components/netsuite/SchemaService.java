package org.talend.components.netsuite;

import java.util.List;

import org.apache.avro.Schema;
import org.talend.components.netsuite.schema.NsSchema;
import org.talend.daikon.NamedThing;

/**
 *
 */
public interface SchemaService {

    List<NamedThing> getRecordTypes();

    List<NamedThing> getSearchableTypes();

    Schema getSchema(String typeName);

    NsSchema getSchemaForSearch(String typeName);

    NsSchema getSchemaForUpdate(String typeName);

    NsSchema getSchemaForDelete(String typeName);

    List<String> getSearchFieldOperators();

}
