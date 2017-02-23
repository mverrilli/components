package org.talend.components.netsuite.schema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class NsSchema {
    protected String name;
    protected List<NsField> fields;
    protected Map<String, NsField> fieldMap;

    public NsSchema(String name, List<NsField> fields) {
        this.name = name;
        this.fields = fields;

        fieldMap = new HashMap<>(fields.size());
        for (NsField field : fields) {
            fieldMap.put(field.getName(), field);
        }
    }

    public String getTypeName() {
        return name;
    }

    public List<NsField> getFields() {
        return fields;
    }

    public NsField getField(String name) {
        return fieldMap.get(name);
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("NsSchema{");
        sb.append("name='").append(name).append('\'');
        sb.append(", fields=").append(fields);
        sb.append(", typeName='").append(getTypeName()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

