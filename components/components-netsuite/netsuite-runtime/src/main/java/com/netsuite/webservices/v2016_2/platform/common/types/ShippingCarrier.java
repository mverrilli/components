
package com.netsuite.webservices.v2016_2.platform.common.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShippingCarrier.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ShippingCarrier"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="_fedexUspsMore"/&gt;
 *     &lt;enumeration value="_ups"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ShippingCarrier", namespace = "urn:types.common_2016_2.platform.webservices.netsuite.com")
@XmlEnum
public enum ShippingCarrier {

    @XmlEnumValue("_fedexUspsMore")
    FEDEX_USPS_MORE("_fedexUspsMore"),
    @XmlEnumValue("_ups")
    UPS("_ups");
    private final String value;

    ShippingCarrier(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ShippingCarrier fromValue(String v) {
        for (ShippingCarrier c: ShippingCarrier.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
