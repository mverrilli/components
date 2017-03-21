package org.talend.components.snowflake;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit-tests for {@link SnowflakeConnectionProperties} class
 */
public class SnowflakeConnectionPropertiesTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SnowflakeConnectionPropertiesTest.class);

    private SnowflakeConnectionProperties snowflakeConnectionProperties;

    @Before
    public void setUp() throws Exception {
        snowflakeConnectionProperties = new SnowflakeConnectionProperties("name");
        snowflakeConnectionProperties.setupProperties();
        snowflakeConnectionProperties.account.setValue("snowflakeAccount");
        snowflakeConnectionProperties.userPassword.password.setValue("password");
        snowflakeConnectionProperties.warehouse.setValue("warehouse");
        snowflakeConnectionProperties.db.setValue("db");
        snowflakeConnectionProperties.schemaName.setValue("schemaName");
        snowflakeConnectionProperties.role.setValue("role");
    }

    /**
     * Checks {@link SnowflakeConnectionProperties#getConnectionUrl()} returns {@link java.lang.String} snowflake url
     * when all params are valid
     */
    @Test
    public void testGetConnectionUrlValidAccount() throws Exception {
        String expectedUrl =
                "jdbc:snowflake://snowflakeAccount.snowflakecomputing.com/" +
                        "?warehouse=warehouse&db=db&schema=schemaName&role=role&tracing=OFF";
        String resultUrl = snowflakeConnectionProperties.getConnectionUrl();

        LOGGER.debug("result url: " + resultUrl);

        Assert.assertEquals(expectedUrl, resultUrl);
    }

    /**
     * Checks {@link SnowflakeConnectionProperties#getConnectionUrl()} throws {@link java.lang.IllegalArgumentException}
     * when snowflake account {@link java.lang.String} value is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetConnectionUrlNullAccount() throws Exception {
        snowflakeConnectionProperties.account.setValue(null);

        snowflakeConnectionProperties.getConnectionUrl();
    }

    /**
     * Checks {@link SnowflakeConnectionProperties#getConnectionUrl()} throws {@link java.lang.IllegalArgumentException}
     * when snowflake account {@link java.lang.String} value equals ignore case to the literal null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetConnectionUrlNullLiteralAccount() throws Exception {
        snowflakeConnectionProperties.account.setValue("null");

        snowflakeConnectionProperties.getConnectionUrl();
    }
}
