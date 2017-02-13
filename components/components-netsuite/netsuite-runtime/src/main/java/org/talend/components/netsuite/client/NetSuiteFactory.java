package org.talend.components.netsuite.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.beanutils.MethodUtils;
import org.talend.components.netsuite.model.EnumAccessor;
import org.talend.components.netsuite.model.Mapper;
import org.talend.components.netsuite.model.PropertyAccess;
import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeManager;

/**
 *
 */
public abstract class NetSuiteFactory {

    private transient static final Logger LOG = LoggerFactory.getLogger(NetSuiteFactory.class);

    public static PropertyAccessor<Object> getPropertyAccessor(Class<?> clazz) {
        if (PropertyAccess.class.isAssignableFrom(clazz)) {
            return OptimizedPropertyAccessor.INSTANCE;
        } else {
            return ReflectionPropertyAccessor.INSTANCE;
        }
    }

    public static EnumAccessor getEnumAccessor(Class<? extends Enum> clazz) {
        return getEnumAccessorImpl(clazz);
    }

    private static AbstractEnumAccessor getEnumAccessorImpl(Class<? extends Enum> clazz) {
        EnumAccessor accessor = null;
        Method m;
        try {
            m = clazz.getDeclaredMethod("getEnumAccessor", new Class[0]);
            if (!m.getReturnType().equals(EnumAccessor.class)) {
                throw new NoSuchMethodException();
            }
            try {
                accessor = (EnumAccessor) m.invoke(new Object[0]);
            } catch (IllegalAccessException | InvocationTargetException e) {
                LOG.error("Failed to get optimized EnumAccessor: enum class " + clazz.getName(), e);
            }
        } catch (NoSuchMethodException e) {
        }
        if (accessor != null) {
            return new OptimizedEnumAccessor(clazz, accessor);
        } else {
            return new ReflectionEnumAccessor(clazz);
        }
    }

    public static Mapper<Enum, String> getEnumToStringMapper(Class<Enum> clazz) {
        return getEnumAccessorImpl(clazz).getToStringMapper();
    }

    public static Mapper<String, Enum> getEnumFromStringMapper(Class<Enum> clazz) {
        return getEnumAccessorImpl(clazz).getFromStringMapper();
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
            TypeInfo metaData = TypeManager.forClass(target.getClass());
            PropertyInfo descriptor = metaData.getProperty(name);
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
            TypeInfo metaData = TypeManager.forClass(target.getClass());
            PropertyInfo descriptor = metaData.getProperty(name);
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
        Method getReadMethod(Class clazz, PropertyInfo descriptor) {
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
        Method getWriteMethod(Class clazz, PropertyInfo descriptor) {
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

    protected static abstract class AbstractEnumAccessor implements EnumAccessor {
        protected Class<?> enumClass;
        protected Mapper<Enum, String> toStringMapper;
        protected Mapper<String, Enum> fromStringMapper;

        protected AbstractEnumAccessor(Class<?> enumClass) {
            this.enumClass = enumClass;
            toStringMapper = new ToStringMapper();
            fromStringMapper = new FromStringMapper();
        }

        public Class<?> getEnumClass() {
            return enumClass;
        }

        public Mapper<Enum, String> getToStringMapper() {
            return toStringMapper;
        }

        public Mapper<String, Enum> getFromStringMapper() {
            return fromStringMapper;
        }

        protected class ToStringMapper implements Mapper<Enum, String> {
            @Override
            public String map(Enum input) {
                return mapToString(input);
            }
        }

        protected class FromStringMapper implements Mapper<String, Enum> {
            @Override
            public Enum map(String input) {
                return mapFromString(input);
            }
        }
    }

    public static class ReflectionEnumAccessor extends AbstractEnumAccessor {

        public ReflectionEnumAccessor(Class<?> enumClass) {
            super(enumClass);
        }

        @Override
        public String mapToString(Enum enumValue) {
            try {
                return (String) MethodUtils.invokeExactMethod(enumValue, "value", null);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getTargetException());
            } catch (NoSuchMethodException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Enum mapFromString(String value) {
            try {
                return (Enum) MethodUtils.invokeExactStaticMethod(enumClass, "fromValue", value);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getTargetException());
            } catch (NoSuchMethodException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class OptimizedEnumAccessor extends AbstractEnumAccessor {
        private EnumAccessor accessor;

        public OptimizedEnumAccessor(Class<?> enumClass, EnumAccessor accessor) {
            super(enumClass);
            this.accessor = accessor;
        }

        @Override
        public String mapToString(Enum enumValue) {
            return accessor.mapToString(enumValue);
        }

        @Override
        public Enum mapFromString(String value) {
            return accessor.mapFromString(value);
        }
    }
}
