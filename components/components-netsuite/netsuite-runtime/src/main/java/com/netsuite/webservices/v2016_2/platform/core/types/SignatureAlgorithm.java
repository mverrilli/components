
package com.netsuite.webservices.v2016_2.platform.core.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SignatureAlgorithm.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SignatureAlgorithm"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="HMAC_SHA256"/&gt;
 *     &lt;enumeration value="HMAC_SHA1"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SignatureAlgorithm", namespace = "urn:types.core_2016_2.platform.webservices.netsuite.com")
@XmlEnum
public enum SignatureAlgorithm {

    @XmlEnumValue("HMAC_SHA256")
    HMAC_SHA_256("HMAC_SHA256"),
    @XmlEnumValue("HMAC_SHA1")
    HMAC_SHA_1("HMAC_SHA1");
    private final String value;

    SignatureAlgorithm(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SignatureAlgorithm fromValue(String v) {
        for (SignatureAlgorithm c: SignatureAlgorithm.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
