package org.talend.components.netsuite.client.model.custom;

import java.util.Map;

import org.talend.components.netsuite.client.model.BeanUtils;

import com.google.common.collect.ImmutableMap;

/**
 *
 */
public class EntityCustomFieldAdapter<T> extends CustomFieldAdapter<T> {

    private static final Map<String, String> recordTypePropertyMap = ImmutableMap.<String, String>builder()
            .put("contact", "appliesToContact")
            .put("customer", "appliesToCustomer")
            .put("employee", "appliesToEmployee")
            .put("entityGroup", "appliesToGroup")
            .put("otherNameCategory", "appliesToOtherName")
            .put("partner", "appliesToPartner")
            .put("pricingGroup", "appliesToPriceList")
            .put("projectTask", "appliesToProject")
            .put("message", "appliesToStatement")
            .put("note", "appliesToStatement")
            .put("vendor", "appliesToVendor")
            .put("siteCategory", "appliesToWebSite")
            .build();

    public EntityCustomFieldAdapter() {
        super("entityCustomField");
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
