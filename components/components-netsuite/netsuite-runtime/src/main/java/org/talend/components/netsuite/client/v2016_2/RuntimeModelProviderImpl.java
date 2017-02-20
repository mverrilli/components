package org.talend.components.netsuite.client.v2016_2;

import java.util.Arrays;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.RuntimeModelProvider;

import com.netsuite.webservices.v2016_2.platform.core.CustomRecordRef;
import com.netsuite.webservices.v2016_2.platform.core.CustomizationRef;
import com.netsuite.webservices.v2016_2.platform.core.ListOrRecordRef;
import com.netsuite.webservices.v2016_2.platform.core.Record;
import com.netsuite.webservices.v2016_2.platform.core.RecordRef;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanField;
import com.netsuite.webservices.v2016_2.platform.core.SearchCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchCustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.SearchDateCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDateField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDoubleCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDoubleField;
import com.netsuite.webservices.v2016_2.platform.core.SearchEnumMultiSelectCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchEnumMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchLongCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchLongField;
import com.netsuite.webservices.v2016_2.platform.core.SearchMultiSelectCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecord;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecordBasic;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringField;
import com.netsuite.webservices.v2016_2.platform.core.SearchTextNumberField;
import com.netsuite.webservices.v2016_2.platform.core.types.RecordType;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchDate;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchDateFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchDoubleFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchEnumMultiSelectFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchLongFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchMultiSelectFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchRecordType;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchStringFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchTextNumberFieldOperator;

/**
 *
 */
public class RuntimeModelProviderImpl extends RuntimeModelProvider {

    private static final LazyInitializer<RuntimeModelProviderImpl> initializer = new LazyInitializer<RuntimeModelProviderImpl>() {
        @Override protected RuntimeModelProviderImpl initialize() throws ConcurrentException {
            return new RuntimeModelProviderImpl();
        }
    };

    public static RuntimeModelProviderImpl getInstance() {
        try {
            return initializer.get();
        } catch (ConcurrentException e) {
            throw new NetSuiteException("Initialization error", e);
        }
    }

    private static final String[] entityTypeList = new String[] {
            "Account",
            "AccountingPeriod",
            "BillingAccount",
            "BillingSchedule",
            "Bin",
            "Budget",
            "CalendarEvent",
            "Campaign",
            "Charge",
            "Classification",
            "Contact",
            "ContactCategory",
            "ContactRole",
            "CouponCode",
            "CurrencyRate",
            "Customer",
            "CustomerCategory",
            "CustomerMessage",
            "CustomerStatus",
            "CustomList",
            "Department",
            "Employee",
            "EntityGroup",
            "ExpenseCategory",
            "FairValuePrice",
            "File",
            "Folder",
            "GiftCertificate",
            "GlobalAccountMapping",
            "InventoryNumber",
            "Issue",
            "ItemAccountMapping",
            "ItemDemandPlan",
            "ItemRevision",
            "ItemSupplyPlan",
            "Job",
            "JobStatus",
            "JobType",
            "Location",
            "ManufacturingCostTemplate",
            "ManufacturingOperationTask",
            "ManufacturingRouting",
            "Message",
            "Nexus",
            "Note",
            "NoteType",
            "OtherNameCategory",
            "Partner",
            "PartnerCategory",
            "PaymentMethod",
            "PayrollItem",
            "PhoneCall",
            "PriceLevel",
            "PricingGroup",
            "ProjectTask",
            "PromotionCode",
            "ResourceAllocation",
            "RevRecSchedule",
            "RevRecTemplate",
            "SalesRole",
            "SiteCategory",
            "Solution",
            "Subsidiary",
            "SupportCase",
            "Task",
            "Term",
            "TimeBill",
            "TimeEntry",
            "TimeSheet",
            "Topic",
            "UnitsType",
            "Usage",
            "Vendor",
            "VendorCategory",
            "WinLossReason",
    };

    private static final String[] transactionTypeList = {
            "AccountingTransaction",
            "AssemblyBuild",
            "AssemblyUnbuild",
            "BinTransfer",
            "BinWorksheet",
            "CashRefund",
            "CashSale",
            "Check",
            "CreditMemo",
            "CustomerDeposit",
            "CustomerPayment",
            "CustomerRefund",
            "Deposit",
            "DepositApplication",
            "Estimate",
            "ExpenseReport",
            "InterCompanyJournalEntry",
            "InventoryAdjustment",
            "InventoryCostRevaluation",
            "InventoryTransfer",
            "Invoice",
            "ItemFulfillment",
            "ItemReceipt",
            "JournalEntry",
            "Opportunity",
            "PaycheckJournal",
            "PurchaseOrder",
            "ReturnAuthorization",
            "SalesOrder",
            "State",
            "TransferOrder",
            "VendorBill",
            "VendorCredit",
            "VendorPayment",
            "VendorReturnAuthorization",
            "WorkOrder",
            "WorkOrderClose",
            "WorkOrderCompletion",
            "WorkOrderIssue"
    };

    private static final String[] itemTypeList = {
            "Address",
            "AssemblyItem",
            "BudgetCategory",
            "CampaignAudience",
            "CampaignCategory",
            "CampaignChannel",
            "CampaignFamily",
            "CampaignOffer",
            "CampaignResponse",
            "CampaignSearchEngine",
            "CampaignSubscription",
            "CampaignVertical",
            "CostCategory",
            "CrmCustomField",
            "Currency",
            "CustomFieldType",
            "CustomRecordCustomField",
            "CustomRecordType",
            "DescriptionItem",
            "DiscountItem",
            "DownloadItem",
            "EntityCustomField",
            "GiftCertificateItem",
            "InterCompanyTransferOrder",
            "InventoryItem",
            "ItemCustomField",
            "ItemGroup",
            "ItemNumberCustomField",
            "ItemOptionCustomField",
            "KitItem",
            "LandedCost",
            "LeadSource",
            "LotNumberedAssemblyItem",
            "LotNumberedInventoryItem",
            "MarkupItem",
            "NonInventoryPurchaseItem",
            "NonInventoryResaleItem",
            "NonInventorySaleItem",
            "OtherChargePurchaseItem",
            "OtherChargeResaleItem",
            "OtherChargeSaleItem",
            "OtherCustomField",
            "PaymentItem",
            "SalesTaxItem",
            "SerializedAssemblyItem",
            "SerializedInventoryItem",
            "ServicePurchaseItem",
            "ServiceResaleItem",
            "ServiceSaleItem",
            "StatisticalJournalEntry",
            "SubtotalItem",
            "SupportCaseIssue",
            "SupportCaseOrigin",
            "SupportCasePriority",
            "SupportCaseStatus",
            "SupportCaseType",
            "TaxAcct",
            "TaxGroup",
            "TaxType",
            "TransactionBodyCustomField",
            "TransactionColumnCustomField"
    };

    public RuntimeModelProviderImpl() {
        logger.info("Initializing standard metadata...");
        long startTime = System.currentTimeMillis();

        standardEntityTypes.addAll(Arrays.asList(entityTypeList));
        standardTransactionTypes.addAll(Arrays.asList(transactionTypeList));
        standardItemTypes.addAll(Arrays.asList(itemTypeList));

        unspecifiedRecordTypes.addAll(Arrays.asList(
                "address", "inventoryDetail", "timeEntry"
        ));
        unspecifiedRecordTypeNames.addAll(Arrays.asList(
                "Address", "InventoryDetail", "TimeEntry"
        ));
        excludedRecordTypeNames.addAll(Arrays.asList(
                "LandedCost"
        ));

        unspecifiedSearchRecordTypes.addAll(Arrays.asList(
                "address", "inventoryDetail", "timeEntry"
        ));
        excludedSearchRecordTypeNames.addAll(Arrays.asList(
                "EntitySearchBasic",
                "PricingSearchBasic",
                "OriginatingLeadSearch",
                "OriginatingLeadSearchBasic",
                "ProjectTaskAssignmentSearchBasic",
                "ItemBinNumberSearchBasic",
                "GroupMemberSearchBasic",
                "InventoryNumberBinSearchBasic"
        ));

        setRecordBaseClass(Record.class);
        setRecordTypeEnumClass(RecordType.class);

        setSearchRecordBaseClasses(Arrays.<Class<?>>asList(SearchRecord.class, SearchRecordBasic.class));
        setSearchRecordTypeEnumClass(SearchRecordType.class);

        setRecordRefClass(RecordRef.class);

        searchFieldClasses.addAll(Arrays.asList(
                SearchBooleanCustomField.class,
                SearchBooleanField.class,
                SearchCustomField.class,
                SearchDateCustomField.class,
                SearchDateField.class,
                SearchDoubleCustomField.class,
                SearchDoubleField.class,
                SearchEnumMultiSelectField.class,
                SearchEnumMultiSelectCustomField.class,
                SearchMultiSelectField.class,
                SearchLongCustomField.class,
                SearchLongField.class,
                SearchMultiSelectCustomField.class,
                SearchMultiSelectField.class,
                SearchRecord.class,
                SearchStringCustomField.class,
                SearchStringField.class,
                SearchTextNumberField.class
        ));

        searchFieldOperatorTypes.addAll(Arrays.asList(
                // Date
                ImmutablePair.<String, Class<?>>of("Date", SearchDateFieldOperator.class),
                // Predefined Date
                ImmutablePair.<String, Class<?>>of("PredefinedDate", SearchDate.class),
                // Long
                ImmutablePair.<String, Class<?>>of("Numeric", SearchLongFieldOperator.class),
                // Double
                ImmutablePair.<String, Class<?>>of("Double", SearchDoubleFieldOperator.class),
                // String
                ImmutablePair.<String, Class<?>>of("String", SearchStringFieldOperator.class),
                // Text Number
                ImmutablePair.<String, Class<?>>of("TextNumber", SearchTextNumberFieldOperator.class),
                // List of values
                ImmutablePair.<String, Class<?>>of("List", SearchMultiSelectFieldOperator.class),
                // List of predefined values
                ImmutablePair.<String, Class<?>>of("List", SearchEnumMultiSelectFieldOperator.class)
        ));

        buildModel();

        registerType(RecordRef.class, null);
        registerType(ListOrRecordRef.class, null);
        registerType(CustomRecordRef.class, null);
        registerType(CustomizationRef.class, null);
        registerType(SearchCustomFieldList.class, null);

        long endTime = System.currentTimeMillis();
        logger.info("Initialized standard metadata: " + (endTime - startTime));
    }

    public static void main(String... args) throws Exception {
        new RuntimeModelProviderImpl();
    }
}
