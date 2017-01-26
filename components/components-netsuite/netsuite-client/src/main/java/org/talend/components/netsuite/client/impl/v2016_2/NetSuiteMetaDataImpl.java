package org.talend.components.netsuite.client.impl.v2016_2;

import java.util.Arrays;
import java.util.Collection;

import org.talend.components.netsuite.client.NetSuiteMetaData;

import com.netsuite.webservices.v2016_2.activities.scheduling.CalendarEvent;
import com.netsuite.webservices.v2016_2.activities.scheduling.CalendarEventSearch;
import com.netsuite.webservices.v2016_2.activities.scheduling.CalendarEventSearchAdvanced;
import com.netsuite.webservices.v2016_2.activities.scheduling.PhoneCall;
import com.netsuite.webservices.v2016_2.activities.scheduling.PhoneCallSearch;
import com.netsuite.webservices.v2016_2.activities.scheduling.PhoneCallSearchAdvanced;
import com.netsuite.webservices.v2016_2.activities.scheduling.ProjectTask;
import com.netsuite.webservices.v2016_2.activities.scheduling.ProjectTaskSearch;
import com.netsuite.webservices.v2016_2.activities.scheduling.ProjectTaskSearchAdvanced;
import com.netsuite.webservices.v2016_2.activities.scheduling.ResourceAllocation;
import com.netsuite.webservices.v2016_2.activities.scheduling.ResourceAllocationSearch;
import com.netsuite.webservices.v2016_2.activities.scheduling.ResourceAllocationSearchAdvanced;
import com.netsuite.webservices.v2016_2.activities.scheduling.TaskSearch;
import com.netsuite.webservices.v2016_2.activities.scheduling.TaskSearchAdvanced;
import com.netsuite.webservices.v2016_2.documents.filecabinet.FileSearch;
import com.netsuite.webservices.v2016_2.documents.filecabinet.FileSearchAdvanced;
import com.netsuite.webservices.v2016_2.documents.filecabinet.FolderSearch;
import com.netsuite.webservices.v2016_2.documents.filecabinet.FolderSearchAdvanced;
import com.netsuite.webservices.v2016_2.general.communication.MessageSearch;
import com.netsuite.webservices.v2016_2.general.communication.MessageSearchAdvanced;
import com.netsuite.webservices.v2016_2.general.communication.NoteSearch;
import com.netsuite.webservices.v2016_2.general.communication.NoteSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.Account;
import com.netsuite.webservices.v2016_2.lists.accounting.AccountSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.AccountSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.AccountingPeriod;
import com.netsuite.webservices.v2016_2.lists.accounting.AccountingPeriodSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.AccountingPeriodSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.BillingSchedule;
import com.netsuite.webservices.v2016_2.lists.accounting.BillingScheduleSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.BillingScheduleSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.Bin;
import com.netsuite.webservices.v2016_2.lists.accounting.BinSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.BinSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.Classification;
import com.netsuite.webservices.v2016_2.lists.accounting.ClassificationSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.ClassificationSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.ContactCategory;
import com.netsuite.webservices.v2016_2.lists.accounting.ContactCategorySearch;
import com.netsuite.webservices.v2016_2.lists.accounting.ContactCategorySearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.ContactRole;
import com.netsuite.webservices.v2016_2.lists.accounting.ContactRoleSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.ContactRoleSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.CurrencyRate;
import com.netsuite.webservices.v2016_2.lists.accounting.CurrencyRateSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.CurrencyRateSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.CustomerCategory;
import com.netsuite.webservices.v2016_2.lists.accounting.CustomerCategorySearch;
import com.netsuite.webservices.v2016_2.lists.accounting.CustomerCategorySearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.CustomerMessage;
import com.netsuite.webservices.v2016_2.lists.accounting.CustomerMessageSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.CustomerMessageSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.Department;
import com.netsuite.webservices.v2016_2.lists.accounting.DepartmentSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.DepartmentSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.ExpenseCategory;
import com.netsuite.webservices.v2016_2.lists.accounting.ExpenseCategorySearch;
import com.netsuite.webservices.v2016_2.lists.accounting.ExpenseCategorySearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.GiftCertificate;
import com.netsuite.webservices.v2016_2.lists.accounting.GiftCertificateSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.GiftCertificateSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.GlobalAccountMapping;
import com.netsuite.webservices.v2016_2.lists.accounting.GlobalAccountMappingSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.GlobalAccountMappingSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.InventoryNumber;
import com.netsuite.webservices.v2016_2.lists.accounting.InventoryNumberSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.InventoryNumberSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.ItemAccountMapping;
import com.netsuite.webservices.v2016_2.lists.accounting.ItemAccountMappingSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.ItemAccountMappingSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.ItemRevision;
import com.netsuite.webservices.v2016_2.lists.accounting.ItemRevisionSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.ItemRevisionSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.LocationSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.LocationSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.Nexus;
import com.netsuite.webservices.v2016_2.lists.accounting.NexusSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.NexusSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.NoteType;
import com.netsuite.webservices.v2016_2.lists.accounting.NoteTypeSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.NoteTypeSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.OtherNameCategory;
import com.netsuite.webservices.v2016_2.lists.accounting.OtherNameCategorySearch;
import com.netsuite.webservices.v2016_2.lists.accounting.OtherNameCategorySearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.PartnerCategory;
import com.netsuite.webservices.v2016_2.lists.accounting.PartnerCategorySearch;
import com.netsuite.webservices.v2016_2.lists.accounting.PartnerCategorySearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.PaymentMethod;
import com.netsuite.webservices.v2016_2.lists.accounting.PaymentMethodSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.PaymentMethodSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.PriceLevel;
import com.netsuite.webservices.v2016_2.lists.accounting.PriceLevelSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.PriceLevelSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.PricingGroup;
import com.netsuite.webservices.v2016_2.lists.accounting.PricingGroupSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.PricingGroupSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.RevRecSchedule;
import com.netsuite.webservices.v2016_2.lists.accounting.RevRecScheduleSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.RevRecScheduleSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.RevRecTemplate;
import com.netsuite.webservices.v2016_2.lists.accounting.RevRecTemplateSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.RevRecTemplateSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.SalesRole;
import com.netsuite.webservices.v2016_2.lists.accounting.SalesRoleSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.SalesRoleSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.Subsidiary;
import com.netsuite.webservices.v2016_2.lists.accounting.SubsidiarySearch;
import com.netsuite.webservices.v2016_2.lists.accounting.SubsidiarySearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.TermSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.TermSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.UnitsType;
import com.netsuite.webservices.v2016_2.lists.accounting.UnitsTypeSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.UnitsTypeSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.VendorCategory;
import com.netsuite.webservices.v2016_2.lists.accounting.VendorCategorySearch;
import com.netsuite.webservices.v2016_2.lists.accounting.VendorCategorySearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.accounting.WinLossReason;
import com.netsuite.webservices.v2016_2.lists.accounting.WinLossReasonSearch;
import com.netsuite.webservices.v2016_2.lists.accounting.WinLossReasonSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.employees.Employee;
import com.netsuite.webservices.v2016_2.lists.employees.EmployeeSearch;
import com.netsuite.webservices.v2016_2.lists.employees.EmployeeSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.employees.PayrollItem;
import com.netsuite.webservices.v2016_2.lists.employees.PayrollItemSearch;
import com.netsuite.webservices.v2016_2.lists.employees.PayrollItemSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.marketing.Campaign;
import com.netsuite.webservices.v2016_2.lists.marketing.CampaignSearch;
import com.netsuite.webservices.v2016_2.lists.marketing.CampaignSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.marketing.CouponCode;
import com.netsuite.webservices.v2016_2.lists.marketing.CouponCodeSearch;
import com.netsuite.webservices.v2016_2.lists.marketing.CouponCodeSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.marketing.PromotionCode;
import com.netsuite.webservices.v2016_2.lists.marketing.PromotionCodeSearch;
import com.netsuite.webservices.v2016_2.lists.marketing.PromotionCodeSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.relationships.Contact;
import com.netsuite.webservices.v2016_2.lists.relationships.ContactSearch;
import com.netsuite.webservices.v2016_2.lists.relationships.ContactSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.relationships.Customer;
import com.netsuite.webservices.v2016_2.lists.relationships.CustomerSearch;
import com.netsuite.webservices.v2016_2.lists.relationships.CustomerSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.relationships.CustomerStatus;
import com.netsuite.webservices.v2016_2.lists.relationships.CustomerStatusSearch;
import com.netsuite.webservices.v2016_2.lists.relationships.CustomerStatusSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.relationships.EntityGroup;
import com.netsuite.webservices.v2016_2.lists.relationships.EntityGroupSearch;
import com.netsuite.webservices.v2016_2.lists.relationships.EntityGroupSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.relationships.JobSearch;
import com.netsuite.webservices.v2016_2.lists.relationships.JobSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.relationships.JobStatusSearch;
import com.netsuite.webservices.v2016_2.lists.relationships.JobStatusSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.relationships.JobTypeSearch;
import com.netsuite.webservices.v2016_2.lists.relationships.JobTypeSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.relationships.Partner;
import com.netsuite.webservices.v2016_2.lists.relationships.PartnerSearch;
import com.netsuite.webservices.v2016_2.lists.relationships.PartnerSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.relationships.Vendor;
import com.netsuite.webservices.v2016_2.lists.relationships.VendorSearch;
import com.netsuite.webservices.v2016_2.lists.relationships.VendorSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingCostTemplate;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingCostTemplateSearch;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingCostTemplateSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingOperationTask;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingOperationTaskSearch;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingOperationTaskSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingRouting;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingRoutingSearch;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingRoutingSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.support.Issue;
import com.netsuite.webservices.v2016_2.lists.support.IssueSearch;
import com.netsuite.webservices.v2016_2.lists.support.IssueSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.support.Solution;
import com.netsuite.webservices.v2016_2.lists.support.SolutionSearch;
import com.netsuite.webservices.v2016_2.lists.support.SolutionSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.support.SupportCase;
import com.netsuite.webservices.v2016_2.lists.support.SupportCaseSearch;
import com.netsuite.webservices.v2016_2.lists.support.SupportCaseSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.support.TopicSearch;
import com.netsuite.webservices.v2016_2.lists.support.TopicSearchAdvanced;
import com.netsuite.webservices.v2016_2.lists.website.SiteCategorySearch;
import com.netsuite.webservices.v2016_2.lists.website.SiteCategorySearchAdvanced;
import com.netsuite.webservices.v2016_2.platform.common.AccountSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.AccountingPeriodSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.BillingScheduleSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.BinSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.BudgetSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CalendarEventSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CampaignSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ChargeSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ClassificationSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ContactCategorySearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ContactRoleSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ContactSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CouponCodeSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CurrencyRateSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomListSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomRecordSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomerCategorySearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomerMessageSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomerSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomerStatusSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.DepartmentSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.EmployeeSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.EntityGroupSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ExpenseCategorySearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.FileSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.FolderSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.GiftCertificateSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.GlobalAccountMappingSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.InventoryNumberSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.IssueSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ItemAccountMappingSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ItemDemandPlanSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ItemRevisionSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ItemSupplyPlanSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.JobSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.JobStatusSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.JobTypeSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.LocationSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ManufacturingCostTemplateSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ManufacturingOperationTaskSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ManufacturingRoutingSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.MessageSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.NexusSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.NoteSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.NoteTypeSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.OtherNameCategorySearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.PartnerCategorySearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.PartnerSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.PaymentMethodSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.PayrollItemSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.PhoneCallSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.PriceLevelSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.PricingGroupSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ProjectTaskSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.PromotionCodeSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.ResourceAllocationSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.RevRecScheduleSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.RevRecTemplateSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.SalesRoleSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.SiteCategorySearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.SolutionSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.SubsidiarySearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.SupportCaseSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.TaskSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.TermSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.TimeBillSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.TimeEntrySearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.TimeSheetSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.TopicSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.TransactionSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.UnitsTypeSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.VendorCategorySearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.VendorSearchBasic;
import com.netsuite.webservices.v2016_2.platform.common.WinLossReasonSearchBasic;
import com.netsuite.webservices.v2016_2.platform.core.ListOrRecordRef;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanField;
import com.netsuite.webservices.v2016_2.platform.core.SearchCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchCustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.SearchDateCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDateField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDoubleCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDoubleField;
import com.netsuite.webservices.v2016_2.platform.core.SearchEnumMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchLongCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchLongField;
import com.netsuite.webservices.v2016_2.platform.core.SearchMultiSelectCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchRecord;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringField;
import com.netsuite.webservices.v2016_2.setup.customization.CustomList;
import com.netsuite.webservices.v2016_2.setup.customization.CustomListSearch;
import com.netsuite.webservices.v2016_2.setup.customization.CustomListSearchAdvanced;
import com.netsuite.webservices.v2016_2.setup.customization.CustomRecord;
import com.netsuite.webservices.v2016_2.setup.customization.CustomRecordSearch;
import com.netsuite.webservices.v2016_2.setup.customization.CustomRecordSearchAdvanced;
import com.netsuite.webservices.v2016_2.transactions.bank.Check;
import com.netsuite.webservices.v2016_2.transactions.bank.Deposit;
import com.netsuite.webservices.v2016_2.transactions.customers.CashRefund;
import com.netsuite.webservices.v2016_2.transactions.customers.Charge;
import com.netsuite.webservices.v2016_2.transactions.customers.ChargeSearch;
import com.netsuite.webservices.v2016_2.transactions.customers.ChargeSearchAdvanced;
import com.netsuite.webservices.v2016_2.transactions.customers.CreditMemo;
import com.netsuite.webservices.v2016_2.transactions.customers.CustomerDeposit;
import com.netsuite.webservices.v2016_2.transactions.customers.CustomerPayment;
import com.netsuite.webservices.v2016_2.transactions.customers.CustomerRefund;
import com.netsuite.webservices.v2016_2.transactions.customers.DepositApplication;
import com.netsuite.webservices.v2016_2.transactions.customers.ReturnAuthorization;
import com.netsuite.webservices.v2016_2.transactions.demandplanning.ItemDemandPlan;
import com.netsuite.webservices.v2016_2.transactions.demandplanning.ItemDemandPlanSearch;
import com.netsuite.webservices.v2016_2.transactions.demandplanning.ItemDemandPlanSearchAdvanced;
import com.netsuite.webservices.v2016_2.transactions.demandplanning.ItemSupplyPlan;
import com.netsuite.webservices.v2016_2.transactions.demandplanning.ItemSupplyPlanSearch;
import com.netsuite.webservices.v2016_2.transactions.demandplanning.ItemSupplyPlanSearchAdvanced;
import com.netsuite.webservices.v2016_2.transactions.employees.ExpenseReport;
import com.netsuite.webservices.v2016_2.transactions.employees.PaycheckJournal;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeBill;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeBillSearch;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeBillSearchAdvanced;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeEntry;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeEntrySearch;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeEntrySearchAdvanced;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeSheet;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeSheetSearch;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeSheetSearchAdvanced;
import com.netsuite.webservices.v2016_2.transactions.financial.Budget;
import com.netsuite.webservices.v2016_2.transactions.financial.BudgetSearch;
import com.netsuite.webservices.v2016_2.transactions.financial.BudgetSearchAdvanced;
import com.netsuite.webservices.v2016_2.transactions.general.InterCompanyJournalEntry;
import com.netsuite.webservices.v2016_2.transactions.general.JournalEntry;
import com.netsuite.webservices.v2016_2.transactions.inventory.AssemblyBuild;
import com.netsuite.webservices.v2016_2.transactions.inventory.AssemblyUnbuild;
import com.netsuite.webservices.v2016_2.transactions.inventory.BinTransfer;
import com.netsuite.webservices.v2016_2.transactions.inventory.BinWorksheet;
import com.netsuite.webservices.v2016_2.transactions.inventory.InventoryAdjustment;
import com.netsuite.webservices.v2016_2.transactions.inventory.InventoryCostRevaluation;
import com.netsuite.webservices.v2016_2.transactions.inventory.InventoryTransfer;
import com.netsuite.webservices.v2016_2.transactions.inventory.TransferOrder;
import com.netsuite.webservices.v2016_2.transactions.inventory.WorkOrder;
import com.netsuite.webservices.v2016_2.transactions.inventory.WorkOrderClose;
import com.netsuite.webservices.v2016_2.transactions.inventory.WorkOrderCompletion;
import com.netsuite.webservices.v2016_2.transactions.inventory.WorkOrderIssue;
import com.netsuite.webservices.v2016_2.transactions.purchases.ItemReceipt;
import com.netsuite.webservices.v2016_2.transactions.purchases.PurchaseOrder;
import com.netsuite.webservices.v2016_2.transactions.purchases.VendorBill;
import com.netsuite.webservices.v2016_2.transactions.purchases.VendorCredit;
import com.netsuite.webservices.v2016_2.transactions.purchases.VendorPayment;
import com.netsuite.webservices.v2016_2.transactions.purchases.VendorReturnAuthorization;
import com.netsuite.webservices.v2016_2.transactions.sales.CashSale;
import com.netsuite.webservices.v2016_2.transactions.sales.Estimate;
import com.netsuite.webservices.v2016_2.transactions.sales.Invoice;
import com.netsuite.webservices.v2016_2.transactions.sales.ItemFulfillment;
import com.netsuite.webservices.v2016_2.transactions.sales.Opportunity;
import com.netsuite.webservices.v2016_2.transactions.sales.SalesOrder;
import com.netsuite.webservices.v2016_2.transactions.sales.TransactionSearch;
import com.netsuite.webservices.v2016_2.transactions.sales.TransactionSearchAdvanced;

import com.netsuite.webservices.v2016_2.platform.core.types.SearchDateFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchDoubleFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchEnumMultiSelectFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchLongFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchMultiSelectFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchStringFieldOperator;

/**
 *
 */
public class NetSuiteMetaDataImpl extends NetSuiteMetaData {

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

    /**
     * "Entity class, Search class, Search Basic class, Search Advanced class"
     */
    private static final Class<?>[][] searchTable = {
            {Account.class, AccountSearch.class, AccountSearchBasic.class, AccountSearchAdvanced.class},
            {AccountingPeriod.class, AccountingPeriodSearch.class, AccountingPeriodSearchBasic.class, AccountingPeriodSearchAdvanced.class},
            {BillingSchedule.class, BillingScheduleSearch.class, BillingScheduleSearchBasic.class, BillingScheduleSearchAdvanced.class},
            {Bin.class, BinSearch.class, BinSearchBasic.class, BinSearchAdvanced.class},
            {Budget.class, BudgetSearch.class, BudgetSearchBasic.class, BudgetSearchAdvanced.class},
            {CalendarEvent.class, CalendarEventSearch.class, CalendarEventSearchBasic.class, CalendarEventSearchAdvanced.class},
            {Campaign.class, CampaignSearch.class, CampaignSearchBasic.class, CampaignSearchAdvanced.class},
            {Charge.class, ChargeSearch.class, ChargeSearchBasic.class, ChargeSearchAdvanced.class},
            {Classification.class, ClassificationSearch.class, ClassificationSearchBasic.class, ClassificationSearchAdvanced.class},
            {Contact.class, ContactSearch.class, ContactSearchBasic.class, ContactSearchAdvanced.class},
            {ContactCategory.class, ContactCategorySearch.class, ContactCategorySearchBasic.class, ContactCategorySearchAdvanced.class},
            {ContactRole.class, ContactRoleSearch.class, ContactRoleSearchBasic.class, ContactRoleSearchAdvanced.class},
            {CouponCode.class, CouponCodeSearch.class, CouponCodeSearchBasic.class, CouponCodeSearchAdvanced.class},
            {CurrencyRate.class, CurrencyRateSearch.class, CurrencyRateSearchBasic.class, CurrencyRateSearchAdvanced.class},
            {Customer.class, CustomerSearch.class, CustomerSearchBasic.class, CustomerSearchAdvanced.class},
            {CustomerCategory.class, CustomerCategorySearch.class, CustomerCategorySearchBasic.class, CustomerCategorySearchAdvanced.class},
            {CustomerMessage.class, CustomerMessageSearch.class, CustomerMessageSearchBasic.class, CustomerMessageSearchAdvanced.class},
            {CustomerStatus.class, CustomerStatusSearch.class, CustomerStatusSearchBasic.class, CustomerStatusSearchAdvanced.class},
            {CustomList.class, CustomListSearch.class, CustomListSearchBasic.class, CustomListSearchAdvanced.class},
            {Department.class, DepartmentSearch.class, DepartmentSearchBasic.class, DepartmentSearchAdvanced.class},
            {Employee.class, EmployeeSearch.class, EmployeeSearchBasic.class, EmployeeSearchAdvanced.class},
            {EntityGroup.class, EntityGroupSearch.class, EntityGroupSearchBasic.class, EntityGroupSearchAdvanced.class},
            {ExpenseCategory.class, ExpenseCategorySearch.class, ExpenseCategorySearchBasic.class, ExpenseCategorySearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.documents.filecabinet.File.class, FileSearch.class, FileSearchBasic.class, FileSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.documents.filecabinet.Folder.class, FolderSearch.class, FolderSearchBasic.class, FolderSearchAdvanced.class},
            {GiftCertificate.class, GiftCertificateSearch.class, GiftCertificateSearchBasic.class, GiftCertificateSearchAdvanced.class},
            {GlobalAccountMapping.class, GlobalAccountMappingSearch.class, GlobalAccountMappingSearchBasic.class, GlobalAccountMappingSearchAdvanced.class},
            {InventoryNumber.class, InventoryNumberSearch.class, InventoryNumberSearchBasic.class, InventoryNumberSearchAdvanced.class},
            {Issue.class, IssueSearch.class, IssueSearchBasic.class, IssueSearchAdvanced.class},
            {ItemAccountMapping.class, ItemAccountMappingSearch.class, ItemAccountMappingSearchBasic.class, ItemAccountMappingSearchAdvanced.class},
            {ItemDemandPlan.class, ItemDemandPlanSearch.class, ItemDemandPlanSearchBasic.class, ItemDemandPlanSearchAdvanced.class},
            {ItemRevision.class, ItemRevisionSearch.class, ItemRevisionSearchBasic.class, ItemRevisionSearchAdvanced.class},
            {ItemSupplyPlan.class, ItemSupplyPlanSearch.class, ItemSupplyPlanSearchBasic.class, ItemSupplyPlanSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.lists.relationships.Job.class, JobSearch.class, JobSearchBasic.class, JobSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.lists.relationships.JobStatus.class, JobStatusSearch.class, JobStatusSearchBasic.class, JobStatusSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.lists.relationships.JobType.class, JobTypeSearch.class, JobTypeSearchBasic.class, JobTypeSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.lists.accounting.Location.class, LocationSearch.class, LocationSearchBasic.class, LocationSearchAdvanced.class},
            {ManufacturingCostTemplate.class, ManufacturingCostTemplateSearch.class, ManufacturingCostTemplateSearchBasic.class, ManufacturingCostTemplateSearchAdvanced.class},
            {ManufacturingOperationTask.class, ManufacturingOperationTaskSearch.class, ManufacturingOperationTaskSearchBasic.class, ManufacturingOperationTaskSearchAdvanced.class},
            {ManufacturingRouting.class, ManufacturingRoutingSearch.class, ManufacturingRoutingSearchBasic.class, ManufacturingRoutingSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.general.communication.Message.class, MessageSearch.class, MessageSearchBasic.class, MessageSearchAdvanced.class},
            {Nexus.class, NexusSearch.class, NexusSearchBasic.class, NexusSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.general.communication.Note.class, NoteSearch.class, NoteSearchBasic.class, NoteSearchAdvanced.class},
            {NoteType.class, NoteTypeSearch.class, NoteTypeSearchBasic.class, NoteTypeSearchAdvanced.class},
            {OtherNameCategory.class, OtherNameCategorySearch.class, OtherNameCategorySearchBasic.class, OtherNameCategorySearchAdvanced.class},
            {Partner.class, PartnerSearch.class, PartnerSearchBasic.class, PartnerSearchAdvanced.class},
            {PartnerCategory.class, PartnerCategorySearch.class, PartnerCategorySearchBasic.class, PartnerCategorySearchAdvanced.class},
            {PaymentMethod.class, PaymentMethodSearch.class, PaymentMethodSearchBasic.class, PaymentMethodSearchAdvanced.class},
            {PayrollItem.class, PayrollItemSearch.class, PayrollItemSearchBasic.class, PayrollItemSearchAdvanced.class},
            {PhoneCall.class, PhoneCallSearch.class, PhoneCallSearchBasic.class, PhoneCallSearchAdvanced.class},
            {PriceLevel.class, PriceLevelSearch.class, PriceLevelSearchBasic.class, PriceLevelSearchAdvanced.class},
            {PricingGroup.class, PricingGroupSearch.class, PricingGroupSearchBasic.class, PricingGroupSearchAdvanced.class},
            {ProjectTask.class, ProjectTaskSearch.class, ProjectTaskSearchBasic.class, ProjectTaskSearchAdvanced.class},
            {PromotionCode.class, PromotionCodeSearch.class, PromotionCodeSearchBasic.class, PromotionCodeSearchAdvanced.class},
            {ResourceAllocation.class, ResourceAllocationSearch.class, ResourceAllocationSearchBasic.class, ResourceAllocationSearchAdvanced.class},
            {RevRecSchedule.class, RevRecScheduleSearch.class, RevRecScheduleSearchBasic.class, RevRecScheduleSearchAdvanced.class},
            {RevRecTemplate.class, RevRecTemplateSearch.class, RevRecTemplateSearchBasic.class, RevRecTemplateSearchAdvanced.class},
            {SalesRole.class, SalesRoleSearch.class, SalesRoleSearchBasic.class, SalesRoleSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.lists.website.SiteCategory.class, SiteCategorySearch.class, SiteCategorySearchBasic.class, SiteCategorySearchAdvanced.class},
            {Solution.class, SolutionSearch.class, SolutionSearchBasic.class, SolutionSearchAdvanced.class},
            {Subsidiary.class, SubsidiarySearch.class, SubsidiarySearchBasic.class, SubsidiarySearchAdvanced.class},
            {SupportCase.class, SupportCaseSearch.class, SupportCaseSearchBasic.class, SupportCaseSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.activities.scheduling.Task.class, TaskSearch.class, TaskSearchBasic.class, TaskSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.lists.accounting.Term.class, TermSearch.class, TermSearchBasic.class, TermSearchAdvanced.class},
            {TimeBill.class, TimeBillSearch.class, TimeBillSearchBasic.class, TimeBillSearchAdvanced.class},
            {TimeEntry.class, TimeEntrySearch.class, TimeEntrySearchBasic.class, TimeEntrySearchAdvanced.class},
            {TimeSheet.class, TimeSheetSearch.class, TimeSheetSearchBasic.class, TimeSheetSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.lists.support.Topic.class, TopicSearch.class, TopicSearchBasic.class, TopicSearchAdvanced.class},
            {UnitsType.class, UnitsTypeSearch.class, UnitsTypeSearchBasic.class, UnitsTypeSearchAdvanced.class},
            {Vendor.class, VendorSearch.class, VendorSearchBasic.class, VendorSearchAdvanced.class},
            {VendorCategory.class, VendorCategorySearch.class, VendorCategorySearchBasic.class, VendorCategorySearchAdvanced.class},
            {WinLossReason.class, WinLossReasonSearch.class, WinLossReasonSearchBasic.class, WinLossReasonSearchAdvanced.class},

            {AssemblyBuild.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {AssemblyUnbuild.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {BinTransfer.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {BinWorksheet.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {CashRefund.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {CashSale.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {Check.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {CreditMemo.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {CustomerDeposit.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {CustomerPayment.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {CustomerRefund.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {Deposit.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {DepositApplication.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {Estimate.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {ExpenseReport.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {InterCompanyJournalEntry.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {InventoryAdjustment.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {InventoryCostRevaluation.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {InventoryTransfer.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {Invoice.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {ItemFulfillment.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {ItemReceipt.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {JournalEntry.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {Opportunity.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {PaycheckJournal.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {PurchaseOrder.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {ReturnAuthorization.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {SalesOrder.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {com.netsuite.webservices.v2016_2.lists.accounting.State.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {TransferOrder.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {VendorBill.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {VendorCredit.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {VendorPayment.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {VendorReturnAuthorization.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {WorkOrder.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {WorkOrderClose.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {WorkOrderCompletion.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},
            {WorkOrderIssue.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class},

            {CustomRecord.class, CustomRecordSearch.class, CustomRecordSearchBasic.class, CustomRecordSearchAdvanced.class}
    };

    private static final Class<?>[] searchFieldTable = {
            SearchCustomFieldList.class,
            SearchBooleanCustomField.class,
            SearchBooleanField.class,
            SearchCustomField.class,
            SearchDateCustomField.class,
            SearchDateField.class,
            SearchDoubleCustomField.class,
            SearchDoubleField.class,
            SearchEnumMultiSelectField.class,
            SearchLongCustomField.class,
            SearchLongField.class,
            SearchMultiSelectCustomField.class,
            SearchMultiSelectField.class,
            SearchRecord.class,
            SearchStringCustomField.class,
            SearchStringField.class
    };
    
    private static final Class<?>[] searchFieldOperatorTable = {
            SearchDateFieldOperator.class,
            SearchDoubleFieldOperator.class,
            SearchEnumMultiSelectFieldOperator.class,
            SearchLongFieldOperator.class,
            SearchMultiSelectFieldOperator.class,
            SearchStringFieldOperator.class
    };

    public NetSuiteMetaDataImpl() {
        initMetaData(searchTable, searchFieldTable, searchFieldOperatorTable);
    }

    public Collection<String> getTransactionTypes() {
        return Arrays.asList(transactionTypeList);
    }

    public Collection<String> getItemTypes() {
        return Arrays.asList(itemTypeList);
    }

    @Override
    public Class<?> getListOrRecordRefClass() {
        return ListOrRecordRef.class;
    }

    public static void main(String... args) throws Exception {
        new NetSuiteMetaDataImpl();
    }
}
