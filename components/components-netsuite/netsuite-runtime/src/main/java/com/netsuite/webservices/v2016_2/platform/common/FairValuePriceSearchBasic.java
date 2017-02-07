
package com.netsuite.webservices.v2016_2.platform.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDateField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDoubleField;
import com.netsuite.webservices.v2016_2.platform.core.SearchEnumMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchLongField;
import com.netsuite.webservices.v2016_2.platform.core.SearchMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecordBasic;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringField;


/**
 * <p>Java class for FairValuePriceSearchBasic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FairValuePriceSearchBasic"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRecordBasic"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="currency" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="endDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDateField" minOccurs="0"/&gt;
 *         &lt;element name="externalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="externalIdString" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="fairValue" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDoubleField" minOccurs="0"/&gt;
 *         &lt;element name="fairValueFormula" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="fairValueRangePolicy" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchEnumMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="highValue" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDoubleField" minOccurs="0"/&gt;
 *         &lt;element name="highValuePercent" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDoubleField" minOccurs="0"/&gt;
 *         &lt;element name="internalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="internalIdNumber" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchLongField" minOccurs="0"/&gt;
 *         &lt;element name="isVsoePrice" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchBooleanField" minOccurs="0"/&gt;
 *         &lt;element name="item" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="itemRevenueCategory" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="lowValue" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDoubleField" minOccurs="0"/&gt;
 *         &lt;element name="lowValuePercent" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDoubleField" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDateField" minOccurs="0"/&gt;
 *         &lt;element name="unitsType" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FairValuePriceSearchBasic", propOrder = {
    "currency",
    "endDate",
    "externalId",
    "externalIdString",
    "fairValue",
    "fairValueFormula",
    "fairValueRangePolicy",
    "highValue",
    "highValuePercent",
    "internalId",
    "internalIdNumber",
    "isVsoePrice",
    "item",
    "itemRevenueCategory",
    "lowValue",
    "lowValuePercent",
    "startDate",
    "unitsType"
})
public class FairValuePriceSearchBasic
    extends SearchRecordBasic
{

    protected SearchMultiSelectField currency;
    protected SearchDateField endDate;
    protected SearchMultiSelectField externalId;
    protected SearchStringField externalIdString;
    protected SearchDoubleField fairValue;
    protected SearchMultiSelectField fairValueFormula;
    protected SearchEnumMultiSelectField fairValueRangePolicy;
    protected SearchDoubleField highValue;
    protected SearchDoubleField highValuePercent;
    protected SearchMultiSelectField internalId;
    protected SearchLongField internalIdNumber;
    protected SearchBooleanField isVsoePrice;
    protected SearchMultiSelectField item;
    protected SearchMultiSelectField itemRevenueCategory;
    protected SearchDoubleField lowValue;
    protected SearchDoubleField lowValuePercent;
    protected SearchDateField startDate;
    protected SearchMultiSelectField unitsType;

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setCurrency(SearchMultiSelectField value) {
        this.currency = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDateField }
     *     
     */
    public SearchDateField getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDateField }
     *     
     */
    public void setEndDate(SearchDateField value) {
        this.endDate = value;
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
     * Gets the value of the fairValue property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDoubleField }
     *     
     */
    public SearchDoubleField getFairValue() {
        return fairValue;
    }

    /**
     * Sets the value of the fairValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDoubleField }
     *     
     */
    public void setFairValue(SearchDoubleField value) {
        this.fairValue = value;
    }

    /**
     * Gets the value of the fairValueFormula property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getFairValueFormula() {
        return fairValueFormula;
    }

    /**
     * Sets the value of the fairValueFormula property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setFairValueFormula(SearchMultiSelectField value) {
        this.fairValueFormula = value;
    }

    /**
     * Gets the value of the fairValueRangePolicy property.
     * 
     * @return
     *     possible object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public SearchEnumMultiSelectField getFairValueRangePolicy() {
        return fairValueRangePolicy;
    }

    /**
     * Sets the value of the fairValueRangePolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public void setFairValueRangePolicy(SearchEnumMultiSelectField value) {
        this.fairValueRangePolicy = value;
    }

    /**
     * Gets the value of the highValue property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDoubleField }
     *     
     */
    public SearchDoubleField getHighValue() {
        return highValue;
    }

    /**
     * Sets the value of the highValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDoubleField }
     *     
     */
    public void setHighValue(SearchDoubleField value) {
        this.highValue = value;
    }

    /**
     * Gets the value of the highValuePercent property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDoubleField }
     *     
     */
    public SearchDoubleField getHighValuePercent() {
        return highValuePercent;
    }

    /**
     * Sets the value of the highValuePercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDoubleField }
     *     
     */
    public void setHighValuePercent(SearchDoubleField value) {
        this.highValuePercent = value;
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
     * Gets the value of the isVsoePrice property.
     * 
     * @return
     *     possible object is
     *     {@link SearchBooleanField }
     *     
     */
    public SearchBooleanField getIsVsoePrice() {
        return isVsoePrice;
    }

    /**
     * Sets the value of the isVsoePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchBooleanField }
     *     
     */
    public void setIsVsoePrice(SearchBooleanField value) {
        this.isVsoePrice = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setItem(SearchMultiSelectField value) {
        this.item = value;
    }

    /**
     * Gets the value of the itemRevenueCategory property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getItemRevenueCategory() {
        return itemRevenueCategory;
    }

    /**
     * Sets the value of the itemRevenueCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setItemRevenueCategory(SearchMultiSelectField value) {
        this.itemRevenueCategory = value;
    }

    /**
     * Gets the value of the lowValue property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDoubleField }
     *     
     */
    public SearchDoubleField getLowValue() {
        return lowValue;
    }

    /**
     * Sets the value of the lowValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDoubleField }
     *     
     */
    public void setLowValue(SearchDoubleField value) {
        this.lowValue = value;
    }

    /**
     * Gets the value of the lowValuePercent property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDoubleField }
     *     
     */
    public SearchDoubleField getLowValuePercent() {
        return lowValuePercent;
    }

    /**
     * Sets the value of the lowValuePercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDoubleField }
     *     
     */
    public void setLowValuePercent(SearchDoubleField value) {
        this.lowValuePercent = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDateField }
     *     
     */
    public SearchDateField getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDateField }
     *     
     */
    public void setStartDate(SearchDateField value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the unitsType property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getUnitsType() {
        return unitsType;
    }

    /**
     * Sets the value of the unitsType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setUnitsType(SearchMultiSelectField value) {
        this.unitsType = value;
    }

}
