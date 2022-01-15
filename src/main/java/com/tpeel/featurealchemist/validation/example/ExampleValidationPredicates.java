package com.tpeel.featurealchemist.validation.example;

import com.tpeel.featurealchemist.models.mapping.Example;

import java.util.function.Predicate;

public class ExampleValidationPredicates {

    public static Predicate<Example> hasKeyword =
            example -> example.getKeyword() != null && !example.getKeyword().isEmpty();

    public static Predicate<Example> hasRows =
            example -> example.getRows() != null && !example.getRows().isEmpty();
}
