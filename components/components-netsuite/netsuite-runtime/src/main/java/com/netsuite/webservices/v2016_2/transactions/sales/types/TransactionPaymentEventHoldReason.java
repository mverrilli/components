
package com.netsuite.webservices.v2016_2.transactions.sales.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionPaymentEventHoldReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransactionPaymentEventHoldReason"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="_authorizationDecline"/&gt;
 *     &lt;enumeration value="_cardExpired"/&gt;
 *     &lt;enumeration value="_cardInvalid"/&gt;
 *     &lt;enumeration value="_confirmationOfTheOperationIsPending"/&gt;
 *     &lt;enumeration value="_externalFraudRejection"/&gt;
 *     &lt;enumeration value="_externalFraudReview"/&gt;
 *     &lt;enumeration value="_fatalError"/&gt;
 *     &lt;enumeration value="_forwardedToPayerAuthentication"/&gt;
 *     &lt;enumeration value="_forwardRequested"/&gt;
 *     &lt;enumeration value="_gatewayError"/&gt;
 *     &lt;enumeration value="_generalHold"/&gt;
 *     &lt;enumeration value="_generalReject"/&gt;
 *     &lt;enumeration value="_operationWasTerminated"/&gt;
 *     &lt;enumeration value="_overridenBy"/&gt;
 *     &lt;enumeration value="_partnerDecline"/&gt;
 *     &lt;enumeration value="_paymentDeviceWasPrimed"/&gt;
 *     &lt;enumeration value="_systemError"/&gt;
 *     &lt;enumeration value="_verbalAuthorizationRequested"/&gt;
 *     &lt;enumeration value="_verificationRejection"/&gt;
 *     &lt;enumeration value="_verificationRequired"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TransactionPaymentEventHoldReason", namespace = "urn:types.sales_2016_2.transactions.webservices.netsuite.com")
@XmlEnum
public enum TransactionPaymentEventHoldReason {

    @XmlEnumValue("_authorizationDecline")
    AUTHORIZATION_DECLINE("_authorizationDecline"),
    @XmlEnumValue("_cardExpired")
    CARD_EXPIRED("_cardExpired"),
    @XmlEnumValue("_cardInvalid")
    CARD_INVALID("_cardInvalid"),
    @XmlEnumValue("_confirmationOfTheOperationIsPending")
    CONFIRMATION_OF_THE_OPERATION_IS_PENDING("_confirmationOfTheOperationIsPending"),
    @XmlEnumValue("_externalFraudRejection")
    EXTERNAL_FRAUD_REJECTION("_externalFraudRejection"),
    @XmlEnumValue("_externalFraudReview")
    EXTERNAL_FRAUD_REVIEW("_externalFraudReview"),
    @XmlEnumValue("_fatalError")
    FATAL_ERROR("_fatalError"),
    @XmlEnumValue("_forwardedToPayerAuthentication")
    FORWARDED_TO_PAYER_AUTHENTICATION("_forwardedToPayerAuthentication"),
    @XmlEnumValue("_forwardRequested")
    FORWARD_REQUESTED("_forwardRequested"),
    @XmlEnumValue("_gatewayError")
    GATEWAY_ERROR("_gatewayError"),
    @XmlEnumValue("_generalHold")
    GENERAL_HOLD("_generalHold"),
    @XmlEnumValue("_generalReject")
    GENERAL_REJECT("_generalReject"),
    @XmlEnumValue("_operationWasTerminated")
    OPERATION_WAS_TERMINATED("_operationWasTerminated"),
    @XmlEnumValue("_overridenBy")
    OVERRIDEN_BY("_overridenBy"),
    @XmlEnumValue("_partnerDecline")
    PARTNER_DECLINE("_partnerDecline"),
    @XmlEnumValue("_paymentDeviceWasPrimed")
    PAYMENT_DEVICE_WAS_PRIMED("_paymentDeviceWasPrimed"),
    @XmlEnumValue("_systemError")
    SYSTEM_ERROR("_systemError"),
    @XmlEnumValue("_verbalAuthorizationRequested")
    VERBAL_AUTHORIZATION_REQUESTED("_verbalAuthorizationRequested"),
    @XmlEnumValue("_verificationRejection")
    VERIFICATION_REJECTION("_verificationRejection"),
    @XmlEnumValue("_verificationRequired")
    VERIFICATION_REQUIRED("_verificationRequired");
    private final String value;

    TransactionPaymentEventHoldReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransactionPaymentEventHoldReason fromValue(String v) {
        for (TransactionPaymentEventHoldReason c: TransactionPaymentEventHoldReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
