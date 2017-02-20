package org.talend.components.netsuite.beans;

import java.util.List;

/**
 *
 */
public interface BeanIntrospector {

    List<PropertyInfo> getProperties(String className) throws Exception;
}
