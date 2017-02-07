package org.talend.components.netsuite.input;

import java.util.List;

import org.apache.commons.lang3.reflect.TypeLiteral;
import org.talend.components.api.properties.ComponentPropertiesImpl;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;
import org.talend.daikon.properties.property.Property;

import static org.talend.daikon.properties.property.PropertyFactory.newProperty;

/**
 *
 */
public class SearchConditionTable extends ComponentPropertiesImpl {

    /**
     *
     */
    private static final TypeLiteral<List<String>> LIST_STRING_TYPE = new TypeLiteral<List<String>>() {// empty
    };

    /**
     * Named constructor to be used is these properties are nested in other properties. Do not subclass this method for
     * initialization, use {@link #init()} instead.
     *
     * @param name
     */
    public SearchConditionTable(String name) {
        super(name);
    }

    public Property<List<String>> field = newProperty(LIST_STRING_TYPE, "field");

    public Property<List<String>> operator = newProperty(LIST_STRING_TYPE, "operator");

    public Property<List<String>> value1 = newProperty(LIST_STRING_TYPE, "value1");

    public Property<List<String>> value2 = newProperty(LIST_STRING_TYPE, "value2");

    @Override
    public void setupLayout() {
        super.setupLayout();

        Form mainForm = new Form(this, Form.MAIN);
        mainForm.addColumn(new Widget(field).setWidgetType(Widget.ENUMERATION_WIDGET_TYPE));
        mainForm.addColumn(new Widget(operator).setWidgetType(Widget.ENUMERATION_WIDGET_TYPE));
        mainForm.addColumn(value1);
        mainForm.addColumn(value2);

        Form refForm = new Form(this, Form.REFERENCE);
        refForm.addColumn(new Widget(field).setWidgetType(Widget.ENUMERATION_WIDGET_TYPE));
        refForm.addColumn(new Widget(operator).setWidgetType(Widget.ENUMERATION_WIDGET_TYPE));
        refForm.addColumn(value1);
        refForm.addColumn(value2);
    }

    @Override
    public void refreshLayout(Form form) {
        super.refreshLayout(form);

        if (form != null && Form.MAIN.equals(form.getName())) {
        }
    }

    @Override
    public void setupProperties() {
        super.setupProperties();
    }
}
