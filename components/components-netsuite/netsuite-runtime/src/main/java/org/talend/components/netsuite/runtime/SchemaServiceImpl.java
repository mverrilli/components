package org.talend.components.netsuite.runtime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.metadata.FieldInfo;
import org.talend.components.netsuite.client.metadata.SearchFieldOperatorTypeInfo;
import org.talend.components.netsuite.client.metadata.SearchRecordInfo;
import org.talend.components.netsuite.client.metadata.TypeInfo;
import org.talend.components.netsuite.schema.NsSchema;
import org.talend.components.netsuite.schema.NsSchemaImpl;
import org.talend.daikon.NamedThing;
import org.talend.daikon.avro.AvroUtils;
import org.talend.daikon.avro.SchemaConstants;

/**
 *
 */
public class SchemaServiceImpl implements SchemaService {
    private NetSuiteClientService clientService;

    public SchemaServiceImpl(NetSuiteClientService clientService) throws NetSuiteException {
        this.clientService = clientService;
    }

    @Override
    public List<NamedThing> getSchemaNames() {
        try {
            List<NamedThing> recordTypes = new ArrayList<>(clientService.getRecordTypes());
            // Sort by display name alphabetically
            Collections.sort(recordTypes, new Comparator<NamedThing>() {
                @Override public int compare(NamedThing o1, NamedThing o2) {
                    return o1.getDisplayName().compareTo(o2.getDisplayName());
                }
            });
            return recordTypes;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public Schema getSchema(String typeName) {
        try {
            TypeInfo def = clientService.getTypeInfo(typeName);
            Schema schema = inferSchemaForRecord(def.getTypeName(), def.getFields());
            return schema;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public List<NamedThing> getSearches() {
        try {
            List<NamedThing> searches = new ArrayList<>(clientService.getSearches());
            // Sort by display name alphabetically
            Collections.sort(searches, new Comparator<NamedThing>() {
                @Override public int compare(NamedThing o1, NamedThing o2) {
                    return o1.getDisplayName().compareTo(o2.getDisplayName());
                }
            });
            return searches;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public NsSchema getSearchRecordSchema(String typeName) {
        try {
            final SearchRecordInfo searchInfo = clientService.getSearchRecordInfo(typeName);
            final TypeInfo searchRecordInfo = clientService.getTypeInfo(searchInfo.getSearchBasicClass());
            List<FieldInfo> searchFieldInfos = searchRecordInfo.getFields();
            return new NsSchemaImpl(searchRecordInfo.getTypeName(), searchFieldInfos);
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public NsSchema getDeleteRecordSchema(String typeName) {
        try {
            final TypeInfo def = clientService.getTypeInfo("RecordRef");
            return new NsSchemaImpl(def.getTypeName(), def.getFields());
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public List<String> getSearchFieldOperators() {
        List<SearchFieldOperatorTypeInfo.QualifiedName> operatorList =
                new ArrayList<>(clientService.getSearchOperatorNames());
        List<String> operatorNames = new ArrayList<>(operatorList.size());
        for (SearchFieldOperatorTypeInfo.QualifiedName operatorName : operatorList) {
            operatorNames.add(operatorName.getQualifiedName());
        }
        return operatorNames;
    }

    /**
     * Infers an Avro schema for the given NsObject. This can be an expensive operation so the schema
     * should be cached where possible. This is always an {@link Schema.Type#RECORD}.
     *
     * @param name name of a record.
     * @return the schema for data given from the object.
     */
    public static Schema inferSchemaForRecord(String name, List<FieldInfo> fieldInfoList) {
        List<Schema.Field> fields = new ArrayList<>();

        for (FieldInfo fieldInfo : fieldInfoList) {

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

        return Schema.createRecord(name, null, null, false, fields);
    }

    /**
     * Infers an Avro schema for the given FieldInfo. This can be an expensive operation so the schema should be
     * cached where possible. The return type will be the Avro Schema that can contain the fieldInfo data without loss of
     * precision.
     *
     * @param fieldInfo the <code>FieldInfo</code> to analyse.
     * @return the schema for data that the fieldInfo describes.
     */
    public static Schema inferSchemaForField(FieldInfo fieldInfo) {
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
