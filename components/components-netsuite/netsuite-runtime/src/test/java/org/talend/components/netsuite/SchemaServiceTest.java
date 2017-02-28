package org.talend.components.netsuite;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.avro.Schema;
import org.junit.Test;
import org.talend.components.netsuite.client.NetSuiteClientService;
import org.talend.components.netsuite.client.model.FieldDesc;
import org.talend.components.netsuite.client.model.TypeDesc;
import org.talend.daikon.avro.SchemaConstants;

/**
 *
 */
public class SchemaServiceTest extends NetSuiteMockTestBase {

    private NetSuiteClientService clientService = NetSuiteClientService.create("2016.2");

    @Test
    public void testInferSchemaForRecordBasic() throws Exception {
        TypeDesc typeDesc = clientService.getBasicTypeInfo("Account");

        Schema s = SchemaServiceImpl.inferSchemaForType(typeDesc.getTypeName(), typeDesc.getFields());
//        System.out.println(s);

        assertThat(s.getType(), is(Schema.Type.RECORD));
        assertThat(s.getName(), is("Account"));
        assertThat(s.getFields(), hasSize(typeDesc.getFields().size()));
        assertThat(s.getObjectProps().keySet(), empty());

        FieldDesc fieldDesc = typeDesc.getField("AcctType");
        Schema.Field f = s.getField(fieldDesc.getName());
        assertUnionType(f.schema(), Arrays.asList(Schema.Type.STRING, Schema.Type.NULL));
        assertThat(f.schema().getObjectProps().keySet(), empty());

        fieldDesc = typeDesc.getField("AcctName");
        f = s.getField(fieldDesc.getName());
        assertUnionType(f.schema(), Arrays.asList(Schema.Type.STRING, Schema.Type.NULL));
        assertThat(f.schema().getObjectProps().keySet(), empty());

        fieldDesc = typeDesc.getField("Inventory");
        f = s.getField(fieldDesc.getName());
        assertUnionType(f.schema(), Arrays.asList(Schema.Type.BOOLEAN, Schema.Type.NULL));
        assertThat(f.schema().getObjectProps().keySet(), empty());

        fieldDesc = typeDesc.getField("TranDate");
        f = s.getField(fieldDesc.getName());
        assertUnionType(f.schema(), Arrays.asList(Schema.Type.LONG, Schema.Type.NULL));
        assertThat(f.getObjectProps().keySet(), containsInAnyOrder(SchemaConstants.TALEND_COLUMN_PATTERN));
        assertThat(f.getProp(SchemaConstants.TALEND_COLUMN_PATTERN), is("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
    }

    @Test
    public void testGetSearchFieldOperators() {
        SchemaService schemaService = new SchemaServiceImpl(clientService);
        List<String> operators = schemaService.getSearchFieldOperators();
        for (String operator : operators) {
            assertNotNull(operator);
//            System.out.println(operator);
        }
    }

    private static void assertUnionType(Schema schema, List<Schema.Type> types) {
        assertThat(schema.getType(), is(Schema.Type.UNION));
        List<Schema> members = schema.getTypes();
        List<Schema.Type> memberTypes = new ArrayList<>(members.size());
        for (Schema member : members) {
            memberTypes.add(member.getType());
        }
        assertThat(types, is(memberTypes));
    }
}
