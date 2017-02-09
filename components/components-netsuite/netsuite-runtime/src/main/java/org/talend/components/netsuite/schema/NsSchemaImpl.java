package org.talend.components.netsuite.schema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class NsSchemaImpl<F extends NsField> implements NsSchema<F> {
    protected String name;
    protected List<F> fields;
    protected Map<String, F> fieldMap;

    public NsSchemaImpl(String name, List<F> fields) {
        this.name = name;
        this.fields = fields;
        this.fields = fields;

        fieldMap = new HashMap<>(fields.size());
        for (F field : fields) {
            fieldMap.put(field.getName(), field);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<F> getFields() {
        return fields;
    }

    @Override
    public F getField(String name) {
        return fieldMap.get(name);
    }
}

