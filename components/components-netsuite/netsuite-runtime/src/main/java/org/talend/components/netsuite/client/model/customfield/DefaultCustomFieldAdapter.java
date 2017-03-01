package org.talend.components.netsuite.client.model.customfield;

import org.talend.components.netsuite.client.model.BasicRecordType;

/**
 *
 */
public class DefaultCustomFieldAdapter<T> extends CustomFieldAdapter<T> {
    private boolean applies;

    public DefaultCustomFieldAdapter(BasicRecordType type, boolean applies) {
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
