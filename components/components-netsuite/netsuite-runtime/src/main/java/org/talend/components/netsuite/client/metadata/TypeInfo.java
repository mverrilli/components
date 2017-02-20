package org.talend.components.netsuite.client.metadata;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.components.netsuite.schema.NsSchema;

/**
 *
 */
public class TypeInfo {
    private String typeName;
    private Class<?> typeClass;
    private List<FieldInfo> fields;
    private Map<String, FieldInfo> fieldMap;

    public TypeInfo(String typeName, Class<?> typeClass, List<FieldInfo> fields) {
        this.typeName = typeName;
        this.typeClass = typeClass;
        this.fields = fields;

        fieldMap = new HashMap<>(fields.size());
        for (FieldInfo fieldInfo : fields) {
            fieldMap.put(fieldInfo.getName(), fieldInfo);
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public Class<?> getTypeClass() {
        return typeClass;
    }

    public FieldInfo getField(String name) {
        return fieldMap.get(name);
    }

    public Map<String, FieldInfo> getFieldMap() {
        return Collections.unmodifiableMap(fieldMap);
    }

    public List<FieldInfo> getFields() {
        return Collections.unmodifiableList(fields);
    }
}
