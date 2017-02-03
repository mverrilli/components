package org.talend.components.netsuite.codegen;

import de.icongmbh.oss.maven.plugin.javassist.ClassTransformer;
import javassist.CtClass;
import javassist.Modifier;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

import java.util.Properties;

/**
 *
 */
public class NetSuiteClassTransformer extends ClassTransformer {

    private NetSuiteClassEnhancer transformer = new NetSuiteClassEnhancer();
    private String outputDir;

    @Override
    protected boolean shouldTransform(CtClass candidateClass) throws Exception {
        if (!candidateClass.isAnnotation() && !candidateClass.isInterface() &&
                !Modifier.isAbstract(candidateClass.getModifiers()) &&
                !Modifier.isPrivate(candidateClass.getModifiers())) {
            if ((candidateClass.hasAnnotation(XmlType.class) || candidateClass.hasAnnotation(XmlEnum.class)) &&
                    candidateClass.getPackageName().startsWith("com.netsuite.webservices.")) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     */
    @Override
    protected void applyTransformations(CtClass classToTransform) throws Exception {
        transformer.transform(classToTransform, outputDir);
    }

    /**
     *
     */
    @Override
    public void configure(final Properties properties) {
        if (null == properties) {
            return;
        }
        outputDir = properties.getProperty("output.dir");
        System.out.println("Output directory: " + outputDir);
    }

}
