
package com.netsuite.webservices.v2016_2.lists.accounting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.lists.accounting.types.LocationAutoAssignmentRegionSetting;
import com.netsuite.webservices.v2016_2.lists.accounting.types.LocationGeolocationMethod;
import com.netsuite.webservices.v2016_2.lists.accounting.types.LocationTimeZone;
import com.netsuite.webservices.v2016_2.lists.accounting.types.LocationType;
import com.netsuite.webservices.v2016_2.platform.common.Address;
import com.netsuite.webservices.v2016_2.platform.core.CustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.Record;
import com.netsuite.webservices.v2016_2.platform.core.RecordRef;
import com.netsuite.webservices.v2016_2.platform.core.RecordRefList;


/**
 * <p>Java class for Location complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Location"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}Record"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="parent" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="includeChildren" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="subsidiaryList" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRefList" minOccurs="0"/&gt;
 *         &lt;element name="isInactive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="tranPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mainAddress" type="{urn:common_2016_2.platform.webservices.netsuite.com}Address" minOccurs="0"/&gt;
 *         &lt;element name="returnAddress" type="{urn:common_2016_2.platform.webservices.netsuite.com}Address" minOccurs="0"/&gt;
 *         &lt;element name="locationType" type="{urn:types.accounting_2016_2.lists.webservices.netsuite.com}LocationType" minOccurs="0"/&gt;
 *         &lt;element name="timeZone" type="{urn:types.accounting_2016_2.lists.webservices.netsuite.com}LocationTimeZone" minOccurs="0"/&gt;
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="logo" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="useBins" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="makeInventoryAvailable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="makeInventoryAvailableStore" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="geolocationMethod" type="{urn:types.accounting_2016_2.lists.webservices.netsuite.com}LocationGeolocationMethod" minOccurs="0"/&gt;
 *         &lt;element name="autoAssignmentRegionSetting" type="{urn:types.accounting_2016_2.lists.webservices.netsuite.com}LocationAutoAssignmentRegionSetting" minOccurs="0"/&gt;
 *         &lt;element name="bufferStock" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="dailyShippingCapacity" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="totalShippingCapacity" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="includeLocationRegionsList" type="{urn:accounting_2016_2.lists.webservices.netsuite.com}LocationRegionsList" minOccurs="0"/&gt;
 *         &lt;element name="excludeLocationRegionsList" type="{urn:accounting_2016_2.lists.webservices.netsuite.com}LocationRegionsList" minOccurs="0"/&gt;
 *         &lt;element name="classTranslationList" type="{urn:accounting_2016_2.lists.webservices.netsuite.com}ClassTranslationList" minOccurs="0"/&gt;
 *         &lt;element name="customFieldList" type="{urn:core_2016_2.platform.webservices.netsuite.com}CustomFieldList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="internalId" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="externalId" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Location", propOrder = {
    "name",
    "parent",
    "includeChildren",
    "subsidiaryList",
    "isInactive",
    "tranPrefix",
    "mainAddress",
    "returnAddress",
    "locationType",
    "timeZone",
    "latitude",
    "longitude",
    "logo",
    "useBins",
    "makeInventoryAvailable",
    "makeInventoryAvailableStore",
    "geolocationMethod",
    "autoAssignmentRegionSetting",
    "bufferStock",
    "dailyShippingCapacity",
    "totalShippingCapacity",
    "includeLocationRegionsList",
    "excludeLocationRegionsList",
    "classTranslationList",
    "customFieldList"
})
public class Location
    extends Record
{

    protected String name;
    protected RecordRef parent;
    protected Boolean includeChildren;
    protected RecordRefList subsidiaryList;
    protected Boolean isInactive;
    protected String tranPrefix;
    protected Address mainAddress;
    protected Address returnAddress;
    @XmlSchemaType(name = "string")
    protected LocationType locationType;
    @XmlSchemaType(name = "string")
    protected LocationTimeZone timeZone;
    protected Double latitude;
    protected Double longitude;
    protected RecordRef logo;
    protected Boolean useBins;
    protected Boolean makeInventoryAvailable;
    protected Boolean makeInventoryAvailableStore;
    @XmlSchemaType(name = "string")
    protected LocationGeolocationMethod geolocationMethod;
    @XmlSchemaType(name = "string")
    protected LocationAutoAssignmentRegionSetting autoAssignmentRegionSetting;
    protected Long bufferStock;
    protected Long dailyShippingCapacity;
    protected Long totalShippingCapacity;
    protected LocationRegionsList includeLocationRegionsList;
    protected LocationRegionsList excludeLocationRegionsList;
    protected ClassTranslationList classTranslationList;
    protected CustomFieldList customFieldList;
    @XmlAttribute(name = "internalId")
    protected String internalId;
    @XmlAttribute(name = "externalId")
    protected String externalId;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setParent(RecordRef value) {
        this.parent = value;
    }

    /**
     * Gets the value of the includeChildren property.
     * This getter has been renamed from isIncludeChildren() to getIncludeChildren() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIncludeChildren() {
        return includeChildren;
    }

    /**
     * Sets the value of the includeChildren property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeChildren(Boolean value) {
        this.includeChildren = value;
    }

    /**
     * Gets the value of the subsidiaryList property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRefList }
     *     
     */
    public RecordRefList getSubsidiaryList() {
        return subsidiaryList;
    }

    /**
     * Sets the value of the subsidiaryList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRefList }
     *     
     */
    public void setSubsidiaryList(RecordRefList value) {
        this.subsidiaryList = value;
    }

    /**
     * Gets the value of the isInactive property.
     * This getter has been renamed from isIsInactive() to getIsInactive() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIsInactive() {
        return isInactive;
    }

    /**
     * Sets the value of the isInactive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsInactive(Boolean value) {
        this.isInactive = value;
    }

    /**
     * Gets the value of the tranPrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranPrefix() {
        return tranPrefix;
    }

    /**
     * Sets the value of the tranPrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranPrefix(String value) {
        this.tranPrefix = value;
    }

    /**
     * Gets the value of the mainAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getMainAddress() {
        return mainAddress;
    }

    /**
     * Sets the value of the mainAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setMainAddress(Address value) {
        this.mainAddress = value;
    }

    /**
     * Gets the value of the returnAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getReturnAddress() {
        return returnAddress;
    }

    /**
     * Sets the value of the returnAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setReturnAddress(Address value) {
        this.returnAddress = value;
    }

    /**
     * Gets the value of the locationType property.
     * 
     * @return
     *     possible object is
     *     {@link LocationType }
     *     
     */
    public LocationType getLocationType() {
        return locationType;
    }

    /**
     * Sets the value of the locationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationType }
     *     
     */
    public void setLocationType(LocationType value) {
        this.locationType = value;
    }

    /**
     * Gets the value of the timeZone property.
     * 
     * @return
     *     possible object is
     *     {@link LocationTimeZone }
     *     
     */
    public LocationTimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the value of the timeZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationTimeZone }
     *     
     */
    public void setTimeZone(LocationTimeZone value) {
        this.timeZone = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLatitude(Double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLongitude(Double value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the logo property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getLogo() {
        return logo;
    }

    /**
     * Sets the value of the logo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setLogo(RecordRef value) {
        this.logo = value;
    }

    /**
     * Gets the value of the useBins property.
     * This getter has been renamed from isUseBins() to getUseBins() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getUseBins() {
        return useBins;
    }

    /**
     * Sets the value of the useBins property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseBins(Boolean value) {
        this.useBins = value;
    }

    /**
     * Gets the value of the makeInventoryAvailable property.
     * This getter has been renamed from isMakeInventoryAvailable() to getMakeInventoryAvailable() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getMakeInventoryAvailable() {
        return makeInventoryAvailable;
    }

    /**
     * Sets the value of the makeInventoryAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMakeInventoryAvailable(Boolean value) {
        this.makeInventoryAvailable = value;
    }

    /**
     * Gets the value of the makeInventoryAvailableStore property.
     * This getter has been renamed from isMakeInventoryAvailableStore() to getMakeInventoryAvailableStore() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getMakeInventoryAvailableStore() {
        return makeInventoryAvailableStore;
    }

    /**
     * Sets the value of the makeInventoryAvailableStore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMakeInventoryAvailableStore(Boolean value) {
        this.makeInventoryAvailableStore = value;
    }

    /**
     * Gets the value of the geolocationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link LocationGeolocationMethod }
     *     
     */
    public LocationGeolocationMethod getGeolocationMethod() {
        return geolocationMethod;
    }

    /**
     * Sets the value of the geolocationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationGeolocationMethod }
     *     
     */
    public void setGeolocationMethod(LocationGeolocationMethod value) {
        this.geolocationMethod = value;
    }

    /**
     * Gets the value of the autoAssignmentRegionSetting property.
     * 
     * @return
     *     possible object is
     *     {@link LocationAutoAssignmentRegionSetting }
     *     
     */
    public LocationAutoAssignmentRegionSetting getAutoAssignmentRegionSetting() {
        return autoAssignmentRegionSetting;
    }

    /**
     * Sets the value of the autoAssignmentRegionSetting property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationAutoAssignmentRegionSetting }
     *     
     */
    public void setAutoAssignmentRegionSetting(LocationAutoAssignmentRegionSetting value) {
        this.autoAssignmentRegionSetting = value;
    }

    /**
     * Gets the value of the bufferStock property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBufferStock() {
        return bufferStock;
    }

    /**
     * Sets the value of the bufferStock property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBufferStock(Long value) {
        this.bufferStock = value;
    }

    /**
     * Gets the value of the dailyShippingCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDailyShippingCapacity() {
        return dailyShippingCapacity;
    }

    /**
     * Sets the value of the dailyShippingCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDailyShippingCapacity(Long value) {
        this.dailyShippingCapacity = value;
    }

    /**
     * Gets the value of the totalShippingCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalShippingCapacity() {
        return totalShippingCapacity;
    }

    /**
     * Sets the value of the totalShippingCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalShippingCapacity(Long value) {
        this.totalShippingCapacity = value;
    }

    /**
     * Gets the value of the includeLocationRegionsList property.
     * 
     * @return
     *     possible object is
     *     {@link LocationRegionsList }
     *     
     */
    public LocationRegionsList getIncludeLocationRegionsList() {
        return includeLocationRegionsList;
    }

    /**
     * Sets the value of the includeLocationRegionsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationRegionsList }
     *     
     */
    public void setIncludeLocationRegionsList(LocationRegionsList value) {
        this.includeLocationRegionsList = value;
    }

    /**
     * Gets the value of the excludeLocationRegionsList property.
     * 
     * @return
     *     possible object is
     *     {@link LocationRegionsList }
     *     
     */
    public LocationRegionsList getExcludeLocationRegionsList() {
        return excludeLocationRegionsList;
    }

    /**
     * Sets the value of the excludeLocationRegionsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationRegionsList }
     *     
     */
    public void setExcludeLocationRegionsList(LocationRegionsList value) {
        this.excludeLocationRegionsList = value;
    }

    /**
     * Gets the value of the classTranslationList property.
     * 
     * @return
     *     possible object is
     *     {@link ClassTranslationList }
     *     
     */
    public ClassTranslationList getClassTranslationList() {
        return classTranslationList;
    }

    /**
     * Sets the value of the classTranslationList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassTranslationList }
     *     
     */
    public void setClassTranslationList(ClassTranslationList value) {
        this.classTranslationList = value;
    }

    /**
     * Gets the value of the customFieldList property.
     * 
     * @return
     *     possible object is
     *     {@link CustomFieldList }
     *     
     */
    public CustomFieldList getCustomFieldList() {
        return customFieldList;
    }

    /**
     * Sets the value of the customFieldList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomFieldList }
     *     
     */
    public void setCustomFieldList(CustomFieldList value) {
        this.customFieldList = value;
    }

    /**
     * Gets the value of the internalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternalId() {
        return internalId;
    }

    /**
     * Sets the value of the internalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternalId(String value) {
        this.internalId = value;
    }

    /**
     * Gets the value of the externalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets the value of the externalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalId(String value) {
        this.externalId = value;
    }

}
