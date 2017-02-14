package org.talend.components.netsuite.client.metadata;

import static org.talend.components.netsuite.client.NetSuiteClientService.toInitialLower;
import static org.talend.components.netsuite.client.NetSuiteClientService.toInitialUpper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.netsuite.model.Mapper;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.model.TypeManager;

import com.google.common.reflect.ClassPath;
import com.netsuite.webservices.activities.scheduling.CalendarEventSearch;
import com.netsuite.webservices.activities.scheduling.CalendarEventSearchAdvanced;
import com.netsuite.webservices.activities.scheduling.PhoneCallSearch;
import com.netsuite.webservices.activities.scheduling.PhoneCallSearchAdvanced;
import com.netsuite.webservices.activities.scheduling.ProjectTaskSearch;
import com.netsuite.webservices.activities.scheduling.ProjectTaskSearchAdvanced;
import com.netsuite.webservices.activities.scheduling.ResourceAllocationSearch;
import com.netsuite.webservices.activities.scheduling.ResourceAllocationSearchAdvanced;
import com.netsuite.webservices.activities.scheduling.TaskSearch;
import com.netsuite.webservices.activities.scheduling.TaskSearchAdvanced;
import com.netsuite.webservices.documents.filecabinet.FileSearch;
import com.netsuite.webservices.documents.filecabinet.FileSearchAdvanced;
import com.netsuite.webservices.documents.filecabinet.FolderSearch;
import com.netsuite.webservices.documents.filecabinet.FolderSearchAdvanced;
import com.netsuite.webservices.general.communication.MessageSearch;
import com.netsuite.webservices.general.communication.MessageSearchAdvanced;
import com.netsuite.webservices.general.communication.NoteSearch;
import com.netsuite.webservices.general.communication.NoteSearchAdvanced;
import com.netsuite.webservices.lists.accounting.AccountSearch;
import com.netsuite.webservices.lists.accounting.AccountSearchAdvanced;
import com.netsuite.webservices.lists.accounting.AccountingPeriodSearch;
import com.netsuite.webservices.lists.accounting.AccountingPeriodSearchAdvanced;
import com.netsuite.webservices.lists.accounting.BillingScheduleSearch;
import com.netsuite.webservices.lists.accounting.BillingScheduleSearchAdvanced;
import com.netsuite.webservices.lists.accounting.BinSearch;
import com.netsuite.webservices.lists.accounting.BinSearchAdvanced;
import com.netsuite.webservices.lists.accounting.ClassificationSearch;
import com.netsuite.webservices.lists.accounting.ClassificationSearchAdvanced;
import com.netsuite.webservices.lists.accounting.ContactCategorySearch;
import com.netsuite.webservices.lists.accounting.ContactCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.ContactRoleSearch;
import com.netsuite.webservices.lists.accounting.ContactRoleSearchAdvanced;
import com.netsuite.webservices.lists.accounting.CurrencyRateSearch;
import com.netsuite.webservices.lists.accounting.CurrencyRateSearchAdvanced;
import com.netsuite.webservices.lists.accounting.CustomerCategorySearch;
import com.netsuite.webservices.lists.accounting.CustomerCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.CustomerMessageSearch;
import com.netsuite.webservices.lists.accounting.CustomerMessageSearchAdvanced;
import com.netsuite.webservices.lists.accounting.DepartmentSearch;
import com.netsuite.webservices.lists.accounting.DepartmentSearchAdvanced;
import com.netsuite.webservices.lists.accounting.ExpenseCategorySearch;
import com.netsuite.webservices.lists.accounting.ExpenseCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.GiftCertificateSearch;
import com.netsuite.webservices.lists.accounting.GiftCertificateSearchAdvanced;
import com.netsuite.webservices.lists.accounting.GlobalAccountMappingSearch;
import com.netsuite.webservices.lists.accounting.GlobalAccountMappingSearchAdvanced;
import com.netsuite.webservices.lists.accounting.InventoryNumberSearch;
import com.netsuite.webservices.lists.accounting.InventoryNumberSearchAdvanced;
import com.netsuite.webservices.lists.accounting.ItemAccountMappingSearch;
import com.netsuite.webservices.lists.accounting.ItemAccountMappingSearchAdvanced;
import com.netsuite.webservices.lists.accounting.ItemRevisionSearch;
import com.netsuite.webservices.lists.accounting.ItemRevisionSearchAdvanced;
import com.netsuite.webservices.lists.accounting.ItemSearch;
import com.netsuite.webservices.lists.accounting.ItemSearchAdvanced;
import com.netsuite.webservices.lists.accounting.LocationSearch;
import com.netsuite.webservices.lists.accounting.LocationSearchAdvanced;
import com.netsuite.webservices.lists.accounting.NexusSearch;
import com.netsuite.webservices.lists.accounting.NexusSearchAdvanced;
import com.netsuite.webservices.lists.accounting.NoteTypeSearch;
import com.netsuite.webservices.lists.accounting.NoteTypeSearchAdvanced;
import com.netsuite.webservices.lists.accounting.OtherNameCategorySearch;
import com.netsuite.webservices.lists.accounting.OtherNameCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.PartnerCategorySearch;
import com.netsuite.webservices.lists.accounting.PartnerCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.PaymentMethodSearch;
import com.netsuite.webservices.lists.accounting.PaymentMethodSearchAdvanced;
import com.netsuite.webservices.lists.accounting.PriceLevelSearch;
import com.netsuite.webservices.lists.accounting.PriceLevelSearchAdvanced;
import com.netsuite.webservices.lists.accounting.PricingGroupSearch;
import com.netsuite.webservices.lists.accounting.PricingGroupSearchAdvanced;
import com.netsuite.webservices.lists.accounting.RevRecScheduleSearch;
import com.netsuite.webservices.lists.accounting.RevRecScheduleSearchAdvanced;
import com.netsuite.webservices.lists.accounting.RevRecTemplateSearch;
import com.netsuite.webservices.lists.accounting.RevRecTemplateSearchAdvanced;
import com.netsuite.webservices.lists.accounting.SalesRoleSearch;
import com.netsuite.webservices.lists.accounting.SalesRoleSearchAdvanced;
import com.netsuite.webservices.lists.accounting.SubsidiarySearch;
import com.netsuite.webservices.lists.accounting.SubsidiarySearchAdvanced;
import com.netsuite.webservices.lists.accounting.TermSearch;
import com.netsuite.webservices.lists.accounting.TermSearchAdvanced;
import com.netsuite.webservices.lists.accounting.UnitsTypeSearch;
import com.netsuite.webservices.lists.accounting.UnitsTypeSearchAdvanced;
import com.netsuite.webservices.lists.accounting.VendorCategorySearch;
import com.netsuite.webservices.lists.accounting.VendorCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.WinLossReasonSearch;
import com.netsuite.webservices.lists.accounting.WinLossReasonSearchAdvanced;
import com.netsuite.webservices.lists.employees.EmployeeSearch;
import com.netsuite.webservices.lists.employees.EmployeeSearchAdvanced;
import com.netsuite.webservices.lists.employees.PayrollItemSearch;
import com.netsuite.webservices.lists.employees.PayrollItemSearchAdvanced;
import com.netsuite.webservices.lists.marketing.CampaignSearch;
import com.netsuite.webservices.lists.marketing.CampaignSearchAdvanced;
import com.netsuite.webservices.lists.marketing.CouponCodeSearch;
import com.netsuite.webservices.lists.marketing.CouponCodeSearchAdvanced;
import com.netsuite.webservices.lists.marketing.PromotionCodeSearch;
import com.netsuite.webservices.lists.marketing.PromotionCodeSearchAdvanced;
import com.netsuite.webservices.lists.relationships.ContactSearch;
import com.netsuite.webservices.lists.relationships.ContactSearchAdvanced;
import com.netsuite.webservices.lists.relationships.CustomerSearch;
import com.netsuite.webservices.lists.relationships.CustomerSearchAdvanced;
import com.netsuite.webservices.lists.relationships.CustomerStatusSearch;
import com.netsuite.webservices.lists.relationships.CustomerStatusSearchAdvanced;
import com.netsuite.webservices.lists.relationships.EntityGroupSearch;
import com.netsuite.webservices.lists.relationships.EntityGroupSearchAdvanced;
import com.netsuite.webservices.lists.relationships.JobSearch;
import com.netsuite.webservices.lists.relationships.JobSearchAdvanced;
import com.netsuite.webservices.lists.relationships.JobStatusSearch;
import com.netsuite.webservices.lists.relationships.JobStatusSearchAdvanced;
import com.netsuite.webservices.lists.relationships.JobTypeSearch;
import com.netsuite.webservices.lists.relationships.JobTypeSearchAdvanced;
import com.netsuite.webservices.lists.relationships.PartnerSearch;
import com.netsuite.webservices.lists.relationships.PartnerSearchAdvanced;
import com.netsuite.webservices.lists.relationships.VendorSearch;
import com.netsuite.webservices.lists.relationships.VendorSearchAdvanced;
import com.netsuite.webservices.lists.supplychain.ManufacturingCostTemplateSearch;
import com.netsuite.webservices.lists.supplychain.ManufacturingCostTemplateSearchAdvanced;
import com.netsuite.webservices.lists.supplychain.ManufacturingOperationTaskSearch;
import com.netsuite.webservices.lists.supplychain.ManufacturingOperationTaskSearchAdvanced;
import com.netsuite.webservices.lists.supplychain.ManufacturingRoutingSearch;
import com.netsuite.webservices.lists.supplychain.ManufacturingRoutingSearchAdvanced;
import com.netsuite.webservices.lists.support.IssueSearch;
import com.netsuite.webservices.lists.support.IssueSearchAdvanced;
import com.netsuite.webservices.lists.support.SolutionSearch;
import com.netsuite.webservices.lists.support.SolutionSearchAdvanced;
import com.netsuite.webservices.lists.support.SupportCaseSearch;
import com.netsuite.webservices.lists.support.SupportCaseSearchAdvanced;
import com.netsuite.webservices.lists.support.TopicSearch;
import com.netsuite.webservices.lists.support.TopicSearchAdvanced;
import com.netsuite.webservices.lists.website.SiteCategorySearch;
import com.netsuite.webservices.lists.website.SiteCategorySearchAdvanced;
import com.netsuite.webservices.platform.common.AccountSearchBasic;
import com.netsuite.webservices.platform.common.AccountingPeriodSearchBasic;
import com.netsuite.webservices.platform.common.AccountingTransactionSearchBasic;
import com.netsuite.webservices.platform.common.BillingScheduleSearchBasic;
import com.netsuite.webservices.platform.common.BinSearchBasic;
import com.netsuite.webservices.platform.common.BudgetSearchBasic;
import com.netsuite.webservices.platform.common.CalendarEventSearchBasic;
import com.netsuite.webservices.platform.common.CampaignSearchBasic;
import com.netsuite.webservices.platform.common.ChargeSearchBasic;
import com.netsuite.webservices.platform.common.ClassificationSearchBasic;
import com.netsuite.webservices.platform.common.ContactCategorySearchBasic;
import com.netsuite.webservices.platform.common.ContactRoleSearchBasic;
import com.netsuite.webservices.platform.common.ContactSearchBasic;
import com.netsuite.webservices.platform.common.CouponCodeSearchBasic;
import com.netsuite.webservices.platform.common.CurrencyRateSearchBasic;
import com.netsuite.webservices.platform.common.CustomListSearchBasic;
import com.netsuite.webservices.platform.common.CustomRecordSearchBasic;
import com.netsuite.webservices.platform.common.CustomerCategorySearchBasic;
import com.netsuite.webservices.platform.common.CustomerMessageSearchBasic;
import com.netsuite.webservices.platform.common.CustomerSearchBasic;
import com.netsuite.webservices.platform.common.CustomerStatusSearchBasic;
import com.netsuite.webservices.platform.common.DepartmentSearchBasic;
import com.netsuite.webservices.platform.common.EmployeeSearchBasic;
import com.netsuite.webservices.platform.common.EntityGroupSearchBasic;
import com.netsuite.webservices.platform.common.ExpenseCategorySearchBasic;
import com.netsuite.webservices.platform.common.FileSearchBasic;
import com.netsuite.webservices.platform.common.FolderSearchBasic;
import com.netsuite.webservices.platform.common.GiftCertificateSearchBasic;
import com.netsuite.webservices.platform.common.GlobalAccountMappingSearchBasic;
import com.netsuite.webservices.platform.common.InventoryNumberSearchBasic;
import com.netsuite.webservices.platform.common.IssueSearchBasic;
import com.netsuite.webservices.platform.common.ItemAccountMappingSearchBasic;
import com.netsuite.webservices.platform.common.ItemDemandPlanSearchBasic;
import com.netsuite.webservices.platform.common.ItemRevisionSearchBasic;
import com.netsuite.webservices.platform.common.ItemSearchBasic;
import com.netsuite.webservices.platform.common.ItemSupplyPlanSearchBasic;
import com.netsuite.webservices.platform.common.JobSearchBasic;
import com.netsuite.webservices.platform.common.JobStatusSearchBasic;
import com.netsuite.webservices.platform.common.JobTypeSearchBasic;
import com.netsuite.webservices.platform.common.LocationSearchBasic;
import com.netsuite.webservices.platform.common.ManufacturingCostTemplateSearchBasic;
import com.netsuite.webservices.platform.common.ManufacturingOperationTaskSearchBasic;
import com.netsuite.webservices.platform.common.ManufacturingRoutingSearchBasic;
import com.netsuite.webservices.platform.common.MessageSearchBasic;
import com.netsuite.webservices.platform.common.NexusSearchBasic;
import com.netsuite.webservices.platform.common.NoteSearchBasic;
import com.netsuite.webservices.platform.common.NoteTypeSearchBasic;
import com.netsuite.webservices.platform.common.OtherNameCategorySearchBasic;
import com.netsuite.webservices.platform.common.PartnerCategorySearchBasic;
import com.netsuite.webservices.platform.common.PartnerSearchBasic;
import com.netsuite.webservices.platform.common.PaymentMethodSearchBasic;
import com.netsuite.webservices.platform.common.PayrollItemSearchBasic;
import com.netsuite.webservices.platform.common.PhoneCallSearchBasic;
import com.netsuite.webservices.platform.common.PriceLevelSearchBasic;
import com.netsuite.webservices.platform.common.PricingGroupSearchBasic;
import com.netsuite.webservices.platform.common.ProjectTaskSearchBasic;
import com.netsuite.webservices.platform.common.PromotionCodeSearchBasic;
import com.netsuite.webservices.platform.common.ResourceAllocationSearchBasic;
import com.netsuite.webservices.platform.common.RevRecScheduleSearchBasic;
import com.netsuite.webservices.platform.common.RevRecTemplateSearchBasic;
import com.netsuite.webservices.platform.common.SalesRoleSearchBasic;
import com.netsuite.webservices.platform.common.SiteCategorySearchBasic;
import com.netsuite.webservices.platform.common.SolutionSearchBasic;
import com.netsuite.webservices.platform.common.SubsidiarySearchBasic;
import com.netsuite.webservices.platform.common.SupportCaseSearchBasic;
import com.netsuite.webservices.platform.common.TaskSearchBasic;
import com.netsuite.webservices.platform.common.TermSearchBasic;
import com.netsuite.webservices.platform.common.TimeBillSearchBasic;
import com.netsuite.webservices.platform.common.TimeSheetSearchBasic;
import com.netsuite.webservices.platform.common.TopicSearchBasic;
import com.netsuite.webservices.platform.common.TransactionSearchBasic;
import com.netsuite.webservices.platform.common.UnitsTypeSearchBasic;
import com.netsuite.webservices.platform.common.VendorCategorySearchBasic;
import com.netsuite.webservices.platform.common.VendorSearchBasic;
import com.netsuite.webservices.platform.common.WinLossReasonSearchBasic;
import com.netsuite.webservices.platform.core.Record;
import com.netsuite.webservices.platform.core.RecordRef;
import com.netsuite.webservices.platform.core.SearchBooleanCustomField;
import com.netsuite.webservices.platform.core.SearchBooleanField;
import com.netsuite.webservices.platform.core.SearchCustomField;
import com.netsuite.webservices.platform.core.SearchCustomFieldList;
import com.netsuite.webservices.platform.core.SearchDateCustomField;
import com.netsuite.webservices.platform.core.SearchDateField;
import com.netsuite.webservices.platform.core.SearchDoubleCustomField;
import com.netsuite.webservices.platform.core.SearchDoubleField;
import com.netsuite.webservices.platform.core.SearchEnumMultiSelectCustomField;
import com.netsuite.webservices.platform.core.SearchEnumMultiSelectField;
import com.netsuite.webservices.platform.core.SearchLongCustomField;
import com.netsuite.webservices.platform.core.SearchLongField;
import com.netsuite.webservices.platform.core.SearchMultiSelectCustomField;
import com.netsuite.webservices.platform.core.SearchMultiSelectField;
import com.netsuite.webservices.platform.core.SearchRecord;
import com.netsuite.webservices.platform.core.SearchStringCustomField;
import com.netsuite.webservices.platform.core.SearchStringField;
import com.netsuite.webservices.platform.core.types.RecordType;
import com.netsuite.webservices.platform.core.types.SearchDate;
import com.netsuite.webservices.platform.core.types.SearchDateFieldOperator;
import com.netsuite.webservices.platform.core.types.SearchDoubleFieldOperator;
import com.netsuite.webservices.platform.core.types.SearchEnumMultiSelectFieldOperator;
import com.netsuite.webservices.platform.core.types.SearchLongFieldOperator;
import com.netsuite.webservices.platform.core.types.SearchMultiSelectFieldOperator;
import com.netsuite.webservices.platform.core.types.SearchRecordType;
import com.netsuite.webservices.platform.core.types.SearchStringFieldOperator;
import com.netsuite.webservices.setup.customization.CustomListSearch;
import com.netsuite.webservices.setup.customization.CustomListSearchAdvanced;
import com.netsuite.webservices.setup.customization.CustomRecordSearch;
import com.netsuite.webservices.setup.customization.CustomRecordSearchAdvanced;
import com.netsuite.webservices.transactions.customers.ChargeSearch;
import com.netsuite.webservices.transactions.customers.ChargeSearchAdvanced;
import com.netsuite.webservices.transactions.demandplanning.ItemDemandPlanSearch;
import com.netsuite.webservices.transactions.demandplanning.ItemDemandPlanSearchAdvanced;
import com.netsuite.webservices.transactions.demandplanning.ItemSupplyPlanSearch;
import com.netsuite.webservices.transactions.demandplanning.ItemSupplyPlanSearchAdvanced;
import com.netsuite.webservices.transactions.employees.TimeBillSearch;
import com.netsuite.webservices.transactions.employees.TimeBillSearchAdvanced;
import com.netsuite.webservices.transactions.employees.TimeSheetSearch;
import com.netsuite.webservices.transactions.employees.TimeSheetSearchAdvanced;
import com.netsuite.webservices.transactions.financial.BudgetSearch;
import com.netsuite.webservices.transactions.financial.BudgetSearchAdvanced;
import com.netsuite.webservices.transactions.sales.AccountingTransactionSearch;
import com.netsuite.webservices.transactions.sales.AccountingTransactionSearchAdvanced;
import com.netsuite.webservices.transactions.sales.TransactionSearch;
import com.netsuite.webservices.transactions.sales.TransactionSearchAdvanced;

//import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
//import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

/**
 *
 */
public class StandardMetaData {

    private transient static final Logger LOG = LoggerFactory.getLogger(StandardMetaData.class);

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
            "Vendor",
            "VendorCategory",
            "WinLossReason",
    };

    private static final String[] transactionTypeList = {
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

    private Set<String> standardEntityTypes = new HashSet<>();
    private Set<String> standardTransactionTypes = new HashSet<>();
    private Set<String> standardItemTypes = new HashSet<>();

    private Map<String, TypeDef> typeMap = new HashMap<>();

    private Map<String, RecordTypeDef> recordTypeDefMap = new HashMap<>();

    private Map<SearchRecordType, SearchRecordDef> searchRecordDefMap = new HashMap<>();
    private Map<RecordType, SearchRecordType> recordSearchTypeMap = new HashMap<>();
    private Map<String, Class<?>> searchFieldMap = new HashMap<>();
    private Map<String, SearchFieldOperatorTypeDef> searchFieldOperatorTypeMap = new HashMap<>();
    private Map<String, String> searchFieldOperatorMap = new HashMap<>();

    public StandardMetaData() {
        initMetaData();
    }

    private void initMetaData() {
        for (String type : entityTypeList) {
            standardEntityTypes.add(type);
        }
        for (String type : transactionTypeList) {
            standardTransactionTypes.add(type);
        }
        for (String type : itemTypeList) {
            standardItemTypes.add(type);
        }

//        FastClasspathScanner scanner = new FastClasspathScanner("com.netsuite.webservices");
//        ScanResult scanResult = scanner.scan();

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            Set<String> excludedTypeNames = new HashSet<>(Arrays.asList(
                    "LandedCost", "Address", "InventoryDetail", "TimeEntry", "CustomFieldType"
            ));
            Set<String> unresolvedTypeNames = new HashSet<>();
            for (final ClassPath.ClassInfo info : ClassPath.from(loader)
                    .getTopLevelClassesRecursive("com.netsuite.webservices")) {
                final Class<?> clazz = info.load();
//            for (String className : scanResult.getNamesOfSubclassesOf(Record.class)) {
//                Class<?> clazz = Class.forName(className);
                String recordTypeName = clazz.getSimpleName();
                RecordType recordType = null;
                if (!excludedTypeNames.contains(recordTypeName)) {
                    try {
                        recordType = RecordType.fromValue(toInitialLower(recordTypeName));
                    } catch (IllegalArgumentException e) {
                        unresolvedTypeNames.add(recordTypeName);
                    }
                }
                RecordTypeDef def = new RecordTypeDef(recordTypeName, recordType, clazz);
                recordTypeDefMap.put(recordTypeName, def);

                registerType(clazz, recordTypeName);
            }
            System.out.println("Unresolved types: " + unresolvedTypeNames);
        } catch (IOException e) {
            LOG.error("Class not found: " + e.getMessage(), e);
        }

//        try {
//            for (String className : scanResult.getNamesOfSubclassesOf(SearchRecord.class)) {
//                Class<?> searchRecordClass = Class.forName(className);
//                String searchRecordTypeName = searchRecordClass.getSimpleName();
//                registerType(searchRecordClass, searchRecordTypeName);
//            }
//        } catch (ClassNotFoundException e) {
//            LOG.error("Class not found: " + e.getMessage(), e);
//        }

        SearchRecordDef[] searchRecordDefs = {
                new SearchRecordDef(SearchRecordType.ACCOUNT, AccountSearch.class, AccountSearchBasic.class, AccountSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.ACCOUNTING_PERIOD, AccountingPeriodSearch.class, AccountingPeriodSearchBasic.class, AccountingPeriodSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.ACCOUNTING_TRANSACTION, AccountingTransactionSearch.class, AccountingTransactionSearchBasic.class, AccountingTransactionSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.BILLING_SCHEDULE, BillingScheduleSearch.class, BillingScheduleSearchBasic.class, BillingScheduleSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.BIN, BinSearch.class, BinSearchBasic.class, BinSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.BUDGET, BudgetSearch.class, BudgetSearchBasic.class, BudgetSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CALENDAR_EVENT, CalendarEventSearch.class, CalendarEventSearchBasic.class, CalendarEventSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CAMPAIGN, CampaignSearch.class, CampaignSearchBasic.class, CampaignSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CHARGE, ChargeSearch.class, ChargeSearchBasic.class, ChargeSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CLASSIFICATION, ClassificationSearch.class, ClassificationSearchBasic.class, ClassificationSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CONTACT, ContactSearch.class, ContactSearchBasic.class, ContactSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CONTACT_CATEGORY, ContactCategorySearch.class, ContactCategorySearchBasic.class, ContactCategorySearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CONTACT_ROLE, ContactRoleSearch.class, ContactRoleSearchBasic.class, ContactRoleSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.COUPON_CODE, CouponCodeSearch.class, CouponCodeSearchBasic.class, CouponCodeSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CURRENCY_RATE, CurrencyRateSearch.class, CurrencyRateSearchBasic.class, CurrencyRateSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CUSTOMER, CustomerSearch.class, CustomerSearchBasic.class, CustomerSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CUSTOMER_CATEGORY, CustomerCategorySearch.class, CustomerCategorySearchBasic.class, CustomerCategorySearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CUSTOMER_MESSAGE, CustomerMessageSearch.class, CustomerMessageSearchBasic.class, CustomerMessageSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CUSTOMER_STATUS, CustomerStatusSearch.class, CustomerStatusSearchBasic.class, CustomerStatusSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.DEPARTMENT, DepartmentSearch.class, DepartmentSearchBasic.class, DepartmentSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.EMPLOYEE, EmployeeSearch.class, EmployeeSearchBasic.class, EmployeeSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.ENTITY_GROUP, EntityGroupSearch.class, EntityGroupSearchBasic.class, EntityGroupSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.EXPENSE_CATEGORY, ExpenseCategorySearch.class, ExpenseCategorySearchBasic.class, ExpenseCategorySearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.FILE, FileSearch.class, FileSearchBasic.class, FileSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.FOLDER, FolderSearch.class, FolderSearchBasic.class, FolderSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.GIFT_CERTIFICATE, GiftCertificateSearch.class, GiftCertificateSearchBasic.class, GiftCertificateSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.GLOBAL_ACCOUNT_MAPPING, GlobalAccountMappingSearch.class, GlobalAccountMappingSearchBasic.class, GlobalAccountMappingSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.INVENTORY_NUMBER, InventoryNumberSearch.class, InventoryNumberSearchBasic.class, InventoryNumberSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.ISSUE, IssueSearch.class, IssueSearchBasic.class, IssueSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.ITEM_ACCOUNT_MAPPING, ItemAccountMappingSearch.class, ItemAccountMappingSearchBasic.class, ItemAccountMappingSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.ITEM_DEMAND_PLAN, ItemDemandPlanSearch.class, ItemDemandPlanSearchBasic.class, ItemDemandPlanSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.ITEM_REVISION, ItemRevisionSearch.class, ItemRevisionSearchBasic.class, ItemRevisionSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.ITEM_SUPPLY_PLAN, ItemSupplyPlanSearch.class, ItemSupplyPlanSearchBasic.class, ItemSupplyPlanSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.JOB, JobSearch.class, JobSearchBasic.class, JobSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.JOB_STATUS, JobStatusSearch.class, JobStatusSearchBasic.class, JobStatusSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.JOB_TYPE, JobTypeSearch.class, JobTypeSearchBasic.class, JobTypeSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.LOCATION, LocationSearch.class, LocationSearchBasic.class, LocationSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.MANUFACTURING_COST_TEMPLATE, ManufacturingCostTemplateSearch.class, ManufacturingCostTemplateSearchBasic.class, ManufacturingCostTemplateSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.MANUFACTURING_OPERATION_TASK, ManufacturingOperationTaskSearch.class, ManufacturingOperationTaskSearchBasic.class, ManufacturingOperationTaskSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.MANUFACTURING_ROUTING, ManufacturingRoutingSearch.class, ManufacturingRoutingSearchBasic.class, ManufacturingRoutingSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.MESSAGE, MessageSearch.class, MessageSearchBasic.class, MessageSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.NEXUS, NexusSearch.class, NexusSearchBasic.class, NexusSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.NOTE, NoteSearch.class, NoteSearchBasic.class, NoteSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.NOTE_TYPE, NoteTypeSearch.class, NoteTypeSearchBasic.class, NoteTypeSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.OTHER_NAME_CATEGORY, OtherNameCategorySearch.class, OtherNameCategorySearchBasic.class, OtherNameCategorySearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.PARTNER, PartnerSearch.class, PartnerSearchBasic.class, PartnerSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.PARTNER_CATEGORY, PartnerCategorySearch.class, PartnerCategorySearchBasic.class, PartnerCategorySearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.PAYMENT_METHOD, PaymentMethodSearch.class, PaymentMethodSearchBasic.class, PaymentMethodSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.PAYROLL_ITEM, PayrollItemSearch.class, PayrollItemSearchBasic.class, PayrollItemSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.PHONE_CALL, PhoneCallSearch.class, PhoneCallSearchBasic.class, PhoneCallSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.PRICE_LEVEL, PriceLevelSearch.class, PriceLevelSearchBasic.class, PriceLevelSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.PRICING_GROUP, PricingGroupSearch.class, PricingGroupSearchBasic.class, PricingGroupSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.PROJECT_TASK, ProjectTaskSearch.class, ProjectTaskSearchBasic.class, ProjectTaskSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.PROMOTION_CODE, PromotionCodeSearch.class, PromotionCodeSearchBasic.class, PromotionCodeSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.RESOURCE_ALLOCATION, ResourceAllocationSearch.class, ResourceAllocationSearchBasic.class, ResourceAllocationSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.REV_REC_SCHEDULE, RevRecScheduleSearch.class, RevRecScheduleSearchBasic.class, RevRecScheduleSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.REV_REC_TEMPLATE, RevRecTemplateSearch.class, RevRecTemplateSearchBasic.class, RevRecTemplateSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.SALES_ROLE, SalesRoleSearch.class, SalesRoleSearchBasic.class, SalesRoleSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.SITE_CATEGORY, SiteCategorySearch.class, SiteCategorySearchBasic.class, SiteCategorySearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.SOLUTION, SolutionSearch.class, SolutionSearchBasic.class, SolutionSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.SUBSIDIARY, SubsidiarySearch.class, SubsidiarySearchBasic.class, SubsidiarySearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.SUPPORT_CASE, SupportCaseSearch.class, SupportCaseSearchBasic.class, SupportCaseSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.TASK, TaskSearch.class, TaskSearchBasic.class, TaskSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.TERM, TermSearch.class, TermSearchBasic.class, TermSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.TIME_BILL, TimeBillSearch.class, TimeBillSearchBasic.class, TimeBillSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.TIME_SHEET, TimeSheetSearch.class, TimeSheetSearchBasic.class, TimeSheetSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.TOPIC, TopicSearch.class, TopicSearchBasic.class, TopicSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.UNITS_TYPE, UnitsTypeSearch.class, UnitsTypeSearchBasic.class, UnitsTypeSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.VENDOR, VendorSearch.class, VendorSearchBasic.class, VendorSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.VENDOR_CATEGORY, VendorCategorySearch.class, VendorCategorySearchBasic.class, VendorCategorySearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.WIN_LOSS_REASON, WinLossReasonSearch.class, WinLossReasonSearchBasic.class, WinLossReasonSearchAdvanced.class),

                new SearchRecordDef(SearchRecordType.CUSTOM_LIST, CustomListSearch.class, CustomListSearchBasic.class, CustomListSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.CUSTOM_RECORD, CustomRecordSearch.class, CustomRecordSearchBasic.class, CustomRecordSearchAdvanced.class),

                new SearchRecordDef(SearchRecordType.TRANSACTION, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new SearchRecordDef(SearchRecordType.ITEM, ItemSearch.class, ItemSearchBasic.class, ItemSearchAdvanced.class),
        };
        registerSearchRecordDefs(searchRecordDefs);

        for (RecordType recordType : RecordType.values()) {
            String recordTypeName = recordType.value();
            String recordTypeNameCapitalized = toInitialUpper(recordType.value());
            if (standardEntityTypes.contains(recordTypeNameCapitalized)) {
                try {
                    SearchRecordType searchRecordType = SearchRecordType.fromValue(recordTypeName);
                    recordSearchTypeMap.put(recordType, searchRecordType);
                } catch (IllegalArgumentException e) {
                    LOG.error("Invalid entity record type: '" + recordTypeName + "'");
                }
            } else if (standardTransactionTypes.contains(recordTypeNameCapitalized)) {
                recordSearchTypeMap.put(recordType, SearchRecordType.TRANSACTION);
            } else if (standardItemTypes.contains(recordTypeNameCapitalized)) {
                recordSearchTypeMap.put(recordType, SearchRecordType.ITEM);
            } else {
                LOG.error("Search record type not found for '" + recordTypeName + "'");
            }
        }

        Class<?>[] searchFieldTable = {
                SearchCustomFieldList.class,
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
                SearchStringField.class
        };
        registerSearchFieldDefs(searchFieldTable);

        SearchFieldOperatorTypeDef<?>[] searchFieldOperatorTable = {
                // Date
                new SearchFieldOperatorTypeDef<>("Date",
                        SearchDateFieldOperator.class, new Mapper<SearchDateFieldOperator, String>() {
                    @Override public String map(SearchDateFieldOperator input) {
                        return input.value();
                    }
                }, new Mapper<String, SearchDateFieldOperator>() {
                    @Override public SearchDateFieldOperator map(String input) {
                        return SearchDateFieldOperator.fromValue(input);
                    }
                }),
                // Predefined Date
                new SearchFieldOperatorTypeDef<>("PredefinedDate",
                        SearchDate.class, new Mapper<SearchDate, String>() {
                    @Override public String map(SearchDate input) {
                        return input.value();
                    }
                }, new Mapper<String, SearchDate>() {
                    @Override public SearchDate map(String input) {
                        return SearchDate.fromValue(input);
                    }
                }),
                // Double
                new SearchFieldOperatorTypeDef<>("Double",
                        SearchDoubleFieldOperator.class, new Mapper<SearchDoubleFieldOperator, String>() {
                    @Override public String map(SearchDoubleFieldOperator input) {
                        return input.value();
                    }
                }, new Mapper<String, SearchDoubleFieldOperator>() {
                    @Override public SearchDoubleFieldOperator map(String input) {
                        return SearchDoubleFieldOperator.fromValue(input);
                    }
                }),
                // Date
                new SearchFieldOperatorTypeDef<>("Numeric",
                        SearchLongFieldOperator.class, new Mapper<SearchLongFieldOperator, String>() {
                    @Override public String map(SearchLongFieldOperator input) {
                        return input.value();
                    }
                }, new Mapper<String, SearchLongFieldOperator>() {
                    @Override public SearchLongFieldOperator map(String input) {
                        return SearchLongFieldOperator.fromValue(input);
                    }
                }),
                // String
                new SearchFieldOperatorTypeDef<>("String",
                        SearchStringFieldOperator.class, new Mapper<SearchStringFieldOperator, String>() {
                    @Override public String map(SearchStringFieldOperator input) {
                        return input.value();
                    }
                }, new Mapper<String, SearchStringFieldOperator>() {
                    @Override public SearchStringFieldOperator map(String input) {
                        return SearchStringFieldOperator.fromValue(input);
                    }
                }),
                // List of values
                new SearchFieldOperatorTypeDef<>("List",
                        SearchMultiSelectFieldOperator.class, new Mapper<SearchMultiSelectFieldOperator, String>() {
                    @Override public String map(SearchMultiSelectFieldOperator input) {
                        return input.value();
                    }
                }, new Mapper<String, SearchMultiSelectFieldOperator>() {
                    @Override public SearchMultiSelectFieldOperator map(String input) {
                        return SearchMultiSelectFieldOperator.fromValue(input);
                    }
                }),
                // List of predefined values
                new SearchFieldOperatorTypeDef<>("List",
                        SearchEnumMultiSelectFieldOperator.class, new Mapper<SearchEnumMultiSelectFieldOperator, String>() {
                    @Override public String map(SearchEnumMultiSelectFieldOperator input) {
                        return input.value();
                    }
                }, new Mapper<String, SearchEnumMultiSelectFieldOperator>() {
                    @Override public SearchEnumMultiSelectFieldOperator map(String input) {
                        return SearchEnumMultiSelectFieldOperator.fromValue(input);
                    }
                }),
                // Boolean (Synthetic)
                new SearchFieldOperatorTypeDef<>("Boolean",
                        SearchFieldOperatorTypeDef.SearchBooleanFieldOperator.class, null, null)
        };
        registerSearchFieldOperatorTypeDefs(searchFieldOperatorTable);

    }

    protected void registerType(Class<?> typeClass, String typeName) {
        String typeNameToRegister = typeName != null ? typeName : typeClass.getSimpleName();
        if (typeMap.containsKey(typeNameToRegister)) {
            TypeDef entityInfo = typeMap.get(typeNameToRegister);
            if (entityInfo.getTypeClass() == typeClass) {
                return;
            } else {
                throw new IllegalArgumentException("Type already registered: " +
                        typeNameToRegister + ", class to register is " +
                        typeClass + ", registered class is " +
                        typeMap.get(typeNameToRegister));
            }
        }

        TypeInfo beanInfo = TypeManager.forClass(typeClass);
        List<PropertyInfo> propertyInfos = beanInfo.getProperties();
        List<FieldDef> fields = new ArrayList<>(propertyInfos.size());
        for (PropertyInfo propertyInfo : propertyInfos) {
            String fieldName = propertyInfo.getName();
            Class fieldValueType = propertyInfo.getReadType();
            if ((fieldName.equals("class") && fieldValueType == Class.class) ||
                    (fieldName.equals("nullFieldList") && fieldValueType.getSimpleName().equals("NullField"))) {
                continue;
            }
            boolean isKeyField = isKeyField(typeClass, propertyInfo);
            FieldDef fieldInfo = new FieldDef(fieldName, fieldValueType, isKeyField, true);
            fields.add(fieldInfo);
        }

        TypeDef entityInfo = new TypeDef(typeName, typeClass, fields);
        typeMap.put(typeNameToRegister, entityInfo);
    }

    protected void registerSearchRecordDefs(SearchRecordDef[] searchRecordDefs) {
        for (SearchRecordDef def : searchRecordDefs) {

            registerType(def.getSearchClass(), null);
            registerType(def.getSearchBasicClass(), null);
            registerType(def.getSearchAdvancedClass(), null);

            if (searchRecordDefMap.containsKey(def.getSearchRecordType())) {
                throw new IllegalArgumentException(
                        "Search record def already registered: "
                                + def.getSearchRecordType() + ", search classes to register are "
                                + def.getSearchClass() + ", "
                                + def.getSearchBasicClass() + ", "
                                + def.getSearchAdvancedClass());
            }
            searchRecordDefMap.put(def.getSearchRecordType(), def);
        }
    }

    protected void registerSearchFieldDefs(Class<?>[] searchFieldTable) {
        searchFieldMap = new HashMap<>(searchFieldTable.length);
        for (Class<?> entry : searchFieldTable) {
            searchFieldMap.put(entry.getSimpleName(), entry);
        }
    }

    protected void registerSearchFieldOperatorTypeDefs(SearchFieldOperatorTypeDef[] searchFieldOperatorTable) {
        searchFieldOperatorTypeMap = new HashMap<>(searchFieldOperatorTable.length);
        for (SearchFieldOperatorTypeDef info : searchFieldOperatorTable) {
            searchFieldOperatorTypeMap.put(info.getTypeName(), info);
        }

        searchFieldOperatorMap.put("SearchMultiSelectField", "SearchMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchMultiSelectCustomField", "SearchMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchEnumMultiSelectField", "SearchEnumMultiSelectFieldOperator");
        searchFieldOperatorMap.put("SearchEnumMultiSelectCustomField", "SearchEnumMultiSelectFieldOperator");
    }

    public Collection<String> getTransactionTypes() {
        return Arrays.asList(transactionTypeList);
    }

    public Collection<String> getItemTypes() {
        return Collections.unmodifiableCollection(standardItemTypes);
    }

    public TypeDef getTypeDef(String typeName) {
        return typeMap.get(typeName);
    }

    public TypeDef getTypeDef(Class<?> clazz) {
        return typeMap.get(clazz.getSimpleName());
    }

    public Collection<String> getRecordTypes() {
        return Collections.unmodifiableCollection(recordTypeDefMap.keySet());
    }

    public RecordTypeDef getRecordTypeDef(String typeName) {
        return recordTypeDefMap.get(typeName);
    }

    public SearchRecordDef getSearchRecordDef(String typeName) {
        RecordTypeDef recordTypeDef = getRecordTypeDef(typeName);
        SearchRecordType searchRecordType = recordSearchTypeMap.get(recordTypeDef.getRecordType());
        return searchRecordDefMap.get(searchRecordType);
    }

    public Class<?> getSearchFieldClass(String searchFieldType) {
        return searchFieldMap.get(searchFieldType);
    }

    public Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName) {
        SearchFieldOperatorTypeDef.QualifiedName operatorQName =
                new SearchFieldOperatorTypeDef.QualifiedName(searchFieldOperatorName);
        String searchFieldOperatorType = searchFieldOperatorMap.get(searchFieldType);
        if (searchFieldOperatorType != null) {
            SearchFieldOperatorTypeDef def = searchFieldOperatorTypeMap.get(searchFieldOperatorType);
            return def.getOperator(searchFieldOperatorName);
        }
        for (SearchFieldOperatorTypeDef def : searchFieldOperatorTypeMap.values()) {
            if (def.hasOperatorName(operatorQName)) {
                return def.getOperator(searchFieldOperatorName);
            }
        }
        return null;
    }

    public Collection<SearchFieldOperatorTypeDef.QualifiedName> getSearchOperatorNames() {
        Set<SearchFieldOperatorTypeDef.QualifiedName> names = new HashSet<>();
        for (SearchFieldOperatorTypeDef info : searchFieldOperatorTypeMap.values()) {
            names.addAll(info.getOperatorNames());
        }
        return Collections.unmodifiableSet(names);
    }

    protected boolean isKeyField(Class<?> entityClass, PropertyInfo propertyInfo) {
        if (Record.class.isAssignableFrom(entityClass) &&
                (propertyInfo.getName().equals("internalId") || propertyInfo.getName().equals("externalId"))) {
            return true;
        }
        if (RecordRef.class.isAssignableFrom(entityClass) &&
                (propertyInfo.getName().equals("internalId") || propertyInfo.getName().equals("externalId"))) {
            return true;
        }
        return false;
    }

    public static void main(String... args) throws Exception {
        new StandardMetaData();
    }
}
