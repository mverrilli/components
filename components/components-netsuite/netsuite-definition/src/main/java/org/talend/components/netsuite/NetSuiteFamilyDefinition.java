package org.talend.components.netsuite;

import aQute.bnd.annotation.component.Component;

import org.talend.components.api.AbstractComponentFamilyDefinition;
import org.talend.components.api.ComponentInstaller;
import org.talend.components.api.Constants;
import org.talend.components.netsuite.input.NetSuiteInputDefinition;

@Component(name = Constants.COMPONENT_INSTALLER_PREFIX + NetSuiteFamilyDefinition.NAME, provide = ComponentInstaller.class)
public class NetSuiteFamilyDefinition extends AbstractComponentFamilyDefinition
        implements ComponentInstaller {

    public static final String NAME = "NetSuite";

    public NetSuiteFamilyDefinition() {
        super(NAME,
                // Components
                new NetSuiteInputDefinition());
    }

    @Override
    public void install(ComponentFrameworkContext ctx) {
        ctx.registerComponentFamilyDefinition(this);
    }

}
