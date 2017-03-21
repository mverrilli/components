// ============================================================================
//
// Copyright (C) 2006-2017 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.components.netsuite.client;

import static org.talend.components.netsuite.client.model.beans.Beans.getSimpleProperty;
import static org.talend.components.netsuite.client.model.beans.Beans.toInitialUpper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.netsuite.client.model.BasicRecordType;
import org.talend.components.netsuite.client.model.CustomFieldDesc;
import org.talend.components.netsuite.client.model.CustomRecordTypeInfo;
import org.talend.components.netsuite.client.model.RecordTypeDesc;
import org.talend.components.netsuite.client.model.RecordTypeInfo;
import org.talend.components.netsuite.client.model.RefType;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.client.model.beans.BeanInfo;
import org.talend.components.netsuite.client.model.beans.Beans;
import org.talend.components.netsuite.client.model.customfield.CustomFieldRefType;
import org.talend.daikon.java8.Function;

/**
 * Base implementation of <code>CustomMetaDataSource</code> which retrieves custom meta data from NetSuite and
 * caches retrieved data.
 */
public abstract class DefaultCustomMetaDataSource<PortT> implements CustomMetaDataSource {
    protected transient final Logger logger = LoggerFactory.getLogger(getClass());

    protected NetSuiteClientService<PortT> clientService;

    public DefaultCustomMetaDataSource(NetSuiteClientService<PortT> clientService) {
        this.clientService = clientService;
    }

    protected Map<String, CustomRecordTypeInfo> customRecordTypeMap = new HashMap<>();

    protected boolean customRecordTypesLoaded = false;

    protected Map<BasicRecordType, List<Object>> customFieldMap = new HashMap<>();

    protected Map<RecordTypeDesc, Map<String, CustomFieldDesc>> recordCustomFieldMap = new HashMap<>();

    protected boolean customFieldsLoaded = false;

    protected Map<String, Map<String, CustomFieldDesc>> customRecordCustomFieldMap = new HashMap<>();

    protected static final List<BasicRecordType> fieldCustomizationTypes = Collections.unmodifiableList(
            Arrays.asList(BasicRecordType.CRM_CUSTOM_FIELD, BasicRecordType.ENTITY_CUSTOM_FIELD, BasicRecordType.ITEM_CUSTOM_FIELD,
                    BasicRecordType.OTHER_CUSTOM_FIELD, BasicRecordType.TRANSACTION_BODY_CUSTOM_FIELD, BasicRecordType.TRANSACTION_COLUMN_CUSTOM_FIELD));

    @Override
    public Collection<CustomRecordTypeInfo> getCustomRecordTypes() {
        return clientService.executeWithLock(new Function<Void, Collection<CustomRecordTypeInfo>>() {

            @Override public Collection<CustomRecordTypeInfo> apply(Void param) {
                retrieveCustomRecordTypes();
                return new ArrayList(customRecordTypeMap.values());
            }
        }, null);
    }

    @Override
    public Map<String, CustomFieldDesc> getCustomFields(RecordTypeInfo recordTypeInfo) {
        return clientService.executeWithLock(new Function<RecordTypeInfo, Map<String, CustomFieldDesc>>() {
            @Override public Map<String, CustomFieldDesc> apply(RecordTypeInfo recordTypeInfo) {
                return getCustomFieldsImpl(recordTypeInfo);
            }
        }, recordTypeInfo);
    }

    @Override
    public CustomRecordTypeInfo getCustomRecordType(String typeName) {
        return clientService.executeWithLock(new Function<String, CustomRecordTypeInfo>() {
            @Override public CustomRecordTypeInfo apply(String typeName) {
                retrieveCustomRecordTypes();
                return customRecordTypeMap.get(typeName);
            }
        }, typeName);
    }

    protected Map<String, CustomFieldDesc> getCustomFieldsImpl(RecordTypeInfo recordTypeInfo) throws NetSuiteException {
        RecordTypeDesc recordType = recordTypeInfo.getRecordType();

        Map<String, CustomFieldDesc> fieldDescMap;

        if (recordTypeInfo instanceof CustomRecordTypeInfo) {
            retrieveCustomRecordCustomFields((CustomRecordTypeInfo) recordTypeInfo);
            fieldDescMap = customRecordCustomFieldMap.get(recordTypeInfo.getName());
        } else {
            retrieveCustomFields();

            fieldDescMap = recordCustomFieldMap.get(recordType.getType());
            if (fieldDescMap == null) {
                fieldDescMap = new HashMap<>();

                for (BasicRecordType customizationType : fieldCustomizationTypes) {
                    List<Object> customFieldList = customFieldMap.get(customizationType);

                    Map<String, CustomFieldDesc> customFieldDescMap = createCustomFieldDescMap(recordType, customizationType,
                            customFieldList);
                    fieldDescMap.putAll(customFieldDescMap);
                }

                recordCustomFieldMap.put(recordType, fieldDescMap);
            }
        }

        return fieldDescMap;
    }

    protected <T> Map<String, CustomFieldDesc> createCustomFieldDescMap(RecordTypeDesc recordType, BasicRecordType customizationType, List<T> customFieldList) throws NetSuiteException {
        Map<String, CustomFieldDesc> customFieldDescMap = new HashMap<>();

        for (T customField : customFieldList) {
            CustomFieldRefType customFieldRefType = clientService.getBasicMetaData()
                    .getCustomFieldRefType(recordType.getType(), customizationType, customField);

            if (customFieldRefType != null) {
                CustomFieldDesc customFieldInfo = new CustomFieldDesc();

                String internalId = (String) getSimpleProperty(customField, "internalId");
                String scriptId = (String) getSimpleProperty(customField, "scriptId");
                String label = (String) getSimpleProperty(customField, "label");

                NsRef customizationRef = new NsRef();
                customizationRef.setRefType(RefType.CUSTOMIZATION_REF);
                customizationRef.setType(customizationType.getType());
                customizationRef.setName(label);
                customizationRef.setInternalId(internalId);
                customizationRef.setScriptId(scriptId);

                customFieldInfo.setRef(customizationRef);
                customFieldInfo.setName(customizationRef.getScriptId());
                customFieldInfo.setCustomFieldType(customFieldRefType);

                TypeDesc typeDesc = clientService.getBasicMetaData().getTypeInfo(customFieldRefType.getTypeName());
                BeanInfo beanInfo = Beans.getBeanInfo(typeDesc.getTypeClass());
                Class<?> valueType = beanInfo.getProperty("value").getWriteType();
                customFieldInfo.setValueType(valueType);
                customFieldInfo.setNullable(true);

                customFieldDescMap.put(customFieldInfo.getName(), customFieldInfo);
            }
        }

        return customFieldDescMap;
    }

    protected void retrieveCustomRecordTypes() throws NetSuiteException {
        if (customRecordTypesLoaded) {
            return;
        }

        List<NsRef> customTypes = new ArrayList<>();

        List<NsRef> customRecordTypes = retrieveCustomizationIds(BasicRecordType.CUSTOM_RECORD_TYPE);
        customTypes.addAll(customRecordTypes);

        List<NsRef> customTransactionTypes = retrieveCustomizationIds(BasicRecordType.CUSTOM_TRANSACTION_TYPE);
        customTypes.addAll(customTransactionTypes);

        for (NsRef customizationRef : customTypes) {
            String recordType = customizationRef.getType();
            RecordTypeDesc recordTypeDesc = null;
            BasicRecordType basicRecordType = BasicRecordType.getByType(recordType);
            if (basicRecordType != null) {
                recordTypeDesc = clientService.getBasicMetaData()
                        .getRecordType(toInitialUpper(basicRecordType.getSearchType()));
            }

            CustomRecordTypeInfo customRecordTypeInfo = new CustomRecordTypeInfo(customizationRef.getScriptId(),
                    recordTypeDesc, customizationRef);
            customRecordTypeMap.put(customRecordTypeInfo.getName(), customRecordTypeInfo);
        }
    }

    protected void retrieveCustomFields() throws NetSuiteException {
        if (customFieldsLoaded) {
            return;
        }

        Map<BasicRecordType, List<NsRef>> fieldCustomizationRefs = new HashMap<>(32);
        for (BasicRecordType customizationType : fieldCustomizationTypes) {
            List<NsRef> customizationRefs = retrieveCustomizationIds(customizationType);
            fieldCustomizationRefs.put(customizationType, customizationRefs);
        }

        for (BasicRecordType customizationType : fieldCustomizationTypes) {
            List<NsRef> customizationRefs = fieldCustomizationRefs.get(customizationType);
            List<Object> fieldCustomizationList = retrieveCustomizations(customizationRefs);
            customFieldMap.put(customizationType, fieldCustomizationList);
        }
    }

    protected void retrieveCustomRecordCustomFields(CustomRecordTypeInfo recordTypeInfo) throws NetSuiteException {
        Map<String, CustomFieldDesc> recordCustomFieldMap = customRecordCustomFieldMap.get(recordTypeInfo.getName());
        if (recordCustomFieldMap != null) {
            return;
        }
        recordCustomFieldMap = retrieveCustomRecordCustomFields(recordTypeInfo.getRecordType(), recordTypeInfo.getRef());
        customRecordCustomFieldMap.put(recordTypeInfo.getName(), recordCustomFieldMap);
    }

    /**
     * Retrieve customization IDs for given customization type.
     *
     * @param type customization type
     * @return list of customization refs
     * @throws NetSuiteException if an error occurs during retrieving
     */
    protected abstract List<NsRef> retrieveCustomizationIds(final BasicRecordType type) throws NetSuiteException;

    /**
     * Retrieve customization for given customization refs.
     *
     * @param nsCustomizationRefs customization refs which to retrieve customization data for
     * @param <T> type of customization record
     * @return list of customization records
     * @throws NetSuiteException if an error occurs during retrieving
     */
    protected abstract <T> List<T> retrieveCustomizations(final List<NsRef> nsCustomizationRefs) throws NetSuiteException;

    /**
     * Retrieve custom fields for given custom record type.
     *
     * @param recordType custom record type descriptor
     * @param nsCustomizationRef customization ref for the custom record type
     * @return custom field map which contains <code>(custom field name, custom field descriptor)</code> entries
     * @throws NetSuiteException if an error occurs during retrieving
     */
    protected abstract Map<String, CustomFieldDesc> retrieveCustomRecordCustomFields(final RecordTypeDesc recordType, final NsRef nsCustomizationRef) throws NetSuiteException;

}
