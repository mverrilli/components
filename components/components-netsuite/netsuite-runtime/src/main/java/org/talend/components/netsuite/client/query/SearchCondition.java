package org.talend.components.netsuite.client.query;

import java.util.List;

/**
 *
 */
public class SearchCondition {
    private String fieldName;
    private String operatorName;
    private List<String> values;

    public SearchCondition() {
    }

    public SearchCondition(String fieldName, String operatorName) {
        this(fieldName, operatorName, null);
    }

    public SearchCondition(String fieldName, String operatorName, List<String> values) {
        this.fieldName = fieldName;
        this.operatorName = operatorName;
        this.values = values;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchCondition{");
        sb.append("fieldName='").append(fieldName).append('\'');
        sb.append(", operatorName='").append(operatorName).append('\'');
        sb.append(", values=").append(values);
        sb.append('}');
        return sb.toString();
    }
}
