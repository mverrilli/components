package org.talend.components.file.avro;

import org.apache.avro.Schema;
import org.talend.daikon.avro.AvroUtils;

/**
 * Converts String to avro double type and vice versa
 */
class StringToDoubleConverter extends StringConverter<Double> {

	private static final Schema DOUBLE_SCHEMA = AvroUtils._double();

	/**
	 * Returns schema of double avro type
	 * 
	 * @return schema of double avro type
	 */
	@Override
	public Schema getSchema() {
		return DOUBLE_SCHEMA;
	}

	@Override
	public String convertToDatum(Double value) {
		return value.toString();
	}

	@Override
	public Double convertToAvro(String value) {
		return Double.valueOf(value);
	}

}
