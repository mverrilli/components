
package com.netsuite.webservices.v2016_2.platform.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.core.Record;


/**
 * <p>Java class for AddRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="record" type="{urn:core_2016_2.platform.webservices.netsuite.com}Record"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddRequest", propOrder = {
    "record"
})
public class AddRequest {

    @XmlElement(required = true)
    protected Record record;

    /**
     * Gets the value of the record property.
     * 
     * @return
     *     possible object is
     *     {@link Record }
     *     
     */
    public Record getRecord() {
        return record;
    }

    /**
     * Sets the value of the record property.
     * 
     * @param value
     *     allowed object is
     *     {@link Record }
     *     
     */
    public void setRecord(Record value) {
        this.record = value;
    }

}
