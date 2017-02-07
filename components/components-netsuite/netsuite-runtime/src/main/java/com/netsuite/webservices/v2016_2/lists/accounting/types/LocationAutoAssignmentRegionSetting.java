
package com.netsuite.webservices.v2016_2.lists.accounting.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocationAutoAssignmentRegionSetting.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LocationAutoAssignmentRegionSetting"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="_allowSpecifiedRegionsOnly"/&gt;
 *     &lt;enumeration value="_allowWorldwide"/&gt;
 *     &lt;enumeration value="_allowWorldwideExceptExcludedRegions"/&gt;
 *     &lt;enumeration value="_disallow"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "LocationAutoAssignmentRegionSetting", namespace = "urn:types.accounting_2016_2.lists.webservices.netsuite.com")
@XmlEnum
public enum LocationAutoAssignmentRegionSetting {

    @XmlEnumValue("_allowSpecifiedRegionsOnly")
    ALLOW_SPECIFIED_REGIONS_ONLY("_allowSpecifiedRegionsOnly"),
    @XmlEnumValue("_allowWorldwide")
    ALLOW_WORLDWIDE("_allowWorldwide"),
    @XmlEnumValue("_allowWorldwideExceptExcludedRegions")
    ALLOW_WORLDWIDE_EXCEPT_EXCLUDED_REGIONS("_allowWorldwideExceptExcludedRegions"),
    @XmlEnumValue("_disallow")
    DISALLOW("_disallow");
    private final String value;

    LocationAutoAssignmentRegionSetting(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LocationAutoAssignmentRegionSetting fromValue(String v) {
        for (LocationAutoAssignmentRegionSetting c: LocationAutoAssignmentRegionSetting.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
