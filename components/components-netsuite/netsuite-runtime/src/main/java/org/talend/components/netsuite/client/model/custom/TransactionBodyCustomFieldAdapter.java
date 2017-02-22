package org.talend.components.netsuite.client.model.custom;

import java.util.Map;

import org.talend.components.netsuite.client.model.BeanUtils;

import com.google.common.collect.ImmutableMap;

/**
 *
 */
public class TransactionBodyCustomFieldAdapter<T> extends CustomFieldAdapter<T> {

    private static final Map<String, String> recordTypePropertyMap = ImmutableMap.<String, String>builder()
            .put("assemblyBuild", "bodyAssemblyBuild")
            .put("purchaseOrder", "bodyPurchase")
            .put("journalEntry", "bodyJournal")
            .put("expenseReport", "bodyExpenseReport")
            .put("opportunity", "bodyOpportunity")
            .put("itemReceipt", "bodyItemReceipt")
            .put("itemFulfillment", "bodyItemFulfillment")
            .put("inventoryAdjustment", "bodyInventoryAdjustment")
            .put("customerPayment", "bodyCustomerPayment")
            .put("vendorPayment", "bodyVendorPayment")
            .build();

    public TransactionBodyCustomFieldAdapter() {
        super("transactionBodyCustomField");
    }

    @Override
    public boolean appliesTo(String recordType, T field) {
        String propertyName = recordTypePropertyMap.get(recordType);
        Boolean applies = propertyName != null ? (Boolean) BeanUtils.getProperty(field, propertyName) : Boolean.FALSE;
        return applies == null ? false : applies.booleanValue();
    }

    @Override
    public CustomFieldRefType apply(T field) {
        return getFieldType(field);
    }

}
