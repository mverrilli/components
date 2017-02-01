package org.talend.components.netsuite.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.MethodUtils;
import org.talend.components.netsuite.BeanMetaData;
import org.talend.components.netsuite.PropertyAccess;
import org.talend.components.netsuite.PropertyAccessor;
import org.talend.components.netsuite.PropertyMetaData;

/**
 *
 */
public abstract class NetSuiteFactory {

    private static boolean messageLoggingEnabled;

    public static boolean isMessageLoggingEnabled() {
        return messageLoggingEnabled;
    }

    public static void setMessageLoggingEnabled(boolean messageLoggingEnabled) {
        NetSuiteFactory.messageLoggingEnabled = messageLoggingEnabled;
    }

    public static NetSuiteConnection getConnection(String apiVersion) throws NetSuiteException {
        NetSuiteConnection connection;
        if (apiVersion.equals("2016.2")) {
            connection = new org.talend.components.netsuite.client.impl.v2016_2.NetSuiteConnectionImpl();
        } else {
            throw new IllegalArgumentException("Invalid api version: " + apiVersion);
        }
        connection.setMessageLoggingEnabled(messageLoggingEnabled);
        return connection;
    }

    public static NetSuiteMetaData getMetaData(String apiVersion) throws NetSuiteException {
        if (apiVersion.equals("2016.2")) {
            return org.talend.components.netsuite.client.impl.v2016_2.NetSuiteMetaDataImpl.getInstance();
        } else {
            throw new IllegalArgumentException("Invalid api version: " + apiVersion);
        }
    }

    public static PropertyAccessor<Object> getPropertyAccessor(Object target) {
        if (target instanceof PropertyAccess) {
            return OptimizedPropertyAccessor.INSTANCE;
        } else {
            return ReflectionPropertyAccessor.INSTANCE;
        }
    }

    public static Mapper<Enum, String> getEnumToStringMapper(Class<Enum> clazz) {
        return ReflectionEnumToStringMapper.INSTANCE;
    }

    public static Mapper<String, Enum> getEnumFromStringMapper(Class<Enum> clazz) {
        return ReflectionEnumFromStringMapper.INSTANCE;
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

    public static class OptimizedPropertyAccessor implements PropertyAccessor<Object> {

        public static final OptimizedPropertyAccessor INSTANCE = new OptimizedPropertyAccessor();

        @Override
        public Object get(Object target, String name) {
            return ((PropertyAccess) target).get(name);
        }

        @Override
        public void set(Object target, String name, Object value) {
            ((PropertyAccess) target).set(name, value);
        }
    }

    public static class ReflectionEnumToStringMapper implements Mapper<Enum, String> {

        public static final ReflectionEnumToStringMapper INSTANCE = new ReflectionEnumToStringMapper();

        @Override
        public String map(Enum input) {
            try {
                return (String) MethodUtils.invokeExactMethod(input, "value", null);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getTargetException());
            } catch (NoSuchMethodException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class ReflectionEnumFromStringMapper implements Mapper<String, Enum> {

        public static final ReflectionEnumFromStringMapper INSTANCE = new ReflectionEnumFromStringMapper();

        @Override
        public Enum map(String input) {
            try {
                return (Enum) MethodUtils.invokeExactStaticMethod(input.getClass(), "fromValue", input);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getTargetException());
            } catch (NoSuchMethodException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
