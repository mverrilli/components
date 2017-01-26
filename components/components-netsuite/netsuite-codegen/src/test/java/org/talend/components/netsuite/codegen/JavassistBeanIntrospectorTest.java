package org.talend.components.netsuite.codegen;

import javassist.ClassPool;
import javassist.CtClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class JavassistBeanIntrospectorTest {

    @Test
    public void testSetterForGetterWithPrimitiveWrapperReturnType() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.getCtClass(BeanA.class.getName());
        Set<JavassistPropertyInfo> propertyInfoSet = JavassistBeanIntrospector.getInstance().getBeanProperties(ctClass);

        Map<String, JavassistPropertyInfo> propertyInfoMap = new HashMap<>();
        for (JavassistPropertyInfo info : propertyInfoSet) {
            propertyInfoMap.put(info.getName(), info);
        }

        assertEquals(3, propertyInfoMap.size());

        JavassistPropertyInfo info1 = propertyInfoMap.get("prop1");
        assertNotNull(info1.getGetter());
        assertNotNull(info1.getSetter());

        JavassistPropertyInfo info2 = propertyInfoMap.get("prop2");
        assertNotNull(info2.getGetter());
        assertNotNull(info2.getSetter());
    }

    @Test
    public void testGetterSetterForNonPrimitivePropertyType() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.getCtClass(BeanB.class.getName());
        Set<JavassistPropertyInfo> propertyInfoSet = JavassistBeanIntrospector.getInstance().getBeanProperties(ctClass);

        Map<String, JavassistPropertyInfo> propertyInfoMap = new HashMap<>();
        for (JavassistPropertyInfo info : propertyInfoSet) {
            propertyInfoMap.put(info.getName(), info);
        }

        assertEquals(2, propertyInfoMap.size());
        JavassistPropertyInfo info = propertyInfoMap.get("list");
        assertNotNull(info.getGetter());
        assertNotNull(info.getSetter());
    }

    public static class BeanA {
        protected Boolean prop1;
        protected boolean prop2;

        public boolean getProp1() {
            return prop1 == null ? true : prop1.booleanValue();
        }

        public void setProp1(Boolean value) {
            this.prop1 = value;
        }

        public Boolean getProp2() {
            return prop2;
        }

        public void setProp2(boolean prop2) {
            this.prop2 = prop2;
        }
    }

    public static class BeanB {
        protected List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
