package org.talend.components.netsuite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 */
public class BeanMetaData {

    private static final ConcurrentMap<Class<?>, BeanMetaData> cache = new ConcurrentHashMap<>();

    private Map<String, PropertyMetaData> propertyMap;

    public BeanMetaData(PropertyMetaData[] properties) {
        this(Arrays.asList(properties));
    }

    public BeanMetaData(Collection<PropertyMetaData> properties) {
        propertyMap = new HashMap<>(properties.size());
        for (PropertyMetaData pmd : properties) {
            propertyMap.put(pmd.getName(), pmd);
        }
    }

    public Collection<PropertyMetaData> getProperties() {
        return propertyMap.values();
    }

    public PropertyMetaData getProperty(String name) {
        return propertyMap.get(name);
    }

    public Collection<String> getPropertyNames() {
        return propertyMap.keySet();
    }

    public static BeanMetaData forClass(Class<?> clazz) {
        BeanMetaData metaData = cache.get(clazz);
        if (metaData == null) {
            BeanMetaData newMetaData = loadForClass(clazz);
            if (cache.putIfAbsent(clazz, newMetaData) == null) {
                metaData = newMetaData;
            } else {
                metaData = cache.get(clazz);
            }
        }
        return metaData;
    }

    protected static BeanMetaData loadForClass(Class<?> clazz) {
        Method m;
        try {
            m = clazz.getDeclaredMethod("getBeanMetaData", new Class[0]);
            if (!m.getReturnType().equals(BeanMetaData.class)) {
                throw new NoSuchMethodException();
            }
            return (BeanMetaData) m.invoke(new Object[0]);
        } catch (NoSuchMethodException e) {
            try {
                Collection<PropertyMetaData> properties = new ArrayList<>();
                for (PropertyInfo info : BeanIntrospector.getInstance().getBeanProperties(clazz)) {
                    properties.add(new PropertyMetaData(info.getName(),
                            info.getReadType(), info.getWriteType(), info.getGetter(), info.getSetter()));
                }
                return new BeanMetaData(properties);
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
//            throw new IllegalArgumentException("Target class doesn't have "
//                    + "'BeanMetaData getBeanMetaData()' static method: " + clazz);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
