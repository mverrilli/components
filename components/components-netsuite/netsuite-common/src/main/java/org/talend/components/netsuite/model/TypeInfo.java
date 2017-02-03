package org.talend.components.netsuite.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class TypeInfo {

    private List<PropertyInfo> properties;
    private Map<String, PropertyInfo> propertyMap;

    public TypeInfo(PropertyInfo[] properties) {
        this(Arrays.asList(properties));
    }

    public TypeInfo(List<PropertyInfo> properties) {
        this.properties = new ArrayList<>(properties);
        propertyMap = new HashMap<>(properties.size());
        for (PropertyInfo pmd : properties) {
            propertyMap.put(pmd.getName(), pmd);
        }
    }

    public List<PropertyInfo> getProperties() {
        return Collections.unmodifiableList(properties);
    }

    public Map<String, PropertyInfo> getPropertyMap() {
        return Collections.unmodifiableMap(propertyMap);
    }

    public PropertyInfo getProperty(String name) {
        return propertyMap.get(name);
    }

}
