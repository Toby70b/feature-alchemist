package com.tpeel.featurealchemist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationErrorMessages;
import com.tpeel.featurealchemist.models.validation.ValidationErrorSeverity;
import com.tpeel.featurealchemist.models.validation.ValidationResponse;
import com.tpeel.featurealchemist.models.mapping.Example;
import com.tpeel.featurealchemist.models.mapping.Feature;
import com.tpeel.featurealchemist.models.mapping.Row;
import com.tpeel.featurealchemist.models.mapping.Scenario;
import com.tpeel.featurealchemist.models.mapping.Step;
import com.tpeel.featurealchemist.models.mapping.Tag;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GherkinValidatorTest {

    private GherkinValidator gherkinValidator;

    @BeforeEach
    public void setup() {
        gherkinValidator = new GherkinValidator();
    }

    @Test
    @DisplayName("If a feature contains validation errors it should return an invalid response with the various validation errors included")
    public void IfAnInvalidFeatureIsProvidedItShouldReturnAnInvalidResponseWithTheVariousValidationErrorsIncluded() {
        Feature feature = createValidFeature();
        feature.setKeyword(null);
        ValidationResponse validationResponse = gherkinValidator.validate(feature);

        assertFalse(validationResponse.isValid());
        assertFalse(validationResponse.containsValidationWarnings());
        assertTrue(validationResponse.containsValidationErrors());
        assertEquals(1, validationResponse.getValidationErrors().size());
        Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.FEATURE_NO_KEYWORD_VALIDATION_MESSAGE, "Feature Name"),
                validationResponse.getValidationErrors().get(0));

    }

    @Test
    @DisplayName("If a feature contains validation warnings it should return an valid response with the various validation errors included")
    public void IfAFeatureContainsValidationWarningsItShouldReturnAValidResponseWithTheVariousValidationErrorsIncluded() {
        Feature feature = createValidFeature();
        feature.setDescription(null);
        ValidationResponse validationResponse = gherkinValidator.validate(feature);

        assertTrue(validationResponse.isValid());
        assertTrue(validationResponse.containsValidationWarnings());
        assertFalse(validationResponse.containsValidationErrors());
        assertEquals(1, validationResponse.getValidationErrors().size());
        assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_DESC_VALIDATION_MESSAGE, "Feature Name"),
                validationResponse.getValidationErrors().get(0));
    }

    @Test
    @DisplayName("If no validation errors are encountered it should return a valid validation response")
    public void ifNoValidationErrorsAreEncounteredItShouldReturnAnEmptyList() {
        Feature feature = createValidFeature();
        ValidationResponse validationResponse = gherkinValidator.validate(feature);
        assertTrue(validationResponse.isValid());
        assertFalse(validationResponse.containsValidationWarnings());
        assertFalse(validationResponse.containsValidationErrors());
        assertEquals(0, validationResponse.getValidationErrors().size());
    }

    @Test
    @DisplayName("If a feature contains an invalid scenario it should return an invalid response with the various validation errors included")
    public void IfAFeatureContainsAnInvalidScenarioItShouldReturnAnInvalidResponseWithTheVariousValidationErrorsIncluded() {
        Feature feature = createValidFeature();
        feature.getScenarios().get(0).setKeyword(null);
        ValidationResponse validationResponse = gherkinValidator.validate(feature);

        assertFalse(validationResponse.isValid());
        assertFalse(validationResponse.containsValidationWarnings());
        assertTrue(validationResponse.containsValidationErrors());
        assertEquals(1, validationResponse.getValidationErrors().size());
        assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_KEYWORD_VALIDATION_MESSAGE, "Scenario Name"),
                validationResponse.getValidationErrors().get(0));
    }

    @Test
    @DisplayName("If a feature contains an invalid step it should return an invalid response with the various validation errors included")
    public void IfAFeatureContainsAnInvalidStepItShouldReturnAnInvalidResponseWithTheVariousValidationErrorsIncluded() {
        Feature feature = createValidFeature();
        feature.getScenarios().get(0).getSteps().get(0).setKeyword(null);
        ValidationResponse validationResponse = gherkinValidator.validate(feature);

        assertFalse(validationResponse.isValid());
        assertFalse(validationResponse.containsValidationWarnings());
        assertTrue(validationResponse.containsValidationErrors());
        assertEquals(1, validationResponse.getValidationErrors().size());
        assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.STEP_NO_KEYWORD_VALIDATION_MESSAGE, "Step Name"),
                validationResponse.getValidationErrors().get(0));
    }

    @Test
    @DisplayName("If a feature contains an invalid example it should return an invalid response with the various validation errors included")
    public void IfAFeatureContainsAnInvalidExampleItShouldReturnAnInvalidResponseWithTheVariousValidationErrorsIncluded() {
        Feature feature = createValidFeature();
        feature.getScenarios().get(0).getExamples().get(0).setKeyword(null);
        ValidationResponse validationResponse = gherkinValidator.validate(feature);

        assertFalse(validationResponse.isValid());
        assertFalse(validationResponse.containsValidationWarnings());
        assertTrue(validationResponse.containsValidationErrors());
        assertEquals(1, validationResponse.getValidationErrors().size());
        assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.EXAMPLE_NO_KEYWORD_VALIDATION_MESSAGE),
                validationResponse.getValidationErrors().get(0));
    }

    @Test
    @DisplayName("If a feature contains an invalid row it should return an invalid response with the various validation errors included")
    public void IfAFeatureContainsAnInvalidRowItShouldReturnAnInvalidResponseWithTheVariousValidationErrorsIncluded() {
        Feature feature = createValidFeature();
        feature.getScenarios().get(0).getExamples().get(0).getRows().get(0).setCells(null);
        ValidationResponse validationResponse = gherkinValidator.validate(feature);

        assertFalse(validationResponse.isValid());
        assertFalse(validationResponse.containsValidationWarnings());
        assertTrue(validationResponse.containsValidationErrors());
        assertEquals(1, validationResponse.getValidationErrors().size());
        assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.ROW_NO_CELLS_VALIDATION_MESSAGE),
                validationResponse.getValidationErrors().get(0));
    }


    /**
     * Create a valid feature object for testing purposes. It is deemed "valid" in that it triggers no errors or
     * warnings from the various gherkin element validators
     *
     * @return a valid feature object
     */
    private Feature createValidFeature() {
        Feature feature = new Feature();
        feature.setKeyword("Feature");
        feature.setName("Feature Name");
        feature.setDescription("Feature Description");
        feature.setScenarios(List.of(createValidScenario()));
        feature.setTags(List.of(new Tag()));
        return feature;
    }

    /**
     * Create a valid scenario object for testing purposes. It is deemed "valid" in that it triggers no errors or
     * warnings from the various gherkin element validators
     *
     * @return a valid scenario object
     */
    private Scenario createValidScenario() {
        Scenario scenario = new Scenario();
        scenario.setKeyword("Scenario");
        scenario.setType("scenario_outline");
        scenario.setName("Scenario Name");
        scenario.setDescription("Scenario Description");
        scenario.setSteps(List.of(createValidStep()));
        scenario.setTags(List.of(new Tag()));
        scenario.setExamples(createValidExampleTable());
        return scenario;
    }

    /**
     * Create a valid example object for testing purposes. It is deemed "valid" in that it triggers no errors or
     * warnings from the various gherkin element validators
     *
     * @return a valid example object
     */
    private List<Example> createValidExampleTable() {
        Example example = new Example();
        example.setKeyword("When");
        example.setRows(createValidRow());
        return List.of(example);
    }

    /**
     * Create a valid Row object for testing purposes. It is deemed "valid" in that it triggers no errors or
     * warnings from the various gherkin element validators
     *
     * @return a valid example object
     */
    private List<Row> createValidRow() {
        Row row = new Row();
        row.setCells(List.of(""));
        return List.of(row);
    }

    /**
     * Create a valid step object for testing purposes. It is deemed "valid" in that it triggers no errors or warnings
     * from the various gherkin element validators
     *
     * @return a valid step object
     */
    private Step createValidStep() {
        Step step = new Step();
        step.setKeyword("Step");
        step.setName("Step Name");
        return step;
    }


}