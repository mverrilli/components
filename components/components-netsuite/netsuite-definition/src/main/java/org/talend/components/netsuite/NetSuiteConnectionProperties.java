package org.talend.components.netsuite;

import java.util.EnumSet;

import org.talend.components.api.properties.ComponentPropertiesImpl;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;
import org.talend.daikon.properties.property.Property;

import static org.talend.daikon.properties.presentation.Widget.widget;
import static org.talend.daikon.properties.property.PropertyFactory.newInteger;
import static org.talend.daikon.properties.property.PropertyFactory.newProperty;
import static org.talend.daikon.properties.property.PropertyFactory.newString;

/**
 *
 */
public class NetSuiteConnectionProperties extends ComponentPropertiesImpl
        implements NetSuiteProvideConnectionProperties {

    public static final String ENDPOINT_URL = "https://webservices.netsuite.com/services/NetSuitePort_2016_2";

    public Property<String> endpoint = newString("endpoint").setRequired();

    public Property<String> email = newString("email").setRequired();

    public Property<String> password = newProperty("password").setRequired()
            .setFlags(EnumSet.of(Property.Flags.ENCRYPT, Property.Flags.SUPPRESS_LOGGING));

    public Property<Integer> role = newInteger("role").setRequired();

    public Property<String> account = newString("account").setRequired();

    public NetSuiteConnectionProperties(String name) {
        super(name);
    }

    @Override
    public void setupProperties() {
        super.setupProperties();

        endpoint.setValue(ENDPOINT_URL);
        email.setValue("youremail@yourcompany.com");
        role.setValue(3);
        account.setValue("");
    }

    @Override
    public void setupLayout() {
        super.setupLayout();

        Form mainForm = new Form(this, Form.MAIN);
        mainForm.addRow(endpoint);
        mainForm.addRow(email);
        mainForm.addRow(widget(password).setWidgetType(Widget.HIDDEN_TEXT_WIDGET_TYPE));
        mainForm.addRow(role);
        mainForm.addRow(account);

        Form advancedForm = new Form(this, Form.ADVANCED);

        // A form for a reference to a connection, used in a NetSuite Input component for example
        Form refForm = Form.create(this, Form.REFERENCE);
    }

    @Override
    public void refreshLayout(Form form) {
        super.refreshLayout(form);
    }

    @Override
    public NetSuiteConnectionProperties getConnectionProperties() {
        return this;
    }
}
