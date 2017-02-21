package org.talend.components.netsuite.client.model.custom;

import org.talend.components.netsuite.client.model.MetaDataProvider;

/**
 *
 */
public class DefaultCustomFieldAdapter<T> extends CustomFieldAdapter<T> {

    public DefaultCustomFieldAdapter(MetaDataProvider metaDataProvider, String type) {
        super(metaDataProvider, type);
    }

    @Override
    public boolean appliesTo(String recordTypeName, T field) {
        return false;
    }

    @Override
    public CustomFieldRefType apply(T field) {
        return null;
    }

}
