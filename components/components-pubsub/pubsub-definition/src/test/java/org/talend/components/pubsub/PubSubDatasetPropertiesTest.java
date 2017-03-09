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

package org.talend.components.pubsub;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.talend.components.api.test.ComponentTestUtils;
import org.talend.daikon.properties.PropertiesDynamicMethodHelper;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;

public class PubSubDatasetPropertiesTest {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    PubSubDatasetProperties properties;

    @Before
    public void reset() {
        properties = new PubSubDatasetProperties("test");
        properties.init();
    }

    @Test
    public void testI18N() {
        ComponentTestUtils.checkAllI18N(properties, errorCollector);
    }

    @Test
    public void testVisible() throws Throwable {
        Form main = properties.getForm(Form.MAIN);
        assertTrue(main.getWidget(properties.topic).isVisible());
        assertTrue(main.getWidget(properties.subscription).isVisible());
        assertTrue(main.getWidget(properties.valueFormat).isVisible());
        assertTrue(main.getWidget(properties.fieldDelimiter).isVisible());
        assertTrue(main.getWidget(properties.avroSchema).isHidden());

        properties.valueFormat.setValue(PubSubDatasetProperties.ValueFormat.AVRO);
        PropertiesDynamicMethodHelper.afterProperty(properties, properties.valueFormat.getName());
        assertTrue(main.getWidget(properties.topic).isVisible());
        assertTrue(main.getWidget(properties.subscription).isVisible());
        assertTrue(main.getWidget(properties.valueFormat).isVisible());
        assertTrue(main.getWidget(properties.fieldDelimiter).isHidden());
        assertTrue(main.getWidget(properties.avroSchema).isVisible());

    }

    /**
     * Checks {@link PubSubDatasetProperties} sets correctly initial schema property
     */
    @Test
    public void testDefaultProperties() {
        assertEquals(PubSubDatasetProperties.ValueFormat.CSV, properties.valueFormat.getValue());
    }

    /**
     * Checks {@link PubSubDatasetProperties} sets correctly initial layout properties
     */
    @Test
    public void testSetupLayout() {
        Form main = properties.getForm(Form.MAIN);
        Collection<Widget> mainWidgets = main.getWidgets();

        List<String> ALL = Arrays.asList(properties.topic.getName(), properties.subscription.getName(),
                properties.valueFormat.getName(), properties.fieldDelimiter.getName(), properties.avroSchema.getName());

        Assert.assertThat(main, notNullValue());
        Assert.assertThat(mainWidgets, hasSize(ALL.size()));

        for (String field : ALL) {
            Widget w = main.getWidget(field);
            Assert.assertThat(w, notNullValue());
        }
    }

}
