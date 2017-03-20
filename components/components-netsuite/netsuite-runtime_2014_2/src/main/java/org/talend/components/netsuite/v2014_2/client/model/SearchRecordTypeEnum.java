// ============================================================================
//
// Copyright (C) 2006-2017 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.components.netsuite.v2014_2.client.model;

import com.netsuite.webservices.v2014_2.activities.scheduling.CalendarEventSearch;
import com.netsuite.webservices.v2014_2.activities.scheduling.CalendarEventSearchAdvanced;
import com.netsuite.webservices.v2014_2.activities.scheduling.PhoneCallSearch;
import com.netsuite.webservices.v2014_2.activities.scheduling.PhoneCallSearchAdvanced;
import com.netsuite.webservices.v2014_2.activities.scheduling.ProjectTaskSearch;
import com.netsuite.webservices.v2014_2.activities.scheduling.ProjectTaskSearchAdvanced;
import com.netsuite.webservices.v2014_2.activities.scheduling.ResourceAllocationSearch;
import com.netsuite.webservices.v2014_2.activities.scheduling.ResourceAllocationSearchAdvanced;
import com.netsuite.webservices.v2014_2.activities.scheduling.TaskSearch;
import com.netsuite.webservices.v2014_2.activities.scheduling.TaskSearchAdvanced;
import com.netsuite.webservices.v2014_2.documents.filecabinet.FileSearch;
import com.netsuite.webservices.v2014_2.documents.filecabinet.FileSearchAdvanced;
import com.netsuite.webservices.v2014_2.documents.filecabinet.FolderSearch;
import com.netsuite.webservices.v2014_2.documents.filecabinet.FolderSearchAdvanced;
import com.netsuite.webservices.v2014_2.general.communication.MessageSearch;
import com.netsuite.webservices.v2014_2.general.communication.MessageSearchAdvanced;
import com.netsuite.webservices.v2014_2.general.communication.NoteSearch;
import com.netsuite.webservices.v2014_2.general.communication.NoteSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.AccountSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.AccountSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.AccountingPeriodSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.AccountingPeriodSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.BinSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.BinSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.ClassificationSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.ClassificationSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.CustomerMessageSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.CustomerMessageSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.DepartmentSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.DepartmentSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.GiftCertificateSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.GiftCertificateSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.InventoryNumberSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.InventoryNumberSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.ItemSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.ItemSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.LocationSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.LocationSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.NexusSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.NexusSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.OtherNameCategorySearch;
import com.netsuite.webservices.v2014_2.lists.accounting.OtherNameCategorySearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.PriceLevelSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.PriceLevelSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.PricingGroupSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.PricingGroupSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.RevRecScheduleSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.RevRecScheduleSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.RevRecTemplateSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.RevRecTemplateSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.SalesRoleSearch;
import com.netsuite.webservices.v2014_2.lists.accounting.SalesRoleSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.accounting.SubsidiarySearch;
import com.netsuite.webservices.v2014_2.lists.accounting.SubsidiarySearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.employees.EmployeeSearch;
import com.netsuite.webservices.v2014_2.lists.employees.EmployeeSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.marketing.CampaignSearch;
import com.netsuite.webservices.v2014_2.lists.marketing.CampaignSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.marketing.PromotionCodeSearch;
import com.netsuite.webservices.v2014_2.lists.marketing.PromotionCodeSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.relationships.ContactSearch;
import com.netsuite.webservices.v2014_2.lists.relationships.ContactSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.relationships.CustomerSearch;
import com.netsuite.webservices.v2014_2.lists.relationships.CustomerSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.relationships.EntityGroupSearch;
import com.netsuite.webservices.v2014_2.lists.relationships.EntityGroupSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.relationships.JobSearch;
import com.netsuite.webservices.v2014_2.lists.relationships.JobSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.relationships.PartnerSearch;
import com.netsuite.webservices.v2014_2.lists.relationships.PartnerSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.relationships.VendorSearch;
import com.netsuite.webservices.v2014_2.lists.relationships.VendorSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.supplychain.ManufacturingCostTemplateSearch;
import com.netsuite.webservices.v2014_2.lists.supplychain.ManufacturingCostTemplateSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.supplychain.ManufacturingOperationTaskSearch;
import com.netsuite.webservices.v2014_2.lists.supplychain.ManufacturingOperationTaskSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.supplychain.ManufacturingRoutingSearch;
import com.netsuite.webservices.v2014_2.lists.supplychain.ManufacturingRoutingSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.support.IssueSearch;
import com.netsuite.webservices.v2014_2.lists.support.IssueSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.support.SolutionSearch;
import com.netsuite.webservices.v2014_2.lists.support.SolutionSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.support.SupportCaseSearch;
import com.netsuite.webservices.v2014_2.lists.support.SupportCaseSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.support.TopicSearch;
import com.netsuite.webservices.v2014_2.lists.support.TopicSearchAdvanced;
import com.netsuite.webservices.v2014_2.lists.website.SiteCategorySearch;
import com.netsuite.webservices.v2014_2.lists.website.SiteCategorySearchAdvanced;
import com.netsuite.webservices.v2014_2.platform.common.AccountSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.AccountingPeriodSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.AppDefinitionSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.AppPackageSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.BinSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.BudgetSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.CalendarEventSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.CampaignSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.ClassificationSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.ContactSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.CustomRecordSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.CustomerMessageSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.CustomerSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.DepartmentSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.EmployeeSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.EntityGroupSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.FileSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.FolderSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.GiftCertificateSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.GroupMemberSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.InventoryDetailSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.InventoryNumberSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.IssueSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.ItemSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.JobSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.LocationSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.ManufacturingCostTemplateSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.ManufacturingOperationTaskSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.ManufacturingRoutingSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.MessageSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.NexusSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.NoteSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.OpportunitySearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.OtherNameCategorySearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.PartnerSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.PhoneCallSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.PriceLevelSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.PricingGroupSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.ProjectTaskSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.PromotionCodeSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.ResourceAllocationSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.RevRecScheduleSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.RevRecTemplateSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.SalesRoleSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.SiteCategorySearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.SolutionSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.SubsidiarySearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.SupportCaseSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.TaskSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.TimeBillSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.TimeEntrySearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.TopicSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.TransactionSearchBasic;
import com.netsuite.webservices.v2014_2.platform.common.VendorSearchBasic;
import com.netsuite.webservices.v2014_2.setup.customization.AppDefinitionSearch;
import com.netsuite.webservices.v2014_2.setup.customization.AppDefinitionSearchAdvanced;
import com.netsuite.webservices.v2014_2.setup.customization.AppPackageSearch;
import com.netsuite.webservices.v2014_2.setup.customization.AppPackageSearchAdvanced;
import com.netsuite.webservices.v2014_2.setup.customization.CustomRecordSearch;
import com.netsuite.webservices.v2014_2.setup.customization.CustomRecordSearchAdvanced;
import com.netsuite.webservices.v2014_2.transactions.employees.TimeBillSearch;
import com.netsuite.webservices.v2014_2.transactions.employees.TimeBillSearchAdvanced;
import com.netsuite.webservices.v2014_2.transactions.employees.TimeEntrySearch;
import com.netsuite.webservices.v2014_2.transactions.employees.TimeEntrySearchAdvanced;
import com.netsuite.webservices.v2014_2.transactions.financial.BudgetSearch;
import com.netsuite.webservices.v2014_2.transactions.financial.BudgetSearchAdvanced;
import com.netsuite.webservices.v2014_2.transactions.sales.OpportunitySearch;
import com.netsuite.webservices.v2014_2.transactions.sales.OpportunitySearchAdvanced;
import com.netsuite.webservices.v2014_2.transactions.sales.TransactionSearch;
import com.netsuite.webservices.v2014_2.transactions.sales.TransactionSearchAdvanced;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import org.talend.components.netsuite.client.model.SearchRecordTypeDesc;

/**
 *
 */
public enum SearchRecordTypeEnum implements SearchRecordTypeDesc {
    ACCOUNT("account", "Account", AccountSearch.class, AccountSearchBasic.class, AccountSearchAdvanced.class),

    ACCOUNTING_PERIOD("accountingPeriod", "AccountingPeriod", AccountingPeriodSearch.class, AccountingPeriodSearchBasic.class, AccountingPeriodSearchAdvanced.class),

    APP_DEFINITION("appDefinition", "AppDefinition", AppDefinitionSearch.class, AppDefinitionSearchBasic.class, AppDefinitionSearchAdvanced.class),

    APP_PACKAGE("appPackage", "AppPackage", AppPackageSearch.class, AppPackageSearchBasic.class, AppPackageSearchAdvanced.class),

    BIN("bin", "Bin", BinSearch.class, BinSearchBasic.class, BinSearchAdvanced.class),

    BUDGET("budget", "Budget", BudgetSearch.class, BudgetSearchBasic.class, BudgetSearchAdvanced.class),

    CALENDAR_EVENT("calendarEvent", "CalendarEvent", CalendarEventSearch.class, CalendarEventSearchBasic.class, CalendarEventSearchAdvanced.class),

    CAMPAIGN("campaign", "Campaign", CampaignSearch.class, CampaignSearchBasic.class, CampaignSearchAdvanced.class),

    CLASSIFICATION("classification", "Classification", ClassificationSearch.class, ClassificationSearchBasic.class, ClassificationSearchAdvanced.class),

    CONTACT("contact", "Contact", ContactSearch.class, ContactSearchBasic.class, ContactSearchAdvanced.class),

    CUSTOMER("customer", "Customer", CustomerSearch.class, CustomerSearchBasic.class, CustomerSearchAdvanced.class),

    CUSTOMER_MESSAGE("customerMessage", "CustomerMessage", CustomerMessageSearch.class, CustomerMessageSearchBasic.class, CustomerMessageSearchAdvanced.class),

    CUSTOM_RECORD("customRecord", "CustomRecord", CustomRecordSearch.class, CustomRecordSearchBasic.class, CustomRecordSearchAdvanced.class),

    DEPARTMENT("department", "Department", DepartmentSearch.class, DepartmentSearchBasic.class, DepartmentSearchAdvanced.class),

    EMPLOYEE("employee", "Employee", EmployeeSearch.class, EmployeeSearchBasic.class, EmployeeSearchAdvanced.class),

    ENTITY_GROUP("entityGroup", "EntityGroup", EntityGroupSearch.class, EntityGroupSearchBasic.class, EntityGroupSearchAdvanced.class),

    FILE("file", "File", FileSearch.class, FileSearchBasic.class, FileSearchAdvanced.class),

    FOLDER("folder", "Folder", FolderSearch.class, FolderSearchBasic.class, FolderSearchAdvanced.class),

    GIFT_CERTIFICATE("giftCertificate", "GiftCertificate", GiftCertificateSearch.class, GiftCertificateSearchBasic.class, GiftCertificateSearchAdvanced.class),

    GROUP_MEMBER("groupMember", "GroupMember", null, GroupMemberSearchBasic.class, null),

    INVENTORY_DETAIL("inventoryDetail", "InventoryDetail", null, InventoryDetailSearchBasic.class, null),

    INVENTORY_NUMBER("inventoryNumber", "InventoryNumber", InventoryNumberSearch.class, InventoryNumberSearchBasic.class, InventoryNumberSearchAdvanced.class),

    ISSUE("issue", "Issue", IssueSearch.class, IssueSearchBasic.class, IssueSearchAdvanced.class),

    ITEM("item", "Item", ItemSearch.class, ItemSearchBasic.class, ItemSearchAdvanced.class),

    JOB("job", "Job", JobSearch.class, JobSearchBasic.class, JobSearchAdvanced.class),

    LOCATION("location", "Location", LocationSearch.class, LocationSearchBasic.class, LocationSearchAdvanced.class),

    MANUFACTURING_COST_TEMPLATE("manufacturingCostTemplate", "ManufacturingCostTemplate", ManufacturingCostTemplateSearch.class, ManufacturingCostTemplateSearchBasic.class, ManufacturingCostTemplateSearchAdvanced.class),

    MANUFACTURING_OPERATION_TASK("manufacturingOperationTask", "ManufacturingOperationTask", ManufacturingOperationTaskSearch.class, ManufacturingOperationTaskSearchBasic.class, ManufacturingOperationTaskSearchAdvanced.class),

    MANUFACTURING_ROUTING("manufacturingRouting", "ManufacturingRouting", ManufacturingRoutingSearch.class, ManufacturingRoutingSearchBasic.class, ManufacturingRoutingSearchAdvanced.class),

    MESSAGE("message", "Message", MessageSearch.class, MessageSearchBasic.class, MessageSearchAdvanced.class),

    NEXUS("nexus", "Nexus", NexusSearch.class, NexusSearchBasic.class, NexusSearchAdvanced.class),

    NOTE("note", "Note", NoteSearch.class, NoteSearchBasic.class, NoteSearchAdvanced.class),

    OPPORTUNITY("opportunity", "Opportunity", OpportunitySearch.class, OpportunitySearchBasic.class, OpportunitySearchAdvanced.class),

    OTHER_NAME_CATEGORY("otherNameCategory", "OtherNameCategory", OtherNameCategorySearch.class, OtherNameCategorySearchBasic.class, OtherNameCategorySearchAdvanced.class),

    PARTNER("partner", "Partner", PartnerSearch.class, PartnerSearchBasic.class, PartnerSearchAdvanced.class),

    PHONE_CALL("phoneCall", "PhoneCall", PhoneCallSearch.class, PhoneCallSearchBasic.class, PhoneCallSearchAdvanced.class),

    PRICE_LEVEL("priceLevel", "PriceLevel", PriceLevelSearch.class, PriceLevelSearchBasic.class, PriceLevelSearchAdvanced.class),

    PRICING_GROUP("pricingGroup", "PricingGroup", PricingGroupSearch.class, PricingGroupSearchBasic.class, PricingGroupSearchAdvanced.class),

    PROJECT_TASK("projectTask", "ProjectTask", ProjectTaskSearch.class, ProjectTaskSearchBasic.class, ProjectTaskSearchAdvanced.class),

    PROMOTION_CODE("promotionCode", "PromotionCode", PromotionCodeSearch.class, PromotionCodeSearchBasic.class, PromotionCodeSearchAdvanced.class),

    RESOURCE_ALLOCATION("resourceAllocation", "ResourceAllocation", ResourceAllocationSearch.class, ResourceAllocationSearchBasic.class, ResourceAllocationSearchAdvanced.class),

    REV_REC_SCHEDULE("revRecSchedule", "RevRecSchedule", RevRecScheduleSearch.class, RevRecScheduleSearchBasic.class, RevRecScheduleSearchAdvanced.class),

    REV_REC_TEMPLATE("revRecTemplate", "RevRecTemplate", RevRecTemplateSearch.class, RevRecTemplateSearchBasic.class, RevRecTemplateSearchAdvanced.class),

    SALES_ROLE("salesRole", "SalesRole", SalesRoleSearch.class, SalesRoleSearchBasic.class, SalesRoleSearchAdvanced.class),

    SITE_CATEGORY("siteCategory", "SiteCategory", SiteCategorySearch.class, SiteCategorySearchBasic.class, SiteCategorySearchAdvanced.class),

    SOLUTION("solution", "Solution", SolutionSearch.class, SolutionSearchBasic.class, SolutionSearchAdvanced.class),

    SUBSIDIARY("subsidiary", "Subsidiary", SubsidiarySearch.class, SubsidiarySearchBasic.class, SubsidiarySearchAdvanced.class),

    SUPPORT_CASE("supportCase", "SupportCase", SupportCaseSearch.class, SupportCaseSearchBasic.class, SupportCaseSearchAdvanced.class),

    TASK("task", "Task", TaskSearch.class, TaskSearchBasic.class, TaskSearchAdvanced.class),

    TIME_BILL("timeBill", "TimeBill", TimeBillSearch.class, TimeBillSearchBasic.class, TimeBillSearchAdvanced.class),

    TIME_ENTRY("timeEntry", "TimeEntry", TimeEntrySearch.class, TimeEntrySearchBasic.class, TimeEntrySearchAdvanced.class),

    TOPIC("topic", "Topic", TopicSearch.class, TopicSearchBasic.class, TopicSearchAdvanced.class),

    TRANSACTION("transaction", "Transaction", TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),

    VENDOR("vendor", "Vendor", VendorSearch.class, VendorSearchBasic.class, VendorSearchAdvanced.class);

    private final String type;

    private final String typeName;

    private final Class searchClass;

    private final Class searchBasicClass;

    private final Class searchAdvancedClass;

    SearchRecordTypeEnum(String type, String typeName, Class searchClass, Class searchBasicClass,
            Class searchAdvancedClass) {
        this.type = type;
        this.typeName = typeName;
        this.searchClass = searchClass;
        this.searchBasicClass = searchBasicClass;
        this.searchAdvancedClass = searchAdvancedClass;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    @Override
    public Class getSearchClass() {
        return this.searchClass;
    }

    @Override
    public Class getSearchBasicClass() {
        return this.searchBasicClass;
    }

    @Override
    public Class getSearchAdvancedClass() {
        return this.searchAdvancedClass;
    }

    public static SearchRecordTypeEnum getByTypeName(String typeName) {
        for (SearchRecordTypeEnum value : values()) {
            if (value.typeName.equals(typeName)) {
                return value;
            }
        }
        return null;
    }
}
