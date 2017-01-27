package org.talend.components.netsuite.client;

import org.talend.components.netsuite.PropertyAccess;
import org.talend.components.netsuite.PropertyAccessor;

/**
 *
 */
public class NsObject implements PropertyAccess {
    private Object target;
    private PropertyAccessor<Object> propertyAccessor;

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
        this.propertyAccessor = NetSuiteMetaData.getPropertyAccessor(target);
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
        return propertyAccessor.get(target, name);
    }

    @Override
    public void set(String name, Object value) {
        if (value instanceof NsObject) {
            value = ((NsObject) value).getTarget();
        }
        propertyAccessor.set(target, name, value);
    }
}
