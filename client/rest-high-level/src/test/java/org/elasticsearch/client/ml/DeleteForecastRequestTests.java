/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */
package org.elasticsearch.client.ml;

import org.elasticsearch.client.ml.job.config.JobTests;
import org.elasticsearch.xcontent.XContentParser;
import org.elasticsearch.test.AbstractXContentTestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteForecastRequestTests extends AbstractXContentTestCase<DeleteForecastRequest> {

    @Override
    protected DeleteForecastRequest createTestInstance() {

        DeleteForecastRequest deleteForecastRequest = new DeleteForecastRequest(JobTests.randomValidJobId());
        if (randomBoolean()) {
            int length = randomInt(10);
            List<String> ids = new ArrayList<>(length);
            for(int i = 0; i < length; i++) {
                ids.add(randomAlphaOfLength(10));
            }
            deleteForecastRequest.setForecastIds(ids);
        }
        if (randomBoolean()) {
            deleteForecastRequest.setAllowNoForecasts(randomBoolean());
        }
        if (randomBoolean()) {
            deleteForecastRequest.timeout(randomTimeValue());
        }
        return deleteForecastRequest;
    }

    @Override
    protected DeleteForecastRequest doParseInstance(XContentParser parser) throws IOException {
        return DeleteForecastRequest.PARSER.apply(parser, null);
    }

    @Override
    protected boolean supportsUnknownFields() {
        return false;
    }

}
