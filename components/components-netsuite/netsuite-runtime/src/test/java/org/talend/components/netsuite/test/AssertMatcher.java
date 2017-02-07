package org.talend.components.netsuite.test;

import junit.framework.AssertionFailedError;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 *
 */
public abstract class AssertMatcher<T> extends BaseMatcher<T> {

    @Override
    public boolean matches(Object o) {
        try {
            doAssert((T) o);
            return true;
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    protected abstract void doAssert(T target) throws Exception;

    @Override
    public void describeTo(Description description) {

    }
}
