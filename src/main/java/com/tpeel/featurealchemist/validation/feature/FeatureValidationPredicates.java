package com.tpeel.featurealchemist.validation.feature;

import com.tpeel.featurealchemist.models.mapping.Feature;

import java.util.function.Predicate;

public class FeatureValidationPredicates {

    public static Predicate<Feature> hasKeyword =
            feature -> feature.getKeyword() != null && !feature.getKeyword().isEmpty();

    public static Predicate<Feature> hasName =
            feature -> feature.getName() != null && !feature.getName().isEmpty();

    public static Predicate<Feature> hasDescription =
            feature -> feature.getDescription() != null && !feature.getDescription().isEmpty();

    public static Predicate<Feature> hasScenarios =
            feature -> feature.getScenarios() != null && !feature.getScenarios().isEmpty();

    public static Predicate<Feature> hasTags =
            feature -> feature.getTags() != null && !feature.getTags().isEmpty();
}
