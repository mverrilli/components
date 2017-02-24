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
package org.talend.components.file.runtime.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.avro.generic.IndexedRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.api.component.runtime.AbstractBoundedReader;
import org.talend.components.api.component.runtime.Result;
import org.talend.components.file.avro.DelimitedStringConverter;

/**
 * Simple implementation of a reader.
 */
public class FileInputReader extends AbstractBoundedReader<IndexedRecord> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileInputReader.class);

    private final String filePath;
    
    private boolean started = false;
    
    private boolean hasMore = false;

    private BufferedReader reader = null;

    private IndexedRecord current;
    
    private final DelimitedStringConverter converter;
    
    /**
     * Holds values for return properties
     */
    private Result result;

    public FileInputReader(FileInputSource source) {
        super(source);
        this.filePath = source.getFilePath();
        this.converter = new DelimitedStringConverter(source.getDesignSchema(), source.getDelimiter());
    }

    @Override
    public boolean start() throws IOException {
        reader = new BufferedReader(new FileReader(filePath));
        result = new Result();
        LOGGER.debug("open: " + filePath); //$NON-NLS-1$
        started = true;
        return advance();
    }
    
    @Override
    public boolean advance() throws IOException {
        if (!started) {
            throw new IllegalStateException("Reader wasn't started"); //$NON-NLS-1$
        }
        hasMore = reader.ready();
        if (hasMore) {
        	String line = reader.readLine();
        	current = converter.convertToAvro(line);
            result.totalCount++;
        }
        return hasMore;
    }

    @Override
    public IndexedRecord getCurrent() throws NoSuchElementException {
        if (!started) {
            throw new NoSuchElementException("Reader wasn't started"); //$NON-NLS-1$
        }
        if (!hasMore) {
        	throw new NoSuchElementException("Has no more elements"); //$NON-NLS-1$
        }
        return current;
    }

    @Override
    public void close() throws IOException {
        if (!started) {
            throw new IllegalStateException("Reader wasn't started"); //$NON-NLS-1$
        }
        reader.close();
        LOGGER.debug("close: " + filePath); //$NON-NLS-1$
        reader = null;
        started = false;
        hasMore = false;
    }

    /**
     * Returns values of Return properties. It is called after component finished his work (after {@link this#close()} method)
     */
    @Override
    public Map<String, Object> getReturnValues() {
        return result.toMap();
    }

}
