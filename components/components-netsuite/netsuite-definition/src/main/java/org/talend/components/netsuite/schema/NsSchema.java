package org.talend.components.netsuite.schema;

import java.util.List;

/**
 *
 */
public interface NsSchema<F extends NsField> {

    String getTypeName();

    List<F> getFields();

    F getField(String name);
}
