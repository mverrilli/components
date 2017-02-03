package org.talend.components.netsuite.model;

/**
 *
 */
public interface Mapper<I, O> {

    O map(I input);
}
