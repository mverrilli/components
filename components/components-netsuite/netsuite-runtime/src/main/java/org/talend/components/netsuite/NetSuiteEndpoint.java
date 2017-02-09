package org.talend.components.netsuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.apache.commons.lang3.StringUtils;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.netsuite.client.NetSuiteConnection;
import org.talend.components.netsuite.client.NetSuiteFactory;
import org.talend.components.netsuite.client.NetSuiteCredentials;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.NetSuiteMetaData;
import org.talend.components.netsuite.connection.NetSuiteConnectionProperties;
import org.talend.components.netsuite.schema.NsSchema;
import org.talend.components.netsuite.schema.NsSchemaImpl;
import org.talend.components.netsuite.runtime.SchemaService;
import org.talend.daikon.NamedThing;
import org.talend.daikon.SimpleNamedThing;
import org.talend.daikon.avro.AvroUtils;
import org.talend.daikon.avro.SchemaConstants;

/**
 *
 */
public class NetSuiteEndpoint implements SchemaService {

    private NetSuiteConnectionProperties properties;

    public NetSuiteEndpoint(NetSuiteConnectionProperties properties) {
        this.properties = properties;
    }

    public NetSuiteConnection connect() throws NetSuiteException {
        NetSuiteConnectionProperties connProps = properties.getConnectionProperties();

        if (StringUtils.isEmpty(connProps.endpoint.getValue())) {
            throw new NetSuiteException("Invalid endpoint URL");
        }
        if (StringUtils.isEmpty(connProps.email.getValue())) {
            throw new NetSuiteException("Invalid email");
        }
        if (StringUtils.isEmpty(connProps.account.getValue())) {
            throw new NetSuiteException("Invalid account");
        }
//        if (StringUtils.isEmpty(connProps.applicationId.getValue())) {
//            throw new NetSuiteException("Invalid application ID");
//        }

        String endpointUrl = StringUtils.strip(connProps.endpoint.getStringValue(), "\"");
        String apiVersion = StringUtils.strip(connProps.apiVersion.getStringValue(), "\"");
        String email = StringUtils.strip(connProps.email.getStringValue(), "\"");
        String password = StringUtils.strip(connProps.password.getStringValue(), "\"");
        Integer roleId = connProps.role.getValue();
        String account = StringUtils.strip(connProps.account.getStringValue(), "\"");
        String applicationId = StringUtils.strip(connProps.applicationId.getStringValue(), "\"");

        NetSuiteCredentials credentials = new NetSuiteCredentials();
        credentials.setEmail(email);
        credentials.setPassword(password);
        credentials.setRoleId(roleId.toString());
        credentials.setAccount(account);
        credentials.setApplicationId(applicationId);

        NetSuiteConnection conn = connect(endpointUrl, apiVersion, credentials);
        return conn;
    }

    protected NetSuiteConnection connect(String endpointUrl, String apiVersion, NetSuiteCredentials credentials)
            throws NetSuiteException {

        NetSuiteConnection conn = NetSuiteFactory.getConnection(apiVersion);
        conn.setEndpointUrl(endpointUrl);
        conn.setCredentials(credentials);
        return conn;
    }

    @Override
    public List<NamedThing> getSchemaNames() {
        try {
            NetSuiteMetaData metaData = NetSuiteFactory.getMetaData(properties.getApiVersion());

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
            NetSuiteMetaData metaData = NetSuiteFactory.getMetaData(properties.getApiVersion());
            NetSuiteMetaData.EntityInfo entityInfo = metaData.getEntity(typeName);

            Schema schema = inferSchemaForEntity(entityInfo);
            return schema;
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public NsSchema getSearchSchema(String typeName) {
        try {
            NetSuiteMetaData metaData = NetSuiteFactory.getMetaData(properties.getApiVersion());
            final NetSuiteMetaData.SearchInfo searchInfo = metaData.getSearchInfo(typeName);
            final NetSuiteMetaData.EntityInfo searchRecordInfo = metaData.getEntity(searchInfo.getSearchBasicClass());
            List<NetSuiteMetaData.FieldInfo> searchFieldInfos = searchRecordInfo.getFields();
            List<String> fieldNames = new ArrayList<>(searchFieldInfos.size());
            for (NetSuiteMetaData.FieldInfo fieldInfo : searchFieldInfos) {
                fieldNames.add(fieldInfo.getName());
            }
            return new NsSchemaImpl(searchRecordInfo.getName(), searchFieldInfos);
        } catch (NetSuiteException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public List<String> getSearchFieldOperators() {
        try {
            NetSuiteMetaData metaData = NetSuiteFactory.getMetaData(properties.getApiVersion());
            List<NetSuiteMetaData.SearchFieldOperatorName> operatorList =
                    new ArrayList<>(metaData.getSearchOperatorNames());
            List<String> operatorNames = new ArrayList<>(operatorList.size());
            for (NetSuiteMetaData.SearchFieldOperatorName operatorName : operatorList) {
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
    public static Schema inferSchemaForEntity(NetSuiteMetaData.EntityInfo in) {
        List<Schema.Field> fields = new ArrayList<>();

        for (NetSuiteMetaData.FieldInfo fieldInfo : in.getFields()) {

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

        return Schema.createRecord(in.getName(), null, null, false, fields);
    }

    /**
     * Infers an Avro schema for the given FieldInfo. This can be an expensive operation so the schema should be
     * cached where possible. The return type will be the Avro Schema that can contain the fieldInfo data without loss of
     * precision.
     *
     * @param fieldInfo the <code>FieldInfo</code> to analyse.
     * @return the schema for data that the fieldInfo describes.
     */
    public static Schema inferSchemaForField(NetSuiteMetaData.FieldInfo fieldInfo) {
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
