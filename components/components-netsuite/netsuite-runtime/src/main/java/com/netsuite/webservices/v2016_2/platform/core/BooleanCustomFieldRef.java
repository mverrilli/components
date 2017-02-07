
package com.netsuite.webservices.v2016_2.platform.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BooleanCustomFieldRef complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BooleanCustomFieldRef"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}CustomFieldRef"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BooleanCustomFieldRef", propOrder = {
    "value"
})
public class BooleanCustomFieldRef
    extends CustomFieldRef
{

    protected boolean value;

    /**
     * Gets the value of the value property.
     * This getter has been renamed from isValue() to getValue() by cxf-xjc-boolean plugin.
     * 
     */
    public boolean getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(boolean value) {
        this.value = value;
    }

}
