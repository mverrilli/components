package org.talend.components.netsuite.model.javassist;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.beanutils.MethodUtils;
import org.talend.components.netsuite.model.PrimitiveInfo;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeIntrospector;

/**
 *
 */
public class JavassistTypeIntrospector implements TypeIntrospector {

    private static final JavassistTypeIntrospector instance = new JavassistTypeIntrospector();

    public static JavassistTypeIntrospector getInstance() {
        return instance;
    }

    public List<PropertyInfo> getProperties(String className) throws Exception {
        CtClass ctClass = ClassPool.getDefault().get(className);

        List<JavassistPropertyInfo> orderedPropInfos = JavassistTypeIntrospector.getInstance()
                .getProperties(ctClass);

        Class<?> clazz = Class.forName(ctClass.getName());

        List<PropertyInfo> result = new ArrayList<>(orderedPropInfos.size());
        for (JavassistPropertyInfo info : orderedPropInfos) {
            String name = info.getName();

            Class<?> readType;
            if (info.getReadType().isPrimitive()) {
                readType = PrimitiveInfo.getPrimitiveType(info.getReadType().getName());
            } else if (info.getReadType().isArray()) {
                if (info.getReadType().getComponentType().isPrimitive() &&
                        info.getReadType().getComponentType().getName().equals("byte")) {
                    readType = byte[].class;
                } else {
                    throw new ReflectiveOperationException("Can't determine property read type class: " +
                            info.getWriteType().getName());
                }
            } else {
                readType = Class.forName(info.getReadType().getName());
            }

            Class<?> writeType = null;
            if (info.getWriteType() != null) {
                if (info.getWriteType().isPrimitive()) {
                    writeType = PrimitiveInfo.getPrimitiveType(info.getWriteType().getName());
                } else if (info.getWriteType().isArray()) {
                    if (info.getWriteType().getComponentType().isPrimitive() &&
                            info.getWriteType().getComponentType().getName().equals("byte")) {
                        writeType = byte[].class;
                    } else {
                        throw new ReflectiveOperationException("Can't determine property write type class: " +
                                info.getWriteType().getName());
                    }
                } else {
                    writeType = Class.forName(info.getWriteType().getName());
                }
            }

            Method readMethod = MethodUtils.getAccessibleMethod(clazz, info.getReadMethod().getName(), (Class[]) null);

            Method writeMethod = info.getWriteMethod() != null ?
                    MethodUtils.getAccessibleMethod(clazz, info.getWriteMethod().getName(), writeType) : null;

            PropertyInfo propertyInfo = new PropertyInfo(name, readType, writeType, readMethod, writeMethod);
            result.add(propertyInfo);
        }

        return result;
    }

    public List<JavassistPropertyInfo> getProperties(CtClass ctClass) throws Exception {

        Set<CtMethod> methods = getMethods(ctClass);
        Collection<JavassistPropertyInfo> propertyInfos = getProperties(methods);

        Map<String, JavassistPropertyInfo> propertyInfoMap = new HashMap<>(propertyInfos.size());
        for (JavassistPropertyInfo propertyInfo : propertyInfos) {
            propertyInfoMap.put(propertyInfo.getName(), propertyInfo);
        }

        List<String> orderedPropNames = getPropertyOrder(ctClass, propertyInfoMap);
        //        System.out.println("  order: " + orderedPropNames);

        List<JavassistPropertyInfo> orderedPropInfos = new ArrayList<>(orderedPropNames.size());
        for (String propName : orderedPropNames) {
            JavassistPropertyInfo propertyInfo = propertyInfoMap.get(propName);
            orderedPropInfos.add(propertyInfo);
            propertyInfoMap.remove(propName);
        }

        if (!propertyInfoMap.isEmpty()) {
            Set<String> propNames = new HashSet<>(propertyInfoMap.keySet());
            for (String propName : propNames) {
                JavassistPropertyInfo info = propertyInfoMap.get(propName);
                if (info.getName().equals("class") &&
                        info.getReadType().getName().equals("java.lang.Class")) {
                    propertyInfoMap.remove(info.getName());
                }
            }
            if (!propertyInfoMap.isEmpty()) {
                throw new IllegalArgumentException("Unhandled properties: " +
                        ctClass + " " + propertyInfoMap.keySet());
            }
        }

        return orderedPropInfos;
    }

    protected List<String> getPropertyOrder(CtClass aClass, Map<String,
            JavassistPropertyInfo> propertyInfoMap) throws Exception {

        Set<String> propNames = new HashSet<>(propertyInfoMap.keySet());
        List<String> orderedPropNames = new ArrayList<>(propNames.size());

        XmlType xmlType = (XmlType) aClass.getAnnotation(XmlType.class);
        if (xmlType != null) {
            String[] propOrder = xmlType.propOrder();
            if (propOrder != null && propOrder.length != 0) {
                for (String propName : propOrder) {
                    if (propName.equals("")) {
                        continue;
                    }

                    String actualPropName = resolveXmlPropertyName(aClass, propertyInfoMap, propName);
                    JavassistPropertyInfo propertyInfo = propertyInfoMap.get(actualPropName);
                    if (propertyInfo == null) {
                        throw new IllegalArgumentException("Property info is null: " +
                                aClass.getName() + " " + actualPropName);
                    }

                    orderedPropNames.add(propertyInfo.getName());

                    propNames.remove(propertyInfo.getName());
                }

                if (!propNames.isEmpty()) {
                    for (String propName : propNames) {
                        JavassistPropertyInfo propertyInfo = propertyInfoMap.get(propName);
                        if (propertyInfo.getName().equals("class") &&
                                propertyInfo.getReadType().getName().equals("java.lang.Class")) {
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

    protected String resolveXmlPropertyName(CtClass aClass,
            Map<String, JavassistPropertyInfo> propertyInfoMap, String xmlPropName) throws Exception {

        String mappedPropName = xmlPropName;
        CtField field = null;
        if (field == null) {
            field = aClass.getDeclaredField(xmlPropName);
            if (field == null) {
                field = aClass.getField(xmlPropName);
            }
        }

        if (field != null) {
            XmlElement xmlElement = (XmlElement) field.getAnnotation(XmlElement.class);
            if (xmlElement != null && !xmlElement.name().equals("##default")) {
                String name = xmlElement.name();
                if (!name.equals("class")) {
                    mappedPropName = name;
                }
            }
        }
        mappedPropName = mappedPropName.replace("_", "");

        JavassistPropertyInfo propertyInfo = propertyInfoMap.get(mappedPropName);
        if (propertyInfo == null) {
            for (String name : propertyInfoMap.keySet()) {
                if (name.equalsIgnoreCase(mappedPropName)) {
                    propertyInfo = propertyInfoMap.get(name);
                    break;
                }
            }
        }

        return propertyInfo != null ? propertyInfo.getName() : null;
    }

    protected Set<JavassistPropertyInfo> getProperties(Set<CtMethod> methods) throws Exception {
        Map<String, CtMethod> getters = new HashMap<>();
        Map<String, List<CtMethod>> setters = new HashMap<>();
        if (methods.isEmpty() == false) {
            for (CtMethod methodInfo : methods) {
                String name = methodInfo.getName();
                if (isGetter(methodInfo)) {
                    String upperName = getUpperPropertyName(name);
                    getters.put(upperName, methodInfo);
                } else if (isSetter(methodInfo)) {
                    String upperName = getUpperPropertyName(name);
                    List<CtMethod> list = setters.get(upperName);
                    if (list == null) {
                        list = new ArrayList<>();
                        setters.put(upperName, list);
                    }
                    list.add(methodInfo);
                }
            }
        }

        HashSet<JavassistPropertyInfo> properties = new HashSet<>();
        if (getters.isEmpty() == false) {
            for (Map.Entry<String, CtMethod> entry : getters.entrySet()) {
                String name = entry.getKey();
                CtMethod getter = entry.getValue();
                CtMethod setter = null;
                List<CtMethod> setterList = setters.remove(name);
                if (setterList != null && setterList.size() != 0) {
                    for (int j = 0; j < setterList.size(); ++j) {
                        CtMethod thisSetter = setterList.get(j);
                        CtClass pinfo = thisSetter.getParameterTypes()[0];
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

                properties.add(new JavassistPropertyInfo(lowerName,
                        getPropertyReadType(getter), getPropertyWriteType(setter), getter, setter));
            }
        }
        if (setters.isEmpty() == false) {
            for (Map.Entry<String, List<CtMethod>> entry : setters.entrySet()) {
                String name = entry.getKey();
                List<CtMethod> setterList = entry.getValue();
                for (CtMethod setter : setterList) {
                    CtClass pinfo = setter.getParameterTypes()[0];
                    String lowerName = getLowerPropertyName(name);
                    properties.add(new JavassistPropertyInfo(lowerName, null, pinfo, null, setter));
                }
            }
        }
        return properties;
    }

    protected static boolean isGetter(CtMethod minfo) throws Exception {
        String name = minfo.getName();
        if ((name.length() > 3 && name.startsWith("get")) || (name.length() > 2 && name.startsWith("is"))) {
            // Don't load these until we verified the name since this hits the classpools for the javassist impl
            // and we want things to be as lazy as possible
            CtClass returnType = minfo.getReturnType();

            // isBoolean() is not a getter for java.lang.Boolean
            if (name.startsWith("is") && !returnType.isPrimitive())
                return false;

            // Again, try to find the length of the parameters without loading up the types
            int params = minfo.getParameterTypes().length;
            if (params == 0 && !PrimitiveInfo.VOID.getName().equals(returnType))
                return true;
        }
        return false;
    }

    protected static boolean isSetter(CtMethod minfo) throws Exception {
        String name = minfo.getName();
        if ((name.length() > 3 && name.startsWith("set"))) {
            // Don't load these until we verified the name since this hits the classpools for the javassist impl
            // and we want things to be as lazy as possible
            CtClass returnType = minfo.getReturnType();

            // Again, try to find the length of the parameters without loading up the types
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

    protected CtClass getPropertyReadType(CtMethod getter) throws Exception {
        if (getter == null)
            throw new IllegalArgumentException("Getter should not be null!");
        return getter.getReturnType();
    }

    protected CtClass getPropertyWriteType(CtMethod setter) throws Exception {
        if (setter == null)
            return null;
        return setter.getParameterTypes()[0];
    }

    protected Set<CtMethod> getMethods(CtClass classInfo) throws Exception {
        Set<CtMethod> result = new HashSet<>();

        while (classInfo != null) {
            CtMethod[] minfos = classInfo.getDeclaredMethods();
            if (minfos != null && minfos.length > 0) {
                for (int i = 0; i < minfos.length; ++i) {
                    CtMethod minfo = minfos[i];
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
