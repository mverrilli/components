package org.talend.components.netsuite.client.model;

import org.talend.components.netsuite.client.NsRef;
import org.talend.components.netsuite.client.model.customfield.CustomFieldRefType;

/**
 *
 */
public class CustomFieldDesc extends FieldDesc {
    private NsRef ref;
    private CustomFieldRefType customFieldType;

    public CustomFieldDesc() {
    }

    public NsRef getRef() {
        return ref;
    }

    public void setRef(NsRef ref) {
        this.ref = ref;
    }

    public CustomFieldRefType getCustomFieldType() {
        return customFieldType;
    }

    public void setCustomFieldType(CustomFieldRefType customFieldType) {
        this.customFieldType = customFieldType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomFieldDesc{");
        sb.append(", name='").append(name).append('\'');
        sb.append(", valueType=").append(valueType);
        sb.append(", key=").append(key);
        sb.append(", nullable=").append(nullable);
        sb.append(", length=").append(length);
        sb.append(", internalName='").append(getInternalName()).append('\'');
        sb.append(", ref=").append(ref);
        sb.append(", customFieldType=").append(customFieldType);
        sb.append('}');
        return sb.toString();
    }
}
