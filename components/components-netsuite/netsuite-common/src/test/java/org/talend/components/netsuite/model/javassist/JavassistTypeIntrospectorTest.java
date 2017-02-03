package org.talend.components.netsuite.model.javassist;

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
import org.talend.components.netsuite.model.PropertyInfo;

/**
 *
 */
public class JavassistTypeIntrospectorTest {

    @Test
    public void testSetterForGetterWithPrimitiveWrapperReturnType() throws Exception {
        List<PropertyInfo> propertyInfoSet = JavassistTypeIntrospector.getInstance()
                .getProperties(BeanA.class.getName());

        Map<String, PropertyInfo> propertyInfoMap = new HashMap<>();
        for (PropertyInfo info : propertyInfoSet) {
            propertyInfoMap.put(info.getName(), info);
        }

        assertEquals(3, propertyInfoMap.size());

        PropertyInfo info1 = propertyInfoMap.get("prop1");
        assertNotNull(info1.getReadMethodName());
        assertNotNull(info1.getWriteMethodName());

        PropertyInfo info2 = propertyInfoMap.get("prop2");
        assertNotNull(info2.getReadMethodName());
        assertNotNull(info2.getWriteMethodName());
    }

    @Test
    public void testGetterSetterForNonPrimitivePropertyType() throws Exception {
        List<PropertyInfo> propertyInfoSet = JavassistTypeIntrospector.getInstance()
                .getProperties(BeanB.class.getName());

        Map<String, PropertyInfo> propertyInfoMap = new HashMap<>();
        for (PropertyInfo info : propertyInfoSet) {
            propertyInfoMap.put(info.getName(), info);
        }

        assertEquals(2, propertyInfoMap.size());
        PropertyInfo info = propertyInfoMap.get("list");
        assertNotNull(info.getReadMethodName());
        assertNotNull(info.getWriteMethodName());
    }

    @Test
    public void testPropertyOrder() throws Exception {
        List<PropertyInfo> propertyInfoSet = JavassistTypeIntrospector.getInstance()
                .getProperties(BeanC.class.getName());

        Map<String, PropertyInfo> propertyInfoMap = new HashMap<>();
        for (PropertyInfo info : propertyInfoSet) {
            propertyInfoMap.put(info.getName(), info);
        }

        assertEquals(3, propertyInfoMap.size());
        PropertyInfo info1 = propertyInfoSet.get(0);
        assertEquals("count", info1.getName());
        PropertyInfo info2 = propertyInfoSet.get(1);
        assertEquals("balance", info2.getName());
        PropertyInfo info3 = propertyInfoSet.get(2);
        assertEquals("private", info3.getName());
    }

    @Test
    public void testNoProperties() throws Exception {
        List<PropertyInfo> propertyInfoSet = JavassistTypeIntrospector.getInstance()
                .getProperties(Request.class.getName());

        Map<String, PropertyInfo> propertyInfoMap = new HashMap<>();
        for (PropertyInfo info : propertyInfoSet) {
            propertyInfoMap.put(info.getName(), info);
        }

        assertEquals(0, propertyInfoMap.size());
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
