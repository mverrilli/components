package org.talend.components.netsuite.schema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class SearchInfo {
    protected String typeName;
    protected List<SearchFieldInfo> fields;
    protected Map<String, SearchFieldInfo> fieldMap;

    public SearchInfo(String typeName, List<SearchFieldInfo> fields) {
        this.typeName = typeName;
        this.fields = fields;

        fieldMap = new HashMap<>(fields.size());
        for (SearchFieldInfo field : fields) {
            fieldMap.put(field.getName(), field);
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public List<SearchFieldInfo> getFields() {
        return fields;
    }

    public SearchFieldInfo getField(String name) {
        return fieldMap.get(name);
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("SearchInfo{");
        sb.append("typeName='").append(typeName).append('\'');
        sb.append(", fields=").append(fields);
        sb.append('}');
        return sb.toString();
    }
}

