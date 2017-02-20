package org.talend.components.netsuite.client.metadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.talend.components.netsuite.beans.Mapper;

/**
 *
 */
public class SearchFieldOperatorTypeInfo<T> {
    private String dataType;
    private String typeName;
    private Class<T> operatorClass;
    private Mapper<T, String> mapper;
    private Mapper<String, T> reverseMapper;

    public SearchFieldOperatorTypeInfo(String dataType, Class<T> operatorClass,
            Mapper<T, String> mapper, Mapper<String, T> reverseMapper) {

        this.dataType = dataType;
        this.operatorClass = operatorClass;
        this.typeName = operatorClass.getSimpleName();
        this.mapper = mapper;
        this.reverseMapper = reverseMapper;
    }

    public String getTypeName() {
        return typeName;
    }

    public Class<T> getOperatorClass() {
        return operatorClass;
    }

    public Mapper<T, String> getMapper() {
        return mapper;
    }

    public Mapper<String, T> getReverseMapper() {
        return reverseMapper;
    }

    public String mapToString(T stringValue) {
        return mapper.map(stringValue);
    }

    public Object mapFromString(String stringValue) {
        return reverseMapper.map(stringValue);
    }

    public QualifiedName getOperatorName(Object value) {
        if (operatorClass == SearchBooleanFieldOperator.class) {
            return SearchBooleanFieldOperator.QUALIFIED_NAME;
        } else {
            return new QualifiedName(dataType, mapToString((T) value));
        }
    }

    public Object getOperator(String qualifiedName) {
        QualifiedName opName = new QualifiedName(qualifiedName);
        if (operatorClass == SearchBooleanFieldOperator.class) {
            if (!opName.equals(SearchBooleanFieldOperator.QUALIFIED_NAME)) {
                throw new IllegalArgumentException(
                        "Invalid operator type: " + "'" + qualifiedName + "' != '" + dataType + "'");
            }
            return SearchBooleanFieldOperator.INSTANCE;
        } else {
            if (!opName.getDataType().equals(dataType)) {
                throw new IllegalArgumentException(
                        "Invalid operator data type: " + "'" + opName.getDataType() + "' != '" + dataType + "'");
            }
            return mapFromString(opName.getName());
        }
    }

    public boolean hasOperatorName(QualifiedName operatorName) {
        return dataType.equals(operatorName.getDataType());
    }

    public List<QualifiedName> getOperatorNames() {
        if (operatorClass.isEnum()) {
            Enum[] values = ((Class<? extends Enum>) getOperatorClass()).getEnumConstants();
            List<QualifiedName> names = new ArrayList<>(values.length);
            for (Enum value : values) {
                names.add(getOperatorName(value));
            }
            return names;
        } else if (operatorClass == SearchBooleanFieldOperator.class) {
            return Arrays.asList(SearchBooleanFieldOperator.QUALIFIED_NAME);
        } else {
            throw new IllegalStateException("Unsupported operator type: " + operatorClass);
        }
    }

    public List<?> getOperatorValues() {
        if (operatorClass.isEnum()) {
            Enum[] values = ((Class<? extends Enum>) getOperatorClass()).getEnumConstants();
            return Arrays.asList(values);
        } else if (operatorClass == SearchBooleanFieldOperator.class) {
            return Arrays.asList(SearchBooleanFieldOperator.INSTANCE);
        } else {
            throw new IllegalStateException("Unsupported operator type: " + operatorClass);
        }
    }

    public static class SearchBooleanFieldOperator {

        public static final QualifiedName QUALIFIED_NAME =
                new QualifiedName("Boolean", null);

        public static final SearchBooleanFieldOperator INSTANCE = new SearchBooleanFieldOperator();
    }

    public static class QualifiedName {
        private String dataType;
        private String name;

        public QualifiedName(String qualifiedName) {
            int i = qualifiedName.indexOf(".");
            if (i == -1) {
                this.dataType = qualifiedName;
                this.name = null;
            } else {
                String thatDataType = qualifiedName.substring(0, i);
                if (thatDataType.isEmpty()) {
                    throw new IllegalArgumentException("Invalid operator data type: " + "'" + thatDataType + "'");
                }
                this.dataType = thatDataType;
                String thatName = qualifiedName.substring(i + 1);
                if (thatName.isEmpty()) {
                    throw new IllegalArgumentException("Invalid operator name: " + "'" + thatName + "'");
                }
                this.name = thatName;
            }
        }

        public QualifiedName(String dataType, String name) {
            this.dataType = dataType;
            this.name = name;
        }

        public String getDataType() {
            return dataType;
        }

        public String getName() {
            return name;
        }

        public String getQualifiedName() {
            return name != null ? dataType + "." + name : dataType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            QualifiedName that = (QualifiedName) o;
            return Objects.equals(dataType, that.dataType) && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dataType, name);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Name{");
            sb.append("dataType='").append(dataType).append('\'');
            sb.append(", name='").append(name).append('\'');
            sb.append(", qualifiedName='").append(getQualifiedName()).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
