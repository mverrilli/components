package org.talend.components.file.avro;

import org.apache.avro.Schema;
import org.talend.daikon.avro.AvroUtils;

/**
 * Converts String to avro long type and vice versa
 */
class StringToLongConverter extends StringConverter<Long> {

	private static final Schema LONG_SCHEMA = AvroUtils._long();

	/**
	 * Returns schema of long avro type
	 * 
	 * @return schema of long avro type
	 */
	@Override
	public Schema getSchema() {
		return LONG_SCHEMA;
	}

	@Override
	public String convertToDatum(Long value) {
		return value.toString();
	}

	@Override
	public Long convertToAvro(String value) {
		return Long.parseLong(value);
	}

}
