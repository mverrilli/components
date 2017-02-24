package org.talend.components.file.avro;

import org.apache.avro.Schema;
import org.talend.daikon.avro.AvroUtils;

/**
 * Converts String to avro int type and vice versa
 */
class StringToIntConverter extends StringConverter<Integer> {

	private static final Schema INT_SCHEMA = AvroUtils._int();

	/**
	 * Returns schema of int avro type
	 * 
	 * @return schema of int avro type
	 */
	@Override
	public Schema getSchema() {
		return INT_SCHEMA;
	}

	@Override
	public String convertToDatum(Integer value) {
		return value.toString();
	}

	@Override
	public Integer convertToAvro(String value) {
		return Integer.parseInt(value);
	}

}
