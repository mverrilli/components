package org.talend.components.netsuite.model.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.beanutils.MethodUtils;
import org.talend.components.netsuite.model.PrimitiveInfo;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeIntrospector;
import org.talend.components.netsuite.model.javassist.JavassistPropertyInfo;
import org.talend.components.netsuite.model.javassist.JavassistTypeIntrospector;

import javassist.ClassPool;
import javassist.CtClass;

/**
 *
 */
public class ReflectionTypeIntrospector implements TypeIntrospector {

    private static final ReflectionTypeIntrospector instance = new ReflectionTypeIntrospector();

    public static ReflectionTypeIntrospector getInstance() {
        return instance;
    }

    public List<PropertyInfo> getProperties(String className) throws Exception {
        Class clazz = Class.forName(className);

        Collection<PropertyInfo> propertyInfos = getProperties(getMethods(clazz));

        Map<String, PropertyInfo> propertyInfoMap = new HashMap<>(propertyInfos.size());
        for (PropertyInfo propertyInfo : propertyInfos) {
            propertyInfoMap.put(propertyInfo.getName(), propertyInfo);
        }

        List<String> orderedPropNames = getPropertyOrder(clazz, propertyInfoMap);

        List<PropertyInfo> orderedPropInfos = new ArrayList<>(orderedPropNames.size());
        for (String propName : orderedPropNames) {
            PropertyInfo propertyInfo = propertyInfoMap.get(propName);
            orderedPropInfos.add(propertyInfo);
            propertyInfoMap.remove(propName);
        }
        if (!propertyInfoMap.isEmpty()) {
            System.out.println("Unhandled properties: " + clazz + " " + propertyInfoMap.keySet());
        }
        return orderedPropInfos;
    }

    protected List<String> getPropertyOrder(Class<?> aClass, Map<String, PropertyInfo> propertyInfoMap) throws Exception {
        Set<String> propNames = new HashSet<>(propertyInfoMap.keySet());
        List<String> orderedPropNames = new ArrayList<>(propNames.size());

        XmlType xmlType = aClass.getAnnotation(XmlType.class);
        if (xmlType != null) {
            String[] propOrder = xmlType.propOrder();
            if (propOrder != null && propOrder.length != 0) {
                for (String propName : propOrder) {
                    if (propName.equals("")) {
                        continue;
                    }

                    String actualPropName = propName;
                    Field field = null;
                    try {
                        field = aClass.getDeclaredField(propName);
                    } catch (NoSuchFieldException e) {
                        try {
                            field = aClass.getField(propName);
                        } catch (NoSuchFieldException e2) {
                        }
                    }

                    if (field != null) {
                        XmlElement xmlElement = field.getAnnotation(XmlElement.class);
                        if (xmlElement != null && !xmlElement.name().equals("##default")) {
                            String name = xmlElement.name();
                            if (!name.equals("class")) {
                                actualPropName = name;
                            }
                        }
                    }
                    actualPropName = actualPropName.replace("_", "");

                    PropertyInfo propertyInfo = propertyInfoMap.get(actualPropName);
                    if (propertyInfo == null) {
                        for (String name : propertyInfoMap.keySet()) {
                            if (name.equalsIgnoreCase(actualPropName)) {
                                propertyInfo = propertyInfoMap.get(name);
                                break;
                            }
                        }
                        if (propertyInfo == null) {
                            throw new IllegalArgumentException("Property info is null: " + aClass + " " + actualPropName);
                        }
                    }

                    orderedPropNames.add(propertyInfo.getName());

                    propNames.remove(propertyInfo.getName());
                }

                if (!propNames.isEmpty()) {
                    for (String propName : propNames) {
                        PropertyInfo propertyInfo = propertyInfoMap.get(propName);
                        if (propertyInfo.getName().equals("class") && propertyInfo.getReadType() == Class.class) {
                            continue;
                        }
                        orderedPropNames.add(propertyInfo.getName());
                    }
                }

            } else {
                orderedPropNames.addAll(propNames);
            }
        } else {
            orderedPropNames.addAll(propNames);
        }
        return orderedPropNames;
    }

    protected Set<PropertyInfo> getProperties(Set<Method> methods) throws Exception {
        Map<String, Method> getters = new HashMap<>();
        Map<String, List<Method>> setters = new HashMap<>();
        if (methods.isEmpty() == false) {
            for (Method methodInfo : methods) {
                String name = methodInfo.getName();
                if (isGetter(methodInfo)) {
                    String upperName = getUpperPropertyName(name);
                    getters.put(upperName, methodInfo);
                } else if (isSetter(methodInfo)) {
                    String upperName = getUpperPropertyName(name);
                    List<Method> list = setters.get(upperName);
                    if (list == null) {
                        list = new ArrayList<>();
                        setters.put(upperName, list);
                    }
                    list.add(methodInfo);
                }
            }
        }

        Set<PropertyInfo> properties = new HashSet<>();
        if (getters.isEmpty() == false) {
            for (Map.Entry<String, Method> entry : getters.entrySet()) {
                String name = entry.getKey();
                Method getter = entry.getValue();
                Method setter = null;
                List<Method> setterList = setters.remove(name);
                if (setterList != null && setterList.size() != 0) {
                    for (int j = 0; j < setterList.size(); ++j) {
                        Method thisSetter = setterList.get(j);
                        Class pinfo = thisSetter.getParameterTypes()[0];
                        if (getter.getReturnType().isPrimitive() && !pinfo.isPrimitive() &&
                                PrimitiveInfo.getPrimitiveWrapperType(getter.getReturnType().getName()).getName()
                                        .equals(pinfo.getName())) {
                            setter = thisSetter;
                            break;
                        } else if (!getter.getReturnType().isPrimitive() && pinfo.isPrimitive() &&
                                PrimitiveInfo.getPrimitiveWrapperType(pinfo.getName()).getName()
                                        .equals(getter.getReturnType().getName())) {
                            setter = thisSetter;
                            break;
                        } else if (getter.getReturnType().equals(pinfo) == true) {
                            setter = thisSetter;
                            break;
                        }
                    }
                }
                String lowerName = getLowerPropertyName(name);

                properties.add(new PropertyInfo(lowerName,
                        getPropertyReadType(getter), getPropertyWriteType(setter), getter, setter));
            }
        }
        if (setters.isEmpty() == false) {
            for (Map.Entry<String, List<Method>> entry : setters.entrySet()) {
                String name = entry.getKey();
                List<Method> setterList = entry.getValue();
                for (Method setter : setterList) {
                    Class pinfo = setter.getParameterTypes()[0];
                    String lowerName = getLowerPropertyName(name);
                    properties.add(new PropertyInfo(lowerName, null, pinfo, null, setter));
                }
            }
        }
        return properties;
    }

    protected static boolean isGetter(Method minfo) throws Exception {
        String name = minfo.getName();
        if ((name.length() > 3 && name.startsWith("get")) || (name.length() > 2 && name.startsWith("is"))) {
            Class returnType = minfo.getReturnType();

            // isBoolean() is not a getter for java.lang.Boolean
            if (name.startsWith("is") && !returnType.isPrimitive())
                return false;

            int params = minfo.getParameterTypes().length;
            if (params == 0 && !PrimitiveInfo.VOID.getName().equals(returnType))
                return true;
        }
        return false;
    }

    protected static boolean isSetter(Method minfo) throws Exception {
        String name = minfo.getName();
        if ((name.length() > 3 && name.startsWith("set"))) {
            Class returnType = minfo.getReturnType();

            int params = minfo.getParameterTypes().length;

            if (params == 1 && PrimitiveInfo.VOID.getName().equals(returnType.getName()))
                return true;
        }
        return false;
    }

    protected static String getUpperPropertyName(String name) {
        int start = 3;
        if (name.startsWith("is"))
            start = 2;

        return name.substring(start);
    }

    protected static String getLowerPropertyName(String name) {
        // If the second character is upper case then we don't make
        // the first character lower case
        if (name.length() > 1) {
            if (Character.isUpperCase(name.charAt(1)))
                return name;
        }

        StringBuilder buffer = new StringBuilder(name.length());
        buffer.append(Character.toLowerCase(name.charAt(0)));
        if (name.length() > 1)
            buffer.append(name.substring(1));
        return buffer.toString();
    }

    protected Class getPropertyReadType(Method getter) throws Exception {
        if (getter == null)
            throw new IllegalArgumentException("Getter should not be null!");
        return getter.getReturnType();
    }

    protected Class getPropertyWriteType(Method setter) throws Exception {
        if (setter == null)
            return null;
        return setter.getParameterTypes()[0];
    }

    protected Set<Method> getMethods(Class classInfo) throws Exception {
        Set<Method> result = new HashSet<>();

        while (classInfo != null) {
            Method[] minfos = classInfo.getDeclaredMethods();
            if (minfos != null && minfos.length > 0) {
                for (int i = 0; i < minfos.length; ++i) {
                    Method minfo = minfos[i];
                    if (!result.contains(minfo) && Modifier.isPublic(minfo.getModifiers())
                            && Modifier.isAbstract(minfo.getModifiers()) == false
                            && Modifier.isStatic(minfo.getModifiers()) == false
                            && Modifier.isVolatile(minfo.getModifiers()) == false)
                        result.add(minfo);
                }
            }

            classInfo = classInfo.getSuperclass();
        }

        return result;
    }
}
