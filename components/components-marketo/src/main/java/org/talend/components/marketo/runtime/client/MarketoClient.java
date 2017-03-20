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
package org.talend.components.marketo.runtime.client;

public abstract class MarketoClient implements MarketoClientService {

    protected final String SCHEMA_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";

    protected final String DATETIME_PATTERN_FROM_PARAMS = "yyyy-MM-dd HH:mm:ss";

    protected String endpoint = null;

    protected String userId = null;

    protected String secretKey = null;

    protected Integer timeout;

    protected Integer retryCount;

    protected Integer retryInterval;

}
