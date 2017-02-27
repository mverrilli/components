package org.talend.components.netsuite.connection;

import java.util.EnumSet;

import org.apache.commons.lang3.StringUtils;
import org.talend.components.api.properties.ComponentPropertiesImpl;
import org.talend.components.api.properties.ComponentReferenceProperties;
import org.talend.components.netsuite.NetSuiteProvideConnectionProperties;
import org.talend.components.netsuite.RuntimeService;
import org.talend.daikon.java8.Function;
import org.talend.daikon.properties.PresentationItem;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;
import org.talend.daikon.properties.property.Property;

import static org.talend.components.netsuite.TNetSuiteComponentDefinition.withRuntimeService;
import static org.talend.daikon.properties.presentation.Widget.widget;
import static org.talend.daikon.properties.property.PropertyFactory.newInteger;
import static org.talend.daikon.properties.property.PropertyFactory.newProperty;
import static org.talend.daikon.properties.property.PropertyFactory.newString;

/**
 *
 */
public class NetSuiteConnectionProperties extends ComponentPropertiesImpl
        implements NetSuiteProvideConnectionProperties {

    public static final String FORM_WIZARD = "Wizard";

    public static final String DEFAULT_ENDPOINT_URL =
            "https://webservices.netsuite.com/services/NetSuitePort_2016_2";

    public static final String DEFAULT_API_VERSION = "2016.2";

    public Property<String> name = newString("name").setRequired();

    public Property<String> endpoint = newString("endpoint").setRequired();

    public Property<String> apiVersion = newString("apiVersion");

    public Property<String> email = newString("email").setRequired();

    public Property<String> password = newProperty("password").setRequired()
            .setFlags(EnumSet.of(Property.Flags.ENCRYPT, Property.Flags.SUPPRESS_LOGGING));

    public Property<Integer> role = newInteger("role").setRequired();

    public Property<String> account = newString("account").setRequired();

    public Property<String> applicationId = newString("applicationId");

    public PresentationItem testConnection = new PresentationItem("testConnection", "Test connection");

    public ComponentReferenceProperties<NetSuiteConnectionProperties> referencedComponent =
            new ComponentReferenceProperties("referencedComponent", TNetSuiteConnectionDefinition.COMPONENT_NAME);

    public NetSuiteConnectionProperties(String name) {
        super(name);
    }

    @Override
    public void setupProperties() {
        super.setupProperties();

        endpoint.setValue(DEFAULT_ENDPOINT_URL);
        apiVersion.setValue(DEFAULT_API_VERSION);
        email.setValue("youremail@yourcompany.com");
        role.setValue(3);
        account.setValue("");
        applicationId.setValue("");
    }

    @Override
    public void setupLayout() {
        super.setupLayout();

        Form mainForm = new Form(this, Form.MAIN);
        mainForm.addRow(endpoint);
        mainForm.addRow(email);
        mainForm.addRow(widget(password)
                .setWidgetType(Widget.HIDDEN_TEXT_WIDGET_TYPE));
        mainForm.addRow(role);
        mainForm.addRow(account);
        mainForm.addRow(applicationId);

        // A form for a reference to a connection
        Form refForm = Form.create(this, Form.REFERENCE);
        Widget compListWidget = widget(referencedComponent)
                .setWidgetType(Widget.COMPONENT_REFERENCE_WIDGET_TYPE);
        refForm.addRow(compListWidget);
        refForm.addRow(mainForm);

        // Wizard
        Form wizardForm = Form.create(this, FORM_WIZARD);
        wizardForm.addRow(name);
        wizardForm.addRow(endpoint);
        wizardForm.addRow(email);
        wizardForm.addRow(widget(password)
                .setWidgetType(Widget.HIDDEN_TEXT_WIDGET_TYPE));
        wizardForm.addRow(role);
        wizardForm.addRow(account);
        wizardForm.addRow(applicationId);
        wizardForm.addColumn(widget(testConnection)
                .setWidgetType(Widget.BUTTON_WIDGET_TYPE).setLongRunning(true));
    }

    @Override
    public void refreshLayout(Form form) {
        super.refreshLayout(form);

        String refComponentId = getReferencedComponentId();
        boolean refConnectionUsed = refComponentId != null
                && refComponentId.startsWith(TNetSuiteConnectionDefinition.COMPONENT_NAME);

        if (form.getName().equals(Form.MAIN) || form.getName().equals(FORM_WIZARD)) {
            form.getWidget(endpoint.getName()).setHidden(refConnectionUsed);
            form.getWidget(email.getName()).setHidden(refConnectionUsed);
            form.getWidget(password.getName()).setHidden(refConnectionUsed);
            form.getWidget(role.getName()).setHidden(refConnectionUsed);
            form.getWidget(account.getName()).setHidden(refConnectionUsed);
            form.getWidget(applicationId.getName()).setHidden(refConnectionUsed);
        }
    }

    public String getApiVersion() {
        return StringUtils.strip(apiVersion.getStringValue(), "\"");
    }

    @Override
    public NetSuiteConnectionProperties getConnectionProperties() {
        return this;
    }

    public String getReferencedComponentId() {
        return referencedComponent.componentInstanceId.getStringValue();
    }

    public NetSuiteConnectionProperties getReferencedConnectionProperties() {
        NetSuiteConnectionProperties refProps = referencedComponent.getReference();
        if (refProps != null) {
            return refProps;
        }
        return null;
    }

    public void afterReferencedComponent() {
        refreshLayout(getForm(Form.MAIN));
        refreshLayout(getForm(Form.REFERENCE));
    }

    public ValidationResult validateTestConnection() throws Exception {
        ValidationResult vr = withRuntimeService(new Function<RuntimeService, ValidationResult>() {
            @Override public ValidationResult apply(RuntimeService runtimeService) {
                return runtimeService.validateConnection(NetSuiteConnectionProperties.this);
            }
        });
        if (vr.getStatus() == ValidationResult.Result.OK) {
            vr.setMessage("Connection successful");
            getForm(FORM_WIZARD).setAllowForward(true);
        } else {
            getForm(FORM_WIZARD).setAllowForward(false);
        }
        return vr;
    }
}