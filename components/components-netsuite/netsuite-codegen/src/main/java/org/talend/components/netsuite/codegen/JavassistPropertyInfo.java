package org.talend.components.netsuite.codegen;

import javassist.CtClass;
import javassist.CtMethod;

/**
 *
 */
public class JavassistPropertyInfo {
    private String name;
    private CtClass readType;
    private CtClass writeType;
    private CtMethod getter;
    private CtMethod setter;

    public JavassistPropertyInfo(String name, CtClass readType, CtClass writeType, CtMethod getter, CtMethod setter) {
        this.name = name;
        this.readType = readType;
        this.writeType = writeType;
        this.getter = getter;
        this.setter = setter;
    }

    public String getName() {
        return name;
    }

    public CtClass getReadType() {
        return readType;
    }

    public CtClass getWriteType() {
        return writeType;
    }

    public CtMethod getGetter() {
        return getter;
    }

    public CtMethod getSetter() {
        return setter;
    }
}
