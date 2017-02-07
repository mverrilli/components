
package com.netsuite.webservices.v2016_2.platform.core.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchRecordType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SearchRecordType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="account"/&gt;
 *     &lt;enumeration value="accountingPeriod"/&gt;
 *     &lt;enumeration value="accountingTransaction"/&gt;
 *     &lt;enumeration value="billingAccount"/&gt;
 *     &lt;enumeration value="billingSchedule"/&gt;
 *     &lt;enumeration value="bin"/&gt;
 *     &lt;enumeration value="budget"/&gt;
 *     &lt;enumeration value="calendarEvent"/&gt;
 *     &lt;enumeration value="campaign"/&gt;
 *     &lt;enumeration value="charge"/&gt;
 *     &lt;enumeration value="classification"/&gt;
 *     &lt;enumeration value="contact"/&gt;
 *     &lt;enumeration value="contactCategory"/&gt;
 *     &lt;enumeration value="contactRole"/&gt;
 *     &lt;enumeration value="costCategory"/&gt;
 *     &lt;enumeration value="couponCode"/&gt;
 *     &lt;enumeration value="currencyRate"/&gt;
 *     &lt;enumeration value="customer"/&gt;
 *     &lt;enumeration value="customerCategory"/&gt;
 *     &lt;enumeration value="customerMessage"/&gt;
 *     &lt;enumeration value="customerStatus"/&gt;
 *     &lt;enumeration value="customList"/&gt;
 *     &lt;enumeration value="customRecord"/&gt;
 *     &lt;enumeration value="department"/&gt;
 *     &lt;enumeration value="employee"/&gt;
 *     &lt;enumeration value="entityGroup"/&gt;
 *     &lt;enumeration value="expenseCategory"/&gt;
 *     &lt;enumeration value="fairValuePrice"/&gt;
 *     &lt;enumeration value="file"/&gt;
 *     &lt;enumeration value="folder"/&gt;
 *     &lt;enumeration value="giftCertificate"/&gt;
 *     &lt;enumeration value="globalAccountMapping"/&gt;
 *     &lt;enumeration value="inventoryNumber"/&gt;
 *     &lt;enumeration value="item"/&gt;
 *     &lt;enumeration value="itemAccountMapping"/&gt;
 *     &lt;enumeration value="itemDemandPlan"/&gt;
 *     &lt;enumeration value="itemRevision"/&gt;
 *     &lt;enumeration value="itemSupplyPlan"/&gt;
 *     &lt;enumeration value="issue"/&gt;
 *     &lt;enumeration value="job"/&gt;
 *     &lt;enumeration value="jobStatus"/&gt;
 *     &lt;enumeration value="jobType"/&gt;
 *     &lt;enumeration value="location"/&gt;
 *     &lt;enumeration value="manufacturingCostTemplate"/&gt;
 *     &lt;enumeration value="manufacturingOperationTask"/&gt;
 *     &lt;enumeration value="manufacturingRouting"/&gt;
 *     &lt;enumeration value="message"/&gt;
 *     &lt;enumeration value="nexus"/&gt;
 *     &lt;enumeration value="note"/&gt;
 *     &lt;enumeration value="noteType"/&gt;
 *     &lt;enumeration value="opportunity"/&gt;
 *     &lt;enumeration value="otherNameCategory"/&gt;
 *     &lt;enumeration value="partner"/&gt;
 *     &lt;enumeration value="partnerCategory"/&gt;
 *     &lt;enumeration value="paymentMethod"/&gt;
 *     &lt;enumeration value="payrollItem"/&gt;
 *     &lt;enumeration value="phoneCall"/&gt;
 *     &lt;enumeration value="priceLevel"/&gt;
 *     &lt;enumeration value="pricingGroup"/&gt;
 *     &lt;enumeration value="projectTask"/&gt;
 *     &lt;enumeration value="promotionCode"/&gt;
 *     &lt;enumeration value="resourceAllocation"/&gt;
 *     &lt;enumeration value="revRecSchedule"/&gt;
 *     &lt;enumeration value="revRecTemplate"/&gt;
 *     &lt;enumeration value="salesRole"/&gt;
 *     &lt;enumeration value="solution"/&gt;
 *     &lt;enumeration value="siteCategory"/&gt;
 *     &lt;enumeration value="subsidiary"/&gt;
 *     &lt;enumeration value="supportCase"/&gt;
 *     &lt;enumeration value="task"/&gt;
 *     &lt;enumeration value="term"/&gt;
 *     &lt;enumeration value="timeBill"/&gt;
 *     &lt;enumeration value="timeSheet"/&gt;
 *     &lt;enumeration value="topic"/&gt;
 *     &lt;enumeration value="transaction"/&gt;
 *     &lt;enumeration value="unitsType"/&gt;
 *     &lt;enumeration value="usage"/&gt;
 *     &lt;enumeration value="vendor"/&gt;
 *     &lt;enumeration value="vendorCategory"/&gt;
 *     &lt;enumeration value="winLossReason"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SearchRecordType", namespace = "urn:types.core_2016_2.platform.webservices.netsuite.com")
@XmlEnum
public enum SearchRecordType {

    @XmlEnumValue("account")
    ACCOUNT("account"),
    @XmlEnumValue("accountingPeriod")
    ACCOUNTING_PERIOD("accountingPeriod"),
    @XmlEnumValue("accountingTransaction")
    ACCOUNTING_TRANSACTION("accountingTransaction"),
    @XmlEnumValue("billingAccount")
    BILLING_ACCOUNT("billingAccount"),
    @XmlEnumValue("billingSchedule")
    BILLING_SCHEDULE("billingSchedule"),
    @XmlEnumValue("bin")
    BIN("bin"),
    @XmlEnumValue("budget")
    BUDGET("budget"),
    @XmlEnumValue("calendarEvent")
    CALENDAR_EVENT("calendarEvent"),
    @XmlEnumValue("campaign")
    CAMPAIGN("campaign"),
    @XmlEnumValue("charge")
    CHARGE("charge"),
    @XmlEnumValue("classification")
    CLASSIFICATION("classification"),
    @XmlEnumValue("contact")
    CONTACT("contact"),
    @XmlEnumValue("contactCategory")
    CONTACT_CATEGORY("contactCategory"),
    @XmlEnumValue("contactRole")
    CONTACT_ROLE("contactRole"),
    @XmlEnumValue("costCategory")
    COST_CATEGORY("costCategory"),
    @XmlEnumValue("couponCode")
    COUPON_CODE("couponCode"),
    @XmlEnumValue("currencyRate")
    CURRENCY_RATE("currencyRate"),
    @XmlEnumValue("customer")
    CUSTOMER("customer"),
    @XmlEnumValue("customerCategory")
    CUSTOMER_CATEGORY("customerCategory"),
    @XmlEnumValue("customerMessage")
    CUSTOMER_MESSAGE("customerMessage"),
    @XmlEnumValue("customerStatus")
    CUSTOMER_STATUS("customerStatus"),
    @XmlEnumValue("customList")
    CUSTOM_LIST("customList"),
    @XmlEnumValue("customRecord")
    CUSTOM_RECORD("customRecord"),
    @XmlEnumValue("department")
    DEPARTMENT("department"),
    @XmlEnumValue("employee")
    EMPLOYEE("employee"),
    @XmlEnumValue("entityGroup")
    ENTITY_GROUP("entityGroup"),
    @XmlEnumValue("expenseCategory")
    EXPENSE_CATEGORY("expenseCategory"),
    @XmlEnumValue("fairValuePrice")
    FAIR_VALUE_PRICE("fairValuePrice"),
    @XmlEnumValue("file")
    FILE("file"),
    @XmlEnumValue("folder")
    FOLDER("folder"),
    @XmlEnumValue("giftCertificate")
    GIFT_CERTIFICATE("giftCertificate"),
    @XmlEnumValue("globalAccountMapping")
    GLOBAL_ACCOUNT_MAPPING("globalAccountMapping"),
    @XmlEnumValue("inventoryNumber")
    INVENTORY_NUMBER("inventoryNumber"),
    @XmlEnumValue("item")
    ITEM("item"),
    @XmlEnumValue("itemAccountMapping")
    ITEM_ACCOUNT_MAPPING("itemAccountMapping"),
    @XmlEnumValue("itemDemandPlan")
    ITEM_DEMAND_PLAN("itemDemandPlan"),
    @XmlEnumValue("itemRevision")
    ITEM_REVISION("itemRevision"),
    @XmlEnumValue("itemSupplyPlan")
    ITEM_SUPPLY_PLAN("itemSupplyPlan"),
    @XmlEnumValue("issue")
    ISSUE("issue"),
    @XmlEnumValue("job")
    JOB("job"),
    @XmlEnumValue("jobStatus")
    JOB_STATUS("jobStatus"),
    @XmlEnumValue("jobType")
    JOB_TYPE("jobType"),
    @XmlEnumValue("location")
    LOCATION("location"),
    @XmlEnumValue("manufacturingCostTemplate")
    MANUFACTURING_COST_TEMPLATE("manufacturingCostTemplate"),
    @XmlEnumValue("manufacturingOperationTask")
    MANUFACTURING_OPERATION_TASK("manufacturingOperationTask"),
    @XmlEnumValue("manufacturingRouting")
    MANUFACTURING_ROUTING("manufacturingRouting"),
    @XmlEnumValue("message")
    MESSAGE("message"),
    @XmlEnumValue("nexus")
    NEXUS("nexus"),
    @XmlEnumValue("note")
    NOTE("note"),
    @XmlEnumValue("noteType")
    NOTE_TYPE("noteType"),
    @XmlEnumValue("opportunity")
    OPPORTUNITY("opportunity"),
    @XmlEnumValue("otherNameCategory")
    OTHER_NAME_CATEGORY("otherNameCategory"),
    @XmlEnumValue("partner")
    PARTNER("partner"),
    @XmlEnumValue("partnerCategory")
    PARTNER_CATEGORY("partnerCategory"),
    @XmlEnumValue("paymentMethod")
    PAYMENT_METHOD("paymentMethod"),
    @XmlEnumValue("payrollItem")
    PAYROLL_ITEM("payrollItem"),
    @XmlEnumValue("phoneCall")
    PHONE_CALL("phoneCall"),
    @XmlEnumValue("priceLevel")
    PRICE_LEVEL("priceLevel"),
    @XmlEnumValue("pricingGroup")
    PRICING_GROUP("pricingGroup"),
    @XmlEnumValue("projectTask")
    PROJECT_TASK("projectTask"),
    @XmlEnumValue("promotionCode")
    PROMOTION_CODE("promotionCode"),
    @XmlEnumValue("resourceAllocation")
    RESOURCE_ALLOCATION("resourceAllocation"),
    @XmlEnumValue("revRecSchedule")
    REV_REC_SCHEDULE("revRecSchedule"),
    @XmlEnumValue("revRecTemplate")
    REV_REC_TEMPLATE("revRecTemplate"),
    @XmlEnumValue("salesRole")
    SALES_ROLE("salesRole"),
    @XmlEnumValue("solution")
    SOLUTION("solution"),
    @XmlEnumValue("siteCategory")
    SITE_CATEGORY("siteCategory"),
    @XmlEnumValue("subsidiary")
    SUBSIDIARY("subsidiary"),
    @XmlEnumValue("supportCase")
    SUPPORT_CASE("supportCase"),
    @XmlEnumValue("task")
    TASK("task"),
    @XmlEnumValue("term")
    TERM("term"),
    @XmlEnumValue("timeBill")
    TIME_BILL("timeBill"),
    @XmlEnumValue("timeSheet")
    TIME_SHEET("timeSheet"),
    @XmlEnumValue("topic")
    TOPIC("topic"),
    @XmlEnumValue("transaction")
    TRANSACTION("transaction"),
    @XmlEnumValue("unitsType")
    UNITS_TYPE("unitsType"),
    @XmlEnumValue("usage")
    USAGE("usage"),
    @XmlEnumValue("vendor")
    VENDOR("vendor"),
    @XmlEnumValue("vendorCategory")
    VENDOR_CATEGORY("vendorCategory"),
    @XmlEnumValue("winLossReason")
    WIN_LOSS_REASON("winLossReason");
    private final String value;

    SearchRecordType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SearchRecordType fromValue(String v) {
        for (SearchRecordType c: SearchRecordType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
