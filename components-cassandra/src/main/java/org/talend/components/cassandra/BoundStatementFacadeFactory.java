package org.talend.components.cassandra;

import org.talend.daikon.schema.type.IndexedRecordFacadeFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.DataType;

/**
 * Creates an {@link IndexedRecordFacadeFactory} that knows how to interpret Cassandra {@link BoundStatement} objects.
 */
public class BoundStatementFacadeFactory extends CassandraBaseFacadeFactory<BoundStatement, BoundStatement, BoundStatement> {

    @Override
    public Class<BoundStatement> getDatumClass() {
        return BoundStatement.class;
    }

    @Override
    protected void setContainerTypeFromInstance(BoundStatement statement) {
        setContainerType(statement);
    }

    @Override
    public DataType getFieldType(int i) {
        return getContainerType().preparedStatement().getVariables().getType(i);
    }

    /**
     * This always returns the instance passed in {@link #setContainerType(BoundStatement)}.
     */
    @Override
    protected BoundStatement createOrGetInstance() {
        return getContainerType();
    }
}
