package org.talend.components.netsuite.beans;

/**
 *
 */
public interface Mapper<I, O> {

    O map(I input);
}
