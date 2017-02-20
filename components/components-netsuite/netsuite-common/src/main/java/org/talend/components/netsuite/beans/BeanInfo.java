package org.talend.components.netsuite.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class BeanInfo {

    private List<PropertyInfo> properties;
    private Map<String, PropertyInfo> propertyMap;

    public BeanInfo(PropertyInfo[] properties) {
        this(Arrays.asList(properties));
    }

    public BeanInfo(List<PropertyInfo> properties) {
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
