
package com.netsuite.webservices.v2016_2.platform.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnDateField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnDoubleField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnStringField;
import com.netsuite.webservices.v2016_2.platform.core.SearchRowBasic;


/**
 * <p>Java class for UsageSearchRowBasic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UsageSearchRowBasic"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRowBasic"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customer" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="date" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDateField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="externalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="internalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="item" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="memo" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="quantity" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnDoubleField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="subscription" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="subscriptionLine" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="subscriptionPlan" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UsageSearchRowBasic", propOrder = {
    "customer",
    "date",
    "externalId",
    "internalId",
    "item",
    "memo",
    "quantity",
    "subscription",
    "subscriptionLine",
    "subscriptionPlan"
})
public class UsageSearchRowBasic
    extends SearchRowBasic
{

    protected List<SearchColumnSelectField> customer;
    protected List<SearchColumnDateField> date;
    protected List<SearchColumnSelectField> externalId;
    protected List<SearchColumnSelectField> internalId;
    protected List<SearchColumnSelectField> item;
    protected List<SearchColumnStringField> memo;
    protected List<SearchColumnDoubleField> quantity;
    protected List<SearchColumnSelectField> subscription;
    protected List<SearchColumnSelectField> subscriptionLine;
    protected List<SearchColumnSelectField> subscriptionPlan;

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
     * Gets the value of the date property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the date property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDateField }
     * 
     * 
     */
    public List<SearchColumnDateField> getDate() {
        if (date == null) {
            date = new ArrayList<SearchColumnDateField>();
        }
        return this.date;
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
     * Gets the value of the quantity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the quantity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuantity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnDoubleField }
     * 
     * 
     */
    public List<SearchColumnDoubleField> getQuantity() {
        if (quantity == null) {
            quantity = new ArrayList<SearchColumnDoubleField>();
        }
        return this.quantity;
    }

    /**
     * Gets the value of the subscription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getSubscription() {
        if (subscription == null) {
            subscription = new ArrayList<SearchColumnSelectField>();
        }
        return this.subscription;
    }

    /**
     * Gets the value of the subscriptionLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscriptionLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscriptionLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getSubscriptionLine() {
        if (subscriptionLine == null) {
            subscriptionLine = new ArrayList<SearchColumnSelectField>();
        }
        return this.subscriptionLine;
    }

    /**
     * Gets the value of the subscriptionPlan property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscriptionPlan property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscriptionPlan().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getSubscriptionPlan() {
        if (subscriptionPlan == null) {
            subscriptionPlan = new ArrayList<SearchColumnSelectField>();
        }
        return this.subscriptionPlan;
    }

}
