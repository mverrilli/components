package org.talend.components.netsuite.util;

/**
 *
 */
public interface Mapper<I, O> {

    O map(I input);
}
