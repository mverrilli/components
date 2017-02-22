package org.talend.components.netsuite.client.v2016_2;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.talend.components.netsuite.client.NetSuiteException;
import org.talend.components.netsuite.client.model.AbstractMetaData;
import org.talend.components.netsuite.client.model.RecordTypeEx;
import org.talend.components.netsuite.client.model.search.SearchRecordTypeEx;

import com.netsuite.webservices.v2016_2.platform.core.BaseRef;
import com.netsuite.webservices.v2016_2.platform.core.CustomFieldRef;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchBooleanField;
import com.netsuite.webservices.v2016_2.platform.core.SearchCustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.SearchDateCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDateField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDoubleCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchDoubleField;
import com.netsuite.webservices.v2016_2.platform.core.SearchEnumMultiSelectCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchEnumMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchLongCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchLongField;
import com.netsuite.webservices.v2016_2.platform.core.SearchMultiSelectCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchMultiSelectField;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringCustomField;
import com.netsuite.webservices.v2016_2.platform.core.SearchStringField;
import com.netsuite.webservices.v2016_2.platform.core.SearchTextNumberField;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchDate;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchDateFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchDoubleFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchEnumMultiSelectFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchLongFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchMultiSelectFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchStringFieldOperator;
import com.netsuite.webservices.v2016_2.platform.core.types.SearchTextNumberFieldOperator;

import static org.talend.components.netsuite.client.model.BeanUtils.toInitialUpper;

/**
 *
 */
public class MetaDataImpl extends AbstractMetaData {

    private static final LazyInitializer<MetaDataImpl> initializer = new LazyInitializer<MetaDataImpl>() {
        @Override protected MetaDataImpl initialize() throws ConcurrentException {
            return new MetaDataImpl();
        }
    };

    public static MetaDataImpl getInstance() {
        try {
            return initializer.get();
        } catch (ConcurrentException e) {
            throw new NetSuiteException("Initialization error", e);
        }
    }

    public MetaDataImpl() {
        logger.info("Initializing standard metadata...");
        long startTime = System.currentTimeMillis();

        registerCustomFieldAdapters();

        registerTypes(BaseRef.class);
        registerTypes(CustomFieldRef.class);
        registerType(SearchCustomFieldList.class, null);

        registerSearchFields(Arrays.asList(
                SearchBooleanCustomField.class,
                SearchBooleanField.class,
                SearchDateCustomField.class,
                SearchDateField.class,
                SearchDoubleCustomField.class,
                SearchDoubleField.class,
                SearchEnumMultiSelectField.class,
                SearchEnumMultiSelectCustomField.class,
                SearchMultiSelectCustomField.class,
                SearchMultiSelectField.class,
                SearchLongCustomField.class,
                SearchLongField.class,
                SearchStringCustomField.class,
                SearchStringField.class,
                SearchTextNumberField.class
        ));

        registerSearchFieldOperatorTypes(Arrays.<Pair<String, Class<?>>>asList(
                ImmutablePair.<String, Class<?>>of("Date", SearchDateFieldOperator.class),
                ImmutablePair.<String, Class<?>>of("PredefinedDate", SearchDate.class),
                ImmutablePair.<String, Class<?>>of("Numeric", SearchLongFieldOperator.class),
                ImmutablePair.<String, Class<?>>of("Double", SearchDoubleFieldOperator.class),
                ImmutablePair.<String, Class<?>>of("String", SearchStringFieldOperator.class),
                ImmutablePair.<String, Class<?>>of("TextNumber", SearchTextNumberFieldOperator.class),
                ImmutablePair.<String, Class<?>>of("List", SearchMultiSelectFieldOperator.class),
                ImmutablePair.<String, Class<?>>of("List", SearchEnumMultiSelectFieldOperator.class)
        ));

        long endTime = System.currentTimeMillis();
        logger.info("Initialized standard metadata: " + (endTime - startTime));
    }

    @Override
    public Collection<RecordTypeEx> getRecordTypes() {
        return Arrays.<RecordTypeEx>asList(RecordTypeEnum.values());
    }

    @Override
    public RecordTypeEx getRecordType(String recordType) {
        return RecordTypeEnum.getByTypeName(toInitialUpper(recordType));
    }

    @Override
    public SearchRecordTypeEx getSearchRecordType(String searchRecordType) {
        return SearchRecordTypeEnum.getByTypeName(toInitialUpper(searchRecordType));
    }
}
