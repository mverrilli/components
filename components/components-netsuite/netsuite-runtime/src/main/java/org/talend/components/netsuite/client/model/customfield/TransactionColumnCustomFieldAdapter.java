package org.talend.components.netsuite.client.model.customfield;

import java.util.Map;

import org.talend.components.netsuite.client.model.BasicRecordType;
import org.talend.components.netsuite.client.model.beans.Beans;

import com.google.common.collect.ImmutableMap;

/**
 *
 */
public class TransactionColumnCustomFieldAdapter<T> extends CustomFieldAdapter<T> {

    private static final Map<String, String> recordTypePropertyMap = ImmutableMap.<String, String>builder()
            .put("expenseCategory", "colExpense")
            .put("expenseReport", "colExpenseReport")
            .put("assemblyBuild", "colBuild")
            .put("itemReceipt", "colItemReceipt")
            .put("itemFulfillment", "colItemFulfillment")
            .put("purchaseOrder", "colPurchase")
            .put("journalEntry", "colJournal")
            .put("salesOrder", "colSale")
            .put("opportunity", "colOpportunity")
            .put("kitItem", "colKitItem")
            .put("timeBill", "colTime")
            .build();

    public TransactionColumnCustomFieldAdapter() {
        super(BasicRecordType.TRANSACTION_COLUMN_CUSTOM_FIELD);
    }

    @Override
    public boolean appliesTo(String recordType, T field) {
        String propertyName = recordTypePropertyMap.get(recordType);
        Boolean applies = propertyName != null ? (Boolean) Beans.getSimpleProperty(field, propertyName) : Boolean.FALSE;
        return applies == null ? false : applies.booleanValue();
    }

    @Override
    public CustomFieldRefType apply(T field) {
        return getFieldType(field);
    }

}
