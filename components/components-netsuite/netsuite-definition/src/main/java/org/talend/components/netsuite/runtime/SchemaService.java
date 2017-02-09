package org.talend.components.netsuite.runtime;

import java.util.List;

import org.apache.avro.Schema;
import org.talend.components.netsuite.schema.NsSchema;
import org.talend.daikon.NamedThing;

/**
 *
 */
public interface SchemaService {

    List<NamedThing> getSchemaNames();

    Schema getSchema(String typeName);

    NsSchema getSearchSchema(String typeName);

    List<String> getSearchFieldOperators();

}
