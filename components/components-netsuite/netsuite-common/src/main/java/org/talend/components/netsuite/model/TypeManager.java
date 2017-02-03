package org.talend.components.netsuite.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.talend.components.netsuite.model.javassist.JavassistTypeIntrospector;

/**
 *
 */
public abstract class TypeManager {

    private static final ConcurrentMap<Class<?>, TypeInfo> cache = new ConcurrentHashMap<>();

    private static final TypeIntrospector TYPE_INTROSPECTOR = new JavassistTypeIntrospector();

    public static TypeInfo forClass(Class<?> clazz) {
        TypeInfo metaData = cache.get(clazz);
        if (metaData == null) {
            TypeInfo newMetaData = loadForClass(clazz);
            if (cache.putIfAbsent(clazz, newMetaData) == null) {
                metaData = newMetaData;
            } else {
                metaData = cache.get(clazz);
            }
        }
        return metaData;
    }

    protected static TypeInfo loadForClass(Class<?> clazz) {
        Method m;
        try {
            m = clazz.getDeclaredMethod("getBeanMetaData", new Class[0]);
            if (!m.getReturnType().equals(TypeInfo.class)) {
                throw new NoSuchMethodException();
            }
            return (TypeInfo) m.invoke(new Object[0]);
        } catch (NoSuchMethodException e) {
            try {
                List<PropertyInfo> properties = getTypeInstrospector().getProperties(clazz.getName());
                return new TypeInfo(properties);
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
//            throw new IllegalArgumentException("Target class doesn't have "
//                    + "'TypeInfo getBeanMetaData()' static method: " + clazz);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static TypeIntrospector getTypeInstrospector() {
        return TYPE_INTROSPECTOR;
    }
}
