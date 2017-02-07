
package com.netsuite.webservices.v2014_2.lists.relationships;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2014_2.platform.common.EmployeeSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.JobTypeSearchBasic;
import com.netsuite.webservices.v2014_2.platform.core.SearchRecord;


/**
 * <p>Java class for JobTypeSearch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JobTypeSearch"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2014_2.platform.webservices.netsuite.com}SearchRecord"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="basic" type="{urn:common_2014_2.platform.webservices.netsuite.com}JobTypeSearchBasic" minOccurs="0"/&gt;
 *         &lt;element name="userJoin" type="{urn:common_2014_2.platform.webservices.netsuite.com}EmployeeSearchBasic" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JobTypeSearch", propOrder = {
    "basic",
    "userJoin"
})
public class JobTypeSearch
    extends SearchRecord
{

    protected JobTypeSearchBasic basic;
    protected EmployeeSearchBasic userJoin;

    /**
     * Gets the value of the basic property.
     * 
     * @return
     *     possible object is
     *     {@link JobTypeSearchBasic }
     *     
     */
    public JobTypeSearchBasic getBasic() {
        return basic;
    }

    /**
     * Sets the value of the basic property.
     * 
     * @param value
     *     allowed object is
     *     {@link JobTypeSearchBasic }
     *     
     */
    public void setBasic(JobTypeSearchBasic value) {
        this.basic = value;
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

}
