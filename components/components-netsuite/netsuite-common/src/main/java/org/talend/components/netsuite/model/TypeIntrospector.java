package org.talend.components.netsuite.model;

import java.util.List;

/**
 *
 */
public interface TypeIntrospector {

    List<PropertyInfo> getProperties(String className) throws Exception;
}
