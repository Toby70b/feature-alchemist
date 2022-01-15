package com.tpeel.featurealchemist.validation.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tpeel.featurealchemist.models.mapping.Feature;
import com.tpeel.featurealchemist.models.mapping.Scenario;
import com.tpeel.featurealchemist.models.mapping.Tag;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationErrorMessages;
import com.tpeel.featurealchemist.models.validation.ValidationErrorSeverity;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

class FeatureValidatorTest {
    private Feature feature;
    private FeatureValidator featureValidator;
    
    @BeforeEach
    public void setup() {
        feature = new Feature();
        featureValidator = new FeatureValidator(feature);
    }

    @Nested
    @DisplayName("Feature Keyword Tests")
    class FeatureKeywordTests {

        @Test
        @DisplayName("If the feature keyword is null it should add an appropriate error to the list of validation errors")
        public void ifTheFeatureKeywordIsNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            feature.setKeyword(null);
            FeatureValidator response = featureValidator.validateFeatureHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.FEATURE_NO_KEYWORD_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the feature keyword is empty it should add an appropriate error to the list of validation errors")
        public void ifTheFeatureKeywordIsEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            feature.setKeyword("");
            FeatureValidator response = featureValidator.validateFeatureHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.FEATURE_NO_KEYWORD_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the feature keyword is not empty there should be no validation errors")
        public void ifTheFeatureKeywordIsNotEmptyThereShouldBeNoValidationErrors() {
            feature.setKeyword("");
            FeatureValidator response = featureValidator.validateFeatureHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.FEATURE_NO_KEYWORD_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the feature keyword validation fails the validation error should include the feature name")
        public void ifTheFeatureKeywordValidationFailsTheValidationErrorShouldIncludeTheFeatureName() {
            feature.setKeyword("");
            feature.setName("Feature Name");
            FeatureValidator response = featureValidator.validateFeatureHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.FEATURE_NO_KEYWORD_VALIDATION_MESSAGE, "Feature Name"),
                    response.getValidationErrors().get(0));
        }
    }

    @Nested
    @DisplayName("Feature Name Tests")
    class FeatureNameTests {

        @Test
        @DisplayName("If the feature name is null it should add an appropriate error to the list of validation errors")
        public void ifTheFeatureNameIsNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            feature.setName(null);
            FeatureValidator response = featureValidator.validateFeatureHasName();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_NAME_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the feature name is empty it should add an appropriate error to the list of validation errors")
        public void ifTheFeatureNameIsEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            feature.setName("");
            FeatureValidator response = featureValidator.validateFeatureHasName();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_NAME_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the feature name is not empty there should be no validation errors")
        public void ifTheFeatureNameIsNotEmptyThereShouldBeNoValidationErrors() {
            feature.setName("Feature Name");
            FeatureValidator response = featureValidator.validateFeatureHasName();
            assertEquals(0, response.getValidationErrors().size());
        }
    }

    @Nested
    @DisplayName("Feature Description Tests")
    class FeatureDescriptionTests {

        @Test
        @DisplayName("If the feature description is null it should add an appropriate error to the list of validation errors")
        public void ifTheFeatureDescriptionIsNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            feature.setDescription(null);
            FeatureValidator response = featureValidator.validateFeatureHasDescription();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_DESC_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the feature description is empty it should add an appropriate error to the list of validation errors")
        public void ifTheFeatureDescriptionIsEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            feature.setDescription("");
            FeatureValidator response = featureValidator.validateFeatureHasDescription();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_DESC_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the feature description is not empty there should be no validation errors")
        public void ifTheFeatureDescriptionIsNotEmptyThereShouldBeNoValidationErrors() {
            feature.setDescription("Feature Description");
            FeatureValidator response = featureValidator.validateFeatureHasDescription();
            assertEquals(0, response.getValidationErrors().size());
        }

        @Test
        @DisplayName("If the feature description validation fails the validation error should include the feature name")
        public void ifTheFeatureDescriptionValidationFailsTheValidationErrorShouldIncludeTheFeatureName() {
            feature.setDescription("");
            feature.setName("Feature Name");
            FeatureValidator response = featureValidator.validateFeatureHasDescription();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_DESC_VALIDATION_MESSAGE, "Feature Name"),
                    response.getValidationErrors().get(0));
        }
    }

    @Nested
    @DisplayName("Feature Scenario Tests")
    class FeatureScenarioTests {

        @Test
        @DisplayName("If the feature scenarios are null it should add an appropriate error to the list of validation errors")
        public void ifTheFeatureScenariosAreNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            feature.setScenarios(null);
            FeatureValidator response = featureValidator.validateFeatureHasScenarios();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_SCENARIOS_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the feature scenarios are empty it should add an appropriate error to the list of validation errors")
        public void ifTheFeatureScenariosAreEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            feature.setScenarios(new ArrayList<>());
            FeatureValidator response = featureValidator.validateFeatureHasScenarios();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_SCENARIOS_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the feature scenarios are not empty there should be no validation errors")
        public void ifTheFeatureScenariosAreEmptyThereShouldBeNoValidationErrors() {
            feature.setScenarios(List.of(new Scenario()));
            FeatureValidator response = featureValidator.validateFeatureHasScenarios();
            assertEquals(0, response.getValidationErrors().size());
        }

        @Test
        @DisplayName("If the feature scenario validation fails the validation error should include the feature name")
        public void ifTheFeatureScenarioValidationFailsTheValidationErrorShouldIncludeTheFeatureName() {
            feature.setScenarios(new ArrayList<>());
            feature.setName("Feature Name");
            FeatureValidator response = featureValidator.validateFeatureHasScenarios();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_SCENARIOS_VALIDATION_MESSAGE, "Feature Name"),
                    response.getValidationErrors().get(0));
        }
    }

    @Nested
    @DisplayName("Feature Tag Tests")
    class FeatureTagTests {

        @Test
        @DisplayName("If the feature tags are null it should add an appropriate error to the list of validation errors")
        public void ifTheFeatureTagsAreNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            feature.setTags(null);
            FeatureValidator response = featureValidator.validateFeatureHasTags();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_TAGS_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the feature tags are empty it should add an appropriate error to the list of validation errors")
        public void ifTheFeatureTagsAreEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            feature.setTags(new ArrayList<>());
            FeatureValidator response = featureValidator.validateFeatureHasTags();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_TAGS_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the feature tags are not empty there should be no validation errors")
        public void ifTheFeatureTagsAreNotEmptyThereShouldBeNoValidationErrors() {
            feature.setTags(List.of(new Tag()));
            FeatureValidator response = featureValidator.validateFeatureHasTags();
            assertEquals(0, response.getValidationErrors().size());
        }

        @Test
        @DisplayName("If the feature tags validation fails the validation error should include the scenario name")
        public void ifTheFeatureTagsValidationFailsTheValidationErrorShouldIncludeTheFeatureName() {
            feature.setTags(new ArrayList<>());
            feature.setName("Feature Name");
            FeatureValidator response = featureValidator.validateFeatureHasTags();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_TAGS_VALIDATION_MESSAGE, "Feature Name"),
                    response.getValidationErrors().get(0));
        }

    }
    
    
}