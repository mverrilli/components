package org.talend.components.netsuite.client.model.custom;

import java.util.Map;

import org.talend.components.netsuite.client.model.BeanUtils;
import org.talend.components.netsuite.client.model.MetaDataProvider;

import com.google.common.collect.ImmutableMap;

/**
 *
 */
public class CrmCustomFieldAdapter<T> extends CustomFieldAdapter<T> {

    private static final Map<String, String> recordTypePropertyMap = ImmutableMap.<String, String>builder()
            .put("campaign", "appliesToCampaign")
            .put("supportCase", "appliesToCase")
            .put("calendarEvent", "appliesToEvent")
            .put("issue", "appliesToIssue")
            .put("phoneCall", "appliesToPhoneCall")
            .put("projectTask", "appliesToProjectTask")
            .put("solution", "appliesToSolution")
            .put("task", "appliesToTask")
            .build();

    public CrmCustomFieldAdapter(MetaDataProvider metaDataProvider) {
        super(metaDataProvider, "crmCustomField");
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