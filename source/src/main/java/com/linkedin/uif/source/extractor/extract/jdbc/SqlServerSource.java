/* (c) 2014 LinkedIn Corp. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 */

package com.linkedin.uif.source.extractor.extract.jdbc;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.linkedin.uif.configuration.WorkUnitState;
import com.linkedin.uif.source.extractor.Extractor;
import com.linkedin.uif.source.extractor.exception.ExtractPrepareException;
import com.linkedin.uif.source.extractor.extract.QueryBasedSource;

/**
 * An implementation of sqlserver source to get work units
 * 
 * @author nveeramr
 */
public class SqlServerSource extends QueryBasedSource<JsonArray, JsonElement> {
	private static final Logger LOG = LoggerFactory.getLogger(SqlServerSource.class);

	public Extractor<JsonArray, JsonElement> getExtractor(WorkUnitState state) throws IOException {
		Extractor<JsonArray, JsonElement> extractor = null;
		try {
			extractor = new SqlServerExtractor(state).build();
		} catch (ExtractPrepareException e) {
			LOG.error("Failed to prepare extractor: error - " + e.getMessage());
			throw new IOException(e);
		}
		return extractor;
	}
}