package com.tpeel.featurealchemist.models.validation;

public class ValidationErrorMessages {
    /* Feature-level validation */
    public static final String FEATURE_NO_KEYWORD_VALIDATION_MESSAGE = "Feature has no keyword";
    public static final String FEATURE_NO_NAME_VALIDATION_MESSAGE = "Feature has no name";
    public static final String FEATURE_NO_DESC_VALIDATION_MESSAGE = "Feature has no description";
    public static final String FEATURE_NO_SCENARIOS_VALIDATION_MESSAGE = "Feature has no scenarios";
    public static final String FEATURE_NO_TAGS_VALIDATION_MESSAGE = "Feature has no tags";

    /* Scenario-level validation */
    public static final String SCENARIO_NO_KEYWORD_VALIDATION_MESSAGE = "Scenario has no keyword";
    public static final String SCENARIO_OUTLINE_NO_EXAMPLES_VALIDATION_MESSAGE = "Scenario outline has no examples";
    public static final String SCENARIO_HAS_EXAMPLES_NO_OUTLINE_KEYWORD_VALIDATION_MESSAGE = "Scenario has example without the outline keyword";
    public static final String SCENARIO_NO_NAME_VALIDATION_MESSAGE = "Scenario has no name";
    public static final String SCENARIO_NO_STEPS_VALIDATION_MESSAGE = "Scenario has no steps";
    public static final String SCENARIO_NO_TAGS_VALIDATION_MESSAGE = "Scenario has no tags";
    public static final String BACKGROUND_SCENARIO_WITH_TAGS_VALIDATION_MESSAGE = "Background scenarios cant have tags";

    /* Step-level validation */
    public static final String STEP_NO_KEYWORD_VALIDATION_MESSAGE = "Step has no keyword";
    public static final String STEP_NO_NAME_VALIDATION_MESSAGE = "Step has no name";

    /* Example-level validation */
    public static final String EXAMPLE_NO_KEYWORD_VALIDATION_MESSAGE = "Example has no keyword";
    public static final String EXAMPLE_NO_ROWS_VALIDATION_MESSAGE = "Example has no rows";

    /* Row-level validation */
    public static final String ROW_NO_CELLS_VALIDATION_MESSAGE = "Row has no cells";
}
