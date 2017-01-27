package org.talend.components.netsuite.beaninfo;

import java.lang.reflect.Method;

/**
 *
 */
public class PropertyInfo {
    private String name;
    private Class<?> readType;
    private Class<?> writeType;
    private Method getter;
    private Method setter;

    public PropertyInfo(String name, Class<?> readType, Class<?> writeType, Method getter, Method setter) {
        this.name = name;
        this.readType = readType;
        this.writeType = writeType;
        this.getter = getter;
        this.setter = setter;
    }

    public String getName() {
        return name;
    }

    public Class<?> getReadType() {
        return readType;
    }

    public Class<?> getWriteType() {
        return writeType;
    }

    public Method getGetter() {
        return getter;
    }

    public Method getSetter() {
        return setter;
    }
}
