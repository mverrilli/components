
package com.netsuite.webservices.v2014_2.platform.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AsyncAddListResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AsyncAddListResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:messages_2014_2.platform.webservices.netsuite.com}AsyncResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:messages_2014_2.platform.webservices.netsuite.com}writeResponseList"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AsyncAddListResult", propOrder = {
    "writeResponseList"
})
public class AsyncAddListResult
    extends AsyncResult
{

    @XmlElement(required = true)
    protected WriteResponseList writeResponseList;

    /**
     * Gets the value of the writeResponseList property.
     * 
     * @return
     *     possible object is
     *     {@link WriteResponseList }
     *     
     */
    public WriteResponseList getWriteResponseList() {
        return writeResponseList;
    }

    /**
     * Sets the value of the writeResponseList property.
     * 
     * @param value
     *     allowed object is
     *     {@link WriteResponseList }
     *     
     */
    public void setWriteResponseList(WriteResponseList value) {
        this.writeResponseList = value;
    }

}
