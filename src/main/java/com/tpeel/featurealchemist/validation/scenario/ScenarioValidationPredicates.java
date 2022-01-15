package com.tpeel.featurealchemist.validation.scenario;

import com.tpeel.featurealchemist.models.mapping.Scenario;

import java.util.function.Predicate;

public class ScenarioValidationPredicates {
    public static final String SCENARIO_OUTLINE_TYPE = "scenario_outline";
    private static final String SCENARIO_TEMPLATE_TYPE = "scenario_template";
    private static final String SCENARIO_BACKGROUND_TYPE = "background";

    public static Predicate<Scenario> hasKeyword =
            scenario -> scenario.getKeyword() != null && !scenario.getKeyword().isEmpty();

    public static Predicate<Scenario> hasName =
            scenario -> scenario.getName() != null && !scenario.getName().isEmpty();

    public static Predicate<Scenario> hasSteps =
            scenario -> scenario.getSteps() != null && !scenario.getSteps().isEmpty();

    public static Predicate<Scenario> hasTags =
            scenario -> scenario.getTags() != null && !scenario.getTags().isEmpty();

    public static Predicate<Scenario> isScenarioOutline =
            scenario -> scenario.getType() != null &&
                    (scenario.getType().equalsIgnoreCase(SCENARIO_OUTLINE_TYPE) || scenario.getType().equalsIgnoreCase(SCENARIO_TEMPLATE_TYPE));

    public static Predicate<Scenario> hasExample =
            scenario -> scenario.getExamples() != null && !scenario.getExamples().isEmpty();

    public static Predicate<Scenario> isBackgroundScenario =
            scenario -> scenario.getType() != null && scenario.getType().equalsIgnoreCase(SCENARIO_BACKGROUND_TYPE);
}
