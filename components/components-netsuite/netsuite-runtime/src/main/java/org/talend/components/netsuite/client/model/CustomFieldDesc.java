package org.talend.components.netsuite.client.model;

import org.talend.components.netsuite.client.NsCustomizationRef;
import org.talend.components.netsuite.client.model.customfield.CustomFieldRefType;

/**
 *
 */
public class CustomFieldDesc extends FieldDesc {
    private NsCustomizationRef customizationRef;
    private CustomFieldRefType customFieldType;

    public CustomFieldDesc() {
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
