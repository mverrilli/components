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

    private String name;

    private Class<?> entityClass;

    private List<NsFieldDef> fields;

    private Map<String, NsFieldDef> fieldMap;

    private TypeInfo typeInfo;

    public NsTypeDef(String name, Class<?> entityClass, List<NsFieldDef> fields, TypeInfo typeInfo) {
        this.name = name;
        this.entityClass = entityClass;
        this.fields = fields;

        fieldMap = new HashMap<>(fields.size());
        for (NsFieldDef fieldInfo : fields) {
            fieldMap.put(fieldInfo.getName(), fieldInfo);
        }

        this.typeInfo = typeInfo;
    }

    public String getName() {
        return name;
    }

    public Class<?> getEntityClass() {
        return entityClass;
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
