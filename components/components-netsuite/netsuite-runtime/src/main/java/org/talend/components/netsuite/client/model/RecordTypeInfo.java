package org.talend.components.netsuite.client.model;

/**
 *
 */
public class RecordTypeInfo {
    private String name;
    private RecordTypeEx recordType;

    public RecordTypeInfo(RecordTypeEx recordType) {
        this.name = recordType.getTypeName();
        this.recordType = recordType;
    }

    public RecordTypeInfo(String name, RecordTypeEx recordType) {
        this.name = name;
        this.recordType = recordType;
    }

    public String getName() {
        return name;
    }

    public RecordTypeEx getRecordType() {
        return recordType;
    }
}
