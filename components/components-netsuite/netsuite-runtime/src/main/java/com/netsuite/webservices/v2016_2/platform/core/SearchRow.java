
package com.netsuite.webservices.v2016_2.platform.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.activities.scheduling.CalendarEventSearchRow;
import com.netsuite.webservices.v2016_2.activities.scheduling.PhoneCallSearchRow;
import com.netsuite.webservices.v2016_2.activities.scheduling.ProjectTaskSearchRow;
import com.netsuite.webservices.v2016_2.activities.scheduling.ResourceAllocationSearchRow;
import com.netsuite.webservices.v2016_2.activities.scheduling.TaskSearchRow;
import com.netsuite.webservices.v2016_2.documents.filecabinet.FileSearchRow;
import com.netsuite.webservices.v2016_2.documents.filecabinet.FolderSearchRow;
import com.netsuite.webservices.v2016_2.general.communication.MessageSearchRow;
import com.netsuite.webservices.v2016_2.general.communication.NoteSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.AccountSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.AccountingPeriodSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.BillingScheduleSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.BinSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.ClassificationSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.ContactCategorySearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.ContactRoleSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.CostCategorySearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.CurrencyRateSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.CustomerCategorySearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.CustomerMessageSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.DepartmentSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.ExpenseCategorySearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.FairValuePriceSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.GiftCertificateSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.GlobalAccountMappingSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.InventoryNumberSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.ItemAccountMappingSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.ItemRevisionSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.ItemSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.LocationSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.NexusSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.NoteTypeSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.OtherNameCategorySearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.PartnerCategorySearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.PaymentMethodSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.PriceLevelSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.PricingGroupSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.RevRecScheduleSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.RevRecTemplateSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.SalesRoleSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.SubsidiarySearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.TermSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.UnitsTypeSearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.VendorCategorySearchRow;
import com.netsuite.webservices.v2016_2.lists.accounting.WinLossReasonSearchRow;
import com.netsuite.webservices.v2016_2.lists.employees.EmployeeSearchRow;
import com.netsuite.webservices.v2016_2.lists.employees.PayrollItemSearchRow;
import com.netsuite.webservices.v2016_2.lists.marketing.CampaignSearchRow;
import com.netsuite.webservices.v2016_2.lists.marketing.CouponCodeSearchRow;
import com.netsuite.webservices.v2016_2.lists.marketing.PromotionCodeSearchRow;
import com.netsuite.webservices.v2016_2.lists.relationships.BillingAccountSearchRow;
import com.netsuite.webservices.v2016_2.lists.relationships.ContactSearchRow;
import com.netsuite.webservices.v2016_2.lists.relationships.CustomerSearchRow;
import com.netsuite.webservices.v2016_2.lists.relationships.CustomerStatusSearchRow;
import com.netsuite.webservices.v2016_2.lists.relationships.EntityGroupSearchRow;
import com.netsuite.webservices.v2016_2.lists.relationships.JobSearchRow;
import com.netsuite.webservices.v2016_2.lists.relationships.JobStatusSearchRow;
import com.netsuite.webservices.v2016_2.lists.relationships.JobTypeSearchRow;
import com.netsuite.webservices.v2016_2.lists.relationships.OriginatingLeadSearchRow;
import com.netsuite.webservices.v2016_2.lists.relationships.PartnerSearchRow;
import com.netsuite.webservices.v2016_2.lists.relationships.VendorSearchRow;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingCostTemplateSearchRow;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingOperationTaskSearchRow;
import com.netsuite.webservices.v2016_2.lists.supplychain.ManufacturingRoutingSearchRow;
import com.netsuite.webservices.v2016_2.lists.support.IssueSearchRow;
import com.netsuite.webservices.v2016_2.lists.support.SolutionSearchRow;
import com.netsuite.webservices.v2016_2.lists.support.SupportCaseSearchRow;
import com.netsuite.webservices.v2016_2.lists.support.TopicSearchRow;
import com.netsuite.webservices.v2016_2.lists.website.SiteCategorySearchRow;
import com.netsuite.webservices.v2016_2.setup.customization.CustomListSearchRow;
import com.netsuite.webservices.v2016_2.setup.customization.CustomRecordSearchRow;
import com.netsuite.webservices.v2016_2.transactions.customers.ChargeSearchRow;
import com.netsuite.webservices.v2016_2.transactions.demandplanning.ItemDemandPlanSearchRow;
import com.netsuite.webservices.v2016_2.transactions.demandplanning.ItemSupplyPlanSearchRow;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeBillSearchRow;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeEntrySearchRow;
import com.netsuite.webservices.v2016_2.transactions.employees.TimeSheetSearchRow;
import com.netsuite.webservices.v2016_2.transactions.financial.BudgetSearchRow;
import com.netsuite.webservices.v2016_2.transactions.sales.AccountingTransactionSearchRow;
import com.netsuite.webservices.v2016_2.transactions.sales.OpportunitySearchRow;
import com.netsuite.webservices.v2016_2.transactions.sales.TransactionSearchRow;
import com.netsuite.webservices.v2016_2.transactions.sales.UsageSearchRow;


/**
 * <p>Java class for SearchRow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchRow"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchRow")
@XmlSeeAlso({
    CampaignSearchRow.class,
    PromotionCodeSearchRow.class,
    CouponCodeSearchRow.class,
    ManufacturingCostTemplateSearchRow.class,
    ManufacturingRoutingSearchRow.class,
    ManufacturingOperationTaskSearchRow.class,
    NoteSearchRow.class,
    MessageSearchRow.class,
    FileSearchRow.class,
    FolderSearchRow.class,
    ContactSearchRow.class,
    CustomerSearchRow.class,
    PartnerSearchRow.class,
    VendorSearchRow.class,
    EntityGroupSearchRow.class,
    JobSearchRow.class,
    CustomerStatusSearchRow.class,
    JobStatusSearchRow.class,
    JobTypeSearchRow.class,
    OriginatingLeadSearchRow.class,
    BillingAccountSearchRow.class,
    CalendarEventSearchRow.class,
    TaskSearchRow.class,
    PhoneCallSearchRow.class,
    ProjectTaskSearchRow.class,
    ResourceAllocationSearchRow.class,
    CustomRecordSearchRow.class,
    CustomListSearchRow.class,
    ItemDemandPlanSearchRow.class,
    ItemSupplyPlanSearchRow.class,
    SiteCategorySearchRow.class,
    OpportunitySearchRow.class,
    TransactionSearchRow.class,
    AccountingTransactionSearchRow.class,
    UsageSearchRow.class,
    SearchRowBasic.class,
    EmployeeSearchRow.class,
    PayrollItemSearchRow.class,
    SupportCaseSearchRow.class,
    SolutionSearchRow.class,
    TopicSearchRow.class,
    IssueSearchRow.class,
    AccountSearchRow.class,
    DepartmentSearchRow.class,
    ClassificationSearchRow.class,
    LocationSearchRow.class,
    ItemSearchRow.class,
    BinSearchRow.class,
    SubsidiarySearchRow.class,
    GiftCertificateSearchRow.class,
    AccountingPeriodSearchRow.class,
    ContactCategorySearchRow.class,
    ContactRoleSearchRow.class,
    CustomerCategorySearchRow.class,
    ExpenseCategorySearchRow.class,
    NoteTypeSearchRow.class,
    PartnerCategorySearchRow.class,
    PaymentMethodSearchRow.class,
    PriceLevelSearchRow.class,
    SalesRoleSearchRow.class,
    TermSearchRow.class,
    VendorCategorySearchRow.class,
    WinLossReasonSearchRow.class,
    UnitsTypeSearchRow.class,
    PricingGroupSearchRow.class,
    InventoryNumberSearchRow.class,
    RevRecScheduleSearchRow.class,
    RevRecTemplateSearchRow.class,
    NexusSearchRow.class,
    OtherNameCategorySearchRow.class,
    CustomerMessageSearchRow.class,
    CurrencyRateSearchRow.class,
    ItemRevisionSearchRow.class,
    BillingScheduleSearchRow.class,
    GlobalAccountMappingSearchRow.class,
    ItemAccountMappingSearchRow.class,
    FairValuePriceSearchRow.class,
    CostCategorySearchRow.class,
    ChargeSearchRow.class,
    BudgetSearchRow.class,
    TimeBillSearchRow.class,
    TimeEntrySearchRow.class,
    TimeSheetSearchRow.class
})
public abstract class SearchRow {


}
