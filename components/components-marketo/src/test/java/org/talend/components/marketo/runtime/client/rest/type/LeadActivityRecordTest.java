// ============================================================================
//
// Copyright (C) 2006-2017 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.components.marketo.runtime.client.rest.type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class LeadActivityRecordTest {

    LeadActivityRecord r;

    @Before
    public void setUp() throws Exception {
        r = new LeadActivityRecord();
        r.setId(1);
        r.setLeadId(2);
        r.setActivityTypeId(3);
        r.setActivityTypeValue("value");
        r.setActivityDate(new Date());
        r.setPrimaryAttributeValue("pvalue");
        r.setPrimaryAttributeValueId(1235);
        r.setAttributes(null);
    }

    @Test
    public void testGetters() throws Exception {

        assertEquals("1", r.getId().toString());
        assertEquals("2", r.getLeadId().toString());
        assertEquals("3", r.getActivityTypeId().toString());
        assertEquals("value", r.getActivityTypeValue());
        assertEquals("pvalue", r.getPrimaryAttributeValue());
        assertEquals((Integer) 1235, r.getPrimaryAttributeValueId());
        assertNotNull(r.getActivityDate());
        assertNull(r.getAttributes());
    }

    @Test
    public void testToString() throws Exception {
        String s = "LeadActivityRecord [id=1, leadId=2, activityDate=null, activityTypeId=3, activityTypeValue=value,"
                + " primaryAttributeValueId=1235, primaryAttributeValue=pvalue, attributes=null]";
        r.setActivityDate(null);
        assertEquals(s, r.toString());
    }

}
