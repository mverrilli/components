
package com.netsuite.webservices.v2014_2.platform.faults.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusDetailCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatusDetailCodeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ABORT_SEARCH_EXCEEDED_MAX_TIME"/&gt;
 *     &lt;enumeration value="ABORT_UPLOAD_VIRUS_DETECTED"/&gt;
 *     &lt;enumeration value="ACCESS_DENIED"/&gt;
 *     &lt;enumeration value="ACCTNG_PRD_REQD"/&gt;
 *     &lt;enumeration value="ACCT_DISABLED"/&gt;
 *     &lt;enumeration value="ACCT_MERGE_DUP"/&gt;
 *     &lt;enumeration value="ACCT_NAME_REQD"/&gt;
 *     &lt;enumeration value="ACCT_NEEDS_CAMPAIGN_PROVISION"/&gt;
 *     &lt;enumeration value="ACCT_NOT_CREATED"/&gt;
 *     &lt;enumeration value="ACCT_NUMS_REQD_OR_DONT_MATCH"/&gt;
 *     &lt;enumeration value="ACCT_NUM_REQD"/&gt;
 *     &lt;enumeration value="ACCT_PERIOD_SETUP_REQD"/&gt;
 *     &lt;enumeration value="ACCT_PRDS_BEING_ADDED"/&gt;
 *     &lt;enumeration value="ACCT_REQD"/&gt;
 *     &lt;enumeration value="ACCT_TEMP_DISABLED"/&gt;
 *     &lt;enumeration value="ACCT_TEMP_UNAVAILABLE"/&gt;
 *     &lt;enumeration value="ACH_NOT_AVAILBL"/&gt;
 *     &lt;enumeration value="ACH_SETUP_REQD"/&gt;
 *     &lt;enumeration value="ACTIVE_AP_ACCT_REQD"/&gt;
 *     &lt;enumeration value="ACTIVE_ROLE_REQD"/&gt;
 *     &lt;enumeration value="ACTIVE_TRANS_EXIST"/&gt;
 *     &lt;enumeration value="ADDITIONAL_AUTHENTICATION_REQUIRED_2FA"/&gt;
 *     &lt;enumeration value="ADDITIONAL_AUTHENTICATION_REQUIRED_SQ"/&gt;
 *     &lt;enumeration value="ADDRESS_LINE_1_REQD"/&gt;
 *     &lt;enumeration value="ADMIN_ACCESS_REQ"/&gt;
 *     &lt;enumeration value="ADMIN_ACCESS_REQD"/&gt;
 *     &lt;enumeration value="ADMIN_ONLY_ACCESS"/&gt;
 *     &lt;enumeration value="ADMIN_USER_REQD"/&gt;
 *     &lt;enumeration value="ADMISSIBILITY_PACKG_TYP_REQD"/&gt;
 *     &lt;enumeration value="ALL_DATA_DELETE_REQD"/&gt;
 *     &lt;enumeration value="ALL_MTRX_SUBITMES_OPTNS_REQD"/&gt;
 *     &lt;enumeration value="ALREADY_IN_INVT"/&gt;
 *     &lt;enumeration value="AMORTZN_INVALID_DATE_RANGE"/&gt;
 *     &lt;enumeration value="AMORTZN_TMPLT_DATA_MISSING"/&gt;
 *     &lt;enumeration value="AMT_DISALLWD"/&gt;
 *     &lt;enumeration value="AMT_EXCEEDS_APPROVAL_LIMIT"/&gt;
 *     &lt;enumeration value="ANSWER_REQD"/&gt;
 *     &lt;enumeration value="APPROVAL_PERMS_REQD"/&gt;
 *     &lt;enumeration value="AREA_CODE_REQD"/&gt;
 *     &lt;enumeration value="ASSIGNEE_REQD"/&gt;
 *     &lt;enumeration value="ATTACHMNT_CONTAINS_VIRUS"/&gt;
 *     &lt;enumeration value="ATTACH_SIZE_EXCEEDED"/&gt;
 *     &lt;enumeration value="AT_LEAST_ONE_FILE_REQD"/&gt;
 *     &lt;enumeration value="AT_LEAST_ONE_PACKAGE_REQD"/&gt;
 *     &lt;enumeration value="AT_LEAST_ONE_RETURN_FLD_REQD"/&gt;
 *     &lt;enumeration value="AT_LEAST_ONE_SUB_REQD"/&gt;
 *     &lt;enumeration value="AUDIT_W2_1099"/&gt;
 *     &lt;enumeration value="AUTO_NUM_UPDATE_DISALLWD"/&gt;
 *     &lt;enumeration value="AVS_ERROR"/&gt;
 *     &lt;enumeration value="BALANCE_EXCEEDS_CREDIT_LIMIT"/&gt;
 *     &lt;enumeration value="BANK_ACCT_REQD"/&gt;
 *     &lt;enumeration value="BASE_CRNCY_REQD"/&gt;
 *     &lt;enumeration value="BILLABLES_DISALLWD"/&gt;
 *     &lt;enumeration value="BILLING_ISSUES"/&gt;
 *     &lt;enumeration value="BILLING_SCHDUL_INVALID_RECURR"/&gt;
 *     &lt;enumeration value="BILLPAY_APPROVAL_UNAVAILBL"/&gt;
 *     &lt;enumeration value="BILLPAY_REGSTRTN_REQD"/&gt;
 *     &lt;enumeration value="BILLPAY_SRVC_UNAVAILBL"/&gt;
 *     &lt;enumeration value="BILL_PAY_STATUS_UNAVAILABLE"/&gt;
 *     &lt;enumeration value="BILL_PMTS_MADE_FROM_ACCT_ONLY"/&gt;
 *     &lt;enumeration value="BIN_DSNT_CONTAIN_ENOUGH_ITEM"/&gt;
 *     &lt;enumeration value="BIN_ITEM_UNAVAILBL"/&gt;
 *     &lt;enumeration value="BIN_SETUP_REQD"/&gt;
 *     &lt;enumeration value="BIN_UNDEFND"/&gt;
 *     &lt;enumeration value="BUNDLE_IS_DEPRECATED"/&gt;
 *     &lt;enumeration value="CALENDAR_PREFS_REQD"/&gt;
 *     &lt;enumeration value="CAMPAGIN_ALREADY_EXECUTED"/&gt;
 *     &lt;enumeration value="CAMPAIGN_IN_USE"/&gt;
 *     &lt;enumeration value="CAMPAIGN_SET_UP_REQD"/&gt;
 *     &lt;enumeration value="CANNOT_RESET_PASSWORD"/&gt;
 *     &lt;enumeration value="CANT_APPLY_PMT"/&gt;
 *     &lt;enumeration value="CANT_AUTO_CREATE_ADJSTMNT"/&gt;
 *     &lt;enumeration value="CANT_CALC_FEDEX_RATES"/&gt;
 *     &lt;enumeration value="CANT_CANCEL_APPRVD_RETRN_AUTH"/&gt;
 *     &lt;enumeration value="CANT_CANCEL_BILL_PMT"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_COMMSSN"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_CONTACT_RESTRICTN"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_CRMRECORDTYPELINKS"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_EVENT_PRIMARY_TYP"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_IP_ADDRESS"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_LEAD_SOURCE_CAT"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_PSWD"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_REV_REC_TMPLT"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_SUB"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_TASK_LINK"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_UNITS_TYP"/&gt;
 *     &lt;enumeration value="CANT_CHANGE_VSOE_ALLOCTN"/&gt;
 *     &lt;enumeration value="CANT_CHG_POSTED_BILL_VRNC"/&gt;
 *     &lt;enumeration value="CANT_COMPLETE_FULFILL"/&gt;
 *     &lt;enumeration value="CANT_CONNECT_TO_STORE"/&gt;
 *     &lt;enumeration value="CANT_CONVERT_CLASS_DEPT"/&gt;
 *     &lt;enumeration value="CANT_CONVERT_CLASS_LOC"/&gt;
 *     &lt;enumeration value="CANT_CONVERT_INVT_ITEM"/&gt;
 *     &lt;enumeration value="CANT_CREATE_FILES"/&gt;
 *     &lt;enumeration value="CANT_CREATE_NON_UNIQUE_RCRD"/&gt;
 *     &lt;enumeration value="CANT_CREATE_PO"/&gt;
 *     &lt;enumeration value="CANT_CREATE_SHIP_LABEL"/&gt;
 *     &lt;enumeration value="CANT_CREATE_WORK_ORD"/&gt;
 *     &lt;enumeration value="CANT_CREAT_SHIP_LABEL"/&gt;
 *     &lt;enumeration value="CANT_DELETE_ACCT"/&gt;
 *     &lt;enumeration value="CANT_DELETE_ACCT_PRD"/&gt;
 *     &lt;enumeration value="CANT_DELETE_ALLOCTN"/&gt;
 *     &lt;enumeration value="CANT_DELETE_BIN"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CATEGORY"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CC_PROCESSOR"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CELL"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CHILD_RCRDS_EXIST"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CHILD_RCRD_FOUND"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CLASS"/&gt;
 *     &lt;enumeration value="CANT_DELETE_COLOR_THEME"/&gt;
 *     &lt;enumeration value="CANT_DELETE_COMMSSN_SCHDUL"/&gt;
 *     &lt;enumeration value="CANT_DELETE_COMPANY"/&gt;
 *     &lt;enumeration value="CANT_DELETE_COMPANY_TYP"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CONTACT_HAS_CHILD"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CSTM_FIELD"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CSTM_FORM"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CSTM_ITEM_FIELD"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CSTM_LAYOUT"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CSTM_LIST"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CSTM_RCRD"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CSTM_RCRD_ENTRY"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CUST"/&gt;
 *     &lt;enumeration value="CANT_DELETE_CUSTOMER"/&gt;
 *     &lt;enumeration value="CANT_DELETE_DEFAULT_FLDR"/&gt;
 *     &lt;enumeration value="CANT_DELETE_DEFAULT_PRIORITY"/&gt;
 *     &lt;enumeration value="CANT_DELETE_DEFAULT_SALES_REP"/&gt;
 *     &lt;enumeration value="CANT_DELETE_DEFAULT_STATUS"/&gt;
 *     &lt;enumeration value="CANT_DELETE_DEFAULT_VALUE"/&gt;
 *     &lt;enumeration value="CANT_DELETE_DEFAULT_WEBSITE"/&gt;
 *     &lt;enumeration value="CANT_DELETE_EMPL"/&gt;
 *     &lt;enumeration value="CANT_DELETE_ENTITY"/&gt;
 *     &lt;enumeration value="CANT_DELETE_FIN_STATMNT_LAYOUT"/&gt;
 *     &lt;enumeration value="CANT_DELETE_FLDR"/&gt;
 *     &lt;enumeration value="CANT_DELETE_HAS_CHILD_ITEM"/&gt;
 *     &lt;enumeration value="CANT_DELETE_INFO_ITEM"/&gt;
 *     &lt;enumeration value="CANT_DELETE_ITEM"/&gt;
 *     &lt;enumeration value="CANT_DELETE_ITEM_LAYOUT"/&gt;
 *     &lt;enumeration value="CANT_DELETE_ITEM_TMPLT"/&gt;
 *     &lt;enumeration value="CANT_DELETE_JOB_RESOURCE_ROLE"/&gt;
 *     &lt;enumeration value="CANT_DELETE_LEGACY_CATEGORY"/&gt;
 *     &lt;enumeration value="CANT_DELETE_LINE"/&gt;
 *     &lt;enumeration value="CANT_DELETE_MEDIA_ITEM"/&gt;
 *     &lt;enumeration value="CANT_DELETE_MEMRZD_TRANS"/&gt;
 *     &lt;enumeration value="CANT_DELETE_OR_CHANGE_ACCT"/&gt;
 *     &lt;enumeration value="CANT_DELETE_PLAN_ASSGNMNT"/&gt;
 *     &lt;enumeration value="CANT_DELETE_PRESNTN_CAT"/&gt;
 *     &lt;enumeration value="CANT_DELETE_RCRD"/&gt;
 *     &lt;enumeration value="CANT_DELETE_RCRDS"/&gt;
 *     &lt;enumeration value="CANT_DELETE_SITE_TAG"/&gt;
 *     &lt;enumeration value="CANT_DELETE_SITE_THEME"/&gt;
 *     &lt;enumeration value="CANT_DELETE_SOLUTN"/&gt;
 *     &lt;enumeration value="CANT_DELETE_STATUS_TYPE"/&gt;
 *     &lt;enumeration value="CANT_DELETE_SUBTAB"/&gt;
 *     &lt;enumeration value="CANT_DELETE_SYSTEM_NOTE"/&gt;
 *     &lt;enumeration value="CANT_DELETE_TAX_VENDOR"/&gt;
 *     &lt;enumeration value="CANT_DELETE_TMPLT_RCRD"/&gt;
 *     &lt;enumeration value="CANT_DELETE_TRANS"/&gt;
 *     &lt;enumeration value="CANT_DELETE_TRAN_LINE"/&gt;
 *     &lt;enumeration value="CANT_DELETE_TRAN_LINES"/&gt;
 *     &lt;enumeration value="CANT_DELETE_UPDATE_ACCT"/&gt;
 *     &lt;enumeration value="CANT_DELETE_URL"/&gt;
 *     &lt;enumeration value="CANT_DELETE_VENDOR"/&gt;
 *     &lt;enumeration value="CANT_DEL_DEFAULT_CALENDAR"/&gt;
 *     &lt;enumeration value="CANT_DEL_DEFAULT_SHIP_METHOD"/&gt;
 *     &lt;enumeration value="CANT_DEL_REALIZED_GAINLOSS"/&gt;
 *     &lt;enumeration value="CANT_DEL_TRANS_RVRSL"/&gt;
 *     &lt;enumeration value="CANT_DIVIDE_BY_ZERO"/&gt;
 *     &lt;enumeration value="CANT_DOWNLOAD_EXPIRED_FILE"/&gt;
 *     &lt;enumeration value="CANT_EDIT_CHARGED_ORDER"/&gt;
 *     &lt;enumeration value="CANT_EDIT_CUST_LIST"/&gt;
 *     &lt;enumeration value="CANT_EDIT_CUST_PMT"/&gt;
 *     &lt;enumeration value="CANT_EDIT_DPLYMNT_IN_PROGRESS"/&gt;
 *     &lt;enumeration value="CANT_EDIT_FOLDER"/&gt;
 *     &lt;enumeration value="CANT_EDIT_OLD_CASE"/&gt;
 *     &lt;enumeration value="CANT_EDIT_STANDARD_OBJ"/&gt;
 *     &lt;enumeration value="CANT_EDIT_TAGATA"/&gt;
 *     &lt;enumeration value="CANT_EDIT_TRAN"/&gt;
 *     &lt;enumeration value="CANT_ESTABLISH_LINK"/&gt;
 *     &lt;enumeration value="CANT_FIND_BUG"/&gt;
 *     &lt;enumeration value="CANT_FIND_MAIL_MERGE_ID"/&gt;
 *     &lt;enumeration value="CANT_FIND_RCRD"/&gt;
 *     &lt;enumeration value="CANT_FIND_SAVED_IMPORT"/&gt;
 *     &lt;enumeration value="CANT_FIND_SOURCE_AMORTZN_ACCT"/&gt;
 *     &lt;enumeration value="CANT_FIND_UPS_REG_FOR_LOC"/&gt;
 *     &lt;enumeration value="CANT_FULFILL_ITEM"/&gt;
 *     &lt;enumeration value="CANT_INACTIVATE_COMMSSN_PLAN"/&gt;
 *     &lt;enumeration value="CANT_INACTIVE_DEFAULT_SYNC_CAT"/&gt;
 *     &lt;enumeration value="CANT_INACTIVE_DEFAULT_TMPLT"/&gt;
 *     &lt;enumeration value="CANT_LOAD_SAVED_SEARCH_PARAM"/&gt;
 *     &lt;enumeration value="CANT_LOGIN_WITH_OAUTH"/&gt;
 *     &lt;enumeration value="CANT_LOOKUP_FLD"/&gt;
 *     &lt;enumeration value="CANT_MAKE_CONTACT_PRIVATE"/&gt;
 *     &lt;enumeration value="CANT_MARK_SHIPPED"/&gt;
 *     &lt;enumeration value="CANT_MERGE_EMPLS"/&gt;
 *     &lt;enumeration value="CANT_MODIFY_APPRVD_TIME"/&gt;
 *     &lt;enumeration value="CANT_MODIFY_FULFILL_STATUS"/&gt;
 *     &lt;enumeration value="CANT_MODIFY_ISSUE_STATUS"/&gt;
 *     &lt;enumeration value="CANT_MODIFY_LOCKED_FLD"/&gt;
 *     &lt;enumeration value="CANT_MODIFY_PARENT"/&gt;
 *     &lt;enumeration value="CANT_MODIFY_REV_REC"/&gt;
 *     &lt;enumeration value="CANT_MODIFY_SUB"/&gt;
 *     &lt;enumeration value="CANT_MODIFY_TAGATA"/&gt;
 *     &lt;enumeration value="CANT_MODIFY_TEGATA"/&gt;
 *     &lt;enumeration value="CANT_MODIFY_VOID_TRANS"/&gt;
 *     &lt;enumeration value="CANT_MOVE_REALIZED_GAINLOSS"/&gt;
 *     &lt;enumeration value="CANT_PAY_TAGATA"/&gt;
 *     &lt;enumeration value="CANT_PROCESS_IMG"/&gt;
 *     &lt;enumeration value="CANT_RCEIV_BEFORE_FULFILL"/&gt;
 *     &lt;enumeration value="CANT_RCEIV_ITEM"/&gt;
 *     &lt;enumeration value="CANT_RECEIVE_TAGATA"/&gt;
 *     &lt;enumeration value="CANT_REJECT_ORDER"/&gt;
 *     &lt;enumeration value="CANT_REMOVE_ACH_PAY_METHOD"/&gt;
 *     &lt;enumeration value="CANT_REMOVE_APPROVAL"/&gt;
 *     &lt;enumeration value="CANT_REMOVE_DOMAIN"/&gt;
 *     &lt;enumeration value="CANT_REMOVE_NEXUS"/&gt;
 *     &lt;enumeration value="CANT_REMOVE_SCHDUL"/&gt;
 *     &lt;enumeration value="CANT_REMOVE_SUB"/&gt;
 *     &lt;enumeration value="CANT_REMOV_ALL_FULFILMNT_LINKS"/&gt;
 *     &lt;enumeration value="CANT_REMOV_ITEM_SUB"/&gt;
 *     &lt;enumeration value="CANT_RESUBMIT_FAILED_DPLYMNT"/&gt;
 *     &lt;enumeration value="CANT_RETURN_FLD"/&gt;
 *     &lt;enumeration value="CANT_RETURN_USED_GIFT_CERT"/&gt;
 *     &lt;enumeration value="CANT_REVERSE_AUTH"/&gt;
 *     &lt;enumeration value="CANT_REV_REC_BODY_AND_LINE"/&gt;
 *     &lt;enumeration value="CANT_SCHDUL_RECUR_EVENT"/&gt;
 *     &lt;enumeration value="CANT_SEND_EMAIL"/&gt;
 *     &lt;enumeration value="CANT_SET_CLOSE_DATE"/&gt;
 *     &lt;enumeration value="CANT_SET_INTERNALID"/&gt;
 *     &lt;enumeration value="CANT_SET_STATUS"/&gt;
 *     &lt;enumeration value="CANT_SWITCH_ROLES_FROM_LOGIN"/&gt;
 *     &lt;enumeration value="CANT_SWITCH_SHIP_METHOD"/&gt;
 *     &lt;enumeration value="CANT_UPDATE_ACCTNG_PRDS"/&gt;
 *     &lt;enumeration value="CANT_UPDATE_AMT"/&gt;
 *     &lt;enumeration value="CANT_UPDATE_DYNAMIC_GROUP"/&gt;
 *     &lt;enumeration value="CANT_UPDATE_FLDR"/&gt;
 *     &lt;enumeration value="CANT_UPDATE_LINKED_TRANS_LINES"/&gt;
 *     &lt;enumeration value="CANT_UPDATE_PRODUCT_FEED"/&gt;
 *     &lt;enumeration value="CANT_UPDATE_RECRD_HAS_CHANGED"/&gt;
 *     &lt;enumeration value="CANT_UPDATE_RECUR_EVENT"/&gt;
 *     &lt;enumeration value="CANT_UPDATE_ROOT_CATEGORY"/&gt;
 *     &lt;enumeration value="CANT_UPDATE_STATUS_TYPE"/&gt;
 *     &lt;enumeration value="CANT_VERIFY_CARD"/&gt;
 *     &lt;enumeration value="CANT_VOID_TRANS"/&gt;
 *     &lt;enumeration value="CARD_EXPIRED"/&gt;
 *     &lt;enumeration value="CARD_ID_REQD"/&gt;
 *     &lt;enumeration value="CASE_ALREADY_ASSIGNED"/&gt;
 *     &lt;enumeration value="CASE_DSNT_EXIST"/&gt;
 *     &lt;enumeration value="CASE_NOT_GROUP_MEMBER"/&gt;
 *     &lt;enumeration value="CASH_SALE_EDIT_DISALLWD"/&gt;
 *     &lt;enumeration value="CC_ACCT_REQD"/&gt;
 *     &lt;enumeration value="CC_ALREADY_SAVED"/&gt;
 *     &lt;enumeration value="CC_EMAIL_ADDRESS_REQD"/&gt;
 *     &lt;enumeration value="CC_NUM_REQD"/&gt;
 *     &lt;enumeration value="CC_PROCESSOR_ERROR"/&gt;
 *     &lt;enumeration value="CC_PROCESSOR_NOT_FOUND"/&gt;
 *     &lt;enumeration value="CC_SECURITY_CODE_REQD"/&gt;
 *     &lt;enumeration value="CERT_AUTH_EXPD"/&gt;
 *     &lt;enumeration value="CERT_EXPD"/&gt;
 *     &lt;enumeration value="CERT_UNAVAILABLE"/&gt;
 *     &lt;enumeration value="CHANGE_PMT_DATE_AND_REAPPROVE"/&gt;
 *     &lt;enumeration value="CHAR_ERROR"/&gt;
 *     &lt;enumeration value="CHECKOUT_EMAIL_REQD"/&gt;
 *     &lt;enumeration value="CITY_REQD"/&gt;
 *     &lt;enumeration value="CLASS_ALREADY_EXISTS"/&gt;
 *     &lt;enumeration value="CLASS_OR_DEPT_OR_CUST_REQD"/&gt;
 *     &lt;enumeration value="CLEAR_AUTOCALC"/&gt;
 *     &lt;enumeration value="CLOSED_TRAN_PRD"/&gt;
 *     &lt;enumeration value="CLOSE_PREVIOUSE_PERIOD"/&gt;
 *     &lt;enumeration value="COGS_ERROR"/&gt;
 *     &lt;enumeration value="COMMSSN_ALREADY_CALCLTD"/&gt;
 *     &lt;enumeration value="COMMSSN_FEATURE_DISABLED"/&gt;
 *     &lt;enumeration value="COMMSSN_PAYROLL_ITEM_REQD"/&gt;
 *     &lt;enumeration value="COMPANION_PROP_REQD"/&gt;
 *     &lt;enumeration value="COMPANY_DISABLED"/&gt;
 *     &lt;enumeration value="COMPANY_FLD_REQD"/&gt;
 *     &lt;enumeration value="COMP_DELETED_OR_MERGED"/&gt;
 *     &lt;enumeration value="CONCUR_BILLPAY_JOB_DISALLWD"/&gt;
 *     &lt;enumeration value="CONCUR_BULK_JOB_DISALLWD"/&gt;
 *     &lt;enumeration value="CONCUR_MASS_UPDATE_DISALLWD"/&gt;
 *     &lt;enumeration value="CONCUR_SEARCH_DISALLWD"/&gt;
 *     &lt;enumeration value="CONSLD_PRNT_AND_CHILD_DISALLWD"/&gt;
 *     &lt;enumeration value="CONTACT_ALREADY_EXISTS"/&gt;
 *     &lt;enumeration value="CONTACT_NOT_GROUP_MEMBR"/&gt;
 *     &lt;enumeration value="COOKIES_DISABLED"/&gt;
 *     &lt;enumeration value="COUNTRY_STATE_MISMATCH"/&gt;
 *     &lt;enumeration value="CREATEDFROM_REQD"/&gt;
 *     &lt;enumeration value="CREDITS_DISALLWD"/&gt;
 *     &lt;enumeration value="CRNCY_MISMATCH_BASE_CRNCY"/&gt;
 *     &lt;enumeration value="CRNCY_NOT_UPDATED"/&gt;
 *     &lt;enumeration value="CRNCY_RCRD_DELETED"/&gt;
 *     &lt;enumeration value="CRNCY_REQD"/&gt;
 *     &lt;enumeration value="CSC_SETUP_REQD"/&gt;
 *     &lt;enumeration value="CSTM_FIELD_KEY_REQD"/&gt;
 *     &lt;enumeration value="CSTM_FIELD_VALUE_REQD"/&gt;
 *     &lt;enumeration value="CSV_DELIMITER_ERROR"/&gt;
 *     &lt;enumeration value="CUST_ARLEADY_HAS_ACCT"/&gt;
 *     &lt;enumeration value="CUST_CNTR_USER_ACCESS_ONLY"/&gt;
 *     &lt;enumeration value="CUST_LEAD_NOT_GROUP_MEMBR"/&gt;
 *     &lt;enumeration value="CYBERSOURCE_ERROR"/&gt;
 *     &lt;enumeration value="CYCLE_IN_PROJECT_PLAN"/&gt;
 *     &lt;enumeration value="DASHBOARD_LOCKED"/&gt;
 *     &lt;enumeration value="DATA_MUST_BE_UNIQUE"/&gt;
 *     &lt;enumeration value="DATA_REQD"/&gt;
 *     &lt;enumeration value="DATE_EXPECTED"/&gt;
 *     &lt;enumeration value="DATE_PARAM_REQD"/&gt;
 *     &lt;enumeration value="DATE_PRD_MISMATCH"/&gt;
 *     &lt;enumeration value="DEFAULT_CUR_REQD"/&gt;
 *     &lt;enumeration value="DEFAULT_EXPENSE_ACCT_REQD"/&gt;
 *     &lt;enumeration value="DEFAULT_ISSUE_OWNER_REQD"/&gt;
 *     &lt;enumeration value="DEFAULT_LIAB_ACCT_REQD"/&gt;
 *     &lt;enumeration value="DEFAULT_ROLE_REQD"/&gt;
 *     &lt;enumeration value="DEFAULT_TYPE_DELETE_DISALLWD"/&gt;
 *     &lt;enumeration value="DEFERRAL_ACCT_REQD"/&gt;
 *     &lt;enumeration value="DEFERRED_REV_REC_ACCT_REQD"/&gt;
 *     &lt;enumeration value="DEPT_IN_USE"/&gt;
 *     &lt;enumeration value="DFRNT_SWAP_PRICE_LEVELS_REQD"/&gt;
 *     &lt;enumeration value="DISALLWD_IP_ADDRESS"/&gt;
 *     &lt;enumeration value="DISCOUNT_ACCT_SETUP_REQD"/&gt;
 *     &lt;enumeration value="DISCOUNT_DISALLWD"/&gt;
 *     &lt;enumeration value="DISCOUNT_DISALLWD_VSOE"/&gt;
 *     &lt;enumeration value="DISCOUNT_EXCEED_TOTAL"/&gt;
 *     &lt;enumeration value="DISTRIB_REQD_ONE_DAY_BFORE"/&gt;
 *     &lt;enumeration value="DOMAIN_IN_USE"/&gt;
 *     &lt;enumeration value="DOMAIN_WEBSITE_REQD"/&gt;
 *     &lt;enumeration value="DROP_SHIP_ERROR"/&gt;
 *     &lt;enumeration value="DROP_SHIP_OR_SPECIAL_ORD_ALLWD"/&gt;
 *     &lt;enumeration value="DUE_DATE_BFORE_START_DATE"/&gt;
 *     &lt;enumeration value="DUE_DATE_REQD"/&gt;
 *     &lt;enumeration value="DUPLICATE_INVENTORY_NUM"/&gt;
 *     &lt;enumeration value="DUPLICATE_KEYS"/&gt;
 *     &lt;enumeration value="DUPLICATE_NAME_FOR_PRD"/&gt;
 *     &lt;enumeration value="DUPLICATE_NAME_FOR_ROLE"/&gt;
 *     &lt;enumeration value="DUP_ACCT_NAME"/&gt;
 *     &lt;enumeration value="DUP_ACCT_NOT_ALLWD"/&gt;
 *     &lt;enumeration value="DUP_ACCT_NUM"/&gt;
 *     &lt;enumeration value="DUP_ACCT_ON_TRANS"/&gt;
 *     &lt;enumeration value="DUP_BIN"/&gt;
 *     &lt;enumeration value="DUP_BUNDLE_IN_ACCT"/&gt;
 *     &lt;enumeration value="DUP_CATEGORY"/&gt;
 *     &lt;enumeration value="DUP_CATEGORY_NAME"/&gt;
 *     &lt;enumeration value="DUP_COLOR_THEME"/&gt;
 *     &lt;enumeration value="DUP_CSTM_FIELD"/&gt;
 *     &lt;enumeration value="DUP_CSTM_LAYOUT"/&gt;
 *     &lt;enumeration value="DUP_CSTM_LIST"/&gt;
 *     &lt;enumeration value="DUP_CSTM_RCRD"/&gt;
 *     &lt;enumeration value="DUP_CSTM_RCRD_ENTRY"/&gt;
 *     &lt;enumeration value="DUP_CSTM_TAB"/&gt;
 *     &lt;enumeration value="DUP_EMPL_EMAIL"/&gt;
 *     &lt;enumeration value="DUP_EMPL_ENTITY_NAME"/&gt;
 *     &lt;enumeration value="DUP_EMPL_TMPLT"/&gt;
 *     &lt;enumeration value="DUP_ENTITY"/&gt;
 *     &lt;enumeration value="DUP_ENTITY_EMAIL"/&gt;
 *     &lt;enumeration value="DUP_ENTITY_NAME"/&gt;
 *     &lt;enumeration value="DUP_FEDEX_ACCT_NUM"/&gt;
 *     &lt;enumeration value="DUP_FINANCL_STATMNT_LAYOUT"/&gt;
 *     &lt;enumeration value="DUP_INFO_ITEM"/&gt;
 *     &lt;enumeration value="DUP_ISSUE_NAME_OR_NUM"/&gt;
 *     &lt;enumeration value="DUP_ITEM"/&gt;
 *     &lt;enumeration value="DUP_ITEM_LAYOUT"/&gt;
 *     &lt;enumeration value="DUP_ITEM_NAME"/&gt;
 *     &lt;enumeration value="DUP_ITEM_OPTION"/&gt;
 *     &lt;enumeration value="DUP_ITEM_TMPLT"/&gt;
 *     &lt;enumeration value="DUP_MATRIX_OPTN_ABBRV"/&gt;
 *     &lt;enumeration value="DUP_MEMRZD_TRANS"/&gt;
 *     &lt;enumeration value="DUP_NAME"/&gt;
 *     &lt;enumeration value="DUP_PAYROLL_ITEM"/&gt;
 *     &lt;enumeration value="DUP_PRESNTN_CAT"/&gt;
 *     &lt;enumeration value="DUP_RCRD"/&gt;
 *     &lt;enumeration value="DUP_RCRD_LINK"/&gt;
 *     &lt;enumeration value="DUP_SALES_TAX_ITEM"/&gt;
 *     &lt;enumeration value="DUP_SHIPPING_ITEM"/&gt;
 *     &lt;enumeration value="DUP_SHORT_NAME"/&gt;
 *     &lt;enumeration value="DUP_SITE_THEME"/&gt;
 *     &lt;enumeration value="DUP_SOURCE_ACCT"/&gt;
 *     &lt;enumeration value="DUP_TAX_CODE"/&gt;
 *     &lt;enumeration value="DUP_TRACKING_NUM"/&gt;
 *     &lt;enumeration value="DUP_TRANS"/&gt;
 *     &lt;enumeration value="DUP_UPS_ACCT_NUM"/&gt;
 *     &lt;enumeration value="DUP_VENDOR_EMAIL"/&gt;
 *     &lt;enumeration value="DUP_VENDOR_NAME"/&gt;
 *     &lt;enumeration value="EARNING_ITEM_REQD"/&gt;
 *     &lt;enumeration value="EBAY_FEE_ERROR"/&gt;
 *     &lt;enumeration value="EBAY_TMPLT_ERROR"/&gt;
 *     &lt;enumeration value="EDITION_DSNT_SUPRT_WORLDPAY"/&gt;
 *     &lt;enumeration value="EIN_OR_TIN_REQD"/&gt;
 *     &lt;enumeration value="EMAIL_ADDRS_REQD"/&gt;
 *     &lt;enumeration value="EMAIL_ADDRS_REQD_TO_NOTIFY"/&gt;
 *     &lt;enumeration value="EMAIL_REQD"/&gt;
 *     &lt;enumeration value="EMAIL_REQD_ACCT_PROVISION"/&gt;
 *     &lt;enumeration value="EMAIL_REQ_HANDLER_ERROR"/&gt;
 *     &lt;enumeration value="EMPL_IN_USE"/&gt;
 *     &lt;enumeration value="ERROR_DELETE_CARD_DATA"/&gt;
 *     &lt;enumeration value="ERROR_IN_TERRITORY_ASSGNMNT"/&gt;
 *     &lt;enumeration value="ERROR_PRCSSNG_TRANS"/&gt;
 *     &lt;enumeration value="ERROR_REFUND_TRANS"/&gt;
 *     &lt;enumeration value="ERROR_REVERSE_AUTH"/&gt;
 *     &lt;enumeration value="ERROR_SENDING_TRAN_EMAIL"/&gt;
 *     &lt;enumeration value="ERROR_VOID_TRANS"/&gt;
 *     &lt;enumeration value="EVENT_ID_NOT_FOUND"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_ALLWD_LOC"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_CONCUR_RQST"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_EMAILS"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_FEATURED_ITEMS"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_FIELD_LENGTH"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_MATRIX_OPTNS"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_PDF_ELEMENTS"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_PDF_EXPORT_COL"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_PIN_RETRIES"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_RCRD"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_REPORT_COL"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_REPORT_ROWS"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_REPORT_SIZE"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_SESSIONS"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_SHIP_PACKAGE"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_TIME"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_TRANS_LINES"/&gt;
 *     &lt;enumeration value="EXCEEDED_MAX_USERS_ALLWD"/&gt;
 *     &lt;enumeration value="EXCEEDED_PER_TRANS_MAX"/&gt;
 *     &lt;enumeration value="EXCEEDED_RQST_SIZE_LIMIT"/&gt;
 *     &lt;enumeration value="EXCEEDS_ALLWD_LICENSES"/&gt;
 *     &lt;enumeration value="EXPENSE_ENTRY_DISALLWD"/&gt;
 *     &lt;enumeration value="EXPIRED_SEARCH_CRITERIA"/&gt;
 *     &lt;enumeration value="EXTERNALID_NOT_SUPPORTED"/&gt;
 *     &lt;enumeration value="EXTERNALID_REQD"/&gt;
 *     &lt;enumeration value="EXT_CAT_LINK_SETUP_REQD"/&gt;
 *     &lt;enumeration value="FAILED_FEDEX_LABEL_VOID"/&gt;
 *     &lt;enumeration value="FAILED_FORM_VALIDATION"/&gt;
 *     &lt;enumeration value="FAILED_UPS_LABEL_VOID"/&gt;
 *     &lt;enumeration value="FAX_NUM_REQD"/&gt;
 *     &lt;enumeration value="FAX_SETUP_REQD"/&gt;
 *     &lt;enumeration value="FEATURE_DISABLED"/&gt;
 *     &lt;enumeration value="FEATURE_UNAVAILABLE"/&gt;
 *     &lt;enumeration value="FEDEX_ACCT_REQD"/&gt;
 *     &lt;enumeration value="FEDEX_CANT_INTEGRATE_FULFILL"/&gt;
 *     &lt;enumeration value="FEDEX_DROPOFF_TYP_REQD"/&gt;
 *     &lt;enumeration value="FEDEX_INVALID_ACCT_NUM"/&gt;
 *     &lt;enumeration value="FEDEX_ITEM_CONTENTS_REQD"/&gt;
 *     &lt;enumeration value="FEDEX_METER_NOT_RETRIEVED"/&gt;
 *     &lt;enumeration value="FEDEX_METER_REQD"/&gt;
 *     &lt;enumeration value="FEDEX_ONE_PACKG_ALLWD"/&gt;
 *     &lt;enumeration value="FEDEX_ORIGIN_COUNTRY_US_REQD"/&gt;
 *     &lt;enumeration value="FEDEX_RATING_SRVC_UNAVAILBL"/&gt;
 *     &lt;enumeration value="FEDEX_REG_NOT_FOUND"/&gt;
 *     &lt;enumeration value="FEDEX_SHIP_SRVC_REQD"/&gt;
 *     &lt;enumeration value="FEDEX_SHIP_SRVC_UNAVAILBL"/&gt;
 *     &lt;enumeration value="FEDEX_UNSUPRTD_ORIGIN_COUNTRY"/&gt;
 *     &lt;enumeration value="FEDEX_USD_EXCHANGE_RATE_REQD"/&gt;
 *     &lt;enumeration value="FEDEX_USER_ERROR"/&gt;
 *     &lt;enumeration value="FEDEX_VOID_ERROR"/&gt;
 *     &lt;enumeration value="FED_ID_REQD"/&gt;
 *     &lt;enumeration value="FED_WITHHOLDING_REQD"/&gt;
 *     &lt;enumeration value="FIELD_CALL_DATE_REQD"/&gt;
 *     &lt;enumeration value="FIELD_DEFN_REQD"/&gt;
 *     &lt;enumeration value="FIELD_NOT_SETTABLE_ON_ADD"/&gt;
 *     &lt;enumeration value="FIELD_PARAM_REQD"/&gt;
 *     &lt;enumeration value="FIELD_REQD"/&gt;
 *     &lt;enumeration value="FILE_ALREADY_EXISTS"/&gt;
 *     &lt;enumeration value="FILE_DISALLWD_IN_ROOT_FLDR"/&gt;
 *     &lt;enumeration value="FILE_MISSING"/&gt;
 *     &lt;enumeration value="FILE_NOT_DOWNLOADABLE"/&gt;
 *     &lt;enumeration value="FILE_NOT_FOUND"/&gt;
 *     &lt;enumeration value="FILE_REQD"/&gt;
 *     &lt;enumeration value="FILE_UPLOAD_IN_PROGRESS"/&gt;
 *     &lt;enumeration value="FILTER_BY_AMT_REQD"/&gt;
 *     &lt;enumeration value="FINANCE_CHARGE_SETUP_REQD"/&gt;
 *     &lt;enumeration value="FINANCE_CHARGE_SET_PREFS"/&gt;
 *     &lt;enumeration value="FIRST_LAST_NAMES_REQD"/&gt;
 *     &lt;enumeration value="FIRST_QTY_BUCKET_MUST_BE_ZERO"/&gt;
 *     &lt;enumeration value="FLD_VALUE_REQD"/&gt;
 *     &lt;enumeration value="FLD_VALUE_TOO_LARGE"/&gt;
 *     &lt;enumeration value="FOLDER_ALREADY_EXISTS"/&gt;
 *     &lt;enumeration value="FORMULA_ERROR"/&gt;
 *     &lt;enumeration value="FORM_RESUBMISSION_REQD"/&gt;
 *     &lt;enumeration value="FORM_SETUP_REQD"/&gt;
 *     &lt;enumeration value="FORM_UNAVAILBL_ONLINE"/&gt;
 *     &lt;enumeration value="FRIENDLY_NAME_REQD"/&gt;
 *     &lt;enumeration value="FULFILL_REQD_FIELDS_MISSING"/&gt;
 *     &lt;enumeration value="FULFILL_REQD_PARAMS_MISSING"/&gt;
 *     &lt;enumeration value="FULL_DISTRIB_REQD"/&gt;
 *     &lt;enumeration value="FULL_USERS_REQD_TO_INTEGRATE"/&gt;
 *     &lt;enumeration value="FX_MALFORMED_RESPONSE"/&gt;
 *     &lt;enumeration value="FX_RATE_REQD_FEDEX_RATE"/&gt;
 *     &lt;enumeration value="FX_TRANS_DISALLWD"/&gt;
 *     &lt;enumeration value="GETALL_RCRD_TYPE_REQD"/&gt;
 *     &lt;enumeration value="GIFT_CERT_AMT_EXCEED_AVAILBL"/&gt;
 *     &lt;enumeration value="GIFT_CERT_AUTH_ALREADY_EXISTS"/&gt;
 *     &lt;enumeration value="GIFT_CERT_CAN_BE_USED_ONCE"/&gt;
 *     &lt;enumeration value="GIFT_CERT_CODE_REQD"/&gt;
 *     &lt;enumeration value="GIFT_CERT_INVALID_NUM"/&gt;
 *     &lt;enumeration value="GIFT_CERT_IN_USE"/&gt;
 *     &lt;enumeration value="GROUP_DSNT_EXIST"/&gt;
 *     &lt;enumeration value="GROUP_REQD"/&gt;
 *     &lt;enumeration value="GROUP_TYPE_REQD"/&gt;
 *     &lt;enumeration value="GRTR_QTY_PRICE_LEVEL_REQD"/&gt;
 *     &lt;enumeration value="IGNORE"/&gt;
 *     &lt;enumeration value="ILLEGAL_ID"/&gt;
 *     &lt;enumeration value="ILLEGAL_PERIOD_STRUCTURE"/&gt;
 *     &lt;enumeration value="INACTIVE_CC_PROFILE"/&gt;
 *     &lt;enumeration value="INACTIVE_RCRD_FOR_ROLE"/&gt;
 *     &lt;enumeration value="INAVLID_FILE_TYP"/&gt;
 *     &lt;enumeration value="INAVLID_ITEM_TYP"/&gt;
 *     &lt;enumeration value="INAVLID_PRICING_MTRX"/&gt;
 *     &lt;enumeration value="INCOMPATIBLE_ACCT_CHANGE"/&gt;
 *     &lt;enumeration value="INCOMPLETE_BILLING_ADDR"/&gt;
 *     &lt;enumeration value="INCOMPLETE_FILE_UPLOAD"/&gt;
 *     &lt;enumeration value="INCRCT_ORD_INFO"/&gt;
 *     &lt;enumeration value="INFINITE_LOOP_DETECTED"/&gt;
 *     &lt;enumeration value="INITIALIZE_ARG_REQD"/&gt;
 *     &lt;enumeration value="INITIALIZE_AUXREF_REQD"/&gt;
 *     &lt;enumeration value="INSTALL_SCRIPT_ERROR"/&gt;
 *     &lt;enumeration value="INSUFCNT_NUM_PRDS_FOR_REV_REC"/&gt;
 *     &lt;enumeration value="INSUFCNT_OPEN_PRDS_FOR_REV_REC"/&gt;
 *     &lt;enumeration value="INSUFFICIENT_CHARS_IN_SEARCH"/&gt;
 *     &lt;enumeration value="INSUFFICIENT_FLD_PERMISSION"/&gt;
 *     &lt;enumeration value="INSUFFICIENT_FUND"/&gt;
 *     &lt;enumeration value="INSUFFICIENT_PERMISSION"/&gt;
 *     &lt;enumeration value="INTEGER_REQD_FOR_QTY"/&gt;
 *     &lt;enumeration value="INTL_FEDEX_ONE_PACKG_ALLWD"/&gt;
 *     &lt;enumeration value="INTL_SHIP_EXCEED_MAX_ITEM"/&gt;
 *     &lt;enumeration value="INVALID_ABN"/&gt;
 *     &lt;enumeration value="INVALID_ACCT"/&gt;
 *     &lt;enumeration value="INVALID_ACCT_NUM_CSTM_FIELD"/&gt;
 *     &lt;enumeration value="INVALID_ACCT_PRD"/&gt;
 *     &lt;enumeration value="INVALID_ACCT_TYP"/&gt;
 *     &lt;enumeration value="INVALID_ACTION"/&gt;
 *     &lt;enumeration value="INVALID_ADDRESS_OR_SHIPPER_NO"/&gt;
 *     &lt;enumeration value="INVALID_ADJUSTMENT_ACCT"/&gt;
 *     &lt;enumeration value="INVALID_AES_FTSR_EXEMPTN_NUM"/&gt;
 *     &lt;enumeration value="INVALID_ALLOCTN_METHOD"/&gt;
 *     &lt;enumeration value="INVALID_AMORTZN_ACCT"/&gt;
 *     &lt;enumeration value="INVALID_AMT"/&gt;
 *     &lt;enumeration value="INVALID_APP_ID"/&gt;
 *     &lt;enumeration value="INVALID_ASSIGN_STATUS_COMBO"/&gt;
 *     &lt;enumeration value="INVALID_AUTH"/&gt;
 *     &lt;enumeration value="INVALID_AUTH_CODE"/&gt;
 *     &lt;enumeration value="INVALID_AUTOAPPLY_VALUE"/&gt;
 *     &lt;enumeration value="INVALID_AVS_ADDR"/&gt;
 *     &lt;enumeration value="INVALID_AVS_ZIP"/&gt;
 *     &lt;enumeration value="INVALID_BALANCE_RANGE"/&gt;
 *     &lt;enumeration value="INVALID_BILLING_SCHDUL"/&gt;
 *     &lt;enumeration value="INVALID_BILLING_SCHDUL_DATE"/&gt;
 *     &lt;enumeration value="INVALID_BILLING_SCHDUL_ENTRY"/&gt;
 *     &lt;enumeration value="INVALID_BIN_NUM"/&gt;
 *     &lt;enumeration value="INVALID_BOM_QTY"/&gt;
 *     &lt;enumeration value="INVALID_BOOLEAN_VALUE"/&gt;
 *     &lt;enumeration value="INVALID_BUG_NUM"/&gt;
 *     &lt;enumeration value="INVALID_CAMPAIGN_CHANNEL"/&gt;
 *     &lt;enumeration value="INVALID_CAMPAIGN_GROUP_SIZE"/&gt;
 *     &lt;enumeration value="INVALID_CAMPAIGN_STATUS"/&gt;
 *     &lt;enumeration value="INVALID_CARD"/&gt;
 *     &lt;enumeration value="INVALID_CARD_ID"/&gt;
 *     &lt;enumeration value="INVALID_CARD_NUM"/&gt;
 *     &lt;enumeration value="INVALID_CARD_TYP"/&gt;
 *     &lt;enumeration value="INVALID_CASE_FORM"/&gt;
 *     &lt;enumeration value="INVALID_CATGRY_TAX_AGENCY_REQ"/&gt;
 *     &lt;enumeration value="INVALID_CC_EMAIL_ADDRESS"/&gt;
 *     &lt;enumeration value="INVALID_CC_NUM"/&gt;
 *     &lt;enumeration value="INVALID_CERT"/&gt;
 *     &lt;enumeration value="INVALID_CERT_AUTH"/&gt;
 *     &lt;enumeration value="INVALID_CHANGE_LIST"/&gt;
 *     &lt;enumeration value="INVALID_CHARGE_AMT"/&gt;
 *     &lt;enumeration value="INVALID_CHARS_IN_EMAIL"/&gt;
 *     &lt;enumeration value="INVALID_CHARS_IN_NAME"/&gt;
 *     &lt;enumeration value="INVALID_CHARS_IN_PARAM_FIELD"/&gt;
 *     &lt;enumeration value="INVALID_CHARS_IN_URL"/&gt;
 *     &lt;enumeration value="INVALID_CHECKOUT_EMAIL"/&gt;
 *     &lt;enumeration value="INVALID_CITY"/&gt;
 *     &lt;enumeration value="INVALID_COLUMN_NAME"/&gt;
 *     &lt;enumeration value="INVALID_COLUMN_VALUE"/&gt;
 *     &lt;enumeration value="INVALID_CONTENT_TYPE"/&gt;
 *     &lt;enumeration value="INVALID_COSTING_METHOD"/&gt;
 *     &lt;enumeration value="INVALID_CRNCY_EXCH_RATE"/&gt;
 *     &lt;enumeration value="INVALID_CRYPT_KEY"/&gt;
 *     &lt;enumeration value="INVALID_CSTM_FIELD_DATA_TYP"/&gt;
 *     &lt;enumeration value="INVALID_CSTM_FIELD_RCRD_TYP"/&gt;
 *     &lt;enumeration value="INVALID_CSTM_FIELD_REF"/&gt;
 *     &lt;enumeration value="INVALID_CSTM_FORM"/&gt;
 *     &lt;enumeration value="INVALID_CSTM_RCRD_KEY"/&gt;
 *     &lt;enumeration value="INVALID_CSTM_RCRD_QUERY"/&gt;
 *     &lt;enumeration value="INVALID_CSTM_RCRD_TYPE_KEY"/&gt;
 *     &lt;enumeration value="INVALID_CSTM_RCRD_TYP_KEY"/&gt;
 *     &lt;enumeration value="INVALID_CUR"/&gt;
 *     &lt;enumeration value="INVALID_CURRENCY_CODE"/&gt;
 *     &lt;enumeration value="INVALID_CURRENCY_TYP"/&gt;
 *     &lt;enumeration value="INVALID_CURR_CODE"/&gt;
 *     &lt;enumeration value="INVALID_CUSTOMER_RCRD"/&gt;
 *     &lt;enumeration value="INVALID_DATA"/&gt;
 *     &lt;enumeration value="INVALID_DATA_FORMAT"/&gt;
 *     &lt;enumeration value="INVALID_DATE"/&gt;
 *     &lt;enumeration value="INVALID_DATE_FORMAT"/&gt;
 *     &lt;enumeration value="INVALID_DATE_RANGE"/&gt;
 *     &lt;enumeration value="INVALID_DATE_TIME"/&gt;
 *     &lt;enumeration value="INVALID_DEAL_RANGE"/&gt;
 *     &lt;enumeration value="INVALID_DELETE_REF"/&gt;
 *     &lt;enumeration value="INVALID_DESTINATION_FLDR"/&gt;
 *     &lt;enumeration value="INVALID_DESTNTN_COUNTRY"/&gt;
 *     &lt;enumeration value="INVALID_DESTNTN_POST_CODE"/&gt;
 *     &lt;enumeration value="INVALID_DESTNTN_STATE"/&gt;
 *     &lt;enumeration value="INVALID_DETACH_RECORD_TYP"/&gt;
 *     &lt;enumeration value="INVALID_DISCOUNT_MARKUP"/&gt;
 *     &lt;enumeration value="INVALID_DOMAIN_KEY"/&gt;
 *     &lt;enumeration value="INVALID_DOMAIN_NAME"/&gt;
 *     &lt;enumeration value="INVALID_DUP_ISSUE_REF"/&gt;
 *     &lt;enumeration value="INVALID_EMAIL"/&gt;
 *     &lt;enumeration value="INVALID_EMAIL_ADDR"/&gt;
 *     &lt;enumeration value="INVALID_END_DATE"/&gt;
 *     &lt;enumeration value="INVALID_END_TIME"/&gt;
 *     &lt;enumeration value="INVALID_ENTITY_INTERNALID"/&gt;
 *     &lt;enumeration value="INVALID_ENTITY_STATUS"/&gt;
 *     &lt;enumeration value="INVALID_EVENT_TIME"/&gt;
 *     &lt;enumeration value="INVALID_EXPNS_ACCT_SUB"/&gt;
 *     &lt;enumeration value="INVALID_EXPRESSION"/&gt;
 *     &lt;enumeration value="INVALID_EXP_DATE"/&gt;
 *     &lt;enumeration value="INVALID_FAX_NUM"/&gt;
 *     &lt;enumeration value="INVALID_FAX_PHONE_FORMAT"/&gt;
 *     &lt;enumeration value="INVALID_FIELD_FOR_RCRD_TYP"/&gt;
 *     &lt;enumeration value="INVALID_FIELD_NAME_FOR_NULL"/&gt;
 *     &lt;enumeration value="INVALID_FILE"/&gt;
 *     &lt;enumeration value="INVALID_FILE_ENCODING"/&gt;
 *     &lt;enumeration value="INVALID_FILE_TYP"/&gt;
 *     &lt;enumeration value="INVALID_FLD"/&gt;
 *     &lt;enumeration value="INVALID_FLDR_SIZE"/&gt;
 *     &lt;enumeration value="INVALID_FLD_RANGE"/&gt;
 *     &lt;enumeration value="INVALID_FLD_TYP"/&gt;
 *     &lt;enumeration value="INVALID_FLD_VALUE"/&gt;
 *     &lt;enumeration value="INVALID_FORMAT_IN_PARAM_FIELD"/&gt;
 *     &lt;enumeration value="INVALID_FORMULA"/&gt;
 *     &lt;enumeration value="INVALID_FORMULA_FIELD"/&gt;
 *     &lt;enumeration value="INVALID_FROM_DATE"/&gt;
 *     &lt;enumeration value="INVALID_FROM_TIME"/&gt;
 *     &lt;enumeration value="INVALID_FULFILMNT_ITEM"/&gt;
 *     &lt;enumeration value="INVALID_FX_BASE_CURRENCY"/&gt;
 *     &lt;enumeration value="INVALID_FX_RATE"/&gt;
 *     &lt;enumeration value="INVALID_GET_REF"/&gt;
 *     &lt;enumeration value="INVALID_GIFT_CERT"/&gt;
 *     &lt;enumeration value="INVALID_GIFT_CERT_AMT"/&gt;
 *     &lt;enumeration value="INVALID_GIFT_CERT_CODE"/&gt;
 *     &lt;enumeration value="INVALID_GOOGLE_CHECKOUT_ACCT"/&gt;
 *     &lt;enumeration value="INVALID_GROUP_TYP"/&gt;
 *     &lt;enumeration value="INVALID_GROUP_TYPE"/&gt;
 *     &lt;enumeration value="INVALID_GRP"/&gt;
 *     &lt;enumeration value="INVALID_GST_PST_AGENCIES"/&gt;
 *     &lt;enumeration value="INVALID_ID"/&gt;
 *     &lt;enumeration value="INVALID_INITIALIZE_ARG"/&gt;
 *     &lt;enumeration value="INVALID_INITIALIZE_AUXREF"/&gt;
 *     &lt;enumeration value="INVALID_INITIALIZE_REF"/&gt;
 *     &lt;enumeration value="INVALID_INSURED_VALUE"/&gt;
 *     &lt;enumeration value="INVALID_INTERNALID"/&gt;
 *     &lt;enumeration value="INVALID_INVENTORY_NUM"/&gt;
 *     &lt;enumeration value="INVALID_INV_DATE"/&gt;
 *     &lt;enumeration value="INVALID_IP_ADDRESS_RULE"/&gt;
 *     &lt;enumeration value="INVALID_ISSUE_BUILD_VERSION"/&gt;
 *     &lt;enumeration value="INVALID_ISSUE_PRIORITY"/&gt;
 *     &lt;enumeration value="INVALID_ISSUE_PRODUCT"/&gt;
 *     &lt;enumeration value="INVALID_ISSUE_STATUS"/&gt;
 *     &lt;enumeration value="INVALID_ITEM_OPTION"/&gt;
 *     &lt;enumeration value="INVALID_ITEM_OPTIONS"/&gt;
 *     &lt;enumeration value="INVALID_ITEM_SUBTYP"/&gt;
 *     &lt;enumeration value="INVALID_ITEM_TYP"/&gt;
 *     &lt;enumeration value="INVALID_ITEM_WEIGHT"/&gt;
 *     &lt;enumeration value="INVALID_JOB_ID"/&gt;
 *     &lt;enumeration value="INVALID_KEY_OR_REF"/&gt;
 *     &lt;enumeration value="INVALID_KEY_PASSWORD_REQD"/&gt;
 *     &lt;enumeration value="INVALID_LINE_ID"/&gt;
 *     &lt;enumeration value="INVALID_LINK_SUM"/&gt;
 *     &lt;enumeration value="INVALID_LIST_ID"/&gt;
 *     &lt;enumeration value="INVALID_LIST_KEY"/&gt;
 *     &lt;enumeration value="INVALID_LOC"/&gt;
 *     &lt;enumeration value="INVALID_LOC_SUB_RESTRICTN"/&gt;
 *     &lt;enumeration value="INVALID_LOGIN"/&gt;
 *     &lt;enumeration value="INVALID_LOGIN_ATTEMPT"/&gt;
 *     &lt;enumeration value="INVALID_LOGIN_CREDENTIALS"/&gt;
 *     &lt;enumeration value="INVALID_LOGIN_IP"/&gt;
 *     &lt;enumeration value="INVALID_LOT_NUM_FORMAT"/&gt;
 *     &lt;enumeration value="INVALID_MARKUP_DISCOUNT"/&gt;
 *     &lt;enumeration value="INVALID_MCC"/&gt;
 *     &lt;enumeration value="INVALID_MEMBER_HIERARCHY"/&gt;
 *     &lt;enumeration value="INVALID_MEMRZD_TRANS"/&gt;
 *     &lt;enumeration value="INVALID_MERCHANT_KEY"/&gt;
 *     &lt;enumeration value="INVALID_MERCHANT_NAME"/&gt;
 *     &lt;enumeration value="INVALID_NAME"/&gt;
 *     &lt;enumeration value="INVALID_NEXUS"/&gt;
 *     &lt;enumeration value="INVALID_NUM"/&gt;
 *     &lt;enumeration value="INVALID_NUMBER"/&gt;
 *     &lt;enumeration value="INVALID_OBJ"/&gt;
 *     &lt;enumeration value="INVALID_ONLINE_FORM"/&gt;
 *     &lt;enumeration value="INVALID_ONLINE_FORM_URL"/&gt;
 *     &lt;enumeration value="INVALID_OPENID_DOMAIN"/&gt;
 *     &lt;enumeration value="INVALID_OPERATION"/&gt;
 *     &lt;enumeration value="INVALID_ORD_STATUS"/&gt;
 *     &lt;enumeration value="INVALID_ORIGIN_COUNTRY"/&gt;
 *     &lt;enumeration value="INVALID_ORIGIN_POSTCODE"/&gt;
 *     &lt;enumeration value="INVALID_ORIGIN_STATE"/&gt;
 *     &lt;enumeration value="INVALID_PAGER_NUM"/&gt;
 *     &lt;enumeration value="INVALID_PAGE_INDEX"/&gt;
 *     &lt;enumeration value="INVALID_PAGE_PARAM"/&gt;
 *     &lt;enumeration value="INVALID_PARAM"/&gt;
 *     &lt;enumeration value="INVALID_PARENT"/&gt;
 *     &lt;enumeration value="INVALID_PARTNER_CODE"/&gt;
 *     &lt;enumeration value="INVALID_PARTNER_ID"/&gt;
 *     &lt;enumeration value="INVALID_PASSWORD"/&gt;
 *     &lt;enumeration value="INVALID_PAYCHECK_DATE"/&gt;
 *     &lt;enumeration value="INVALID_PERIOD"/&gt;
 *     &lt;enumeration value="INVALID_PHONE"/&gt;
 *     &lt;enumeration value="INVALID_PHONE_FAX_PAGER_NUM"/&gt;
 *     &lt;enumeration value="INVALID_PHONE_NUM"/&gt;
 *     &lt;enumeration value="INVALID_PHONE_NUMBER"/&gt;
 *     &lt;enumeration value="INVALID_PICKUP_POSTAL_CODE"/&gt;
 *     &lt;enumeration value="INVALID_PIN"/&gt;
 *     &lt;enumeration value="INVALID_PIN_DEBIT_TRANS_TYP"/&gt;
 *     &lt;enumeration value="INVALID_PORTLET_TYP"/&gt;
 *     &lt;enumeration value="INVALID_POST"/&gt;
 *     &lt;enumeration value="INVALID_PRESENTATION_TYP"/&gt;
 *     &lt;enumeration value="INVALID_PROBABILITY_RANGE"/&gt;
 *     &lt;enumeration value="INVALID_PROFILE_ID"/&gt;
 *     &lt;enumeration value="INVALID_PROJ_BILLING_TYP"/&gt;
 *     &lt;enumeration value="INVALID_PST_TAX_VALUE"/&gt;
 *     &lt;enumeration value="INVALID_PSWD"/&gt;
 *     &lt;enumeration value="INVALID_PSWD_HINT"/&gt;
 *     &lt;enumeration value="INVALID_PSWD_ILLEGAL_CHAR"/&gt;
 *     &lt;enumeration value="INVALID_PURCHASE_TAX_CODE"/&gt;
 *     &lt;enumeration value="INVALID_QTY"/&gt;
 *     &lt;enumeration value="INVALID_QUANTITY"/&gt;
 *     &lt;enumeration value="INVALID_QUESTION"/&gt;
 *     &lt;enumeration value="INVALID_RCRD"/&gt;
 *     &lt;enumeration value="INVALID_RCRD_CONVERSION"/&gt;
 *     &lt;enumeration value="INVALID_RCRD_HEADER_"/&gt;
 *     &lt;enumeration value="INVALID_RCRD_ID"/&gt;
 *     &lt;enumeration value="INVALID_RCRD_INITIALIZE"/&gt;
 *     &lt;enumeration value="INVALID_RCRD_OBJ"/&gt;
 *     &lt;enumeration value="INVALID_RCRD_REF"/&gt;
 *     &lt;enumeration value="INVALID_RCRD_TRANSFRM"/&gt;
 *     &lt;enumeration value="INVALID_RCRD_TYPE"/&gt;
 *     &lt;enumeration value="INVALID_RECIPIENT"/&gt;
 *     &lt;enumeration value="INVALID_RECR_REF"/&gt;
 *     &lt;enumeration value="INVALID_RECUR_DATE_RANGE"/&gt;
 *     &lt;enumeration value="INVALID_RECUR_DESC_REQD"/&gt;
 *     &lt;enumeration value="INVALID_RECUR_DOW"/&gt;
 *     &lt;enumeration value="INVALID_RECUR_DOWIM"/&gt;
 *     &lt;enumeration value="INVALID_RECUR_DOWMASK"/&gt;
 *     &lt;enumeration value="INVALID_RECUR_FREQUENCY"/&gt;
 *     &lt;enumeration value="INVALID_RECUR_PATTERN"/&gt;
 *     &lt;enumeration value="INVALID_RECUR_PERIOD"/&gt;
 *     &lt;enumeration value="INVALID_RECUR_TIME_ZONE_REQD"/&gt;
 *     &lt;enumeration value="INVALID_REFFERER_EMAIL"/&gt;
 *     &lt;enumeration value="INVALID_REFUND_AMT"/&gt;
 *     &lt;enumeration value="INVALID_REF_CANT_INITIALIZE"/&gt;
 *     &lt;enumeration value="INVALID_REF_KEY"/&gt;
 *     &lt;enumeration value="INVALID_REPORT"/&gt;
 *     &lt;enumeration value="INVALID_REPORT_ID"/&gt;
 *     &lt;enumeration value="INVALID_REPORT_ROW"/&gt;
 *     &lt;enumeration value="INVALID_REQUEST"/&gt;
 *     &lt;enumeration value="INVALID_RESOURCE_TIME"/&gt;
 *     &lt;enumeration value="INVALID_RESULT_SUMMARY_FUNC"/&gt;
 *     &lt;enumeration value="INVALID_RETURN_DATA_OBJECT"/&gt;
 *     &lt;enumeration value="INVALID_REV_REC_DATE_RANGE"/&gt;
 *     &lt;enumeration value="INVALID_ROLE"/&gt;
 *     &lt;enumeration value="INVALID_ROLE_FOR_EVENT"/&gt;
 *     &lt;enumeration value="INVALID_RQST_CONTACTS_EXIST"/&gt;
 *     &lt;enumeration value="INVALID_RQST_PARENT_REQD"/&gt;
 *     &lt;enumeration value="INVALID_RQST_SBCUST_JOBS_EXIST"/&gt;
 *     &lt;enumeration value="INVALID_SAVEDSEARCH"/&gt;
 *     &lt;enumeration value="INVALID_SAVED_SRCH"/&gt;
 *     &lt;enumeration value="INVALID_SCHDUL_AMT"/&gt;
 *     &lt;enumeration value="INVALID_SCHDUL_FORMAT"/&gt;
 *     &lt;enumeration value="INVALID_SCRIPT_ID"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_CRITERIA"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_FIELD_KEY"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_FIELD_NAME"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_FIELD_OBJ"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_JOIN_ID"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_MORE"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_OPERATOR"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_PAGE_INDEX"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_PAGE_SIZE"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_PREF"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_SELECT_OBJ"/&gt;
 *     &lt;enumeration value="INVALID_SEARCH_VALUE"/&gt;
 *     &lt;enumeration value="INVALID_SECONDARY_EMAIL"/&gt;
 *     &lt;enumeration value="INVALID_SECPAY_CREDENTIALS"/&gt;
 *     &lt;enumeration value="INVALID_SERIAL_NUM"/&gt;
 *     &lt;enumeration value="INVALID_SERIAL_OR_LOT_NUMBER"/&gt;
 *     &lt;enumeration value="INVALID_SERVICE_CODE"/&gt;
 *     &lt;enumeration value="INVALID_SESSION"/&gt;
 *     &lt;enumeration value="INVALID_SHIPPER_STATE"/&gt;
 *     &lt;enumeration value="INVALID_SHIP_DATE"/&gt;
 *     &lt;enumeration value="INVALID_SHIP_FROM_STATE"/&gt;
 *     &lt;enumeration value="INVALID_SHIP_GRP"/&gt;
 *     &lt;enumeration value="INVALID_SHIP_SRVC"/&gt;
 *     &lt;enumeration value="INVALID_SHIP_TO_SATE"/&gt;
 *     &lt;enumeration value="INVALID_SITE_CSTM_FILE"/&gt;
 *     &lt;enumeration value="INVALID_SOAP_HEADER"/&gt;
 *     &lt;enumeration value="INVALID_SRCH"/&gt;
 *     &lt;enumeration value="INVALID_SRCH_CRITERIA"/&gt;
 *     &lt;enumeration value="INVALID_SRCH_CSTM_FLD"/&gt;
 *     &lt;enumeration value="INVALID_SRCH_FUNCTN"/&gt;
 *     &lt;enumeration value="INVALID_SRCH_SORT"/&gt;
 *     &lt;enumeration value="INVALID_SRCH_SUMMARY_TYP"/&gt;
 *     &lt;enumeration value="INVALID_SRCH_TYP"/&gt;
 *     &lt;enumeration value="INVALID_SRVC_ITEM_SUB"/&gt;
 *     &lt;enumeration value="INVALID_SSO"/&gt;
 *     &lt;enumeration value="INVALID_SSS_DEBUG_SESSION"/&gt;
 *     &lt;enumeration value="INVALID_STATE"/&gt;
 *     &lt;enumeration value="INVALID_STATUS"/&gt;
 *     &lt;enumeration value="INVALID_SUB"/&gt;
 *     &lt;enumeration value="INVALID_SUBLIST_DESC"/&gt;
 *     &lt;enumeration value="INVALID_SUBSCRIPTION_STATUS"/&gt;
 *     &lt;enumeration value="INVALID_SUMMARY_SRCH"/&gt;
 *     &lt;enumeration value="INVALID_SUPERVISOR"/&gt;
 *     &lt;enumeration value="INVALID_TASK_ID"/&gt;
 *     &lt;enumeration value="INVALID_TAX_AMT"/&gt;
 *     &lt;enumeration value="INVALID_TAX_CODE"/&gt;
 *     &lt;enumeration value="INVALID_TAX_CODES"/&gt;
 *     &lt;enumeration value="INVALID_TAX_CODE_FOR_SUB"/&gt;
 *     &lt;enumeration value="INVALID_TAX_PMT"/&gt;
 *     &lt;enumeration value="INVALID_TAX_VALUE"/&gt;
 *     &lt;enumeration value="INVALID_TIME_FORMAT"/&gt;
 *     &lt;enumeration value="INVALID_TO_DATE"/&gt;
 *     &lt;enumeration value="INVALID_TRACKING_NUM"/&gt;
 *     &lt;enumeration value="INVALID_TRANS"/&gt;
 *     &lt;enumeration value="INVALID_TRANSACTION_DATE"/&gt;
 *     &lt;enumeration value="INVALID_TRANSACTIO_DATE"/&gt;
 *     &lt;enumeration value="INVALID_TRANS_AMT"/&gt;
 *     &lt;enumeration value="INVALID_TRANS_COMPNT"/&gt;
 *     &lt;enumeration value="INVALID_TRANS_ID"/&gt;
 *     &lt;enumeration value="INVALID_TRANS_SUB_ACCT"/&gt;
 *     &lt;enumeration value="INVALID_TRANS_SUB_CLASS"/&gt;
 *     &lt;enumeration value="INVALID_TRANS_SUB_DEPT"/&gt;
 *     &lt;enumeration value="INVALID_TRANS_SUB_ENTITY"/&gt;
 *     &lt;enumeration value="INVALID_TRANS_SUB_ITEM"/&gt;
 *     &lt;enumeration value="INVALID_TRANS_SUB_LOC"/&gt;
 *     &lt;enumeration value="INVALID_TRANS_TYP"/&gt;
 *     &lt;enumeration value="INVALID_TRAN_ITEM_LINE"/&gt;
 *     &lt;enumeration value="INVALID_TRIAL_TYP"/&gt;
 *     &lt;enumeration value="INVALID_TYP"/&gt;
 *     &lt;enumeration value="INVALID_UNIT_TYP"/&gt;
 *     &lt;enumeration value="INVALID_UNSUPRTD_RCRD_TYP"/&gt;
 *     &lt;enumeration value="INVALID_UPS_ACCT"/&gt;
 *     &lt;enumeration value="INVALID_UPS_PACKG_WEIGHT"/&gt;
 *     &lt;enumeration value="INVALID_UPS_VALUES"/&gt;
 *     &lt;enumeration value="INVALID_URL"/&gt;
 *     &lt;enumeration value="INVALID_URL_PARAM"/&gt;
 *     &lt;enumeration value="INVALID_VALUE"/&gt;
 *     &lt;enumeration value="INVALID_VAT_AMOUNT"/&gt;
 *     &lt;enumeration value="INVALID_VAT_REGSTRTN_NUM"/&gt;
 *     &lt;enumeration value="INVALID_VSOE_ALLOCTN"/&gt;
 *     &lt;enumeration value="INVALID_WEBSITE_SECTION"/&gt;
 *     &lt;enumeration value="INVALID_WO"/&gt;
 *     &lt;enumeration value="INVALID_WORLDPAY_ID"/&gt;
 *     &lt;enumeration value="INVALID_WO_ITEM"/&gt;
 *     &lt;enumeration value="INVALID_WS_VERSION"/&gt;
 *     &lt;enumeration value="INVALID_YEAR"/&gt;
 *     &lt;enumeration value="INVALID_YEAR_FORMAT"/&gt;
 *     &lt;enumeration value="INVALID_ZIP_CODE"/&gt;
 *     &lt;enumeration value="INVALID_ZIP_FILE"/&gt;
 *     &lt;enumeration value="INVALID_ZIP_POST_CODE"/&gt;
 *     &lt;enumeration value="INVENTORY_NUM_DISALLWD"/&gt;
 *     &lt;enumeration value="INVLAID_BOOLEAN_VALUE"/&gt;
 *     &lt;enumeration value="IP_REQUEST"/&gt;
 *     &lt;enumeration value="ISSUE_ASSIGNEE_DISALLWD"/&gt;
 *     &lt;enumeration value="ISSUE_PRODUCT_VERSION_MISMATCH"/&gt;
 *     &lt;enumeration value="ISSUE_VERSION_BUILD_MISMATCH"/&gt;
 *     &lt;enumeration value="ITEM_ACCT_REQD"/&gt;
 *     &lt;enumeration value="ITEM_COUNT_MISMATCH"/&gt;
 *     &lt;enumeration value="ITEM_IS_UNAVAILABLE"/&gt;
 *     &lt;enumeration value="ITEM_NAME_MUST_BE_UNIQUE"/&gt;
 *     &lt;enumeration value="ITEM_NOT_UNIQUE"/&gt;
 *     &lt;enumeration value="ITEM_PARAM_REQD_IN_URL"/&gt;
 *     &lt;enumeration value="ITEM_QTY_AMT_MISMATCH"/&gt;
 *     &lt;enumeration value="ITEM_TYP_REQS_UNIT"/&gt;
 *     &lt;enumeration value="JE_AMOUNTS_MUST_BALANCE"/&gt;
 *     &lt;enumeration value="JE_LINE_MISSING_REQD_DATA"/&gt;
 *     &lt;enumeration value="JE_MAX_ONE_LINE"/&gt;
 *     &lt;enumeration value="JE_REV_REC_IN_PROGRESS"/&gt;
 *     &lt;enumeration value="JE_UNEXPECTED_ERROR"/&gt;
 *     &lt;enumeration value="JOB_NOT_COMPLETE"/&gt;
 *     &lt;enumeration value="JS_EXCEPTION"/&gt;
 *     &lt;enumeration value="KEY_REQD"/&gt;
 *     &lt;enumeration value="KPI_SETUP_REQD"/&gt;
 *     &lt;enumeration value="LABEL_REQD"/&gt;
 *     &lt;enumeration value="LANGUAGE_SETUP_REQD"/&gt;
 *     &lt;enumeration value="LINKED_ACCT_DONT_MATCH"/&gt;
 *     &lt;enumeration value="LINKED_ENTITIES_DONT_MATCH"/&gt;
 *     &lt;enumeration value="LINKED_ITEMS_DONT_MATCH"/&gt;
 *     &lt;enumeration value="LINK_LINES_TO_ONE_ORD"/&gt;
 *     &lt;enumeration value="LIST_ID_REQD"/&gt;
 *     &lt;enumeration value="LIST_KEY_REQD"/&gt;
 *     &lt;enumeration value="LOCATIONS_IN_USE"/&gt;
 *     &lt;enumeration value="LOCATIONS_SETUP_REQD"/&gt;
 *     &lt;enumeration value="LOCATION_REQD"/&gt;
 *     &lt;enumeration value="LOCKED_DASHBOARD"/&gt;
 *     &lt;enumeration value="LOGIN_DISABLED"/&gt;
 *     &lt;enumeration value="LOGIN_DISABLED_PARTNER_CTR"/&gt;
 *     &lt;enumeration value="LOGIN_EMAIL_REQD"/&gt;
 *     &lt;enumeration value="LOGIN_NAME_AND_PSWD_REQD"/&gt;
 *     &lt;enumeration value="LOGIN_REQD"/&gt;
 *     &lt;enumeration value="LOST_UPSELL_CRITERIA"/&gt;
 *     &lt;enumeration value="MACHN_LIST_KEY_NAMES_REQD"/&gt;
 *     &lt;enumeration value="MANDATORY_PRD_TYPE_REQD"/&gt;
 *     &lt;enumeration value="MASS_UPDATE_CRITERIA_LOST"/&gt;
 *     &lt;enumeration value="MATCHING_CUR_SUB_REQD"/&gt;
 *     &lt;enumeration value="MATCHING_SERIAL_NUM_REQD"/&gt;
 *     &lt;enumeration value="MATRIX_INFO_TEMP_LOST"/&gt;
 *     &lt;enumeration value="MATRIX_SUBITEM_NAME_TOO_LONG"/&gt;
 *     &lt;enumeration value="MAX_16_LINES_ALLWD_PER_BILLPAY"/&gt;
 *     &lt;enumeration value="MAX_200_LINES_ALLWD_ON_TRANS"/&gt;
 *     &lt;enumeration value="MAX_BARCODE_PRINT_EXCEEDED"/&gt;
 *     &lt;enumeration value="MAX_BULK_MERGE_RCRDS_EXCEEDED"/&gt;
 *     &lt;enumeration value="MAX_EMAILS_EXCEEDED"/&gt;
 *     &lt;enumeration value="MAX_RCRDS_EXCEEDED"/&gt;
 *     &lt;enumeration value="MAX_VALUES_EXCEEDED"/&gt;
 *     &lt;enumeration value="MEDIA_FILE_INVALID_JSCRIPT"/&gt;
 *     &lt;enumeration value="MEDIA_NOT_FOUND"/&gt;
 *     &lt;enumeration value="MEDIA_NOT_INITIALIZED"/&gt;
 *     &lt;enumeration value="MEMORIZED_TRANS_ERROR"/&gt;
 *     &lt;enumeration value="MERGE_OPERATION_DISALLWD"/&gt;
 *     &lt;enumeration value="MERGE_RCRD_REQD"/&gt;
 *     &lt;enumeration value="METAVANTE_ERROR"/&gt;
 *     &lt;enumeration value="METAVANTE_SECRET_ANSWER_REQD"/&gt;
 *     &lt;enumeration value="METAVANTE_SECRET_QESTION_REQD"/&gt;
 *     &lt;enumeration value="METAVANTE_SETUP_REQD"/&gt;
 *     &lt;enumeration value="METAVANTE_TEMP_UNAVAILBL"/&gt;
 *     &lt;enumeration value="MISMATCHED_CURRENCY"/&gt;
 *     &lt;enumeration value="MISMATCHED_QTY_PRICING"/&gt;
 *     &lt;enumeration value="MISMATCHED_SEARCH_PARENTHESIS"/&gt;
 *     &lt;enumeration value="MISMATCH_EVENT_ISSUE_STATUS"/&gt;
 *     &lt;enumeration value="MISMATCH_ISSUE_PRODUCT_VERSION"/&gt;
 *     &lt;enumeration value="MISMATCH_SALES_CONTRIBUTION"/&gt;
 *     &lt;enumeration value="MISSING_ACCT_PRD"/&gt;
 *     &lt;enumeration value="MISSING_CRNCY_EXCH_RATE"/&gt;
 *     &lt;enumeration value="MISSING_ENUM"/&gt;
 *     &lt;enumeration value="MISSING_REQD_FLD"/&gt;
 *     &lt;enumeration value="MISSNG_ACCT_PRD"/&gt;
 *     &lt;enumeration value="MISSNG_REV_REC_RCRD"/&gt;
 *     &lt;enumeration value="MISSNG_SO_REV_REC_PARAMS"/&gt;
 *     &lt;enumeration value="MISSNG_SO_START_END_DATES"/&gt;
 *     &lt;enumeration value="MLI_REQD"/&gt;
 *     &lt;enumeration value="MLTPLE_TAX_LINES_DISALLWD"/&gt;
 *     &lt;enumeration value="MSNG_FIELD_OWRTE_MUST_BE_TRUE"/&gt;
 *     &lt;enumeration value="MST_UPDATE_ITEMS_THEN_RATES"/&gt;
 *     &lt;enumeration value="MULTISELECT_TYPE_REQD"/&gt;
 *     &lt;enumeration value="MULTI_ACCT_CANT_CHANGE_PSWD"/&gt;
 *     &lt;enumeration value="MULTI_LOC_INVT_ERROR"/&gt;
 *     &lt;enumeration value="MULTI_PRIMARY_PARTNER_DISALLWD"/&gt;
 *     &lt;enumeration value="MULTI_SHIP_ROUTES_REQD"/&gt;
 *     &lt;enumeration value="MUST_DEFINE_BASE_UNIT"/&gt;
 *     &lt;enumeration value="MUST_RESUBMIT_RCRD"/&gt;
 *     &lt;enumeration value="NAME_ALREADY_IN_USE"/&gt;
 *     &lt;enumeration value="NAME_REQD"/&gt;
 *     &lt;enumeration value="NAME_TYPE_FLDR_FIELDS_REQD"/&gt;
 *     &lt;enumeration value="NARROW_KEYWORD_SEARCH"/&gt;
 *     &lt;enumeration value="NEED_BILL_VARIANCE_ACCT"/&gt;
 *     &lt;enumeration value="NEGATIVE_PAYMENT_DISALLWD"/&gt;
 *     &lt;enumeration value="NEGATIVE_TAX_RATE_DISALLWD"/&gt;
 *     &lt;enumeration value="NEW_CONNECTION_DISALLWD"/&gt;
 *     &lt;enumeration value="NEXUS_REQD"/&gt;
 *     &lt;enumeration value="NONMATCHING_EMAILS"/&gt;
 *     &lt;enumeration value="NONUNIQUE_INDEX_VALUE"/&gt;
 *     &lt;enumeration value="NONZERO_AMT_REQD"/&gt;
 *     &lt;enumeration value="NONZERO_QTY_REQD"/&gt;
 *     &lt;enumeration value="NONZERO_WEIGHT_REQD"/&gt;
 *     &lt;enumeration value="NON_ADMIN_CANNOT_INITIATE_LINK"/&gt;
 *     &lt;enumeration value="NOT_AN_INVITEE"/&gt;
 *     &lt;enumeration value="NOT_IN_INVT"/&gt;
 *     &lt;enumeration value="NO_DATA_FOUND"/&gt;
 *     &lt;enumeration value="NO_EXPENSES_FOR_PRD"/&gt;
 *     &lt;enumeration value="NO_ITEMS_TO_PRINT"/&gt;
 *     &lt;enumeration value="NO_MASS_UPDATES_RUNNING"/&gt;
 *     &lt;enumeration value="NO_MTRX_ITEMS_TO_UPDATE"/&gt;
 *     &lt;enumeration value="NO_ORD_SHPMNT"/&gt;
 *     &lt;enumeration value="NO_RCRDS_MATCH"/&gt;
 *     &lt;enumeration value="NO_RCRD_FOR_USER"/&gt;
 *     &lt;enumeration value="NO_SCHDUL_APPLIED"/&gt;
 *     &lt;enumeration value="NO_UPSERT"/&gt;
 *     &lt;enumeration value="NULL_CHECK_NUMBER"/&gt;
 *     &lt;enumeration value="NUMERIC_CHECK_NUM_REQD"/&gt;
 *     &lt;enumeration value="NUMERIC_REF_NUM_REQD"/&gt;
 *     &lt;enumeration value="NUM_ITEMS_GRTR_THAN_QTY"/&gt;
 *     &lt;enumeration value="NUM_ITEMS_NOT_EQUAL_TO_QTY"/&gt;
 *     &lt;enumeration value="NUM_REQD_FOR_FIRST_LABEL"/&gt;
 *     &lt;enumeration value="OI_FEATURE_REQD"/&gt;
 *     &lt;enumeration value="OI_PERMISSION_REQD"/&gt;
 *     &lt;enumeration value="ONE_ADMIN_REQD_PER_ACCT"/&gt;
 *     &lt;enumeration value="ONE_DIRECT_DEPOSIT_ACT_ALLWD"/&gt;
 *     &lt;enumeration value="ONE_EMPL_REQD"/&gt;
 *     &lt;enumeration value="ONE_PAY_ITEM_PER_EMPL"/&gt;
 *     &lt;enumeration value="ONE_POSITIVE_VALUE_REQD"/&gt;
 *     &lt;enumeration value="ONE_RCRD_REQD_FOR_MASS_UPDATE"/&gt;
 *     &lt;enumeration value="ONE_ROLE_REQD"/&gt;
 *     &lt;enumeration value="ONLINE_BANK_FILE_REQD"/&gt;
 *     &lt;enumeration value="ONLINE_BILL_PAY_DUP"/&gt;
 *     &lt;enumeration value="ONLINE_BILL_PAY_SETUP_REQD"/&gt;
 *     &lt;enumeration value="ONLINE_FORM_DSNT_EXIST"/&gt;
 *     &lt;enumeration value="ONLINE_FORM_EMPTY"/&gt;
 *     &lt;enumeration value="ONLINE_FORM_ID_REQD"/&gt;
 *     &lt;enumeration value="ONLINE_FORM_USER_ACCESS_ONLY"/&gt;
 *     &lt;enumeration value="ONLINE_ORD_FEATURE_DISABLED"/&gt;
 *     &lt;enumeration value="ONLY_ONE_CONTRIB_ITEM_REQD"/&gt;
 *     &lt;enumeration value="ONLY_ONE_DEDCT_ITEM_REQD"/&gt;
 *     &lt;enumeration value="ONLY_ONE_DISTRIB_ALLWD"/&gt;
 *     &lt;enumeration value="ONLY_ONE_EARNING_ITEM_REQD"/&gt;
 *     &lt;enumeration value="ONLY_ONE_LOT_NUM_ALLWD"/&gt;
 *     &lt;enumeration value="ONLY_ONE_PREF_BIN_ALLWD"/&gt;
 *     &lt;enumeration value="ONLY_ONE_UNIT_AS_BASE_UNIT"/&gt;
 *     &lt;enumeration value="ONLY_ONE_UPLOAD_ALLWD"/&gt;
 *     &lt;enumeration value="ONLY_ONE_WITHLD_ITEM_REQD"/&gt;
 *     &lt;enumeration value="OPENID_DOMAIN_IN_USE"/&gt;
 *     &lt;enumeration value="OPENID_NOT_ENABLED"/&gt;
 *     &lt;enumeration value="OPRTN_UNAVAILBL_TO_GATEWAY"/&gt;
 *     &lt;enumeration value="ORDER_DSNT_EXIST"/&gt;
 *     &lt;enumeration value="ORD_ALREADY_APPRVD"/&gt;
 *     &lt;enumeration value="ORD_UNAVAILBL_TO_FULFILL"/&gt;
 *     &lt;enumeration value="OTHER_PMT_AUTH_IN_PROGRESS"/&gt;
 *     &lt;enumeration value="OVERAGE_DISALLWD"/&gt;
 *     &lt;enumeration value="OVERLAPPING_PRDS_DISALLWD"/&gt;
 *     &lt;enumeration value="OVER_FULFILL_DISALLWD"/&gt;
 *     &lt;enumeration value="OVER_FULFILL_RECEIV_DISALLWD"/&gt;
 *     &lt;enumeration value="OWNER_REQD"/&gt;
 *     &lt;enumeration value="PACKAGE_WEIGHT_REQD"/&gt;
 *     &lt;enumeration value="PACKG_LEVEL_REF_DISALLWD"/&gt;
 *     &lt;enumeration value="PACKG_VALUE_TOO_LARGE"/&gt;
 *     &lt;enumeration value="PARENT_CANT_ITSELF_BE_MEMBER"/&gt;
 *     &lt;enumeration value="PARENT_MUST_BE_MATRIX_ITEM"/&gt;
 *     &lt;enumeration value="PARENT_REQD"/&gt;
 *     &lt;enumeration value="PARSING_ERROR"/&gt;
 *     &lt;enumeration value="PARTIAL_FULFILL_RCEIV_DISALLWD"/&gt;
 *     &lt;enumeration value="PARTNER_ACCESS_DENIED"/&gt;
 *     &lt;enumeration value="PARTNER_ACCT_NOT_LINKED"/&gt;
 *     &lt;enumeration value="PARTNER_CODE_ALREADY_USED"/&gt;
 *     &lt;enumeration value="PAYCHECK_ALREADY_PAID"/&gt;
 *     &lt;enumeration value="PAYCHECK_IN_USE"/&gt;
 *     &lt;enumeration value="PAYEE_REQD_FOR_PMT"/&gt;
 *     &lt;enumeration value="PAYPAL_FUND_SOURCE_REQD"/&gt;
 *     &lt;enumeration value="PAYPAL_INVALID_PMT_METHOD"/&gt;
 *     &lt;enumeration value="PAYPAL_PMT_NOTIFICATION"/&gt;
 *     &lt;enumeration value="PAYPAL_SETUP_REQD"/&gt;
 *     &lt;enumeration value="PAYROLL_COMMITTED"/&gt;
 *     &lt;enumeration value="PAYROLL_EXPENSE_ACCT_REQD"/&gt;
 *     &lt;enumeration value="PAYROLL_FEATURE_DISABLED"/&gt;
 *     &lt;enumeration value="PAYROLL_FEATURE_UNAVAILABLE"/&gt;
 *     &lt;enumeration value="PAYROLL_FUND_ACCT_REQD"/&gt;
 *     &lt;enumeration value="PAYROLL_IN_PROCESS"/&gt;
 *     &lt;enumeration value="PAYROLL_ITEM_DELETE_DISALLWD"/&gt;
 *     &lt;enumeration value="PAYROLL_LIABILITY_ACCT_REQD"/&gt;
 *     &lt;enumeration value="PAYROLL_MAINTENANCE"/&gt;
 *     &lt;enumeration value="PAYROLL_SETUP_REQD"/&gt;
 *     &lt;enumeration value="PAYROLL_UPDATE_REQD"/&gt;
 *     &lt;enumeration value="PAY_HOLD_ON_SO"/&gt;
 *     &lt;enumeration value="PERMISSION_VIOLATION"/&gt;
 *     &lt;enumeration value="PHONE_NUM_REQD"/&gt;
 *     &lt;enumeration value="PIN_DEBIT_TRANS_DISALLWD"/&gt;
 *     &lt;enumeration value="PLAN_IN_USE"/&gt;
 *     &lt;enumeration value="PLAN_OVERLAP_DISALLWD"/&gt;
 *     &lt;enumeration value="PMT_ALREADY_APPRVD"/&gt;
 *     &lt;enumeration value="PMT_ALREADY_EXISTS"/&gt;
 *     &lt;enumeration value="PMT_ALREADY_SBMTD"/&gt;
 *     &lt;enumeration value="PMT_EDIT_DISALLWD"/&gt;
 *     &lt;enumeration value="POSITIVE_BIN_QTY_REQD"/&gt;
 *     &lt;enumeration value="POSITIVE_QTY_REQD"/&gt;
 *     &lt;enumeration value="POSITIVE_UNITCOST_REQD"/&gt;
 *     &lt;enumeration value="POSTING_DISCOUNT_DISALLWD"/&gt;
 *     &lt;enumeration value="POSTING_PRD_SETUP_REQD"/&gt;
 *     &lt;enumeration value="PRDS_DISALLWD_NAMES_NOT_UNIQUE"/&gt;
 *     &lt;enumeration value="PRD_SETUP_REQD"/&gt;
 *     &lt;enumeration value="PREFERRED_TAX_AGENCY_REQD"/&gt;
 *     &lt;enumeration value="PREF_VENDOR_COST_REQD"/&gt;
 *     &lt;enumeration value="PREF_VENDOR_REQD"/&gt;
 *     &lt;enumeration value="PRIVATE_RCRD_ACCESS_DISALLWD"/&gt;
 *     &lt;enumeration value="PRIVATE_STATUS_CHNG_DISALLWD"/&gt;
 *     &lt;enumeration value="PRODUCT_MODULE_MISMATCH"/&gt;
 *     &lt;enumeration value="PSWD_EXPIRED"/&gt;
 *     &lt;enumeration value="PSWD_REQD"/&gt;
 *     &lt;enumeration value="PWSDS_DONT_MATCH"/&gt;
 *     &lt;enumeration value="QTY_EXCEEDED_QTY_BUCKETS"/&gt;
 *     &lt;enumeration value="QTY_REQD"/&gt;
 *     &lt;enumeration value="RATE_REQUEST_SHPMNT_REQD"/&gt;
 *     &lt;enumeration value="RATE_SRVC_UNAVAILBL"/&gt;
 *     &lt;enumeration value="RCRD_DELETED_SINCE_RETRIEVED"/&gt;
 *     &lt;enumeration value="RCRD_DSNT_EXIST"/&gt;
 *     &lt;enumeration value="RCRD_EDITED_SINCE_RETRIEVED"/&gt;
 *     &lt;enumeration value="RCRD_HAS_BEEN_CHANGED"/&gt;
 *     &lt;enumeration value="RCRD_ID_NOT_INT"/&gt;
 *     &lt;enumeration value="RCRD_LOCKED_BY_WF"/&gt;
 *     &lt;enumeration value="RCRD_NOT_FOUND"/&gt;
 *     &lt;enumeration value="RCRD_PREVSLY_DELETED"/&gt;
 *     &lt;enumeration value="RCRD_REF_RCRD_TYP_MISMATCH"/&gt;
 *     &lt;enumeration value="RCRD_SUB_MISMATCH_WITH_CLASS"/&gt;
 *     &lt;enumeration value="RCRD_TYPE_REQD"/&gt;
 *     &lt;enumeration value="RCRD_UNEDITABLE"/&gt;
 *     &lt;enumeration value="REACHED_LIST_END"/&gt;
 *     &lt;enumeration value="REACHED_LIST_START"/&gt;
 *     &lt;enumeration value="RECALCING_PLAN_SCHDUL"/&gt;
 *     &lt;enumeration value="RECURSV_REF_DISALLWD"/&gt;
 *     &lt;enumeration value="RECUR_EVENT_DISALLWD"/&gt;
 *     &lt;enumeration value="REC_TYP_REQD"/&gt;
 *     &lt;enumeration value="REPORT_EXPIRED"/&gt;
 *     &lt;enumeration value="REQD_FORM_TAG_MISSING"/&gt;
 *     &lt;enumeration value="REQD_LOC_FIELDS_MISSING"/&gt;
 *     &lt;enumeration value="REQD_SUB_FIELDS_MISSING"/&gt;
 *     &lt;enumeration value="REQUEST_PARAM_REQD"/&gt;
 *     &lt;enumeration value="REVERSAL_DATE_WARNING"/&gt;
 *     &lt;enumeration value="REV_REC_DATE_REQD"/&gt;
 *     &lt;enumeration value="REV_REC_TMPLT_DATA_MISSING"/&gt;
 *     &lt;enumeration value="REV_REC_UPDATE_DISALLWD"/&gt;
 *     &lt;enumeration value="ROLE_REQD"/&gt;
 *     &lt;enumeration value="ROLE_REQUIRED"/&gt;
 *     &lt;enumeration value="ROUNDING_DIFF_TOO_BIG"/&gt;
 *     &lt;enumeration value="ROUNDING_ERROR"/&gt;
 *     &lt;enumeration value="ROUTING_NUM_REQD"/&gt;
 *     &lt;enumeration value="SALES_DISCOUNT_ACCT_REQD"/&gt;
 *     &lt;enumeration value="SAME_ACCT_TYP_REQD_FOR_PARENT"/&gt;
 *     &lt;enumeration value="SAVED_SRCH_EMAIL_ERROR"/&gt;
 *     &lt;enumeration value="SCHDUL_EDIT_DISALLWD"/&gt;
 *     &lt;enumeration value="SCHEDULED_REPORT_ERROR"/&gt;
 *     &lt;enumeration value="SEARCH_DATE_FILTER_REQD"/&gt;
 *     &lt;enumeration value="SEARCH_ERROR"/&gt;
 *     &lt;enumeration value="SEARCH_INTEGER_REQD"/&gt;
 *     &lt;enumeration value="SEARCH_TIMED_OUT"/&gt;
 *     &lt;enumeration value="SECURE_TRANS_REQD_ON_CHECKOUT"/&gt;
 *     &lt;enumeration value="SERIAL_NUM_MATCH_MULTI_ITEMS"/&gt;
 *     &lt;enumeration value="SESSION_TERMD_2ND_LOGIN_DECTD"/&gt;
 *     &lt;enumeration value="SESSION_TIMED_OUT"/&gt;
 *     &lt;enumeration value="SETUP_METER_REQD"/&gt;
 *     &lt;enumeration value="SET_SHIPPING_PICKUP_TYP"/&gt;
 *     &lt;enumeration value="SHIPFROM_ADDRESS_NOT_SET"/&gt;
 *     &lt;enumeration value="SHIPMNT_INSURANCE_NOT_AVAILABLE"/&gt;
 *     &lt;enumeration value="SHIP_ADDR_REQD"/&gt;
 *     &lt;enumeration value="SHIP_MANIFEST_ALREADY_PRCSSD"/&gt;
 *     &lt;enumeration value="SHIP_MANIFEST_ERROR"/&gt;
 *     &lt;enumeration value="SHIP_SETUP_REQD"/&gt;
 *     &lt;enumeration value="SHIP_TALBE_UNBALNCD"/&gt;
 *     &lt;enumeration value="SHIP_USER_ERROR"/&gt;
 *     &lt;enumeration value="SINGLE_VALUE_REQD"/&gt;
 *     &lt;enumeration value="SITEMAP_GEN_ERROR"/&gt;
 *     &lt;enumeration value="SITE_DOMAIN_NAME_REQD"/&gt;
 *     &lt;enumeration value="SITE_TAG_ALREADY_EXISTS"/&gt;
 *     &lt;enumeration value="SO_HAS_CHILD_TRANS"/&gt;
 *     &lt;enumeration value="SO_LINE_HAS_PO"/&gt;
 *     &lt;enumeration value="SRVC_UNAVAILBL_FOR_LOC"/&gt;
 *     &lt;enumeration value="SSS_AUTHOR_MUST_BE_EMPLOYEE"/&gt;
 *     &lt;enumeration value="SSS_CONNECTION_TIME_OUT"/&gt;
 *     &lt;enumeration value="SSS_DEBUG_DISALLWD"/&gt;
 *     &lt;enumeration value="SSS_DRIP_EMAIL_RAN_OUT_OF_COUPON_CODES"/&gt;
 *     &lt;enumeration value="SSS_DUP_DRIP_EMAIL"/&gt;
 *     &lt;enumeration value="SSS_FILE_SIZE_EXCEEDED"/&gt;
 *     &lt;enumeration value="SSS_INSTRUCTION_COUNT_EXCEEDED"/&gt;
 *     &lt;enumeration value="SSS_INVALID_ATTACH_RECORD_TYPE"/&gt;
 *     &lt;enumeration value="SSS_INVALID_BCC_EMAIL"/&gt;
 *     &lt;enumeration value="SSS_INVALID_CC_EMAIL"/&gt;
 *     &lt;enumeration value="SSS_INVALID_CMPGN_EVENT_ID"/&gt;
 *     &lt;enumeration value="SSS_INVALID_EMAIL_TEMPLATE"/&gt;
 *     &lt;enumeration value="SSS_INVALID_FORM_ELEMENT_NAME"/&gt;
 *     &lt;enumeration value="SSS_INVALID_GSO_FLTR_OPRTOR"/&gt;
 *     &lt;enumeration value="SSS_INVALID_HEADER"/&gt;
 *     &lt;enumeration value="SSS_INVALID_HOST_CERT"/&gt;
 *     &lt;enumeration value="SSS_INVALID_LIST_COLUMN_NAME"/&gt;
 *     &lt;enumeration value="SSS_INVALID_LOCK_WAIT_TIME"/&gt;
 *     &lt;enumeration value="SSS_INVALID_LOG_TYPE"/&gt;
 *     &lt;enumeration value="SSS_INVALID_PORTLET_INTERVAL"/&gt;
 *     &lt;enumeration value="SSS_INVALID_SCRIPTLET_ID"/&gt;
 *     &lt;enumeration value="SSS_INVALID_SRCH_COL"/&gt;
 *     &lt;enumeration value="SSS_INVALID_SRCH_COLUMN_JOIN"/&gt;
 *     &lt;enumeration value="SSS_INVALID_SRCH_COLUMN_SUM"/&gt;
 *     &lt;enumeration value="SSS_INVALID_SRCH_FILTER"/&gt;
 *     &lt;enumeration value="SSS_INVALID_SRCH_FILTER_JOIN"/&gt;
 *     &lt;enumeration value="SSS_INVALID_SRCH_OPERATOR"/&gt;
 *     &lt;enumeration value="SSS_INVALID_SUBLIST_OPERATION"/&gt;
 *     &lt;enumeration value="SSS_INVALID_SUBMIT_OPTION"/&gt;
 *     &lt;enumeration value="SSS_INVALID_TYPE_ARG"/&gt;
 *     &lt;enumeration value="SSS_INVALID_UI_OBJECT_TYPE"/&gt;
 *     &lt;enumeration value="SSS_INVALID_URL"/&gt;
 *     &lt;enumeration value="SSS_INVALID_URL_CATEGORY"/&gt;
 *     &lt;enumeration value="SSS_INVALID_WF_RCRD_TYPE"/&gt;
 *     &lt;enumeration value="SSS_MEMORY_USAGE_EXCEEDED"/&gt;
 *     &lt;enumeration value="SSS_MISSING_REQD_ARGUMENT"/&gt;
 *     &lt;enumeration value="SSS_QUEUE_LIMIT_EXCEEDED"/&gt;
 *     &lt;enumeration value="SSS_RECORD_TYPE_MISMATCH"/&gt;
 *     &lt;enumeration value="SSS_REQUEST_TIME_EXCEEDED"/&gt;
 *     &lt;enumeration value="SSS_SSO_CONFIG_REQD"/&gt;
 *     &lt;enumeration value="SSS_STACK_FRAME_DEPTH_EXCEEDED"/&gt;
 *     &lt;enumeration value="SSS_TIME_LIMIT_EXCEEDED"/&gt;
 *     &lt;enumeration value="SSS_TRANSACTION_REQD"/&gt;
 *     &lt;enumeration value="SSS_TRANS_IN_PROGRESS"/&gt;
 *     &lt;enumeration value="SSS_UNKNOWN_HOST"/&gt;
 *     &lt;enumeration value="SSS_USAGE_LIMIT_EXCEEDED"/&gt;
 *     &lt;enumeration value="START_DATE_AFTER_END_DATE"/&gt;
 *     &lt;enumeration value="START_DATE_REQD"/&gt;
 *     &lt;enumeration value="STATE_ALREADY_EXISTS"/&gt;
 *     &lt;enumeration value="STATE_REQD"/&gt;
 *     &lt;enumeration value="STATUS_ASSIGNEE_REQD"/&gt;
 *     &lt;enumeration value="STORAGE_LIMIT_EXCEEDED"/&gt;
 *     &lt;enumeration value="STORE_ALIAS_UNAVAILABLE"/&gt;
 *     &lt;enumeration value="STORE_DOMAIN_UNAVAILABLE"/&gt;
 *     &lt;enumeration value="SUBITEM_REQD"/&gt;
 *     &lt;enumeration value="SUBSIDIARY_MISMATCH"/&gt;
 *     &lt;enumeration value="SUB_MISMATCH"/&gt;
 *     &lt;enumeration value="SUB_RESTRICT_VIEW_REQD"/&gt;
 *     &lt;enumeration value="SUB_TAX_AGENCY_REQD"/&gt;
 *     &lt;enumeration value="SUCCESS_TRANS"/&gt;
 *     &lt;enumeration value="SUPRT_CNTR_LOGIN_ERROR"/&gt;
 *     &lt;enumeration value="TAGATA_ALREADY_ENDORSED"/&gt;
 *     &lt;enumeration value="TAG_ALREADY_EXISTS"/&gt;
 *     &lt;enumeration value="TAG_SUBSTITUTN_ERROR"/&gt;
 *     &lt;enumeration value="TAX_ACCT_SETUP_REQD"/&gt;
 *     &lt;enumeration value="TAX_CODES_SETUP_PROBLEM"/&gt;
 *     &lt;enumeration value="TAX_CODES_SETUP_REQD"/&gt;
 *     &lt;enumeration value="TAX_CODE_REQD"/&gt;
 *     &lt;enumeration value="TAX_GROUP_SETUP_REQD"/&gt;
 *     &lt;enumeration value="TAX_PRD_REQD"/&gt;
 *     &lt;enumeration value="TAX_SETUP_REQD"/&gt;
 *     &lt;enumeration value="TEMPLATE_NOT_FOUND"/&gt;
 *     &lt;enumeration value="THIRD_PARTY_BILLING_ACCT_REQD"/&gt;
 *     &lt;enumeration value="TICKET_NOT_LOCATED"/&gt;
 *     &lt;enumeration value="TIMEOUT_THE_RECORD_DOESNT_EXIST_ANYMORE"/&gt;
 *     &lt;enumeration value="TIME_ENTRY_DISALLWD"/&gt;
 *     &lt;enumeration value="TOPIC_REQD"/&gt;
 *     &lt;enumeration value="TRANSACTION_DELETED"/&gt;
 *     &lt;enumeration value="TRANSORD_SHIP_REC_MISMATCH"/&gt;
 *     &lt;enumeration value="TRANS_ALREADY_REFUNDED"/&gt;
 *     &lt;enumeration value="TRANS_ALREADY_SETTLED"/&gt;
 *     &lt;enumeration value="TRANS_ALREADY_VOIDED"/&gt;
 *     &lt;enumeration value="TRANS_AMTS_UNBALNCD"/&gt;
 *     &lt;enumeration value="TRANS_APPLIED_AMTS_UNBALNCD"/&gt;
 *     &lt;enumeration value="TRANS_CLASS_UNBALNCD"/&gt;
 *     &lt;enumeration value="TRANS_DEPT_UNBALNCD"/&gt;
 *     &lt;enumeration value="TRANS_DOES_NOT_EXIST"/&gt;
 *     &lt;enumeration value="TRANS_DSNT_EXIST"/&gt;
 *     &lt;enumeration value="TRANS_EDIT_DISALLWD"/&gt;
 *     &lt;enumeration value="TRANS_FORGN_CRNCY_MISMATCH"/&gt;
 *     &lt;enumeration value="TRANS_FORGN_CUR_UNBALNCD"/&gt;
 *     &lt;enumeration value="TRANS_IN_USE"/&gt;
 *     &lt;enumeration value="TRANS_LINES_UNBALNCD"/&gt;
 *     &lt;enumeration value="TRANS_LINE_AND_PMT_UNBALNCD"/&gt;
 *     &lt;enumeration value="TRANS_LOC_UNBALNCD"/&gt;
 *     &lt;enumeration value="TRANS_NOT_CLEANED"/&gt;
 *     &lt;enumeration value="TRANS_NOT_COMPLETED"/&gt;
 *     &lt;enumeration value="TRANS_PRCSSNG_ERROR"/&gt;
 *     &lt;enumeration value="TRANS_UNBALNCD"/&gt;
 *     &lt;enumeration value="TRAN_DATE_REQD"/&gt;
 *     &lt;enumeration value="TRAN_LINE_FX_AMT_REQD"/&gt;
 *     &lt;enumeration value="TRAN_LINK_FX_AMT_REQD"/&gt;
 *     &lt;enumeration value="TRAN_PERIOD_CLOSED"/&gt;
 *     &lt;enumeration value="TRAN_PRD_CLOSED"/&gt;
 *     &lt;enumeration value="TWO_FA_AUTH_REQD"/&gt;
 *     &lt;enumeration value="TWO_FA_REQD"/&gt;
 *     &lt;enumeration value="UNABLE_TO_PRINT_CHECKS"/&gt;
 *     &lt;enumeration value="UNABLE_TO_PRINT_DEPOSITS"/&gt;
 *     &lt;enumeration value="UNAUTH_CAMPAIGN_RSPNS_RQST"/&gt;
 *     &lt;enumeration value="UNAUTH_UNSUBSCRIBE_RQST"/&gt;
 *     &lt;enumeration value="UNDEFINED_ACCTNG_PRD"/&gt;
 *     &lt;enumeration value="UNDEFINED_CSTM_FIELD"/&gt;
 *     &lt;enumeration value="UNDEFINED_TAX_PRD"/&gt;
 *     &lt;enumeration value="UNEXPECTED_ERROR"/&gt;
 *     &lt;enumeration value="UNIQUE_CONTACT_NAME_REQD"/&gt;
 *     &lt;enumeration value="UNIQUE_CUST_EMAIL_REQD"/&gt;
 *     &lt;enumeration value="UNIQUE_CUST_ID_REQD"/&gt;
 *     &lt;enumeration value="UNIQUE_ENTITY_NAME_REQD"/&gt;
 *     &lt;enumeration value="UNIQUE_GROUPID_REQD"/&gt;
 *     &lt;enumeration value="UNIQUE_PARTNER_CODE_REQD"/&gt;
 *     &lt;enumeration value="UNIQUE_QTY_REQD"/&gt;
 *     &lt;enumeration value="UNIQUE_RCRD_ID_REQD"/&gt;
 *     &lt;enumeration value="UNIQUE_SOLUTION_CODE_REQD"/&gt;
 *     &lt;enumeration value="UNITS_TYP_IN_USE"/&gt;
 *     &lt;enumeration value="UNKNOWN_CARRIER"/&gt;
 *     &lt;enumeration value="UNKNOWN_RCRD_TYPE"/&gt;
 *     &lt;enumeration value="UNKNOWN_SCRIPT_TYP"/&gt;
 *     &lt;enumeration value="UNKNWN_ALLOCTN_SCHDUL_FREQ_TYP"/&gt;
 *     &lt;enumeration value="UNKNWN_EMAIL_AUTHOR"/&gt;
 *     &lt;enumeration value="UNKNWN_EXCHANGE_RATE"/&gt;
 *     &lt;enumeration value="UNRECOGNIZED_METHOD"/&gt;
 *     &lt;enumeration value="UNSUBSCRIBE_REQD"/&gt;
 *     &lt;enumeration value="UNSUPRTD_DOC_TYP"/&gt;
 *     &lt;enumeration value="UPDATE_DISALLWD"/&gt;
 *     &lt;enumeration value="UPDATE_PRICE_AMT_REQD"/&gt;
 *     &lt;enumeration value="UPGRADE_WS_VERSION"/&gt;
 *     &lt;enumeration value="UPS_CANT_INTEGRATE_FULFILL"/&gt;
 *     &lt;enumeration value="UPS_CONFIG_ERROR"/&gt;
 *     &lt;enumeration value="UPS_LICENSE_AGREEMNT_REQD"/&gt;
 *     &lt;enumeration value="UPS_ONLINE_RATE_UNAVAILBL"/&gt;
 *     &lt;enumeration value="UPS_ONLINE_SHIP_UNAVAILBL"/&gt;
 *     &lt;enumeration value="UPS_REG_NUM_IN_USE"/&gt;
 *     &lt;enumeration value="UPS_SETUP_REQD"/&gt;
 *     &lt;enumeration value="UPS_USER_ERROR"/&gt;
 *     &lt;enumeration value="UPS_VOID_ERROR"/&gt;
 *     &lt;enumeration value="UPS_XML_ERROR"/&gt;
 *     &lt;enumeration value="URL_ID_PARAM_REQD"/&gt;
 *     &lt;enumeration value="URL_REQD"/&gt;
 *     &lt;enumeration value="USER_CONTEXT_REQD"/&gt;
 *     &lt;enumeration value="USER_DISABLED"/&gt;
 *     &lt;enumeration value="USER_ERROR"/&gt;
 *     &lt;enumeration value="USPS_ACCT_NUM_ALREADY_EXISTS"/&gt;
 *     &lt;enumeration value="USPS_INVALID_INSURED_VALUE"/&gt;
 *     &lt;enumeration value="USPS_INVALID_PACKAGING"/&gt;
 *     &lt;enumeration value="USPS_INVALID_PSWD"/&gt;
 *     &lt;enumeration value="USPS_LABEL_VOIDED"/&gt;
 *     &lt;enumeration value="USPS_MAX_ITEM_EXCEEDED"/&gt;
 *     &lt;enumeration value="USPS_ONE_PACKAGE_ALLWD"/&gt;
 *     &lt;enumeration value="USPS_PASS_PHRASE_NOT_UPDATED"/&gt;
 *     &lt;enumeration value="USPS_REFUND_FAILED"/&gt;
 *     &lt;enumeration value="USPS_RETRY"/&gt;
 *     &lt;enumeration value="USPS_VALIDATE_ADDR"/&gt;
 *     &lt;enumeration value="USPS_VERIFY_TRACKING_NUM"/&gt;
 *     &lt;enumeration value="USPS_VOID_ERROR"/&gt;
 *     &lt;enumeration value="VALID_EMAIL_REQD"/&gt;
 *     &lt;enumeration value="VALID_EMAIL_REQD_FOR_LOGIN"/&gt;
 *     &lt;enumeration value="VALID_FIRST_NAME_REQD"/&gt;
 *     &lt;enumeration value="VALID_LAST_NAME_REQD"/&gt;
 *     &lt;enumeration value="VALID_LINE_ITEM_REQD"/&gt;
 *     &lt;enumeration value="VALID_PHONE_NUM_REQD"/&gt;
 *     &lt;enumeration value="VALID_PRD_REQD"/&gt;
 *     &lt;enumeration value="VALID_URL_REQD"/&gt;
 *     &lt;enumeration value="VALID_VERSION_REQD_IN_URL"/&gt;
 *     &lt;enumeration value="VALID_WORK_PHONE_REQD"/&gt;
 *     &lt;enumeration value="VALID_ZIPCODE_REQD"/&gt;
 *     &lt;enumeration value="VENDOR_TYPE_REQD"/&gt;
 *     &lt;enumeration value="VERIFY_DESTNTN_ZIP_CODE"/&gt;
 *     &lt;enumeration value="VERIFY_PAYROLL_FUND_ACCT"/&gt;
 *     &lt;enumeration value="VERIFY_ZIP_CODE_SETUP"/&gt;
 *     &lt;enumeration value="VISA_ERROR"/&gt;
 *     &lt;enumeration value="VOIDING_REVERSAL_DISALLWD"/&gt;
 *     &lt;enumeration value="VOID_FAILED"/&gt;
 *     &lt;enumeration value="VSOE_CANT_ADD_ITEM_GROUP"/&gt;
 *     &lt;enumeration value="VSOE_REV_REC_TMPLT_REQD"/&gt;
 *     &lt;enumeration value="VSOE_TOTAL_ALLOCATION_ERROR"/&gt;
 *     &lt;enumeration value="VSOE_TRAN_VSOE_BUNDLE_ERROR"/&gt;
 *     &lt;enumeration value="WARNING"/&gt;
 *     &lt;enumeration value="WF_EXEC_USAGE_LIMIT_EXCEEDED"/&gt;
 *     &lt;enumeration value="WORK_DAYS_REQD"/&gt;
 *     &lt;enumeration value="WORLDPAY_ERROR"/&gt;
 *     &lt;enumeration value="WRITE_OFF_ACCT_REQD"/&gt;
 *     &lt;enumeration value="WS_CONCUR_SESSION_DISALLWD"/&gt;
 *     &lt;enumeration value="WS_EXCEEDED_CONCUR_USERS_ALLWD"/&gt;
 *     &lt;enumeration value="WS_EXCEEDED_MAX_CONCUR_RQST"/&gt;
 *     &lt;enumeration value="WS_FEATURE_REQD"/&gt;
 *     &lt;enumeration value="WS_INVALID_SEARCH_OPERATN"/&gt;
 *     &lt;enumeration value="WS_LOG_IN_REQD"/&gt;
 *     &lt;enumeration value="WS_PERMISSION_REQD"/&gt;
 *     &lt;enumeration value="ZIP_FILE_CONTAINS_VIRUS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "StatusDetailCodeType", namespace = "urn:types.faults_2014_2.platform.webservices.netsuite.com")
@XmlEnum
public enum StatusDetailCodeType {

    ABORT_SEARCH_EXCEEDED_MAX_TIME("ABORT_SEARCH_EXCEEDED_MAX_TIME"),
    ABORT_UPLOAD_VIRUS_DETECTED("ABORT_UPLOAD_VIRUS_DETECTED"),
    ACCESS_DENIED("ACCESS_DENIED"),
    ACCTNG_PRD_REQD("ACCTNG_PRD_REQD"),
    ACCT_DISABLED("ACCT_DISABLED"),
    ACCT_MERGE_DUP("ACCT_MERGE_DUP"),
    ACCT_NAME_REQD("ACCT_NAME_REQD"),
    ACCT_NEEDS_CAMPAIGN_PROVISION("ACCT_NEEDS_CAMPAIGN_PROVISION"),
    ACCT_NOT_CREATED("ACCT_NOT_CREATED"),
    ACCT_NUMS_REQD_OR_DONT_MATCH("ACCT_NUMS_REQD_OR_DONT_MATCH"),
    ACCT_NUM_REQD("ACCT_NUM_REQD"),
    ACCT_PERIOD_SETUP_REQD("ACCT_PERIOD_SETUP_REQD"),
    ACCT_PRDS_BEING_ADDED("ACCT_PRDS_BEING_ADDED"),
    ACCT_REQD("ACCT_REQD"),
    ACCT_TEMP_DISABLED("ACCT_TEMP_DISABLED"),
    ACCT_TEMP_UNAVAILABLE("ACCT_TEMP_UNAVAILABLE"),
    ACH_NOT_AVAILBL("ACH_NOT_AVAILBL"),
    ACH_SETUP_REQD("ACH_SETUP_REQD"),
    ACTIVE_AP_ACCT_REQD("ACTIVE_AP_ACCT_REQD"),
    ACTIVE_ROLE_REQD("ACTIVE_ROLE_REQD"),
    ACTIVE_TRANS_EXIST("ACTIVE_TRANS_EXIST"),
    @XmlEnumValue("ADDITIONAL_AUTHENTICATION_REQUIRED_2FA")
    ADDITIONAL_AUTHENTICATION_REQUIRED_2_FA("ADDITIONAL_AUTHENTICATION_REQUIRED_2FA"),
    ADDITIONAL_AUTHENTICATION_REQUIRED_SQ("ADDITIONAL_AUTHENTICATION_REQUIRED_SQ"),
    ADDRESS_LINE_1_REQD("ADDRESS_LINE_1_REQD"),
    ADMIN_ACCESS_REQ("ADMIN_ACCESS_REQ"),
    ADMIN_ACCESS_REQD("ADMIN_ACCESS_REQD"),
    ADMIN_ONLY_ACCESS("ADMIN_ONLY_ACCESS"),
    ADMIN_USER_REQD("ADMIN_USER_REQD"),
    ADMISSIBILITY_PACKG_TYP_REQD("ADMISSIBILITY_PACKG_TYP_REQD"),
    ALL_DATA_DELETE_REQD("ALL_DATA_DELETE_REQD"),
    ALL_MTRX_SUBITMES_OPTNS_REQD("ALL_MTRX_SUBITMES_OPTNS_REQD"),
    ALREADY_IN_INVT("ALREADY_IN_INVT"),
    AMORTZN_INVALID_DATE_RANGE("AMORTZN_INVALID_DATE_RANGE"),
    AMORTZN_TMPLT_DATA_MISSING("AMORTZN_TMPLT_DATA_MISSING"),
    AMT_DISALLWD("AMT_DISALLWD"),
    AMT_EXCEEDS_APPROVAL_LIMIT("AMT_EXCEEDS_APPROVAL_LIMIT"),
    ANSWER_REQD("ANSWER_REQD"),
    APPROVAL_PERMS_REQD("APPROVAL_PERMS_REQD"),
    AREA_CODE_REQD("AREA_CODE_REQD"),
    ASSIGNEE_REQD("ASSIGNEE_REQD"),
    ATTACHMNT_CONTAINS_VIRUS("ATTACHMNT_CONTAINS_VIRUS"),
    ATTACH_SIZE_EXCEEDED("ATTACH_SIZE_EXCEEDED"),
    AT_LEAST_ONE_FILE_REQD("AT_LEAST_ONE_FILE_REQD"),
    AT_LEAST_ONE_PACKAGE_REQD("AT_LEAST_ONE_PACKAGE_REQD"),
    AT_LEAST_ONE_RETURN_FLD_REQD("AT_LEAST_ONE_RETURN_FLD_REQD"),
    AT_LEAST_ONE_SUB_REQD("AT_LEAST_ONE_SUB_REQD"),
    @XmlEnumValue("AUDIT_W2_1099")
    AUDIT_W_2_1099("AUDIT_W2_1099"),
    AUTO_NUM_UPDATE_DISALLWD("AUTO_NUM_UPDATE_DISALLWD"),
    AVS_ERROR("AVS_ERROR"),
    BALANCE_EXCEEDS_CREDIT_LIMIT("BALANCE_EXCEEDS_CREDIT_LIMIT"),
    BANK_ACCT_REQD("BANK_ACCT_REQD"),
    BASE_CRNCY_REQD("BASE_CRNCY_REQD"),
    BILLABLES_DISALLWD("BILLABLES_DISALLWD"),
    BILLING_ISSUES("BILLING_ISSUES"),
    BILLING_SCHDUL_INVALID_RECURR("BILLING_SCHDUL_INVALID_RECURR"),
    BILLPAY_APPROVAL_UNAVAILBL("BILLPAY_APPROVAL_UNAVAILBL"),
    BILLPAY_REGSTRTN_REQD("BILLPAY_REGSTRTN_REQD"),
    BILLPAY_SRVC_UNAVAILBL("BILLPAY_SRVC_UNAVAILBL"),
    BILL_PAY_STATUS_UNAVAILABLE("BILL_PAY_STATUS_UNAVAILABLE"),
    BILL_PMTS_MADE_FROM_ACCT_ONLY("BILL_PMTS_MADE_FROM_ACCT_ONLY"),
    BIN_DSNT_CONTAIN_ENOUGH_ITEM("BIN_DSNT_CONTAIN_ENOUGH_ITEM"),
    BIN_ITEM_UNAVAILBL("BIN_ITEM_UNAVAILBL"),
    BIN_SETUP_REQD("BIN_SETUP_REQD"),
    BIN_UNDEFND("BIN_UNDEFND"),
    BUNDLE_IS_DEPRECATED("BUNDLE_IS_DEPRECATED"),
    CALENDAR_PREFS_REQD("CALENDAR_PREFS_REQD"),
    CAMPAGIN_ALREADY_EXECUTED("CAMPAGIN_ALREADY_EXECUTED"),
    CAMPAIGN_IN_USE("CAMPAIGN_IN_USE"),
    CAMPAIGN_SET_UP_REQD("CAMPAIGN_SET_UP_REQD"),
    CANNOT_RESET_PASSWORD("CANNOT_RESET_PASSWORD"),
    CANT_APPLY_PMT("CANT_APPLY_PMT"),
    CANT_AUTO_CREATE_ADJSTMNT("CANT_AUTO_CREATE_ADJSTMNT"),
    CANT_CALC_FEDEX_RATES("CANT_CALC_FEDEX_RATES"),
    CANT_CANCEL_APPRVD_RETRN_AUTH("CANT_CANCEL_APPRVD_RETRN_AUTH"),
    CANT_CANCEL_BILL_PMT("CANT_CANCEL_BILL_PMT"),
    CANT_CHANGE_COMMSSN("CANT_CHANGE_COMMSSN"),
    CANT_CHANGE_CONTACT_RESTRICTN("CANT_CHANGE_CONTACT_RESTRICTN"),
    CANT_CHANGE_CRMRECORDTYPELINKS("CANT_CHANGE_CRMRECORDTYPELINKS"),
    CANT_CHANGE_EVENT_PRIMARY_TYP("CANT_CHANGE_EVENT_PRIMARY_TYP"),
    CANT_CHANGE_IP_ADDRESS("CANT_CHANGE_IP_ADDRESS"),
    CANT_CHANGE_LEAD_SOURCE_CAT("CANT_CHANGE_LEAD_SOURCE_CAT"),
    CANT_CHANGE_PSWD("CANT_CHANGE_PSWD"),
    CANT_CHANGE_REV_REC_TMPLT("CANT_CHANGE_REV_REC_TMPLT"),
    CANT_CHANGE_SUB("CANT_CHANGE_SUB"),
    CANT_CHANGE_TASK_LINK("CANT_CHANGE_TASK_LINK"),
    CANT_CHANGE_UNITS_TYP("CANT_CHANGE_UNITS_TYP"),
    CANT_CHANGE_VSOE_ALLOCTN("CANT_CHANGE_VSOE_ALLOCTN"),
    CANT_CHG_POSTED_BILL_VRNC("CANT_CHG_POSTED_BILL_VRNC"),
    CANT_COMPLETE_FULFILL("CANT_COMPLETE_FULFILL"),
    CANT_CONNECT_TO_STORE("CANT_CONNECT_TO_STORE"),
    CANT_CONVERT_CLASS_DEPT("CANT_CONVERT_CLASS_DEPT"),
    CANT_CONVERT_CLASS_LOC("CANT_CONVERT_CLASS_LOC"),
    CANT_CONVERT_INVT_ITEM("CANT_CONVERT_INVT_ITEM"),
    CANT_CREATE_FILES("CANT_CREATE_FILES"),
    CANT_CREATE_NON_UNIQUE_RCRD("CANT_CREATE_NON_UNIQUE_RCRD"),
    CANT_CREATE_PO("CANT_CREATE_PO"),
    CANT_CREATE_SHIP_LABEL("CANT_CREATE_SHIP_LABEL"),
    CANT_CREATE_WORK_ORD("CANT_CREATE_WORK_ORD"),
    CANT_CREAT_SHIP_LABEL("CANT_CREAT_SHIP_LABEL"),
    CANT_DELETE_ACCT("CANT_DELETE_ACCT"),
    CANT_DELETE_ACCT_PRD("CANT_DELETE_ACCT_PRD"),
    CANT_DELETE_ALLOCTN("CANT_DELETE_ALLOCTN"),
    CANT_DELETE_BIN("CANT_DELETE_BIN"),
    CANT_DELETE_CATEGORY("CANT_DELETE_CATEGORY"),
    CANT_DELETE_CC_PROCESSOR("CANT_DELETE_CC_PROCESSOR"),
    CANT_DELETE_CELL("CANT_DELETE_CELL"),
    CANT_DELETE_CHILD_RCRDS_EXIST("CANT_DELETE_CHILD_RCRDS_EXIST"),
    CANT_DELETE_CHILD_RCRD_FOUND("CANT_DELETE_CHILD_RCRD_FOUND"),
    CANT_DELETE_CLASS("CANT_DELETE_CLASS"),
    CANT_DELETE_COLOR_THEME("CANT_DELETE_COLOR_THEME"),
    CANT_DELETE_COMMSSN_SCHDUL("CANT_DELETE_COMMSSN_SCHDUL"),
    CANT_DELETE_COMPANY("CANT_DELETE_COMPANY"),
    CANT_DELETE_COMPANY_TYP("CANT_DELETE_COMPANY_TYP"),
    CANT_DELETE_CONTACT_HAS_CHILD("CANT_DELETE_CONTACT_HAS_CHILD"),
    CANT_DELETE_CSTM_FIELD("CANT_DELETE_CSTM_FIELD"),
    CANT_DELETE_CSTM_FORM("CANT_DELETE_CSTM_FORM"),
    CANT_DELETE_CSTM_ITEM_FIELD("CANT_DELETE_CSTM_ITEM_FIELD"),
    CANT_DELETE_CSTM_LAYOUT("CANT_DELETE_CSTM_LAYOUT"),
    CANT_DELETE_CSTM_LIST("CANT_DELETE_CSTM_LIST"),
    CANT_DELETE_CSTM_RCRD("CANT_DELETE_CSTM_RCRD"),
    CANT_DELETE_CSTM_RCRD_ENTRY("CANT_DELETE_CSTM_RCRD_ENTRY"),
    CANT_DELETE_CUST("CANT_DELETE_CUST"),
    CANT_DELETE_CUSTOMER("CANT_DELETE_CUSTOMER"),
    CANT_DELETE_DEFAULT_FLDR("CANT_DELETE_DEFAULT_FLDR"),
    CANT_DELETE_DEFAULT_PRIORITY("CANT_DELETE_DEFAULT_PRIORITY"),
    CANT_DELETE_DEFAULT_SALES_REP("CANT_DELETE_DEFAULT_SALES_REP"),
    CANT_DELETE_DEFAULT_STATUS("CANT_DELETE_DEFAULT_STATUS"),
    CANT_DELETE_DEFAULT_VALUE("CANT_DELETE_DEFAULT_VALUE"),
    CANT_DELETE_DEFAULT_WEBSITE("CANT_DELETE_DEFAULT_WEBSITE"),
    CANT_DELETE_EMPL("CANT_DELETE_EMPL"),
    CANT_DELETE_ENTITY("CANT_DELETE_ENTITY"),
    CANT_DELETE_FIN_STATMNT_LAYOUT("CANT_DELETE_FIN_STATMNT_LAYOUT"),
    CANT_DELETE_FLDR("CANT_DELETE_FLDR"),
    CANT_DELETE_HAS_CHILD_ITEM("CANT_DELETE_HAS_CHILD_ITEM"),
    CANT_DELETE_INFO_ITEM("CANT_DELETE_INFO_ITEM"),
    CANT_DELETE_ITEM("CANT_DELETE_ITEM"),
    CANT_DELETE_ITEM_LAYOUT("CANT_DELETE_ITEM_LAYOUT"),
    CANT_DELETE_ITEM_TMPLT("CANT_DELETE_ITEM_TMPLT"),
    CANT_DELETE_JOB_RESOURCE_ROLE("CANT_DELETE_JOB_RESOURCE_ROLE"),
    CANT_DELETE_LEGACY_CATEGORY("CANT_DELETE_LEGACY_CATEGORY"),
    CANT_DELETE_LINE("CANT_DELETE_LINE"),
    CANT_DELETE_MEDIA_ITEM("CANT_DELETE_MEDIA_ITEM"),
    CANT_DELETE_MEMRZD_TRANS("CANT_DELETE_MEMRZD_TRANS"),
    CANT_DELETE_OR_CHANGE_ACCT("CANT_DELETE_OR_CHANGE_ACCT"),
    CANT_DELETE_PLAN_ASSGNMNT("CANT_DELETE_PLAN_ASSGNMNT"),
    CANT_DELETE_PRESNTN_CAT("CANT_DELETE_PRESNTN_CAT"),
    CANT_DELETE_RCRD("CANT_DELETE_RCRD"),
    CANT_DELETE_RCRDS("CANT_DELETE_RCRDS"),
    CANT_DELETE_SITE_TAG("CANT_DELETE_SITE_TAG"),
    CANT_DELETE_SITE_THEME("CANT_DELETE_SITE_THEME"),
    CANT_DELETE_SOLUTN("CANT_DELETE_SOLUTN"),
    CANT_DELETE_STATUS_TYPE("CANT_DELETE_STATUS_TYPE"),
    CANT_DELETE_SUBTAB("CANT_DELETE_SUBTAB"),
    CANT_DELETE_SYSTEM_NOTE("CANT_DELETE_SYSTEM_NOTE"),
    CANT_DELETE_TAX_VENDOR("CANT_DELETE_TAX_VENDOR"),
    CANT_DELETE_TMPLT_RCRD("CANT_DELETE_TMPLT_RCRD"),
    CANT_DELETE_TRANS("CANT_DELETE_TRANS"),
    CANT_DELETE_TRAN_LINE("CANT_DELETE_TRAN_LINE"),
    CANT_DELETE_TRAN_LINES("CANT_DELETE_TRAN_LINES"),
    CANT_DELETE_UPDATE_ACCT("CANT_DELETE_UPDATE_ACCT"),
    CANT_DELETE_URL("CANT_DELETE_URL"),
    CANT_DELETE_VENDOR("CANT_DELETE_VENDOR"),
    CANT_DEL_DEFAULT_CALENDAR("CANT_DEL_DEFAULT_CALENDAR"),
    CANT_DEL_DEFAULT_SHIP_METHOD("CANT_DEL_DEFAULT_SHIP_METHOD"),
    CANT_DEL_REALIZED_GAINLOSS("CANT_DEL_REALIZED_GAINLOSS"),
    CANT_DEL_TRANS_RVRSL("CANT_DEL_TRANS_RVRSL"),
    CANT_DIVIDE_BY_ZERO("CANT_DIVIDE_BY_ZERO"),
    CANT_DOWNLOAD_EXPIRED_FILE("CANT_DOWNLOAD_EXPIRED_FILE"),
    CANT_EDIT_CHARGED_ORDER("CANT_EDIT_CHARGED_ORDER"),
    CANT_EDIT_CUST_LIST("CANT_EDIT_CUST_LIST"),
    CANT_EDIT_CUST_PMT("CANT_EDIT_CUST_PMT"),
    CANT_EDIT_DPLYMNT_IN_PROGRESS("CANT_EDIT_DPLYMNT_IN_PROGRESS"),
    CANT_EDIT_FOLDER("CANT_EDIT_FOLDER"),
    CANT_EDIT_OLD_CASE("CANT_EDIT_OLD_CASE"),
    CANT_EDIT_STANDARD_OBJ("CANT_EDIT_STANDARD_OBJ"),
    CANT_EDIT_TAGATA("CANT_EDIT_TAGATA"),
    CANT_EDIT_TRAN("CANT_EDIT_TRAN"),
    CANT_ESTABLISH_LINK("CANT_ESTABLISH_LINK"),
    CANT_FIND_BUG("CANT_FIND_BUG"),
    CANT_FIND_MAIL_MERGE_ID("CANT_FIND_MAIL_MERGE_ID"),
    CANT_FIND_RCRD("CANT_FIND_RCRD"),
    CANT_FIND_SAVED_IMPORT("CANT_FIND_SAVED_IMPORT"),
    CANT_FIND_SOURCE_AMORTZN_ACCT("CANT_FIND_SOURCE_AMORTZN_ACCT"),
    CANT_FIND_UPS_REG_FOR_LOC("CANT_FIND_UPS_REG_FOR_LOC"),
    CANT_FULFILL_ITEM("CANT_FULFILL_ITEM"),
    CANT_INACTIVATE_COMMSSN_PLAN("CANT_INACTIVATE_COMMSSN_PLAN"),
    CANT_INACTIVE_DEFAULT_SYNC_CAT("CANT_INACTIVE_DEFAULT_SYNC_CAT"),
    CANT_INACTIVE_DEFAULT_TMPLT("CANT_INACTIVE_DEFAULT_TMPLT"),
    CANT_LOAD_SAVED_SEARCH_PARAM("CANT_LOAD_SAVED_SEARCH_PARAM"),
    CANT_LOGIN_WITH_OAUTH("CANT_LOGIN_WITH_OAUTH"),
    CANT_LOOKUP_FLD("CANT_LOOKUP_FLD"),
    CANT_MAKE_CONTACT_PRIVATE("CANT_MAKE_CONTACT_PRIVATE"),
    CANT_MARK_SHIPPED("CANT_MARK_SHIPPED"),
    CANT_MERGE_EMPLS("CANT_MERGE_EMPLS"),
    CANT_MODIFY_APPRVD_TIME("CANT_MODIFY_APPRVD_TIME"),
    CANT_MODIFY_FULFILL_STATUS("CANT_MODIFY_FULFILL_STATUS"),
    CANT_MODIFY_ISSUE_STATUS("CANT_MODIFY_ISSUE_STATUS"),
    CANT_MODIFY_LOCKED_FLD("CANT_MODIFY_LOCKED_FLD"),
    CANT_MODIFY_PARENT("CANT_MODIFY_PARENT"),
    CANT_MODIFY_REV_REC("CANT_MODIFY_REV_REC"),
    CANT_MODIFY_SUB("CANT_MODIFY_SUB"),
    CANT_MODIFY_TAGATA("CANT_MODIFY_TAGATA"),
    CANT_MODIFY_TEGATA("CANT_MODIFY_TEGATA"),
    CANT_MODIFY_VOID_TRANS("CANT_MODIFY_VOID_TRANS"),
    CANT_MOVE_REALIZED_GAINLOSS("CANT_MOVE_REALIZED_GAINLOSS"),
    CANT_PAY_TAGATA("CANT_PAY_TAGATA"),
    CANT_PROCESS_IMG("CANT_PROCESS_IMG"),
    CANT_RCEIV_BEFORE_FULFILL("CANT_RCEIV_BEFORE_FULFILL"),
    CANT_RCEIV_ITEM("CANT_RCEIV_ITEM"),
    CANT_RECEIVE_TAGATA("CANT_RECEIVE_TAGATA"),
    CANT_REJECT_ORDER("CANT_REJECT_ORDER"),
    CANT_REMOVE_ACH_PAY_METHOD("CANT_REMOVE_ACH_PAY_METHOD"),
    CANT_REMOVE_APPROVAL("CANT_REMOVE_APPROVAL"),
    CANT_REMOVE_DOMAIN("CANT_REMOVE_DOMAIN"),
    CANT_REMOVE_NEXUS("CANT_REMOVE_NEXUS"),
    CANT_REMOVE_SCHDUL("CANT_REMOVE_SCHDUL"),
    CANT_REMOVE_SUB("CANT_REMOVE_SUB"),
    CANT_REMOV_ALL_FULFILMNT_LINKS("CANT_REMOV_ALL_FULFILMNT_LINKS"),
    CANT_REMOV_ITEM_SUB("CANT_REMOV_ITEM_SUB"),
    CANT_RESUBMIT_FAILED_DPLYMNT("CANT_RESUBMIT_FAILED_DPLYMNT"),
    CANT_RETURN_FLD("CANT_RETURN_FLD"),
    CANT_RETURN_USED_GIFT_CERT("CANT_RETURN_USED_GIFT_CERT"),
    CANT_REVERSE_AUTH("CANT_REVERSE_AUTH"),
    CANT_REV_REC_BODY_AND_LINE("CANT_REV_REC_BODY_AND_LINE"),
    CANT_SCHDUL_RECUR_EVENT("CANT_SCHDUL_RECUR_EVENT"),
    CANT_SEND_EMAIL("CANT_SEND_EMAIL"),
    CANT_SET_CLOSE_DATE("CANT_SET_CLOSE_DATE"),
    CANT_SET_INTERNALID("CANT_SET_INTERNALID"),
    CANT_SET_STATUS("CANT_SET_STATUS"),
    CANT_SWITCH_ROLES_FROM_LOGIN("CANT_SWITCH_ROLES_FROM_LOGIN"),
    CANT_SWITCH_SHIP_METHOD("CANT_SWITCH_SHIP_METHOD"),
    CANT_UPDATE_ACCTNG_PRDS("CANT_UPDATE_ACCTNG_PRDS"),
    CANT_UPDATE_AMT("CANT_UPDATE_AMT"),
    CANT_UPDATE_DYNAMIC_GROUP("CANT_UPDATE_DYNAMIC_GROUP"),
    CANT_UPDATE_FLDR("CANT_UPDATE_FLDR"),
    CANT_UPDATE_LINKED_TRANS_LINES("CANT_UPDATE_LINKED_TRANS_LINES"),
    CANT_UPDATE_PRODUCT_FEED("CANT_UPDATE_PRODUCT_FEED"),
    CANT_UPDATE_RECRD_HAS_CHANGED("CANT_UPDATE_RECRD_HAS_CHANGED"),
    CANT_UPDATE_RECUR_EVENT("CANT_UPDATE_RECUR_EVENT"),
    CANT_UPDATE_ROOT_CATEGORY("CANT_UPDATE_ROOT_CATEGORY"),
    CANT_UPDATE_STATUS_TYPE("CANT_UPDATE_STATUS_TYPE"),
    CANT_VERIFY_CARD("CANT_VERIFY_CARD"),
    CANT_VOID_TRANS("CANT_VOID_TRANS"),
    CARD_EXPIRED("CARD_EXPIRED"),
    CARD_ID_REQD("CARD_ID_REQD"),
    CASE_ALREADY_ASSIGNED("CASE_ALREADY_ASSIGNED"),
    CASE_DSNT_EXIST("CASE_DSNT_EXIST"),
    CASE_NOT_GROUP_MEMBER("CASE_NOT_GROUP_MEMBER"),
    CASH_SALE_EDIT_DISALLWD("CASH_SALE_EDIT_DISALLWD"),
    CC_ACCT_REQD("CC_ACCT_REQD"),
    CC_ALREADY_SAVED("CC_ALREADY_SAVED"),
    CC_EMAIL_ADDRESS_REQD("CC_EMAIL_ADDRESS_REQD"),
    CC_NUM_REQD("CC_NUM_REQD"),
    CC_PROCESSOR_ERROR("CC_PROCESSOR_ERROR"),
    CC_PROCESSOR_NOT_FOUND("CC_PROCESSOR_NOT_FOUND"),
    CC_SECURITY_CODE_REQD("CC_SECURITY_CODE_REQD"),
    CERT_AUTH_EXPD("CERT_AUTH_EXPD"),
    CERT_EXPD("CERT_EXPD"),
    CERT_UNAVAILABLE("CERT_UNAVAILABLE"),
    CHANGE_PMT_DATE_AND_REAPPROVE("CHANGE_PMT_DATE_AND_REAPPROVE"),
    CHAR_ERROR("CHAR_ERROR"),
    CHECKOUT_EMAIL_REQD("CHECKOUT_EMAIL_REQD"),
    CITY_REQD("CITY_REQD"),
    CLASS_ALREADY_EXISTS("CLASS_ALREADY_EXISTS"),
    CLASS_OR_DEPT_OR_CUST_REQD("CLASS_OR_DEPT_OR_CUST_REQD"),
    CLEAR_AUTOCALC("CLEAR_AUTOCALC"),
    CLOSED_TRAN_PRD("CLOSED_TRAN_PRD"),
    CLOSE_PREVIOUSE_PERIOD("CLOSE_PREVIOUSE_PERIOD"),
    COGS_ERROR("COGS_ERROR"),
    COMMSSN_ALREADY_CALCLTD("COMMSSN_ALREADY_CALCLTD"),
    COMMSSN_FEATURE_DISABLED("COMMSSN_FEATURE_DISABLED"),
    COMMSSN_PAYROLL_ITEM_REQD("COMMSSN_PAYROLL_ITEM_REQD"),
    COMPANION_PROP_REQD("COMPANION_PROP_REQD"),
    COMPANY_DISABLED("COMPANY_DISABLED"),
    COMPANY_FLD_REQD("COMPANY_FLD_REQD"),
    COMP_DELETED_OR_MERGED("COMP_DELETED_OR_MERGED"),
    CONCUR_BILLPAY_JOB_DISALLWD("CONCUR_BILLPAY_JOB_DISALLWD"),
    CONCUR_BULK_JOB_DISALLWD("CONCUR_BULK_JOB_DISALLWD"),
    CONCUR_MASS_UPDATE_DISALLWD("CONCUR_MASS_UPDATE_DISALLWD"),
    CONCUR_SEARCH_DISALLWD("CONCUR_SEARCH_DISALLWD"),
    CONSLD_PRNT_AND_CHILD_DISALLWD("CONSLD_PRNT_AND_CHILD_DISALLWD"),
    CONTACT_ALREADY_EXISTS("CONTACT_ALREADY_EXISTS"),
    CONTACT_NOT_GROUP_MEMBR("CONTACT_NOT_GROUP_MEMBR"),
    COOKIES_DISABLED("COOKIES_DISABLED"),
    COUNTRY_STATE_MISMATCH("COUNTRY_STATE_MISMATCH"),
    CREATEDFROM_REQD("CREATEDFROM_REQD"),
    CREDITS_DISALLWD("CREDITS_DISALLWD"),
    CRNCY_MISMATCH_BASE_CRNCY("CRNCY_MISMATCH_BASE_CRNCY"),
    CRNCY_NOT_UPDATED("CRNCY_NOT_UPDATED"),
    CRNCY_RCRD_DELETED("CRNCY_RCRD_DELETED"),
    CRNCY_REQD("CRNCY_REQD"),
    CSC_SETUP_REQD("CSC_SETUP_REQD"),
    CSTM_FIELD_KEY_REQD("CSTM_FIELD_KEY_REQD"),
    CSTM_FIELD_VALUE_REQD("CSTM_FIELD_VALUE_REQD"),
    CSV_DELIMITER_ERROR("CSV_DELIMITER_ERROR"),
    CUST_ARLEADY_HAS_ACCT("CUST_ARLEADY_HAS_ACCT"),
    CUST_CNTR_USER_ACCESS_ONLY("CUST_CNTR_USER_ACCESS_ONLY"),
    CUST_LEAD_NOT_GROUP_MEMBR("CUST_LEAD_NOT_GROUP_MEMBR"),
    CYBERSOURCE_ERROR("CYBERSOURCE_ERROR"),
    CYCLE_IN_PROJECT_PLAN("CYCLE_IN_PROJECT_PLAN"),
    DASHBOARD_LOCKED("DASHBOARD_LOCKED"),
    DATA_MUST_BE_UNIQUE("DATA_MUST_BE_UNIQUE"),
    DATA_REQD("DATA_REQD"),
    DATE_EXPECTED("DATE_EXPECTED"),
    DATE_PARAM_REQD("DATE_PARAM_REQD"),
    DATE_PRD_MISMATCH("DATE_PRD_MISMATCH"),
    DEFAULT_CUR_REQD("DEFAULT_CUR_REQD"),
    DEFAULT_EXPENSE_ACCT_REQD("DEFAULT_EXPENSE_ACCT_REQD"),
    DEFAULT_ISSUE_OWNER_REQD("DEFAULT_ISSUE_OWNER_REQD"),
    DEFAULT_LIAB_ACCT_REQD("DEFAULT_LIAB_ACCT_REQD"),
    DEFAULT_ROLE_REQD("DEFAULT_ROLE_REQD"),
    DEFAULT_TYPE_DELETE_DISALLWD("DEFAULT_TYPE_DELETE_DISALLWD"),
    DEFERRAL_ACCT_REQD("DEFERRAL_ACCT_REQD"),
    DEFERRED_REV_REC_ACCT_REQD("DEFERRED_REV_REC_ACCT_REQD"),
    DEPT_IN_USE("DEPT_IN_USE"),
    DFRNT_SWAP_PRICE_LEVELS_REQD("DFRNT_SWAP_PRICE_LEVELS_REQD"),
    DISALLWD_IP_ADDRESS("DISALLWD_IP_ADDRESS"),
    DISCOUNT_ACCT_SETUP_REQD("DISCOUNT_ACCT_SETUP_REQD"),
    DISCOUNT_DISALLWD("DISCOUNT_DISALLWD"),
    DISCOUNT_DISALLWD_VSOE("DISCOUNT_DISALLWD_VSOE"),
    DISCOUNT_EXCEED_TOTAL("DISCOUNT_EXCEED_TOTAL"),
    DISTRIB_REQD_ONE_DAY_BFORE("DISTRIB_REQD_ONE_DAY_BFORE"),
    DOMAIN_IN_USE("DOMAIN_IN_USE"),
    DOMAIN_WEBSITE_REQD("DOMAIN_WEBSITE_REQD"),
    DROP_SHIP_ERROR("DROP_SHIP_ERROR"),
    DROP_SHIP_OR_SPECIAL_ORD_ALLWD("DROP_SHIP_OR_SPECIAL_ORD_ALLWD"),
    DUE_DATE_BFORE_START_DATE("DUE_DATE_BFORE_START_DATE"),
    DUE_DATE_REQD("DUE_DATE_REQD"),
    DUPLICATE_INVENTORY_NUM("DUPLICATE_INVENTORY_NUM"),
    DUPLICATE_KEYS("DUPLICATE_KEYS"),
    DUPLICATE_NAME_FOR_PRD("DUPLICATE_NAME_FOR_PRD"),
    DUPLICATE_NAME_FOR_ROLE("DUPLICATE_NAME_FOR_ROLE"),
    DUP_ACCT_NAME("DUP_ACCT_NAME"),
    DUP_ACCT_NOT_ALLWD("DUP_ACCT_NOT_ALLWD"),
    DUP_ACCT_NUM("DUP_ACCT_NUM"),
    DUP_ACCT_ON_TRANS("DUP_ACCT_ON_TRANS"),
    DUP_BIN("DUP_BIN"),
    DUP_BUNDLE_IN_ACCT("DUP_BUNDLE_IN_ACCT"),
    DUP_CATEGORY("DUP_CATEGORY"),
    DUP_CATEGORY_NAME("DUP_CATEGORY_NAME"),
    DUP_COLOR_THEME("DUP_COLOR_THEME"),
    DUP_CSTM_FIELD("DUP_CSTM_FIELD"),
    DUP_CSTM_LAYOUT("DUP_CSTM_LAYOUT"),
    DUP_CSTM_LIST("DUP_CSTM_LIST"),
    DUP_CSTM_RCRD("DUP_CSTM_RCRD"),
    DUP_CSTM_RCRD_ENTRY("DUP_CSTM_RCRD_ENTRY"),
    DUP_CSTM_TAB("DUP_CSTM_TAB"),
    DUP_EMPL_EMAIL("DUP_EMPL_EMAIL"),
    DUP_EMPL_ENTITY_NAME("DUP_EMPL_ENTITY_NAME"),
    DUP_EMPL_TMPLT("DUP_EMPL_TMPLT"),
    DUP_ENTITY("DUP_ENTITY"),
    DUP_ENTITY_EMAIL("DUP_ENTITY_EMAIL"),
    DUP_ENTITY_NAME("DUP_ENTITY_NAME"),
    DUP_FEDEX_ACCT_NUM("DUP_FEDEX_ACCT_NUM"),
    DUP_FINANCL_STATMNT_LAYOUT("DUP_FINANCL_STATMNT_LAYOUT"),
    DUP_INFO_ITEM("DUP_INFO_ITEM"),
    DUP_ISSUE_NAME_OR_NUM("DUP_ISSUE_NAME_OR_NUM"),
    DUP_ITEM("DUP_ITEM"),
    DUP_ITEM_LAYOUT("DUP_ITEM_LAYOUT"),
    DUP_ITEM_NAME("DUP_ITEM_NAME"),
    DUP_ITEM_OPTION("DUP_ITEM_OPTION"),
    DUP_ITEM_TMPLT("DUP_ITEM_TMPLT"),
    DUP_MATRIX_OPTN_ABBRV("DUP_MATRIX_OPTN_ABBRV"),
    DUP_MEMRZD_TRANS("DUP_MEMRZD_TRANS"),
    DUP_NAME("DUP_NAME"),
    DUP_PAYROLL_ITEM("DUP_PAYROLL_ITEM"),
    DUP_PRESNTN_CAT("DUP_PRESNTN_CAT"),
    DUP_RCRD("DUP_RCRD"),
    DUP_RCRD_LINK("DUP_RCRD_LINK"),
    DUP_SALES_TAX_ITEM("DUP_SALES_TAX_ITEM"),
    DUP_SHIPPING_ITEM("DUP_SHIPPING_ITEM"),
    DUP_SHORT_NAME("DUP_SHORT_NAME"),
    DUP_SITE_THEME("DUP_SITE_THEME"),
    DUP_SOURCE_ACCT("DUP_SOURCE_ACCT"),
    DUP_TAX_CODE("DUP_TAX_CODE"),
    DUP_TRACKING_NUM("DUP_TRACKING_NUM"),
    DUP_TRANS("DUP_TRANS"),
    DUP_UPS_ACCT_NUM("DUP_UPS_ACCT_NUM"),
    DUP_VENDOR_EMAIL("DUP_VENDOR_EMAIL"),
    DUP_VENDOR_NAME("DUP_VENDOR_NAME"),
    EARNING_ITEM_REQD("EARNING_ITEM_REQD"),
    EBAY_FEE_ERROR("EBAY_FEE_ERROR"),
    EBAY_TMPLT_ERROR("EBAY_TMPLT_ERROR"),
    EDITION_DSNT_SUPRT_WORLDPAY("EDITION_DSNT_SUPRT_WORLDPAY"),
    EIN_OR_TIN_REQD("EIN_OR_TIN_REQD"),
    EMAIL_ADDRS_REQD("EMAIL_ADDRS_REQD"),
    EMAIL_ADDRS_REQD_TO_NOTIFY("EMAIL_ADDRS_REQD_TO_NOTIFY"),
    EMAIL_REQD("EMAIL_REQD"),
    EMAIL_REQD_ACCT_PROVISION("EMAIL_REQD_ACCT_PROVISION"),
    EMAIL_REQ_HANDLER_ERROR("EMAIL_REQ_HANDLER_ERROR"),
    EMPL_IN_USE("EMPL_IN_USE"),
    ERROR_DELETE_CARD_DATA("ERROR_DELETE_CARD_DATA"),
    ERROR_IN_TERRITORY_ASSGNMNT("ERROR_IN_TERRITORY_ASSGNMNT"),
    ERROR_PRCSSNG_TRANS("ERROR_PRCSSNG_TRANS"),
    ERROR_REFUND_TRANS("ERROR_REFUND_TRANS"),
    ERROR_REVERSE_AUTH("ERROR_REVERSE_AUTH"),
    ERROR_SENDING_TRAN_EMAIL("ERROR_SENDING_TRAN_EMAIL"),
    ERROR_VOID_TRANS("ERROR_VOID_TRANS"),
    EVENT_ID_NOT_FOUND("EVENT_ID_NOT_FOUND"),
    EXCEEDED_MAX_ALLWD_LOC("EXCEEDED_MAX_ALLWD_LOC"),
    EXCEEDED_MAX_CONCUR_RQST("EXCEEDED_MAX_CONCUR_RQST"),
    EXCEEDED_MAX_EMAILS("EXCEEDED_MAX_EMAILS"),
    EXCEEDED_MAX_FEATURED_ITEMS("EXCEEDED_MAX_FEATURED_ITEMS"),
    EXCEEDED_MAX_FIELD_LENGTH("EXCEEDED_MAX_FIELD_LENGTH"),
    EXCEEDED_MAX_MATRIX_OPTNS("EXCEEDED_MAX_MATRIX_OPTNS"),
    EXCEEDED_MAX_PDF_ELEMENTS("EXCEEDED_MAX_PDF_ELEMENTS"),
    EXCEEDED_MAX_PDF_EXPORT_COL("EXCEEDED_MAX_PDF_EXPORT_COL"),
    EXCEEDED_MAX_PIN_RETRIES("EXCEEDED_MAX_PIN_RETRIES"),
    EXCEEDED_MAX_RCRD("EXCEEDED_MAX_RCRD"),
    EXCEEDED_MAX_REPORT_COL("EXCEEDED_MAX_REPORT_COL"),
    EXCEEDED_MAX_REPORT_ROWS("EXCEEDED_MAX_REPORT_ROWS"),
    EXCEEDED_MAX_REPORT_SIZE("EXCEEDED_MAX_REPORT_SIZE"),
    EXCEEDED_MAX_SESSIONS("EXCEEDED_MAX_SESSIONS"),
    EXCEEDED_MAX_SHIP_PACKAGE("EXCEEDED_MAX_SHIP_PACKAGE"),
    EXCEEDED_MAX_TIME("EXCEEDED_MAX_TIME"),
    EXCEEDED_MAX_TRANS_LINES("EXCEEDED_MAX_TRANS_LINES"),
    EXCEEDED_MAX_USERS_ALLWD("EXCEEDED_MAX_USERS_ALLWD"),
    EXCEEDED_PER_TRANS_MAX("EXCEEDED_PER_TRANS_MAX"),
    EXCEEDED_RQST_SIZE_LIMIT("EXCEEDED_RQST_SIZE_LIMIT"),
    EXCEEDS_ALLWD_LICENSES("EXCEEDS_ALLWD_LICENSES"),
    EXPENSE_ENTRY_DISALLWD("EXPENSE_ENTRY_DISALLWD"),
    EXPIRED_SEARCH_CRITERIA("EXPIRED_SEARCH_CRITERIA"),
    EXTERNALID_NOT_SUPPORTED("EXTERNALID_NOT_SUPPORTED"),
    EXTERNALID_REQD("EXTERNALID_REQD"),
    EXT_CAT_LINK_SETUP_REQD("EXT_CAT_LINK_SETUP_REQD"),
    FAILED_FEDEX_LABEL_VOID("FAILED_FEDEX_LABEL_VOID"),
    FAILED_FORM_VALIDATION("FAILED_FORM_VALIDATION"),
    FAILED_UPS_LABEL_VOID("FAILED_UPS_LABEL_VOID"),
    FAX_NUM_REQD("FAX_NUM_REQD"),
    FAX_SETUP_REQD("FAX_SETUP_REQD"),
    FEATURE_DISABLED("FEATURE_DISABLED"),
    FEATURE_UNAVAILABLE("FEATURE_UNAVAILABLE"),
    FEDEX_ACCT_REQD("FEDEX_ACCT_REQD"),
    FEDEX_CANT_INTEGRATE_FULFILL("FEDEX_CANT_INTEGRATE_FULFILL"),
    FEDEX_DROPOFF_TYP_REQD("FEDEX_DROPOFF_TYP_REQD"),
    FEDEX_INVALID_ACCT_NUM("FEDEX_INVALID_ACCT_NUM"),
    FEDEX_ITEM_CONTENTS_REQD("FEDEX_ITEM_CONTENTS_REQD"),
    FEDEX_METER_NOT_RETRIEVED("FEDEX_METER_NOT_RETRIEVED"),
    FEDEX_METER_REQD("FEDEX_METER_REQD"),
    FEDEX_ONE_PACKG_ALLWD("FEDEX_ONE_PACKG_ALLWD"),
    FEDEX_ORIGIN_COUNTRY_US_REQD("FEDEX_ORIGIN_COUNTRY_US_REQD"),
    FEDEX_RATING_SRVC_UNAVAILBL("FEDEX_RATING_SRVC_UNAVAILBL"),
    FEDEX_REG_NOT_FOUND("FEDEX_REG_NOT_FOUND"),
    FEDEX_SHIP_SRVC_REQD("FEDEX_SHIP_SRVC_REQD"),
    FEDEX_SHIP_SRVC_UNAVAILBL("FEDEX_SHIP_SRVC_UNAVAILBL"),
    FEDEX_UNSUPRTD_ORIGIN_COUNTRY("FEDEX_UNSUPRTD_ORIGIN_COUNTRY"),
    FEDEX_USD_EXCHANGE_RATE_REQD("FEDEX_USD_EXCHANGE_RATE_REQD"),
    FEDEX_USER_ERROR("FEDEX_USER_ERROR"),
    FEDEX_VOID_ERROR("FEDEX_VOID_ERROR"),
    FED_ID_REQD("FED_ID_REQD"),
    FED_WITHHOLDING_REQD("FED_WITHHOLDING_REQD"),
    FIELD_CALL_DATE_REQD("FIELD_CALL_DATE_REQD"),
    FIELD_DEFN_REQD("FIELD_DEFN_REQD"),
    FIELD_NOT_SETTABLE_ON_ADD("FIELD_NOT_SETTABLE_ON_ADD"),
    FIELD_PARAM_REQD("FIELD_PARAM_REQD"),
    FIELD_REQD("FIELD_REQD"),
    FILE_ALREADY_EXISTS("FILE_ALREADY_EXISTS"),
    FILE_DISALLWD_IN_ROOT_FLDR("FILE_DISALLWD_IN_ROOT_FLDR"),
    FILE_MISSING("FILE_MISSING"),
    FILE_NOT_DOWNLOADABLE("FILE_NOT_DOWNLOADABLE"),
    FILE_NOT_FOUND("FILE_NOT_FOUND"),
    FILE_REQD("FILE_REQD"),
    FILE_UPLOAD_IN_PROGRESS("FILE_UPLOAD_IN_PROGRESS"),
    FILTER_BY_AMT_REQD("FILTER_BY_AMT_REQD"),
    FINANCE_CHARGE_SETUP_REQD("FINANCE_CHARGE_SETUP_REQD"),
    FINANCE_CHARGE_SET_PREFS("FINANCE_CHARGE_SET_PREFS"),
    FIRST_LAST_NAMES_REQD("FIRST_LAST_NAMES_REQD"),
    FIRST_QTY_BUCKET_MUST_BE_ZERO("FIRST_QTY_BUCKET_MUST_BE_ZERO"),
    FLD_VALUE_REQD("FLD_VALUE_REQD"),
    FLD_VALUE_TOO_LARGE("FLD_VALUE_TOO_LARGE"),
    FOLDER_ALREADY_EXISTS("FOLDER_ALREADY_EXISTS"),
    FORMULA_ERROR("FORMULA_ERROR"),
    FORM_RESUBMISSION_REQD("FORM_RESUBMISSION_REQD"),
    FORM_SETUP_REQD("FORM_SETUP_REQD"),
    FORM_UNAVAILBL_ONLINE("FORM_UNAVAILBL_ONLINE"),
    FRIENDLY_NAME_REQD("FRIENDLY_NAME_REQD"),
    FULFILL_REQD_FIELDS_MISSING("FULFILL_REQD_FIELDS_MISSING"),
    FULFILL_REQD_PARAMS_MISSING("FULFILL_REQD_PARAMS_MISSING"),
    FULL_DISTRIB_REQD("FULL_DISTRIB_REQD"),
    FULL_USERS_REQD_TO_INTEGRATE("FULL_USERS_REQD_TO_INTEGRATE"),
    FX_MALFORMED_RESPONSE("FX_MALFORMED_RESPONSE"),
    FX_RATE_REQD_FEDEX_RATE("FX_RATE_REQD_FEDEX_RATE"),
    FX_TRANS_DISALLWD("FX_TRANS_DISALLWD"),
    GETALL_RCRD_TYPE_REQD("GETALL_RCRD_TYPE_REQD"),
    GIFT_CERT_AMT_EXCEED_AVAILBL("GIFT_CERT_AMT_EXCEED_AVAILBL"),
    GIFT_CERT_AUTH_ALREADY_EXISTS("GIFT_CERT_AUTH_ALREADY_EXISTS"),
    GIFT_CERT_CAN_BE_USED_ONCE("GIFT_CERT_CAN_BE_USED_ONCE"),
    GIFT_CERT_CODE_REQD("GIFT_CERT_CODE_REQD"),
    GIFT_CERT_INVALID_NUM("GIFT_CERT_INVALID_NUM"),
    GIFT_CERT_IN_USE("GIFT_CERT_IN_USE"),
    GROUP_DSNT_EXIST("GROUP_DSNT_EXIST"),
    GROUP_REQD("GROUP_REQD"),
    GROUP_TYPE_REQD("GROUP_TYPE_REQD"),
    GRTR_QTY_PRICE_LEVEL_REQD("GRTR_QTY_PRICE_LEVEL_REQD"),
    IGNORE("IGNORE"),
    ILLEGAL_ID("ILLEGAL_ID"),
    ILLEGAL_PERIOD_STRUCTURE("ILLEGAL_PERIOD_STRUCTURE"),
    INACTIVE_CC_PROFILE("INACTIVE_CC_PROFILE"),
    INACTIVE_RCRD_FOR_ROLE("INACTIVE_RCRD_FOR_ROLE"),
    INAVLID_FILE_TYP("INAVLID_FILE_TYP"),
    INAVLID_ITEM_TYP("INAVLID_ITEM_TYP"),
    INAVLID_PRICING_MTRX("INAVLID_PRICING_MTRX"),
    INCOMPATIBLE_ACCT_CHANGE("INCOMPATIBLE_ACCT_CHANGE"),
    INCOMPLETE_BILLING_ADDR("INCOMPLETE_BILLING_ADDR"),
    INCOMPLETE_FILE_UPLOAD("INCOMPLETE_FILE_UPLOAD"),
    INCRCT_ORD_INFO("INCRCT_ORD_INFO"),
    INFINITE_LOOP_DETECTED("INFINITE_LOOP_DETECTED"),
    INITIALIZE_ARG_REQD("INITIALIZE_ARG_REQD"),
    INITIALIZE_AUXREF_REQD("INITIALIZE_AUXREF_REQD"),
    INSTALL_SCRIPT_ERROR("INSTALL_SCRIPT_ERROR"),
    INSUFCNT_NUM_PRDS_FOR_REV_REC("INSUFCNT_NUM_PRDS_FOR_REV_REC"),
    INSUFCNT_OPEN_PRDS_FOR_REV_REC("INSUFCNT_OPEN_PRDS_FOR_REV_REC"),
    INSUFFICIENT_CHARS_IN_SEARCH("INSUFFICIENT_CHARS_IN_SEARCH"),
    INSUFFICIENT_FLD_PERMISSION("INSUFFICIENT_FLD_PERMISSION"),
    INSUFFICIENT_FUND("INSUFFICIENT_FUND"),
    INSUFFICIENT_PERMISSION("INSUFFICIENT_PERMISSION"),
    INTEGER_REQD_FOR_QTY("INTEGER_REQD_FOR_QTY"),
    INTL_FEDEX_ONE_PACKG_ALLWD("INTL_FEDEX_ONE_PACKG_ALLWD"),
    INTL_SHIP_EXCEED_MAX_ITEM("INTL_SHIP_EXCEED_MAX_ITEM"),
    INVALID_ABN("INVALID_ABN"),
    INVALID_ACCT("INVALID_ACCT"),
    INVALID_ACCT_NUM_CSTM_FIELD("INVALID_ACCT_NUM_CSTM_FIELD"),
    INVALID_ACCT_PRD("INVALID_ACCT_PRD"),
    INVALID_ACCT_TYP("INVALID_ACCT_TYP"),
    INVALID_ACTION("INVALID_ACTION"),
    INVALID_ADDRESS_OR_SHIPPER_NO("INVALID_ADDRESS_OR_SHIPPER_NO"),
    INVALID_ADJUSTMENT_ACCT("INVALID_ADJUSTMENT_ACCT"),
    INVALID_AES_FTSR_EXEMPTN_NUM("INVALID_AES_FTSR_EXEMPTN_NUM"),
    INVALID_ALLOCTN_METHOD("INVALID_ALLOCTN_METHOD"),
    INVALID_AMORTZN_ACCT("INVALID_AMORTZN_ACCT"),
    INVALID_AMT("INVALID_AMT"),
    INVALID_APP_ID("INVALID_APP_ID"),
    INVALID_ASSIGN_STATUS_COMBO("INVALID_ASSIGN_STATUS_COMBO"),
    INVALID_AUTH("INVALID_AUTH"),
    INVALID_AUTH_CODE("INVALID_AUTH_CODE"),
    INVALID_AUTOAPPLY_VALUE("INVALID_AUTOAPPLY_VALUE"),
    INVALID_AVS_ADDR("INVALID_AVS_ADDR"),
    INVALID_AVS_ZIP("INVALID_AVS_ZIP"),
    INVALID_BALANCE_RANGE("INVALID_BALANCE_RANGE"),
    INVALID_BILLING_SCHDUL("INVALID_BILLING_SCHDUL"),
    INVALID_BILLING_SCHDUL_DATE("INVALID_BILLING_SCHDUL_DATE"),
    INVALID_BILLING_SCHDUL_ENTRY("INVALID_BILLING_SCHDUL_ENTRY"),
    INVALID_BIN_NUM("INVALID_BIN_NUM"),
    INVALID_BOM_QTY("INVALID_BOM_QTY"),
    INVALID_BOOLEAN_VALUE("INVALID_BOOLEAN_VALUE"),
    INVALID_BUG_NUM("INVALID_BUG_NUM"),
    INVALID_CAMPAIGN_CHANNEL("INVALID_CAMPAIGN_CHANNEL"),
    INVALID_CAMPAIGN_GROUP_SIZE("INVALID_CAMPAIGN_GROUP_SIZE"),
    INVALID_CAMPAIGN_STATUS("INVALID_CAMPAIGN_STATUS"),
    INVALID_CARD("INVALID_CARD"),
    INVALID_CARD_ID("INVALID_CARD_ID"),
    INVALID_CARD_NUM("INVALID_CARD_NUM"),
    INVALID_CARD_TYP("INVALID_CARD_TYP"),
    INVALID_CASE_FORM("INVALID_CASE_FORM"),
    INVALID_CATGRY_TAX_AGENCY_REQ("INVALID_CATGRY_TAX_AGENCY_REQ"),
    INVALID_CC_EMAIL_ADDRESS("INVALID_CC_EMAIL_ADDRESS"),
    INVALID_CC_NUM("INVALID_CC_NUM"),
    INVALID_CERT("INVALID_CERT"),
    INVALID_CERT_AUTH("INVALID_CERT_AUTH"),
    INVALID_CHANGE_LIST("INVALID_CHANGE_LIST"),
    INVALID_CHARGE_AMT("INVALID_CHARGE_AMT"),
    INVALID_CHARS_IN_EMAIL("INVALID_CHARS_IN_EMAIL"),
    INVALID_CHARS_IN_NAME("INVALID_CHARS_IN_NAME"),
    INVALID_CHARS_IN_PARAM_FIELD("INVALID_CHARS_IN_PARAM_FIELD"),
    INVALID_CHARS_IN_URL("INVALID_CHARS_IN_URL"),
    INVALID_CHECKOUT_EMAIL("INVALID_CHECKOUT_EMAIL"),
    INVALID_CITY("INVALID_CITY"),
    INVALID_COLUMN_NAME("INVALID_COLUMN_NAME"),
    INVALID_COLUMN_VALUE("INVALID_COLUMN_VALUE"),
    INVALID_CONTENT_TYPE("INVALID_CONTENT_TYPE"),
    INVALID_COSTING_METHOD("INVALID_COSTING_METHOD"),
    INVALID_CRNCY_EXCH_RATE("INVALID_CRNCY_EXCH_RATE"),
    INVALID_CRYPT_KEY("INVALID_CRYPT_KEY"),
    INVALID_CSTM_FIELD_DATA_TYP("INVALID_CSTM_FIELD_DATA_TYP"),
    INVALID_CSTM_FIELD_RCRD_TYP("INVALID_CSTM_FIELD_RCRD_TYP"),
    INVALID_CSTM_FIELD_REF("INVALID_CSTM_FIELD_REF"),
    INVALID_CSTM_FORM("INVALID_CSTM_FORM"),
    INVALID_CSTM_RCRD_KEY("INVALID_CSTM_RCRD_KEY"),
    INVALID_CSTM_RCRD_QUERY("INVALID_CSTM_RCRD_QUERY"),
    INVALID_CSTM_RCRD_TYPE_KEY("INVALID_CSTM_RCRD_TYPE_KEY"),
    INVALID_CSTM_RCRD_TYP_KEY("INVALID_CSTM_RCRD_TYP_KEY"),
    INVALID_CUR("INVALID_CUR"),
    INVALID_CURRENCY_CODE("INVALID_CURRENCY_CODE"),
    INVALID_CURRENCY_TYP("INVALID_CURRENCY_TYP"),
    INVALID_CURR_CODE("INVALID_CURR_CODE"),
    INVALID_CUSTOMER_RCRD("INVALID_CUSTOMER_RCRD"),
    INVALID_DATA("INVALID_DATA"),
    INVALID_DATA_FORMAT("INVALID_DATA_FORMAT"),
    INVALID_DATE("INVALID_DATE"),
    INVALID_DATE_FORMAT("INVALID_DATE_FORMAT"),
    INVALID_DATE_RANGE("INVALID_DATE_RANGE"),
    INVALID_DATE_TIME("INVALID_DATE_TIME"),
    INVALID_DEAL_RANGE("INVALID_DEAL_RANGE"),
    INVALID_DELETE_REF("INVALID_DELETE_REF"),
    INVALID_DESTINATION_FLDR("INVALID_DESTINATION_FLDR"),
    INVALID_DESTNTN_COUNTRY("INVALID_DESTNTN_COUNTRY"),
    INVALID_DESTNTN_POST_CODE("INVALID_DESTNTN_POST_CODE"),
    INVALID_DESTNTN_STATE("INVALID_DESTNTN_STATE"),
    INVALID_DETACH_RECORD_TYP("INVALID_DETACH_RECORD_TYP"),
    INVALID_DISCOUNT_MARKUP("INVALID_DISCOUNT_MARKUP"),
    INVALID_DOMAIN_KEY("INVALID_DOMAIN_KEY"),
    INVALID_DOMAIN_NAME("INVALID_DOMAIN_NAME"),
    INVALID_DUP_ISSUE_REF("INVALID_DUP_ISSUE_REF"),
    INVALID_EMAIL("INVALID_EMAIL"),
    INVALID_EMAIL_ADDR("INVALID_EMAIL_ADDR"),
    INVALID_END_DATE("INVALID_END_DATE"),
    INVALID_END_TIME("INVALID_END_TIME"),
    INVALID_ENTITY_INTERNALID("INVALID_ENTITY_INTERNALID"),
    INVALID_ENTITY_STATUS("INVALID_ENTITY_STATUS"),
    INVALID_EVENT_TIME("INVALID_EVENT_TIME"),
    INVALID_EXPNS_ACCT_SUB("INVALID_EXPNS_ACCT_SUB"),
    INVALID_EXPRESSION("INVALID_EXPRESSION"),
    INVALID_EXP_DATE("INVALID_EXP_DATE"),
    INVALID_FAX_NUM("INVALID_FAX_NUM"),
    INVALID_FAX_PHONE_FORMAT("INVALID_FAX_PHONE_FORMAT"),
    INVALID_FIELD_FOR_RCRD_TYP("INVALID_FIELD_FOR_RCRD_TYP"),
    INVALID_FIELD_NAME_FOR_NULL("INVALID_FIELD_NAME_FOR_NULL"),
    INVALID_FILE("INVALID_FILE"),
    INVALID_FILE_ENCODING("INVALID_FILE_ENCODING"),
    INVALID_FILE_TYP("INVALID_FILE_TYP"),
    INVALID_FLD("INVALID_FLD"),
    INVALID_FLDR_SIZE("INVALID_FLDR_SIZE"),
    INVALID_FLD_RANGE("INVALID_FLD_RANGE"),
    INVALID_FLD_TYP("INVALID_FLD_TYP"),
    INVALID_FLD_VALUE("INVALID_FLD_VALUE"),
    INVALID_FORMAT_IN_PARAM_FIELD("INVALID_FORMAT_IN_PARAM_FIELD"),
    INVALID_FORMULA("INVALID_FORMULA"),
    INVALID_FORMULA_FIELD("INVALID_FORMULA_FIELD"),
    INVALID_FROM_DATE("INVALID_FROM_DATE"),
    INVALID_FROM_TIME("INVALID_FROM_TIME"),
    INVALID_FULFILMNT_ITEM("INVALID_FULFILMNT_ITEM"),
    INVALID_FX_BASE_CURRENCY("INVALID_FX_BASE_CURRENCY"),
    INVALID_FX_RATE("INVALID_FX_RATE"),
    INVALID_GET_REF("INVALID_GET_REF"),
    INVALID_GIFT_CERT("INVALID_GIFT_CERT"),
    INVALID_GIFT_CERT_AMT("INVALID_GIFT_CERT_AMT"),
    INVALID_GIFT_CERT_CODE("INVALID_GIFT_CERT_CODE"),
    INVALID_GOOGLE_CHECKOUT_ACCT("INVALID_GOOGLE_CHECKOUT_ACCT"),
    INVALID_GROUP_TYP("INVALID_GROUP_TYP"),
    INVALID_GROUP_TYPE("INVALID_GROUP_TYPE"),
    INVALID_GRP("INVALID_GRP"),
    INVALID_GST_PST_AGENCIES("INVALID_GST_PST_AGENCIES"),
    INVALID_ID("INVALID_ID"),
    INVALID_INITIALIZE_ARG("INVALID_INITIALIZE_ARG"),
    INVALID_INITIALIZE_AUXREF("INVALID_INITIALIZE_AUXREF"),
    INVALID_INITIALIZE_REF("INVALID_INITIALIZE_REF"),
    INVALID_INSURED_VALUE("INVALID_INSURED_VALUE"),
    INVALID_INTERNALID("INVALID_INTERNALID"),
    INVALID_INVENTORY_NUM("INVALID_INVENTORY_NUM"),
    INVALID_INV_DATE("INVALID_INV_DATE"),
    INVALID_IP_ADDRESS_RULE("INVALID_IP_ADDRESS_RULE"),
    INVALID_ISSUE_BUILD_VERSION("INVALID_ISSUE_BUILD_VERSION"),
    INVALID_ISSUE_PRIORITY("INVALID_ISSUE_PRIORITY"),
    INVALID_ISSUE_PRODUCT("INVALID_ISSUE_PRODUCT"),
    INVALID_ISSUE_STATUS("INVALID_ISSUE_STATUS"),
    INVALID_ITEM_OPTION("INVALID_ITEM_OPTION"),
    INVALID_ITEM_OPTIONS("INVALID_ITEM_OPTIONS"),
    INVALID_ITEM_SUBTYP("INVALID_ITEM_SUBTYP"),
    INVALID_ITEM_TYP("INVALID_ITEM_TYP"),
    INVALID_ITEM_WEIGHT("INVALID_ITEM_WEIGHT"),
    INVALID_JOB_ID("INVALID_JOB_ID"),
    INVALID_KEY_OR_REF("INVALID_KEY_OR_REF"),
    INVALID_KEY_PASSWORD_REQD("INVALID_KEY_PASSWORD_REQD"),
    INVALID_LINE_ID("INVALID_LINE_ID"),
    INVALID_LINK_SUM("INVALID_LINK_SUM"),
    INVALID_LIST_ID("INVALID_LIST_ID"),
    INVALID_LIST_KEY("INVALID_LIST_KEY"),
    INVALID_LOC("INVALID_LOC"),
    INVALID_LOC_SUB_RESTRICTN("INVALID_LOC_SUB_RESTRICTN"),
    INVALID_LOGIN("INVALID_LOGIN"),
    INVALID_LOGIN_ATTEMPT("INVALID_LOGIN_ATTEMPT"),
    INVALID_LOGIN_CREDENTIALS("INVALID_LOGIN_CREDENTIALS"),
    INVALID_LOGIN_IP("INVALID_LOGIN_IP"),
    INVALID_LOT_NUM_FORMAT("INVALID_LOT_NUM_FORMAT"),
    INVALID_MARKUP_DISCOUNT("INVALID_MARKUP_DISCOUNT"),
    INVALID_MCC("INVALID_MCC"),
    INVALID_MEMBER_HIERARCHY("INVALID_MEMBER_HIERARCHY"),
    INVALID_MEMRZD_TRANS("INVALID_MEMRZD_TRANS"),
    INVALID_MERCHANT_KEY("INVALID_MERCHANT_KEY"),
    INVALID_MERCHANT_NAME("INVALID_MERCHANT_NAME"),
    INVALID_NAME("INVALID_NAME"),
    INVALID_NEXUS("INVALID_NEXUS"),
    INVALID_NUM("INVALID_NUM"),
    INVALID_NUMBER("INVALID_NUMBER"),
    INVALID_OBJ("INVALID_OBJ"),
    INVALID_ONLINE_FORM("INVALID_ONLINE_FORM"),
    INVALID_ONLINE_FORM_URL("INVALID_ONLINE_FORM_URL"),
    INVALID_OPENID_DOMAIN("INVALID_OPENID_DOMAIN"),
    INVALID_OPERATION("INVALID_OPERATION"),
    INVALID_ORD_STATUS("INVALID_ORD_STATUS"),
    INVALID_ORIGIN_COUNTRY("INVALID_ORIGIN_COUNTRY"),
    INVALID_ORIGIN_POSTCODE("INVALID_ORIGIN_POSTCODE"),
    INVALID_ORIGIN_STATE("INVALID_ORIGIN_STATE"),
    INVALID_PAGER_NUM("INVALID_PAGER_NUM"),
    INVALID_PAGE_INDEX("INVALID_PAGE_INDEX"),
    INVALID_PAGE_PARAM("INVALID_PAGE_PARAM"),
    INVALID_PARAM("INVALID_PARAM"),
    INVALID_PARENT("INVALID_PARENT"),
    INVALID_PARTNER_CODE("INVALID_PARTNER_CODE"),
    INVALID_PARTNER_ID("INVALID_PARTNER_ID"),
    INVALID_PASSWORD("INVALID_PASSWORD"),
    INVALID_PAYCHECK_DATE("INVALID_PAYCHECK_DATE"),
    INVALID_PERIOD("INVALID_PERIOD"),
    INVALID_PHONE("INVALID_PHONE"),
    INVALID_PHONE_FAX_PAGER_NUM("INVALID_PHONE_FAX_PAGER_NUM"),
    INVALID_PHONE_NUM("INVALID_PHONE_NUM"),
    INVALID_PHONE_NUMBER("INVALID_PHONE_NUMBER"),
    INVALID_PICKUP_POSTAL_CODE("INVALID_PICKUP_POSTAL_CODE"),
    INVALID_PIN("INVALID_PIN"),
    INVALID_PIN_DEBIT_TRANS_TYP("INVALID_PIN_DEBIT_TRANS_TYP"),
    INVALID_PORTLET_TYP("INVALID_PORTLET_TYP"),
    INVALID_POST("INVALID_POST"),
    INVALID_PRESENTATION_TYP("INVALID_PRESENTATION_TYP"),
    INVALID_PROBABILITY_RANGE("INVALID_PROBABILITY_RANGE"),
    INVALID_PROFILE_ID("INVALID_PROFILE_ID"),
    INVALID_PROJ_BILLING_TYP("INVALID_PROJ_BILLING_TYP"),
    INVALID_PST_TAX_VALUE("INVALID_PST_TAX_VALUE"),
    INVALID_PSWD("INVALID_PSWD"),
    INVALID_PSWD_HINT("INVALID_PSWD_HINT"),
    INVALID_PSWD_ILLEGAL_CHAR("INVALID_PSWD_ILLEGAL_CHAR"),
    INVALID_PURCHASE_TAX_CODE("INVALID_PURCHASE_TAX_CODE"),
    INVALID_QTY("INVALID_QTY"),
    INVALID_QUANTITY("INVALID_QUANTITY"),
    INVALID_QUESTION("INVALID_QUESTION"),
    INVALID_RCRD("INVALID_RCRD"),
    INVALID_RCRD_CONVERSION("INVALID_RCRD_CONVERSION"),
    @XmlEnumValue("INVALID_RCRD_HEADER_")
    INVALID_RCRD_HEADER("INVALID_RCRD_HEADER_"),
    INVALID_RCRD_ID("INVALID_RCRD_ID"),
    INVALID_RCRD_INITIALIZE("INVALID_RCRD_INITIALIZE"),
    INVALID_RCRD_OBJ("INVALID_RCRD_OBJ"),
    INVALID_RCRD_REF("INVALID_RCRD_REF"),
    INVALID_RCRD_TRANSFRM("INVALID_RCRD_TRANSFRM"),
    INVALID_RCRD_TYPE("INVALID_RCRD_TYPE"),
    INVALID_RECIPIENT("INVALID_RECIPIENT"),
    INVALID_RECR_REF("INVALID_RECR_REF"),
    INVALID_RECUR_DATE_RANGE("INVALID_RECUR_DATE_RANGE"),
    INVALID_RECUR_DESC_REQD("INVALID_RECUR_DESC_REQD"),
    INVALID_RECUR_DOW("INVALID_RECUR_DOW"),
    INVALID_RECUR_DOWIM("INVALID_RECUR_DOWIM"),
    INVALID_RECUR_DOWMASK("INVALID_RECUR_DOWMASK"),
    INVALID_RECUR_FREQUENCY("INVALID_RECUR_FREQUENCY"),
    INVALID_RECUR_PATTERN("INVALID_RECUR_PATTERN"),
    INVALID_RECUR_PERIOD("INVALID_RECUR_PERIOD"),
    INVALID_RECUR_TIME_ZONE_REQD("INVALID_RECUR_TIME_ZONE_REQD"),
    INVALID_REFFERER_EMAIL("INVALID_REFFERER_EMAIL"),
    INVALID_REFUND_AMT("INVALID_REFUND_AMT"),
    INVALID_REF_CANT_INITIALIZE("INVALID_REF_CANT_INITIALIZE"),
    INVALID_REF_KEY("INVALID_REF_KEY"),
    INVALID_REPORT("INVALID_REPORT"),
    INVALID_REPORT_ID("INVALID_REPORT_ID"),
    INVALID_REPORT_ROW("INVALID_REPORT_ROW"),
    INVALID_REQUEST("INVALID_REQUEST"),
    INVALID_RESOURCE_TIME("INVALID_RESOURCE_TIME"),
    INVALID_RESULT_SUMMARY_FUNC("INVALID_RESULT_SUMMARY_FUNC"),
    INVALID_RETURN_DATA_OBJECT("INVALID_RETURN_DATA_OBJECT"),
    INVALID_REV_REC_DATE_RANGE("INVALID_REV_REC_DATE_RANGE"),
    INVALID_ROLE("INVALID_ROLE"),
    INVALID_ROLE_FOR_EVENT("INVALID_ROLE_FOR_EVENT"),
    INVALID_RQST_CONTACTS_EXIST("INVALID_RQST_CONTACTS_EXIST"),
    INVALID_RQST_PARENT_REQD("INVALID_RQST_PARENT_REQD"),
    INVALID_RQST_SBCUST_JOBS_EXIST("INVALID_RQST_SBCUST_JOBS_EXIST"),
    INVALID_SAVEDSEARCH("INVALID_SAVEDSEARCH"),
    INVALID_SAVED_SRCH("INVALID_SAVED_SRCH"),
    INVALID_SCHDUL_AMT("INVALID_SCHDUL_AMT"),
    INVALID_SCHDUL_FORMAT("INVALID_SCHDUL_FORMAT"),
    INVALID_SCRIPT_ID("INVALID_SCRIPT_ID"),
    INVALID_SEARCH("INVALID_SEARCH"),
    INVALID_SEARCH_CRITERIA("INVALID_SEARCH_CRITERIA"),
    INVALID_SEARCH_FIELD_KEY("INVALID_SEARCH_FIELD_KEY"),
    INVALID_SEARCH_FIELD_NAME("INVALID_SEARCH_FIELD_NAME"),
    INVALID_SEARCH_FIELD_OBJ("INVALID_SEARCH_FIELD_OBJ"),
    INVALID_SEARCH_JOIN_ID("INVALID_SEARCH_JOIN_ID"),
    INVALID_SEARCH_MORE("INVALID_SEARCH_MORE"),
    INVALID_SEARCH_OPERATOR("INVALID_SEARCH_OPERATOR"),
    INVALID_SEARCH_PAGE_INDEX("INVALID_SEARCH_PAGE_INDEX"),
    INVALID_SEARCH_PAGE_SIZE("INVALID_SEARCH_PAGE_SIZE"),
    INVALID_SEARCH_PREF("INVALID_SEARCH_PREF"),
    INVALID_SEARCH_SELECT_OBJ("INVALID_SEARCH_SELECT_OBJ"),
    INVALID_SEARCH_VALUE("INVALID_SEARCH_VALUE"),
    INVALID_SECONDARY_EMAIL("INVALID_SECONDARY_EMAIL"),
    INVALID_SECPAY_CREDENTIALS("INVALID_SECPAY_CREDENTIALS"),
    INVALID_SERIAL_NUM("INVALID_SERIAL_NUM"),
    INVALID_SERIAL_OR_LOT_NUMBER("INVALID_SERIAL_OR_LOT_NUMBER"),
    INVALID_SERVICE_CODE("INVALID_SERVICE_CODE"),
    INVALID_SESSION("INVALID_SESSION"),
    INVALID_SHIPPER_STATE("INVALID_SHIPPER_STATE"),
    INVALID_SHIP_DATE("INVALID_SHIP_DATE"),
    INVALID_SHIP_FROM_STATE("INVALID_SHIP_FROM_STATE"),
    INVALID_SHIP_GRP("INVALID_SHIP_GRP"),
    INVALID_SHIP_SRVC("INVALID_SHIP_SRVC"),
    INVALID_SHIP_TO_SATE("INVALID_SHIP_TO_SATE"),
    INVALID_SITE_CSTM_FILE("INVALID_SITE_CSTM_FILE"),
    INVALID_SOAP_HEADER("INVALID_SOAP_HEADER"),
    INVALID_SRCH("INVALID_SRCH"),
    INVALID_SRCH_CRITERIA("INVALID_SRCH_CRITERIA"),
    INVALID_SRCH_CSTM_FLD("INVALID_SRCH_CSTM_FLD"),
    INVALID_SRCH_FUNCTN("INVALID_SRCH_FUNCTN"),
    INVALID_SRCH_SORT("INVALID_SRCH_SORT"),
    INVALID_SRCH_SUMMARY_TYP("INVALID_SRCH_SUMMARY_TYP"),
    INVALID_SRCH_TYP("INVALID_SRCH_TYP"),
    INVALID_SRVC_ITEM_SUB("INVALID_SRVC_ITEM_SUB"),
    INVALID_SSO("INVALID_SSO"),
    INVALID_SSS_DEBUG_SESSION("INVALID_SSS_DEBUG_SESSION"),
    INVALID_STATE("INVALID_STATE"),
    INVALID_STATUS("INVALID_STATUS"),
    INVALID_SUB("INVALID_SUB"),
    INVALID_SUBLIST_DESC("INVALID_SUBLIST_DESC"),
    INVALID_SUBSCRIPTION_STATUS("INVALID_SUBSCRIPTION_STATUS"),
    INVALID_SUMMARY_SRCH("INVALID_SUMMARY_SRCH"),
    INVALID_SUPERVISOR("INVALID_SUPERVISOR"),
    INVALID_TASK_ID("INVALID_TASK_ID"),
    INVALID_TAX_AMT("INVALID_TAX_AMT"),
    INVALID_TAX_CODE("INVALID_TAX_CODE"),
    INVALID_TAX_CODES("INVALID_TAX_CODES"),
    INVALID_TAX_CODE_FOR_SUB("INVALID_TAX_CODE_FOR_SUB"),
    INVALID_TAX_PMT("INVALID_TAX_PMT"),
    INVALID_TAX_VALUE("INVALID_TAX_VALUE"),
    INVALID_TIME_FORMAT("INVALID_TIME_FORMAT"),
    INVALID_TO_DATE("INVALID_TO_DATE"),
    INVALID_TRACKING_NUM("INVALID_TRACKING_NUM"),
    INVALID_TRANS("INVALID_TRANS"),
    INVALID_TRANSACTION_DATE("INVALID_TRANSACTION_DATE"),
    INVALID_TRANSACTIO_DATE("INVALID_TRANSACTIO_DATE"),
    INVALID_TRANS_AMT("INVALID_TRANS_AMT"),
    INVALID_TRANS_COMPNT("INVALID_TRANS_COMPNT"),
    INVALID_TRANS_ID("INVALID_TRANS_ID"),
    INVALID_TRANS_SUB_ACCT("INVALID_TRANS_SUB_ACCT"),
    INVALID_TRANS_SUB_CLASS("INVALID_TRANS_SUB_CLASS"),
    INVALID_TRANS_SUB_DEPT("INVALID_TRANS_SUB_DEPT"),
    INVALID_TRANS_SUB_ENTITY("INVALID_TRANS_SUB_ENTITY"),
    INVALID_TRANS_SUB_ITEM("INVALID_TRANS_SUB_ITEM"),
    INVALID_TRANS_SUB_LOC("INVALID_TRANS_SUB_LOC"),
    INVALID_TRANS_TYP("INVALID_TRANS_TYP"),
    INVALID_TRAN_ITEM_LINE("INVALID_TRAN_ITEM_LINE"),
    INVALID_TRIAL_TYP("INVALID_TRIAL_TYP"),
    INVALID_TYP("INVALID_TYP"),
    INVALID_UNIT_TYP("INVALID_UNIT_TYP"),
    INVALID_UNSUPRTD_RCRD_TYP("INVALID_UNSUPRTD_RCRD_TYP"),
    INVALID_UPS_ACCT("INVALID_UPS_ACCT"),
    INVALID_UPS_PACKG_WEIGHT("INVALID_UPS_PACKG_WEIGHT"),
    INVALID_UPS_VALUES("INVALID_UPS_VALUES"),
    INVALID_URL("INVALID_URL"),
    INVALID_URL_PARAM("INVALID_URL_PARAM"),
    INVALID_VALUE("INVALID_VALUE"),
    INVALID_VAT_AMOUNT("INVALID_VAT_AMOUNT"),
    INVALID_VAT_REGSTRTN_NUM("INVALID_VAT_REGSTRTN_NUM"),
    INVALID_VSOE_ALLOCTN("INVALID_VSOE_ALLOCTN"),
    INVALID_WEBSITE_SECTION("INVALID_WEBSITE_SECTION"),
    INVALID_WO("INVALID_WO"),
    INVALID_WORLDPAY_ID("INVALID_WORLDPAY_ID"),
    INVALID_WO_ITEM("INVALID_WO_ITEM"),
    INVALID_WS_VERSION("INVALID_WS_VERSION"),
    INVALID_YEAR("INVALID_YEAR"),
    INVALID_YEAR_FORMAT("INVALID_YEAR_FORMAT"),
    INVALID_ZIP_CODE("INVALID_ZIP_CODE"),
    INVALID_ZIP_FILE("INVALID_ZIP_FILE"),
    INVALID_ZIP_POST_CODE("INVALID_ZIP_POST_CODE"),
    INVENTORY_NUM_DISALLWD("INVENTORY_NUM_DISALLWD"),
    INVLAID_BOOLEAN_VALUE("INVLAID_BOOLEAN_VALUE"),
    IP_REQUEST("IP_REQUEST"),
    ISSUE_ASSIGNEE_DISALLWD("ISSUE_ASSIGNEE_DISALLWD"),
    ISSUE_PRODUCT_VERSION_MISMATCH("ISSUE_PRODUCT_VERSION_MISMATCH"),
    ISSUE_VERSION_BUILD_MISMATCH("ISSUE_VERSION_BUILD_MISMATCH"),
    ITEM_ACCT_REQD("ITEM_ACCT_REQD"),
    ITEM_COUNT_MISMATCH("ITEM_COUNT_MISMATCH"),
    ITEM_IS_UNAVAILABLE("ITEM_IS_UNAVAILABLE"),
    ITEM_NAME_MUST_BE_UNIQUE("ITEM_NAME_MUST_BE_UNIQUE"),
    ITEM_NOT_UNIQUE("ITEM_NOT_UNIQUE"),
    ITEM_PARAM_REQD_IN_URL("ITEM_PARAM_REQD_IN_URL"),
    ITEM_QTY_AMT_MISMATCH("ITEM_QTY_AMT_MISMATCH"),
    ITEM_TYP_REQS_UNIT("ITEM_TYP_REQS_UNIT"),
    JE_AMOUNTS_MUST_BALANCE("JE_AMOUNTS_MUST_BALANCE"),
    JE_LINE_MISSING_REQD_DATA("JE_LINE_MISSING_REQD_DATA"),
    JE_MAX_ONE_LINE("JE_MAX_ONE_LINE"),
    JE_REV_REC_IN_PROGRESS("JE_REV_REC_IN_PROGRESS"),
    JE_UNEXPECTED_ERROR("JE_UNEXPECTED_ERROR"),
    JOB_NOT_COMPLETE("JOB_NOT_COMPLETE"),
    JS_EXCEPTION("JS_EXCEPTION"),
    KEY_REQD("KEY_REQD"),
    KPI_SETUP_REQD("KPI_SETUP_REQD"),
    LABEL_REQD("LABEL_REQD"),
    LANGUAGE_SETUP_REQD("LANGUAGE_SETUP_REQD"),
    LINKED_ACCT_DONT_MATCH("LINKED_ACCT_DONT_MATCH"),
    LINKED_ENTITIES_DONT_MATCH("LINKED_ENTITIES_DONT_MATCH"),
    LINKED_ITEMS_DONT_MATCH("LINKED_ITEMS_DONT_MATCH"),
    LINK_LINES_TO_ONE_ORD("LINK_LINES_TO_ONE_ORD"),
    LIST_ID_REQD("LIST_ID_REQD"),
    LIST_KEY_REQD("LIST_KEY_REQD"),
    LOCATIONS_IN_USE("LOCATIONS_IN_USE"),
    LOCATIONS_SETUP_REQD("LOCATIONS_SETUP_REQD"),
    LOCATION_REQD("LOCATION_REQD"),
    LOCKED_DASHBOARD("LOCKED_DASHBOARD"),
    LOGIN_DISABLED("LOGIN_DISABLED"),
    LOGIN_DISABLED_PARTNER_CTR("LOGIN_DISABLED_PARTNER_CTR"),
    LOGIN_EMAIL_REQD("LOGIN_EMAIL_REQD"),
    LOGIN_NAME_AND_PSWD_REQD("LOGIN_NAME_AND_PSWD_REQD"),
    LOGIN_REQD("LOGIN_REQD"),
    LOST_UPSELL_CRITERIA("LOST_UPSELL_CRITERIA"),
    MACHN_LIST_KEY_NAMES_REQD("MACHN_LIST_KEY_NAMES_REQD"),
    MANDATORY_PRD_TYPE_REQD("MANDATORY_PRD_TYPE_REQD"),
    MASS_UPDATE_CRITERIA_LOST("MASS_UPDATE_CRITERIA_LOST"),
    MATCHING_CUR_SUB_REQD("MATCHING_CUR_SUB_REQD"),
    MATCHING_SERIAL_NUM_REQD("MATCHING_SERIAL_NUM_REQD"),
    MATRIX_INFO_TEMP_LOST("MATRIX_INFO_TEMP_LOST"),
    MATRIX_SUBITEM_NAME_TOO_LONG("MATRIX_SUBITEM_NAME_TOO_LONG"),
    MAX_16_LINES_ALLWD_PER_BILLPAY("MAX_16_LINES_ALLWD_PER_BILLPAY"),
    MAX_200_LINES_ALLWD_ON_TRANS("MAX_200_LINES_ALLWD_ON_TRANS"),
    MAX_BARCODE_PRINT_EXCEEDED("MAX_BARCODE_PRINT_EXCEEDED"),
    MAX_BULK_MERGE_RCRDS_EXCEEDED("MAX_BULK_MERGE_RCRDS_EXCEEDED"),
    MAX_EMAILS_EXCEEDED("MAX_EMAILS_EXCEEDED"),
    MAX_RCRDS_EXCEEDED("MAX_RCRDS_EXCEEDED"),
    MAX_VALUES_EXCEEDED("MAX_VALUES_EXCEEDED"),
    MEDIA_FILE_INVALID_JSCRIPT("MEDIA_FILE_INVALID_JSCRIPT"),
    MEDIA_NOT_FOUND("MEDIA_NOT_FOUND"),
    MEDIA_NOT_INITIALIZED("MEDIA_NOT_INITIALIZED"),
    MEMORIZED_TRANS_ERROR("MEMORIZED_TRANS_ERROR"),
    MERGE_OPERATION_DISALLWD("MERGE_OPERATION_DISALLWD"),
    MERGE_RCRD_REQD("MERGE_RCRD_REQD"),
    METAVANTE_ERROR("METAVANTE_ERROR"),
    METAVANTE_SECRET_ANSWER_REQD("METAVANTE_SECRET_ANSWER_REQD"),
    METAVANTE_SECRET_QESTION_REQD("METAVANTE_SECRET_QESTION_REQD"),
    METAVANTE_SETUP_REQD("METAVANTE_SETUP_REQD"),
    METAVANTE_TEMP_UNAVAILBL("METAVANTE_TEMP_UNAVAILBL"),
    MISMATCHED_CURRENCY("MISMATCHED_CURRENCY"),
    MISMATCHED_QTY_PRICING("MISMATCHED_QTY_PRICING"),
    MISMATCHED_SEARCH_PARENTHESIS("MISMATCHED_SEARCH_PARENTHESIS"),
    MISMATCH_EVENT_ISSUE_STATUS("MISMATCH_EVENT_ISSUE_STATUS"),
    MISMATCH_ISSUE_PRODUCT_VERSION("MISMATCH_ISSUE_PRODUCT_VERSION"),
    MISMATCH_SALES_CONTRIBUTION("MISMATCH_SALES_CONTRIBUTION"),
    MISSING_ACCT_PRD("MISSING_ACCT_PRD"),
    MISSING_CRNCY_EXCH_RATE("MISSING_CRNCY_EXCH_RATE"),
    MISSING_ENUM("MISSING_ENUM"),
    MISSING_REQD_FLD("MISSING_REQD_FLD"),
    MISSNG_ACCT_PRD("MISSNG_ACCT_PRD"),
    MISSNG_REV_REC_RCRD("MISSNG_REV_REC_RCRD"),
    MISSNG_SO_REV_REC_PARAMS("MISSNG_SO_REV_REC_PARAMS"),
    MISSNG_SO_START_END_DATES("MISSNG_SO_START_END_DATES"),
    MLI_REQD("MLI_REQD"),
    MLTPLE_TAX_LINES_DISALLWD("MLTPLE_TAX_LINES_DISALLWD"),
    MSNG_FIELD_OWRTE_MUST_BE_TRUE("MSNG_FIELD_OWRTE_MUST_BE_TRUE"),
    MST_UPDATE_ITEMS_THEN_RATES("MST_UPDATE_ITEMS_THEN_RATES"),
    MULTISELECT_TYPE_REQD("MULTISELECT_TYPE_REQD"),
    MULTI_ACCT_CANT_CHANGE_PSWD("MULTI_ACCT_CANT_CHANGE_PSWD"),
    MULTI_LOC_INVT_ERROR("MULTI_LOC_INVT_ERROR"),
    MULTI_PRIMARY_PARTNER_DISALLWD("MULTI_PRIMARY_PARTNER_DISALLWD"),
    MULTI_SHIP_ROUTES_REQD("MULTI_SHIP_ROUTES_REQD"),
    MUST_DEFINE_BASE_UNIT("MUST_DEFINE_BASE_UNIT"),
    MUST_RESUBMIT_RCRD("MUST_RESUBMIT_RCRD"),
    NAME_ALREADY_IN_USE("NAME_ALREADY_IN_USE"),
    NAME_REQD("NAME_REQD"),
    NAME_TYPE_FLDR_FIELDS_REQD("NAME_TYPE_FLDR_FIELDS_REQD"),
    NARROW_KEYWORD_SEARCH("NARROW_KEYWORD_SEARCH"),
    NEED_BILL_VARIANCE_ACCT("NEED_BILL_VARIANCE_ACCT"),
    NEGATIVE_PAYMENT_DISALLWD("NEGATIVE_PAYMENT_DISALLWD"),
    NEGATIVE_TAX_RATE_DISALLWD("NEGATIVE_TAX_RATE_DISALLWD"),
    NEW_CONNECTION_DISALLWD("NEW_CONNECTION_DISALLWD"),
    NEXUS_REQD("NEXUS_REQD"),
    NONMATCHING_EMAILS("NONMATCHING_EMAILS"),
    NONUNIQUE_INDEX_VALUE("NONUNIQUE_INDEX_VALUE"),
    NONZERO_AMT_REQD("NONZERO_AMT_REQD"),
    NONZERO_QTY_REQD("NONZERO_QTY_REQD"),
    NONZERO_WEIGHT_REQD("NONZERO_WEIGHT_REQD"),
    NON_ADMIN_CANNOT_INITIATE_LINK("NON_ADMIN_CANNOT_INITIATE_LINK"),
    NOT_AN_INVITEE("NOT_AN_INVITEE"),
    NOT_IN_INVT("NOT_IN_INVT"),
    NO_DATA_FOUND("NO_DATA_FOUND"),
    NO_EXPENSES_FOR_PRD("NO_EXPENSES_FOR_PRD"),
    NO_ITEMS_TO_PRINT("NO_ITEMS_TO_PRINT"),
    NO_MASS_UPDATES_RUNNING("NO_MASS_UPDATES_RUNNING"),
    NO_MTRX_ITEMS_TO_UPDATE("NO_MTRX_ITEMS_TO_UPDATE"),
    NO_ORD_SHPMNT("NO_ORD_SHPMNT"),
    NO_RCRDS_MATCH("NO_RCRDS_MATCH"),
    NO_RCRD_FOR_USER("NO_RCRD_FOR_USER"),
    NO_SCHDUL_APPLIED("NO_SCHDUL_APPLIED"),
    NO_UPSERT("NO_UPSERT"),
    NULL_CHECK_NUMBER("NULL_CHECK_NUMBER"),
    NUMERIC_CHECK_NUM_REQD("NUMERIC_CHECK_NUM_REQD"),
    NUMERIC_REF_NUM_REQD("NUMERIC_REF_NUM_REQD"),
    NUM_ITEMS_GRTR_THAN_QTY("NUM_ITEMS_GRTR_THAN_QTY"),
    NUM_ITEMS_NOT_EQUAL_TO_QTY("NUM_ITEMS_NOT_EQUAL_TO_QTY"),
    NUM_REQD_FOR_FIRST_LABEL("NUM_REQD_FOR_FIRST_LABEL"),
    OI_FEATURE_REQD("OI_FEATURE_REQD"),
    OI_PERMISSION_REQD("OI_PERMISSION_REQD"),
    ONE_ADMIN_REQD_PER_ACCT("ONE_ADMIN_REQD_PER_ACCT"),
    ONE_DIRECT_DEPOSIT_ACT_ALLWD("ONE_DIRECT_DEPOSIT_ACT_ALLWD"),
    ONE_EMPL_REQD("ONE_EMPL_REQD"),
    ONE_PAY_ITEM_PER_EMPL("ONE_PAY_ITEM_PER_EMPL"),
    ONE_POSITIVE_VALUE_REQD("ONE_POSITIVE_VALUE_REQD"),
    ONE_RCRD_REQD_FOR_MASS_UPDATE("ONE_RCRD_REQD_FOR_MASS_UPDATE"),
    ONE_ROLE_REQD("ONE_ROLE_REQD"),
    ONLINE_BANK_FILE_REQD("ONLINE_BANK_FILE_REQD"),
    ONLINE_BILL_PAY_DUP("ONLINE_BILL_PAY_DUP"),
    ONLINE_BILL_PAY_SETUP_REQD("ONLINE_BILL_PAY_SETUP_REQD"),
    ONLINE_FORM_DSNT_EXIST("ONLINE_FORM_DSNT_EXIST"),
    ONLINE_FORM_EMPTY("ONLINE_FORM_EMPTY"),
    ONLINE_FORM_ID_REQD("ONLINE_FORM_ID_REQD"),
    ONLINE_FORM_USER_ACCESS_ONLY("ONLINE_FORM_USER_ACCESS_ONLY"),
    ONLINE_ORD_FEATURE_DISABLED("ONLINE_ORD_FEATURE_DISABLED"),
    ONLY_ONE_CONTRIB_ITEM_REQD("ONLY_ONE_CONTRIB_ITEM_REQD"),
    ONLY_ONE_DEDCT_ITEM_REQD("ONLY_ONE_DEDCT_ITEM_REQD"),
    ONLY_ONE_DISTRIB_ALLWD("ONLY_ONE_DISTRIB_ALLWD"),
    ONLY_ONE_EARNING_ITEM_REQD("ONLY_ONE_EARNING_ITEM_REQD"),
    ONLY_ONE_LOT_NUM_ALLWD("ONLY_ONE_LOT_NUM_ALLWD"),
    ONLY_ONE_PREF_BIN_ALLWD("ONLY_ONE_PREF_BIN_ALLWD"),
    ONLY_ONE_UNIT_AS_BASE_UNIT("ONLY_ONE_UNIT_AS_BASE_UNIT"),
    ONLY_ONE_UPLOAD_ALLWD("ONLY_ONE_UPLOAD_ALLWD"),
    ONLY_ONE_WITHLD_ITEM_REQD("ONLY_ONE_WITHLD_ITEM_REQD"),
    OPENID_DOMAIN_IN_USE("OPENID_DOMAIN_IN_USE"),
    OPENID_NOT_ENABLED("OPENID_NOT_ENABLED"),
    OPRTN_UNAVAILBL_TO_GATEWAY("OPRTN_UNAVAILBL_TO_GATEWAY"),
    ORDER_DSNT_EXIST("ORDER_DSNT_EXIST"),
    ORD_ALREADY_APPRVD("ORD_ALREADY_APPRVD"),
    ORD_UNAVAILBL_TO_FULFILL("ORD_UNAVAILBL_TO_FULFILL"),
    OTHER_PMT_AUTH_IN_PROGRESS("OTHER_PMT_AUTH_IN_PROGRESS"),
    OVERAGE_DISALLWD("OVERAGE_DISALLWD"),
    OVERLAPPING_PRDS_DISALLWD("OVERLAPPING_PRDS_DISALLWD"),
    OVER_FULFILL_DISALLWD("OVER_FULFILL_DISALLWD"),
    OVER_FULFILL_RECEIV_DISALLWD("OVER_FULFILL_RECEIV_DISALLWD"),
    OWNER_REQD("OWNER_REQD"),
    PACKAGE_WEIGHT_REQD("PACKAGE_WEIGHT_REQD"),
    PACKG_LEVEL_REF_DISALLWD("PACKG_LEVEL_REF_DISALLWD"),
    PACKG_VALUE_TOO_LARGE("PACKG_VALUE_TOO_LARGE"),
    PARENT_CANT_ITSELF_BE_MEMBER("PARENT_CANT_ITSELF_BE_MEMBER"),
    PARENT_MUST_BE_MATRIX_ITEM("PARENT_MUST_BE_MATRIX_ITEM"),
    PARENT_REQD("PARENT_REQD"),
    PARSING_ERROR("PARSING_ERROR"),
    PARTIAL_FULFILL_RCEIV_DISALLWD("PARTIAL_FULFILL_RCEIV_DISALLWD"),
    PARTNER_ACCESS_DENIED("PARTNER_ACCESS_DENIED"),
    PARTNER_ACCT_NOT_LINKED("PARTNER_ACCT_NOT_LINKED"),
    PARTNER_CODE_ALREADY_USED("PARTNER_CODE_ALREADY_USED"),
    PAYCHECK_ALREADY_PAID("PAYCHECK_ALREADY_PAID"),
    PAYCHECK_IN_USE("PAYCHECK_IN_USE"),
    PAYEE_REQD_FOR_PMT("PAYEE_REQD_FOR_PMT"),
    PAYPAL_FUND_SOURCE_REQD("PAYPAL_FUND_SOURCE_REQD"),
    PAYPAL_INVALID_PMT_METHOD("PAYPAL_INVALID_PMT_METHOD"),
    PAYPAL_PMT_NOTIFICATION("PAYPAL_PMT_NOTIFICATION"),
    PAYPAL_SETUP_REQD("PAYPAL_SETUP_REQD"),
    PAYROLL_COMMITTED("PAYROLL_COMMITTED"),
    PAYROLL_EXPENSE_ACCT_REQD("PAYROLL_EXPENSE_ACCT_REQD"),
    PAYROLL_FEATURE_DISABLED("PAYROLL_FEATURE_DISABLED"),
    PAYROLL_FEATURE_UNAVAILABLE("PAYROLL_FEATURE_UNAVAILABLE"),
    PAYROLL_FUND_ACCT_REQD("PAYROLL_FUND_ACCT_REQD"),
    PAYROLL_IN_PROCESS("PAYROLL_IN_PROCESS"),
    PAYROLL_ITEM_DELETE_DISALLWD("PAYROLL_ITEM_DELETE_DISALLWD"),
    PAYROLL_LIABILITY_ACCT_REQD("PAYROLL_LIABILITY_ACCT_REQD"),
    PAYROLL_MAINTENANCE("PAYROLL_MAINTENANCE"),
    PAYROLL_SETUP_REQD("PAYROLL_SETUP_REQD"),
    PAYROLL_UPDATE_REQD("PAYROLL_UPDATE_REQD"),
    PAY_HOLD_ON_SO("PAY_HOLD_ON_SO"),
    PERMISSION_VIOLATION("PERMISSION_VIOLATION"),
    PHONE_NUM_REQD("PHONE_NUM_REQD"),
    PIN_DEBIT_TRANS_DISALLWD("PIN_DEBIT_TRANS_DISALLWD"),
    PLAN_IN_USE("PLAN_IN_USE"),
    PLAN_OVERLAP_DISALLWD("PLAN_OVERLAP_DISALLWD"),
    PMT_ALREADY_APPRVD("PMT_ALREADY_APPRVD"),
    PMT_ALREADY_EXISTS("PMT_ALREADY_EXISTS"),
    PMT_ALREADY_SBMTD("PMT_ALREADY_SBMTD"),
    PMT_EDIT_DISALLWD("PMT_EDIT_DISALLWD"),
    POSITIVE_BIN_QTY_REQD("POSITIVE_BIN_QTY_REQD"),
    POSITIVE_QTY_REQD("POSITIVE_QTY_REQD"),
    POSITIVE_UNITCOST_REQD("POSITIVE_UNITCOST_REQD"),
    POSTING_DISCOUNT_DISALLWD("POSTING_DISCOUNT_DISALLWD"),
    POSTING_PRD_SETUP_REQD("POSTING_PRD_SETUP_REQD"),
    PRDS_DISALLWD_NAMES_NOT_UNIQUE("PRDS_DISALLWD_NAMES_NOT_UNIQUE"),
    PRD_SETUP_REQD("PRD_SETUP_REQD"),
    PREFERRED_TAX_AGENCY_REQD("PREFERRED_TAX_AGENCY_REQD"),
    PREF_VENDOR_COST_REQD("PREF_VENDOR_COST_REQD"),
    PREF_VENDOR_REQD("PREF_VENDOR_REQD"),
    PRIVATE_RCRD_ACCESS_DISALLWD("PRIVATE_RCRD_ACCESS_DISALLWD"),
    PRIVATE_STATUS_CHNG_DISALLWD("PRIVATE_STATUS_CHNG_DISALLWD"),
    PRODUCT_MODULE_MISMATCH("PRODUCT_MODULE_MISMATCH"),
    PSWD_EXPIRED("PSWD_EXPIRED"),
    PSWD_REQD("PSWD_REQD"),
    PWSDS_DONT_MATCH("PWSDS_DONT_MATCH"),
    QTY_EXCEEDED_QTY_BUCKETS("QTY_EXCEEDED_QTY_BUCKETS"),
    QTY_REQD("QTY_REQD"),
    RATE_REQUEST_SHPMNT_REQD("RATE_REQUEST_SHPMNT_REQD"),
    RATE_SRVC_UNAVAILBL("RATE_SRVC_UNAVAILBL"),
    RCRD_DELETED_SINCE_RETRIEVED("RCRD_DELETED_SINCE_RETRIEVED"),
    RCRD_DSNT_EXIST("RCRD_DSNT_EXIST"),
    RCRD_EDITED_SINCE_RETRIEVED("RCRD_EDITED_SINCE_RETRIEVED"),
    RCRD_HAS_BEEN_CHANGED("RCRD_HAS_BEEN_CHANGED"),
    RCRD_ID_NOT_INT("RCRD_ID_NOT_INT"),
    RCRD_LOCKED_BY_WF("RCRD_LOCKED_BY_WF"),
    RCRD_NOT_FOUND("RCRD_NOT_FOUND"),
    RCRD_PREVSLY_DELETED("RCRD_PREVSLY_DELETED"),
    RCRD_REF_RCRD_TYP_MISMATCH("RCRD_REF_RCRD_TYP_MISMATCH"),
    RCRD_SUB_MISMATCH_WITH_CLASS("RCRD_SUB_MISMATCH_WITH_CLASS"),
    RCRD_TYPE_REQD("RCRD_TYPE_REQD"),
    RCRD_UNEDITABLE("RCRD_UNEDITABLE"),
    REACHED_LIST_END("REACHED_LIST_END"),
    REACHED_LIST_START("REACHED_LIST_START"),
    RECALCING_PLAN_SCHDUL("RECALCING_PLAN_SCHDUL"),
    RECURSV_REF_DISALLWD("RECURSV_REF_DISALLWD"),
    RECUR_EVENT_DISALLWD("RECUR_EVENT_DISALLWD"),
    REC_TYP_REQD("REC_TYP_REQD"),
    REPORT_EXPIRED("REPORT_EXPIRED"),
    REQD_FORM_TAG_MISSING("REQD_FORM_TAG_MISSING"),
    REQD_LOC_FIELDS_MISSING("REQD_LOC_FIELDS_MISSING"),
    REQD_SUB_FIELDS_MISSING("REQD_SUB_FIELDS_MISSING"),
    REQUEST_PARAM_REQD("REQUEST_PARAM_REQD"),
    REVERSAL_DATE_WARNING("REVERSAL_DATE_WARNING"),
    REV_REC_DATE_REQD("REV_REC_DATE_REQD"),
    REV_REC_TMPLT_DATA_MISSING("REV_REC_TMPLT_DATA_MISSING"),
    REV_REC_UPDATE_DISALLWD("REV_REC_UPDATE_DISALLWD"),
    ROLE_REQD("ROLE_REQD"),
    ROLE_REQUIRED("ROLE_REQUIRED"),
    ROUNDING_DIFF_TOO_BIG("ROUNDING_DIFF_TOO_BIG"),
    ROUNDING_ERROR("ROUNDING_ERROR"),
    ROUTING_NUM_REQD("ROUTING_NUM_REQD"),
    SALES_DISCOUNT_ACCT_REQD("SALES_DISCOUNT_ACCT_REQD"),
    SAME_ACCT_TYP_REQD_FOR_PARENT("SAME_ACCT_TYP_REQD_FOR_PARENT"),
    SAVED_SRCH_EMAIL_ERROR("SAVED_SRCH_EMAIL_ERROR"),
    SCHDUL_EDIT_DISALLWD("SCHDUL_EDIT_DISALLWD"),
    SCHEDULED_REPORT_ERROR("SCHEDULED_REPORT_ERROR"),
    SEARCH_DATE_FILTER_REQD("SEARCH_DATE_FILTER_REQD"),
    SEARCH_ERROR("SEARCH_ERROR"),
    SEARCH_INTEGER_REQD("SEARCH_INTEGER_REQD"),
    SEARCH_TIMED_OUT("SEARCH_TIMED_OUT"),
    SECURE_TRANS_REQD_ON_CHECKOUT("SECURE_TRANS_REQD_ON_CHECKOUT"),
    SERIAL_NUM_MATCH_MULTI_ITEMS("SERIAL_NUM_MATCH_MULTI_ITEMS"),
    @XmlEnumValue("SESSION_TERMD_2ND_LOGIN_DECTD")
    SESSION_TERMD_2_ND_LOGIN_DECTD("SESSION_TERMD_2ND_LOGIN_DECTD"),
    SESSION_TIMED_OUT("SESSION_TIMED_OUT"),
    SETUP_METER_REQD("SETUP_METER_REQD"),
    SET_SHIPPING_PICKUP_TYP("SET_SHIPPING_PICKUP_TYP"),
    SHIPFROM_ADDRESS_NOT_SET("SHIPFROM_ADDRESS_NOT_SET"),
    SHIPMNT_INSURANCE_NOT_AVAILABLE("SHIPMNT_INSURANCE_NOT_AVAILABLE"),
    SHIP_ADDR_REQD("SHIP_ADDR_REQD"),
    SHIP_MANIFEST_ALREADY_PRCSSD("SHIP_MANIFEST_ALREADY_PRCSSD"),
    SHIP_MANIFEST_ERROR("SHIP_MANIFEST_ERROR"),
    SHIP_SETUP_REQD("SHIP_SETUP_REQD"),
    SHIP_TALBE_UNBALNCD("SHIP_TALBE_UNBALNCD"),
    SHIP_USER_ERROR("SHIP_USER_ERROR"),
    SINGLE_VALUE_REQD("SINGLE_VALUE_REQD"),
    SITEMAP_GEN_ERROR("SITEMAP_GEN_ERROR"),
    SITE_DOMAIN_NAME_REQD("SITE_DOMAIN_NAME_REQD"),
    SITE_TAG_ALREADY_EXISTS("SITE_TAG_ALREADY_EXISTS"),
    SO_HAS_CHILD_TRANS("SO_HAS_CHILD_TRANS"),
    SO_LINE_HAS_PO("SO_LINE_HAS_PO"),
    SRVC_UNAVAILBL_FOR_LOC("SRVC_UNAVAILBL_FOR_LOC"),
    SSS_AUTHOR_MUST_BE_EMPLOYEE("SSS_AUTHOR_MUST_BE_EMPLOYEE"),
    SSS_CONNECTION_TIME_OUT("SSS_CONNECTION_TIME_OUT"),
    SSS_DEBUG_DISALLWD("SSS_DEBUG_DISALLWD"),
    SSS_DRIP_EMAIL_RAN_OUT_OF_COUPON_CODES("SSS_DRIP_EMAIL_RAN_OUT_OF_COUPON_CODES"),
    SSS_DUP_DRIP_EMAIL("SSS_DUP_DRIP_EMAIL"),
    SSS_FILE_SIZE_EXCEEDED("SSS_FILE_SIZE_EXCEEDED"),
    SSS_INSTRUCTION_COUNT_EXCEEDED("SSS_INSTRUCTION_COUNT_EXCEEDED"),
    SSS_INVALID_ATTACH_RECORD_TYPE("SSS_INVALID_ATTACH_RECORD_TYPE"),
    SSS_INVALID_BCC_EMAIL("SSS_INVALID_BCC_EMAIL"),
    SSS_INVALID_CC_EMAIL("SSS_INVALID_CC_EMAIL"),
    SSS_INVALID_CMPGN_EVENT_ID("SSS_INVALID_CMPGN_EVENT_ID"),
    SSS_INVALID_EMAIL_TEMPLATE("SSS_INVALID_EMAIL_TEMPLATE"),
    SSS_INVALID_FORM_ELEMENT_NAME("SSS_INVALID_FORM_ELEMENT_NAME"),
    SSS_INVALID_GSO_FLTR_OPRTOR("SSS_INVALID_GSO_FLTR_OPRTOR"),
    SSS_INVALID_HEADER("SSS_INVALID_HEADER"),
    SSS_INVALID_HOST_CERT("SSS_INVALID_HOST_CERT"),
    SSS_INVALID_LIST_COLUMN_NAME("SSS_INVALID_LIST_COLUMN_NAME"),
    SSS_INVALID_LOCK_WAIT_TIME("SSS_INVALID_LOCK_WAIT_TIME"),
    SSS_INVALID_LOG_TYPE("SSS_INVALID_LOG_TYPE"),
    SSS_INVALID_PORTLET_INTERVAL("SSS_INVALID_PORTLET_INTERVAL"),
    SSS_INVALID_SCRIPTLET_ID("SSS_INVALID_SCRIPTLET_ID"),
    SSS_INVALID_SRCH_COL("SSS_INVALID_SRCH_COL"),
    SSS_INVALID_SRCH_COLUMN_JOIN("SSS_INVALID_SRCH_COLUMN_JOIN"),
    SSS_INVALID_SRCH_COLUMN_SUM("SSS_INVALID_SRCH_COLUMN_SUM"),
    SSS_INVALID_SRCH_FILTER("SSS_INVALID_SRCH_FILTER"),
    SSS_INVALID_SRCH_FILTER_JOIN("SSS_INVALID_SRCH_FILTER_JOIN"),
    SSS_INVALID_SRCH_OPERATOR("SSS_INVALID_SRCH_OPERATOR"),
    SSS_INVALID_SUBLIST_OPERATION("SSS_INVALID_SUBLIST_OPERATION"),
    SSS_INVALID_SUBMIT_OPTION("SSS_INVALID_SUBMIT_OPTION"),
    SSS_INVALID_TYPE_ARG("SSS_INVALID_TYPE_ARG"),
    SSS_INVALID_UI_OBJECT_TYPE("SSS_INVALID_UI_OBJECT_TYPE"),
    SSS_INVALID_URL("SSS_INVALID_URL"),
    SSS_INVALID_URL_CATEGORY("SSS_INVALID_URL_CATEGORY"),
    SSS_INVALID_WF_RCRD_TYPE("SSS_INVALID_WF_RCRD_TYPE"),
    SSS_MEMORY_USAGE_EXCEEDED("SSS_MEMORY_USAGE_EXCEEDED"),
    SSS_MISSING_REQD_ARGUMENT("SSS_MISSING_REQD_ARGUMENT"),
    SSS_QUEUE_LIMIT_EXCEEDED("SSS_QUEUE_LIMIT_EXCEEDED"),
    SSS_RECORD_TYPE_MISMATCH("SSS_RECORD_TYPE_MISMATCH"),
    SSS_REQUEST_TIME_EXCEEDED("SSS_REQUEST_TIME_EXCEEDED"),
    SSS_SSO_CONFIG_REQD("SSS_SSO_CONFIG_REQD"),
    SSS_STACK_FRAME_DEPTH_EXCEEDED("SSS_STACK_FRAME_DEPTH_EXCEEDED"),
    SSS_TIME_LIMIT_EXCEEDED("SSS_TIME_LIMIT_EXCEEDED"),
    SSS_TRANSACTION_REQD("SSS_TRANSACTION_REQD"),
    SSS_TRANS_IN_PROGRESS("SSS_TRANS_IN_PROGRESS"),
    SSS_UNKNOWN_HOST("SSS_UNKNOWN_HOST"),
    SSS_USAGE_LIMIT_EXCEEDED("SSS_USAGE_LIMIT_EXCEEDED"),
    START_DATE_AFTER_END_DATE("START_DATE_AFTER_END_DATE"),
    START_DATE_REQD("START_DATE_REQD"),
    STATE_ALREADY_EXISTS("STATE_ALREADY_EXISTS"),
    STATE_REQD("STATE_REQD"),
    STATUS_ASSIGNEE_REQD("STATUS_ASSIGNEE_REQD"),
    STORAGE_LIMIT_EXCEEDED("STORAGE_LIMIT_EXCEEDED"),
    STORE_ALIAS_UNAVAILABLE("STORE_ALIAS_UNAVAILABLE"),
    STORE_DOMAIN_UNAVAILABLE("STORE_DOMAIN_UNAVAILABLE"),
    SUBITEM_REQD("SUBITEM_REQD"),
    SUBSIDIARY_MISMATCH("SUBSIDIARY_MISMATCH"),
    SUB_MISMATCH("SUB_MISMATCH"),
    SUB_RESTRICT_VIEW_REQD("SUB_RESTRICT_VIEW_REQD"),
    SUB_TAX_AGENCY_REQD("SUB_TAX_AGENCY_REQD"),
    SUCCESS_TRANS("SUCCESS_TRANS"),
    SUPRT_CNTR_LOGIN_ERROR("SUPRT_CNTR_LOGIN_ERROR"),
    TAGATA_ALREADY_ENDORSED("TAGATA_ALREADY_ENDORSED"),
    TAG_ALREADY_EXISTS("TAG_ALREADY_EXISTS"),
    TAG_SUBSTITUTN_ERROR("TAG_SUBSTITUTN_ERROR"),
    TAX_ACCT_SETUP_REQD("TAX_ACCT_SETUP_REQD"),
    TAX_CODES_SETUP_PROBLEM("TAX_CODES_SETUP_PROBLEM"),
    TAX_CODES_SETUP_REQD("TAX_CODES_SETUP_REQD"),
    TAX_CODE_REQD("TAX_CODE_REQD"),
    TAX_GROUP_SETUP_REQD("TAX_GROUP_SETUP_REQD"),
    TAX_PRD_REQD("TAX_PRD_REQD"),
    TAX_SETUP_REQD("TAX_SETUP_REQD"),
    TEMPLATE_NOT_FOUND("TEMPLATE_NOT_FOUND"),
    THIRD_PARTY_BILLING_ACCT_REQD("THIRD_PARTY_BILLING_ACCT_REQD"),
    TICKET_NOT_LOCATED("TICKET_NOT_LOCATED"),
    TIMEOUT_THE_RECORD_DOESNT_EXIST_ANYMORE("TIMEOUT_THE_RECORD_DOESNT_EXIST_ANYMORE"),
    TIME_ENTRY_DISALLWD("TIME_ENTRY_DISALLWD"),
    TOPIC_REQD("TOPIC_REQD"),
    TRANSACTION_DELETED("TRANSACTION_DELETED"),
    TRANSORD_SHIP_REC_MISMATCH("TRANSORD_SHIP_REC_MISMATCH"),
    TRANS_ALREADY_REFUNDED("TRANS_ALREADY_REFUNDED"),
    TRANS_ALREADY_SETTLED("TRANS_ALREADY_SETTLED"),
    TRANS_ALREADY_VOIDED("TRANS_ALREADY_VOIDED"),
    TRANS_AMTS_UNBALNCD("TRANS_AMTS_UNBALNCD"),
    TRANS_APPLIED_AMTS_UNBALNCD("TRANS_APPLIED_AMTS_UNBALNCD"),
    TRANS_CLASS_UNBALNCD("TRANS_CLASS_UNBALNCD"),
    TRANS_DEPT_UNBALNCD("TRANS_DEPT_UNBALNCD"),
    TRANS_DOES_NOT_EXIST("TRANS_DOES_NOT_EXIST"),
    TRANS_DSNT_EXIST("TRANS_DSNT_EXIST"),
    TRANS_EDIT_DISALLWD("TRANS_EDIT_DISALLWD"),
    TRANS_FORGN_CRNCY_MISMATCH("TRANS_FORGN_CRNCY_MISMATCH"),
    TRANS_FORGN_CUR_UNBALNCD("TRANS_FORGN_CUR_UNBALNCD"),
    TRANS_IN_USE("TRANS_IN_USE"),
    TRANS_LINES_UNBALNCD("TRANS_LINES_UNBALNCD"),
    TRANS_LINE_AND_PMT_UNBALNCD("TRANS_LINE_AND_PMT_UNBALNCD"),
    TRANS_LOC_UNBALNCD("TRANS_LOC_UNBALNCD"),
    TRANS_NOT_CLEANED("TRANS_NOT_CLEANED"),
    TRANS_NOT_COMPLETED("TRANS_NOT_COMPLETED"),
    TRANS_PRCSSNG_ERROR("TRANS_PRCSSNG_ERROR"),
    TRANS_UNBALNCD("TRANS_UNBALNCD"),
    TRAN_DATE_REQD("TRAN_DATE_REQD"),
    TRAN_LINE_FX_AMT_REQD("TRAN_LINE_FX_AMT_REQD"),
    TRAN_LINK_FX_AMT_REQD("TRAN_LINK_FX_AMT_REQD"),
    TRAN_PERIOD_CLOSED("TRAN_PERIOD_CLOSED"),
    TRAN_PRD_CLOSED("TRAN_PRD_CLOSED"),
    TWO_FA_AUTH_REQD("TWO_FA_AUTH_REQD"),
    TWO_FA_REQD("TWO_FA_REQD"),
    UNABLE_TO_PRINT_CHECKS("UNABLE_TO_PRINT_CHECKS"),
    UNABLE_TO_PRINT_DEPOSITS("UNABLE_TO_PRINT_DEPOSITS"),
    UNAUTH_CAMPAIGN_RSPNS_RQST("UNAUTH_CAMPAIGN_RSPNS_RQST"),
    UNAUTH_UNSUBSCRIBE_RQST("UNAUTH_UNSUBSCRIBE_RQST"),
    UNDEFINED_ACCTNG_PRD("UNDEFINED_ACCTNG_PRD"),
    UNDEFINED_CSTM_FIELD("UNDEFINED_CSTM_FIELD"),
    UNDEFINED_TAX_PRD("UNDEFINED_TAX_PRD"),
    UNEXPECTED_ERROR("UNEXPECTED_ERROR"),
    UNIQUE_CONTACT_NAME_REQD("UNIQUE_CONTACT_NAME_REQD"),
    UNIQUE_CUST_EMAIL_REQD("UNIQUE_CUST_EMAIL_REQD"),
    UNIQUE_CUST_ID_REQD("UNIQUE_CUST_ID_REQD"),
    UNIQUE_ENTITY_NAME_REQD("UNIQUE_ENTITY_NAME_REQD"),
    UNIQUE_GROUPID_REQD("UNIQUE_GROUPID_REQD"),
    UNIQUE_PARTNER_CODE_REQD("UNIQUE_PARTNER_CODE_REQD"),
    UNIQUE_QTY_REQD("UNIQUE_QTY_REQD"),
    UNIQUE_RCRD_ID_REQD("UNIQUE_RCRD_ID_REQD"),
    UNIQUE_SOLUTION_CODE_REQD("UNIQUE_SOLUTION_CODE_REQD"),
    UNITS_TYP_IN_USE("UNITS_TYP_IN_USE"),
    UNKNOWN_CARRIER("UNKNOWN_CARRIER"),
    UNKNOWN_RCRD_TYPE("UNKNOWN_RCRD_TYPE"),
    UNKNOWN_SCRIPT_TYP("UNKNOWN_SCRIPT_TYP"),
    UNKNWN_ALLOCTN_SCHDUL_FREQ_TYP("UNKNWN_ALLOCTN_SCHDUL_FREQ_TYP"),
    UNKNWN_EMAIL_AUTHOR("UNKNWN_EMAIL_AUTHOR"),
    UNKNWN_EXCHANGE_RATE("UNKNWN_EXCHANGE_RATE"),
    UNRECOGNIZED_METHOD("UNRECOGNIZED_METHOD"),
    UNSUBSCRIBE_REQD("UNSUBSCRIBE_REQD"),
    UNSUPRTD_DOC_TYP("UNSUPRTD_DOC_TYP"),
    UPDATE_DISALLWD("UPDATE_DISALLWD"),
    UPDATE_PRICE_AMT_REQD("UPDATE_PRICE_AMT_REQD"),
    UPGRADE_WS_VERSION("UPGRADE_WS_VERSION"),
    UPS_CANT_INTEGRATE_FULFILL("UPS_CANT_INTEGRATE_FULFILL"),
    UPS_CONFIG_ERROR("UPS_CONFIG_ERROR"),
    UPS_LICENSE_AGREEMNT_REQD("UPS_LICENSE_AGREEMNT_REQD"),
    UPS_ONLINE_RATE_UNAVAILBL("UPS_ONLINE_RATE_UNAVAILBL"),
    UPS_ONLINE_SHIP_UNAVAILBL("UPS_ONLINE_SHIP_UNAVAILBL"),
    UPS_REG_NUM_IN_USE("UPS_REG_NUM_IN_USE"),
    UPS_SETUP_REQD("UPS_SETUP_REQD"),
    UPS_USER_ERROR("UPS_USER_ERROR"),
    UPS_VOID_ERROR("UPS_VOID_ERROR"),
    UPS_XML_ERROR("UPS_XML_ERROR"),
    URL_ID_PARAM_REQD("URL_ID_PARAM_REQD"),
    URL_REQD("URL_REQD"),
    USER_CONTEXT_REQD("USER_CONTEXT_REQD"),
    USER_DISABLED("USER_DISABLED"),
    USER_ERROR("USER_ERROR"),
    USPS_ACCT_NUM_ALREADY_EXISTS("USPS_ACCT_NUM_ALREADY_EXISTS"),
    USPS_INVALID_INSURED_VALUE("USPS_INVALID_INSURED_VALUE"),
    USPS_INVALID_PACKAGING("USPS_INVALID_PACKAGING"),
    USPS_INVALID_PSWD("USPS_INVALID_PSWD"),
    USPS_LABEL_VOIDED("USPS_LABEL_VOIDED"),
    USPS_MAX_ITEM_EXCEEDED("USPS_MAX_ITEM_EXCEEDED"),
    USPS_ONE_PACKAGE_ALLWD("USPS_ONE_PACKAGE_ALLWD"),
    USPS_PASS_PHRASE_NOT_UPDATED("USPS_PASS_PHRASE_NOT_UPDATED"),
    USPS_REFUND_FAILED("USPS_REFUND_FAILED"),
    USPS_RETRY("USPS_RETRY"),
    USPS_VALIDATE_ADDR("USPS_VALIDATE_ADDR"),
    USPS_VERIFY_TRACKING_NUM("USPS_VERIFY_TRACKING_NUM"),
    USPS_VOID_ERROR("USPS_VOID_ERROR"),
    VALID_EMAIL_REQD("VALID_EMAIL_REQD"),
    VALID_EMAIL_REQD_FOR_LOGIN("VALID_EMAIL_REQD_FOR_LOGIN"),
    VALID_FIRST_NAME_REQD("VALID_FIRST_NAME_REQD"),
    VALID_LAST_NAME_REQD("VALID_LAST_NAME_REQD"),
    VALID_LINE_ITEM_REQD("VALID_LINE_ITEM_REQD"),
    VALID_PHONE_NUM_REQD("VALID_PHONE_NUM_REQD"),
    VALID_PRD_REQD("VALID_PRD_REQD"),
    VALID_URL_REQD("VALID_URL_REQD"),
    VALID_VERSION_REQD_IN_URL("VALID_VERSION_REQD_IN_URL"),
    VALID_WORK_PHONE_REQD("VALID_WORK_PHONE_REQD"),
    VALID_ZIPCODE_REQD("VALID_ZIPCODE_REQD"),
    VENDOR_TYPE_REQD("VENDOR_TYPE_REQD"),
    VERIFY_DESTNTN_ZIP_CODE("VERIFY_DESTNTN_ZIP_CODE"),
    VERIFY_PAYROLL_FUND_ACCT("VERIFY_PAYROLL_FUND_ACCT"),
    VERIFY_ZIP_CODE_SETUP("VERIFY_ZIP_CODE_SETUP"),
    VISA_ERROR("VISA_ERROR"),
    VOIDING_REVERSAL_DISALLWD("VOIDING_REVERSAL_DISALLWD"),
    VOID_FAILED("VOID_FAILED"),
    VSOE_CANT_ADD_ITEM_GROUP("VSOE_CANT_ADD_ITEM_GROUP"),
    VSOE_REV_REC_TMPLT_REQD("VSOE_REV_REC_TMPLT_REQD"),
    VSOE_TOTAL_ALLOCATION_ERROR("VSOE_TOTAL_ALLOCATION_ERROR"),
    VSOE_TRAN_VSOE_BUNDLE_ERROR("VSOE_TRAN_VSOE_BUNDLE_ERROR"),
    WARNING("WARNING"),
    WF_EXEC_USAGE_LIMIT_EXCEEDED("WF_EXEC_USAGE_LIMIT_EXCEEDED"),
    WORK_DAYS_REQD("WORK_DAYS_REQD"),
    WORLDPAY_ERROR("WORLDPAY_ERROR"),
    WRITE_OFF_ACCT_REQD("WRITE_OFF_ACCT_REQD"),
    WS_CONCUR_SESSION_DISALLWD("WS_CONCUR_SESSION_DISALLWD"),
    WS_EXCEEDED_CONCUR_USERS_ALLWD("WS_EXCEEDED_CONCUR_USERS_ALLWD"),
    WS_EXCEEDED_MAX_CONCUR_RQST("WS_EXCEEDED_MAX_CONCUR_RQST"),
    WS_FEATURE_REQD("WS_FEATURE_REQD"),
    WS_INVALID_SEARCH_OPERATN("WS_INVALID_SEARCH_OPERATN"),
    WS_LOG_IN_REQD("WS_LOG_IN_REQD"),
    WS_PERMISSION_REQD("WS_PERMISSION_REQD"),
    ZIP_FILE_CONTAINS_VIRUS("ZIP_FILE_CONTAINS_VIRUS");
    private final String value;

    StatusDetailCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusDetailCodeType fromValue(String v) {
        for (StatusDetailCodeType c: StatusDetailCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
