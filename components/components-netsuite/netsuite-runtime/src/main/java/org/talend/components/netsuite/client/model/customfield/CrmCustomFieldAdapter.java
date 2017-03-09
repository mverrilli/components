package org.talend.components.netsuite.client.model.customfield;

import java.util.Map;

import org.talend.components.netsuite.client.model.BasicRecordType;
import org.talend.components.netsuite.client.model.beans.Beans;

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

    public CrmCustomFieldAdapter() {
        super(BasicRecordType.CRM_CUSTOM_FIELD);
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
