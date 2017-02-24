package org.talend.components.file.avro;

import org.apache.avro.Schema;
import org.talend.daikon.avro.AvroUtils;

/**
 * Converts String to avro float type and vice versa
 */
class StringToFloatConverter extends StringConverter<Float> {

	private static final Schema FLOAT_SCHEMA = AvroUtils._float();

	/**
	 * Returns schema of float avro type
	 * 
	 * @return schema of float avro type
	 */
	@Override
	public Schema getSchema() {
		return FLOAT_SCHEMA;
	}

	@Override
	public String convertToDatum(Float value) {
		return value.toString();
	}

	@Override
	public Float convertToAvro(String value) {
		return Float.valueOf(value);
	}

}
