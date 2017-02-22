package org.talend.components.netsuite.client.model.custom;

/**
 *
 */
public class DefaultCustomFieldAdapter<T> extends CustomFieldAdapter<T> {

    public DefaultCustomFieldAdapter(String type) {
        super(type);
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
