package org.talend.components.netsuite.client.metadata;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.components.netsuite.schema.NsSchema;

/**
 *
 */
public class TypeDef implements NsSchema<FieldDef> {
    private String typeName;
    private Class<?> typeClass;
    private List<FieldDef> fields;
    private Map<String, FieldDef> fieldMap;

    public TypeDef(String typeName, Class<?> typeClass, List<FieldDef> fields) {
        this.typeName = typeName;
        this.typeClass = typeClass;
        this.fields = fields;

        fieldMap = new HashMap<>(fields.size());
        for (FieldDef fieldInfo : fields) {
            fieldMap.put(fieldInfo.getName(), fieldInfo);
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public Class<?> getTypeClass() {
        return typeClass;
    }

    public FieldDef getField(String name) {
        return fieldMap.get(name);
    }

    public Map<String, FieldDef> getFieldMap() {
        return Collections.unmodifiableMap(fieldMap);
    }

    public List<FieldDef> getFields() {
        return Collections.unmodifiableList(fields);
    }
}
