
package com.netsuite.webservices.v2014_2.platform.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Preferences complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Preferences"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="warningAsError" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="disableMandatoryCustomFieldValidation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="disableSystemNotesForCustomFields" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ignoreReadOnlyFields" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Preferences", propOrder = {
    "warningAsError",
    "disableMandatoryCustomFieldValidation",
    "disableSystemNotesForCustomFields",
    "ignoreReadOnlyFields"
})
public class Preferences {

    protected Boolean warningAsError;
    protected Boolean disableMandatoryCustomFieldValidation;
    protected Boolean disableSystemNotesForCustomFields;
    protected Boolean ignoreReadOnlyFields;

    /**
     * Gets the value of the warningAsError property.
     * This getter has been renamed from isWarningAsError() to getWarningAsError() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getWarningAsError() {
        return warningAsError;
    }

    /**
     * Sets the value of the warningAsError property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWarningAsError(Boolean value) {
        this.warningAsError = value;
    }

    /**
     * Gets the value of the disableMandatoryCustomFieldValidation property.
     * This getter has been renamed from isDisableMandatoryCustomFieldValidation() to getDisableMandatoryCustomFieldValidation() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getDisableMandatoryCustomFieldValidation() {
        return disableMandatoryCustomFieldValidation;
    }

    /**
     * Sets the value of the disableMandatoryCustomFieldValidation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisableMandatoryCustomFieldValidation(Boolean value) {
        this.disableMandatoryCustomFieldValidation = value;
    }

    /**
     * Gets the value of the disableSystemNotesForCustomFields property.
     * This getter has been renamed from isDisableSystemNotesForCustomFields() to getDisableSystemNotesForCustomFields() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getDisableSystemNotesForCustomFields() {
        return disableSystemNotesForCustomFields;
    }

    /**
     * Sets the value of the disableSystemNotesForCustomFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisableSystemNotesForCustomFields(Boolean value) {
        this.disableSystemNotesForCustomFields = value;
    }

    /**
     * Gets the value of the ignoreReadOnlyFields property.
     * This getter has been renamed from isIgnoreReadOnlyFields() to getIgnoreReadOnlyFields() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIgnoreReadOnlyFields() {
        return ignoreReadOnlyFields;
    }

    /**
     * Sets the value of the ignoreReadOnlyFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIgnoreReadOnlyFields(Boolean value) {
        this.ignoreReadOnlyFields = value;
    }

}
