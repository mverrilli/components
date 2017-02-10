package org.talend.components.netsuite.client.metadata;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.schema.NsSchema;

/**
 *
 */
public class NsTypeDef implements NsSchema<NsFieldDef> {
    private String typeName;
    private Class<?> typeClass;
    private List<NsFieldDef> fields;
    private Map<String, NsFieldDef> fieldMap;
    private TypeInfo typeInfo;

    public NsTypeDef(String typeName, Class<?> typeClass, List<NsFieldDef> fields, TypeInfo typeInfo) {
        this.typeName = typeName;
        this.typeClass = typeClass;
        this.fields = fields;

        fieldMap = new HashMap<>(fields.size());
        for (NsFieldDef fieldInfo : fields) {
            fieldMap.put(fieldInfo.getName(), fieldInfo);
        }

        this.typeInfo = typeInfo;
    }

    public String getTypeName() {
        return typeName;
    }

    public Class<?> getTypeClass() {
        return typeClass;
    }

    public NsFieldDef getField(String name) {
        return fieldMap.get(name);
    }

    public Map<String, NsFieldDef> getFieldMap() {
        return Collections.unmodifiableMap(fieldMap);
    }

    public List<NsFieldDef> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public TypeInfo getTypeInfo() {
        return typeInfo;
    }
}
