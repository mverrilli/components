package org.talend.components.netsuite.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.MethodUtils;
import org.talend.components.netsuite.BeanMetaData;
import org.talend.components.netsuite.PropertyAccessor;
import org.talend.components.netsuite.PropertyMetaData;

/**
 *
 */
public class NsObject implements PropertyAccessor {

    /** An empty class array */
    private static final Class[] EMPTY_CLASS_PARAMETERS = new Class[0];
    /** An empty object array */
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    private Object target;

    private static boolean debugEnabled = false;

    public static void setDebugEnabled(boolean enabled) {
        debugEnabled = enabled;
    }

    public static boolean isDebugEnabled() {
        return debugEnabled;
    }

    public NsObject(Object target) {
        if (target instanceof NsObject) {
            throw new IllegalArgumentException("Target object already is NsObject: " + target);
        }
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public static NsObject asNsObject(Object target) {
        if (target instanceof NsObject) {
            return (NsObject) target;
        }
//        if (debugEnabled) {
//            if (target instanceof PropertyAccessor) {
//                System.out.println("Using native property access: " + target.getClass());
//            } else {
//                System.out.println("Using reflection based property access: " + target.getClass());
//            }
//        }
        return new NsObject(target);
    }

    @Override
    public Object get(String name) {
        if (target instanceof PropertyAccessor) {
            return ((PropertyAccessor) target).get(name);
        }
        return get(target, name);
    }

    @Override
    public void set(String name, Object value) {
        if (target instanceof PropertyAccessor) {
            ((PropertyAccessor) target).set(name, value);
        }
        if (value instanceof NsObject) {
            value = ((NsObject) value).getTarget();
        }
        set(target, name, value);
    }

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
