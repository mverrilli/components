package org.talend.components.netsuite.codegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

import org.talend.components.netsuite.beaninfo.PrimitiveInfo;

/**
 *
 */
public class JavassistTypeIntrospector {

    private static final JavassistTypeIntrospector instance = new JavassistTypeIntrospector();

    public static JavassistTypeIntrospector getInstance() {
        return instance;
    }

    public Set<JavassistPropertyInfo> getBeanProperties(CtClass ctClass) throws Exception {
        return getBeanProperties(getMethods(ctClass));
    }

    protected Set<JavassistPropertyInfo> getBeanProperties(Set<CtMethod> methods) throws Exception {
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
