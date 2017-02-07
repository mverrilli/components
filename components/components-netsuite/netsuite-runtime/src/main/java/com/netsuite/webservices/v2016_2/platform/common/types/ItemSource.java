
package com.netsuite.webservices.v2016_2.platform.common.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ItemSource.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ItemSource"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="_stock"/&gt;
 *     &lt;enumeration value="_phantom"/&gt;
 *     &lt;enumeration value="_workOrder"/&gt;
 *     &lt;enumeration value="_purchaseOrder"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ItemSource", namespace = "urn:types.common_2016_2.platform.webservices.netsuite.com")
@XmlEnum
public enum ItemSource {

    @XmlEnumValue("_stock")
    STOCK("_stock"),
    @XmlEnumValue("_phantom")
    PHANTOM("_phantom"),
    @XmlEnumValue("_workOrder")
    WORK_ORDER("_workOrder"),
    @XmlEnumValue("_purchaseOrder")
    PURCHASE_ORDER("_purchaseOrder");
    private final String value;

    ItemSource(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ItemSource fromValue(String v) {
        for (ItemSource c: ItemSource.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
