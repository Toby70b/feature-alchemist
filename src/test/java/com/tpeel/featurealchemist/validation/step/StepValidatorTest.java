package com.tpeel.featurealchemist.validation.step;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tpeel.featurealchemist.models.mapping.Step;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationErrorMessages;
import com.tpeel.featurealchemist.models.validation.ValidationErrorSeverity;
import org.junit.jupiter.api.*;

class StepValidatorTest {
    private Step step;
    private StepValidator stepValidator;


    @BeforeEach
    public void setup() {
        step = new Step();
        stepValidator = new StepValidator(step);
    }

    @Nested
    @DisplayName("Step Keyword Tests")
    class StepKeywordTests {

        @Test
        @DisplayName("If the step keyword is null it should add an appropriate error to the list of validation errors")
        public void ifTheStepKeywordIsNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            step.setKeyword(null);
            StepValidator response = stepValidator.validateStepHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.STEP_NO_KEYWORD_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the step keyword is empty it should add an appropriate error to the list of validation errors")
        public void ifTheStepKeywordIsEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            step.setKeyword("");
            StepValidator response = stepValidator.validateStepHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.STEP_NO_KEYWORD_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the step keyword is not empty there should be no validation errors")
        public void ifTheStepKeywordIsNotEmptyThereShouldBeNoValidationErrors() {
            step.setKeyword("Step");
            StepValidator response = stepValidator.validateStepHasKeyword();
            assertEquals(0, response.getValidationErrors().size());
        }

        @Test
        @DisplayName("If the step keyword validation fails the validation error should include the step name")
        public void ifTheStepKeywordValidationFailsTheValidationErrorShouldIncludeTheScenarioName() {
            step.setKeyword(null);
            step.setName("Step Name");
            StepValidator response = stepValidator.validateStepHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.STEP_NO_KEYWORD_VALIDATION_MESSAGE,"Step Name"),
                    response.getValidationErrors().get(0));
        }
    }

    @Nested
    @DisplayName("Step Name Tests")
    class StepNameTests {

        @Test
        @DisplayName("If the step name is null it should add an appropriate error to the list of validation errors")
        public void ifTheStepNameIsNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            step.setName(null);
            StepValidator response = stepValidator.validateStepHasName();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.STEP_NO_NAME_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the step name is empty it should add an appropriate error to the list of validation errors")
        public void ifTheStepNameIsEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            step.setName("");
            StepValidator response = stepValidator.validateStepHasName();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.STEP_NO_NAME_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the step name is not empty there should be no validation errors")
        public void ifTheStepNameIsNotEmptyThereShouldBeNoValidationErrors() {
            step.setName("Step");
            StepValidator response = stepValidator.validateStepHasName();
            assertEquals(0, response.getValidationErrors().size());
        }
    }
}