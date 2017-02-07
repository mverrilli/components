
package com.netsuite.webservices.v2016_2.lists.accounting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.netsuite.webservices.v2016_2.lists.accounting.types.FairValuePriceFairValueRangePolicy;
import com.netsuite.webservices.v2016_2.platform.core.CustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.DimensionList;
import com.netsuite.webservices.v2016_2.platform.core.Record;
import com.netsuite.webservices.v2016_2.platform.core.RecordRef;


/**
 * <p>Java class for FairValuePrice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FairValuePrice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}Record"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customForm" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="item" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="itemRevenueCategory" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="fairValue" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="fairValueFormula" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="currency" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="unitsType" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="units" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="fairValueRangePolicy" type="{urn:types.accounting_2016_2.lists.webservices.netsuite.com}FairValuePriceFairValueRangePolicy" minOccurs="0"/&gt;
 *         &lt;element name="lowValue" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="lowValuePercent" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="highValue" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="highValuePercent" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="isVsoePrice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="customFieldList" type="{urn:core_2016_2.platform.webservices.netsuite.com}CustomFieldList" minOccurs="0"/&gt;
 *         &lt;element name="dimensionList" type="{urn:core_2016_2.platform.webservices.netsuite.com}DimensionList" minOccurs="0"/&gt;
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
@XmlType(name = "FairValuePrice", propOrder = {
    "customForm",
    "item",
    "itemRevenueCategory",
    "fairValue",
    "fairValueFormula",
    "currency",
    "unitsType",
    "units",
    "fairValueRangePolicy",
    "lowValue",
    "lowValuePercent",
    "highValue",
    "highValuePercent",
    "isVsoePrice",
    "startDate",
    "endDate",
    "customFieldList",
    "dimensionList"
})
public class FairValuePrice
    extends Record
{

    protected RecordRef customForm;
    protected RecordRef item;
    protected RecordRef itemRevenueCategory;
    protected Double fairValue;
    protected RecordRef fairValueFormula;
    protected RecordRef currency;
    protected RecordRef unitsType;
    protected RecordRef units;
    @XmlSchemaType(name = "string")
    protected FairValuePriceFairValueRangePolicy fairValueRangePolicy;
    protected Double lowValue;
    protected Double lowValuePercent;
    protected Double highValue;
    protected Double highValuePercent;
    protected Boolean isVsoePrice;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected CustomFieldList customFieldList;
    protected DimensionList dimensionList;
    @XmlAttribute(name = "internalId")
    protected String internalId;
    @XmlAttribute(name = "externalId")
    protected String externalId;

    /**
     * Gets the value of the customForm property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getCustomForm() {
        return customForm;
    }

    /**
     * Sets the value of the customForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setCustomForm(RecordRef value) {
        this.customForm = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setItem(RecordRef value) {
        this.item = value;
    }

    /**
     * Gets the value of the itemRevenueCategory property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getItemRevenueCategory() {
        return itemRevenueCategory;
    }

    /**
     * Sets the value of the itemRevenueCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setItemRevenueCategory(RecordRef value) {
        this.itemRevenueCategory = value;
    }

    /**
     * Gets the value of the fairValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFairValue() {
        return fairValue;
    }

    /**
     * Sets the value of the fairValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFairValue(Double value) {
        this.fairValue = value;
    }

    /**
     * Gets the value of the fairValueFormula property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getFairValueFormula() {
        return fairValueFormula;
    }

    /**
     * Sets the value of the fairValueFormula property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setFairValueFormula(RecordRef value) {
        this.fairValueFormula = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setCurrency(RecordRef value) {
        this.currency = value;
    }

    /**
     * Gets the value of the unitsType property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getUnitsType() {
        return unitsType;
    }

    /**
     * Sets the value of the unitsType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setUnitsType(RecordRef value) {
        this.unitsType = value;
    }

    /**
     * Gets the value of the units property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getUnits() {
        return units;
    }

    /**
     * Sets the value of the units property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setUnits(RecordRef value) {
        this.units = value;
    }

    /**
     * Gets the value of the fairValueRangePolicy property.
     * 
     * @return
     *     possible object is
     *     {@link FairValuePriceFairValueRangePolicy }
     *     
     */
    public FairValuePriceFairValueRangePolicy getFairValueRangePolicy() {
        return fairValueRangePolicy;
    }

    /**
     * Sets the value of the fairValueRangePolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link FairValuePriceFairValueRangePolicy }
     *     
     */
    public void setFairValueRangePolicy(FairValuePriceFairValueRangePolicy value) {
        this.fairValueRangePolicy = value;
    }

    /**
     * Gets the value of the lowValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLowValue() {
        return lowValue;
    }

    /**
     * Sets the value of the lowValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLowValue(Double value) {
        this.lowValue = value;
    }

    /**
     * Gets the value of the lowValuePercent property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLowValuePercent() {
        return lowValuePercent;
    }

    /**
     * Sets the value of the lowValuePercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLowValuePercent(Double value) {
        this.lowValuePercent = value;
    }

    /**
     * Gets the value of the highValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getHighValue() {
        return highValue;
    }

    /**
     * Sets the value of the highValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setHighValue(Double value) {
        this.highValue = value;
    }

    /**
     * Gets the value of the highValuePercent property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getHighValuePercent() {
        return highValuePercent;
    }

    /**
     * Sets the value of the highValuePercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setHighValuePercent(Double value) {
        this.highValuePercent = value;
    }

    /**
     * Gets the value of the isVsoePrice property.
     * This getter has been renamed from isIsVsoePrice() to getIsVsoePrice() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIsVsoePrice() {
        return isVsoePrice;
    }

    /**
     * Sets the value of the isVsoePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsVsoePrice(Boolean value) {
        this.isVsoePrice = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
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
     * Gets the value of the dimensionList property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionList }
     *     
     */
    public DimensionList getDimensionList() {
        return dimensionList;
    }

    /**
     * Sets the value of the dimensionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionList }
     *     
     */
    public void setDimensionList(DimensionList value) {
        this.dimensionList = value;
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
