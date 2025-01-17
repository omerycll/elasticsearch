/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */
package org.elasticsearch.client.ml.dataframe.evaluation.regression;

import org.elasticsearch.client.ml.dataframe.evaluation.MlEvaluationNamedXContentProvider;
import org.elasticsearch.xcontent.NamedXContentRegistry;
import org.elasticsearch.xcontent.XContentParser;
import org.elasticsearch.test.AbstractXContentTestCase;

import java.io.IOException;

public class MeanSquaredLogarithmicErrorMetricResultTests extends AbstractXContentTestCase<MeanSquaredLogarithmicErrorMetric.Result> {

    public static MeanSquaredLogarithmicErrorMetric.Result randomResult() {
        return new MeanSquaredLogarithmicErrorMetric.Result(randomDouble());
    }

    @Override
    protected MeanSquaredLogarithmicErrorMetric.Result createTestInstance() {
        return randomResult();
    }

    @Override
    protected MeanSquaredLogarithmicErrorMetric.Result doParseInstance(XContentParser parser) throws IOException {
        return MeanSquaredLogarithmicErrorMetric.Result.fromXContent(parser);
    }

    @Override
    protected boolean supportsUnknownFields() {
        return true;
    }

    @Override
    protected NamedXContentRegistry xContentRegistry() {
        return new NamedXContentRegistry(new MlEvaluationNamedXContentProvider().getNamedXContentParsers());
    }
}
