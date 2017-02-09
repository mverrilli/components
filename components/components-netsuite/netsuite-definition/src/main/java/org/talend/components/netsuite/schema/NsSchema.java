package org.talend.components.netsuite.schema;

import java.util.List;

/**
 *
 */
public interface NsSchema<F extends NsField> {

    String getName();

    List<F> getFields();

    F getField(String name);
}
