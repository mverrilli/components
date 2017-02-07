
package com.netsuite.webservices.v2016_2.lists.accounting;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.common.AddressSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.EmployeeSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.LocationSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.core.SearchRow;


/**
 * <p>Java class for LocationSearchRow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocationSearchRow"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRow"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="basic" type="{urn:common_2016_2.platform.webservices.netsuite.com}LocationSearchRowBasic" minOccurs="0"/&gt;
 *         &lt;element name="addressJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}AddressSearchRowBasic" minOccurs="0"/&gt;
 *         &lt;element name="returnAddressJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}AddressSearchRowBasic" minOccurs="0"/&gt;
 *         &lt;element name="userJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}EmployeeSearchRowBasic" minOccurs="0"/&gt;
 *         &lt;element name="customSearchJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}CustomSearchRowBasic" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationSearchRow", propOrder = {
    "basic",
    "addressJoin",
    "returnAddressJoin",
    "userJoin",
    "customSearchJoin"
})
public class LocationSearchRow
    extends SearchRow
{

    protected LocationSearchRowBasic basic;
    protected AddressSearchRowBasic addressJoin;
    protected AddressSearchRowBasic returnAddressJoin;
    protected EmployeeSearchRowBasic userJoin;
    protected List<CustomSearchRowBasic> customSearchJoin;

    /**
     * Gets the value of the basic property.
     * 
     * @return
     *     possible object is
     *     {@link LocationSearchRowBasic }
     *     
     */
    public LocationSearchRowBasic getBasic() {
        return basic;
    }

    /**
     * Sets the value of the basic property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationSearchRowBasic }
     *     
     */
    public void setBasic(LocationSearchRowBasic value) {
        this.basic = value;
    }

    /**
     * Gets the value of the addressJoin property.
     * 
     * @return
     *     possible object is
     *     {@link AddressSearchRowBasic }
     *     
     */
    public AddressSearchRowBasic getAddressJoin() {
        return addressJoin;
    }

    /**
     * Sets the value of the addressJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressSearchRowBasic }
     *     
     */
    public void setAddressJoin(AddressSearchRowBasic value) {
        this.addressJoin = value;
    }

    /**
     * Gets the value of the returnAddressJoin property.
     * 
     * @return
     *     possible object is
     *     {@link AddressSearchRowBasic }
     *     
     */
    public AddressSearchRowBasic getReturnAddressJoin() {
        return returnAddressJoin;
    }

    /**
     * Sets the value of the returnAddressJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressSearchRowBasic }
     *     
     */
    public void setReturnAddressJoin(AddressSearchRowBasic value) {
        this.returnAddressJoin = value;
    }

    /**
     * Gets the value of the userJoin property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeSearchRowBasic }
     *     
     */
    public EmployeeSearchRowBasic getUserJoin() {
        return userJoin;
    }

    /**
     * Sets the value of the userJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeSearchRowBasic }
     *     
     */
    public void setUserJoin(EmployeeSearchRowBasic value) {
        this.userJoin = value;
    }

    /**
     * Gets the value of the customSearchJoin property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customSearchJoin property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomSearchJoin().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomSearchRowBasic }
     * 
     * 
     */
    public List<CustomSearchRowBasic> getCustomSearchJoin() {
        if (customSearchJoin == null) {
            customSearchJoin = new ArrayList<CustomSearchRowBasic>();
        }
        return this.customSearchJoin;
    }

}
