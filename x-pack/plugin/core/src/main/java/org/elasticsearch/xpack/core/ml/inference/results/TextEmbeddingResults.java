/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.core.ml.inference.results;

import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class TextEmbeddingResults implements InferenceResults {

    public static final String NAME = "text_embedding_result";

    private final String resultsField;
    private final double[] inference;

    public TextEmbeddingResults(String resultsField, double[] inference) {
        this.inference = inference;
        this.resultsField = resultsField;
    }

    public TextEmbeddingResults(StreamInput in) throws IOException {
        inference = in.readDoubleArray();
        resultsField = in.readString();
    }

    public String getResultsField() {
        return resultsField;
    }

    public double[] getInference() {
        return inference;
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        return builder.field(resultsField, inference);
    }

    @Override
    public String getWriteableName() {
        return NAME;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeDoubleArray(inference);
        out.writeString(resultsField);
    }

    @Override
    public Map<String, Object> asMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(resultsField, inference);
        return map;
    }

    @Override
    public Object predictedValue() {
        throw new UnsupportedOperationException("[" + NAME + "] does not support a single predicted value");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextEmbeddingResults that = (TextEmbeddingResults) o;
        return Arrays.equals(inference, that.inference) && Objects.equals(resultsField, that.resultsField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(inference), resultsField);
    }
}
