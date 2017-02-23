package org.talend.components.netsuite.client.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class TypeDesc {
    private String typeName;
    private Class<?> typeClass;
    private List<FieldDesc> fields;
    private Map<String, FieldDesc> fieldMap;

    public TypeDesc(String typeName, Class<?> typeClass, List<FieldDesc> fields) {
        this.typeName = typeName;
        this.typeClass = typeClass;
        this.fields = fields;

        fieldMap = new HashMap<>(fields.size());
        for (FieldDesc fieldDesc : fields) {
            fieldMap.put(fieldDesc.getName(), fieldDesc);
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public Class<?> getTypeClass() {
        return typeClass;
    }

    public FieldDesc getField(String name) {
        return fieldMap.get(name);
    }

    public Map<String, FieldDesc> getFieldMap() {
        return Collections.unmodifiableMap(fieldMap);
    }

    public List<FieldDesc> getFields() {
        return Collections.unmodifiableList(fields);
    }
}
