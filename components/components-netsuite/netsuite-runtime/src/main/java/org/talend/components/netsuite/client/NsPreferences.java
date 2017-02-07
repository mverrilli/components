package org.talend.components.netsuite.client;

/**
 *
 */
public class NsPreferences {
    protected Boolean warningAsError;
    protected Boolean disableMandatoryCustomFieldValidation;
    protected Boolean disableSystemNotesForCustomFields;
    protected Boolean ignoreReadOnlyFields;
    protected Boolean runServerSuiteScriptAndTriggerWorkflows;

    public Boolean getWarningAsError() {
        return warningAsError;
    }

    public void setWarningAsError(Boolean value) {
        this.warningAsError = value;
    }

    public Boolean getDisableMandatoryCustomFieldValidation() {
        return disableMandatoryCustomFieldValidation;
    }

    public void setDisableMandatoryCustomFieldValidation(Boolean value) {
        this.disableMandatoryCustomFieldValidation = value;
    }

    public Boolean getDisableSystemNotesForCustomFields() {
        return disableSystemNotesForCustomFields;
    }

    public void setDisableSystemNotesForCustomFields(Boolean value) {
        this.disableSystemNotesForCustomFields = value;
    }

    public Boolean getIgnoreReadOnlyFields() {
        return ignoreReadOnlyFields;
    }

    public void setIgnoreReadOnlyFields(Boolean value) {
        this.ignoreReadOnlyFields = value;
    }

    public Boolean getRunServerSuiteScriptAndTriggerWorkflows() {
        return runServerSuiteScriptAndTriggerWorkflows;
    }

    public void setRunServerSuiteScriptAndTriggerWorkflows(Boolean value) {
        this.runServerSuiteScriptAndTriggerWorkflows = value;
    }

}
