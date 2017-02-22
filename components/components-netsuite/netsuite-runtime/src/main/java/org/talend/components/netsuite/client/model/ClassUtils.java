package org.talend.components.netsuite.client.model;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 */
public abstract class ClassUtils {

    public static void collectXmlTypes(Class<?> rootClass, Class<?> clazz, Set<Class<?>> classes) {
        if (classes.contains(clazz)) {
            return;
        }

        if (clazz != rootClass && rootClass.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
            classes.add(clazz);
        }

        XmlSeeAlso xmlSeeAlso = clazz.getAnnotation(XmlSeeAlso.class);
        if (xmlSeeAlso != null) {
            Collection<Class<?>> referencedClasses = new HashSet<>(Arrays.<Class<?>>asList(xmlSeeAlso.value()));
            for (Class<?> referencedClass : referencedClasses) {
                collectXmlTypes(rootClass, referencedClass, classes);
            }
        }
    }


}
