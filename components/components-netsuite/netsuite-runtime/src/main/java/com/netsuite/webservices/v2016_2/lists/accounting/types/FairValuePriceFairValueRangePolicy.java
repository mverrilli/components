
package com.netsuite.webservices.v2016_2.lists.accounting.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FairValuePriceFairValueRangePolicy.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FairValuePriceFairValueRangePolicy"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="_boundary"/&gt;
 *     &lt;enumeration value="_fairValue"/&gt;
 *     &lt;enumeration value="_high"/&gt;
 *     &lt;enumeration value="_low"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "FairValuePriceFairValueRangePolicy", namespace = "urn:types.accounting_2016_2.lists.webservices.netsuite.com")
@XmlEnum
public enum FairValuePriceFairValueRangePolicy {

    @XmlEnumValue("_boundary")
    BOUNDARY("_boundary"),
    @XmlEnumValue("_fairValue")
    FAIR_VALUE("_fairValue"),
    @XmlEnumValue("_high")
    HIGH("_high"),
    @XmlEnumValue("_low")
    LOW("_low");
    private final String value;

    FairValuePriceFairValueRangePolicy(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FairValuePriceFairValueRangePolicy fromValue(String v) {
        for (FairValuePriceFairValueRangePolicy c: FairValuePriceFairValueRangePolicy.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
