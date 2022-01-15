package com.tpeel.featurealchemist;

import com.tpeel.featurealchemist.models.mapping.*;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationResponse;
import com.tpeel.featurealchemist.validation.example.ExampleValidator;
import com.tpeel.featurealchemist.validation.feature.FeatureValidator;
import com.tpeel.featurealchemist.validation.row.RowValidator;
import com.tpeel.featurealchemist.validation.scenario.ScenarioValidator;
import com.tpeel.featurealchemist.validation.step.StepValidator;

import java.util.ArrayList;
import java.util.List;

public class GherkinValidator {

    public ValidationResponse validate(Feature feature) {
        ValidationResponse response = new ValidationResponse();
        List<ValidationError> validationErrors = new ArrayList<>(validateFeature(feature));
        if (feature.getScenarios() != null) {
            feature.getScenarios().forEach(scenario -> {
                validationErrors.addAll(validateScenario(scenario));
                if (scenario.getSteps() != null) {
                    scenario.getSteps().stream().map(this::validateStep).forEach(validationErrors::addAll);
                }
                if (scenario.getExamples() != null) {
                    scenario.getExamples().forEach(example -> {
                        validationErrors.addAll(validateExample(example));
                        if (example.getRows() != null) {
                            example.getRows().stream().map(this::validateRow).forEach(validationErrors::addAll);
                        }
                    });
                }
            });
        }
        response.setValidationErrors(validationErrors);
        response.setValid(!response.containsValidationErrors());
        return response;
    }

    private List<ValidationError> validateFeature(Feature feature) {
        FeatureValidator featureValidator = new FeatureValidator(feature);
        return featureValidator
                .validateFeatureHasKeyword()
                .validateFeatureHasName()
                .validateFeatureHasDescription()
                .validateFeatureHasScenarios()
                .validateFeatureHasTags()
                .getValidationErrors();

    }

    private List<ValidationError> validateScenario(Scenario scenario) {
        ScenarioValidator scenarioValidator = new ScenarioValidator(scenario);
        return scenarioValidator
                .validateScenarioHasKeyword()
                .validateScenarioHasName()
                .validateScenarioHasTagsIfAppropriate()
                .validateScenarioHasExamplesIfAppropriate()
                .validateScenarioHasSteps()
                .getValidationErrors();
    }

    private List<ValidationError> validateStep(Step step) {
        StepValidator stepValidator = new StepValidator(step);
        return stepValidator
                .validateStepHasKeyword()
                .validateStepHasName()
                .getValidationErrors();
    }

    private List<ValidationError> validateExample(Example example) {
        ExampleValidator exampleValidator = new ExampleValidator(example);
        return exampleValidator
                .validateExampleHasKeyword()
                .validateExampleHasRows()
                .getValidationErrors();
    }

    private List<ValidationError> validateRow(Row row) {
        RowValidator rowValidator = new RowValidator(row);
        return rowValidator
                .validateRowHasCells()
                .getValidationErrors();
    }

}
