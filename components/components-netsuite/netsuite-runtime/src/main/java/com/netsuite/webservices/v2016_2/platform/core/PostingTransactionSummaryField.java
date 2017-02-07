
package com.netsuite.webservices.v2016_2.platform.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PostingTransactionSummaryField complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PostingTransactionSummaryField"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="account" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="parentItem" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="item" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="entity" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="class" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="subsidiary" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="book" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostingTransactionSummaryField", propOrder = {
    "period",
    "account",
    "parentItem",
    "item",
    "entity",
    "department",
    "clazz",
    "location",
    "subsidiary",
    "book"
})
public class PostingTransactionSummaryField {

    protected Boolean period;
    protected Boolean account;
    protected Boolean parentItem;
    protected Boolean item;
    protected Boolean entity;
    protected Boolean department;
    @XmlElement(name = "class")
    protected Boolean clazz;
    protected Boolean location;
    protected Boolean subsidiary;
    protected Boolean book;

    /**
     * Gets the value of the period property.
     * This getter has been renamed from isPeriod() to getPeriod() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPeriod(Boolean value) {
        this.period = value;
    }

    /**
     * Gets the value of the account property.
     * This getter has been renamed from isAccount() to getAccount() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAccount(Boolean value) {
        this.account = value;
    }

    /**
     * Gets the value of the parentItem property.
     * This getter has been renamed from isParentItem() to getParentItem() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getParentItem() {
        return parentItem;
    }

    /**
     * Sets the value of the parentItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setParentItem(Boolean value) {
        this.parentItem = value;
    }

    /**
     * Gets the value of the item property.
     * This getter has been renamed from isItem() to getItem() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setItem(Boolean value) {
        this.item = value;
    }

    /**
     * Gets the value of the entity property.
     * This getter has been renamed from isEntity() to getEntity() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getEntity() {
        return entity;
    }

    /**
     * Sets the value of the entity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEntity(Boolean value) {
        this.entity = value;
    }

    /**
     * Gets the value of the department property.
     * This getter has been renamed from isDepartment() to getDepartment() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getDepartment() {
        return department;
    }

    /**
     * Sets the value of the department property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDepartment(Boolean value) {
        this.department = value;
    }

    /**
     * Gets the value of the clazz property.
     * This getter has been renamed from isClazz() to getClazz() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setClazz(Boolean value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the location property.
     * This getter has been renamed from isLocation() to getLocation() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLocation(Boolean value) {
        this.location = value;
    }

    /**
     * Gets the value of the subsidiary property.
     * This getter has been renamed from isSubsidiary() to getSubsidiary() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getSubsidiary() {
        return subsidiary;
    }

    /**
     * Sets the value of the subsidiary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSubsidiary(Boolean value) {
        this.subsidiary = value;
    }

    /**
     * Gets the value of the book property.
     * This getter has been renamed from isBook() to getBook() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getBook() {
        return book;
    }

    /**
     * Sets the value of the book property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBook(Boolean value) {
        this.book = value;
    }

}
