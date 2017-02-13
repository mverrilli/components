package org.talend.components.netsuite.client;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.talend.components.netsuite.client.schema.NsFieldDef;
import org.talend.components.netsuite.client.schema.NsSearchDef;
import org.talend.components.netsuite.client.schema.NsSearchFieldOperatorTypeDef;
import org.talend.components.netsuite.client.schema.NsTypeDef;
import org.talend.components.netsuite.model.Mapper;
import org.talend.components.netsuite.model.PropertyInfo;
import org.talend.components.netsuite.model.TypeInfo;
import org.talend.components.netsuite.model.TypeManager;

import com.netsuite.webservices.platform.ExceededRequestSizeFault;
import com.netsuite.webservices.platform.InsufficientPermissionFault;
import com.netsuite.webservices.platform.InvalidCredentialsFault;
import com.netsuite.webservices.platform.InvalidSessionFault;
import com.netsuite.webservices.platform.NetSuitePortType;
import com.netsuite.webservices.platform.NetSuiteService;
import com.netsuite.webservices.platform.UnexpectedErrorFault;
import com.netsuite.webservices.platform.core.BaseRef;
import com.netsuite.webservices.platform.core.DataCenterUrls;
import com.netsuite.webservices.platform.core.Passport;
import com.netsuite.webservices.platform.core.RecordRef;
import com.netsuite.webservices.platform.core.SearchResult;
import com.netsuite.webservices.platform.core.Status;
import com.netsuite.webservices.platform.messages.AddListRequest;
import com.netsuite.webservices.platform.messages.AddRequest;
import com.netsuite.webservices.platform.messages.ApplicationInfo;
import com.netsuite.webservices.platform.messages.DeleteListRequest;
import com.netsuite.webservices.platform.messages.DeleteRequest;
import com.netsuite.webservices.platform.messages.GetDataCenterUrlsRequest;
import com.netsuite.webservices.platform.messages.GetDataCenterUrlsResponse;
import com.netsuite.webservices.platform.messages.LoginRequest;
import com.netsuite.webservices.platform.messages.LoginResponse;
import com.netsuite.webservices.platform.messages.LogoutRequest;
import com.netsuite.webservices.platform.messages.Preferences;
import com.netsuite.webservices.platform.messages.SearchMoreRequest;
import com.netsuite.webservices.platform.messages.SearchMoreWithIdRequest;
import com.netsuite.webservices.platform.messages.SearchNextRequest;
import com.netsuite.webservices.platform.messages.SearchPreferences;
import com.netsuite.webservices.platform.messages.SearchRequest;
import com.netsuite.webservices.platform.messages.SessionResponse;
import com.netsuite.webservices.activities.scheduling.CalendarEvent;
import com.netsuite.webservices.activities.scheduling.CalendarEventSearch;
import com.netsuite.webservices.activities.scheduling.CalendarEventSearchAdvanced;
import com.netsuite.webservices.activities.scheduling.PhoneCall;
import com.netsuite.webservices.activities.scheduling.PhoneCallSearch;
import com.netsuite.webservices.activities.scheduling.PhoneCallSearchAdvanced;
import com.netsuite.webservices.activities.scheduling.ProjectTask;
import com.netsuite.webservices.activities.scheduling.ProjectTaskSearch;
import com.netsuite.webservices.activities.scheduling.ProjectTaskSearchAdvanced;
import com.netsuite.webservices.activities.scheduling.ResourceAllocation;
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
import com.netsuite.webservices.lists.accounting.Account;
import com.netsuite.webservices.lists.accounting.AccountSearch;
import com.netsuite.webservices.lists.accounting.AccountSearchAdvanced;
import com.netsuite.webservices.lists.accounting.AccountingPeriod;
import com.netsuite.webservices.lists.accounting.AccountingPeriodSearch;
import com.netsuite.webservices.lists.accounting.AccountingPeriodSearchAdvanced;
import com.netsuite.webservices.lists.accounting.BillingSchedule;
import com.netsuite.webservices.lists.accounting.BillingScheduleSearch;
import com.netsuite.webservices.lists.accounting.BillingScheduleSearchAdvanced;
import com.netsuite.webservices.lists.accounting.Bin;
import com.netsuite.webservices.lists.accounting.BinSearch;
import com.netsuite.webservices.lists.accounting.BinSearchAdvanced;
import com.netsuite.webservices.lists.accounting.Classification;
import com.netsuite.webservices.lists.accounting.ClassificationSearch;
import com.netsuite.webservices.lists.accounting.ClassificationSearchAdvanced;
import com.netsuite.webservices.lists.accounting.ContactCategory;
import com.netsuite.webservices.lists.accounting.ContactCategorySearch;
import com.netsuite.webservices.lists.accounting.ContactCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.ContactRole;
import com.netsuite.webservices.lists.accounting.ContactRoleSearch;
import com.netsuite.webservices.lists.accounting.ContactRoleSearchAdvanced;
import com.netsuite.webservices.lists.accounting.CurrencyRate;
import com.netsuite.webservices.lists.accounting.CurrencyRateSearch;
import com.netsuite.webservices.lists.accounting.CurrencyRateSearchAdvanced;
import com.netsuite.webservices.lists.accounting.CustomerCategory;
import com.netsuite.webservices.lists.accounting.CustomerCategorySearch;
import com.netsuite.webservices.lists.accounting.CustomerCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.CustomerMessage;
import com.netsuite.webservices.lists.accounting.CustomerMessageSearch;
import com.netsuite.webservices.lists.accounting.CustomerMessageSearchAdvanced;
import com.netsuite.webservices.lists.accounting.Department;
import com.netsuite.webservices.lists.accounting.DepartmentSearch;
import com.netsuite.webservices.lists.accounting.DepartmentSearchAdvanced;
import com.netsuite.webservices.lists.accounting.ExpenseCategory;
import com.netsuite.webservices.lists.accounting.ExpenseCategorySearch;
import com.netsuite.webservices.lists.accounting.ExpenseCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.GiftCertificate;
import com.netsuite.webservices.lists.accounting.GiftCertificateSearch;
import com.netsuite.webservices.lists.accounting.GiftCertificateSearchAdvanced;
import com.netsuite.webservices.lists.accounting.GlobalAccountMapping;
import com.netsuite.webservices.lists.accounting.GlobalAccountMappingSearch;
import com.netsuite.webservices.lists.accounting.GlobalAccountMappingSearchAdvanced;
import com.netsuite.webservices.lists.accounting.InventoryNumber;
import com.netsuite.webservices.lists.accounting.InventoryNumberSearch;
import com.netsuite.webservices.lists.accounting.InventoryNumberSearchAdvanced;
import com.netsuite.webservices.lists.accounting.ItemAccountMapping;
import com.netsuite.webservices.lists.accounting.ItemAccountMappingSearch;
import com.netsuite.webservices.lists.accounting.ItemAccountMappingSearchAdvanced;
import com.netsuite.webservices.lists.accounting.ItemRevision;
import com.netsuite.webservices.lists.accounting.ItemRevisionSearch;
import com.netsuite.webservices.lists.accounting.ItemRevisionSearchAdvanced;
import com.netsuite.webservices.lists.accounting.LocationSearch;
import com.netsuite.webservices.lists.accounting.LocationSearchAdvanced;
import com.netsuite.webservices.lists.accounting.Nexus;
import com.netsuite.webservices.lists.accounting.NexusSearch;
import com.netsuite.webservices.lists.accounting.NexusSearchAdvanced;
import com.netsuite.webservices.lists.accounting.NoteType;
import com.netsuite.webservices.lists.accounting.NoteTypeSearch;
import com.netsuite.webservices.lists.accounting.NoteTypeSearchAdvanced;
import com.netsuite.webservices.lists.accounting.OtherNameCategory;
import com.netsuite.webservices.lists.accounting.OtherNameCategorySearch;
import com.netsuite.webservices.lists.accounting.OtherNameCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.PartnerCategory;
import com.netsuite.webservices.lists.accounting.PartnerCategorySearch;
import com.netsuite.webservices.lists.accounting.PartnerCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.PaymentMethod;
import com.netsuite.webservices.lists.accounting.PaymentMethodSearch;
import com.netsuite.webservices.lists.accounting.PaymentMethodSearchAdvanced;
import com.netsuite.webservices.lists.accounting.PriceLevel;
import com.netsuite.webservices.lists.accounting.PriceLevelSearch;
import com.netsuite.webservices.lists.accounting.PriceLevelSearchAdvanced;
import com.netsuite.webservices.lists.accounting.PricingGroup;
import com.netsuite.webservices.lists.accounting.PricingGroupSearch;
import com.netsuite.webservices.lists.accounting.PricingGroupSearchAdvanced;
import com.netsuite.webservices.lists.accounting.RevRecSchedule;
import com.netsuite.webservices.lists.accounting.RevRecScheduleSearch;
import com.netsuite.webservices.lists.accounting.RevRecScheduleSearchAdvanced;
import com.netsuite.webservices.lists.accounting.RevRecTemplate;
import com.netsuite.webservices.lists.accounting.RevRecTemplateSearch;
import com.netsuite.webservices.lists.accounting.RevRecTemplateSearchAdvanced;
import com.netsuite.webservices.lists.accounting.SalesRole;
import com.netsuite.webservices.lists.accounting.SalesRoleSearch;
import com.netsuite.webservices.lists.accounting.SalesRoleSearchAdvanced;
import com.netsuite.webservices.lists.accounting.Subsidiary;
import com.netsuite.webservices.lists.accounting.SubsidiarySearch;
import com.netsuite.webservices.lists.accounting.SubsidiarySearchAdvanced;
import com.netsuite.webservices.lists.accounting.TermSearch;
import com.netsuite.webservices.lists.accounting.TermSearchAdvanced;
import com.netsuite.webservices.lists.accounting.UnitsType;
import com.netsuite.webservices.lists.accounting.UnitsTypeSearch;
import com.netsuite.webservices.lists.accounting.UnitsTypeSearchAdvanced;
import com.netsuite.webservices.lists.accounting.VendorCategory;
import com.netsuite.webservices.lists.accounting.VendorCategorySearch;
import com.netsuite.webservices.lists.accounting.VendorCategorySearchAdvanced;
import com.netsuite.webservices.lists.accounting.WinLossReason;
import com.netsuite.webservices.lists.accounting.WinLossReasonSearch;
import com.netsuite.webservices.lists.accounting.WinLossReasonSearchAdvanced;
import com.netsuite.webservices.lists.employees.Employee;
import com.netsuite.webservices.lists.employees.EmployeeSearch;
import com.netsuite.webservices.lists.employees.EmployeeSearchAdvanced;
import com.netsuite.webservices.lists.employees.PayrollItem;
import com.netsuite.webservices.lists.employees.PayrollItemSearch;
import com.netsuite.webservices.lists.employees.PayrollItemSearchAdvanced;
import com.netsuite.webservices.lists.marketing.Campaign;
import com.netsuite.webservices.lists.marketing.CampaignSearch;
import com.netsuite.webservices.lists.marketing.CampaignSearchAdvanced;
import com.netsuite.webservices.lists.marketing.CouponCode;
import com.netsuite.webservices.lists.marketing.CouponCodeSearch;
import com.netsuite.webservices.lists.marketing.CouponCodeSearchAdvanced;
import com.netsuite.webservices.lists.marketing.PromotionCode;
import com.netsuite.webservices.lists.marketing.PromotionCodeSearch;
import com.netsuite.webservices.lists.marketing.PromotionCodeSearchAdvanced;
import com.netsuite.webservices.lists.relationships.Contact;
import com.netsuite.webservices.lists.relationships.ContactSearch;
import com.netsuite.webservices.lists.relationships.ContactSearchAdvanced;
import com.netsuite.webservices.lists.relationships.Customer;
import com.netsuite.webservices.lists.relationships.CustomerSearch;
import com.netsuite.webservices.lists.relationships.CustomerSearchAdvanced;
import com.netsuite.webservices.lists.relationships.CustomerStatus;
import com.netsuite.webservices.lists.relationships.CustomerStatusSearch;
import com.netsuite.webservices.lists.relationships.CustomerStatusSearchAdvanced;
import com.netsuite.webservices.lists.relationships.EntityGroup;
import com.netsuite.webservices.lists.relationships.EntityGroupSearch;
import com.netsuite.webservices.lists.relationships.EntityGroupSearchAdvanced;
import com.netsuite.webservices.lists.relationships.JobSearch;
import com.netsuite.webservices.lists.relationships.JobSearchAdvanced;
import com.netsuite.webservices.lists.relationships.JobStatusSearch;
import com.netsuite.webservices.lists.relationships.JobStatusSearchAdvanced;
import com.netsuite.webservices.lists.relationships.JobTypeSearch;
import com.netsuite.webservices.lists.relationships.JobTypeSearchAdvanced;
import com.netsuite.webservices.lists.relationships.Partner;
import com.netsuite.webservices.lists.relationships.PartnerSearch;
import com.netsuite.webservices.lists.relationships.PartnerSearchAdvanced;
import com.netsuite.webservices.lists.relationships.Vendor;
import com.netsuite.webservices.lists.relationships.VendorSearch;
import com.netsuite.webservices.lists.relationships.VendorSearchAdvanced;
import com.netsuite.webservices.lists.supplychain.ManufacturingCostTemplate;
import com.netsuite.webservices.lists.supplychain.ManufacturingCostTemplateSearch;
import com.netsuite.webservices.lists.supplychain.ManufacturingCostTemplateSearchAdvanced;
import com.netsuite.webservices.lists.supplychain.ManufacturingOperationTask;
import com.netsuite.webservices.lists.supplychain.ManufacturingOperationTaskSearch;
import com.netsuite.webservices.lists.supplychain.ManufacturingOperationTaskSearchAdvanced;
import com.netsuite.webservices.lists.supplychain.ManufacturingRouting;
import com.netsuite.webservices.lists.supplychain.ManufacturingRoutingSearch;
import com.netsuite.webservices.lists.supplychain.ManufacturingRoutingSearchAdvanced;
import com.netsuite.webservices.lists.support.Issue;
import com.netsuite.webservices.lists.support.IssueSearch;
import com.netsuite.webservices.lists.support.IssueSearchAdvanced;
import com.netsuite.webservices.lists.support.Solution;
import com.netsuite.webservices.lists.support.SolutionSearch;
import com.netsuite.webservices.lists.support.SolutionSearchAdvanced;
import com.netsuite.webservices.lists.support.SupportCase;
import com.netsuite.webservices.lists.support.SupportCaseSearch;
import com.netsuite.webservices.lists.support.SupportCaseSearchAdvanced;
import com.netsuite.webservices.lists.support.TopicSearch;
import com.netsuite.webservices.lists.support.TopicSearchAdvanced;
import com.netsuite.webservices.lists.website.SiteCategorySearch;
import com.netsuite.webservices.lists.website.SiteCategorySearchAdvanced;
import com.netsuite.webservices.platform.common.AccountSearchBasic;
import com.netsuite.webservices.platform.common.AccountingPeriodSearchBasic;
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
import com.netsuite.webservices.platform.common.TimeEntrySearchBasic;
import com.netsuite.webservices.platform.common.TimeSheetSearchBasic;
import com.netsuite.webservices.platform.common.TopicSearchBasic;
import com.netsuite.webservices.platform.common.TransactionSearchBasic;
import com.netsuite.webservices.platform.common.UnitsTypeSearchBasic;
import com.netsuite.webservices.platform.common.VendorCategorySearchBasic;
import com.netsuite.webservices.platform.common.VendorSearchBasic;
import com.netsuite.webservices.platform.common.WinLossReasonSearchBasic;
import com.netsuite.webservices.platform.core.Record;
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
import com.netsuite.webservices.platform.core.types.SearchDate;
import com.netsuite.webservices.platform.core.types.SearchDateFieldOperator;
import com.netsuite.webservices.platform.core.types.SearchDoubleFieldOperator;
import com.netsuite.webservices.platform.core.types.SearchEnumMultiSelectFieldOperator;
import com.netsuite.webservices.platform.core.types.SearchLongFieldOperator;
import com.netsuite.webservices.platform.core.types.SearchMultiSelectFieldOperator;
import com.netsuite.webservices.platform.core.types.SearchStringFieldOperator;
import com.netsuite.webservices.platform.messages.UpdateListRequest;
import com.netsuite.webservices.platform.messages.UpdateRequest;
import com.netsuite.webservices.platform.messages.UpsertListRequest;
import com.netsuite.webservices.platform.messages.UpsertRequest;
import com.netsuite.webservices.platform.messages.WriteResponse;
import com.netsuite.webservices.platform.messages.WriteResponseList;
import com.netsuite.webservices.setup.customization.CustomList;
import com.netsuite.webservices.setup.customization.CustomListSearch;
import com.netsuite.webservices.setup.customization.CustomListSearchAdvanced;
import com.netsuite.webservices.setup.customization.CustomRecord;
import com.netsuite.webservices.setup.customization.CustomRecordSearch;
import com.netsuite.webservices.setup.customization.CustomRecordSearchAdvanced;
import com.netsuite.webservices.transactions.bank.Check;
import com.netsuite.webservices.transactions.bank.Deposit;
import com.netsuite.webservices.transactions.customers.CashRefund;
import com.netsuite.webservices.transactions.customers.Charge;
import com.netsuite.webservices.transactions.customers.ChargeSearch;
import com.netsuite.webservices.transactions.customers.ChargeSearchAdvanced;
import com.netsuite.webservices.transactions.customers.CreditMemo;
import com.netsuite.webservices.transactions.customers.CustomerDeposit;
import com.netsuite.webservices.transactions.customers.CustomerPayment;
import com.netsuite.webservices.transactions.customers.CustomerRefund;
import com.netsuite.webservices.transactions.customers.DepositApplication;
import com.netsuite.webservices.transactions.customers.ReturnAuthorization;
import com.netsuite.webservices.transactions.demandplanning.ItemDemandPlan;
import com.netsuite.webservices.transactions.demandplanning.ItemDemandPlanSearch;
import com.netsuite.webservices.transactions.demandplanning.ItemDemandPlanSearchAdvanced;
import com.netsuite.webservices.transactions.demandplanning.ItemSupplyPlan;
import com.netsuite.webservices.transactions.demandplanning.ItemSupplyPlanSearch;
import com.netsuite.webservices.transactions.demandplanning.ItemSupplyPlanSearchAdvanced;
import com.netsuite.webservices.transactions.employees.ExpenseReport;
import com.netsuite.webservices.transactions.employees.PaycheckJournal;
import com.netsuite.webservices.transactions.employees.TimeBill;
import com.netsuite.webservices.transactions.employees.TimeBillSearch;
import com.netsuite.webservices.transactions.employees.TimeBillSearchAdvanced;
import com.netsuite.webservices.transactions.employees.TimeEntry;
import com.netsuite.webservices.transactions.employees.TimeEntrySearch;
import com.netsuite.webservices.transactions.employees.TimeEntrySearchAdvanced;
import com.netsuite.webservices.transactions.employees.TimeSheet;
import com.netsuite.webservices.transactions.employees.TimeSheetSearch;
import com.netsuite.webservices.transactions.employees.TimeSheetSearchAdvanced;
import com.netsuite.webservices.transactions.financial.Budget;
import com.netsuite.webservices.transactions.financial.BudgetSearch;
import com.netsuite.webservices.transactions.financial.BudgetSearchAdvanced;
import com.netsuite.webservices.transactions.general.InterCompanyJournalEntry;
import com.netsuite.webservices.transactions.general.JournalEntry;
import com.netsuite.webservices.transactions.inventory.AssemblyBuild;
import com.netsuite.webservices.transactions.inventory.AssemblyUnbuild;
import com.netsuite.webservices.transactions.inventory.BinTransfer;
import com.netsuite.webservices.transactions.inventory.BinWorksheet;
import com.netsuite.webservices.transactions.inventory.InventoryAdjustment;
import com.netsuite.webservices.transactions.inventory.InventoryCostRevaluation;
import com.netsuite.webservices.transactions.inventory.InventoryTransfer;
import com.netsuite.webservices.transactions.inventory.TransferOrder;
import com.netsuite.webservices.transactions.inventory.WorkOrder;
import com.netsuite.webservices.transactions.inventory.WorkOrderClose;
import com.netsuite.webservices.transactions.inventory.WorkOrderCompletion;
import com.netsuite.webservices.transactions.inventory.WorkOrderIssue;
import com.netsuite.webservices.transactions.purchases.ItemReceipt;
import com.netsuite.webservices.transactions.purchases.PurchaseOrder;
import com.netsuite.webservices.transactions.purchases.VendorBill;
import com.netsuite.webservices.transactions.purchases.VendorCredit;
import com.netsuite.webservices.transactions.purchases.VendorPayment;
import com.netsuite.webservices.transactions.purchases.VendorReturnAuthorization;
import com.netsuite.webservices.transactions.sales.CashSale;
import com.netsuite.webservices.transactions.sales.Estimate;
import com.netsuite.webservices.transactions.sales.Invoice;
import com.netsuite.webservices.transactions.sales.ItemFulfillment;
import com.netsuite.webservices.transactions.sales.Opportunity;
import com.netsuite.webservices.transactions.sales.SalesOrder;
import com.netsuite.webservices.transactions.sales.TransactionSearch;
import com.netsuite.webservices.transactions.sales.TransactionSearchAdvanced;

/**
 *
 */
public class NetSuiteClientService {

    public static final int DEFAULT_PAGE_SIZE = 100;

    public static final String MESSAGE_LOGGING_ENABLED_PROPERTY_NAME =
            "org.talend.components.netsuite.client.messageLoggingEnabled";

    public static final String DEFAULT_ENDPOINT_URL =
            "https://webservices.netsuite.com/services/NetSuitePort_2016_2";

    public static final String NS_URI_PLATFORM_MESSAGES =
            "urn:messages_2016_2.platform.webservices.netsuite.com";

    private String endpointUrl;

    private NetSuiteCredentials credentials;

    private SearchPreferencesEx searchPreferences;
    private PreferencesEx preferences;

    private ReentrantLock lock = new ReentrantLock();

    private boolean messageLoggingEnabled = false;

    private int retryCount = 3;
    private int retriesBeforeLogin = 2;
    private int retryInterval = 5;

    private int searchPageSize = DEFAULT_PAGE_SIZE;
    private boolean bodyFieldsOnly = true;

    private boolean treatWarningsAsErrors = false;
    private boolean disableMandatoryCustomFieldValidation = false;

    private boolean useRequestLevelCredentials = false;

    private boolean loggedIn = false;

    private NetSuitePortType port;

    public NetSuiteClientService() {
        loadStandardMetaData();
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public NetSuiteCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(NetSuiteCredentials credentials) {
        this.credentials = credentials;
    }

    public int getSearchPageSize() {
        return searchPageSize;
    }

    public void setSearchPageSize(int searchPageSize) {
        this.searchPageSize = searchPageSize;
    }

    public boolean isBodyFieldsOnly() {
        return bodyFieldsOnly;
    }

    public void setBodyFieldsOnly(boolean bodyFieldsOnly) {
        this.bodyFieldsOnly = bodyFieldsOnly;
    }

    public boolean isTreatWarningsAsErrors() {
        return treatWarningsAsErrors;
    }

    public void setTreatWarningsAsErrors(boolean treatWarningsAsErrors) {
        this.treatWarningsAsErrors = treatWarningsAsErrors;
    }

    public boolean isDisableMandatoryCustomFieldValidation() {
        return disableMandatoryCustomFieldValidation;
    }

    public void setDisableMandatoryCustomFieldValidation(boolean disableMandatoryCustomFieldValidation) {
        this.disableMandatoryCustomFieldValidation = disableMandatoryCustomFieldValidation;
    }

    public boolean isUseRequestLevelCredentials() {
        return useRequestLevelCredentials;
    }

    public void setUseRequestLevelCredentials(boolean useRequestLevelCredentials) {
        this.useRequestLevelCredentials = useRequestLevelCredentials;
    }

    public void login() throws NetSuiteException {
        lock.lock();
        try {
            relogin();
        } finally {
            lock.unlock();
        }
    }

    public SearchQuery newSearch() throws NetSuiteException {
        return new SearchQuery(this);
    }

    public SearchResultEx search(final SearchRecord searchRecord) throws NetSuiteException {
        return execute(new PortOperation<SearchResultEx>() {
            @Override public SearchResultEx execute(NetSuitePortType port) throws Exception {
                SearchRequest request = new SearchRequest();
                SearchRecord r = NsObject.unwrap(searchRecord, SearchRecord.class);
                request.setSearchRecord(r);

                SearchResult result = port.search(request).getSearchResult();
                return new SearchResultEx(result);
            }
        });
    }

    public SearchResultEx searchMore(final int pageIndex) throws NetSuiteException {
        return execute(new PortOperation<SearchResultEx>() {
            @Override public SearchResultEx execute(NetSuitePortType port) throws Exception {
                SearchMoreRequest request = new SearchMoreRequest();
                request.setPageIndex(pageIndex);

                SearchResult result = port.searchMore(request).getSearchResult();
                return new SearchResultEx(result);
            }
        });
    }

    public SearchResultEx searchMoreWithId(
            final String searchId, final int pageIndex) throws NetSuiteException {
        return execute(new PortOperation<SearchResultEx>() {
            @Override public SearchResultEx execute(NetSuitePortType port) throws Exception {
                SearchMoreWithIdRequest request = new SearchMoreWithIdRequest();
                request.setSearchId(searchId);
                request.setPageIndex(pageIndex);

                SearchResult result = port.searchMoreWithId(request).getSearchResult();
                return new SearchResultEx(result);
            }
        });
    }

    public SearchResultEx searchNext() throws NetSuiteException {
        return execute(new PortOperation<SearchResultEx>() {
            @Override public SearchResultEx execute(NetSuitePortType port) throws Exception {
                SearchNextRequest request = new SearchNextRequest();
                SearchResult result = port.searchNext(request).getSearchResult();
                return new SearchResultEx(result);
            }
        });
    }

    public WriteResponseEx add(final Record record) throws NetSuiteException {
        if (record == null) {
            return new WriteResponseEx(new WriteResponse());
        }
        return execute(new PortOperation<WriteResponseEx>() {
            @Override public WriteResponseEx execute(NetSuitePortType port) throws Exception {
                AddRequest request = new AddRequest();
                request.setRecord(record);

                WriteResponse response = port.add(request).getWriteResponse();
                return new WriteResponseEx(response);
            }
        });
    }

    public List<WriteResponseEx> addList(final List<Record> records) throws NetSuiteException {
        if (records == null || records.isEmpty()) {
            return Collections.emptyList();
        }
        return execute(new PortOperation<List<WriteResponseEx>>() {
            @Override public List<WriteResponseEx> execute(NetSuitePortType port) throws Exception {
                AddListRequest request = new AddListRequest();
                request.getRecord().addAll(records);

                WriteResponseList writeResponseList = port.addList(request).getWriteResponseList();
                return toWriteResponseExList(writeResponseList);
            }
        });
    }

    public WriteResponseEx update(final Record record) throws NetSuiteException {
        if (record == null) {
            return new WriteResponseEx(new WriteResponse());
        }
        return execute(new PortOperation<WriteResponseEx>() {
            @Override public WriteResponseEx execute(NetSuitePortType port) throws Exception {
                UpdateRequest request = new UpdateRequest();
                request.setRecord(record);

                WriteResponse response = port.update(request).getWriteResponse();
                return new WriteResponseEx(response);
            }
        });
    }

    public List<WriteResponseEx> updateList(final List<Record> records) throws NetSuiteException {
        if (records == null || records.isEmpty()) {
            return Collections.emptyList();
        }
        return execute(new PortOperation<List<WriteResponseEx>>() {
            @Override public List<WriteResponseEx> execute(NetSuitePortType port) throws Exception {
                UpdateListRequest request = new UpdateListRequest();
                request.getRecord().addAll(records);

                WriteResponseList writeResponseList = port.updateList(request).getWriteResponseList();
                return toWriteResponseExList(writeResponseList);
            }
        });
    }

    public WriteResponseEx upsert(final Record record) throws NetSuiteException {
        if (record == null) {
            return new WriteResponseEx(new WriteResponse());
        }
        return execute(new PortOperation<WriteResponseEx>() {
            @Override public WriteResponseEx execute(NetSuitePortType port) throws Exception {
                UpsertRequest request = new UpsertRequest();
                request.setRecord(record);

                WriteResponse response = port.upsert(request).getWriteResponse();
                return new WriteResponseEx(response);
            }
        });
    }

    public List<WriteResponseEx> upsertList(final List<Record> records) throws NetSuiteException {
        if (records == null || records.isEmpty()) {
            return Collections.emptyList();
        }
        return execute(new PortOperation<List<WriteResponseEx>>() {
            @Override public List<WriteResponseEx> execute(NetSuitePortType port) throws Exception {
                UpsertListRequest request = new UpsertListRequest();
                request.getRecord().addAll(records);

                WriteResponseList writeResponseList = port.upsertList(request).getWriteResponseList();
                return toWriteResponseExList(writeResponseList);
            }
        });
    }

    public WriteResponseEx delete(final BaseRef ref) throws NetSuiteException {
        if (ref == null) {
            return new WriteResponseEx(new WriteResponse());
        }
        return execute(new PortOperation<WriteResponseEx>() {
            @Override public WriteResponseEx execute(NetSuitePortType port) throws Exception {
                DeleteRequest request = new DeleteRequest();
                BaseRef baseRef = NsObject.unwrap(ref, BaseRef.class);
                request.setBaseRef(baseRef);

                WriteResponse writeResponse = port.delete(request).getWriteResponse();
                return new WriteResponseEx(writeResponse);
            }
        });
    }

    public List<WriteResponseEx> deleteList(final List<BaseRef> refs) throws NetSuiteException {
        if (refs == null || refs.isEmpty()) {
            return Collections.emptyList();
        }
        return execute(new PortOperation<List<WriteResponseEx>>() {
            @Override public List<WriteResponseEx> execute(NetSuitePortType port) throws Exception {
                DeleteListRequest request = new DeleteListRequest();
                request.getBaseRef().addAll(refs);

                WriteResponseList writeResponseList = port.deleteList(request).getWriteResponseList();
                return toWriteResponseExList(writeResponseList);
            }
        });
    }

    public <R> R execute(PortOperation<R> op) throws NetSuiteException {
        if (useRequestLevelCredentials) {
            return executeUsingRequestLevelCredentials(op);
        } else {
            return executeUsingLogin(op);
        }
    }

    protected <R> R executeUsingLogin(PortOperation<R> op) throws NetSuiteException {
        lock.lock();
        try {
            login(false);

            try {
                return op.execute(port);
            } catch (Exception e) {
                throw new NetSuiteException(e.getMessage(), e);
            }
        } finally {
            lock.unlock();
        }
    }

    private <R> R executeUsingRequestLevelCredentials(PortOperation<R> op) throws NetSuiteException {
        lock.lock();
        try {
            relogin();

            try {
                return op.execute(port);
            } catch (Exception e) {
                throw new NetSuiteException(e.getMessage(), e);
            }
        } finally {
            lock.unlock();
        }
    }

    protected void setHeader(NetSuitePortType port, Header header) {
        BindingProvider provider = (BindingProvider) port;
        Map<String, Object> requestContext = provider.getRequestContext();
        List<Header> list = (List<Header>) requestContext.get(Header.HEADER_LIST);
        if (list == null) {
            list = new ArrayList<>();
            requestContext.put(Header.HEADER_LIST, list);
        }
        removeHeader(list, header.getName());
        list.add(header);
    }

    protected void removeHeader(QName name) {
        removeHeader(port, name);
    }

    protected void removeHeader(NetSuitePortType port, QName name) {
        BindingProvider provider = (BindingProvider) port;
        Map<String, Object> requestContext = provider.getRequestContext();
        List<Header> list = (List<Header>) requestContext.get(Header.HEADER_LIST);
        removeHeader(list, name);
    }

    private void removeHeader(List<Header> list, QName name) {
        if (list != null) {
            Iterator<Header> headerIterator = list.iterator();
            while (headerIterator.hasNext()) {
                Header header = headerIterator.next();
                if (header.getName().equals(name)) {
                    headerIterator.remove();
                }
            }
        }
    }

    private void relogin() throws NetSuiteException {
        login(true);
    }

    private void login(boolean relogin) throws NetSuiteException {
        if (relogin) {
            loggedIn = false;
        }
        if (loggedIn) {
            return;
        }

        if (port != null) {
            try {
                doLogout();
            } catch (Exception e) {
            }
        }

        doLogin();

        SearchPreferencesEx searchPreferences = new SearchPreferencesEx();
        searchPreferences.setPageSize(searchPageSize);
        searchPreferences.setBodyFieldsOnly(Boolean.valueOf(bodyFieldsOnly));

        this.searchPreferences = searchPreferences;

        PreferencesEx preferences = new PreferencesEx();
        preferences.setDisableMandatoryCustomFieldValidation(disableMandatoryCustomFieldValidation);
        preferences.setWarningAsError(treatWarningsAsErrors);

        this.preferences = preferences;

        setPreferences(port, preferences, searchPreferences);

        loggedIn = true;
    }

    public int getRetryCount() {
        return retryCount;
    }

    /**
     * Sets the number of retry attempts made when an operation fails.
     */
    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getRetryInterval() {
        return retryInterval;
    }

    /**
     * Sets the length of time (in seconds) that a session will sleep before attempting the retry of a failed operation.
     */
    public void setRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
    }

    public boolean isMessageLoggingEnabled() {
        return messageLoggingEnabled;
    }

    public void setMessageLoggingEnabled(boolean messageLoggingEnabled) {
        this.messageLoggingEnabled = messageLoggingEnabled;
    }

    protected void waitForRetryInterval() {
        try {
            Thread.sleep(getRetryInterval() * 1000);
        } catch (InterruptedException e) {

        }
    }

    protected void setPreferences(NetSuitePortType port,
            PreferencesEx nsPreferences, SearchPreferencesEx nsSearchPreferences) throws NetSuiteException {

        SearchPreferences searchPreferences = new SearchPreferences();
        Preferences preferences = new Preferences();
        try {
            BeanUtils.copyProperties(searchPreferences, nsSearchPreferences);
            BeanUtils.copyProperties(preferences, nsPreferences);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new NetSuiteException(e.getMessage(), e);
        }

        try {
            Header searchPreferencesHeader = new Header(new QName(NS_URI_PLATFORM_MESSAGES, "searchPreferences"),
                    searchPreferences, new JAXBDataBinding(searchPreferences.getClass()));

            Header preferencesHeader = new Header(new QName(NS_URI_PLATFORM_MESSAGES, "preferences"),
                    preferences, new JAXBDataBinding(preferences.getClass()));

            setHeader(port, preferencesHeader);
            setHeader(port, searchPreferencesHeader);

        } catch (JAXBException e) {
            throw new NetSuiteException("XML binding error", e);
        }
    }

    protected void setLoginHeaders(NetSuitePortType port) throws NetSuiteException {
        if (credentials.getApplicationId() != null) {
            ApplicationInfo applicationInfo = new ApplicationInfo();
            applicationInfo.setApplicationId(credentials.getApplicationId());

            try {
                if (applicationInfo != null) {
                    Header appInfoHeader = new Header(new QName(NS_URI_PLATFORM_MESSAGES, "applicationInfo"),
                            applicationInfo, new JAXBDataBinding(applicationInfo.getClass()));
                    setHeader(port, appInfoHeader);
                }
            } catch (JAXBException e) {
                throw new NetSuiteException("XML binding error", e);
            }
        }
    }

    protected void remoteLoginHeaders(NetSuitePortType port) throws NetSuiteException {
        removeHeader(port, new QName(NS_URI_PLATFORM_MESSAGES, "applicationInfo"));
    }

    protected void doLogout() throws NetSuiteException {
        try {
            LogoutRequest request = new LogoutRequest();
            port.logout(request);
        } catch (Exception e) {
            throw new NetSuiteException(e.getMessage(), e);
        }
    }

    protected void doLogin() throws NetSuiteException {
        port = getNetSuitePort(endpointUrl, credentials.getAccount());

        setLoginHeaders(port);

        PortOperation<SessionResponse> loginOp;
        if (!credentials.isUseSsoLogin()) {
            final Passport passport = createPassport();
            loginOp = new PortOperation<SessionResponse>() {
                @Override public SessionResponse execute(NetSuitePortType port) throws Exception {
                    LoginRequest request = new LoginRequest();
                    request.setPassport(passport);
                    LoginResponse response = port.login(request);
                    return response.getSessionResponse();
                }
            };
        } else {
            throw new NetSuiteException("SSO login not supported");
        }

        Status status = null;
        SessionResponse sessionResponse;
        String exceptionMessage = null;
        for (int i = 0; i < getRetryCount(); i++) {
            try {
                sessionResponse = loginOp.execute(port);
                status = sessionResponse.getStatus();

            } catch (InvalidCredentialsFault f) {
                throw new NetSuiteException(f.getFaultInfo().getMessage());
            } catch (UnexpectedErrorFault f) {
                exceptionMessage = f.getFaultInfo().getMessage();
            } catch (Exception e) {
                exceptionMessage = e.getMessage();
            }

            if (status != null) {
                break;
            }

            if (i != getRetryCount() - 1) {
                waitForRetryInterval();
            }
        }

        if (status == null || !status.getIsSuccess()) {
            String message = "Login Failed:";
            if (status != null && status.getStatusDetail() != null && status.getStatusDetail().size() > 0) {
                message = message + " " + status.getStatusDetail().get(0).getCode();
                message = message + " " + status.getStatusDetail().get(0).getMessage();
            } else if (exceptionMessage != null) {
                message = message + " " + exceptionMessage;
            }

            throw new NetSuiteException(message);
        }

        remoteLoginHeaders(port);
    }

    private Passport createPassport() {
        RecordRef roleRecord = new RecordRef();
        roleRecord.setInternalId(credentials.getRoleId());

        final Passport passport = new Passport();
        passport.setEmail(credentials.getEmail());
        passport.setPassword(credentials.getPassword());
        passport.setRole(roleRecord);
        passport.setAccount(credentials.getAccount());

        return passport;
    }

    private NetSuitePortType getNetSuitePort(String defaultEndpointUrl, String account) throws NetSuiteException {
        try {
            URL wsdlLocationUrl = this.getClass().getResource("/wsdl/2016.2/netsuite.wsdl");

            NetSuiteService service = new NetSuiteService(wsdlLocationUrl, NetSuiteService.SERVICE);

            List<WebServiceFeature> features = new ArrayList<>(2);
            if (isMessageLoggingEnabled()) {
                features.add(new LoggingFeature());
            }
            NetSuitePortType port = service.getNetSuitePort(
                    features.toArray(new WebServiceFeature[features.size()]));

            BindingProvider provider = (BindingProvider) port;
            Map<String, Object> requestContext = provider.getRequestContext();
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, defaultEndpointUrl);

            GetDataCenterUrlsRequest dataCenterRequest = new GetDataCenterUrlsRequest();
            dataCenterRequest.setAccount(account);
            DataCenterUrls urls = null;
            GetDataCenterUrlsResponse response = port.getDataCenterUrls(dataCenterRequest);
            if (response != null && response.getGetDataCenterUrlsResult() != null) {
                urls = response.getGetDataCenterUrlsResult().getDataCenterUrls();
            }
            if (urls == null) {
                throw new NetSuiteException("Can't get a correct webservice domain! "
                        + "Please check your configuration or try to run again.");
            }

            String wsDomain = urls.getWebservicesDomain();
            String endpointUrl = wsDomain.concat(new URL(defaultEndpointUrl).getPath());

            requestContext.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            return port;
        } catch (WebServiceException | MalformedURLException | InsufficientPermissionFault | InvalidCredentialsFault | InvalidSessionFault |
                UnexpectedErrorFault | ExceededRequestSizeFault e) {
            throw new NetSuiteException("Failed to get NetSuite port due to error", e);
        }
    }

    private boolean errorCanBeWorkedAround (Throwable t) {
        if (t instanceof InvalidSessionFault ||
                t instanceof RemoteException ||
                t instanceof SOAPFaultException ||
                t instanceof SocketException)
            return true;

        return false;
    }

    protected List<WriteResponseEx> toWriteResponseExList(WriteResponseList writeResponseList) {
        List<WriteResponseEx> nsWriteResponses = new ArrayList<>(writeResponseList.getWriteResponse().size());
        for (WriteResponse writeResponse : writeResponseList.getWriteResponse()) {
            nsWriteResponses.add(new WriteResponseEx(writeResponse));
        }
        return nsWriteResponses;
    }

    public interface PortOperation<R> {
        R execute(NetSuitePortType port) throws Exception;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Meta Data
    ///////////////////////////////////////////////////////////////////////////

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

    private Map<String, NsTypeDef> typeMap = new HashMap<>();
    private Map<String, NsSearchDef> searchMap = new HashMap<>();
    private Map<String, Class<?>> searchFieldMap = new HashMap<>();
    private Map<String, NsSearchFieldOperatorTypeDef> searchFieldOperatorTypeMap = new HashMap<>();
    private Map<String, String> searchFieldOperatorMap = new HashMap<>();

    protected void loadStandardMetaData() {
        NsSearchDef[] searchTable = {
                new NsSearchDef(Account.class, AccountSearch.class, AccountSearchBasic.class, AccountSearchAdvanced.class),
                new NsSearchDef(AccountingPeriod.class, AccountingPeriodSearch.class, AccountingPeriodSearchBasic.class, AccountingPeriodSearchAdvanced.class),
                new NsSearchDef(BillingSchedule.class, BillingScheduleSearch.class, BillingScheduleSearchBasic.class, BillingScheduleSearchAdvanced.class),
                new NsSearchDef(Bin.class, BinSearch.class, BinSearchBasic.class, BinSearchAdvanced.class),
                new NsSearchDef(Budget.class, BudgetSearch.class, BudgetSearchBasic.class, BudgetSearchAdvanced.class),
                new NsSearchDef(CalendarEvent.class, CalendarEventSearch.class, CalendarEventSearchBasic.class, CalendarEventSearchAdvanced.class),
                new NsSearchDef(Campaign.class, CampaignSearch.class, CampaignSearchBasic.class, CampaignSearchAdvanced.class),
                new NsSearchDef(Charge.class, ChargeSearch.class, ChargeSearchBasic.class, ChargeSearchAdvanced.class),
                new NsSearchDef(Classification.class, ClassificationSearch.class, ClassificationSearchBasic.class, ClassificationSearchAdvanced.class),
                new NsSearchDef(Contact.class, ContactSearch.class, ContactSearchBasic.class, ContactSearchAdvanced.class),
                new NsSearchDef(ContactCategory.class, ContactCategorySearch.class, ContactCategorySearchBasic.class, ContactCategorySearchAdvanced.class),
                new NsSearchDef(ContactRole.class, ContactRoleSearch.class, ContactRoleSearchBasic.class, ContactRoleSearchAdvanced.class),
                new NsSearchDef(CouponCode.class, CouponCodeSearch.class, CouponCodeSearchBasic.class, CouponCodeSearchAdvanced.class),
                new NsSearchDef(CurrencyRate.class, CurrencyRateSearch.class, CurrencyRateSearchBasic.class, CurrencyRateSearchAdvanced.class),
                new NsSearchDef(Customer.class, CustomerSearch.class, CustomerSearchBasic.class, CustomerSearchAdvanced.class),
                new NsSearchDef(CustomerCategory.class, CustomerCategorySearch.class, CustomerCategorySearchBasic.class, CustomerCategorySearchAdvanced.class),
                new NsSearchDef(CustomerMessage.class, CustomerMessageSearch.class, CustomerMessageSearchBasic.class, CustomerMessageSearchAdvanced.class),
                new NsSearchDef(CustomerStatus.class, CustomerStatusSearch.class, CustomerStatusSearchBasic.class, CustomerStatusSearchAdvanced.class),
                new NsSearchDef(CustomList.class, CustomListSearch.class, CustomListSearchBasic.class, CustomListSearchAdvanced.class),
                new NsSearchDef(Department.class, DepartmentSearch.class, DepartmentSearchBasic.class, DepartmentSearchAdvanced.class),
                new NsSearchDef(Employee.class, EmployeeSearch.class, EmployeeSearchBasic.class, EmployeeSearchAdvanced.class),
                new NsSearchDef(EntityGroup.class, EntityGroupSearch.class, EntityGroupSearchBasic.class, EntityGroupSearchAdvanced.class),
                new NsSearchDef(ExpenseCategory.class, ExpenseCategorySearch.class, ExpenseCategorySearchBasic.class, ExpenseCategorySearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.documents.filecabinet.File.class, FileSearch.class, FileSearchBasic.class, FileSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.documents.filecabinet.Folder.class, FolderSearch.class, FolderSearchBasic.class, FolderSearchAdvanced.class),
                new NsSearchDef(GiftCertificate.class, GiftCertificateSearch.class, GiftCertificateSearchBasic.class, GiftCertificateSearchAdvanced.class),
                new NsSearchDef(GlobalAccountMapping.class, GlobalAccountMappingSearch.class, GlobalAccountMappingSearchBasic.class, GlobalAccountMappingSearchAdvanced.class),
                new NsSearchDef(InventoryNumber.class, InventoryNumberSearch.class, InventoryNumberSearchBasic.class, InventoryNumberSearchAdvanced.class),
                new NsSearchDef(Issue.class, IssueSearch.class, IssueSearchBasic.class, IssueSearchAdvanced.class),
                new NsSearchDef(ItemAccountMapping.class, ItemAccountMappingSearch.class, ItemAccountMappingSearchBasic.class, ItemAccountMappingSearchAdvanced.class),
                new NsSearchDef(ItemDemandPlan.class, ItemDemandPlanSearch.class, ItemDemandPlanSearchBasic.class, ItemDemandPlanSearchAdvanced.class),
                new NsSearchDef(ItemRevision.class, ItemRevisionSearch.class, ItemRevisionSearchBasic.class, ItemRevisionSearchAdvanced.class),
                new NsSearchDef(ItemSupplyPlan.class, ItemSupplyPlanSearch.class, ItemSupplyPlanSearchBasic.class, ItemSupplyPlanSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.lists.relationships.Job.class, JobSearch.class, JobSearchBasic.class, JobSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.lists.relationships.JobStatus.class, JobStatusSearch.class, JobStatusSearchBasic.class, JobStatusSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.lists.relationships.JobType.class, JobTypeSearch.class, JobTypeSearchBasic.class, JobTypeSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.lists.accounting.Location.class, LocationSearch.class, LocationSearchBasic.class, LocationSearchAdvanced.class),
                new NsSearchDef(ManufacturingCostTemplate.class, ManufacturingCostTemplateSearch.class, ManufacturingCostTemplateSearchBasic.class, ManufacturingCostTemplateSearchAdvanced.class),
                new NsSearchDef(ManufacturingOperationTask.class, ManufacturingOperationTaskSearch.class, ManufacturingOperationTaskSearchBasic.class, ManufacturingOperationTaskSearchAdvanced.class),
                new NsSearchDef(ManufacturingRouting.class, ManufacturingRoutingSearch.class, ManufacturingRoutingSearchBasic.class, ManufacturingRoutingSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.general.communication.Message.class, MessageSearch.class, MessageSearchBasic.class, MessageSearchAdvanced.class),
                new NsSearchDef(Nexus.class, NexusSearch.class, NexusSearchBasic.class, NexusSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.general.communication.Note.class, NoteSearch.class, NoteSearchBasic.class, NoteSearchAdvanced.class),
                new NsSearchDef(NoteType.class, NoteTypeSearch.class, NoteTypeSearchBasic.class, NoteTypeSearchAdvanced.class),
                new NsSearchDef(OtherNameCategory.class, OtherNameCategorySearch.class, OtherNameCategorySearchBasic.class, OtherNameCategorySearchAdvanced.class),
                new NsSearchDef(Partner.class, PartnerSearch.class, PartnerSearchBasic.class, PartnerSearchAdvanced.class),
                new NsSearchDef(PartnerCategory.class, PartnerCategorySearch.class, PartnerCategorySearchBasic.class, PartnerCategorySearchAdvanced.class),
                new NsSearchDef(PaymentMethod.class, PaymentMethodSearch.class, PaymentMethodSearchBasic.class, PaymentMethodSearchAdvanced.class),
                new NsSearchDef(PayrollItem.class, PayrollItemSearch.class, PayrollItemSearchBasic.class, PayrollItemSearchAdvanced.class),
                new NsSearchDef(PhoneCall.class, PhoneCallSearch.class, PhoneCallSearchBasic.class, PhoneCallSearchAdvanced.class),
                new NsSearchDef(PriceLevel.class, PriceLevelSearch.class, PriceLevelSearchBasic.class, PriceLevelSearchAdvanced.class),
                new NsSearchDef(PricingGroup.class, PricingGroupSearch.class, PricingGroupSearchBasic.class, PricingGroupSearchAdvanced.class),
                new NsSearchDef(ProjectTask.class, ProjectTaskSearch.class, ProjectTaskSearchBasic.class, ProjectTaskSearchAdvanced.class),
                new NsSearchDef(PromotionCode.class, PromotionCodeSearch.class, PromotionCodeSearchBasic.class, PromotionCodeSearchAdvanced.class),
                new NsSearchDef(ResourceAllocation.class, ResourceAllocationSearch.class, ResourceAllocationSearchBasic.class, ResourceAllocationSearchAdvanced.class),
                new NsSearchDef(RevRecSchedule.class, RevRecScheduleSearch.class, RevRecScheduleSearchBasic.class, RevRecScheduleSearchAdvanced.class),
                new NsSearchDef(RevRecTemplate.class, RevRecTemplateSearch.class, RevRecTemplateSearchBasic.class, RevRecTemplateSearchAdvanced.class),
                new NsSearchDef(SalesRole.class, SalesRoleSearch.class, SalesRoleSearchBasic.class, SalesRoleSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.lists.website.SiteCategory.class, SiteCategorySearch.class, SiteCategorySearchBasic.class, SiteCategorySearchAdvanced.class),
                new NsSearchDef(Solution.class, SolutionSearch.class, SolutionSearchBasic.class, SolutionSearchAdvanced.class),
                new NsSearchDef(Subsidiary.class, SubsidiarySearch.class, SubsidiarySearchBasic.class, SubsidiarySearchAdvanced.class),
                new NsSearchDef(SupportCase.class, SupportCaseSearch.class, SupportCaseSearchBasic.class, SupportCaseSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.activities.scheduling.Task.class, TaskSearch.class, TaskSearchBasic.class, TaskSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.lists.accounting.Term.class, TermSearch.class, TermSearchBasic.class, TermSearchAdvanced.class),
                new NsSearchDef(TimeBill.class, TimeBillSearch.class, TimeBillSearchBasic.class, TimeBillSearchAdvanced.class),
                new NsSearchDef(TimeEntry.class, TimeEntrySearch.class, TimeEntrySearchBasic.class, TimeEntrySearchAdvanced.class),
                new NsSearchDef(TimeSheet.class, TimeSheetSearch.class, TimeSheetSearchBasic.class, TimeSheetSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.lists.support.Topic.class, TopicSearch.class, TopicSearchBasic.class, TopicSearchAdvanced.class),
                new NsSearchDef(UnitsType.class, UnitsTypeSearch.class, UnitsTypeSearchBasic.class, UnitsTypeSearchAdvanced.class),
                new NsSearchDef(Vendor.class, VendorSearch.class, VendorSearchBasic.class, VendorSearchAdvanced.class),
                new NsSearchDef(VendorCategory.class, VendorCategorySearch.class, VendorCategorySearchBasic.class, VendorCategorySearchAdvanced.class),
                new NsSearchDef(WinLossReason.class, WinLossReasonSearch.class, WinLossReasonSearchBasic.class, WinLossReasonSearchAdvanced.class),

                new NsSearchDef(AssemblyBuild.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(AssemblyUnbuild.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(BinTransfer.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(BinWorksheet.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(CashRefund.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(CashSale.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(Check.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(CreditMemo.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(CustomerDeposit.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(CustomerPayment.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(CustomerRefund.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(Deposit.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(DepositApplication.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(Estimate.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(ExpenseReport.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(InterCompanyJournalEntry.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(InventoryAdjustment.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(InventoryCostRevaluation.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(InventoryTransfer.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(Invoice.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(ItemFulfillment.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(ItemReceipt.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(JournalEntry.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(Opportunity.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(PaycheckJournal.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(PurchaseOrder.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(ReturnAuthorization.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(SalesOrder.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(com.netsuite.webservices.lists.accounting.State.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(TransferOrder.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(VendorBill.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(VendorCredit.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(VendorPayment.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(VendorReturnAuthorization.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(WorkOrder.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(WorkOrderClose.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(WorkOrderCompletion.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),
                new NsSearchDef(WorkOrderIssue.class, TransactionSearch.class, TransactionSearchBasic.class, TransactionSearchAdvanced.class),

                new NsSearchDef(CustomRecord.class, CustomRecordSearch.class, CustomRecordSearchBasic.class, CustomRecordSearchAdvanced.class)
        };
        registerSearchDefs(searchTable);

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

        NsSearchFieldOperatorTypeDef<?>[] searchFieldOperatorTable = {
                // Date
                new NsSearchFieldOperatorTypeDef<>("Date",
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
                new NsSearchFieldOperatorTypeDef<>("PredefinedDate",
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
                new NsSearchFieldOperatorTypeDef<>("Double",
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
                new NsSearchFieldOperatorTypeDef<>("Numeric",
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
                new NsSearchFieldOperatorTypeDef<>("String",
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
                new NsSearchFieldOperatorTypeDef<>("List",
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
                new NsSearchFieldOperatorTypeDef<>("List",
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
                new NsSearchFieldOperatorTypeDef<>("Boolean",
                        NsSearchFieldOperatorTypeDef.SearchBooleanFieldOperator.class, null, null)
        };
        registerSearchFieldOperatorTypeDefs(searchFieldOperatorTable);
    }

    protected void registerType(Class<?> typeClass, String typeName) {
        String typeNameToRegister = typeName != null ? typeName : typeClass.getSimpleName();
        if (typeMap.containsKey(typeNameToRegister)) {
            NsTypeDef entityInfo = typeMap.get(typeNameToRegister);
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
        List<NsFieldDef> fields = new ArrayList<>(propertyInfos.size());
        for (PropertyInfo propertyInfo : propertyInfos) {
            String fieldName = propertyInfo.getName();
            Class fieldValueType = propertyInfo.getReadType();
            if ((fieldName.equals("class") && fieldValueType == Class.class) ||
                    (fieldName.equals("nullFieldList") && fieldValueType.getSimpleName().equals("NullField"))) {
                continue;
            }
            boolean isKeyField = isKeyField(typeClass, propertyInfo);
            NsFieldDef fieldInfo = new NsFieldDef(fieldName, fieldValueType, isKeyField, true);
            fields.add(fieldInfo);
        }

        NsTypeDef entityInfo = new NsTypeDef(typeName, typeClass, fields);
        typeMap.put(typeNameToRegister, entityInfo);
    }

    protected void registerSearchDefs(NsSearchDef[] searchTable) {
        for (NsSearchDef entry : searchTable) {
            String typeName = entry.getRecordTypeName();

            registerType(entry.getRecordClass(), typeName);
            registerType(entry.getSearchClass(), null);
            registerType(entry.getSearchBasicClass(), null);
            registerType(entry.getSearchAdvancedClass(), null);

            if (searchMap.containsKey(typeName)) {
                throw new IllegalArgumentException(
                        "Search entry already registered: "
                                + typeName + ", search classes to register are "
                                + entry.getSearchClass() + ", "
                                + entry.getSearchBasicClass() + ", "
                                + entry.getSearchAdvancedClass());
            }
            searchMap.put(typeName, entry);
        }
    }

    protected void registerSearchFieldDefs(Class<?>[] searchFieldTable) {
        searchFieldMap = new HashMap<>(searchFieldTable.length);
        for (Class<?> entry : searchFieldTable) {
            searchFieldMap.put(entry.getSimpleName(), entry);
        }
    }

    protected void registerSearchFieldOperatorTypeDefs(NsSearchFieldOperatorTypeDef[] searchFieldOperatorTable) {
        searchFieldOperatorTypeMap = new HashMap<>(searchFieldOperatorTable.length);
        for (NsSearchFieldOperatorTypeDef info : searchFieldOperatorTable) {
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
        return Arrays.asList(itemTypeList);
    }

    public NsTypeDef getTypeDef(String typeName) {
        return typeMap.get(typeName);
    }

    public NsTypeDef getTypeDef(Class<?> clazz) {
        return typeMap.get(clazz.getSimpleName());
    }

    public Collection<String> getRecordTypes() {
        return Collections.unmodifiableSet(searchMap.keySet());
    }

    public NsSearchDef getSearchDef(String typeName) {
        return searchMap.get(typeName);
    }

    public Class<?> getSearchFieldClass(String searchFieldType) {
        return searchFieldMap.get(searchFieldType);
    }

    public Object getSearchFieldOperatorByName(String searchFieldType, String searchFieldOperatorName) {
        NsSearchFieldOperatorTypeDef.QualifiedName operatorQName =
                new NsSearchFieldOperatorTypeDef.QualifiedName(searchFieldOperatorName);
        String searchFieldOperatorType = searchFieldOperatorMap.get(searchFieldType);
        if (searchFieldOperatorType != null) {
            NsSearchFieldOperatorTypeDef def = searchFieldOperatorTypeMap.get(searchFieldOperatorType);
            return def.getOperator(searchFieldOperatorName);
        }
        for (NsSearchFieldOperatorTypeDef def : searchFieldOperatorTypeMap.values()) {
            if (def.hasOperatorName(operatorQName)) {
                return def.getOperator(searchFieldOperatorName);
            }
        }
        return null;
    }

    public Collection<NsSearchFieldOperatorTypeDef.QualifiedName> getSearchOperatorNames() {
        Set<NsSearchFieldOperatorTypeDef.QualifiedName> names = new HashSet<>();
        for (NsSearchFieldOperatorTypeDef info : searchFieldOperatorTypeMap.values()) {
            names.addAll(info.getOperatorNames());
        }
        return Collections.unmodifiableSet(names);
    }

    public <T> T createType(String typeName) throws NetSuiteException {
        NsTypeDef typeDef = getTypeDef(typeName);
        if (typeDef == null) {
            throw new NetSuiteException("Unknown type: " + typeName);
        }
        return (T) createInstance(typeDef.getTypeClass());
    }

    protected <T> T createInstance(Class<T> clazz) throws NetSuiteException {
        try {
            T target = clazz.cast(clazz.newInstance());
            return target;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new NetSuiteException("Failed to instantiate object: " + clazz, e);
        }
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

    protected void updateCustomMetaData() throws NetSuiteException {

    }

    public static String toInitialUpper(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    public static String toInitialLower(String value) {
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }

    public static String toNetSuiteType(String value) {
        return "_" + toInitialLower(value);
    }
}
