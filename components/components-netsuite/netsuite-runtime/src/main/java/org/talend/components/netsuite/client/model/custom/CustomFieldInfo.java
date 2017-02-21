package org.talend.components.netsuite.client.model.custom;

import org.talend.components.netsuite.client.common.NsCustomizationRef;
import org.talend.components.netsuite.client.model.FieldInfo;

/**
 *
 */
public class CustomFieldInfo extends FieldInfo {
    private NsCustomizationRef customizationRef;
    private CustomFieldRefType customFieldType;

    public CustomFieldInfo() {
    }

    public NsCustomizationRef getCustomizationRef() {
        return customizationRef;
    }

    public void setCustomizationRef(NsCustomizationRef customizationRef) {
        this.customizationRef = customizationRef;
    }

    public CustomFieldRefType getCustomFieldType() {
        return customFieldType;
    }

    public void setCustomFieldType(CustomFieldRefType customFieldType) {
        this.customFieldType = customFieldType;
    }
}
