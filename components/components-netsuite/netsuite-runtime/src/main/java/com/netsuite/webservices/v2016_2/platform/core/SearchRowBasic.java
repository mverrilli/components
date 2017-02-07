
package com.netsuite.webservices.v2016_2.platform.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.netsuite.webservices.v2016_2.platform.common.AccountSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.AccountingPeriodSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.AccountingTransactionSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.AddressSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.BillingAccountSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.BillingScheduleSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.BinSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.BudgetSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CalendarEventSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CampaignSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ChargeSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ClassificationSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ContactCategorySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ContactRoleSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ContactSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CostCategorySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CouponCodeSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CurrencyRateSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomListSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomRecordSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomerCategorySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomerMessageSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomerSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.CustomerStatusSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.DepartmentSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.EmployeeSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.EntityGroupSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.EntitySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ExpenseCategorySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.FairValuePriceSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.FileSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.FolderSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.GiftCertificateSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.GlobalAccountMappingSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.InventoryDetailSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.InventoryNumberBinSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.InventoryNumberSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.IssueSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ItemAccountMappingSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ItemBinNumberSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ItemDemandPlanSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ItemRevisionSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ItemSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ItemSupplyPlanSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.JobSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.JobStatusSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.JobTypeSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.LocationSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ManufacturingCostTemplateSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ManufacturingOperationTaskSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ManufacturingRoutingSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.MessageSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.NexusSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.NoteSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.NoteTypeSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.OpportunitySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.OriginatingLeadSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.OtherNameCategorySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.PartnerCategorySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.PartnerSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.PaymentMethodSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.PayrollItemSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.PhoneCallSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.PriceLevelSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.PricingGroupSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.PricingSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ProjectTaskAssignmentSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ProjectTaskSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.PromotionCodeSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.ResourceAllocationSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.RevRecScheduleSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.RevRecTemplateSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.SalesRoleSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.SiteCategorySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.SolutionSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.SubsidiarySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.SupportCaseSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.TaskSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.TermSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.TimeBillSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.TimeEntrySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.TimeSheetSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.TopicSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.TransactionSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.UnitsTypeSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.UsageSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.VendorCategorySearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.VendorSearchRowBasic;
import com.netsuite.webservices.v2016_2.platform.common.WinLossReasonSearchRowBasic;


/**
 * <p>Java class for SearchRowBasic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchRowBasic"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:core_2016_2.platform.webservices.netsuite.com}SearchRow"&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchRowBasic")
@XmlSeeAlso({
    InventoryDetailSearchRowBasic.class,
    EntitySearchRowBasic.class,
    ContactSearchRowBasic.class,
    CustomerSearchRowBasic.class,
    CalendarEventSearchRowBasic.class,
    TaskSearchRowBasic.class,
    OpportunitySearchRowBasic.class,
    EmployeeSearchRowBasic.class,
    PhoneCallSearchRowBasic.class,
    SupportCaseSearchRowBasic.class,
    MessageSearchRowBasic.class,
    NoteSearchRowBasic.class,
    CustomRecordSearchRowBasic.class,
    AccountSearchRowBasic.class,
    RevRecScheduleSearchRowBasic.class,
    RevRecTemplateSearchRowBasic.class,
    BinSearchRowBasic.class,
    DepartmentSearchRowBasic.class,
    LocationSearchRowBasic.class,
    ClassificationSearchRowBasic.class,
    TransactionSearchRowBasic.class,
    ItemSearchRowBasic.class,
    PartnerSearchRowBasic.class,
    VendorSearchRowBasic.class,
    SiteCategorySearchRowBasic.class,
    TimeBillSearchRowBasic.class,
    SolutionSearchRowBasic.class,
    TopicSearchRowBasic.class,
    SubsidiarySearchRowBasic.class,
    GiftCertificateSearchRowBasic.class,
    FolderSearchRowBasic.class,
    FileSearchRowBasic.class,
    JobSearchRowBasic.class,
    IssueSearchRowBasic.class,
    CampaignSearchRowBasic.class,
    EntityGroupSearchRowBasic.class,
    PromotionCodeSearchRowBasic.class,
    BudgetSearchRowBasic.class,
    ProjectTaskSearchRowBasic.class,
    ProjectTaskAssignmentSearchRowBasic.class,
    AccountingPeriodSearchRowBasic.class,
    ContactCategorySearchRowBasic.class,
    ContactRoleSearchRowBasic.class,
    CustomerCategorySearchRowBasic.class,
    CustomerStatusSearchRowBasic.class,
    ExpenseCategorySearchRowBasic.class,
    JobStatusSearchRowBasic.class,
    JobTypeSearchRowBasic.class,
    NoteTypeSearchRowBasic.class,
    PartnerCategorySearchRowBasic.class,
    PaymentMethodSearchRowBasic.class,
    PriceLevelSearchRowBasic.class,
    SalesRoleSearchRowBasic.class,
    TermSearchRowBasic.class,
    VendorCategorySearchRowBasic.class,
    WinLossReasonSearchRowBasic.class,
    OriginatingLeadSearchRowBasic.class,
    UnitsTypeSearchRowBasic.class,
    CustomListSearchRowBasic.class,
    PricingGroupSearchRowBasic.class,
    InventoryNumberSearchRowBasic.class,
    InventoryNumberBinSearchRowBasic.class,
    ItemBinNumberSearchRowBasic.class,
    PricingSearchRowBasic.class,
    NexusSearchRowBasic.class,
    OtherNameCategorySearchRowBasic.class,
    CustomerMessageSearchRowBasic.class,
    ItemDemandPlanSearchRowBasic.class,
    ItemSupplyPlanSearchRowBasic.class,
    CurrencyRateSearchRowBasic.class,
    ItemRevisionSearchRowBasic.class,
    CouponCodeSearchRowBasic.class,
    PayrollItemSearchRowBasic.class,
    ManufacturingCostTemplateSearchRowBasic.class,
    ManufacturingRoutingSearchRowBasic.class,
    ManufacturingOperationTaskSearchRowBasic.class,
    ResourceAllocationSearchRowBasic.class,
    ChargeSearchRowBasic.class,
    BillingScheduleSearchRowBasic.class,
    GlobalAccountMappingSearchRowBasic.class,
    ItemAccountMappingSearchRowBasic.class,
    TimeEntrySearchRowBasic.class,
    TimeSheetSearchRowBasic.class,
    AccountingTransactionSearchRowBasic.class,
    AddressSearchRowBasic.class,
    BillingAccountSearchRowBasic.class,
    FairValuePriceSearchRowBasic.class,
    UsageSearchRowBasic.class,
    CostCategorySearchRowBasic.class
})
public abstract class SearchRowBasic
    extends SearchRow
{


}
