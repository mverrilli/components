package org.talend.components.netsuite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.CustomFieldDesc;
import org.talend.components.netsuite.client.model.FieldDesc;
import org.talend.components.netsuite.client.model.RecordTypeInfo;
import org.talend.components.netsuite.client.model.SearchRecordTypeDesc;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.components.netsuite.client.model.customfield.CustomFieldRefType;
import org.talend.components.netsuite.client.model.search.SearchFieldOperatorType;
import org.talend.components.netsuite.schema.NsField;
import org.talend.components.netsuite.schema.NsSchema;
import org.talend.daikon.NamedThing;
import org.talend.daikon.SimpleNamedThing;
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
    public List<NamedThing> getRecordTypes() {
        try {
            Collection<RecordTypeInfo> recordTypeList = clientService.getRecordTypes();

            List<NamedThing> recordTypes = new ArrayList<>(recordTypeList.size());
            for (RecordTypeInfo recordTypeInfo : recordTypeList) {
                recordTypes.add(new SimpleNamedThing(recordTypeInfo.getName(), recordTypeInfo.getDisplayName()));
            }

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
            TypeDesc typeDesc = clientService.getTypeInfo(typeName);

            List<FieldDesc> fieldDescList = new ArrayList<>(typeDesc.getFields());
            // Sort by name alphabetically
            Collections.sort(fieldDescList, new Comparator<FieldDesc>() {
                @Override public int compare(FieldDesc o1, FieldDesc o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            Schema schema = inferSchemaForRecord(typeDesc.getTypeName(), typeDesc.getFields());
            return schema;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public List<NamedThing> getSearchableTypes() {
        try {
            List<NamedThing> searchableTypes = new ArrayList<>(clientService.getSearchableTypes());
            // Sort by display name alphabetically
            Collections.sort(searchableTypes, new Comparator<NamedThing>() {
                @Override public int compare(NamedThing o1, NamedThing o2) {
                    return o1.getDisplayName().compareTo(o2.getDisplayName());
                }
            });
            return searchableTypes;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public NsSchema getSchemaForSearch(String typeName) {
        try {
            final SearchRecordTypeDesc searchInfo = clientService.getSearchRecordType(typeName);
            final TypeDesc searchRecordInfo = clientService.getBasicTypeInfo(searchInfo.getSearchBasicClass());
            return toNsSchema(searchRecordInfo);
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public NsSchema getSchemaForUpdate(String typeName) {
        try {
            final TypeDesc typeDesc = clientService.getTypeInfo(typeName);
            return toNsSchema(typeDesc);
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public NsSchema getSchemaForDelete(String typeName) {
        try {
            final TypeDesc typeDesc = clientService.getTypeInfo("RecordRef");
            return toNsSchema(typeDesc);
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    public static NsSchema toNsSchema(final TypeDesc typeDesc) {
        List<FieldDesc> fieldDescList = typeDesc.getFields();

        List<NsField> fields = new ArrayList<>(fieldDescList.size());
        for (FieldDesc fieldDesc : fieldDescList) {
            NsField field = new NsField(fieldDesc.getName(), fieldDesc.getValueType());
            fields.add(field);
        }
        // Sort by name alphabetically
        Collections.sort(fields, new Comparator<NsField>() {
            @Override public int compare(NsField o1, NsField o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return new NsSchema(typeDesc.getTypeName(), fields);
    }

    @Override
    public List<String> getSearchFieldOperators() {
        List<SearchFieldOperatorType.QualifiedName> operatorList =
                new ArrayList<>(clientService.getSearchOperatorNames());
        List<String> operatorNames = new ArrayList<>(operatorList.size());
        for (SearchFieldOperatorType.QualifiedName operatorName : operatorList) {
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
    public static Schema inferSchemaForRecord(String name, List<FieldDesc> fieldDescList) {
        List<Schema.Field> fields = new ArrayList<>();

        for (FieldDesc fieldDesc : fieldDescList) {

            Schema.Field avroField = new Schema.Field(fieldDesc.getName(),
                    inferSchemaForField(fieldDesc), null, (Object) null);
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
                if (fieldDesc.getLength() != 0) {
                    avroField.addProp(SchemaConstants.TALEND_COLUMN_DB_LENGTH, String.valueOf(fieldDesc.getLength()));
                }
            }

            if (fieldDesc instanceof CustomFieldDesc) {
                CustomFieldDesc customFieldInfo = (CustomFieldDesc) fieldDesc;
                CustomFieldRefType customFieldRefType = customFieldInfo.getCustomFieldType();

                if (customFieldRefType == CustomFieldRefType.DATE) {
                    avroField.addProp(SchemaConstants.TALEND_COLUMN_PATTERN, "yyyy-MM-dd'T'HH:mm:ss'.000Z'");
                }
            } else {
                Class<?> fieldType = fieldDesc.getValueType();

                if (fieldType == XMLGregorianCalendar.class) {
                    avroField.addProp(SchemaConstants.TALEND_COLUMN_PATTERN, "yyyy-MM-dd'T'HH:mm:ss'.000Z'");
                }
            }

            if (avroField.defaultVal() != null) {
                avroField.addProp(SchemaConstants.TALEND_COLUMN_DEFAULT, String.valueOf(avroField.defaultVal()));
            }

            fields.add(avroField);
        }

        return Schema.createRecord(name, null, null, false, fields);
    }

    /**
     * Infers an Avro schema for the given FieldDesc. This can be an expensive operation so the schema should be
     * cached where possible. The return type will be the Avro Schema that can contain the fieldDesc data without loss of
     * precision.
     *
     * @param fieldDesc the <code>FieldDesc</code> to analyse.
     * @return the schema for data that the fieldDesc describes.
     */
    public static Schema inferSchemaForField(FieldDesc fieldDesc) {
        Schema base;

        if (fieldDesc instanceof CustomFieldDesc) {
            CustomFieldDesc customFieldInfo = (CustomFieldDesc) fieldDesc;
            CustomFieldRefType customFieldRefType = customFieldInfo.getCustomFieldType();

            if (customFieldRefType == CustomFieldRefType.BOOLEAN) {
                base = AvroUtils._boolean();
            } else if (customFieldRefType == CustomFieldRefType.LONG) {
                base = AvroUtils._int();
            } else if (customFieldRefType == CustomFieldRefType.DOUBLE) {
                base = AvroUtils._double();
            } else if (customFieldRefType == CustomFieldRefType.DATE) {
                base = AvroUtils._logicalTimestamp();
            } else if (customFieldRefType == CustomFieldRefType.STRING) {
                base = AvroUtils._string();
            } else {
                base = AvroUtils._string();
            }

        } else {
            Class<?> fieldType = fieldDesc.getValueType();

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
        }

        base = fieldDesc.isNullable() ? AvroUtils.wrapAsNullable(base) : base;

        return base;
    }

}
