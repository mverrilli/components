
package com.netsuite.webservices.v2014_2.lists.relationships;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerAddressbookList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerAddressbookList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="addressbook" type="{urn:relationships_2014_2.lists.webservices.netsuite.com}CustomerAddressbook" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="replaceAll" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerAddressbookList", propOrder = {
    "addressbook"
})
public class CustomerAddressbookList {

    protected List<CustomerAddressbook> addressbook;
    @XmlAttribute(name = "replaceAll")
    protected Boolean replaceAll;

    /**
     * Gets the value of the addressbook property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressbook property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressbook().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerAddressbook }
     * 
     * 
     */
    public List<CustomerAddressbook> getAddressbook() {
        if (addressbook == null) {
            addressbook = new ArrayList<CustomerAddressbook>();
        }
        return this.addressbook;
    }

    /**
     * Gets the value of the replaceAll property.
     * This getter has been renamed from isReplaceAll() to getReplaceAll() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean getReplaceAll() {
        if (replaceAll == null) {
            return true;
        } else {
            return replaceAll;
        }
    }

    /**
     * Sets the value of the replaceAll property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReplaceAll(Boolean value) {
        this.replaceAll = value;
    }

}
