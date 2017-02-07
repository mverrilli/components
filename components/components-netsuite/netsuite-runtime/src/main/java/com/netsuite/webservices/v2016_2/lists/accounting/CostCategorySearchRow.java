
package com.netsuite.webservices.v2016_2.lists.accounting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.common.AccountSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CostCategorySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.TransactionSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.core.SearchRow;


/**
 * <p>Java class for CostCategorySearchRow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CostCategorySearchRow"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRow"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="basic" type="{urn:common_2016_2.platform.webservices.netsuite.com}CostCategorySearchRowBasic" minOccurs="0"/&gt;
 *         &lt;element name="accountJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}AccountSearchRowBasic" minOccurs="0"/&gt;
 *         &lt;element name="transactionJoin" type="{urn:common_2016_2.platform.webservices.netsuite.com}TransactionSearchRowBasic" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CostCategorySearchRow", propOrder = {
    "basic",
    "accountJoin",
    "transactionJoin"
})
public class CostCategorySearchRow
    extends SearchRow
{

    protected CostCategorySearchRowBasic basic;
    protected AccountSearchRowBasic accountJoin;
    protected TransactionSearchRowBasic transactionJoin;

    /**
     * Gets the value of the basic property.
     * 
     * @return
     *     possible object is
     *     {@link CostCategorySearchRowBasic }
     *     
     */
    public CostCategorySearchRowBasic getBasic() {
        return basic;
    }

    /**
     * Sets the value of the basic property.
     * 
     * @param value
     *     allowed object is
     *     {@link CostCategorySearchRowBasic }
     *     
     */
    public void setBasic(CostCategorySearchRowBasic value) {
        this.basic = value;
    }

    /**
     * Gets the value of the accountJoin property.
     * 
     * @return
     *     possible object is
     *     {@link AccountSearchRowBasic }
     *     
     */
    public AccountSearchRowBasic getAccountJoin() {
        return accountJoin;
    }

    /**
     * Sets the value of the accountJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountSearchRowBasic }
     *     
     */
    public void setAccountJoin(AccountSearchRowBasic value) {
        this.accountJoin = value;
    }

    /**
     * Gets the value of the transactionJoin property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionSearchRowBasic }
     *     
     */
    public TransactionSearchRowBasic getTransactionJoin() {
        return transactionJoin;
    }

    /**
     * Sets the value of the transactionJoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionSearchRowBasic }
     *     
     */
    public void setTransactionJoin(TransactionSearchRowBasic value) {
        this.transactionJoin = value;
    }

}
