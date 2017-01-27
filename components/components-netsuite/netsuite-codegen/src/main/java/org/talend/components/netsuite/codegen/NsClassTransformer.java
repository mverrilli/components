package org.talend.components.netsuite.codegen;

import de.icongmbh.oss.maven.plugin.javassist.ClassTransformer;
import javassist.CtClass;
import javassist.Modifier;

import javax.xml.bind.annotation.XmlType;

import java.util.Properties;

/**
 *
 */
public class NsClassTransformer extends ClassTransformer {

    private NsBeanClassEnhancer transformer = new NsBeanClassEnhancer();

    @Override
    protected boolean shouldTransform(CtClass candidateClass) throws Exception {
        if (!candidateClass.isEnum() && !candidateClass.isAnnotation() && !candidateClass.isInterface() &&
                !Modifier.isAbstract(candidateClass.getModifiers()) &&
                !Modifier.isPrivate(candidateClass.getModifiers()) &&
                candidateClass.hasAnnotation(XmlType.class) &&
                candidateClass.getPackageName().startsWith("com.netsuite.webservices.")) {
            // System.out.println("Candidate: " + candidateClass.getName());
            return true;
        }
        return false;
    }

    /**
     * Hack the toString() method.
     */
    @Override
    protected void applyTransformations(CtClass classToTransform) throws Exception {
        transformer.transform(classToTransform);
    }

    /**
     *
     */
    @Override
    public void configure(final Properties properties) {
        if (null == properties) {
            return;
        }
    }

}
