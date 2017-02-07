
package com.netsuite.webservices.v2016_2.lists.accounting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.common.types.Language;
import com.netsuite.webservices.v2016_2.platform.core.RecordRef;


/**
 * <p>Java class for AccountLocalizations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountLocalizations"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accountingContext" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="acctNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acctName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="locale" type="{urn:types.common_2016_2.platform.webservices.netsuite.com}Language" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountLocalizations", propOrder = {
    "accountingContext",
    "acctNumber",
    "acctName",
    "locale"
})
public class AccountLocalizations {

    protected RecordRef accountingContext;
    protected String acctNumber;
    protected String acctName;
    @XmlSchemaType(name = "string")
    protected Language locale;

    /**
     * Gets the value of the accountingContext property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getAccountingContext() {
        return accountingContext;
    }

    /**
     * Sets the value of the accountingContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setAccountingContext(RecordRef value) {
        this.accountingContext = value;
    }

    /**
     * Gets the value of the acctNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctNumber() {
        return acctNumber;
    }

    /**
     * Sets the value of the acctNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctNumber(String value) {
        this.acctNumber = value;
    }

    /**
     * Gets the value of the acctName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * Sets the value of the acctName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctName(String value) {
        this.acctName = value;
    }

    /**
     * Gets the value of the locale property.
     * 
     * @return
     *     possible object is
     *     {@link Language }
     *     
     */
    public Language getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Language }
     *     
     */
    public void setLocale(Language value) {
        this.locale = value;
    }

}
