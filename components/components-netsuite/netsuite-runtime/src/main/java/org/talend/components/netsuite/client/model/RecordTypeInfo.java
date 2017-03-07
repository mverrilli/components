package org.talend.components.netsuite.client.model;

/**
 *
 */
public class RecordTypeInfo {
    protected String name;
    protected RecordTypeDesc recordType;

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

    public String getDisplayName() {
        return recordType.getTypeName();
    }

    public RecordTypeDesc getRecordType() {
        return recordType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RecordTypeInfo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", recordType=").append(recordType);
        sb.append(", displayName='").append(getDisplayName()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
