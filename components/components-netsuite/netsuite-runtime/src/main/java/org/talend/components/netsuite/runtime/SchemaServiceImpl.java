package org.talend.components.netsuite.runtime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.components.netsuite.client.metadata.NsFieldDef;
import org.talend.components.netsuite.client.metadata.NsSearchDef;
import org.talend.components.netsuite.client.metadata.NsSearchFieldOperatorTypeDef;
import org.talend.components.netsuite.client.metadata.NsTypeDef;
import org.talend.components.netsuite.schema.NsSchema;
import org.talend.components.netsuite.schema.NsSchemaImpl;
import org.talend.daikon.NamedThing;
import org.talend.daikon.SimpleNamedThing;
import org.talend.daikon.avro.AvroUtils;
import org.talend.daikon.avro.SchemaConstants;

/**
 *
 */
public class SchemaServiceImpl implements SchemaService {
    private NetSuiteConnection connection;

    public SchemaServiceImpl(NetSuiteConnection connection) throws NetSuiteException {
        this.connection = connection;
    }

    @Override
    public List<NamedThing> getSchemaNames() {
        try {
            NetSuiteMetaData metaData = connection.getMetaData();

            List<String> recordTypes = new ArrayList<>(metaData.getRecordTypes());
            // Sort alphabetically
            Collections.sort(recordTypes);

            List<NamedThing> schemaNames = new ArrayList<>();
            for (String typeName : recordTypes) {
                schemaNames.add(new SimpleNamedThing(typeName, typeName));
            }

            return schemaNames;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public Schema getSchema(String typeName) {
        try {
            NetSuiteMetaData metaData = connection.getMetaData();

            NsTypeDef entityInfo = metaData.getTypeDef(typeName);

            Schema schema = inferSchemaForEntity(entityInfo);
            return schema;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public NsSchema getSearchSchema(String typeName) {
        try {
            NetSuiteMetaData metaData = connection.getMetaData();

            final NsSearchDef searchInfo = metaData.getSearchDef(typeName);
            final NsTypeDef searchRecordInfo = metaData.getTypeDef(searchInfo.getSearchBasicClass());
            List<NsFieldDef> searchFieldInfos = searchRecordInfo.getFields();
            List<String> fieldNames = new ArrayList<>(searchFieldInfos.size());
            for (NsFieldDef fieldInfo : searchFieldInfos) {
                fieldNames.add(fieldInfo.getName());
            }
            return new NsSchemaImpl(searchRecordInfo.getTypeName(), searchFieldInfos);
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public List<String> getSearchFieldOperators() {
        try {
            NetSuiteMetaData metaData = connection.getMetaData();

            List<NsSearchFieldOperatorTypeDef.QualifiedName> operatorList =
                    new ArrayList<>(metaData.getSearchOperatorNames());
            List<String> operatorNames = new ArrayList<>(operatorList.size());
            for (NsSearchFieldOperatorTypeDef.QualifiedName operatorName : operatorList) {
                operatorNames.add(operatorName.getQualifiedName());
            }
            return operatorNames;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    /**
     * Infers an Avro schema for the given NsObject. This can be an expensive operation so the schema
     * should be cached where possible. This is always an {@link Schema.Type#RECORD}.
     *
     * @param in the <code>EntityInfo</code> to analyse.
     * @return the schema for data given from the object.
     */
    public static Schema inferSchemaForEntity(NsTypeDef in) {
        List<Schema.Field> fields = new ArrayList<>();

        for (NsFieldDef fieldInfo : in.getFields()) {

            Schema.Field avroField = new Schema.Field(fieldInfo.getName(),
                    inferSchemaForField(fieldInfo), null, (Object) null);
            // Add some Talend6 custom properties to the schema.
            Schema avroFieldSchema = avroField.schema();
            if (avroFieldSchema.getType() == Schema.Type.UNION) {
                for (Schema schema : avroFieldSchema.getTypes()) {
                    if (avroFieldSchema.getType() != Schema.Type.NULL) {
                        avroFieldSchema = schema;
                        break;
                    }
                }
            }

            if (AvroUtils.isSameType(avroFieldSchema, AvroUtils._string())) {
                if (fieldInfo.getLength() != 0) {
                    avroField.addProp(SchemaConstants.TALEND_COLUMN_DB_LENGTH, String.valueOf(fieldInfo.getLength()));
                }
            }

            Class<?> fieldType = fieldInfo.getValueType();
            if (fieldType == XMLGregorianCalendar.class) {
                avroField.addProp(SchemaConstants.TALEND_COLUMN_PATTERN, "yyyy-MM-dd'T'HH:mm:ss'.000Z'");
            } else if (fieldType.isEnum()) {
                avroField.addProp(SchemaConstants.TALEND_COLUMN_DB_TYPE, fieldType.getName());
            }

            if (avroField.defaultVal() != null) {
                avroField.addProp(SchemaConstants.TALEND_COLUMN_DEFAULT, String.valueOf(avroField.defaultVal()));
            }

            fields.add(avroField);
        }

        return Schema.createRecord(in.getTypeName(), null, null, false, fields);
    }

    /**
     * Infers an Avro schema for the given FieldInfo. This can be an expensive operation so the schema should be
     * cached where possible. The return type will be the Avro Schema that can contain the fieldInfo data without loss of
     * precision.
     *
     * @param fieldInfo the <code>FieldInfo</code> to analyse.
     * @return the schema for data that the fieldInfo describes.
     */
    public static Schema inferSchemaForField(NsFieldDef fieldInfo) {
        Schema base;

        Class<?> fieldType = fieldInfo.getValueType();

        if (fieldType == Boolean.TYPE || fieldType == Boolean.class) {
            base = AvroUtils._boolean();
        } else if (fieldType == Integer.TYPE || fieldType == Integer.class) {
            base = AvroUtils._int();
        } else if (fieldType == Long.TYPE || fieldType == Long.class) {
            base = AvroUtils._long();
        } else if (fieldType == Float.TYPE || fieldType == Float.class) {
            base = AvroUtils._float();
        } else if (fieldType == Double.TYPE || fieldType == Double.class) {
            base = AvroUtils._double();
        } else if (fieldType == XMLGregorianCalendar.class) {
            base = AvroUtils._logicalTimestamp();
        } else if (fieldType == String.class) {
            base = AvroUtils._string();
        } else if (fieldType.isEnum()) {
            base = AvroUtils._string();
        } else {
            base = AvroUtils._string();
        }

        base = fieldInfo.isNullable() ? AvroUtils.wrapAsNullable(base) : base;

        return base;
    }

}
