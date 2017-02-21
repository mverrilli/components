package org.talend.components.netsuite.client.model.custom;

import java.util.Map;

import org.talend.components.netsuite.client.model.BeanUtils;
import org.talend.components.netsuite.client.model.MetaDataProvider;

import com.google.common.collect.ImmutableMap;

/**
 *
 */
public class ItemCustomFieldAdapter<T> extends CustomFieldAdapter<T> {

    private static final Map<String, String> recordTypePropertyMap = ImmutableMap.<String, String>builder()
            .put("entityGroup", "appliesToGroup")
            .put("inventoryItem", "appliesToInventory")
            .put("assemblyItem", "appliesToItemAssembly")
            .put("kitItem", "appliesToKit")
            .put("nonInventoryPurchaseItem", "appliesToNonInventory")
            .put("nonInventoryResaleItem", "appliesToNonInventory")
            .put("nonInventorySaleItem", "appliesToNonInventory")
            .put("otherChargePurchaseItem", "appliesToOtherCharge")
            .put("otherChargeResaleItem", "appliesToOtherCharge")
            .put("otherChargeSaleItem", "appliesToOtherCharge")
            .put("pricingGroup", "appliesToPriceList")
            .put("servicePurchaseItem", "appliesToService")
            .put("serviceResaleItem", "appliesToService")
            .put("serviceSaleItem", "appliesToService")
            .build();

    public ItemCustomFieldAdapter(MetaDataProvider metaDataProvider) {
        super(metaDataProvider, "itemCustomField");
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
