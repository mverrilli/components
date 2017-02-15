package org.talend.components.netsuite.client.metadata;

import org.talend.components.netsuite.client.NetSuiteFactory;

/**
 *
 */
public class RecordTypeDef {
    private String name;
    private String recordType;
    private Class<?> recordClass;

    public RecordTypeDef(String recordType, Class<?> recordClass) {
        this.name = NetSuiteFactory.toInitialUpper(recordType);
        this.recordType = recordType;
        this.recordClass = recordClass;
    }

    public String getName() {
        return name;
    }

    public String getRecordType() {
        return recordType;
    }

    public Class<?> getRecordClass() {
        return recordClass;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("RecordTypeDef{");
        sb.append(", name=").append(name);
        sb.append(", recordType=").append(recordType);
        sb.append(", recordClass=").append(recordClass);
        sb.append('}');
        return sb.toString();
    }
}
