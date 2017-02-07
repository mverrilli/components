
package com.netsuite.webservices.v2016_2.platform.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchPreferences complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchPreferences"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bodyFieldsOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="returnSearchColumns" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchPreferences", propOrder = {
    "bodyFieldsOnly",
    "returnSearchColumns",
    "pageSize"
})
public class SearchPreferences {

    @XmlElement(defaultValue = "true")
    protected Boolean bodyFieldsOnly;
    @XmlElement(defaultValue = "true")
    protected Boolean returnSearchColumns;
    protected Integer pageSize;

    /**
     * Gets the value of the bodyFieldsOnly property.
     * This getter has been renamed from isBodyFieldsOnly() to getBodyFieldsOnly() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getBodyFieldsOnly() {
        return bodyFieldsOnly;
    }

    /**
     * Sets the value of the bodyFieldsOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBodyFieldsOnly(Boolean value) {
        this.bodyFieldsOnly = value;
    }

    /**
     * Gets the value of the returnSearchColumns property.
     * This getter has been renamed from isReturnSearchColumns() to getReturnSearchColumns() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getReturnSearchColumns() {
        return returnSearchColumns;
    }

    /**
     * Sets the value of the returnSearchColumns property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnSearchColumns(Boolean value) {
        this.returnSearchColumns = value;
    }

    /**
     * Gets the value of the pageSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPageSize(Integer value) {
        this.pageSize = value;
    }

}
