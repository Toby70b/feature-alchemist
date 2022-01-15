package com.tpeel.featurealchemist.validation.scenario;

import com.tpeel.featurealchemist.models.mapping.Scenario;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationErrorMessages;
import com.tpeel.featurealchemist.models.validation.ValidationErrorSeverity;
import com.tpeel.featurealchemist.models.validation.Validator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScenarioValidator {
    Validator<Scenario> scenarioValidator = new Validator<>();
    List<ValidationError> validationErrors = new ArrayList<>();
    private Scenario scenario;

    public ScenarioValidator(Scenario scenario) {
        this.scenario = scenario;
    }

    public ScenarioValidator validateScenarioHasKeyword() {
        if (!scenarioValidator.validate(ScenarioValidationPredicates.hasKeyword, scenario)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_KEYWORD_VALIDATION_MESSAGE, scenario.getName()));
        }
        return this;
    }

    public ScenarioValidator validateScenarioHasName() {
        if (!scenarioValidator.validate(ScenarioValidationPredicates.hasName, scenario)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.SCENARIO_NO_NAME_VALIDATION_MESSAGE));
        }
        return this;
    }

    public ScenarioValidator validateScenarioHasSteps() {
        if (!scenarioValidator.validate(ScenarioValidationPredicates.hasSteps, scenario)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_STEPS_VALIDATION_MESSAGE, scenario.getName()));
        }
        return this;
    }

    public ScenarioValidator validateScenarioHasTagsIfAppropriate() {
        if (scenarioValidator.validate(ScenarioValidationPredicates.isBackgroundScenario.and(ScenarioValidationPredicates.hasTags), scenario)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.BACKGROUND_SCENARIO_WITH_TAGS_VALIDATION_MESSAGE, scenario.getName()));
        }
        if (scenarioValidator.validate(ScenarioValidationPredicates.isBackgroundScenario.negate().and(ScenarioValidationPredicates.hasTags.negate()), scenario)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_TAGS_VALIDATION_MESSAGE, scenario.getName()));
        }
        return this;
    }

    public ScenarioValidator validateScenarioHasExamplesIfAppropriate() {
        if (scenarioValidator.validate(ScenarioValidationPredicates.isScenarioOutline.and(ScenarioValidationPredicates.hasExample.negate()), scenario)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_OUTLINE_NO_EXAMPLES_VALIDATION_MESSAGE, scenario.getName()));
        }
        if (scenarioValidator.validate(ScenarioValidationPredicates.hasExample.and(ScenarioValidationPredicates.isScenarioOutline.negate()), scenario)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_HAS_EXAMPLES_NO_OUTLINE_KEYWORD_VALIDATION_MESSAGE, scenario.getName()));
        }
        return this;
    }
}
