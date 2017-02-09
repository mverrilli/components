package org.talend.components.netsuite.runtime;

import org.talend.components.api.component.runtime.DependenciesReader;
import org.talend.components.api.component.runtime.JarRuntimeInfo;
import org.talend.components.api.component.runtime.SimpleRuntimeInfo;
import org.talend.daikon.runtime.RuntimeInfo;

/**
 *
 */
public class RuntimeInfoFactory {

    private static final RuntimeInfoFactory instance = new RuntimeInfoFactory();

    public static final String RUNTIME_MAVEN_URI = "mvn:org.talend.components/netsuite-runtime";
    public static final String MAVEN_GROUP_ID = "org.talend.components";
    public static final String MAVEN_ARTIFACT_ID = "netsuite-runtime";

    public static final String SOURCE_OR_SINK_CLASS =
            "org.talend.components.netsuite.NetSuiteSourceOrSink";
    public static final String SOURCE_CLASS =
            "org.talend.components.netsuite.NetSuiteSource";
    public static final String SINK_CLASS =
            "org.talend.components.netsuite.NetSuiteSink";

    public static final String RUNTIME_SERVICE_CLASS =
            "org.talend.components.netsuite.runtime.RuntimeServiceImpl";

    public static RuntimeInfoFactory getInstance() {
        return instance;
    }

    public RuntimeInfo getRuntimeInfo(String runtimeClassName) {
        return new SimpleRuntimeInfo(RuntimeInfoFactory.class.getClassLoader(),
                DependenciesReader.computeDependenciesFilePath(MAVEN_GROUP_ID, MAVEN_ARTIFACT_ID),
                runtimeClassName);
//        return new JarRuntimeInfo(RUNTIME_MAVEN_URI,
//                DependenciesReader.computeDependenciesFilePath(MAVEN_GROUP_ID, MAVEN_ARTIFACT_ID),
//                runtimeClassName);
    }

}
