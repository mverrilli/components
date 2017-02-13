package org.talend.components.netsuite.client.schema;

/**
 *
 */
public class NsSearchDef {
    private String recordTypeName;
    private Class<?> recordClass;
    private Class<?> searchClass;
    private Class<?> searchBasicClass;
    private Class<?> searchAdvancedClass;

    public NsSearchDef(String recordTypeName, Class<?> recordClass, Class<?> searchClass, Class<?> searchBasicClass,
            Class<?> searchAdvancedClass) {
        this.recordTypeName = recordTypeName;
        this.recordClass = recordClass;
        this.searchClass = searchClass;
        this.searchBasicClass = searchBasicClass;
        this.searchAdvancedClass = searchAdvancedClass;
    }

    public NsSearchDef(Class<?> recordClass, Class<?> searchClass, Class<?> searchBasicClass, Class<?> searchAdvancedClass) {
        this.recordClass = recordClass;
        this.recordTypeName = recordClass.getSimpleName();
        this.searchClass = searchClass;
        this.searchBasicClass = searchBasicClass;
        this.searchAdvancedClass = searchAdvancedClass;
    }

    public String getRecordTypeName() {
        return recordTypeName;
    }

    public Class<?> getRecordClass() {
        return recordClass;
    }

    public Class<?> getSearchClass() {
        return searchClass;
    }

    public Class<?> getSearchBasicClass() {
        return searchBasicClass;
    }

    public Class<?> getSearchAdvancedClass() {
        return searchAdvancedClass;
    }

    public boolean isItemSearch() {
        return recordTypeName.equals("ItemSearch");
    }

    public boolean isTransactionSearch() {
        return recordTypeName.equals("TransactionSearch");
    }
}
