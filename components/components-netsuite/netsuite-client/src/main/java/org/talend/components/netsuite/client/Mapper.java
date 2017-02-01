package org.talend.components.netsuite.client;

/**
 *
 */
public interface Mapper<I, O> {

    O map(I input);
}
