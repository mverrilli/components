package org.talend.components.file.avro;

import org.talend.daikon.avro.converter.AvroConverter;

/**
 * Common abstract field converter for delimited string Inheritors should have
 * package access. They are not supposed to be used outside of this package
 */
abstract class StringConverter<AvroT> implements AvroConverter<String, AvroT> {

	/**
	 * Returns datum class, which is String
	 * 
	 * @return String.class
	 */
	@Override
	public Class<String> getDatumClass() {
		return String.class;
	}

}
