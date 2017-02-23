package org.talend.components.netsuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.joda.time.DateTime;
import org.talend.components.netsuite.beans.BeanInfo;
import org.talend.components.netsuite.beans.BeanManager;
import org.talend.components.netsuite.beans.PropertyInfo;
import org.talend.components.netsuite.client.model.BeanUtils;
import org.talend.components.netsuite.test.TestFixture;
import org.talend.daikon.avro.AvroUtils;

import com.netsuite.webservices.v2016_2.platform.core.Record;
import com.netsuite.webservices.v2016_2.platform.core.RecordList;
import com.netsuite.webservices.v2016_2.platform.core.SearchResult;
import com.netsuite.webservices.v2016_2.platform.core.Status;

/**
 *
 */
public abstract class NetSuiteTestBase {

    protected static List<TestFixture> classScopedTestFixtures = new ArrayList<>();
    protected List<TestFixture> testFixtures = new ArrayList<>();

    protected static Random rnd = new Random(System.currentTimeMillis());
    protected static DatatypeFactory datatypeFactory;

    static {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public void setUp() throws Exception {
        for (TestFixture testFixture : testFixtures) {
            testFixture.setUp();
        }
    }

    public void tearDown() throws Exception {
        List<TestFixture> reversed = new ArrayList<>(testFixtures);
        Collections.reverse(reversed);
        for (TestFixture testFixture : reversed) {
            testFixture.tearDown();
        }
        testFixtures.clear();
    }

    public static void setUpClassScopedTestFixtures() throws Exception {
        for (TestFixture testFixture : classScopedTestFixtures) {
            testFixture.setUp();
        }
    }

    public static void tearDownClassScopedTestFixtures() throws Exception {
        List<TestFixture> reversed = new ArrayList<>(classScopedTestFixtures);
        Collections.reverse(reversed);
        for (TestFixture testFixture : reversed) {
            testFixture.tearDown();
        }
        classScopedTestFixtures.clear();
    }

    protected Schema getDynamicSchema() {
        Schema emptySchema = Schema.createRecord("dynamic", null, null, false);
        emptySchema.setFields(new ArrayList<Schema.Field>());
        emptySchema = AvroUtils.setIncludeAllFields(emptySchema, true);
        return emptySchema;
    }

    protected static Schema.Field getFieldByName(List<Schema.Field> fields, String name) {
        for (Schema.Field field : fields) {
            if (field.name().equals(name)) {
                return field;
            }
        }
        return null;
    }

    protected <T extends Record> List<SearchResult> makeRecordPages(
            Class<T> clazz, int count, int pageSize) throws Exception {

        int totalPages = count / pageSize;
        if (count % pageSize != 0) {
            totalPages += 1;
        }

        String searchId = UUID.randomUUID().toString();

        Status status = new Status();
        status.setIsSuccess(true);

        List<SearchResult> pageResults = new ArrayList<>();
        SearchResult result = null;
        while (count > 0) {
            Record record = composeObject(clazz);

            if (result == null) {
                result = new SearchResult();
                result.setSearchId(searchId);
                result.setTotalPages(totalPages);
                result.setTotalRecords(count);
                result.setPageIndex(pageResults.size() + 1);
                result.setPageSize(pageSize);
                result.setStatus(status);
            }

            if (result.getRecordList() == null) {
                result.setRecordList(new RecordList());
            }
            result.getRecordList().getRecord().add(record);

            if (result.getRecordList().getRecord().size() == pageSize) {
                pageResults.add(result);
                result = null;
            }

            count--;
        }

        if (result != null) {
            pageResults.add(result);
        }

        return pageResults;
    }

    protected <T> T composeObject(Class<T> clazz) throws Exception {
        BeanInfo beanInfo = BeanManager.getBeanInfo(clazz);
        List<PropertyInfo> propertyInfoList = beanInfo.getProperties();

        T obj = clazz.newInstance();

        for (PropertyInfo propertyInfo : propertyInfoList) {
            if (propertyInfo.getWriteType() != null) {
                Object value = composeValue(propertyInfo.getWriteType());
                BeanUtils.setProperty(obj, propertyInfo.getName(), value);
            }
        }

        return obj;
    }

    protected static Object composeValue(Class<?> clazz) throws Exception {
        if (clazz == Boolean.class) {
            return Boolean.valueOf(rnd.nextBoolean());
        }
        if (clazz == Long.class) {
            return Long.valueOf(rnd.nextLong());
        }
        if (clazz == Double.class) {
            return Double.valueOf(rnd.nextLong());
        }
        if (clazz == Integer.class) {
            return Integer.valueOf(rnd.nextInt());
        }
        if (clazz == String.class) {
            int len = 10 + rnd.nextInt(100);
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                sb.append((char) (32 + rnd.nextInt(127 - 32)));
            }
            return sb.toString();
        }
        if (clazz == XMLGregorianCalendar.class) {
            return composeDateTime();
        }
        if (clazz.isEnum()) {
            Object[] values = clazz.getEnumConstants();
            return values[rnd.nextInt(values.length)];
        }
        return null;
    }

    protected static XMLGregorianCalendar composeDateTime() throws Exception {
        DateTime dateTime = DateTime.now();

        XMLGregorianCalendar xts = datatypeFactory.newXMLGregorianCalendar();
        xts.setYear(dateTime.getYear());
        xts.setMonth(dateTime.getMonthOfYear());
        xts.setDay(dateTime.getDayOfMonth());
        xts.setHour(dateTime.getHourOfDay());
        xts.setMinute(dateTime.getMinuteOfHour());
        xts.setSecond(dateTime.getSecondOfMinute());
        xts.setMillisecond(dateTime.getMillisOfSecond());
        xts.setTimezone(dateTime.getZone().toTimeZone().getRawOffset() / 60000);

        return xts;

    }

}
