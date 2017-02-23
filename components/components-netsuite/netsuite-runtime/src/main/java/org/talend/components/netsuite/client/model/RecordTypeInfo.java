package org.talend.components.netsuite.client.model;

/**
 *
 */
public class RecordTypeInfo {
    private String name;
    private RecordTypeDesc recordType;

    public RecordTypeInfo(RecordTypeDesc recordType) {
        this.name = recordType.getTypeName();
        this.recordType = recordType;
    }

    public RecordTypeInfo(String name, RecordTypeDesc recordType) {
        this.name = name;
        this.recordType = recordType;
    }

    public String getName() {
        return name;
    }

    public RecordTypeDesc getRecordType() {
        return recordType;
    }
}
