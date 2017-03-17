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
package ${package}.runtime.reader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.component.runtime.BoundedSource;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.common.avro.RootSchemaUtils;
import ${package}.avro.DelimitedStringConverter;
import ${package}.avro.DelimitedStringSchemaInferrer;
import ${package}.${componentPackage}.${componentName}Properties;
import org.talend.daikon.NamedThing;
import org.talend.daikon.avro.converter.AvroConverter;
import org.talend.daikon.properties.ValidationResult;

/**
 * The ${componentName}Source provides the mechanism to supply data to other
 * components at run-time.
 *
 * Based on the Apache Beam project, the Source mechanism is appropriate to
 * describe distributed and non-distributed data sources and can be adapted
 * to scalable big data execution engines on a cluster, or run locally.
 *
 * This example component describes an input source that is guaranteed to be
 * run in a single JVM (whether on a cluster or locally)
 */
public class ${componentName}Source implements BoundedSource {

    /** Default serial version UID. */
    private static final long serialVersionUID = 1L;
    
    private ${componentName}Properties inputProperties;
    
    private String filePath;
    
    private Schema designSchema;
    
    private Schema runtimeSchema;
    
    private String delimiter;

    @Override
    public ValidationResult initialize(RuntimeContainer container, ComponentProperties properties) {
        this.inputProperties = (${componentName}Properties) properties;
        return ValidationResult.OK;
    }

    /**
     * Validates that component can connect to Data Store
     * Here, method checks that file exist
     */
    @Override
    public ValidationResult validate(RuntimeContainer container) {
        File file = new File(getFilePath());
        if (file.exists()) {
            return ValidationResult.OK;
        } else {
            ValidationResult vr = new ValidationResult();
            vr.setMessage("The file '" + file.getPath() + "' does not exist."); //$NON-NLS-1$//$NON-NLS-2$
            vr.setStatus(ValidationResult.Result.ERROR);
            return vr;
        }
    }

    @Override
    public Schema getEndpointSchema(RuntimeContainer container, String schemaName) throws IOException {
        return null;
    }

    @Override
    public List<NamedThing> getSchemaNames(RuntimeContainer container) throws IOException {
        return null;
    }

    @Override
    public List<? extends BoundedSource> splitIntoBundles(long desiredBundleSizeBytes, RuntimeContainer adaptor) throws Exception {
       // There can be only one.
       return Arrays.asList(this);
    }

    @Override
    public long getEstimatedSizeBytes(RuntimeContainer adaptor) {
       // This will be ignored since the source will never be split.
       return 0;
    }

    @Override
    public boolean producesSortedKeys(RuntimeContainer adaptor) {
       return false;
    }
    
    @Override
    public ${componentName}Reader createReader(RuntimeContainer container) {
        return new ${componentName}Reader(this);
    }
    
    /**
     * Creates converter, which converts delimited string to
     * {@link IndexedRecord} and vice versa. <code>delimitedString</code> is
     * used to infer Runtime schema in case Design schema contains dynamic field
     * 
     * @param delimitedString
     *            a line, which was read from file source
     * @return {@link AvroConverter} from delimited string to
     *         {@link IndexedRecord}
     */
    AvroConverter<String, IndexedRecord> createConverter(String delimitedString) {
        Schema runtimeSchema = getRuntimeSchema(delimitedString);
        AvroConverter<String, IndexedRecord> converter = new DelimitedStringConverter(runtimeSchema, getDelimiter());
        return converter;
    }
    
	/**
	 * Creates Root schema, which is used during IndexedRecord creation
	 * 
	 * @param delimitedString
	 *            a line, which was read from file source
	 * @return avro Root schema
	 */
	Schema createRootSchema(String delimitedString) {
		Schema runtimeSchema = getRuntimeSchema(delimitedString);
		Schema rootSchema = RootSchemaUtils.createRootSchema(runtimeSchema, ${componentName}Properties.outOfBandSchema);
		return rootSchema;
	}

    Schema getDesignSchema() {
        return inputProperties.schema.schema.getValue();
    }
    
    String getFilePath() {
        return inputProperties.filename.getValue();
    }
    
    String getDelimiter() {
        if (inputProperties.useCustomDelimiter.getValue()) {
            return inputProperties.customDelimiter.getValue();
        } else {
            return inputProperties.delimiter.getValue().getDelimiter();
        }
    }
    
    /**
	 * Creates Runtime schema from data line, if it is not exist yet and returns
	 * it
	 * 
	 * @param delimitedString
	 *            data line
	 * @return avro Runtime schema
	 */
	private Schema getRuntimeSchema(String delimitedString) {
		if (runtimeSchema == null) {
			runtimeSchema = new DelimitedStringSchemaInferrer(getDelimiter()).inferSchema(getDesignSchema(), delimitedString);
		}
		return runtimeSchema;
	}    
}
