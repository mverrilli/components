package org.talend.components.netsuite.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;
import org.talend.components.netsuite.BeanMetaData;
import org.talend.components.netsuite.PropertyAccess;
import org.talend.components.netsuite.PropertyAccessor;
import org.talend.components.netsuite.PropertyMetaData;

/**
 *
 */
public abstract class NetSuiteMetaData {

    protected Map<String, Entity> typeMap;
    protected Map<String, Class<?>[]> searchMap;
    protected Map<String, Class<?>> searchFieldMap;
    protected Map<String, Class<?>> searchFieldOperatorMap;

    protected void initMetaData(
            Class<?>[][] searchTable,
            Class<?>[] searchFieldTable,
            Class<?>[] searchFieldOperatorTable) {

        typeMap = new HashMap<>(searchTable.length);

        searchMap = new HashMap<>(searchTable.length);

        for (Class<?>[] entry : searchTable) {
            Class<?> typeClass = entry[0];
            String typeName = typeClass.getSimpleName();

            registerType(typeClass, typeName);

            Map<String, Field> fields = new HashMap<>();
            BeanMetaData beanMetaData = BeanMetaData.forClass(typeClass);
            Collection<PropertyMetaData> descriptors = beanMetaData.getProperties();
            for (PropertyMetaData descriptor : descriptors) {
                String fieldName = descriptor.getName();
                Class fieldValueType = descriptor.getReadType();
                if ((fieldName.equals("class") && fieldValueType == Class.class) ||
                        (fieldName.equals("nullFieldList") && fieldValueType.getSimpleName().equals("NullField"))) {
                    continue;
                }
                Field field = new Field(fieldName, fieldValueType, false, false);
                fields.put(field.getName(), field);
            }

            Entity entity = new Entity(typeName, typeClass, Collections.unmodifiableMap(fields));
            typeMap.put(typeName, entity);

            Class<?> searchClass = entry[1];
            Class<?> searchBasicClass = entry[2];
            Class<?> searchAdvancedClass = entry[3];
            if (searchMap.containsKey(typeName)) {
                throw new IllegalArgumentException(
                        "Search entry already registered: " + typeName + ", search classes to register are "
                                + searchClass + ", " + searchBasicClass + ", " + searchAdvancedClass);
            }
            searchMap.put(typeName, new Class<?>[]{
                    searchClass,
                    searchBasicClass,
                    searchAdvancedClass
            });
        }

        searchFieldMap = new HashMap<>(searchFieldTable.length);
        for (Class<?> entry : searchFieldTable) {
            searchFieldMap.put(entry.getSimpleName(), entry);
        }

        searchFieldOperatorMap = new HashMap<>(searchFieldOperatorTable.length);
        for (Class<?> entry : searchFieldOperatorTable) {
            searchFieldOperatorMap.put(entry.getSimpleName(), entry);
        }
    }

    protected void registerType(Class<?> typeClass, String typeName) {
        String typeNameToRegister = typeName != null ? typeName : typeClass.getSimpleName();
        if (typeMap.containsKey(typeNameToRegister)) {
            throw new IllegalArgumentException("Type already registered: " + typeNameToRegister +
                    ", class to register is " + typeClass +
                    ", registered class is " + typeMap.get(typeNameToRegister));
        }
    }

    public abstract Collection<String> getTransactionTypes();

    public abstract Collection<String> getItemTypes();

    public Class<?> getEntityClass(String typeName) {
        Entity entity = typeMap.get(typeName);
        return entity != null ? entity.getEntityType() : null;
    }

    public Entity getEntity(String typeName) {
        Entity entity = typeMap.get(typeName);
        return entity;
    }

    public Class<?> getSearchClass(String typeName) {
        return getSearchClass(typeName, 0);
    }

    public Class<?> getSearchBasicClass(String typeName) {
        return getSearchClass(typeName, 1);
    }

    public Class<?> getSearchAdvancedClass(String typeName) {
        return getSearchClass(typeName, 2);
    }

    private Class<?> getSearchClass(String typeName, int key) {
        Class<?>[] entry = searchMap.get(typeName);
        return entry[key];
    }

    public Class<?> getSearchFieldClass(String searchFieldTypeName) {
        return searchFieldMap.get(searchFieldTypeName);
    }

    public Class<?> getSearchFieldOperatorClass(String searchFieldOperatorTypeName) {
        return searchFieldOperatorMap.get(searchFieldOperatorTypeName);
    }

    public abstract Class<?> getListOrRecordRefClass();

    public static String toInitialUpper(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    public static String toInitialLower(String value) {
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }

    public static String toNetSuiteType(String value) {
        return "_" + toInitialLower(value);
    }

    public static PropertyAccessor<Object> getPropertyAccessor(Object target) {
        if (target instanceof PropertyAccess) {
            return EnhancedPropertyAccessor.INSTANCE;
        } else {
            return ReflectionPropertyAccessor.INSTANCE;
        }
    }

    public static class Entity {
        private String name;
        private Class<?> entityType;
        private Map<String, NetSuiteMetaData.Field> fields;

        public Entity(String name, Class<?> entityType, Map<String, NetSuiteMetaData.Field> fields) {
            this.name = name;
            this.entityType = entityType;
            this.fields = fields;
        }

        public String getName() {
            return name;
        }

        public Class<?> getEntityType() {
            return entityType;
        }

        public NetSuiteMetaData.Field getField(String name) {
            return fields.get(name);
        }

        public Map<String, NetSuiteMetaData.Field> getFields() {
            return fields;
        }
    }

    public static class Field {
        private String name;
        private Class valueType;
        private boolean key;
        private boolean nullable;

        public Field(String name, Class valueType, boolean key, boolean nullable) {
            this.name = name;
            this.valueType = valueType;
            this.key = key;
            this.nullable = nullable;
        }

        public String getName() {
            return name;
        }

        public Class getValueType() {
            return valueType;
        }

        public int getLength() {
            return 0;
        }

        public boolean isKey() {
            return key;
        }

        public boolean isNullable() {
            return nullable;
        }
    }

    public static class ReflectionPropertyAccessor implements PropertyAccessor<Object> {

        public static final ReflectionPropertyAccessor INSTANCE = new ReflectionPropertyAccessor();

        /** An empty class array */
        private static final Class[] EMPTY_CLASS_PARAMETERS = new Class[0];
        /** An empty object array */
        private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

        public Object get(Object target, String name) {
            if (name == null) {
                throw new IllegalArgumentException("No name specified for bean class '" +
                        target.getClass() + "'");
            }

            // Retrieve the property getter method for the specified property
            BeanMetaData metaData = BeanMetaData.forClass(target.getClass());
            PropertyMetaData descriptor = metaData.getProperty(name);
            if (descriptor == null) {
                throw new IllegalArgumentException("Unknown property '" +
                        name + "' on class '" + target.getClass() + "'");
            }
            Method readMethod = getReadMethod(target.getClass(), descriptor);
            if (readMethod == null) {
                throw new IllegalArgumentException("Property '" + name +
                        "' has no getter method in class '" + target.getClass() + "'");
            }

            // Call the property getter and return the value
            try {
                Object value = invokeMethod(readMethod, target, EMPTY_OBJECT_ARRAY);
                return (value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        public void set(Object target, String name, Object value) {
            if (name == null) {
                throw new IllegalArgumentException("No name specified for bean class '" +
                        target.getClass() + "'");
            }

            // Retrieve the property setter method for the specified property
            BeanMetaData metaData = BeanMetaData.forClass(target.getClass());
            PropertyMetaData descriptor = metaData.getProperty(name);
            if (descriptor == null) {
                throw new IllegalArgumentException("Unknown property '" +
                        name + "' on class '" + target.getClass() + "'" );
            }
            Method writeMethod = getWriteMethod(target.getClass(), descriptor);
            if (writeMethod == null) {
                throw new IllegalArgumentException("Property '" + name +
                        "' has no setter method in class '" + target.getClass() + "'");
            }

            // Call the property setter method
            Object[] values = new Object[1];
            values[0] = value;

            try {
                invokeMethod(writeMethod, target, values);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        /** This just catches and wraps IllegalArgumentException. */
        private Object invokeMethod(
                Method method,
                Object bean,
                Object[] values)
                throws
                IllegalAccessException,
                InvocationTargetException {

            try {
                return method.invoke(bean, values);
            } catch (IllegalArgumentException cause) {
                if(bean == null) {
                    throw new IllegalArgumentException("No bean specified " +
                            "- this should have been checked before reaching this method");
                }
                String valueString = "";
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        if (i>0) {
                            valueString += ", " ;
                        }
                        valueString += (values[i]).getClass().getName();
                    }
                }
                String expectedString = "";
                Class[] parTypes = method.getParameterTypes();
                if (parTypes != null) {
                    for (int i = 0; i < parTypes.length; i++) {
                        if (i > 0) {
                            expectedString += ", ";
                        }
                        expectedString += parTypes[i].getName();
                    }
                }
                IllegalArgumentException e = new IllegalArgumentException(
                        "Cannot invoke " + method.getDeclaringClass().getName() + "."
                                + method.getName() + " on bean class '" + bean.getClass() +
                                "' - " + cause.getMessage()
                                + " - had objects of type \"" + valueString
                                + "\" but expected signature \""
                                +   expectedString + "\""
                );
                throw e;
            }
        }

        /**
         * <p>Return an accessible property getter method for this property,
         * if there is one; otherwise return <code>null</code>.</p>
         *
         * @param clazz The class of the read method will be invoked on
         * @param descriptor Property descriptor to return a getter for
         * @return The read method
         */
        Method getReadMethod(Class clazz, PropertyMetaData descriptor) {
            return (MethodUtils.getAccessibleMethod(clazz, descriptor.getReadMethodName(), EMPTY_CLASS_PARAMETERS));
        }

        /**
         * <p>Return an accessible property setter method for this property,
         * if there is one; otherwise return <code>null</code>.</p>
         *
         * @param clazz The class of the read method will be invoked on
         * @param descriptor Property descriptor to return a setter for
         * @return The write method
         */
        Method getWriteMethod(Class clazz, PropertyMetaData descriptor) {
            return (MethodUtils.getAccessibleMethod(clazz, descriptor.getWriteMethodName(),
                    new Class[]{descriptor.getWriteType()}));
        }
    }

    public static class EnhancedPropertyAccessor implements PropertyAccessor<Object> {

        public static final EnhancedPropertyAccessor INSTANCE = new EnhancedPropertyAccessor();

        @Override
        public Object get(Object target, String name) {
            return ((PropertyAccess) target).get(name);
        }

        @Override
        public void set(Object target, String name, Object value) {
            ((PropertyAccess) target).set(name, value);
        }
    }

}
