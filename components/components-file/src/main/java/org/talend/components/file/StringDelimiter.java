package org.talend.components.file;

/**
 * Enumeration of standard string delimiters
 * 
 * This shows possible values for dropdown list UI element i18n message for it
 * should be placed in ${componentName}Properties.properties file
 */
public enum StringDelimiter {
	SEMICOLON {

		@Override
		public String getDelimiter() {
			return ";";
		}

	},
	COLON {

		@Override
		public String getDelimiter() {
			return ":";
		}

	},
	COMMA {

		@Override
		public String getDelimiter() {
			return ",";
		}
	};

	public abstract String getDelimiter();
}
