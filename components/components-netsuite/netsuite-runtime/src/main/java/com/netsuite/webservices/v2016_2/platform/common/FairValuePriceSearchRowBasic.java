
package com.netsuite.webservices.v2016_2.platform.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnBooleanField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnDateField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnDoubleField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnEnumSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchRowBasic;


/**
 * <p>Java class for FairValuePriceSearchRowBasic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FairValuePriceSearchRowBasic"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRowBasic"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="currency" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="endDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDateField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="externalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="fairValue" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDoubleField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="fairValueFormula" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="fairValueRangePolicy" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnEnumSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="highValue" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDoubleField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="highValuePercent" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDoubleField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="internalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="isVsoePrice" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnBooleanField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="item" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="itemRevenueCategory" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="lowValue" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDoubleField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="lowValuePercent" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDoubleField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDateField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="units" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="unitsType" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FairValuePriceSearchRowBasic", propOrder = {
    "currency",
    "endDate",
    "externalId",
    "fairValue",
    "fairValueFormula",
    "fairValueRangePolicy",
    "highValue",
    "highValuePercent",
    "internalId",
    "isVsoePrice",
    "item",
    "itemRevenueCategory",
    "lowValue",
    "lowValuePercent",
    "startDate",
    "units",
    "unitsType"
})
public class FairValuePriceSearchRowBasic
    extends SearchRowBasic
{

    protected List<SearchColumnSelectField> currency;
    protected List<SearchColumnDateField> endDate;
    protected List<SearchColumnSelectField> externalId;
    protected List<SearchColumnDoubleField> fairValue;
    protected List<SearchColumnSelectField> fairValueFormula;
    protected List<SearchColumnEnumSelectField> fairValueRangePolicy;
    protected List<SearchColumnDoubleField> highValue;
    protected List<SearchColumnDoubleField> highValuePercent;
    protected List<SearchColumnSelectField> internalId;
    protected List<SearchColumnBooleanField> isVsoePrice;
    protected List<SearchColumnSelectField> item;
    protected List<SearchColumnSelectField> itemRevenueCategory;
    protected List<SearchColumnDoubleField> lowValue;
    protected List<SearchColumnDoubleField> lowValuePercent;
    protected List<SearchColumnDateField> startDate;
    protected List<SearchColumnSelectField> units;
    protected List<SearchColumnSelectField> unitsType;

    /**
     * Gets the value of the currency property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the currency property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCurrency().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getCurrency() {
        if (currency == null) {
            currency = new ArrayList<SearchColumnSelectField>();
        }
        return this.currency;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the endDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEndDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDateField }
     * 
     * 
     */
    public List<SearchColumnDateField> getEndDate() {
        if (endDate == null) {
            endDate = new ArrayList<SearchColumnDateField>();
        }
        return this.endDate;
    }

    /**
     * Gets the value of the externalId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the externalId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExternalId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getExternalId() {
        if (externalId == null) {
            externalId = new ArrayList<SearchColumnSelectField>();
        }
        return this.externalId;
    }

    /**
     * Gets the value of the fairValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fairValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFairValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDoubleField }
     * 
     * 
     */
    public List<SearchColumnDoubleField> getFairValue() {
        if (fairValue == null) {
            fairValue = new ArrayList<SearchColumnDoubleField>();
        }
        return this.fairValue;
    }

    /**
     * Gets the value of the fairValueFormula property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fairValueFormula property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFairValueFormula().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getFairValueFormula() {
        if (fairValueFormula == null) {
            fairValueFormula = new ArrayList<SearchColumnSelectField>();
        }
        return this.fairValueFormula;
    }

    /**
     * Gets the value of the fairValueRangePolicy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fairValueRangePolicy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFairValueRangePolicy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnEnumSelectField }
     * 
     * 
     */
    public List<SearchColumnEnumSelectField> getFairValueRangePolicy() {
        if (fairValueRangePolicy == null) {
            fairValueRangePolicy = new ArrayList<SearchColumnEnumSelectField>();
        }
        return this.fairValueRangePolicy;
    }

    /**
     * Gets the value of the highValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the highValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHighValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDoubleField }
     * 
     * 
     */
    public List<SearchColumnDoubleField> getHighValue() {
        if (highValue == null) {
            highValue = new ArrayList<SearchColumnDoubleField>();
        }
        return this.highValue;
    }

    /**
     * Gets the value of the highValuePercent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the highValuePercent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHighValuePercent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDoubleField }
     * 
     * 
     */
    public List<SearchColumnDoubleField> getHighValuePercent() {
        if (highValuePercent == null) {
            highValuePercent = new ArrayList<SearchColumnDoubleField>();
        }
        return this.highValuePercent;
    }

    /**
     * Gets the value of the internalId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the internalId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInternalId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getInternalId() {
        if (internalId == null) {
            internalId = new ArrayList<SearchColumnSelectField>();
        }
        return this.internalId;
    }

    /**
     * Gets the value of the isVsoePrice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the isVsoePrice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIsVsoePrice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnBooleanField }
     * 
     * 
     */
    public List<SearchColumnBooleanField> getIsVsoePrice() {
        if (isVsoePrice == null) {
            isVsoePrice = new ArrayList<SearchColumnBooleanField>();
        }
        return this.isVsoePrice;
    }

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getItem() {
        if (item == null) {
            item = new ArrayList<SearchColumnSelectField>();
        }
        return this.item;
    }

    /**
     * Gets the value of the itemRevenueCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemRevenueCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemRevenueCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getItemRevenueCategory() {
        if (itemRevenueCategory == null) {
            itemRevenueCategory = new ArrayList<SearchColumnSelectField>();
        }
        return this.itemRevenueCategory;
    }

    /**
     * Gets the value of the lowValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lowValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLowValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDoubleField }
     * 
     * 
     */
    public List<SearchColumnDoubleField> getLowValue() {
        if (lowValue == null) {
            lowValue = new ArrayList<SearchColumnDoubleField>();
        }
        return this.lowValue;
    }

    /**
     * Gets the value of the lowValuePercent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lowValuePercent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLowValuePercent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDoubleField }
     * 
     * 
     */
    public List<SearchColumnDoubleField> getLowValuePercent() {
        if (lowValuePercent == null) {
            lowValuePercent = new ArrayList<SearchColumnDoubleField>();
        }
        return this.lowValuePercent;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the startDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStartDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDateField }
     * 
     * 
     */
    public List<SearchColumnDateField> getStartDate() {
        if (startDate == null) {
            startDate = new ArrayList<SearchColumnDateField>();
        }
        return this.startDate;
    }

    /**
     * Gets the value of the units property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the units property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnits().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getUnits() {
        if (units == null) {
            units = new ArrayList<SearchColumnSelectField>();
        }
        return this.units;
    }

    /**
     * Gets the value of the unitsType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unitsType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnitsType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getUnitsType() {
        if (unitsType == null) {
            unitsType = new ArrayList<SearchColumnSelectField>();
        }
        return this.unitsType;
    }

}
