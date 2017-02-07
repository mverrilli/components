
package com.netsuite.webservices.v2016_2.lists.accounting;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.common.AccountSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.AddressSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomSearchJoin;
import com.netsuite.webservices.v2016_2.platform.common.EmployeeSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.SubsidiarySearchBasic;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecord;


/**
 * <p>Java class for SubsidiarySearch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubsidiarySearch"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRecord"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="basic" type="{urn:common_2016_2.platform.webservices.netsuite.com}SubsidiarySearchBasic" minOccurs="0"/&gt;
 *         &lt;element name="addressJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}AddressSearchBasic" minOccurs="0"/&gt;
 *         &lt;element name="defaultAdvanceToApplyAccountJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}AccountSearchBasic" minOccurs="0"/&gt;
 *         &lt;element name="returnAddressJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}AddressSearchBasic" minOccurs="0"/&gt;
 *         &lt;element name="shippingAddressJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}AddressSearchBasic" minOccurs="0"/&gt;
 *         &lt;element name="userJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}EmployeeSearchBasic" minOccurs="0"/&gt;
 *         &lt;element name="customSearchJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}CustomSearchJoin" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubsidiarySearch", propOrder = {
    "basic",
    "addressJoin",
    "defaultAdvanceToApplyAccountJoin",
    "returnAddressJoin",
    "shippingAddressJoin",
    "userJoin",
    "customSearchJoin"
})
public class SubsidiarySearch
    extends SearchRecord
{

    protected SubsidiarySearchBasic basic;
    protected AddressSearchBasic addressJoin;
    protected AccountSearchBasic defaultAdvanceToApplyAccountJoin;
    protected AddressSearchBasic returnAddressJoin;
    protected AddressSearchBasic shippingAddressJoin;
    protected EmployeeSearchBasic userJoin;
    protected List<CustomSearchJoin> customSearchJoin;

    /**
     * Gets the value of the basic property.
     * 
     * @return
     *     possible object is
     *     {@link SubsidiarySearchBasic }
     *     
     */
    public SubsidiarySearchBasic getBasic() {
        return basic;
    }

    /**
     * Sets the value of the basic property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubsidiarySearchBasic }
     *     
     */
    public void setBasic(SubsidiarySearchBasic value) {
        this.basic = value;
    }

    /**
     * Gets the value of the addressJoin property.
     * 
     * @return
     *     possible object is
     *     {@link AddressSearchBasic }
     *     
     */
    public AddressSearchBasic getAddressJoin() {
        return addressJoin;
    }

    /**
     * Sets the value of the addressJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressSearchBasic }
     *     
     */
    public void setAddressJoin(AddressSearchBasic value) {
        this.addressJoin = value;
    }

    /**
     * Gets the value of the defaultAdvanceToApplyAccountJoin property.
     * 
     * @return
     *     possible object is
     *     {@link AccountSearchBasic }
     *     
     */
    public AccountSearchBasic getDefaultAdvanceToApplyAccountJoin() {
        return defaultAdvanceToApplyAccountJoin;
    }

    /**
     * Sets the value of the defaultAdvanceToApplyAccountJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountSearchBasic }
     *     
     */
    public void setDefaultAdvanceToApplyAccountJoin(AccountSearchBasic value) {
        this.defaultAdvanceToApplyAccountJoin = value;
    }

    /**
     * Gets the value of the returnAddressJoin property.
     * 
     * @return
     *     possible object is
     *     {@link AddressSearchBasic }
     *     
     */
    public AddressSearchBasic getReturnAddressJoin() {
        return returnAddressJoin;
    }

    /**
     * Sets the value of the returnAddressJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressSearchBasic }
     *     
     */
    public void setReturnAddressJoin(AddressSearchBasic value) {
        this.returnAddressJoin = value;
    }

    /**
     * Gets the value of the shippingAddressJoin property.
     * 
     * @return
     *     possible object is
     *     {@link AddressSearchBasic }
     *     
     */
    public AddressSearchBasic getShippingAddressJoin() {
        return shippingAddressJoin;
    }

    /**
     * Sets the value of the shippingAddressJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressSearchBasic }
     *     
     */
    public void setShippingAddressJoin(AddressSearchBasic value) {
        this.shippingAddressJoin = value;
    }

    /**
     * Gets the value of the userJoin property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeSearchBasic }
     *     
     */
    public EmployeeSearchBasic getUserJoin() {
        return userJoin;
    }

    /**
     * Sets the value of the userJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeSearchBasic }
     *     
     */
    public void setUserJoin(EmployeeSearchBasic value) {
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
     * {@link CustomSearchJoin }
     * 
     * 
     */
    public List<CustomSearchJoin> getCustomSearchJoin() {
        if (customSearchJoin == null) {
            customSearchJoin = new ArrayList<CustomSearchJoin>();
        }
        return this.customSearchJoin;
    }

}
