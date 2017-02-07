
package com.netsuite.webservices.v2016_2.platform.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnBooleanField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnCustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnEnumSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchColumnStringField;
import com.netsuite.webservices.v2016_2.platform.core.SearchRowBasic;


/**
 * <p>Java class for AddressSearchRowBasic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressSearchRowBasic"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRowBasic"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="address" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="address1" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="address2" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="address3" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="addressee" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="attention" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="city" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="country" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnEnumSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="countryCode" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="externalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="internalId" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnSelectField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="override" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnBooleanField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="phone" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="state" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="zip" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnStringField" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="customFieldList" type="{urn:core_2016_2.platform.webservices.netsuite.com}SearchColumnCustomFieldList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressSearchRowBasic", propOrder = {
    "address",
    "address1",
    "address2",
    "address3",
    "addressee",
    "attention",
    "city",
    "country",
    "countryCode",
    "externalId",
    "internalId",
    "override",
    "phone",
    "state",
    "zip",
    "customFieldList"
})
public class AddressSearchRowBasic
    extends SearchRowBasic
{

    protected List<SearchColumnStringField> address;
    protected List<SearchColumnStringField> address1;
    protected List<SearchColumnStringField> address2;
    protected List<SearchColumnStringField> address3;
    protected List<SearchColumnStringField> addressee;
    protected List<SearchColumnStringField> attention;
    protected List<SearchColumnStringField> city;
    protected List<SearchColumnEnumSelectField> country;
    protected List<SearchColumnStringField> countryCode;
    protected List<SearchColumnSelectField> externalId;
    protected List<SearchColumnSelectField> internalId;
    protected List<SearchColumnBooleanField> override;
    protected List<SearchColumnStringField> phone;
    protected List<SearchColumnStringField> state;
    protected List<SearchColumnStringField> zip;
    protected SearchColumnCustomFieldList customFieldList;

    /**
     * Gets the value of the address property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getAddress() {
        if (address == null) {
            address = new ArrayList<SearchColumnStringField>();
        }
        return this.address;
    }

    /**
     * Gets the value of the address1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getAddress1() {
        if (address1 == null) {
            address1 = new ArrayList<SearchColumnStringField>();
        }
        return this.address1;
    }

    /**
     * Gets the value of the address2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getAddress2() {
        if (address2 == null) {
            address2 = new ArrayList<SearchColumnStringField>();
        }
        return this.address2;
    }

    /**
     * Gets the value of the address3 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address3 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress3().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getAddress3() {
        if (address3 == null) {
            address3 = new ArrayList<SearchColumnStringField>();
        }
        return this.address3;
    }

    /**
     * Gets the value of the addressee property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressee property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressee().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getAddressee() {
        if (addressee == null) {
            addressee = new ArrayList<SearchColumnStringField>();
        }
        return this.addressee;
    }

    /**
     * Gets the value of the attention property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attention property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttention().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getAttention() {
        if (attention == null) {
            attention = new ArrayList<SearchColumnStringField>();
        }
        return this.attention;
    }

    /**
     * Gets the value of the city property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the city property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getCity() {
        if (city == null) {
            city = new ArrayList<SearchColumnStringField>();
        }
        return this.city;
    }

    /**
     * Gets the value of the country property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the country property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnEnumSelectField }
     * 
     * 
     */
    public List<SearchColumnEnumSelectField> getCountry() {
        if (country == null) {
            country = new ArrayList<SearchColumnEnumSelectField>();
        }
        return this.country;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the countryCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountryCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getCountryCode() {
        if (countryCode == null) {
            countryCode = new ArrayList<SearchColumnStringField>();
        }
        return this.countryCode;
    }

    /**
     * Gets the value of the externalId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the externalId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExternalId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getExternalId() {
        if (externalId == null) {
            externalId = new ArrayList<SearchColumnSelectField>();
        }
        return this.externalId;
    }

    /**
     * Gets the value of the internalId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the internalId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInternalId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnSelectField }
     * 
     * 
     */
    public List<SearchColumnSelectField> getInternalId() {
        if (internalId == null) {
            internalId = new ArrayList<SearchColumnSelectField>();
        }
        return this.internalId;
    }

    /**
     * Gets the value of the override property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the override property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOverride().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnBooleanField }
     * 
     * 
     */
    public List<SearchColumnBooleanField> getOverride() {
        if (override == null) {
            override = new ArrayList<SearchColumnBooleanField>();
        }
        return this.override;
    }

    /**
     * Gets the value of the phone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getPhone() {
        if (phone == null) {
            phone = new ArrayList<SearchColumnStringField>();
        }
        return this.phone;
    }

    /**
     * Gets the value of the state property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the state property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getState() {
        if (state == null) {
            state = new ArrayList<SearchColumnStringField>();
        }
        return this.state;
    }

    /**
     * Gets the value of the zip property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zip property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZip().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchColumnStringField }
     * 
     * 
     */
    public List<SearchColumnStringField> getZip() {
        if (zip == null) {
            zip = new ArrayList<SearchColumnStringField>();
        }
        return this.zip;
    }

    /**
     * Gets the value of the customFieldList property.
     * 
     * @return
     *     possible object is
     *     {@link SearchColumnCustomFieldList }
     *     
     */
    public SearchColumnCustomFieldList getCustomFieldList() {
        return customFieldList;
    }

    /**
     * Sets the value of the customFieldList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchColumnCustomFieldList }
     *     
     */
    public void setCustomFieldList(SearchColumnCustomFieldList value) {
        this.customFieldList = value;
    }

}
