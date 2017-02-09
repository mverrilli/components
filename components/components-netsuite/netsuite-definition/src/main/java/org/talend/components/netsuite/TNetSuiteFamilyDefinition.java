package org.talend.components.netsuite;

import aQute.bnd.annotation.component.Component;

import org.talend.components.api.AbstractComponentFamilyDefinition;
import org.talend.components.api.ComponentInstaller;
import org.talend.components.api.Constants;
import org.talend.components.netsuite.connection.TNetSuiteConnectionDefinition;
import org.talend.components.netsuite.input.TNetSuiteInputDefinition;
import org.talend.components.netsuite.output.TNetSuiteOutputDefinition;

@Component(
        name = Constants.COMPONENT_INSTALLER_PREFIX + TNetSuiteFamilyDefinition.NAME,
        provide = ComponentInstaller.class
)
public class TNetSuiteFamilyDefinition extends AbstractComponentFamilyDefinition
        implements ComponentInstaller {

    public static final String NAME = "NetSuite";

    public TNetSuiteFamilyDefinition() {
        super(NAME,
                // Components
                new TNetSuiteConnectionDefinition(),
                new TNetSuiteInputDefinition(),
                new TNetSuiteOutputDefinition());
    }

    @Override
    public void install(ComponentFrameworkContext ctx) {
        ctx.registerComponentFamilyDefinition(this);
    }

}
