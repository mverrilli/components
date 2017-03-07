package org.talend.components.netsuite;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.talend.components.api.test.AbstractComponentTest2;
import org.talend.components.netsuite.input.NetSuiteInputDefinition;
import org.talend.components.netsuite.output.NetSuiteOutputDefinition;
import org.talend.daikon.definition.Definition;
import org.talend.daikon.definition.service.DefinitionRegistryService;

public class NetSuiteInputTestBase extends AbstractComponentTest2 {

    @Inject DefinitionRegistryService defReg;

    @Override
    public DefinitionRegistryService getDefinitionRegistry() {
        return defReg;
    }

    @Test
    public void componentHasBeenRegistered(){
        assertThat(getDefinitionRegistry().getDefinitionsMapByType(Definition.class).get(NetSuiteInputDefinition.COMPONENT_NAME),
                notNullValue());
        assertThat(getDefinitionRegistry().getDefinitionsMapByType(Definition.class).get(NetSuiteOutputDefinition.COMPONENT_NAME),
                notNullValue());
    }
}
