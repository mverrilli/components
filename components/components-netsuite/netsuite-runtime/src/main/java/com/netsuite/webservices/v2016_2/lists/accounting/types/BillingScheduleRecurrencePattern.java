
package com.netsuite.webservices.v2016_2.lists.accounting.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BillingScheduleRecurrencePattern.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BillingScheduleRecurrencePattern"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="_anniversaryBillDate"/&gt;
 *     &lt;enumeration value="_fixedBillDate"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "BillingScheduleRecurrencePattern", namespace = "urn:types.accounting_2016_2.lists.webservices.netsuite.com")
@XmlEnum
public enum BillingScheduleRecurrencePattern {

    @XmlEnumValue("_anniversaryBillDate")
    ANNIVERSARY_BILL_DATE("_anniversaryBillDate"),
    @XmlEnumValue("_fixedBillDate")
    FIXED_BILL_DATE("_fixedBillDate");
    private final String value;

    BillingScheduleRecurrencePattern(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BillingScheduleRecurrencePattern fromValue(String v) {
        for (BillingScheduleRecurrencePattern c: BillingScheduleRecurrencePattern.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
