package org.talend.components.netsuite.client.model;

import org.talend.components.netsuite.client.common.NsCustomizationRef;

/**
 *
 */
public class CustomRecordTypeInfo extends RecordTypeInfo {
    private NsCustomizationRef customizationRef;

    public CustomRecordTypeInfo(String name, RecordTypeDesc recordType, NsCustomizationRef customizationRef) {
        super(name, recordType);
        this.customizationRef = customizationRef;
    }

    public String getDisplayName() {
        return customizationRef.getName();
    }

    public NsCustomizationRef getCustomizationRef() {
        return customizationRef;
    }

    public void setCustomizationRef(NsCustomizationRef customizationRef) {
        this.customizationRef = customizationRef;
    }
}
