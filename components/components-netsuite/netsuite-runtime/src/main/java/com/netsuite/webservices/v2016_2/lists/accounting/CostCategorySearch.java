
package com.netsuite.webservices.v2016_2.lists.accounting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.common.AccountSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CostCategorySearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.TransactionSearchBasic;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecord;


/**
 * <p>Java class for CostCategorySearch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CostCategorySearch"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRecord"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="basic" type="{urn:common_2016_2.platform.webservices.netsuite.com}CostCategorySearchBasic" minOccurs="0"/&gt;
 *         &lt;element name="accountJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}AccountSearchBasic" minOccurs="0"/&gt;
 *         &lt;element name="transactionJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}TransactionSearchBasic" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CostCategorySearch", propOrder = {
    "basic",
    "accountJoin",
    "transactionJoin"
})
public class CostCategorySearch
    extends SearchRecord
{

    protected CostCategorySearchBasic basic;
    protected AccountSearchBasic accountJoin;
    protected TransactionSearchBasic transactionJoin;

    /**
     * Gets the value of the basic property.
     * 
     * @return
     *     possible object is
     *     {@link CostCategorySearchBasic }
     *     
     */
    public CostCategorySearchBasic getBasic() {
        return basic;
    }

    /**
     * Sets the value of the basic property.
     * 
     * @param value
     *     allowed object is
     *     {@link CostCategorySearchBasic }
     *     
     */
    public void setBasic(CostCategorySearchBasic value) {
        this.basic = value;
    }

    /**
     * Gets the value of the accountJoin property.
     * 
     * @return
     *     possible object is
     *     {@link AccountSearchBasic }
     *     
     */
    public AccountSearchBasic getAccountJoin() {
        return accountJoin;
    }

    /**
     * Sets the value of the accountJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountSearchBasic }
     *     
     */
    public void setAccountJoin(AccountSearchBasic value) {
        this.accountJoin = value;
    }

    /**
     * Gets the value of the transactionJoin property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionSearchBasic }
     *     
     */
    public TransactionSearchBasic getTransactionJoin() {
        return transactionJoin;
    }

    /**
     * Sets the value of the transactionJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionSearchBasic }
     *     
     */
    public void setTransactionJoin(TransactionSearchBasic value) {
        this.transactionJoin = value;
    }

}
