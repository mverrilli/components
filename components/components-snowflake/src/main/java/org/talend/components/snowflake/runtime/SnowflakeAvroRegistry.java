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
package org.talend.components.snowflake.runtime;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.common.avro.JDBCAvroRegistry;
import org.talend.daikon.avro.AvroUtils;
import org.talend.daikon.avro.SchemaConstants;

public class SnowflakeAvroRegistry extends JDBCAvroRegistry {

    private static final SnowflakeAvroRegistry sInstance = new SnowflakeAvroRegistry();

    public static SnowflakeAvroRegistry get() {
        return sInstance;
    }

    @Override
    protected Field sqlType2Avro(int size, int scale, int dbtype, boolean nullable, String name, String dbColumnName,
            Object defaultValue, boolean isKey) {
        Field field = null;
        Schema schema = null;

        switch (dbtype) {
        case java.sql.Types.VARCHAR:
        case java.sql.Types.LONGVARCHAR:
        case java.sql.Types.CHAR:
            schema = AvroUtils._string();
            field = wrap(nullable, schema, name);
            field.addProp(SchemaConstants.TALEND_COLUMN_DB_LENGTH, size);
            break;
        case java.sql.Types.INTEGER:
        case java.sql.Types.DECIMAL:
        case java.sql.Types.BIGINT:
        case java.sql.Types.NUMERIC:
        case java.sql.Types.TINYINT:
        case java.sql.Types.SMALLINT:
            schema = AvroUtils._decimal();
            field = wrap(nullable, schema, name);
            field.addProp(SchemaConstants.TALEND_COLUMN_PRECISION, size);
            field.addProp(SchemaConstants.TALEND_COLUMN_SCALE, scale);
            break;
        case java.sql.Types.DOUBLE:
        case java.sql.Types.FLOAT:
        case java.sql.Types.REAL:
            schema = AvroUtils._double();
            field = wrap(nullable, schema, name);
            break;
        case java.sql.Types.DATE:
            schema = AvroUtils._int();
            LogicalTypes.date().addToSchema(schema);
            field = wrap(nullable, schema, name);
            break;
        case java.sql.Types.TIME:
            schema = AvroUtils._int();
            LogicalTypes.timeMillis().addToSchema(schema);
            field = wrap(nullable, schema, name);
            break;
        case java.sql.Types.TIMESTAMP:
            schema = AvroUtils._long();
            LogicalTypes.timestampMillis().addToSchema(schema);
            field = wrap(nullable, schema, name);
            break;
        case java.sql.Types.BOOLEAN:
            schema = AvroUtils._boolean();
            field = wrap(nullable, schema, name);
            break;
        default:
            schema = AvroUtils._string();
            field = wrap(nullable, schema, name);
            break;
        }

        field.addProp(SchemaConstants.TALEND_COLUMN_DB_TYPE, dbtype);
        field.addProp(SchemaConstants.TALEND_COLUMN_DB_COLUMN_NAME, dbColumnName);

        if (defaultValue != null) {
            field.addProp(SchemaConstants.TALEND_COLUMN_DEFAULT, defaultValue);
        }
        
        if (isKey) {
            field.addProp(SchemaConstants.TALEND_COLUMN_IS_KEY, "true");
        }

        return field;
    }

    public JDBCConverter getConverter(final Field f) {
        Schema basicSchema = AvroUtils.unwrapIfNullable(f.schema());

        if (basicSchema.getLogicalType() == LogicalTypes.date()) {
            return new JDBCConverter() {

                @Override
                public Object convertToAvro(ResultSet value) {
                    int index = f.pos() + 1;
                    try {
                        // Snowflake stores the value as the number of days. So it is possible to retrieve that as an
                        // int value instead of converting it to Date first and then to days from milliseconds. If we
                        // convert it to date, Snowflake jdbc shifts the time to 00:00 in current timezone.
                        return value.getInt(index);
                    } catch (SQLException e) {
                        throw new ComponentException(e);
                    }
                }
            };
        } else if (basicSchema.getLogicalType() == LogicalTypes.timeMillis()) {
            return new JDBCConverter() {

                @Override
                public Object convertToAvro(ResultSet value) {
                    int index = f.pos() + 1;
                    try {
                        // Snowflake - milliseconds since midnight
                        java.sql.Time time = value.getTime(index);
                        return (time != null) ? (int) value.getTime(index).getTime() : null;
                    } catch (SQLException e) {
                        throw new ComponentException(e);
                    }
                }
            };
        } else if (basicSchema.getLogicalType() == LogicalTypes.timestampMillis()) {
            return new JDBCConverter() {

                @Override
                public Object convertToAvro(ResultSet value) {
                    int index = f.pos() + 1;
                    try {
                        // Milliseconds since epoc
                        java.sql.Timestamp timestamp = value.getTimestamp(index);
                        return (timestamp != null) ? timestamp.getTime() : null;
                    } catch (SQLException e) {
                        throw new ComponentException(e);
                    }
                }
            };
        } else {
            return super.getConverter(f);
        }
    }
}
