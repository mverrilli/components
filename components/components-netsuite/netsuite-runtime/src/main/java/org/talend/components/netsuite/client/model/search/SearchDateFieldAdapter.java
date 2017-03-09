package org.talend.components.netsuite.client.model.search;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.BasicMetaData;

import static org.talend.components.netsuite.client.model.beans.Beans.setProperty;

/**
 *
 */
public class SearchDateFieldAdapter<T> extends SearchFieldAdapter<T> {

    private DatatypeFactory datatypeFactory;

    public SearchDateFieldAdapter(BasicMetaData metaData, SearchFieldType fieldType, Class<T> fieldClass) {
        super(metaData, fieldType, fieldClass);

        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new NetSuiteException("Failed to create XML data type factory", e);
        }
    }

    @Override
    public T populate(T fieldObject, String internalId, String operatorName, List<String> values) {
        T nsObject = fieldObject != null ? fieldObject : createField(internalId);

        SearchFieldOperatorName operatorQName =
                new SearchFieldOperatorName(operatorName);

        if (operatorQName.getDataType().equals("PredefinedDate")) {
            setProperty(nsObject, "predefinedSearchValue",
                    metaData.getSearchFieldOperatorByName(fieldType.getFieldTypeName(), operatorName));
        } else {
            if (values != null && values.size() != 0) {
                Calendar calValue = Calendar.getInstance();

                String dateFormat = "yyyy-MM-dd";
                String timeFormat = "HH:mm:ss";

                String format = dateFormat + " " + timeFormat;

                if (values.get(0).length() == dateFormat.length()) {
                    format = dateFormat;
                }

                if (values.get(0).length() == timeFormat.length()) {
                    values.set(0, new SimpleDateFormat(dateFormat)
                            .format(calValue.getTime()) + " " + values.get(0));
                }

                DateFormat df = new SimpleDateFormat(format);

                try {
                    calValue.setTime(df.parse(values.get(0)));
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Invalid date/time format: " + values.get(0));
                }

                XMLGregorianCalendar xts = datatypeFactory.newXMLGregorianCalendar();
                xts.setYear(calValue.get(Calendar.YEAR));
                xts.setMonth(calValue.get(Calendar.MONTH) + 1);
                xts.setDay(calValue.get(Calendar.DAY_OF_MONTH));
                xts.setHour(calValue.get(Calendar.HOUR_OF_DAY));
                xts.setMinute(calValue.get(Calendar.MINUTE));
                xts.setSecond(calValue.get(Calendar.SECOND));
                xts.setMillisecond(calValue.get(Calendar.MILLISECOND));
                xts.setTimezone(calValue.get(Calendar.ZONE_OFFSET) / 60000);

                setProperty(nsObject,"searchValue", xts);

                if (values.size() > 1) {
                    try {
                        calValue.setTime(df.parse(values.get(1)));
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Invalid date/time format: " + values.get(1));
                    }

                    XMLGregorianCalendar xts2 = datatypeFactory.newXMLGregorianCalendar();
                    xts2.setYear(calValue.get(Calendar.YEAR));
                    xts2.setMonth(calValue.get(Calendar.MONTH) + 1);
                    xts2.setDay(calValue.get(Calendar.DAY_OF_MONTH));
                    xts2.setHour(calValue.get(Calendar.HOUR_OF_DAY));
                    xts2.setMinute(calValue.get(Calendar.MINUTE));
                    xts2.setSecond(calValue.get(Calendar.SECOND));
                    xts2.setMillisecond(calValue.get(Calendar.MILLISECOND));
                    xts2.setTimezone(calValue.get(Calendar.ZONE_OFFSET) / 60000);

                    setProperty(nsObject, "searchValue2", xts2);
                }
            }

            setProperty(nsObject, "operator", metaData.getSearchFieldOperatorByName(fieldType.getFieldTypeName(), operatorName));
        }

        return nsObject;
    }
}
