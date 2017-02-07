
package com.netsuite.webservices.v2014_2.platform.common.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PermissionCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PermissionCode"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="_accessPaymentAuditLog"/&gt;
 *     &lt;enumeration value="_accountDetail"/&gt;
 *     &lt;enumeration value="_accounting"/&gt;
 *     &lt;enumeration value="_accountingBook"/&gt;
 *     &lt;enumeration value="_accountingLists"/&gt;
 *     &lt;enumeration value="_accounts"/&gt;
 *     &lt;enumeration value="_accountsPayable"/&gt;
 *     &lt;enumeration value="_accountsPayableGraphing"/&gt;
 *     &lt;enumeration value="_accountsPayableRegister"/&gt;
 *     &lt;enumeration value="_accountsReceivable"/&gt;
 *     &lt;enumeration value="_accountsReceivableGraphing"/&gt;
 *     &lt;enumeration value="_accountsReceivableRegister"/&gt;
 *     &lt;enumeration value="_accountsReceivableUnbilled"/&gt;
 *     &lt;enumeration value="_adjustInventory"/&gt;
 *     &lt;enumeration value="_adjustInventoryWorksheet"/&gt;
 *     &lt;enumeration value="_admindocs"/&gt;
 *     &lt;enumeration value="_adpImportData"/&gt;
 *     &lt;enumeration value="_adpSetup"/&gt;
 *     &lt;enumeration value="_advancedAnalytics"/&gt;
 *     &lt;enumeration value="_advancedPDFHTMLTemplates"/&gt;
 *     &lt;enumeration value="_allocationSchedules"/&gt;
 *     &lt;enumeration value="_allowNonGLChanges"/&gt;
 *     &lt;enumeration value="_amendW4"/&gt;
 *     &lt;enumeration value="_amortizationReports"/&gt;
 *     &lt;enumeration value="_amortizationSchedules"/&gt;
 *     &lt;enumeration value="_appDefinitionsAndPackages"/&gt;
 *     &lt;enumeration value="_applicationPublishers"/&gt;
 *     &lt;enumeration value="_approveDirectDeposit"/&gt;
 *     &lt;enumeration value="_approveEFT"/&gt;
 *     &lt;enumeration value="_approveOnlineBillPayments"/&gt;
 *     &lt;enumeration value="_approveVendorPayments"/&gt;
 *     &lt;enumeration value="_auditTrail"/&gt;
 *     &lt;enumeration value="_backupYourData"/&gt;
 *     &lt;enumeration value="_balanceSheet"/&gt;
 *     &lt;enumeration value="_bankAccountRegisters"/&gt;
 *     &lt;enumeration value="_billingInformation"/&gt;
 *     &lt;enumeration value="_billingSchedules"/&gt;
 *     &lt;enumeration value="_billingSetup"/&gt;
 *     &lt;enumeration value="_billOfDistribution"/&gt;
 *     &lt;enumeration value="_billOfMaterialsInquiry"/&gt;
 *     &lt;enumeration value="_billPurchaseOrders"/&gt;
 *     &lt;enumeration value="_bills"/&gt;
 *     &lt;enumeration value="_billSalesOrders"/&gt;
 *     &lt;enumeration value="_bins"/&gt;
 *     &lt;enumeration value="_binTransfer"/&gt;
 *     &lt;enumeration value="_binWorksheet"/&gt;
 *     &lt;enumeration value="_blanketPurchaseOrder"/&gt;
 *     &lt;enumeration value="_blanketPurchaseOrderApproval"/&gt;
 *     &lt;enumeration value="_budget"/&gt;
 *     &lt;enumeration value="_buildAssemblies"/&gt;
 *     &lt;enumeration value="_buildWorkOrders"/&gt;
 *     &lt;enumeration value="_calculateTime"/&gt;
 *     &lt;enumeration value="_calendar"/&gt;
 *     &lt;enumeration value="_campaignHistory"/&gt;
 *     &lt;enumeration value="_cases"/&gt;
 *     &lt;enumeration value="_cashFlowStatement"/&gt;
 *     &lt;enumeration value="_cashSale"/&gt;
 *     &lt;enumeration value="_cashSaleRefund"/&gt;
 *     &lt;enumeration value="_changeEmailOrPassword"/&gt;
 *     &lt;enumeration value="_changeRole"/&gt;
 *     &lt;enumeration value="_chargeRule"/&gt;
 *     &lt;enumeration value="_chargeRunRules"/&gt;
 *     &lt;enumeration value="_check"/&gt;
 *     &lt;enumeration value="_checkItemAvailability"/&gt;
 *     &lt;enumeration value="_classes"/&gt;
 *     &lt;enumeration value="_classSegmentMapping"/&gt;
 *     &lt;enumeration value="_closeAccount"/&gt;
 *     &lt;enumeration value="_closeWorkOrders"/&gt;
 *     &lt;enumeration value="_colorThemes"/&gt;
 *     &lt;enumeration value="_commissionFeatureSetup"/&gt;
 *     &lt;enumeration value="_commissionReports"/&gt;
 *     &lt;enumeration value="_commitOrders"/&gt;
 *     &lt;enumeration value="_commitPayroll"/&gt;
 *     &lt;enumeration value="_companies"/&gt;
 *     &lt;enumeration value="_companyInformation"/&gt;
 *     &lt;enumeration value="_companyPreferences"/&gt;
 *     &lt;enumeration value="_competitors"/&gt;
 *     &lt;enumeration value="_componentWhereUsed"/&gt;
 *     &lt;enumeration value="_contactRoles"/&gt;
 *     &lt;enumeration value="_contacts"/&gt;
 *     &lt;enumeration value="_convertClassesToDepartments"/&gt;
 *     &lt;enumeration value="_convertClassesToLocations"/&gt;
 *     &lt;enumeration value="_copyBudgets"/&gt;
 *     &lt;enumeration value="_copyChartOfAccountsToNewCompany"/&gt;
 *     &lt;enumeration value="_copyProjectTasks"/&gt;
 *     &lt;enumeration value="_costedBillOfMaterialsInquiry"/&gt;
 *     &lt;enumeration value="_costOfGoodsSoldRegisters"/&gt;
 *     &lt;enumeration value="_countInventory"/&gt;
 *     &lt;enumeration value="_createAllocationSchedules"/&gt;
 *     &lt;enumeration value="_createConsolidationCompany"/&gt;
 *     &lt;enumeration value="_createFiscalCalendar"/&gt;
 *     &lt;enumeration value="_createInventoryCounts"/&gt;
 *     &lt;enumeration value="_creditCard"/&gt;
 *     &lt;enumeration value="_creditCardProcessing"/&gt;
 *     &lt;enumeration value="_creditCardRefund"/&gt;
 *     &lt;enumeration value="_creditCardRegisters"/&gt;
 *     &lt;enumeration value="_creditMemo"/&gt;
 *     &lt;enumeration value="_creditReturns"/&gt;
 *     &lt;enumeration value="_crmGroups"/&gt;
 *     &lt;enumeration value="_crmLists"/&gt;
 *     &lt;enumeration value="_currency"/&gt;
 *     &lt;enumeration value="_currencyAdjustmentJournal"/&gt;
 *     &lt;enumeration value="_currencyRevaluation"/&gt;
 *     &lt;enumeration value="_customAddressForm"/&gt;
 *     &lt;enumeration value="_customBodyFields"/&gt;
 *     &lt;enumeration value="_customCenterCategories"/&gt;
 *     &lt;enumeration value="_customCenterLinks"/&gt;
 *     &lt;enumeration value="_customCenters"/&gt;
 *     &lt;enumeration value="_customCenterTabs"/&gt;
 *     &lt;enumeration value="_customColumnFields"/&gt;
 *     &lt;enumeration value="_customEntityFields"/&gt;
 *     &lt;enumeration value="_customEntryForms"/&gt;
 *     &lt;enumeration value="_customerCharge"/&gt;
 *     &lt;enumeration value="_customerDeposit"/&gt;
 *     &lt;enumeration value="_customerPayment"/&gt;
 *     &lt;enumeration value="_customerProfile"/&gt;
 *     &lt;enumeration value="_customerRefund"/&gt;
 *     &lt;enumeration value="_customers"/&gt;
 *     &lt;enumeration value="_customerStatus"/&gt;
 *     &lt;enumeration value="_customEventFields"/&gt;
 *     &lt;enumeration value="_customFields"/&gt;
 *     &lt;enumeration value="_customHTMLLayouts"/&gt;
 *     &lt;enumeration value="_customItemFields"/&gt;
 *     &lt;enumeration value="_customItemNumberFields"/&gt;
 *     &lt;enumeration value="_customizePage"/&gt;
 *     &lt;enumeration value="_customLists"/&gt;
 *     &lt;enumeration value="_customPDFLayouts"/&gt;
 *     &lt;enumeration value="_customRecordEntries"/&gt;
 *     &lt;enumeration value="_customRecordTypes"/&gt;
 *     &lt;enumeration value="_customSublists"/&gt;
 *     &lt;enumeration value="_customSubtabs"/&gt;
 *     &lt;enumeration value="_customTransactionFields"/&gt;
 *     &lt;enumeration value="_customTransactionForms"/&gt;
 *     &lt;enumeration value="_deferredExpenseRegisters"/&gt;
 *     &lt;enumeration value="_deferredRevenueRegisters"/&gt;
 *     &lt;enumeration value="_deleteAllData"/&gt;
 *     &lt;enumeration value="_deletedRecords"/&gt;
 *     &lt;enumeration value="_deleteEvent"/&gt;
 *     &lt;enumeration value="_departments"/&gt;
 *     &lt;enumeration value="_departmentSegmentMapping"/&gt;
 *     &lt;enumeration value="_deposit"/&gt;
 *     &lt;enumeration value="_depositApplication"/&gt;
 *     &lt;enumeration value="_directDepositStatus"/&gt;
 *     &lt;enumeration value="_distributeInventory"/&gt;
 *     &lt;enumeration value="_distributionNetwork"/&gt;
 *     &lt;enumeration value="_documentsAndFiles"/&gt;
 *     &lt;enumeration value="_duplicateCaseManagement"/&gt;
 *     &lt;enumeration value="_duplicateDetectionSetup"/&gt;
 *     &lt;enumeration value="_duplicateRecordManagement"/&gt;
 *     &lt;enumeration value="_ebayExportImport"/&gt;
 *     &lt;enumeration value="_editForecast"/&gt;
 *     &lt;enumeration value="_editManagerForecast"/&gt;
 *     &lt;enumeration value="_editProfile"/&gt;
 *     &lt;enumeration value="_eftStatus"/&gt;
 *     &lt;enumeration value="_emailReports"/&gt;
 *     &lt;enumeration value="_emailTemplate"/&gt;
 *     &lt;enumeration value="_employeeCommissionSchedulesPlans"/&gt;
 *     &lt;enumeration value="_employeeCommissionTransaction"/&gt;
 *     &lt;enumeration value="_employeeCommissionTransactionApproval"/&gt;
 *     &lt;enumeration value="_employeeReminders"/&gt;
 *     &lt;enumeration value="_employees"/&gt;
 *     &lt;enumeration value="_employeeSocialSecurityNumbers"/&gt;
 *     &lt;enumeration value="_enableFeatures"/&gt;
 *     &lt;enumeration value="_enterCompletions"/&gt;
 *     &lt;enumeration value="_enterOpeningBalances"/&gt;
 *     &lt;enumeration value="_enterVendorCredits"/&gt;
 *     &lt;enumeration value="_enterYearToDatePayrollAdjustments"/&gt;
 *     &lt;enumeration value="_entityAccountMapping"/&gt;
 *     &lt;enumeration value="_equityRegisters"/&gt;
 *     &lt;enumeration value="_escalationAssignment"/&gt;
 *     &lt;enumeration value="_escalationAssignmentRule"/&gt;
 *     &lt;enumeration value="_establishQuotas"/&gt;
 *     &lt;enumeration value="_estimate"/&gt;
 *     &lt;enumeration value="_events"/&gt;
 *     &lt;enumeration value="_expenseCategories"/&gt;
 *     &lt;enumeration value="_expenseRegisters"/&gt;
 *     &lt;enumeration value="_expenseReport"/&gt;
 *     &lt;enumeration value="_expenses"/&gt;
 *     &lt;enumeration value="_exportAsIIF"/&gt;
 *     &lt;enumeration value="_exportLists"/&gt;
 *     &lt;enumeration value="_faxMessages"/&gt;
 *     &lt;enumeration value="_faxTemplate"/&gt;
 *     &lt;enumeration value="_features"/&gt;
 *     &lt;enumeration value="_financeCharge"/&gt;
 *     &lt;enumeration value="_financeChargePreferences"/&gt;
 *     &lt;enumeration value="_financialHistory"/&gt;
 *     &lt;enumeration value="_financialStatementLayouts"/&gt;
 *     &lt;enumeration value="_financialStatements"/&gt;
 *     &lt;enumeration value="_financialStatementSections"/&gt;
 *     &lt;enumeration value="_findMatchingOnlineBankingTransactions"/&gt;
 *     &lt;enumeration value="_findTransaction"/&gt;
 *     &lt;enumeration value="_fiscalCalendars"/&gt;
 *     &lt;enumeration value="_fixedAssetRegisters"/&gt;
 *     &lt;enumeration value="_form1099FederalMiscellaneousIncome"/&gt;
 *     &lt;enumeration value="_form940EmployersAnnualFederalUnemploymentTaxReturn"/&gt;
 *     &lt;enumeration value="_form941EmployersQuarterlyFederalTaxReturn"/&gt;
 *     &lt;enumeration value="_formW2WageAndTaxStatement"/&gt;
 *     &lt;enumeration value="_formW4EmployeesWithholdingAllowanceCertificate"/&gt;
 *     &lt;enumeration value="_fulfillOrders"/&gt;
 *     &lt;enumeration value="_generalLedger"/&gt;
 *     &lt;enumeration value="_generatePriceLists"/&gt;
 *     &lt;enumeration value="_generateRevenueCommitment"/&gt;
 *     &lt;enumeration value="_generateRevenueCommitmentReversals"/&gt;
 *     &lt;enumeration value="_generateSingleOrderRevenueContracts"/&gt;
 *     &lt;enumeration value="_generateStatements"/&gt;
 *     &lt;enumeration value="_genericAdminPermission"/&gt;
 *     &lt;enumeration value="_genericResources"/&gt;
 *     &lt;enumeration value="_globalAccountMapping"/&gt;
 *     &lt;enumeration value="_grantingAccessToReports"/&gt;
 *     &lt;enumeration value="_gstSummaryReport"/&gt;
 *     &lt;enumeration value="_importCSVFile"/&gt;
 *     &lt;enumeration value="_importOnlineBankingQIFFile"/&gt;
 *     &lt;enumeration value="_importQuickenQIFFile"/&gt;
 *     &lt;enumeration value="_importStateSalesTax"/&gt;
 *     &lt;enumeration value="_income"/&gt;
 *     &lt;enumeration value="_incomeRegisters"/&gt;
 *     &lt;enumeration value="_incomeStatement"/&gt;
 *     &lt;enumeration value="_individualPaycheck"/&gt;
 *     &lt;enumeration value="_integration"/&gt;
 *     &lt;enumeration value="_intercompanyAdjustments"/&gt;
 *     &lt;enumeration value="_internalPublisher"/&gt;
 *     &lt;enumeration value="_inventory"/&gt;
 *     &lt;enumeration value="_inventoryCostTemplate"/&gt;
 *     &lt;enumeration value="_invoice"/&gt;
 *     &lt;enumeration value="_invoiceApproval"/&gt;
 *     &lt;enumeration value="_issueComponents"/&gt;
 *     &lt;enumeration value="_issueReports"/&gt;
 *     &lt;enumeration value="_issues"/&gt;
 *     &lt;enumeration value="_issueSetup"/&gt;
 *     &lt;enumeration value="_itemAccountMapping"/&gt;
 *     &lt;enumeration value="_itemCategoryLayouts"/&gt;
 *     &lt;enumeration value="_itemDemandPlan"/&gt;
 *     &lt;enumeration value="_itemFulfillment"/&gt;
 *     &lt;enumeration value="_itemReceipt"/&gt;
 *     &lt;enumeration value="_itemRevisions"/&gt;
 *     &lt;enumeration value="_items"/&gt;
 *     &lt;enumeration value="_itemSupplyPlan"/&gt;
 *     &lt;enumeration value="_itemTemplates"/&gt;
 *     &lt;enumeration value="_jobs"/&gt;
 *     &lt;enumeration value="_journalApproval"/&gt;
 *     &lt;enumeration value="_knowledgeBase"/&gt;
 *     &lt;enumeration value="_kpiScorecards"/&gt;
 *     &lt;enumeration value="_leadConversion"/&gt;
 *     &lt;enumeration value="_leadConversionMapping"/&gt;
 *     &lt;enumeration value="_leadSnapshotReminders"/&gt;
 *     &lt;enumeration value="_letterMessages"/&gt;
 *     &lt;enumeration value="_letterTemplate"/&gt;
 *     &lt;enumeration value="_loadSampleData"/&gt;
 *     &lt;enumeration value="_locations"/&gt;
 *     &lt;enumeration value="_locationSegmentMapping"/&gt;
 *     &lt;enumeration value="_lockTransactions"/&gt;
 *     &lt;enumeration value="_longTermLiabilityRegisters"/&gt;
 *     &lt;enumeration value="_mailMerge"/&gt;
 *     &lt;enumeration value="_makeJournalEntry"/&gt;
 *     &lt;enumeration value="_manageAccountingPeriods"/&gt;
 *     &lt;enumeration value="_managePayroll"/&gt;
 *     &lt;enumeration value="_manageRoles"/&gt;
 *     &lt;enumeration value="_manageTaxReportingPeriods"/&gt;
 *     &lt;enumeration value="_manageUsers"/&gt;
 *     &lt;enumeration value="_manufacturingCostTemplate"/&gt;
 *     &lt;enumeration value="_manufacturingRouting"/&gt;
 *     &lt;enumeration value="_marketingCampaignReports"/&gt;
 *     &lt;enumeration value="_marketingCampaigns"/&gt;
 *     &lt;enumeration value="_marketingTemplate"/&gt;
 *     &lt;enumeration value="_markIssueAsShowStopper"/&gt;
 *     &lt;enumeration value="_markWorkOrdersBuilt"/&gt;
 *     &lt;enumeration value="_markWorkOrdersFirmed"/&gt;
 *     &lt;enumeration value="_markWorkOrdersReleased"/&gt;
 *     &lt;enumeration value="_massUpdates"/&gt;
 *     &lt;enumeration value="_mediaFolders"/&gt;
 *     &lt;enumeration value="_memorizedTransactions"/&gt;
 *     &lt;enumeration value="_mobileDeviceAccess"/&gt;
 *     &lt;enumeration value="_netWorth"/&gt;
 *     &lt;enumeration value="_nonPostingRegisters"/&gt;
 *     &lt;enumeration value="_noPermissionNecessary"/&gt;
 *     &lt;enumeration value="_notesTab"/&gt;
 *     &lt;enumeration value="_offlineClient"/&gt;
 *     &lt;enumeration value="_onlineBankingStatement"/&gt;
 *     &lt;enumeration value="_onlineCaseForm"/&gt;
 *     &lt;enumeration value="_onlineCustomerForm"/&gt;
 *     &lt;enumeration value="_onlineCustomRecordForm"/&gt;
 *     &lt;enumeration value="_openidSingleSignOn"/&gt;
 *     &lt;enumeration value="_opportunity"/&gt;
 *     &lt;enumeration value="_otherAssetRegisters"/&gt;
 *     &lt;enumeration value="_otherCurrentAssetRegisters"/&gt;
 *     &lt;enumeration value="_otherCurrentLiabilityRegisters"/&gt;
 *     &lt;enumeration value="_otherCustomFields"/&gt;
 *     &lt;enumeration value="_otherExpenseRegisters"/&gt;
 *     &lt;enumeration value="_otherIncomeRegisters"/&gt;
 *     &lt;enumeration value="_otherLists"/&gt;
 *     &lt;enumeration value="_otherNames"/&gt;
 *     &lt;enumeration value="_outlookIntegration"/&gt;
 *     &lt;enumeration value="_outlookIntegration3"/&gt;
 *     &lt;enumeration value="_overrideEstimatedCostOnTransactions"/&gt;
 *     &lt;enumeration value="_overridePaymentHold"/&gt;
 *     &lt;enumeration value="_overridePeriodRestrictions"/&gt;
 *     &lt;enumeration value="_partnerAuthorizedCommissionReports"/&gt;
 *     &lt;enumeration value="_partnerCommissionReports"/&gt;
 *     &lt;enumeration value="_partnerCommissionSchedulesPlans"/&gt;
 *     &lt;enumeration value="_partnerCommissionTransaction"/&gt;
 *     &lt;enumeration value="_partnerCommissionTransactionApproval"/&gt;
 *     &lt;enumeration value="_partnerContribution"/&gt;
 *     &lt;enumeration value="_partnerMonitorResults"/&gt;
 *     &lt;enumeration value="_partners"/&gt;
 *     &lt;enumeration value="_payBills"/&gt;
 *     &lt;enumeration value="_paycheckJournal"/&gt;
 *     &lt;enumeration value="_paychecks"/&gt;
 *     &lt;enumeration value="_paymentMethods"/&gt;
 *     &lt;enumeration value="_payrollCheckRegister"/&gt;
 *     &lt;enumeration value="_payrollHoursAndEarnings"/&gt;
 *     &lt;enumeration value="_payrollItems"/&gt;
 *     &lt;enumeration value="_payrollJournalReport"/&gt;
 *     &lt;enumeration value="_payrollLiabilityPayments"/&gt;
 *     &lt;enumeration value="_payrollLiabilityReport"/&gt;
 *     &lt;enumeration value="_payrollStateWithholding"/&gt;
 *     &lt;enumeration value="_payrollSummaryAndDetailReports"/&gt;
 *     &lt;enumeration value="_paySalesTax"/&gt;
 *     &lt;enumeration value="_payTaxLiability"/&gt;
 *     &lt;enumeration value="_pdfMessages"/&gt;
 *     &lt;enumeration value="_pdfTemplate"/&gt;
 *     &lt;enumeration value="_performSearch"/&gt;
 *     &lt;enumeration value="_phoneCalls"/&gt;
 *     &lt;enumeration value="_plannedStandardCost"/&gt;
 *     &lt;enumeration value="_postingPeriodOnTransactions"/&gt;
 *     &lt;enumeration value="_postTime"/&gt;
 *     &lt;enumeration value="_postVendorBillVariances"/&gt;
 *     &lt;enumeration value="_presentationCategories"/&gt;
 *     &lt;enumeration value="_printChecksAndForms"/&gt;
 *     &lt;enumeration value="_printEmailFax"/&gt;
 *     &lt;enumeration value="_printShipmentDocuments"/&gt;
 *     &lt;enumeration value="_processGSTRefund"/&gt;
 *     &lt;enumeration value="_processPayroll"/&gt;
 *     &lt;enumeration value="_projectProjectTemplateConversion"/&gt;
 *     &lt;enumeration value="_projectTasks"/&gt;
 *     &lt;enumeration value="_projectTemplates"/&gt;
 *     &lt;enumeration value="_promotionCode"/&gt;
 *     &lt;enumeration value="_provisioning"/&gt;
 *     &lt;enumeration value="_provisioningForQa"/&gt;
 *     &lt;enumeration value="_provisionTestDrive"/&gt;
 *     &lt;enumeration value="_pstSummaryReport"/&gt;
 *     &lt;enumeration value="_publicTemplateCategories"/&gt;
 *     &lt;enumeration value="_publishDashboards"/&gt;
 *     &lt;enumeration value="_publishEmployeeList"/&gt;
 *     &lt;enumeration value="_publishForms"/&gt;
 *     &lt;enumeration value="_publishKnowledgeBase"/&gt;
 *     &lt;enumeration value="_publishRSSFeeds"/&gt;
 *     &lt;enumeration value="_publishSearch"/&gt;
 *     &lt;enumeration value="_purchaseContract"/&gt;
 *     &lt;enumeration value="_purchaseContractApproval"/&gt;
 *     &lt;enumeration value="_purchaseOrder"/&gt;
 *     &lt;enumeration value="_purchaseOrderReports"/&gt;
 *     &lt;enumeration value="_purchases"/&gt;
 *     &lt;enumeration value="_quantityPricingSchedules"/&gt;
 *     &lt;enumeration value="_quotaReports"/&gt;
 *     &lt;enumeration value="_receiveOrder"/&gt;
 *     &lt;enumeration value="_receiveReturns"/&gt;
 *     &lt;enumeration value="_recognizeGiftCertificateIncome"/&gt;
 *     &lt;enumeration value="_reconcile"/&gt;
 *     &lt;enumeration value="_reconcileReporting"/&gt;
 *     &lt;enumeration value="_recordCustomField"/&gt;
 *     &lt;enumeration value="_refundReturns"/&gt;
 *     &lt;enumeration value="_relatedItems"/&gt;
 *     &lt;enumeration value="_reportCustomization"/&gt;
 *     &lt;enumeration value="_reportScheduling"/&gt;
 *     &lt;enumeration value="_requisition"/&gt;
 *     &lt;enumeration value="_requisitionApproval"/&gt;
 *     &lt;enumeration value="_resource"/&gt;
 *     &lt;enumeration value="_resourceAllocationApproval"/&gt;
 *     &lt;enumeration value="_resourceAllocationReports"/&gt;
 *     &lt;enumeration value="_resourceAllocations"/&gt;
 *     &lt;enumeration value="_returnAuthApproval"/&gt;
 *     &lt;enumeration value="_returnAuthorization"/&gt;
 *     &lt;enumeration value="_returnAuthorizationReports"/&gt;
 *     &lt;enumeration value="_revalueInventoryCost"/&gt;
 *     &lt;enumeration value="_revenueCommitment"/&gt;
 *     &lt;enumeration value="_revenueCommitmentReversal"/&gt;
 *     &lt;enumeration value="_revenueContracts"/&gt;
 *     &lt;enumeration value="_revenueManagementVSOE"/&gt;
 *     &lt;enumeration value="_revenueRecognitionReports"/&gt;
 *     &lt;enumeration value="_revenueRecognitionSchedules"/&gt;
 *     &lt;enumeration value="_roles"/&gt;
 *     &lt;enumeration value="_runPayroll"/&gt;
 *     &lt;enumeration value="_sales"/&gt;
 *     &lt;enumeration value="_salesByPartner"/&gt;
 *     &lt;enumeration value="_salesByPromotionCode"/&gt;
 *     &lt;enumeration value="_salesCampaigns"/&gt;
 *     &lt;enumeration value="_salesForceAutomation"/&gt;
 *     &lt;enumeration value="_salesForceAutomationSetup"/&gt;
 *     &lt;enumeration value="_salesOrder"/&gt;
 *     &lt;enumeration value="_salesOrderApproval"/&gt;
 *     &lt;enumeration value="_salesOrderFulfillmentReports"/&gt;
 *     &lt;enumeration value="_salesOrderReports"/&gt;
 *     &lt;enumeration value="_salesOrderTransactionReport"/&gt;
 *     &lt;enumeration value="_salesRoles"/&gt;
 *     &lt;enumeration value="_salesTerritory"/&gt;
 *     &lt;enumeration value="_salesTerritoryRule"/&gt;
 *     &lt;enumeration value="_samlSingleSignOn"/&gt;
 *     &lt;enumeration value="_scheduleMassUpdates"/&gt;
 *     &lt;enumeration value="_setUpAccounting"/&gt;
 *     &lt;enumeration value="_setUpAchProcessing"/&gt;
 *     &lt;enumeration value="_setUpAdpPayroll"/&gt;
 *     &lt;enumeration value="_setUpBillPay"/&gt;
 *     &lt;enumeration value="_setUpBudgets"/&gt;
 *     &lt;enumeration value="_setUpCampaignEmailAddresses"/&gt;
 *     &lt;enumeration value="_setupCampaigns"/&gt;
 *     &lt;enumeration value="_setUpCompany"/&gt;
 *     &lt;enumeration value="_setUpCsvPreferences"/&gt;
 *     &lt;enumeration value="_setUpDomains"/&gt;
 *     &lt;enumeration value="_setUpEbay"/&gt;
 *     &lt;enumeration value="_setUpImageResizing"/&gt;
 *     &lt;enumeration value="_setUpOpenidSingleSignOn"/&gt;
 *     &lt;enumeration value="_setUpPartnerMonitor"/&gt;
 *     &lt;enumeration value="_setUpPayroll"/&gt;
 *     &lt;enumeration value="_setUpReminders"/&gt;
 *     &lt;enumeration value="_setUpSamlSingleSignOn"/&gt;
 *     &lt;enumeration value="_setUpSnapshots"/&gt;
 *     &lt;enumeration value="_setUpSynchronization"/&gt;
 *     &lt;enumeration value="_setUpWebServices"/&gt;
 *     &lt;enumeration value="_setUpWebSite"/&gt;
 *     &lt;enumeration value="_setUpYearStatus"/&gt;
 *     &lt;enumeration value="_shippingItems"/&gt;
 *     &lt;enumeration value="_shippingPartnerPackage"/&gt;
 *     &lt;enumeration value="_shippingPartnerRegistration"/&gt;
 *     &lt;enumeration value="_shippingPartnerShipment"/&gt;
 *     &lt;enumeration value="_shortcuts"/&gt;
 *     &lt;enumeration value="_standardCostVersion"/&gt;
 *     &lt;enumeration value="_statementCharge"/&gt;
 *     &lt;enumeration value="_statisticalAccountRegisters"/&gt;
 *     &lt;enumeration value="_storeCategories"/&gt;
 *     &lt;enumeration value="_storeContentCategories"/&gt;
 *     &lt;enumeration value="_storeContentItems"/&gt;
 *     &lt;enumeration value="_storeLogoUpload"/&gt;
 *     &lt;enumeration value="_storeTabs"/&gt;
 *     &lt;enumeration value="_subsidiaries"/&gt;
 *     &lt;enumeration value="_suiteBundler"/&gt;
 *     &lt;enumeration value="_suiteBundlerUpgrades"/&gt;
 *     &lt;enumeration value="_suiteScript"/&gt;
 *     &lt;enumeration value="_suiteSignon"/&gt;
 *     &lt;enumeration value="_support"/&gt;
 *     &lt;enumeration value="_supportCaseIssue"/&gt;
 *     &lt;enumeration value="_supportCaseOrigin"/&gt;
 *     &lt;enumeration value="_supportCasePriority"/&gt;
 *     &lt;enumeration value="_supportCaseSnapshotReminders"/&gt;
 *     &lt;enumeration value="_supportCaseStatus"/&gt;
 *     &lt;enumeration value="_supportCaseTerritory"/&gt;
 *     &lt;enumeration value="_supportCaseTerritoryRule"/&gt;
 *     &lt;enumeration value="_supportCaseType"/&gt;
 *     &lt;enumeration value="_supportSetup"/&gt;
 *     &lt;enumeration value="_swapPricesBetweenPriceLevels"/&gt;
 *     &lt;enumeration value="_systemEmailTemplate"/&gt;
 *     &lt;enumeration value="_systemStatus"/&gt;
 *     &lt;enumeration value="_tasks"/&gt;
 *     &lt;enumeration value="_tax"/&gt;
 *     &lt;enumeration value="_taxItems"/&gt;
 *     &lt;enumeration value="_taxSchedules"/&gt;
 *     &lt;enumeration value="_teamSellingContribution"/&gt;
 *     &lt;enumeration value="_tegataAccounts"/&gt;
 *     &lt;enumeration value="_tegataPayable"/&gt;
 *     &lt;enumeration value="_tegataReceivable"/&gt;
 *     &lt;enumeration value="_telephonyIntegration"/&gt;
 *     &lt;enumeration value="_templateCategories"/&gt;
 *     &lt;enumeration value="_testdriveMasters"/&gt;
 *     &lt;enumeration value="_timer"/&gt;
 *     &lt;enumeration value="_timeTracking"/&gt;
 *     &lt;enumeration value="_trackMessages"/&gt;
 *     &lt;enumeration value="_trackTime"/&gt;
 *     &lt;enumeration value="_transactionDetail"/&gt;
 *     &lt;enumeration value="_transactionNumberingAuditLog"/&gt;
 *     &lt;enumeration value="_transferFunds"/&gt;
 *     &lt;enumeration value="_transferInventory"/&gt;
 *     &lt;enumeration value="_transferOrder"/&gt;
 *     &lt;enumeration value="_transferOrderApproval"/&gt;
 *     &lt;enumeration value="_translation"/&gt;
 *     &lt;enumeration value="_trialBalance"/&gt;
 *     &lt;enumeration value="_twoFactorAuthentication"/&gt;
 *     &lt;enumeration value="_unbilledReceivableRegisters"/&gt;
 *     &lt;enumeration value="_unbuildAssemblies"/&gt;
 *     &lt;enumeration value="_uncategorizedPresentationItems"/&gt;
 *     &lt;enumeration value="_units"/&gt;
 *     &lt;enumeration value="_updatePrices"/&gt;
 *     &lt;enumeration value="_upsellAssistant"/&gt;
 *     &lt;enumeration value="_upsellSetup"/&gt;
 *     &lt;enumeration value="_upsellWizard"/&gt;
 *     &lt;enumeration value="_userPreferences"/&gt;
 *     &lt;enumeration value="_usersAndPasswords"/&gt;
 *     &lt;enumeration value="_vendorBillApproval"/&gt;
 *     &lt;enumeration value="_vendorPaymentStatus"/&gt;
 *     &lt;enumeration value="_vendorReturnAuthApproval"/&gt;
 *     &lt;enumeration value="_vendorReturnAuthorization"/&gt;
 *     &lt;enumeration value="_vendorReturns"/&gt;
 *     &lt;enumeration value="_vendors"/&gt;
 *     &lt;enumeration value="_viewLoginAuditTrail"/&gt;
 *     &lt;enumeration value="_viewOnlineBillPayStatus"/&gt;
 *     &lt;enumeration value="_viewPaymentEvents"/&gt;
 *     &lt;enumeration value="_viewUnencryptedCreditCards"/&gt;
 *     &lt;enumeration value="_webServices"/&gt;
 *     &lt;enumeration value="_webSiteExternalPublisher"/&gt;
 *     &lt;enumeration value="_webSiteReport"/&gt;
 *     &lt;enumeration value="_webStoreEmailTemplate"/&gt;
 *     &lt;enumeration value="_webStoreReport"/&gt;
 *     &lt;enumeration value="_workCalendar"/&gt;
 *     &lt;enumeration value="_workflow"/&gt;
 *     &lt;enumeration value="_workOrder"/&gt;
 *     &lt;enumeration value="_workOrderClose"/&gt;
 *     &lt;enumeration value="_workOrderCompletion"/&gt;
 *     &lt;enumeration value="_workOrderIssue"/&gt;
 *     &lt;enumeration value="_workplaces"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PermissionCode", namespace = "urn:types.common_2014_2.platform.webservices.netsuite.com")
@XmlEnum
public enum PermissionCode {

    @XmlEnumValue("_accessPaymentAuditLog")
    ACCESS_PAYMENT_AUDIT_LOG("_accessPaymentAuditLog"),
    @XmlEnumValue("_accountDetail")
    ACCOUNT_DETAIL("_accountDetail"),
    @XmlEnumValue("_accounting")
    ACCOUNTING("_accounting"),
    @XmlEnumValue("_accountingBook")
    ACCOUNTING_BOOK("_accountingBook"),
    @XmlEnumValue("_accountingLists")
    ACCOUNTING_LISTS("_accountingLists"),
    @XmlEnumValue("_accounts")
    ACCOUNTS("_accounts"),
    @XmlEnumValue("_accountsPayable")
    ACCOUNTS_PAYABLE("_accountsPayable"),
    @XmlEnumValue("_accountsPayableGraphing")
    ACCOUNTS_PAYABLE_GRAPHING("_accountsPayableGraphing"),
    @XmlEnumValue("_accountsPayableRegister")
    ACCOUNTS_PAYABLE_REGISTER("_accountsPayableRegister"),
    @XmlEnumValue("_accountsReceivable")
    ACCOUNTS_RECEIVABLE("_accountsReceivable"),
    @XmlEnumValue("_accountsReceivableGraphing")
    ACCOUNTS_RECEIVABLE_GRAPHING("_accountsReceivableGraphing"),
    @XmlEnumValue("_accountsReceivableRegister")
    ACCOUNTS_RECEIVABLE_REGISTER("_accountsReceivableRegister"),
    @XmlEnumValue("_accountsReceivableUnbilled")
    ACCOUNTS_RECEIVABLE_UNBILLED("_accountsReceivableUnbilled"),
    @XmlEnumValue("_adjustInventory")
    ADJUST_INVENTORY("_adjustInventory"),
    @XmlEnumValue("_adjustInventoryWorksheet")
    ADJUST_INVENTORY_WORKSHEET("_adjustInventoryWorksheet"),
    @XmlEnumValue("_admindocs")
    ADMINDOCS("_admindocs"),
    @XmlEnumValue("_adpImportData")
    ADP_IMPORT_DATA("_adpImportData"),
    @XmlEnumValue("_adpSetup")
    ADP_SETUP("_adpSetup"),
    @XmlEnumValue("_advancedAnalytics")
    ADVANCED_ANALYTICS("_advancedAnalytics"),
    @XmlEnumValue("_advancedPDFHTMLTemplates")
    ADVANCED_PDFHTML_TEMPLATES("_advancedPDFHTMLTemplates"),
    @XmlEnumValue("_allocationSchedules")
    ALLOCATION_SCHEDULES("_allocationSchedules"),
    @XmlEnumValue("_allowNonGLChanges")
    ALLOW_NON_GL_CHANGES("_allowNonGLChanges"),
    @XmlEnumValue("_amendW4")
    AMEND_W_4("_amendW4"),
    @XmlEnumValue("_amortizationReports")
    AMORTIZATION_REPORTS("_amortizationReports"),
    @XmlEnumValue("_amortizationSchedules")
    AMORTIZATION_SCHEDULES("_amortizationSchedules"),
    @XmlEnumValue("_appDefinitionsAndPackages")
    APP_DEFINITIONS_AND_PACKAGES("_appDefinitionsAndPackages"),
    @XmlEnumValue("_applicationPublishers")
    APPLICATION_PUBLISHERS("_applicationPublishers"),
    @XmlEnumValue("_approveDirectDeposit")
    APPROVE_DIRECT_DEPOSIT("_approveDirectDeposit"),
    @XmlEnumValue("_approveEFT")
    APPROVE_EFT("_approveEFT"),
    @XmlEnumValue("_approveOnlineBillPayments")
    APPROVE_ONLINE_BILL_PAYMENTS("_approveOnlineBillPayments"),
    @XmlEnumValue("_approveVendorPayments")
    APPROVE_VENDOR_PAYMENTS("_approveVendorPayments"),
    @XmlEnumValue("_auditTrail")
    AUDIT_TRAIL("_auditTrail"),
    @XmlEnumValue("_backupYourData")
    BACKUP_YOUR_DATA("_backupYourData"),
    @XmlEnumValue("_balanceSheet")
    BALANCE_SHEET("_balanceSheet"),
    @XmlEnumValue("_bankAccountRegisters")
    BANK_ACCOUNT_REGISTERS("_bankAccountRegisters"),
    @XmlEnumValue("_billingInformation")
    BILLING_INFORMATION("_billingInformation"),
    @XmlEnumValue("_billingSchedules")
    BILLING_SCHEDULES("_billingSchedules"),
    @XmlEnumValue("_billingSetup")
    BILLING_SETUP("_billingSetup"),
    @XmlEnumValue("_billOfDistribution")
    BILL_OF_DISTRIBUTION("_billOfDistribution"),
    @XmlEnumValue("_billOfMaterialsInquiry")
    BILL_OF_MATERIALS_INQUIRY("_billOfMaterialsInquiry"),
    @XmlEnumValue("_billPurchaseOrders")
    BILL_PURCHASE_ORDERS("_billPurchaseOrders"),
    @XmlEnumValue("_bills")
    BILLS("_bills"),
    @XmlEnumValue("_billSalesOrders")
    BILL_SALES_ORDERS("_billSalesOrders"),
    @XmlEnumValue("_bins")
    BINS("_bins"),
    @XmlEnumValue("_binTransfer")
    BIN_TRANSFER("_binTransfer"),
    @XmlEnumValue("_binWorksheet")
    BIN_WORKSHEET("_binWorksheet"),
    @XmlEnumValue("_blanketPurchaseOrder")
    BLANKET_PURCHASE_ORDER("_blanketPurchaseOrder"),
    @XmlEnumValue("_blanketPurchaseOrderApproval")
    BLANKET_PURCHASE_ORDER_APPROVAL("_blanketPurchaseOrderApproval"),
    @XmlEnumValue("_budget")
    BUDGET("_budget"),
    @XmlEnumValue("_buildAssemblies")
    BUILD_ASSEMBLIES("_buildAssemblies"),
    @XmlEnumValue("_buildWorkOrders")
    BUILD_WORK_ORDERS("_buildWorkOrders"),
    @XmlEnumValue("_calculateTime")
    CALCULATE_TIME("_calculateTime"),
    @XmlEnumValue("_calendar")
    CALENDAR("_calendar"),
    @XmlEnumValue("_campaignHistory")
    CAMPAIGN_HISTORY("_campaignHistory"),
    @XmlEnumValue("_cases")
    CASES("_cases"),
    @XmlEnumValue("_cashFlowStatement")
    CASH_FLOW_STATEMENT("_cashFlowStatement"),
    @XmlEnumValue("_cashSale")
    CASH_SALE("_cashSale"),
    @XmlEnumValue("_cashSaleRefund")
    CASH_SALE_REFUND("_cashSaleRefund"),
    @XmlEnumValue("_changeEmailOrPassword")
    CHANGE_EMAIL_OR_PASSWORD("_changeEmailOrPassword"),
    @XmlEnumValue("_changeRole")
    CHANGE_ROLE("_changeRole"),
    @XmlEnumValue("_chargeRule")
    CHARGE_RULE("_chargeRule"),
    @XmlEnumValue("_chargeRunRules")
    CHARGE_RUN_RULES("_chargeRunRules"),
    @XmlEnumValue("_check")
    CHECK("_check"),
    @XmlEnumValue("_checkItemAvailability")
    CHECK_ITEM_AVAILABILITY("_checkItemAvailability"),
    @XmlEnumValue("_classes")
    CLASSES("_classes"),
    @XmlEnumValue("_classSegmentMapping")
    CLASS_SEGMENT_MAPPING("_classSegmentMapping"),
    @XmlEnumValue("_closeAccount")
    CLOSE_ACCOUNT("_closeAccount"),
    @XmlEnumValue("_closeWorkOrders")
    CLOSE_WORK_ORDERS("_closeWorkOrders"),
    @XmlEnumValue("_colorThemes")
    COLOR_THEMES("_colorThemes"),
    @XmlEnumValue("_commissionFeatureSetup")
    COMMISSION_FEATURE_SETUP("_commissionFeatureSetup"),
    @XmlEnumValue("_commissionReports")
    COMMISSION_REPORTS("_commissionReports"),
    @XmlEnumValue("_commitOrders")
    COMMIT_ORDERS("_commitOrders"),
    @XmlEnumValue("_commitPayroll")
    COMMIT_PAYROLL("_commitPayroll"),
    @XmlEnumValue("_companies")
    COMPANIES("_companies"),
    @XmlEnumValue("_companyInformation")
    COMPANY_INFORMATION("_companyInformation"),
    @XmlEnumValue("_companyPreferences")
    COMPANY_PREFERENCES("_companyPreferences"),
    @XmlEnumValue("_competitors")
    COMPETITORS("_competitors"),
    @XmlEnumValue("_componentWhereUsed")
    COMPONENT_WHERE_USED("_componentWhereUsed"),
    @XmlEnumValue("_contactRoles")
    CONTACT_ROLES("_contactRoles"),
    @XmlEnumValue("_contacts")
    CONTACTS("_contacts"),
    @XmlEnumValue("_convertClassesToDepartments")
    CONVERT_CLASSES_TO_DEPARTMENTS("_convertClassesToDepartments"),
    @XmlEnumValue("_convertClassesToLocations")
    CONVERT_CLASSES_TO_LOCATIONS("_convertClassesToLocations"),
    @XmlEnumValue("_copyBudgets")
    COPY_BUDGETS("_copyBudgets"),
    @XmlEnumValue("_copyChartOfAccountsToNewCompany")
    COPY_CHART_OF_ACCOUNTS_TO_NEW_COMPANY("_copyChartOfAccountsToNewCompany"),
    @XmlEnumValue("_copyProjectTasks")
    COPY_PROJECT_TASKS("_copyProjectTasks"),
    @XmlEnumValue("_costedBillOfMaterialsInquiry")
    COSTED_BILL_OF_MATERIALS_INQUIRY("_costedBillOfMaterialsInquiry"),
    @XmlEnumValue("_costOfGoodsSoldRegisters")
    COST_OF_GOODS_SOLD_REGISTERS("_costOfGoodsSoldRegisters"),
    @XmlEnumValue("_countInventory")
    COUNT_INVENTORY("_countInventory"),
    @XmlEnumValue("_createAllocationSchedules")
    CREATE_ALLOCATION_SCHEDULES("_createAllocationSchedules"),
    @XmlEnumValue("_createConsolidationCompany")
    CREATE_CONSOLIDATION_COMPANY("_createConsolidationCompany"),
    @XmlEnumValue("_createFiscalCalendar")
    CREATE_FISCAL_CALENDAR("_createFiscalCalendar"),
    @XmlEnumValue("_createInventoryCounts")
    CREATE_INVENTORY_COUNTS("_createInventoryCounts"),
    @XmlEnumValue("_creditCard")
    CREDIT_CARD("_creditCard"),
    @XmlEnumValue("_creditCardProcessing")
    CREDIT_CARD_PROCESSING("_creditCardProcessing"),
    @XmlEnumValue("_creditCardRefund")
    CREDIT_CARD_REFUND("_creditCardRefund"),
    @XmlEnumValue("_creditCardRegisters")
    CREDIT_CARD_REGISTERS("_creditCardRegisters"),
    @XmlEnumValue("_creditMemo")
    CREDIT_MEMO("_creditMemo"),
    @XmlEnumValue("_creditReturns")
    CREDIT_RETURNS("_creditReturns"),
    @XmlEnumValue("_crmGroups")
    CRM_GROUPS("_crmGroups"),
    @XmlEnumValue("_crmLists")
    CRM_LISTS("_crmLists"),
    @XmlEnumValue("_currency")
    CURRENCY("_currency"),
    @XmlEnumValue("_currencyAdjustmentJournal")
    CURRENCY_ADJUSTMENT_JOURNAL("_currencyAdjustmentJournal"),
    @XmlEnumValue("_currencyRevaluation")
    CURRENCY_REVALUATION("_currencyRevaluation"),
    @XmlEnumValue("_customAddressForm")
    CUSTOM_ADDRESS_FORM("_customAddressForm"),
    @XmlEnumValue("_customBodyFields")
    CUSTOM_BODY_FIELDS("_customBodyFields"),
    @XmlEnumValue("_customCenterCategories")
    CUSTOM_CENTER_CATEGORIES("_customCenterCategories"),
    @XmlEnumValue("_customCenterLinks")
    CUSTOM_CENTER_LINKS("_customCenterLinks"),
    @XmlEnumValue("_customCenters")
    CUSTOM_CENTERS("_customCenters"),
    @XmlEnumValue("_customCenterTabs")
    CUSTOM_CENTER_TABS("_customCenterTabs"),
    @XmlEnumValue("_customColumnFields")
    CUSTOM_COLUMN_FIELDS("_customColumnFields"),
    @XmlEnumValue("_customEntityFields")
    CUSTOM_ENTITY_FIELDS("_customEntityFields"),
    @XmlEnumValue("_customEntryForms")
    CUSTOM_ENTRY_FORMS("_customEntryForms"),
    @XmlEnumValue("_customerCharge")
    CUSTOMER_CHARGE("_customerCharge"),
    @XmlEnumValue("_customerDeposit")
    CUSTOMER_DEPOSIT("_customerDeposit"),
    @XmlEnumValue("_customerPayment")
    CUSTOMER_PAYMENT("_customerPayment"),
    @XmlEnumValue("_customerProfile")
    CUSTOMER_PROFILE("_customerProfile"),
    @XmlEnumValue("_customerRefund")
    CUSTOMER_REFUND("_customerRefund"),
    @XmlEnumValue("_customers")
    CUSTOMERS("_customers"),
    @XmlEnumValue("_customerStatus")
    CUSTOMER_STATUS("_customerStatus"),
    @XmlEnumValue("_customEventFields")
    CUSTOM_EVENT_FIELDS("_customEventFields"),
    @XmlEnumValue("_customFields")
    CUSTOM_FIELDS("_customFields"),
    @XmlEnumValue("_customHTMLLayouts")
    CUSTOM_HTML_LAYOUTS("_customHTMLLayouts"),
    @XmlEnumValue("_customItemFields")
    CUSTOM_ITEM_FIELDS("_customItemFields"),
    @XmlEnumValue("_customItemNumberFields")
    CUSTOM_ITEM_NUMBER_FIELDS("_customItemNumberFields"),
    @XmlEnumValue("_customizePage")
    CUSTOMIZE_PAGE("_customizePage"),
    @XmlEnumValue("_customLists")
    CUSTOM_LISTS("_customLists"),
    @XmlEnumValue("_customPDFLayouts")
    CUSTOM_PDF_LAYOUTS("_customPDFLayouts"),
    @XmlEnumValue("_customRecordEntries")
    CUSTOM_RECORD_ENTRIES("_customRecordEntries"),
    @XmlEnumValue("_customRecordTypes")
    CUSTOM_RECORD_TYPES("_customRecordTypes"),
    @XmlEnumValue("_customSublists")
    CUSTOM_SUBLISTS("_customSublists"),
    @XmlEnumValue("_customSubtabs")
    CUSTOM_SUBTABS("_customSubtabs"),
    @XmlEnumValue("_customTransactionFields")
    CUSTOM_TRANSACTION_FIELDS("_customTransactionFields"),
    @XmlEnumValue("_customTransactionForms")
    CUSTOM_TRANSACTION_FORMS("_customTransactionForms"),
    @XmlEnumValue("_deferredExpenseRegisters")
    DEFERRED_EXPENSE_REGISTERS("_deferredExpenseRegisters"),
    @XmlEnumValue("_deferredRevenueRegisters")
    DEFERRED_REVENUE_REGISTERS("_deferredRevenueRegisters"),
    @XmlEnumValue("_deleteAllData")
    DELETE_ALL_DATA("_deleteAllData"),
    @XmlEnumValue("_deletedRecords")
    DELETED_RECORDS("_deletedRecords"),
    @XmlEnumValue("_deleteEvent")
    DELETE_EVENT("_deleteEvent"),
    @XmlEnumValue("_departments")
    DEPARTMENTS("_departments"),
    @XmlEnumValue("_departmentSegmentMapping")
    DEPARTMENT_SEGMENT_MAPPING("_departmentSegmentMapping"),
    @XmlEnumValue("_deposit")
    DEPOSIT("_deposit"),
    @XmlEnumValue("_depositApplication")
    DEPOSIT_APPLICATION("_depositApplication"),
    @XmlEnumValue("_directDepositStatus")
    DIRECT_DEPOSIT_STATUS("_directDepositStatus"),
    @XmlEnumValue("_distributeInventory")
    DISTRIBUTE_INVENTORY("_distributeInventory"),
    @XmlEnumValue("_distributionNetwork")
    DISTRIBUTION_NETWORK("_distributionNetwork"),
    @XmlEnumValue("_documentsAndFiles")
    DOCUMENTS_AND_FILES("_documentsAndFiles"),
    @XmlEnumValue("_duplicateCaseManagement")
    DUPLICATE_CASE_MANAGEMENT("_duplicateCaseManagement"),
    @XmlEnumValue("_duplicateDetectionSetup")
    DUPLICATE_DETECTION_SETUP("_duplicateDetectionSetup"),
    @XmlEnumValue("_duplicateRecordManagement")
    DUPLICATE_RECORD_MANAGEMENT("_duplicateRecordManagement"),
    @XmlEnumValue("_ebayExportImport")
    EBAY_EXPORT_IMPORT("_ebayExportImport"),
    @XmlEnumValue("_editForecast")
    EDIT_FORECAST("_editForecast"),
    @XmlEnumValue("_editManagerForecast")
    EDIT_MANAGER_FORECAST("_editManagerForecast"),
    @XmlEnumValue("_editProfile")
    EDIT_PROFILE("_editProfile"),
    @XmlEnumValue("_eftStatus")
    EFT_STATUS("_eftStatus"),
    @XmlEnumValue("_emailReports")
    EMAIL_REPORTS("_emailReports"),
    @XmlEnumValue("_emailTemplate")
    EMAIL_TEMPLATE("_emailTemplate"),
    @XmlEnumValue("_employeeCommissionSchedulesPlans")
    EMPLOYEE_COMMISSION_SCHEDULES_PLANS("_employeeCommissionSchedulesPlans"),
    @XmlEnumValue("_employeeCommissionTransaction")
    EMPLOYEE_COMMISSION_TRANSACTION("_employeeCommissionTransaction"),
    @XmlEnumValue("_employeeCommissionTransactionApproval")
    EMPLOYEE_COMMISSION_TRANSACTION_APPROVAL("_employeeCommissionTransactionApproval"),
    @XmlEnumValue("_employeeReminders")
    EMPLOYEE_REMINDERS("_employeeReminders"),
    @XmlEnumValue("_employees")
    EMPLOYEES("_employees"),
    @XmlEnumValue("_employeeSocialSecurityNumbers")
    EMPLOYEE_SOCIAL_SECURITY_NUMBERS("_employeeSocialSecurityNumbers"),
    @XmlEnumValue("_enableFeatures")
    ENABLE_FEATURES("_enableFeatures"),
    @XmlEnumValue("_enterCompletions")
    ENTER_COMPLETIONS("_enterCompletions"),
    @XmlEnumValue("_enterOpeningBalances")
    ENTER_OPENING_BALANCES("_enterOpeningBalances"),
    @XmlEnumValue("_enterVendorCredits")
    ENTER_VENDOR_CREDITS("_enterVendorCredits"),
    @XmlEnumValue("_enterYearToDatePayrollAdjustments")
    ENTER_YEAR_TO_DATE_PAYROLL_ADJUSTMENTS("_enterYearToDatePayrollAdjustments"),
    @XmlEnumValue("_entityAccountMapping")
    ENTITY_ACCOUNT_MAPPING("_entityAccountMapping"),
    @XmlEnumValue("_equityRegisters")
    EQUITY_REGISTERS("_equityRegisters"),
    @XmlEnumValue("_escalationAssignment")
    ESCALATION_ASSIGNMENT("_escalationAssignment"),
    @XmlEnumValue("_escalationAssignmentRule")
    ESCALATION_ASSIGNMENT_RULE("_escalationAssignmentRule"),
    @XmlEnumValue("_establishQuotas")
    ESTABLISH_QUOTAS("_establishQuotas"),
    @XmlEnumValue("_estimate")
    ESTIMATE("_estimate"),
    @XmlEnumValue("_events")
    EVENTS("_events"),
    @XmlEnumValue("_expenseCategories")
    EXPENSE_CATEGORIES("_expenseCategories"),
    @XmlEnumValue("_expenseRegisters")
    EXPENSE_REGISTERS("_expenseRegisters"),
    @XmlEnumValue("_expenseReport")
    EXPENSE_REPORT("_expenseReport"),
    @XmlEnumValue("_expenses")
    EXPENSES("_expenses"),
    @XmlEnumValue("_exportAsIIF")
    EXPORT_AS_IIF("_exportAsIIF"),
    @XmlEnumValue("_exportLists")
    EXPORT_LISTS("_exportLists"),
    @XmlEnumValue("_faxMessages")
    FAX_MESSAGES("_faxMessages"),
    @XmlEnumValue("_faxTemplate")
    FAX_TEMPLATE("_faxTemplate"),
    @XmlEnumValue("_features")
    FEATURES("_features"),
    @XmlEnumValue("_financeCharge")
    FINANCE_CHARGE("_financeCharge"),
    @XmlEnumValue("_financeChargePreferences")
    FINANCE_CHARGE_PREFERENCES("_financeChargePreferences"),
    @XmlEnumValue("_financialHistory")
    FINANCIAL_HISTORY("_financialHistory"),
    @XmlEnumValue("_financialStatementLayouts")
    FINANCIAL_STATEMENT_LAYOUTS("_financialStatementLayouts"),
    @XmlEnumValue("_financialStatements")
    FINANCIAL_STATEMENTS("_financialStatements"),
    @XmlEnumValue("_financialStatementSections")
    FINANCIAL_STATEMENT_SECTIONS("_financialStatementSections"),
    @XmlEnumValue("_findMatchingOnlineBankingTransactions")
    FIND_MATCHING_ONLINE_BANKING_TRANSACTIONS("_findMatchingOnlineBankingTransactions"),
    @XmlEnumValue("_findTransaction")
    FIND_TRANSACTION("_findTransaction"),
    @XmlEnumValue("_fiscalCalendars")
    FISCAL_CALENDARS("_fiscalCalendars"),
    @XmlEnumValue("_fixedAssetRegisters")
    FIXED_ASSET_REGISTERS("_fixedAssetRegisters"),
    @XmlEnumValue("_form1099FederalMiscellaneousIncome")
    FORM_1099_FEDERAL_MISCELLANEOUS_INCOME("_form1099FederalMiscellaneousIncome"),
    @XmlEnumValue("_form940EmployersAnnualFederalUnemploymentTaxReturn")
    FORM_940_EMPLOYERS_ANNUAL_FEDERAL_UNEMPLOYMENT_TAX_RETURN("_form940EmployersAnnualFederalUnemploymentTaxReturn"),
    @XmlEnumValue("_form941EmployersQuarterlyFederalTaxReturn")
    FORM_941_EMPLOYERS_QUARTERLY_FEDERAL_TAX_RETURN("_form941EmployersQuarterlyFederalTaxReturn"),
    @XmlEnumValue("_formW2WageAndTaxStatement")
    FORM_W_2_WAGE_AND_TAX_STATEMENT("_formW2WageAndTaxStatement"),
    @XmlEnumValue("_formW4EmployeesWithholdingAllowanceCertificate")
    FORM_W_4_EMPLOYEES_WITHHOLDING_ALLOWANCE_CERTIFICATE("_formW4EmployeesWithholdingAllowanceCertificate"),
    @XmlEnumValue("_fulfillOrders")
    FULFILL_ORDERS("_fulfillOrders"),
    @XmlEnumValue("_generalLedger")
    GENERAL_LEDGER("_generalLedger"),
    @XmlEnumValue("_generatePriceLists")
    GENERATE_PRICE_LISTS("_generatePriceLists"),
    @XmlEnumValue("_generateRevenueCommitment")
    GENERATE_REVENUE_COMMITMENT("_generateRevenueCommitment"),
    @XmlEnumValue("_generateRevenueCommitmentReversals")
    GENERATE_REVENUE_COMMITMENT_REVERSALS("_generateRevenueCommitmentReversals"),
    @XmlEnumValue("_generateSingleOrderRevenueContracts")
    GENERATE_SINGLE_ORDER_REVENUE_CONTRACTS("_generateSingleOrderRevenueContracts"),
    @XmlEnumValue("_generateStatements")
    GENERATE_STATEMENTS("_generateStatements"),
    @XmlEnumValue("_genericAdminPermission")
    GENERIC_ADMIN_PERMISSION("_genericAdminPermission"),
    @XmlEnumValue("_genericResources")
    GENERIC_RESOURCES("_genericResources"),
    @XmlEnumValue("_globalAccountMapping")
    GLOBAL_ACCOUNT_MAPPING("_globalAccountMapping"),
    @XmlEnumValue("_grantingAccessToReports")
    GRANTING_ACCESS_TO_REPORTS("_grantingAccessToReports"),
    @XmlEnumValue("_gstSummaryReport")
    GST_SUMMARY_REPORT("_gstSummaryReport"),
    @XmlEnumValue("_importCSVFile")
    IMPORT_CSV_FILE("_importCSVFile"),
    @XmlEnumValue("_importOnlineBankingQIFFile")
    IMPORT_ONLINE_BANKING_QIF_FILE("_importOnlineBankingQIFFile"),
    @XmlEnumValue("_importQuickenQIFFile")
    IMPORT_QUICKEN_QIF_FILE("_importQuickenQIFFile"),
    @XmlEnumValue("_importStateSalesTax")
    IMPORT_STATE_SALES_TAX("_importStateSalesTax"),
    @XmlEnumValue("_income")
    INCOME("_income"),
    @XmlEnumValue("_incomeRegisters")
    INCOME_REGISTERS("_incomeRegisters"),
    @XmlEnumValue("_incomeStatement")
    INCOME_STATEMENT("_incomeStatement"),
    @XmlEnumValue("_individualPaycheck")
    INDIVIDUAL_PAYCHECK("_individualPaycheck"),
    @XmlEnumValue("_integration")
    INTEGRATION("_integration"),
    @XmlEnumValue("_intercompanyAdjustments")
    INTERCOMPANY_ADJUSTMENTS("_intercompanyAdjustments"),
    @XmlEnumValue("_internalPublisher")
    INTERNAL_PUBLISHER("_internalPublisher"),
    @XmlEnumValue("_inventory")
    INVENTORY("_inventory"),
    @XmlEnumValue("_inventoryCostTemplate")
    INVENTORY_COST_TEMPLATE("_inventoryCostTemplate"),
    @XmlEnumValue("_invoice")
    INVOICE("_invoice"),
    @XmlEnumValue("_invoiceApproval")
    INVOICE_APPROVAL("_invoiceApproval"),
    @XmlEnumValue("_issueComponents")
    ISSUE_COMPONENTS("_issueComponents"),
    @XmlEnumValue("_issueReports")
    ISSUE_REPORTS("_issueReports"),
    @XmlEnumValue("_issues")
    ISSUES("_issues"),
    @XmlEnumValue("_issueSetup")
    ISSUE_SETUP("_issueSetup"),
    @XmlEnumValue("_itemAccountMapping")
    ITEM_ACCOUNT_MAPPING("_itemAccountMapping"),
    @XmlEnumValue("_itemCategoryLayouts")
    ITEM_CATEGORY_LAYOUTS("_itemCategoryLayouts"),
    @XmlEnumValue("_itemDemandPlan")
    ITEM_DEMAND_PLAN("_itemDemandPlan"),
    @XmlEnumValue("_itemFulfillment")
    ITEM_FULFILLMENT("_itemFulfillment"),
    @XmlEnumValue("_itemReceipt")
    ITEM_RECEIPT("_itemReceipt"),
    @XmlEnumValue("_itemRevisions")
    ITEM_REVISIONS("_itemRevisions"),
    @XmlEnumValue("_items")
    ITEMS("_items"),
    @XmlEnumValue("_itemSupplyPlan")
    ITEM_SUPPLY_PLAN("_itemSupplyPlan"),
    @XmlEnumValue("_itemTemplates")
    ITEM_TEMPLATES("_itemTemplates"),
    @XmlEnumValue("_jobs")
    JOBS("_jobs"),
    @XmlEnumValue("_journalApproval")
    JOURNAL_APPROVAL("_journalApproval"),
    @XmlEnumValue("_knowledgeBase")
    KNOWLEDGE_BASE("_knowledgeBase"),
    @XmlEnumValue("_kpiScorecards")
    KPI_SCORECARDS("_kpiScorecards"),
    @XmlEnumValue("_leadConversion")
    LEAD_CONVERSION("_leadConversion"),
    @XmlEnumValue("_leadConversionMapping")
    LEAD_CONVERSION_MAPPING("_leadConversionMapping"),
    @XmlEnumValue("_leadSnapshotReminders")
    LEAD_SNAPSHOT_REMINDERS("_leadSnapshotReminders"),
    @XmlEnumValue("_letterMessages")
    LETTER_MESSAGES("_letterMessages"),
    @XmlEnumValue("_letterTemplate")
    LETTER_TEMPLATE("_letterTemplate"),
    @XmlEnumValue("_loadSampleData")
    LOAD_SAMPLE_DATA("_loadSampleData"),
    @XmlEnumValue("_locations")
    LOCATIONS("_locations"),
    @XmlEnumValue("_locationSegmentMapping")
    LOCATION_SEGMENT_MAPPING("_locationSegmentMapping"),
    @XmlEnumValue("_lockTransactions")
    LOCK_TRANSACTIONS("_lockTransactions"),
    @XmlEnumValue("_longTermLiabilityRegisters")
    LONG_TERM_LIABILITY_REGISTERS("_longTermLiabilityRegisters"),
    @XmlEnumValue("_mailMerge")
    MAIL_MERGE("_mailMerge"),
    @XmlEnumValue("_makeJournalEntry")
    MAKE_JOURNAL_ENTRY("_makeJournalEntry"),
    @XmlEnumValue("_manageAccountingPeriods")
    MANAGE_ACCOUNTING_PERIODS("_manageAccountingPeriods"),
    @XmlEnumValue("_managePayroll")
    MANAGE_PAYROLL("_managePayroll"),
    @XmlEnumValue("_manageRoles")
    MANAGE_ROLES("_manageRoles"),
    @XmlEnumValue("_manageTaxReportingPeriods")
    MANAGE_TAX_REPORTING_PERIODS("_manageTaxReportingPeriods"),
    @XmlEnumValue("_manageUsers")
    MANAGE_USERS("_manageUsers"),
    @XmlEnumValue("_manufacturingCostTemplate")
    MANUFACTURING_COST_TEMPLATE("_manufacturingCostTemplate"),
    @XmlEnumValue("_manufacturingRouting")
    MANUFACTURING_ROUTING("_manufacturingRouting"),
    @XmlEnumValue("_marketingCampaignReports")
    MARKETING_CAMPAIGN_REPORTS("_marketingCampaignReports"),
    @XmlEnumValue("_marketingCampaigns")
    MARKETING_CAMPAIGNS("_marketingCampaigns"),
    @XmlEnumValue("_marketingTemplate")
    MARKETING_TEMPLATE("_marketingTemplate"),
    @XmlEnumValue("_markIssueAsShowStopper")
    MARK_ISSUE_AS_SHOW_STOPPER("_markIssueAsShowStopper"),
    @XmlEnumValue("_markWorkOrdersBuilt")
    MARK_WORK_ORDERS_BUILT("_markWorkOrdersBuilt"),
    @XmlEnumValue("_markWorkOrdersFirmed")
    MARK_WORK_ORDERS_FIRMED("_markWorkOrdersFirmed"),
    @XmlEnumValue("_markWorkOrdersReleased")
    MARK_WORK_ORDERS_RELEASED("_markWorkOrdersReleased"),
    @XmlEnumValue("_massUpdates")
    MASS_UPDATES("_massUpdates"),
    @XmlEnumValue("_mediaFolders")
    MEDIA_FOLDERS("_mediaFolders"),
    @XmlEnumValue("_memorizedTransactions")
    MEMORIZED_TRANSACTIONS("_memorizedTransactions"),
    @XmlEnumValue("_mobileDeviceAccess")
    MOBILE_DEVICE_ACCESS("_mobileDeviceAccess"),
    @XmlEnumValue("_netWorth")
    NET_WORTH("_netWorth"),
    @XmlEnumValue("_nonPostingRegisters")
    NON_POSTING_REGISTERS("_nonPostingRegisters"),
    @XmlEnumValue("_noPermissionNecessary")
    NO_PERMISSION_NECESSARY("_noPermissionNecessary"),
    @XmlEnumValue("_notesTab")
    NOTES_TAB("_notesTab"),
    @XmlEnumValue("_offlineClient")
    OFFLINE_CLIENT("_offlineClient"),
    @XmlEnumValue("_onlineBankingStatement")
    ONLINE_BANKING_STATEMENT("_onlineBankingStatement"),
    @XmlEnumValue("_onlineCaseForm")
    ONLINE_CASE_FORM("_onlineCaseForm"),
    @XmlEnumValue("_onlineCustomerForm")
    ONLINE_CUSTOMER_FORM("_onlineCustomerForm"),
    @XmlEnumValue("_onlineCustomRecordForm")
    ONLINE_CUSTOM_RECORD_FORM("_onlineCustomRecordForm"),
    @XmlEnumValue("_openidSingleSignOn")
    OPENID_SINGLE_SIGN_ON("_openidSingleSignOn"),
    @XmlEnumValue("_opportunity")
    OPPORTUNITY("_opportunity"),
    @XmlEnumValue("_otherAssetRegisters")
    OTHER_ASSET_REGISTERS("_otherAssetRegisters"),
    @XmlEnumValue("_otherCurrentAssetRegisters")
    OTHER_CURRENT_ASSET_REGISTERS("_otherCurrentAssetRegisters"),
    @XmlEnumValue("_otherCurrentLiabilityRegisters")
    OTHER_CURRENT_LIABILITY_REGISTERS("_otherCurrentLiabilityRegisters"),
    @XmlEnumValue("_otherCustomFields")
    OTHER_CUSTOM_FIELDS("_otherCustomFields"),
    @XmlEnumValue("_otherExpenseRegisters")
    OTHER_EXPENSE_REGISTERS("_otherExpenseRegisters"),
    @XmlEnumValue("_otherIncomeRegisters")
    OTHER_INCOME_REGISTERS("_otherIncomeRegisters"),
    @XmlEnumValue("_otherLists")
    OTHER_LISTS("_otherLists"),
    @XmlEnumValue("_otherNames")
    OTHER_NAMES("_otherNames"),
    @XmlEnumValue("_outlookIntegration")
    OUTLOOK_INTEGRATION("_outlookIntegration"),
    @XmlEnumValue("_outlookIntegration3")
    OUTLOOK_INTEGRATION_3("_outlookIntegration3"),
    @XmlEnumValue("_overrideEstimatedCostOnTransactions")
    OVERRIDE_ESTIMATED_COST_ON_TRANSACTIONS("_overrideEstimatedCostOnTransactions"),
    @XmlEnumValue("_overridePaymentHold")
    OVERRIDE_PAYMENT_HOLD("_overridePaymentHold"),
    @XmlEnumValue("_overridePeriodRestrictions")
    OVERRIDE_PERIOD_RESTRICTIONS("_overridePeriodRestrictions"),
    @XmlEnumValue("_partnerAuthorizedCommissionReports")
    PARTNER_AUTHORIZED_COMMISSION_REPORTS("_partnerAuthorizedCommissionReports"),
    @XmlEnumValue("_partnerCommissionReports")
    PARTNER_COMMISSION_REPORTS("_partnerCommissionReports"),
    @XmlEnumValue("_partnerCommissionSchedulesPlans")
    PARTNER_COMMISSION_SCHEDULES_PLANS("_partnerCommissionSchedulesPlans"),
    @XmlEnumValue("_partnerCommissionTransaction")
    PARTNER_COMMISSION_TRANSACTION("_partnerCommissionTransaction"),
    @XmlEnumValue("_partnerCommissionTransactionApproval")
    PARTNER_COMMISSION_TRANSACTION_APPROVAL("_partnerCommissionTransactionApproval"),
    @XmlEnumValue("_partnerContribution")
    PARTNER_CONTRIBUTION("_partnerContribution"),
    @XmlEnumValue("_partnerMonitorResults")
    PARTNER_MONITOR_RESULTS("_partnerMonitorResults"),
    @XmlEnumValue("_partners")
    PARTNERS("_partners"),
    @XmlEnumValue("_payBills")
    PAY_BILLS("_payBills"),
    @XmlEnumValue("_paycheckJournal")
    PAYCHECK_JOURNAL("_paycheckJournal"),
    @XmlEnumValue("_paychecks")
    PAYCHECKS("_paychecks"),
    @XmlEnumValue("_paymentMethods")
    PAYMENT_METHODS("_paymentMethods"),
    @XmlEnumValue("_payrollCheckRegister")
    PAYROLL_CHECK_REGISTER("_payrollCheckRegister"),
    @XmlEnumValue("_payrollHoursAndEarnings")
    PAYROLL_HOURS_AND_EARNINGS("_payrollHoursAndEarnings"),
    @XmlEnumValue("_payrollItems")
    PAYROLL_ITEMS("_payrollItems"),
    @XmlEnumValue("_payrollJournalReport")
    PAYROLL_JOURNAL_REPORT("_payrollJournalReport"),
    @XmlEnumValue("_payrollLiabilityPayments")
    PAYROLL_LIABILITY_PAYMENTS("_payrollLiabilityPayments"),
    @XmlEnumValue("_payrollLiabilityReport")
    PAYROLL_LIABILITY_REPORT("_payrollLiabilityReport"),
    @XmlEnumValue("_payrollStateWithholding")
    PAYROLL_STATE_WITHHOLDING("_payrollStateWithholding"),
    @XmlEnumValue("_payrollSummaryAndDetailReports")
    PAYROLL_SUMMARY_AND_DETAIL_REPORTS("_payrollSummaryAndDetailReports"),
    @XmlEnumValue("_paySalesTax")
    PAY_SALES_TAX("_paySalesTax"),
    @XmlEnumValue("_payTaxLiability")
    PAY_TAX_LIABILITY("_payTaxLiability"),
    @XmlEnumValue("_pdfMessages")
    PDF_MESSAGES("_pdfMessages"),
    @XmlEnumValue("_pdfTemplate")
    PDF_TEMPLATE("_pdfTemplate"),
    @XmlEnumValue("_performSearch")
    PERFORM_SEARCH("_performSearch"),
    @XmlEnumValue("_phoneCalls")
    PHONE_CALLS("_phoneCalls"),
    @XmlEnumValue("_plannedStandardCost")
    PLANNED_STANDARD_COST("_plannedStandardCost"),
    @XmlEnumValue("_postingPeriodOnTransactions")
    POSTING_PERIOD_ON_TRANSACTIONS("_postingPeriodOnTransactions"),
    @XmlEnumValue("_postTime")
    POST_TIME("_postTime"),
    @XmlEnumValue("_postVendorBillVariances")
    POST_VENDOR_BILL_VARIANCES("_postVendorBillVariances"),
    @XmlEnumValue("_presentationCategories")
    PRESENTATION_CATEGORIES("_presentationCategories"),
    @XmlEnumValue("_printChecksAndForms")
    PRINT_CHECKS_AND_FORMS("_printChecksAndForms"),
    @XmlEnumValue("_printEmailFax")
    PRINT_EMAIL_FAX("_printEmailFax"),
    @XmlEnumValue("_printShipmentDocuments")
    PRINT_SHIPMENT_DOCUMENTS("_printShipmentDocuments"),
    @XmlEnumValue("_processGSTRefund")
    PROCESS_GST_REFUND("_processGSTRefund"),
    @XmlEnumValue("_processPayroll")
    PROCESS_PAYROLL("_processPayroll"),
    @XmlEnumValue("_projectProjectTemplateConversion")
    PROJECT_PROJECT_TEMPLATE_CONVERSION("_projectProjectTemplateConversion"),
    @XmlEnumValue("_projectTasks")
    PROJECT_TASKS("_projectTasks"),
    @XmlEnumValue("_projectTemplates")
    PROJECT_TEMPLATES("_projectTemplates"),
    @XmlEnumValue("_promotionCode")
    PROMOTION_CODE("_promotionCode"),
    @XmlEnumValue("_provisioning")
    PROVISIONING("_provisioning"),
    @XmlEnumValue("_provisioningForQa")
    PROVISIONING_FOR_QA("_provisioningForQa"),
    @XmlEnumValue("_provisionTestDrive")
    PROVISION_TEST_DRIVE("_provisionTestDrive"),
    @XmlEnumValue("_pstSummaryReport")
    PST_SUMMARY_REPORT("_pstSummaryReport"),
    @XmlEnumValue("_publicTemplateCategories")
    PUBLIC_TEMPLATE_CATEGORIES("_publicTemplateCategories"),
    @XmlEnumValue("_publishDashboards")
    PUBLISH_DASHBOARDS("_publishDashboards"),
    @XmlEnumValue("_publishEmployeeList")
    PUBLISH_EMPLOYEE_LIST("_publishEmployeeList"),
    @XmlEnumValue("_publishForms")
    PUBLISH_FORMS("_publishForms"),
    @XmlEnumValue("_publishKnowledgeBase")
    PUBLISH_KNOWLEDGE_BASE("_publishKnowledgeBase"),
    @XmlEnumValue("_publishRSSFeeds")
    PUBLISH_RSS_FEEDS("_publishRSSFeeds"),
    @XmlEnumValue("_publishSearch")
    PUBLISH_SEARCH("_publishSearch"),
    @XmlEnumValue("_purchaseContract")
    PURCHASE_CONTRACT("_purchaseContract"),
    @XmlEnumValue("_purchaseContractApproval")
    PURCHASE_CONTRACT_APPROVAL("_purchaseContractApproval"),
    @XmlEnumValue("_purchaseOrder")
    PURCHASE_ORDER("_purchaseOrder"),
    @XmlEnumValue("_purchaseOrderReports")
    PURCHASE_ORDER_REPORTS("_purchaseOrderReports"),
    @XmlEnumValue("_purchases")
    PURCHASES("_purchases"),
    @XmlEnumValue("_quantityPricingSchedules")
    QUANTITY_PRICING_SCHEDULES("_quantityPricingSchedules"),
    @XmlEnumValue("_quotaReports")
    QUOTA_REPORTS("_quotaReports"),
    @XmlEnumValue("_receiveOrder")
    RECEIVE_ORDER("_receiveOrder"),
    @XmlEnumValue("_receiveReturns")
    RECEIVE_RETURNS("_receiveReturns"),
    @XmlEnumValue("_recognizeGiftCertificateIncome")
    RECOGNIZE_GIFT_CERTIFICATE_INCOME("_recognizeGiftCertificateIncome"),
    @XmlEnumValue("_reconcile")
    RECONCILE("_reconcile"),
    @XmlEnumValue("_reconcileReporting")
    RECONCILE_REPORTING("_reconcileReporting"),
    @XmlEnumValue("_recordCustomField")
    RECORD_CUSTOM_FIELD("_recordCustomField"),
    @XmlEnumValue("_refundReturns")
    REFUND_RETURNS("_refundReturns"),
    @XmlEnumValue("_relatedItems")
    RELATED_ITEMS("_relatedItems"),
    @XmlEnumValue("_reportCustomization")
    REPORT_CUSTOMIZATION("_reportCustomization"),
    @XmlEnumValue("_reportScheduling")
    REPORT_SCHEDULING("_reportScheduling"),
    @XmlEnumValue("_requisition")
    REQUISITION("_requisition"),
    @XmlEnumValue("_requisitionApproval")
    REQUISITION_APPROVAL("_requisitionApproval"),
    @XmlEnumValue("_resource")
    RESOURCE("_resource"),
    @XmlEnumValue("_resourceAllocationApproval")
    RESOURCE_ALLOCATION_APPROVAL("_resourceAllocationApproval"),
    @XmlEnumValue("_resourceAllocationReports")
    RESOURCE_ALLOCATION_REPORTS("_resourceAllocationReports"),
    @XmlEnumValue("_resourceAllocations")
    RESOURCE_ALLOCATIONS("_resourceAllocations"),
    @XmlEnumValue("_returnAuthApproval")
    RETURN_AUTH_APPROVAL("_returnAuthApproval"),
    @XmlEnumValue("_returnAuthorization")
    RETURN_AUTHORIZATION("_returnAuthorization"),
    @XmlEnumValue("_returnAuthorizationReports")
    RETURN_AUTHORIZATION_REPORTS("_returnAuthorizationReports"),
    @XmlEnumValue("_revalueInventoryCost")
    REVALUE_INVENTORY_COST("_revalueInventoryCost"),
    @XmlEnumValue("_revenueCommitment")
    REVENUE_COMMITMENT("_revenueCommitment"),
    @XmlEnumValue("_revenueCommitmentReversal")
    REVENUE_COMMITMENT_REVERSAL("_revenueCommitmentReversal"),
    @XmlEnumValue("_revenueContracts")
    REVENUE_CONTRACTS("_revenueContracts"),
    @XmlEnumValue("_revenueManagementVSOE")
    REVENUE_MANAGEMENT_VSOE("_revenueManagementVSOE"),
    @XmlEnumValue("_revenueRecognitionReports")
    REVENUE_RECOGNITION_REPORTS("_revenueRecognitionReports"),
    @XmlEnumValue("_revenueRecognitionSchedules")
    REVENUE_RECOGNITION_SCHEDULES("_revenueRecognitionSchedules"),
    @XmlEnumValue("_roles")
    ROLES("_roles"),
    @XmlEnumValue("_runPayroll")
    RUN_PAYROLL("_runPayroll"),
    @XmlEnumValue("_sales")
    SALES("_sales"),
    @XmlEnumValue("_salesByPartner")
    SALES_BY_PARTNER("_salesByPartner"),
    @XmlEnumValue("_salesByPromotionCode")
    SALES_BY_PROMOTION_CODE("_salesByPromotionCode"),
    @XmlEnumValue("_salesCampaigns")
    SALES_CAMPAIGNS("_salesCampaigns"),
    @XmlEnumValue("_salesForceAutomation")
    SALES_FORCE_AUTOMATION("_salesForceAutomation"),
    @XmlEnumValue("_salesForceAutomationSetup")
    SALES_FORCE_AUTOMATION_SETUP("_salesForceAutomationSetup"),
    @XmlEnumValue("_salesOrder")
    SALES_ORDER("_salesOrder"),
    @XmlEnumValue("_salesOrderApproval")
    SALES_ORDER_APPROVAL("_salesOrderApproval"),
    @XmlEnumValue("_salesOrderFulfillmentReports")
    SALES_ORDER_FULFILLMENT_REPORTS("_salesOrderFulfillmentReports"),
    @XmlEnumValue("_salesOrderReports")
    SALES_ORDER_REPORTS("_salesOrderReports"),
    @XmlEnumValue("_salesOrderTransactionReport")
    SALES_ORDER_TRANSACTION_REPORT("_salesOrderTransactionReport"),
    @XmlEnumValue("_salesRoles")
    SALES_ROLES("_salesRoles"),
    @XmlEnumValue("_salesTerritory")
    SALES_TERRITORY("_salesTerritory"),
    @XmlEnumValue("_salesTerritoryRule")
    SALES_TERRITORY_RULE("_salesTerritoryRule"),
    @XmlEnumValue("_samlSingleSignOn")
    SAML_SINGLE_SIGN_ON("_samlSingleSignOn"),
    @XmlEnumValue("_scheduleMassUpdates")
    SCHEDULE_MASS_UPDATES("_scheduleMassUpdates"),
    @XmlEnumValue("_setUpAccounting")
    SET_UP_ACCOUNTING("_setUpAccounting"),
    @XmlEnumValue("_setUpAchProcessing")
    SET_UP_ACH_PROCESSING("_setUpAchProcessing"),
    @XmlEnumValue("_setUpAdpPayroll")
    SET_UP_ADP_PAYROLL("_setUpAdpPayroll"),
    @XmlEnumValue("_setUpBillPay")
    SET_UP_BILL_PAY("_setUpBillPay"),
    @XmlEnumValue("_setUpBudgets")
    SET_UP_BUDGETS("_setUpBudgets"),
    @XmlEnumValue("_setUpCampaignEmailAddresses")
    SET_UP_CAMPAIGN_EMAIL_ADDRESSES("_setUpCampaignEmailAddresses"),
    @XmlEnumValue("_setupCampaigns")
    SETUP_CAMPAIGNS("_setupCampaigns"),
    @XmlEnumValue("_setUpCompany")
    SET_UP_COMPANY("_setUpCompany"),
    @XmlEnumValue("_setUpCsvPreferences")
    SET_UP_CSV_PREFERENCES("_setUpCsvPreferences"),
    @XmlEnumValue("_setUpDomains")
    SET_UP_DOMAINS("_setUpDomains"),
    @XmlEnumValue("_setUpEbay")
    SET_UP_EBAY("_setUpEbay"),
    @XmlEnumValue("_setUpImageResizing")
    SET_UP_IMAGE_RESIZING("_setUpImageResizing"),
    @XmlEnumValue("_setUpOpenidSingleSignOn")
    SET_UP_OPENID_SINGLE_SIGN_ON("_setUpOpenidSingleSignOn"),
    @XmlEnumValue("_setUpPartnerMonitor")
    SET_UP_PARTNER_MONITOR("_setUpPartnerMonitor"),
    @XmlEnumValue("_setUpPayroll")
    SET_UP_PAYROLL("_setUpPayroll"),
    @XmlEnumValue("_setUpReminders")
    SET_UP_REMINDERS("_setUpReminders"),
    @XmlEnumValue("_setUpSamlSingleSignOn")
    SET_UP_SAML_SINGLE_SIGN_ON("_setUpSamlSingleSignOn"),
    @XmlEnumValue("_setUpSnapshots")
    SET_UP_SNAPSHOTS("_setUpSnapshots"),
    @XmlEnumValue("_setUpSynchronization")
    SET_UP_SYNCHRONIZATION("_setUpSynchronization"),
    @XmlEnumValue("_setUpWebServices")
    SET_UP_WEB_SERVICES("_setUpWebServices"),
    @XmlEnumValue("_setUpWebSite")
    SET_UP_WEB_SITE("_setUpWebSite"),
    @XmlEnumValue("_setUpYearStatus")
    SET_UP_YEAR_STATUS("_setUpYearStatus"),
    @XmlEnumValue("_shippingItems")
    SHIPPING_ITEMS("_shippingItems"),
    @XmlEnumValue("_shippingPartnerPackage")
    SHIPPING_PARTNER_PACKAGE("_shippingPartnerPackage"),
    @XmlEnumValue("_shippingPartnerRegistration")
    SHIPPING_PARTNER_REGISTRATION("_shippingPartnerRegistration"),
    @XmlEnumValue("_shippingPartnerShipment")
    SHIPPING_PARTNER_SHIPMENT("_shippingPartnerShipment"),
    @XmlEnumValue("_shortcuts")
    SHORTCUTS("_shortcuts"),
    @XmlEnumValue("_standardCostVersion")
    STANDARD_COST_VERSION("_standardCostVersion"),
    @XmlEnumValue("_statementCharge")
    STATEMENT_CHARGE("_statementCharge"),
    @XmlEnumValue("_statisticalAccountRegisters")
    STATISTICAL_ACCOUNT_REGISTERS("_statisticalAccountRegisters"),
    @XmlEnumValue("_storeCategories")
    STORE_CATEGORIES("_storeCategories"),
    @XmlEnumValue("_storeContentCategories")
    STORE_CONTENT_CATEGORIES("_storeContentCategories"),
    @XmlEnumValue("_storeContentItems")
    STORE_CONTENT_ITEMS("_storeContentItems"),
    @XmlEnumValue("_storeLogoUpload")
    STORE_LOGO_UPLOAD("_storeLogoUpload"),
    @XmlEnumValue("_storeTabs")
    STORE_TABS("_storeTabs"),
    @XmlEnumValue("_subsidiaries")
    SUBSIDIARIES("_subsidiaries"),
    @XmlEnumValue("_suiteBundler")
    SUITE_BUNDLER("_suiteBundler"),
    @XmlEnumValue("_suiteBundlerUpgrades")
    SUITE_BUNDLER_UPGRADES("_suiteBundlerUpgrades"),
    @XmlEnumValue("_suiteScript")
    SUITE_SCRIPT("_suiteScript"),
    @XmlEnumValue("_suiteSignon")
    SUITE_SIGNON("_suiteSignon"),
    @XmlEnumValue("_support")
    SUPPORT("_support"),
    @XmlEnumValue("_supportCaseIssue")
    SUPPORT_CASE_ISSUE("_supportCaseIssue"),
    @XmlEnumValue("_supportCaseOrigin")
    SUPPORT_CASE_ORIGIN("_supportCaseOrigin"),
    @XmlEnumValue("_supportCasePriority")
    SUPPORT_CASE_PRIORITY("_supportCasePriority"),
    @XmlEnumValue("_supportCaseSnapshotReminders")
    SUPPORT_CASE_SNAPSHOT_REMINDERS("_supportCaseSnapshotReminders"),
    @XmlEnumValue("_supportCaseStatus")
    SUPPORT_CASE_STATUS("_supportCaseStatus"),
    @XmlEnumValue("_supportCaseTerritory")
    SUPPORT_CASE_TERRITORY("_supportCaseTerritory"),
    @XmlEnumValue("_supportCaseTerritoryRule")
    SUPPORT_CASE_TERRITORY_RULE("_supportCaseTerritoryRule"),
    @XmlEnumValue("_supportCaseType")
    SUPPORT_CASE_TYPE("_supportCaseType"),
    @XmlEnumValue("_supportSetup")
    SUPPORT_SETUP("_supportSetup"),
    @XmlEnumValue("_swapPricesBetweenPriceLevels")
    SWAP_PRICES_BETWEEN_PRICE_LEVELS("_swapPricesBetweenPriceLevels"),
    @XmlEnumValue("_systemEmailTemplate")
    SYSTEM_EMAIL_TEMPLATE("_systemEmailTemplate"),
    @XmlEnumValue("_systemStatus")
    SYSTEM_STATUS("_systemStatus"),
    @XmlEnumValue("_tasks")
    TASKS("_tasks"),
    @XmlEnumValue("_tax")
    TAX("_tax"),
    @XmlEnumValue("_taxItems")
    TAX_ITEMS("_taxItems"),
    @XmlEnumValue("_taxSchedules")
    TAX_SCHEDULES("_taxSchedules"),
    @XmlEnumValue("_teamSellingContribution")
    TEAM_SELLING_CONTRIBUTION("_teamSellingContribution"),
    @XmlEnumValue("_tegataAccounts")
    TEGATA_ACCOUNTS("_tegataAccounts"),
    @XmlEnumValue("_tegataPayable")
    TEGATA_PAYABLE("_tegataPayable"),
    @XmlEnumValue("_tegataReceivable")
    TEGATA_RECEIVABLE("_tegataReceivable"),
    @XmlEnumValue("_telephonyIntegration")
    TELEPHONY_INTEGRATION("_telephonyIntegration"),
    @XmlEnumValue("_templateCategories")
    TEMPLATE_CATEGORIES("_templateCategories"),
    @XmlEnumValue("_testdriveMasters")
    TESTDRIVE_MASTERS("_testdriveMasters"),
    @XmlEnumValue("_timer")
    TIMER("_timer"),
    @XmlEnumValue("_timeTracking")
    TIME_TRACKING("_timeTracking"),
    @XmlEnumValue("_trackMessages")
    TRACK_MESSAGES("_trackMessages"),
    @XmlEnumValue("_trackTime")
    TRACK_TIME("_trackTime"),
    @XmlEnumValue("_transactionDetail")
    TRANSACTION_DETAIL("_transactionDetail"),
    @XmlEnumValue("_transactionNumberingAuditLog")
    TRANSACTION_NUMBERING_AUDIT_LOG("_transactionNumberingAuditLog"),
    @XmlEnumValue("_transferFunds")
    TRANSFER_FUNDS("_transferFunds"),
    @XmlEnumValue("_transferInventory")
    TRANSFER_INVENTORY("_transferInventory"),
    @XmlEnumValue("_transferOrder")
    TRANSFER_ORDER("_transferOrder"),
    @XmlEnumValue("_transferOrderApproval")
    TRANSFER_ORDER_APPROVAL("_transferOrderApproval"),
    @XmlEnumValue("_translation")
    TRANSLATION("_translation"),
    @XmlEnumValue("_trialBalance")
    TRIAL_BALANCE("_trialBalance"),
    @XmlEnumValue("_twoFactorAuthentication")
    TWO_FACTOR_AUTHENTICATION("_twoFactorAuthentication"),
    @XmlEnumValue("_unbilledReceivableRegisters")
    UNBILLED_RECEIVABLE_REGISTERS("_unbilledReceivableRegisters"),
    @XmlEnumValue("_unbuildAssemblies")
    UNBUILD_ASSEMBLIES("_unbuildAssemblies"),
    @XmlEnumValue("_uncategorizedPresentationItems")
    UNCATEGORIZED_PRESENTATION_ITEMS("_uncategorizedPresentationItems"),
    @XmlEnumValue("_units")
    UNITS("_units"),
    @XmlEnumValue("_updatePrices")
    UPDATE_PRICES("_updatePrices"),
    @XmlEnumValue("_upsellAssistant")
    UPSELL_ASSISTANT("_upsellAssistant"),
    @XmlEnumValue("_upsellSetup")
    UPSELL_SETUP("_upsellSetup"),
    @XmlEnumValue("_upsellWizard")
    UPSELL_WIZARD("_upsellWizard"),
    @XmlEnumValue("_userPreferences")
    USER_PREFERENCES("_userPreferences"),
    @XmlEnumValue("_usersAndPasswords")
    USERS_AND_PASSWORDS("_usersAndPasswords"),
    @XmlEnumValue("_vendorBillApproval")
    VENDOR_BILL_APPROVAL("_vendorBillApproval"),
    @XmlEnumValue("_vendorPaymentStatus")
    VENDOR_PAYMENT_STATUS("_vendorPaymentStatus"),
    @XmlEnumValue("_vendorReturnAuthApproval")
    VENDOR_RETURN_AUTH_APPROVAL("_vendorReturnAuthApproval"),
    @XmlEnumValue("_vendorReturnAuthorization")
    VENDOR_RETURN_AUTHORIZATION("_vendorReturnAuthorization"),
    @XmlEnumValue("_vendorReturns")
    VENDOR_RETURNS("_vendorReturns"),
    @XmlEnumValue("_vendors")
    VENDORS("_vendors"),
    @XmlEnumValue("_viewLoginAuditTrail")
    VIEW_LOGIN_AUDIT_TRAIL("_viewLoginAuditTrail"),
    @XmlEnumValue("_viewOnlineBillPayStatus")
    VIEW_ONLINE_BILL_PAY_STATUS("_viewOnlineBillPayStatus"),
    @XmlEnumValue("_viewPaymentEvents")
    VIEW_PAYMENT_EVENTS("_viewPaymentEvents"),
    @XmlEnumValue("_viewUnencryptedCreditCards")
    VIEW_UNENCRYPTED_CREDIT_CARDS("_viewUnencryptedCreditCards"),
    @XmlEnumValue("_webServices")
    WEB_SERVICES("_webServices"),
    @XmlEnumValue("_webSiteExternalPublisher")
    WEB_SITE_EXTERNAL_PUBLISHER("_webSiteExternalPublisher"),
    @XmlEnumValue("_webSiteReport")
    WEB_SITE_REPORT("_webSiteReport"),
    @XmlEnumValue("_webStoreEmailTemplate")
    WEB_STORE_EMAIL_TEMPLATE("_webStoreEmailTemplate"),
    @XmlEnumValue("_webStoreReport")
    WEB_STORE_REPORT("_webStoreReport"),
    @XmlEnumValue("_workCalendar")
    WORK_CALENDAR("_workCalendar"),
    @XmlEnumValue("_workflow")
    WORKFLOW("_workflow"),
    @XmlEnumValue("_workOrder")
    WORK_ORDER("_workOrder"),
    @XmlEnumValue("_workOrderClose")
    WORK_ORDER_CLOSE("_workOrderClose"),
    @XmlEnumValue("_workOrderCompletion")
    WORK_ORDER_COMPLETION("_workOrderCompletion"),
    @XmlEnumValue("_workOrderIssue")
    WORK_ORDER_ISSUE("_workOrderIssue"),
    @XmlEnumValue("_workplaces")
    WORKPLACES("_workplaces");
    private final String value;

    PermissionCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PermissionCode fromValue(String v) {
        for (PermissionCode c: PermissionCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
