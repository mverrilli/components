
package com.netsuite.webservices.v2016_2.platform.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeletionReason complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeletionReason"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="deletionReasonCode" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef"/&gt;
 *         &lt;element name="deletionReasonMemo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeletionReason", propOrder = {
    "deletionReasonCode",
    "deletionReasonMemo"
})
public class DeletionReason {

    @XmlElement(required = true)
    protected RecordRef deletionReasonCode;
    protected String deletionReasonMemo;

    /**
     * Gets the value of the deletionReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getDeletionReasonCode() {
        return deletionReasonCode;
    }

    /**
     * Sets the value of the deletionReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setDeletionReasonCode(RecordRef value) {
        this.deletionReasonCode = value;
    }

    /**
     * Gets the value of the deletionReasonMemo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeletionReasonMemo() {
        return deletionReasonMemo;
    }

    /**
     * Sets the value of the deletionReasonMemo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeletionReasonMemo(String value) {
        this.deletionReasonMemo = value;
    }

}
