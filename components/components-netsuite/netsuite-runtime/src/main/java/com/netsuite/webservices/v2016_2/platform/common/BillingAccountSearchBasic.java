
package com.netsuite.webservices.v2016_2.platform.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanField;
import com.netsuite.webservices.v2016_2.platform.core.SearchCustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.SearchDateField;
import com.netsuite.webservices.v2016_2.platform.core.SearchEnumMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchLongField;
import com.netsuite.webservices.v2016_2.platform.core.SearchMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecordBasic;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringField;


/**
 * <p>Java class for BillingAccountSearchBasic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillingAccountSearchBasic"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRecordBasic"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="billingSchedule" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="cashSaleForm" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="class" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="currency" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="customer" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="customerDefault" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchBooleanField" minOccurs="0"/&gt;
 *         &lt;element name="dateCreated" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDateField" minOccurs="0"/&gt;
 *         &lt;element name="department" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="externalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="externalIdString" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="frequency" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchEnumMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="idNumber" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="inactive" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchBooleanField" minOccurs="0"/&gt;
 *         &lt;element name="internalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="internalIdNumber" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchLongField" minOccurs="0"/&gt;
 *         &lt;element name="invoiceForm" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="lastBillCycleDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDateField" minOccurs="0"/&gt;
 *         &lt;element name="lastBillDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDateField" minOccurs="0"/&gt;
 *         &lt;element name="location" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
 *         &lt;element name="memo" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchStringField" minOccurs="0"/&gt;
 *         &lt;element name="nextBillCycleDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDateField" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchDateField" minOccurs="0"/&gt;
 *         &lt;element name="subsidiary" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchMultiSelectField" minOccurs="0"/&gt;
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
@XmlType(name = "BillingAccountSearchBasic", propOrder = {
    "billingSchedule",
    "cashSaleForm",
    "clazz",
    "currency",
    "customer",
    "customerDefault",
    "dateCreated",
    "department",
    "externalId",
    "externalIdString",
    "frequency",
    "idNumber",
    "inactive",
    "internalId",
    "internalIdNumber",
    "invoiceForm",
    "lastBillCycleDate",
    "lastBillDate",
    "location",
    "memo",
    "name",
    "nextBillCycleDate",
    "startDate",
    "subsidiary",
    "customFieldList"
})
public class BillingAccountSearchBasic
    extends SearchRecordBasic
{

    protected SearchMultiSelectField billingSchedule;
    protected SearchMultiSelectField cashSaleForm;
    @XmlElement(name = "class")
    protected SearchMultiSelectField clazz;
    protected SearchMultiSelectField currency;
    protected SearchMultiSelectField customer;
    protected SearchBooleanField customerDefault;
    protected SearchDateField dateCreated;
    protected SearchMultiSelectField department;
    protected SearchMultiSelectField externalId;
    protected SearchStringField externalIdString;
    protected SearchEnumMultiSelectField frequency;
    protected SearchStringField idNumber;
    protected SearchBooleanField inactive;
    protected SearchMultiSelectField internalId;
    protected SearchLongField internalIdNumber;
    protected SearchMultiSelectField invoiceForm;
    protected SearchDateField lastBillCycleDate;
    protected SearchDateField lastBillDate;
    protected SearchMultiSelectField location;
    protected SearchStringField memo;
    protected SearchStringField name;
    protected SearchDateField nextBillCycleDate;
    protected SearchDateField startDate;
    protected SearchMultiSelectField subsidiary;
    protected SearchCustomFieldList customFieldList;

    /**
     * Gets the value of the billingSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getBillingSchedule() {
        return billingSchedule;
    }

    /**
     * Sets the value of the billingSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setBillingSchedule(SearchMultiSelectField value) {
        this.billingSchedule = value;
    }

    /**
     * Gets the value of the cashSaleForm property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getCashSaleForm() {
        return cashSaleForm;
    }

    /**
     * Sets the value of the cashSaleForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setCashSaleForm(SearchMultiSelectField value) {
        this.cashSaleForm = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setClazz(SearchMultiSelectField value) {
        this.clazz = value;
    }

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
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setCustomer(SearchMultiSelectField value) {
        this.customer = value;
    }

    /**
     * Gets the value of the customerDefault property.
     * 
     * @return
     *     possible object is
     *     {@link SearchBooleanField }
     *     
     */
    public SearchBooleanField getCustomerDefault() {
        return customerDefault;
    }

    /**
     * Sets the value of the customerDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchBooleanField }
     *     
     */
    public void setCustomerDefault(SearchBooleanField value) {
        this.customerDefault = value;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDateField }
     *     
     */
    public SearchDateField getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDateField }
     *     
     */
    public void setDateCreated(SearchDateField value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the department property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getDepartment() {
        return department;
    }

    /**
     * Sets the value of the department property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setDepartment(SearchMultiSelectField value) {
        this.department = value;
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
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public SearchEnumMultiSelectField getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchEnumMultiSelectField }
     *     
     */
    public void setFrequency(SearchEnumMultiSelectField value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the idNumber property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getIdNumber() {
        return idNumber;
    }

    /**
     * Sets the value of the idNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setIdNumber(SearchStringField value) {
        this.idNumber = value;
    }

    /**
     * Gets the value of the inactive property.
     * 
     * @return
     *     possible object is
     *     {@link SearchBooleanField }
     *     
     */
    public SearchBooleanField getInactive() {
        return inactive;
    }

    /**
     * Sets the value of the inactive property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchBooleanField }
     *     
     */
    public void setInactive(SearchBooleanField value) {
        this.inactive = value;
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
     * Gets the value of the invoiceForm property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getInvoiceForm() {
        return invoiceForm;
    }

    /**
     * Sets the value of the invoiceForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setInvoiceForm(SearchMultiSelectField value) {
        this.invoiceForm = value;
    }

    /**
     * Gets the value of the lastBillCycleDate property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDateField }
     *     
     */
    public SearchDateField getLastBillCycleDate() {
        return lastBillCycleDate;
    }

    /**
     * Sets the value of the lastBillCycleDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDateField }
     *     
     */
    public void setLastBillCycleDate(SearchDateField value) {
        this.lastBillCycleDate = value;
    }

    /**
     * Gets the value of the lastBillDate property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDateField }
     *     
     */
    public SearchDateField getLastBillDate() {
        return lastBillDate;
    }

    /**
     * Sets the value of the lastBillDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDateField }
     *     
     */
    public void setLastBillDate(SearchDateField value) {
        this.lastBillDate = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public SearchMultiSelectField getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchMultiSelectField }
     *     
     */
    public void setLocation(SearchMultiSelectField value) {
        this.location = value;
    }

    /**
     * Gets the value of the memo property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStringField }
     *     
     */
    public SearchStringField getMemo() {
        return memo;
    }

    /**
     * Sets the value of the memo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStringField }
     *     
     */
    public void setMemo(SearchStringField value) {
        this.memo = value;
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
     * Gets the value of the nextBillCycleDate property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDateField }
     *     
     */
    public SearchDateField getNextBillCycleDate() {
        return nextBillCycleDate;
    }

    /**
     * Sets the value of the nextBillCycleDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDateField }
     *     
     */
    public void setNextBillCycleDate(SearchDateField value) {
        this.nextBillCycleDate = value;
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
