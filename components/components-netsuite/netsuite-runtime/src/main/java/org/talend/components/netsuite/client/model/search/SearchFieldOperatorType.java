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

package org.talend.components.netsuite.client.model.search;

/**
 *
 */
public enum SearchFieldOperatorType {
    BOOLEAN("Boolean", "SearchBooleanFieldOperator"),
    STRING("String", "SearchStringFieldOperator"),
    LONG("Long", "SearchLongFieldOperator"),
    DOUBLE("Double", "SearchDoubleFieldOperator"),
    DATE("Date", "SearchDateFieldOperator"),
    PREDEFINED_DATE("PredefinedDate", "SearchDate"),
    TEXT_NUMBER("TextNumber", "SearchTextNumberFieldOperator"),
    MULTI_SELECT("List", "SearchMultiSelectFieldOperator"),
    ENUM_MULTI_SELECT("List", "SearchEnumMultiSelectFieldOperator");

    private String dataType;
    private String operatorTypeName;

    SearchFieldOperatorType(String dataType, String operatorTypeName) {
        this.dataType = dataType;
        this.operatorTypeName = operatorTypeName;
    }

    public String getDataType() {
        return dataType;
    }

    public String getOperatorTypeName() {
        return operatorTypeName;
    }

    public boolean dataTypeEquals(String thatDataType) {
        return this.dataType.equals(thatDataType);
    }

    public static class SearchBooleanFieldOperator {

        public static final SearchFieldOperatorName NAME =
                new SearchFieldOperatorName(SearchFieldOperatorType.BOOLEAN.getDataType(), null);

        public static final SearchBooleanFieldOperator INSTANCE = new SearchBooleanFieldOperator();
    }

}
