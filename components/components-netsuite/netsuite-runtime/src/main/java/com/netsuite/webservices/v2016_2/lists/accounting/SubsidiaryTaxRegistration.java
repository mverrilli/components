
package com.netsuite.webservices.v2016_2.lists.accounting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.netsuite.webservices.v2016_2.platform.common.types.Country;
import com.netsuite.webservices.v2016_2.platform.core.RecordRef;


/**
 * <p>Java class for SubsidiaryTaxRegistration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubsidiaryTaxRegistration"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="nexusCountry" type="{urn:types.common_2016_2.platform.webservices.netsuite.com}Country" minOccurs="0"/&gt;
 *         &lt;element name="nexus" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="taxAgency" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="taxRegistrationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="taxEngine" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="effectiveFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="validUntil" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubsidiaryTaxRegistration", propOrder = {
    "id",
    "nexusCountry",
    "nexus",
    "taxAgency",
    "taxRegistrationNumber",
    "taxEngine",
    "effectiveFrom",
    "validUntil"
})
public class SubsidiaryTaxRegistration {

    protected Long id;
    @XmlSchemaType(name = "string")
    protected Country nexusCountry;
    protected RecordRef nexus;
    protected RecordRef taxAgency;
    protected String taxRegistrationNumber;
    protected RecordRef taxEngine;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar effectiveFrom;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar validUntil;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the nexusCountry property.
     * 
     * @return
     *     possible object is
     *     {@link Country }
     *     
     */
    public Country getNexusCountry() {
        return nexusCountry;
    }

    /**
     * Sets the value of the nexusCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Country }
     *     
     */
    public void setNexusCountry(Country value) {
        this.nexusCountry = value;
    }

    /**
     * Gets the value of the nexus property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getNexus() {
        return nexus;
    }

    /**
     * Sets the value of the nexus property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setNexus(RecordRef value) {
        this.nexus = value;
    }

    /**
     * Gets the value of the taxAgency property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getTaxAgency() {
        return taxAgency;
    }

    /**
     * Sets the value of the taxAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setTaxAgency(RecordRef value) {
        this.taxAgency = value;
    }

    /**
     * Gets the value of the taxRegistrationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    /**
     * Sets the value of the taxRegistrationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxRegistrationNumber(String value) {
        this.taxRegistrationNumber = value;
    }

    /**
     * Gets the value of the taxEngine property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getTaxEngine() {
        return taxEngine;
    }

    /**
     * Sets the value of the taxEngine property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setTaxEngine(RecordRef value) {
        this.taxEngine = value;
    }

    /**
     * Gets the value of the effectiveFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveFrom() {
        return effectiveFrom;
    }

    /**
     * Sets the value of the effectiveFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveFrom(XMLGregorianCalendar value) {
        this.effectiveFrom = value;
    }

    /**
     * Gets the value of the validUntil property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidUntil() {
        return validUntil;
    }

    /**
     * Sets the value of the validUntil property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidUntil(XMLGregorianCalendar value) {
        this.validUntil = value;
    }

}
