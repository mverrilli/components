
package com.netsuite.webservices.v2016_2.platform.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsolidatedExchangeRateFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsolidatedExchangeRateFilter"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="period" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef"/&gt;
 *         &lt;element name="book" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="fromSubsidiary" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="toSubsidiary" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsolidatedExchangeRateFilter", propOrder = {
    "period",
    "book",
    "fromSubsidiary",
    "toSubsidiary"
})
public class ConsolidatedExchangeRateFilter {

    @XmlElement(required = true)
    protected RecordRef period;
    protected RecordRef book;
    protected RecordRef fromSubsidiary;
    protected RecordRef toSubsidiary;

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setPeriod(RecordRef value) {
        this.period = value;
    }

    /**
     * Gets the value of the book property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getBook() {
        return book;
    }

    /**
     * Sets the value of the book property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setBook(RecordRef value) {
        this.book = value;
    }

    /**
     * Gets the value of the fromSubsidiary property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getFromSubsidiary() {
        return fromSubsidiary;
    }

    /**
     * Sets the value of the fromSubsidiary property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setFromSubsidiary(RecordRef value) {
        this.fromSubsidiary = value;
    }

    /**
     * Gets the value of the toSubsidiary property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getToSubsidiary() {
        return toSubsidiary;
    }

    /**
     * Sets the value of the toSubsidiary property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setToSubsidiary(RecordRef value) {
        this.toSubsidiary = value;
    }

}
