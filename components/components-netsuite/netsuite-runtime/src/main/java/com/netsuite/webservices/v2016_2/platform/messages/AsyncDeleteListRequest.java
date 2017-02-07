
package com.netsuite.webservices.v2016_2.platform.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.core.BaseRef;
import com.netsuite.webservices.v2016_2.platform.core.DeletionReason;


/**
 * <p>Java class for AsyncDeleteListRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AsyncDeleteListRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="baseRef" type="{urn:core_2016_2.platform.webservices.netsuite.com}BaseRef" maxOccurs="unbounded"/&gt;
 *         &lt;element name="deletionReason" type="{urn:core_2016_2.platform.webservices.netsuite.com}DeletionReason" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AsyncDeleteListRequest", propOrder = {
    "baseRef",
    "deletionReason"
})
public class AsyncDeleteListRequest {

    @XmlElement(required = true)
    protected List<BaseRef> baseRef;
    protected DeletionReason deletionReason;

    /**
     * Gets the value of the baseRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the baseRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBaseRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BaseRef }
     * 
     * 
     */
    public List<BaseRef> getBaseRef() {
        if (baseRef == null) {
            baseRef = new ArrayList<BaseRef>();
        }
        return this.baseRef;
    }

    /**
     * Gets the value of the deletionReason property.
     * 
     * @return
     *     possible object is
     *     {@link DeletionReason }
     *     
     */
    public DeletionReason getDeletionReason() {
        return deletionReason;
    }

    /**
     * Sets the value of the deletionReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeletionReason }
     *     
     */
    public void setDeletionReason(DeletionReason value) {
        this.deletionReason = value;
    }

}
