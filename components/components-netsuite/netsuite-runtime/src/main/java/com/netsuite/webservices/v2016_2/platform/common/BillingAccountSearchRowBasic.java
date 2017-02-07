
package com.netsuite.webservices.v2016_2.platform.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnBooleanField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnCustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnDateField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnEnumSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnStringField;
import com.netsuite.webservices.v2016_2.platform.core.SearchRowBasic;


/**
 * <p>Java class for BillingAccountSearchRowBasic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillingAccountSearchRowBasic"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRowBasic"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="billingSchedule" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="cashSaleForm" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="class" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="currency" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="customer" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="customerDefault" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnBooleanField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dateCreated" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDateField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="department" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="externalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="frequency" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnEnumSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="idNumber" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="inactive" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnBooleanField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="internalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="invoiceForm" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="lastBillCycleDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDateField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="lastBillDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDateField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="location" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="memo" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="nextBillCycleDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDateField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDateField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="subsidiary" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="customFieldList" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnCustomFieldList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillingAccountSearchRowBasic", propOrder = {
    "billingSchedule",
    "cashSaleForm",
    "clazz",
    "currency",
    "customer",
    "customerDefault",
    "dateCreated",
    "department",
    "externalId",
    "frequency",
    "idNumber",
    "inactive",
    "internalId",
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
public class BillingAccountSearchRowBasic
    extends SearchRowBasic
{

    protected List<SearchColumnSelectField> billingSchedule;
    protected List<SearchColumnSelectField> cashSaleForm;
    @XmlElement(name = "class")
    protected List<SearchColumnSelectField> clazz;
    protected List<SearchColumnSelectField> currency;
    protected List<SearchColumnSelectField> customer;
    protected List<SearchColumnBooleanField> customerDefault;
    protected List<SearchColumnDateField> dateCreated;
    protected List<SearchColumnSelectField> department;
    protected List<SearchColumnSelectField> externalId;
    protected List<SearchColumnEnumSelectField> frequency;
    protected List<SearchColumnStringField> idNumber;
    protected List<SearchColumnBooleanField> inactive;
    protected List<SearchColumnSelectField> internalId;
    protected List<SearchColumnSelectField> invoiceForm;
    protected List<SearchColumnDateField> lastBillCycleDate;
    protected List<SearchColumnDateField> lastBillDate;
    protected List<SearchColumnSelectField> location;
    protected List<SearchColumnStringField> memo;
    protected List<SearchColumnStringField> name;
    protected List<SearchColumnDateField> nextBillCycleDate;
    protected List<SearchColumnDateField> startDate;
    protected List<SearchColumnSelectField> subsidiary;
    protected SearchColumnCustomFieldList customFieldList;

    /**
     * Gets the value of the billingSchedule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billingSchedule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillingSchedule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getBillingSchedule() {
        if (billingSchedule == null) {
            billingSchedule = new ArrayList<SearchColumnSelectField>();
        }
        return this.billingSchedule;
    }

    /**
     * Gets the value of the cashSaleForm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cashSaleForm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCashSaleForm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getCashSaleForm() {
        if (cashSaleForm == null) {
            cashSaleForm = new ArrayList<SearchColumnSelectField>();
        }
        return this.cashSaleForm;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clazz property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClazz().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getClazz() {
        if (clazz == null) {
            clazz = new ArrayList<SearchColumnSelectField>();
        }
        return this.clazz;
    }

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
     * Gets the value of the customer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getCustomer() {
        if (customer == null) {
            customer = new ArrayList<SearchColumnSelectField>();
        }
        return this.customer;
    }

    /**
     * Gets the value of the customerDefault property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerDefault property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerDefault().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnBooleanField }
     * 
     * 
     */
    public List<SearchColumnBooleanField> getCustomerDefault() {
        if (customerDefault == null) {
            customerDefault = new ArrayList<SearchColumnBooleanField>();
        }
        return this.customerDefault;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dateCreated property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDateCreated().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDateField }
     * 
     * 
     */
    public List<SearchColumnDateField> getDateCreated() {
        if (dateCreated == null) {
            dateCreated = new ArrayList<SearchColumnDateField>();
        }
        return this.dateCreated;
    }

    /**
     * Gets the value of the department property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the department property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepartment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getDepartment() {
        if (department == null) {
            department = new ArrayList<SearchColumnSelectField>();
        }
        return this.department;
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
     * Gets the value of the frequency property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frequency property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrequency().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnEnumSelectField }
     * 
     * 
     */
    public List<SearchColumnEnumSelectField> getFrequency() {
        if (frequency == null) {
            frequency = new ArrayList<SearchColumnEnumSelectField>();
        }
        return this.frequency;
    }

    /**
     * Gets the value of the idNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getIdNumber() {
        if (idNumber == null) {
            idNumber = new ArrayList<SearchColumnStringField>();
        }
        return this.idNumber;
    }

    /**
     * Gets the value of the inactive property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inactive property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInactive().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnBooleanField }
     * 
     * 
     */
    public List<SearchColumnBooleanField> getInactive() {
        if (inactive == null) {
            inactive = new ArrayList<SearchColumnBooleanField>();
        }
        return this.inactive;
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
     * Gets the value of the invoiceForm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceForm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoiceForm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getInvoiceForm() {
        if (invoiceForm == null) {
            invoiceForm = new ArrayList<SearchColumnSelectField>();
        }
        return this.invoiceForm;
    }

    /**
     * Gets the value of the lastBillCycleDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lastBillCycleDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLastBillCycleDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDateField }
     * 
     * 
     */
    public List<SearchColumnDateField> getLastBillCycleDate() {
        if (lastBillCycleDate == null) {
            lastBillCycleDate = new ArrayList<SearchColumnDateField>();
        }
        return this.lastBillCycleDate;
    }

    /**
     * Gets the value of the lastBillDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lastBillDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLastBillDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDateField }
     * 
     * 
     */
    public List<SearchColumnDateField> getLastBillDate() {
        if (lastBillDate == null) {
            lastBillDate = new ArrayList<SearchColumnDateField>();
        }
        return this.lastBillDate;
    }

    /**
     * Gets the value of the location property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the location property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getLocation() {
        if (location == null) {
            location = new ArrayList<SearchColumnSelectField>();
        }
        return this.location;
    }

    /**
     * Gets the value of the memo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the memo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMemo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getMemo() {
        if (memo == null) {
            memo = new ArrayList<SearchColumnStringField>();
        }
        return this.memo;
    }

    /**
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getName() {
        if (name == null) {
            name = new ArrayList<SearchColumnStringField>();
        }
        return this.name;
    }

    /**
     * Gets the value of the nextBillCycleDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nextBillCycleDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNextBillCycleDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDateField }
     * 
     * 
     */
    public List<SearchColumnDateField> getNextBillCycleDate() {
        if (nextBillCycleDate == null) {
            nextBillCycleDate = new ArrayList<SearchColumnDateField>();
        }
        return this.nextBillCycleDate;
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
     * Gets the value of the subsidiary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subsidiary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubsidiary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getSubsidiary() {
        if (subsidiary == null) {
            subsidiary = new ArrayList<SearchColumnSelectField>();
        }
        return this.subsidiary;
    }

    /**
     * Gets the value of the customFieldList property.
     * 
     * @return
     *     possible object is
     *     {@link SearchColumnCustomFieldList }
     *     
     */
    public SearchColumnCustomFieldList getCustomFieldList() {
        return customFieldList;
    }

    /**
     * Sets the value of the customFieldList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchColumnCustomFieldList }
     *     
     */
    public void setCustomFieldList(SearchColumnCustomFieldList value) {
        this.customFieldList = value;
    }

}
