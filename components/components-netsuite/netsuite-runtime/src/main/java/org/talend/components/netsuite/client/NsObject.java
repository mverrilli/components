package org.talend.components.netsuite.client;

import org.apache.commons.beanutils.expression.DefaultResolver;
import org.apache.commons.beanutils.expression.Resolver;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeManager;

/**
 *
 */
public class NsObject<T> {
    private static final Resolver resolver = new DefaultResolver();

    private T target;

    public NsObject(T target) {
        if (target instanceof NsObject) {
            throw new IllegalArgumentException("Target object already is NsObject: " + target);
        }
        this.target = target;
    }

    public T getTarget() {
        return target;
    }

    public static <T> NsObject<T> wrap(T target) {
        if (target instanceof NsObject) {
            return (NsObject<T>) target;
        }
        return new NsObject<>(target);
    }

    public static Object unwrap(Object target) {
        return unwrap(target, Object.class);
    }

    public static <T> T unwrap(Object target, Class<T> clazz) {
        if (target instanceof NsObject) {
            return (T) ((NsObject) target).getTarget();
        }
        return clazz.cast(target);
    }

    public Object get(String name) {
        return getPropertyAccessor(target).get(target, name);
    }

    public void set(String name, Object value) {
        if (value instanceof NsObject) {
            value = ((NsObject) value).getTarget();
        }
        getPropertyAccessor(target).set(target, name, value);
    }

    public static void set(Object target, String expr, Object value) {
        try {
            Object current = target;
            if (resolver.hasNested(expr)) {
                String currExpr = expr;
                while (resolver.hasNested(currExpr)) {
                    String next = resolver.next(currExpr);
                    Object obj = getPropertyAccessor(current).get(current, next);
                    if (obj == null) {
                        PropertyInfo pd = TypeManager.getPropertyInfo(current, next);
                        if (!pd.getWriteType().isPrimitive()) {
                            obj = pd.getWriteType().newInstance();
                            getPropertyAccessor(current).set(current, next, obj);
                        }
                    }
                    current = obj;
                    currExpr = resolver.remove(currExpr);
                }
                getPropertyAccessor(current).set(current, currExpr, value);
            } else {
                getPropertyAccessor(current).set(current, expr, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object get(Object target, String expr) {
        try {
            Object current = target;
            if (resolver.hasNested(expr)) {
                String currExpr = expr;
                while (resolver.hasNested(currExpr) && current != null) {
                    String next = resolver.next(currExpr);
                    current = getPropertyAccessor(current).get(current, next);
                    currExpr = resolver.remove(currExpr);
                }
                return current != null ? getPropertyAccessor(current).get(current, currExpr) : null;
            } else {
                return getPropertyAccessor(current).get(current, expr);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Property property(final Object target, final String expr) {
        return new Property() {
            @Override public void set(Object value) {
                NsObject.set(target, expr, value);
            }
            @Override public Object get() {
                return NsObject.get(target, expr);
            }
        };
    }

    protected static <T> PropertyAccessor<T> getPropertyAccessor(T target) {
        return (PropertyAccessor<T>) NetSuiteFactory.getPropertyAccessor(target.getClass());
    }

    public interface Property {

        void set(Object value);

        Object get();
    }
}
