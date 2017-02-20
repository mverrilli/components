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
    private CtMethod readMethod;
    private CtMethod writeMethod;

    public JavassistPropertyInfo(String name, CtClass readType, CtClass writeType, CtMethod readMethod, CtMethod writeMethod) {
        this.name = name;
        this.readType = readType;
        this.writeType = writeType;
        this.readMethod = readMethod;
        this.writeMethod = writeMethod;
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

    public CtMethod getReadMethod() {
        return readMethod;
    }

    public CtMethod getWriteMethod() {
        return writeMethod;
    }
}
