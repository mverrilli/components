
package com.netsuite.webservices.v2016_2.platform.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanField;
import com.netsuite.webservices.v2016_2.platform.core.SearchCustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.SearchDoubleField;
import com.netsuite.webservices.v2016_2.platform.core.SearchEnumMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchLongField;
import com.netsuite.webservices.v2016_2.platform.core.SearchMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecordBasic;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringField;


/**
 * <p>Java class for LocationSearchBasic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocationSearchBasic"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRecordBasic"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="address" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="autoAssignmentRegionSetting" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchEnumMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="bufferStock" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchLongField" minOccurs="0"/&gt;
 *         &lt;element name="city" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="country" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchEnumMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="county" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="dailyShippingCapacity" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchLongField" minOccurs="0"/&gt;
 *         &lt;element name="externalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="externalIdString" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="geolocationMethod" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchEnumMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="internalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="internalIdNumber" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchLongField" minOccurs="0"/&gt;
 *         &lt;element name="isInactive" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchBooleanField" minOccurs="0"/&gt;
 *         &lt;element name="isOffice" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchBooleanField" minOccurs="0"/&gt;
 *         &lt;element name="latitude" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDoubleField" minOccurs="0"/&gt;
 *         &lt;element name="locationType" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchEnumMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="longitude" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDoubleField" minOccurs="0"/&gt;
 *         &lt;element name="makeInventoryAvailable" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchBooleanField" minOccurs="0"/&gt;
 *         &lt;element name="makeInventoryAvailableStore" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchBooleanField" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="nameNoHierarchy" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="phone" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="state" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="subsidiary" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="timeZone" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchEnumMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="totalShippingCapacity" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchLongField" minOccurs="0"/&gt;
 *         &lt;element name="tranprefix" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="usesBins" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchBooleanField" minOccurs="0"/&gt;
 *         &lt;element name="zip" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="customFieldList" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchCustomFieldList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationSearchBasic", propOrder = {
    "address",
    "autoAssignmentRegionSetting",
    "bufferStock",
    "city",
    "country",
    "county",
    "dailyShippingCapacity",
    "externalId",
    "externalIdString",
    "geolocationMethod",
    "internalId",
    "internalIdNumber",
    "isInactive",
    "isOffice",
    "latitude",
    "locationType",
    "longitude",
    "makeInventoryAvailable",
    "makeInventoryAvailableStore",
    "name",
    "nameNoHierarchy",
    "phone",
    "state",
    "subsidiary",
    "timeZone",
    "totalShippingCapacity",
    "tranprefix",
    "usesBins",
    "zip",
    "customFieldList"
})
public class LocationSearchBasic
    extends SearchRecordBasic
{

    protected SearchStringField address;
    protected SearchEnumMultiSelectField autoAssignmentRegionSetting;
    protected SearchLongField bufferStock;
    protected SearchStringField city;
    protected SearchEnumMultiSelectField country;
    protected SearchStringField county;
    protected SearchLongField dailyShippingCapacity;
    protected SearchMultiSelectField externalId;
    protected SearchStringField externalIdString;
    protected SearchEnumMultiSelectField geolocationMethod;
    protected SearchMultiSelectField internalId;
    protected SearchLongField internalIdNumber;
    protected SearchBooleanField isInactive;
    protected SearchBooleanField isOffice;
    protected SearchDoubleField latitude;
    protected SearchEnumMultiSelectField locationType;
    protected SearchDoubleField longitude;
    protected SearchBooleanField makeInventoryAvailable;
    protected SearchBooleanField makeInventoryAvailableStore;
    protected SearchStringField name;
    protected SearchStringField nameNoHierarchy;
    protected SearchStringField phone;
    protected SearchStringField state;
    protected SearchMultiSelectField subsidiary;
    protected SearchEnumMultiSelectField timeZone;
    protected SearchLongField totalShippingCapacity;
    protected SearchStringField tranprefix;
    protected SearchBooleanField usesBins;
    protected SearchStringField zip;
    protected SearchCustomFieldList customFieldList;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setAddress(SearchStringField value) {
        this.address = value;
    }

    /**
     * Gets the value of the autoAssignmentRegionSetting property.
     * 
     * @return
     *     possible object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public SearchEnumMultiSelectField getAutoAssignmentRegionSetting() {
        return autoAssignmentRegionSetting;
    }

    /**
     * Sets the value of the autoAssignmentRegionSetting property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public void setAutoAssignmentRegionSetting(SearchEnumMultiSelectField value) {
        this.autoAssignmentRegionSetting = value;
    }

    /**
     * Gets the value of the bufferStock property.
     * 
     * @return
     *     possible object is
     *     {@link SearchLongField }
     *     
     */
    public SearchLongField getBufferStock() {
        return bufferStock;
    }

    /**
     * Sets the value of the bufferStock property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchLongField }
     *     
     */
    public void setBufferStock(SearchLongField value) {
        this.bufferStock = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setCity(SearchStringField value) {
        this.city = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public SearchEnumMultiSelectField getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public void setCountry(SearchEnumMultiSelectField value) {
        this.country = value;
    }

    /**
     * Gets the value of the county property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getCounty() {
        return county;
    }

    /**
     * Sets the value of the county property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setCounty(SearchStringField value) {
        this.county = value;
    }

    /**
     * Gets the value of the dailyShippingCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link SearchLongField }
     *     
     */
    public SearchLongField getDailyShippingCapacity() {
        return dailyShippingCapacity;
    }

    /**
     * Sets the value of the dailyShippingCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchLongField }
     *     
     */
    public void setDailyShippingCapacity(SearchLongField value) {
        this.dailyShippingCapacity = value;
    }

    /**
     * Gets the value of the externalId property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getExternalId() {
        return externalId;
    }

    /**
     * Sets the value of the externalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setExternalId(SearchMultiSelectField value) {
        this.externalId = value;
    }

    /**
     * Gets the value of the externalIdString property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getExternalIdString() {
        return externalIdString;
    }

    /**
     * Sets the value of the externalIdString property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setExternalIdString(SearchStringField value) {
        this.externalIdString = value;
    }

    /**
     * Gets the value of the geolocationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public SearchEnumMultiSelectField getGeolocationMethod() {
        return geolocationMethod;
    }

    /**
     * Sets the value of the geolocationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public void setGeolocationMethod(SearchEnumMultiSelectField value) {
        this.geolocationMethod = value;
    }

    /**
     * Gets the value of the internalId property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getInternalId() {
        return internalId;
    }

    /**
     * Sets the value of the internalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setInternalId(SearchMultiSelectField value) {
        this.internalId = value;
    }

    /**
     * Gets the value of the internalIdNumber property.
     * 
     * @return
     *     possible object is
     *     {@link SearchLongField }
     *     
     */
    public SearchLongField getInternalIdNumber() {
        return internalIdNumber;
    }

    /**
     * Sets the value of the internalIdNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchLongField }
     *     
     */
    public void setInternalIdNumber(SearchLongField value) {
        this.internalIdNumber = value;
    }

    /**
     * Gets the value of the isInactive property.
     * 
     * @return
     *     possible object is
     *     {@link SearchBooleanField }
     *     
     */
    public SearchBooleanField getIsInactive() {
        return isInactive;
    }

    /**
     * Sets the value of the isInactive property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchBooleanField }
     *     
     */
    public void setIsInactive(SearchBooleanField value) {
        this.isInactive = value;
    }

    /**
     * Gets the value of the isOffice property.
     * 
     * @return
     *     possible object is
     *     {@link SearchBooleanField }
     *     
     */
    public SearchBooleanField getIsOffice() {
        return isOffice;
    }

    /**
     * Sets the value of the isOffice property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchBooleanField }
     *     
     */
    public void setIsOffice(SearchBooleanField value) {
        this.isOffice = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDoubleField }
     *     
     */
    public SearchDoubleField getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDoubleField }
     *     
     */
    public void setLatitude(SearchDoubleField value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the locationType property.
     * 
     * @return
     *     possible object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public SearchEnumMultiSelectField getLocationType() {
        return locationType;
    }

    /**
     * Sets the value of the locationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public void setLocationType(SearchEnumMultiSelectField value) {
        this.locationType = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDoubleField }
     *     
     */
    public SearchDoubleField getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDoubleField }
     *     
     */
    public void setLongitude(SearchDoubleField value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the makeInventoryAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link SearchBooleanField }
     *     
     */
    public SearchBooleanField getMakeInventoryAvailable() {
        return makeInventoryAvailable;
    }

    /**
     * Sets the value of the makeInventoryAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchBooleanField }
     *     
     */
    public void setMakeInventoryAvailable(SearchBooleanField value) {
        this.makeInventoryAvailable = value;
    }

    /**
     * Gets the value of the makeInventoryAvailableStore property.
     * 
     * @return
     *     possible object is
     *     {@link SearchBooleanField }
     *     
     */
    public SearchBooleanField getMakeInventoryAvailableStore() {
        return makeInventoryAvailableStore;
    }

    /**
     * Sets the value of the makeInventoryAvailableStore property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchBooleanField }
     *     
     */
    public void setMakeInventoryAvailableStore(SearchBooleanField value) {
        this.makeInventoryAvailableStore = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setName(SearchStringField value) {
        this.name = value;
    }

    /**
     * Gets the value of the nameNoHierarchy property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getNameNoHierarchy() {
        return nameNoHierarchy;
    }

    /**
     * Sets the value of the nameNoHierarchy property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setNameNoHierarchy(SearchStringField value) {
        this.nameNoHierarchy = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setPhone(SearchStringField value) {
        this.phone = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setState(SearchStringField value) {
        this.state = value;
    }

    /**
     * Gets the value of the subsidiary property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getSubsidiary() {
        return subsidiary;
    }

    /**
     * Sets the value of the subsidiary property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setSubsidiary(SearchMultiSelectField value) {
        this.subsidiary = value;
    }

    /**
     * Gets the value of the timeZone property.
     * 
     * @return
     *     possible object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public SearchEnumMultiSelectField getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the value of the timeZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public void setTimeZone(SearchEnumMultiSelectField value) {
        this.timeZone = value;
    }

    /**
     * Gets the value of the totalShippingCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link SearchLongField }
     *     
     */
    public SearchLongField getTotalShippingCapacity() {
        return totalShippingCapacity;
    }

    /**
     * Sets the value of the totalShippingCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchLongField }
     *     
     */
    public void setTotalShippingCapacity(SearchLongField value) {
        this.totalShippingCapacity = value;
    }

    /**
     * Gets the value of the tranprefix property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getTranprefix() {
        return tranprefix;
    }

    /**
     * Sets the value of the tranprefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setTranprefix(SearchStringField value) {
        this.tranprefix = value;
    }

    /**
     * Gets the value of the usesBins property.
     * 
     * @return
     *     possible object is
     *     {@link SearchBooleanField }
     *     
     */
    public SearchBooleanField getUsesBins() {
        return usesBins;
    }

    /**
     * Sets the value of the usesBins property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchBooleanField }
     *     
     */
    public void setUsesBins(SearchBooleanField value) {
        this.usesBins = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setZip(SearchStringField value) {
        this.zip = value;
    }

    /**
     * Gets the value of the customFieldList property.
     * 
     * @return
     *     possible object is
     *     {@link SearchCustomFieldList }
     *     
     */
    public SearchCustomFieldList getCustomFieldList() {
        return customFieldList;
    }

    /**
     * Sets the value of the customFieldList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchCustomFieldList }
     *     
     */
    public void setCustomFieldList(SearchCustomFieldList value) {
        this.customFieldList = value;
    }

}
