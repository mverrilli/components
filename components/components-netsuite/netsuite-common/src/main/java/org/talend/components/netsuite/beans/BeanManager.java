package org.talend.components.netsuite.beans;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 */
public abstract class BeanManager {

    private static final ConcurrentMap<Class<?>, BeanInfo> cache = new ConcurrentHashMap<>();

    private static final BeanIntrospector INSTANCE = new ReflectionBeanIntrospector();

    public static PropertyInfo getPropertyInfo(Object target, String name) {
        BeanInfo metaData = getBeanInfo(target.getClass());
        return metaData != null ? metaData.getProperty(name) : null;
    }

    public static BeanInfo getBeanInfo(Class<?> clazz) {
        BeanInfo metaData = cache.get(clazz);
        if (metaData == null) {
            BeanInfo newMetaData = loadBeanInfoForClass(clazz);
            if (cache.putIfAbsent(clazz, newMetaData) == null) {
                metaData = newMetaData;
            } else {
                metaData = cache.get(clazz);
            }
        }
        return metaData;
    }

    protected static BeanInfo loadBeanInfoForClass(Class<?> clazz) {
        try {
            List<PropertyInfo> properties = getTypeInstrospector().getProperties(clazz.getName());
            return new BeanInfo(properties);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static BeanIntrospector getTypeInstrospector() {
        return INSTANCE;
    }
}
