
package com.netsuite.webservices.v2014_2.platform.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2014_2.platform.faults.types.StatusDetailCodeType;
import com.netsuite.webservices.v2014_2.platform.faults.types.StatusDetailType;


/**
 * <p>Java class for StatusDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusDetail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="code" type="{urn:types.faults_2014_2.platform.webservices.netsuite.com}StatusDetailCodeType"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="type" type="{urn:types.faults_2014_2.platform.webservices.netsuite.com}StatusDetailType" default="ERROR" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusDetail", propOrder = {
    "code",
    "message"
})
public class StatusDetail {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected StatusDetailCodeType code;
    protected String message;
    @XmlAttribute(name = "type")
    protected StatusDetailType type;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link StatusDetailCodeType }
     *     
     */
    public StatusDetailCodeType getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusDetailCodeType }
     *     
     */
    public void setCode(StatusDetailCodeType value) {
        this.code = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link StatusDetailType }
     *     
     */
    public StatusDetailType getType() {
        if (type == null) {
            return StatusDetailType.ERROR;
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusDetailType }
     *     
     */
    public void setType(StatusDetailType value) {
        this.type = value;
    }

}
