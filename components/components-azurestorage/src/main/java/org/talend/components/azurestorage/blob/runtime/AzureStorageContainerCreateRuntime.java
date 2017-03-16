package org.talend.components.azurestorage.blob.runtime;

import org.talend.components.api.component.runtime.ComponentDriverInitialization;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.azurestorage.blob.tazurestoragecontainercreate.TAzureStorageContainerCreateProperties;
import org.talend.daikon.properties.ValidationResult;

/**
 * Runtime implementation for AzureStorage container create feature. 
 * Creates container
 * These methods are called only on Driver node in following order:
 * 1) {@link this#initialize(RuntimeContainer, TAzureStorageContainerCreateProperties)}
 * 2) {@link this#runAtDriver(RuntimeContainer)}
 * Instances of this class should not be serialized and sent on worker nodes
 */
public class AzureStorageContainerCreateRuntime implements ComponentDriverInitialization<TAzureStorageContainerCreateProperties> {

	/**
	 * Component properties; should not be changed inside this class
	 */
	private TAzureStorageContainerCreateProperties properties;
	
	@Override
	public ValidationResult initialize(RuntimeContainer container, TAzureStorageContainerCreateProperties properties) {
		this.properties = properties;
		// TODO add some properties validation here
		return ValidationResult.OK;
	}

	@Override
	public void runAtDriver(RuntimeContainer container) {
		// TODO Auto-generated method stub
		
	}

}
