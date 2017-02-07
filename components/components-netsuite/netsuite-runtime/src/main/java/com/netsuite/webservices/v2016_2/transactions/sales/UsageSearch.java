
package com.netsuite.webservices.v2016_2.transactions.sales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.common.UsageSearchBasic;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecord;


/**
 * <p>Java class for UsageSearch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UsageSearch"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRecord"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="basic" type="{urn:common_2016_2.platform.webservices.netsuite.com}UsageSearchBasic" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UsageSearch", propOrder = {
    "basic"
})
public class UsageSearch
    extends SearchRecord
{

    protected UsageSearchBasic basic;

    /**
     * Gets the value of the basic property.
     * 
     * @return
     *     possible object is
     *     {@link UsageSearchBasic }
     *     
     */
    public UsageSearchBasic getBasic() {
        return basic;
    }

    /**
     * Sets the value of the basic property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageSearchBasic }
     *     
     */
    public void setBasic(UsageSearchBasic value) {
        this.basic = value;
    }

}
