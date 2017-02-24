package org.talend.components.file.avro;

import org.apache.avro.Schema;
import org.talend.daikon.avro.AvroUtils;

/**
 * Converts String to avro boolean type and vice versa
 */
class StringToBooleanConverter extends StringConverter<Boolean> {

	private static final Schema BOOLEAN_SCHEMA = AvroUtils._boolean();

	/**
	 * Returns schema of boolean avro type
	 * 
	 * @return schema of boolean avro type
	 */
	@Override
	public Schema getSchema() {
		return BOOLEAN_SCHEMA;
	}

	@Override
	public String convertToDatum(Boolean value) {
		return value.toString();
	}

	@Override
	public Boolean convertToAvro(String value) {
		return Boolean.parseBoolean(value);
	}

}
