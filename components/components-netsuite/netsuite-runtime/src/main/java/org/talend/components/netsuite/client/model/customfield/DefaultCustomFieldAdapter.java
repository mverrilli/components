package org.talend.components.netsuite.client.model.customfield;

/**
 *
 */
public class DefaultCustomFieldAdapter<T> extends CustomFieldAdapter<T> {
    private boolean applies;

    public DefaultCustomFieldAdapter(String type, boolean applies) {
        super(type);

        this.applies = applies;
    }

    @Override
    public boolean appliesTo(String recordTypeName, T field) {
        return applies;
    }

    @Override
    public CustomFieldRefType apply(T field) {
        return applies ? getFieldType(field) : null;
    }

}
