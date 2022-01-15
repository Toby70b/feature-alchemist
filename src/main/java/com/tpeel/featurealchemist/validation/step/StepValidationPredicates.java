package com.tpeel.featurealchemist.validation.step;

import com.tpeel.featurealchemist.models.mapping.Step;

import java.util.function.Predicate;

public class StepValidationPredicates {

    public static Predicate<Step> hasKeyword =
            step -> step.getKeyword() != null && !step.getKeyword().isEmpty();

    public static Predicate<Step> hasName =
            step -> step.getName() != null && !step.getName().isEmpty();
}
