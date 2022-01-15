package com.tpeel.featurealchemist.validation.row;

import com.tpeel.featurealchemist.models.mapping.Row;

import java.util.function.Predicate;

public class RowValidationPredicates {

    public static Predicate<Row> hasCells =
            row -> row.getCells() != null && !row.getCells().isEmpty();
}
