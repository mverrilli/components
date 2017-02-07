
package com.netsuite.webservices.v2016_2.transactions.sales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.common.UsageSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.core.SearchRow;


/**
 * <p>Java class for UsageSearchRow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UsageSearchRow"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRow"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="basic" type="{urn:common_2016_2.platform.webservices.netsuite.com}UsageSearchRowBasic" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UsageSearchRow", propOrder = {
    "basic"
})
public class UsageSearchRow
    extends SearchRow
{

    protected UsageSearchRowBasic basic;

    /**
     * Gets the value of the basic property.
     * 
     * @return
     *     possible object is
     *     {@link UsageSearchRowBasic }
     *     
     */
    public UsageSearchRowBasic getBasic() {
        return basic;
    }

    /**
     * Sets the value of the basic property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageSearchRowBasic }
     *     
     */
    public void setBasic(UsageSearchRowBasic value) {
        this.basic = value;
    }

}
