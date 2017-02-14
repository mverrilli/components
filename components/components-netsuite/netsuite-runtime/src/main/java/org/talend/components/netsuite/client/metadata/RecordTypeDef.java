package org.talend.components.netsuite.client.metadata;

import org.talend.components.netsuite.client.NetSuiteClientService;

import com.netsuite.webservices.platform.core.types.RecordType;

/**
 *
 */
public class RecordTypeDef {
    private String name;
    private RecordType recordType;
    private Class<?> recordClass;

    public RecordTypeDef(RecordType recordType, Class<?> recordClass) {
        this(NetSuiteClientService.toInitialUpper(recordType.value()), recordType, recordClass);
    }

    public RecordTypeDef(String name, RecordType recordType, Class<?> recordClass) {
        this.name = name;
        this.recordType = recordType;
        this.recordClass = recordClass;
    }

    public String getName() {
        return name;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public Class<?> getRecordClass() {
        return recordClass;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("RecordTypeDef{");
        sb.append("name='").append(name).append('\'');
        sb.append(", recordType=").append(recordType);
        sb.append(", recordClass=").append(recordClass);
        sb.append('}');
        return sb.toString();
    }
}
