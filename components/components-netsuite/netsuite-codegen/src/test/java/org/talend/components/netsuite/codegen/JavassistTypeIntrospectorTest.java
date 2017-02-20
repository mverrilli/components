package org.talend.components.netsuite.codegen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.junit.Test;

/**
 *
 */
public class JavassistTypeIntrospectorTest {

    @Test
    public void testSetterForGetterWithPrimitiveWrapperReturnType() throws Exception {
        List<JavassistPropertyInfo> propertyInfoSet = JavassistTypeIntrospector.getInstance()
                .getProperties(BeanA.class.getName());

        Map<String, JavassistPropertyInfo> propertyInfoMap = new HashMap<>();
        for (JavassistPropertyInfo info : propertyInfoSet) {
            propertyInfoMap.put(info.getName(), info);
        }

        assertEquals(3, propertyInfoMap.size());

        JavassistPropertyInfo info1 = propertyInfoMap.get("prop1");
        assertNotNull(info1.getReadMethod());
        assertNotNull(info1.getWriteMethod());

        JavassistPropertyInfo info2 = propertyInfoMap.get("prop2");
        assertNotNull(info2.getReadMethod());
        assertNotNull(info2.getWriteMethod());
    }

    @Test
    public void testGetterSetterForNonPrimitivePropertyType() throws Exception {
        List<JavassistPropertyInfo> propertyInfoSet = JavassistTypeIntrospector.getInstance()
                .getProperties(BeanB.class.getName());

        Map<String, JavassistPropertyInfo> propertyInfoMap = new HashMap<>();
        for (JavassistPropertyInfo info : propertyInfoSet) {
            propertyInfoMap.put(info.getName(), info);
        }

        assertEquals(2, propertyInfoMap.size());
        JavassistPropertyInfo info = propertyInfoMap.get("list");
        assertNotNull(info.getReadMethod());
        assertNotNull(info.getWriteMethod());
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

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "BeanC", propOrder = {
            "count", "balance", "_private"
    })
    public static class BeanC {
        protected Long count;
        protected Double balance;
        @XmlElement(name = "private")
        protected Boolean _private;

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public Boolean getPrivate() {
            return _private;
        }

        public void setPrivate(Boolean _private) {
            this._private = _private;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "Request")
    public static class Request {

    }
}
