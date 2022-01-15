package com.tpeel.featurealchemist.validation.scenario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tpeel.featurealchemist.models.mapping.Example;
import com.tpeel.featurealchemist.models.mapping.Scenario;
import com.tpeel.featurealchemist.models.mapping.Step;
import com.tpeel.featurealchemist.models.mapping.Tag;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationErrorMessages;
import com.tpeel.featurealchemist.models.validation.ValidationErrorSeverity;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

class ScenarioValidatorTest {

    private Scenario scenario;
    private ScenarioValidator scenarioValidator;


    @BeforeEach
    public void setup() {
        scenario = new Scenario();
        scenarioValidator = new ScenarioValidator(scenario);
    }

    @Nested
    @DisplayName("Scenario Keyword Tests")
    class ScenarioKeywordTests {

        @Test
        @DisplayName("If the scenario keyword is null it should add an appropriate error to the list of validation errors")
        public void ifTheScenarioKeywordIsNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            scenario.setKeyword(null);
            ScenarioValidator response = scenarioValidator.validateScenarioHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_KEYWORD_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the scenario keyword is empty it should add an appropriate error to the list of validation errors")
        public void ifTheScenarioKeywordIsEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            scenario.setKeyword("");
            ScenarioValidator response = scenarioValidator.validateScenarioHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_KEYWORD_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the scenario keyword is not empty there should be no validation errors")
        public void ifTheScenarioKeywordIsNotEmptyThereShouldBeNoValidationErrors() {
            scenario.setKeyword("Keyword");
            ScenarioValidator response = scenarioValidator.validateScenarioHasKeyword();
            assertEquals(0, response.getValidationErrors().size());
        }

        @Test
        @DisplayName("If the scenario keyword validation fails the validation error should include the scenario name")
        public void ifTheScenarioKeywordValidationFailsTheValidationErrorShouldIncludeTheScenarioName() {
            scenario.setKeyword("");
            scenario.setName("Scenario Name");
            ScenarioValidator response = scenarioValidator.validateScenarioHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_KEYWORD_VALIDATION_MESSAGE, "Scenario Name"),
                    response.getValidationErrors().get(0));
        }
    }

    @Nested
    @DisplayName("Scenario Name Tests")
    class ScenarioNameTests {

        @Test
        @DisplayName("If the scenario name is null it should add an appropriate error to the list of validation errors")
        public void ifTheScenarioNameIsNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            scenario.setName(null);
            ScenarioValidator response = scenarioValidator.validateScenarioHasName();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.SCENARIO_NO_NAME_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the scenario name is empty it should add an appropriate error to the list of validation errors")
        public void ifTheScenarioNameIsEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            scenario.setName("");
            ScenarioValidator response = scenarioValidator.validateScenarioHasName();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.SCENARIO_NO_NAME_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the scenario name is not empty there should be no validation errors")
        public void ifTheScenarioNameIsNotEmptyThereShouldBeNoValidationErrors() {
            scenario.setName("Scenario Name");
            ScenarioValidator response = scenarioValidator.validateScenarioHasName();
            assertEquals(0, response.getValidationErrors().size());
        }
    }

    @Nested
    @DisplayName("Scenario Step Tests")
    class ScenarioStepTests {

        @Test
        @DisplayName("If the scenario steps are null it should add an appropriate error to the list of validation errors")
        public void ifTheScenarioStepsAreNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            scenario.setSteps(null);
            ScenarioValidator response = scenarioValidator.validateScenarioHasSteps();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_STEPS_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the scenario steps are empty it should add an appropriate error to the list of validation errors")
        public void ifTheScenarioStepsAreEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            scenario.setSteps(new ArrayList<>());
            ScenarioValidator response = scenarioValidator.validateScenarioHasSteps();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_STEPS_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the scenario steps are not empty there should be no validation errors")
        public void ifTheScenarioStepsAreEmptyThereShouldBeNoValidationErrors() {
            scenario.setSteps(List.of(new Step()));
            ScenarioValidator response = scenarioValidator.validateScenarioHasSteps();
            assertEquals(0, response.getValidationErrors().size());
        }

        @Test
        @DisplayName("If the scenario step validation fails the validation error should include the scenario name")
        public void ifTheScenarioStepValidationFailsTheValidationErrorShouldIncludeTheScenarioName() {
            scenario.setSteps(new ArrayList<>());
            scenario.setName("Scenario Name");
            ScenarioValidator response = scenarioValidator.validateScenarioHasSteps();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_STEPS_VALIDATION_MESSAGE, "Scenario Name"),
                    response.getValidationErrors().get(0));
        }
    }

    @Nested
    @DisplayName("Scenario Tag Tests")
    class ScenarioTagTests {

        @Nested
        @DisplayName("Normal Scenario Tag Tests")
        class NormalScenarioTagTests {

            @Test
            @DisplayName("If the scenario tags are null it should add an appropriate error to the list of validation errors")
            public void ifTheScenarioTagsAreNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
                scenario.setTags(null);
                ScenarioValidator response = scenarioValidator.validateScenarioHasTagsIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_TAGS_VALIDATION_MESSAGE),
                        response.getValidationErrors().get(0));
            }

            @Test
            @DisplayName("If the scenario tags are empty it should add an appropriate error to the list of validation errors")
            public void ifTheScenarioTagsAreEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
                scenario.setTags(new ArrayList<>());
                ScenarioValidator response = scenarioValidator.validateScenarioHasTagsIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_TAGS_VALIDATION_MESSAGE),
                        response.getValidationErrors().get(0));
            }

            @Test
            @DisplayName("If the scenario tags are not empty there should be no validation errors")
            public void ifTheScenarioTagsAreNotEmptyThereShouldBeNoValidationErrors() {
                scenario.setTags(List.of(new com.tpeel.featurealchemist.models.mapping.Tag()));
                ScenarioValidator response = scenarioValidator.validateScenarioHasTagsIfAppropriate();
                assertEquals(0, response.getValidationErrors().size());
            }

            @Test
            @DisplayName("If the scenario tags validation fails the validation error should include the scenario name")
            public void ifTheScenarioTagsValidationFailsTheValidationErrorShouldIncludeTheScenarioName() {
                scenario.setTags(new ArrayList<>());
                scenario.setName("Scenario Name");
                ScenarioValidator response = scenarioValidator.validateScenarioHasTagsIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_NO_TAGS_VALIDATION_MESSAGE, "Scenario Name"),
                        response.getValidationErrors().get(0));
            }

        }

        @Nested
        @DisplayName("Background Scenario Tag Tests")
        class BackgroundScenarioTagTests {

            @Test
            @DisplayName("If the background scenario tags are not empty it should add an appropriate error to the list of validation errors")
            public void ifTheBackgroundScenarioTagsAreNotEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
                scenario.setTags(List.of(new com.tpeel.featurealchemist.models.mapping.Tag()));
                scenario.setType("background");
                ScenarioValidator response = scenarioValidator.validateScenarioHasTagsIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.BACKGROUND_SCENARIO_WITH_TAGS_VALIDATION_MESSAGE),
                        response.getValidationErrors().get(0));
            }

            @Test
            @DisplayName("If the background scenario tags are null there should be no validation errors")
            public void ifTheBackgroundScenarioTagsAreNullThereShouldBeNoValidationErrors() {
                scenario.setTags(null);
                scenario.setType("background");
                ScenarioValidator response = scenarioValidator.validateScenarioHasTagsIfAppropriate();
                assertEquals(0, response.getValidationErrors().size());
            }

            @Test
            @DisplayName("If the background scenario tags are empty there should be no validation errors")
            public void ifTheBackgroundScenarioTagsAreEmptyThereShouldBeNoValidationErrors() {
                scenario.setTags(new ArrayList<>());
                scenario.setType("background");
                ScenarioValidator response = scenarioValidator.validateScenarioHasTagsIfAppropriate();
                assertEquals(0, response.getValidationErrors().size());
            }

            @Test
            @DisplayName("If the background scenario tags validation fails the validation error should include the scenario name")
            public void ifTheBackgroundScenarioTagsValidationFailsTheValidationErrorShouldIncludeTheScenarioName() {
                scenario.setTags(List.of(new Tag()));
                scenario.setType("background");
                scenario.setName("Scenario Name");
                ScenarioValidator response = scenarioValidator.validateScenarioHasTagsIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(
                        new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.BACKGROUND_SCENARIO_WITH_TAGS_VALIDATION_MESSAGE, "Scenario Name"),
                        response.getValidationErrors().get(0));
            }

        }
    }

    @Nested
    @DisplayName("Scenario Example Table Tests")
    class ScenarioExampleTableTests {

        @Nested
        @DisplayName("Normal Scenario Example Table Tests")
        class NormalScenarioExampleTableTests {

            @Test
            @DisplayName("If the scenario example table is not empty it should add an appropriate error to the list of validation errors")
            public void ifTheScenarioExampleTableIsNotEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
                scenario.setExamples(List.of(new Example()));
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_HAS_EXAMPLES_NO_OUTLINE_KEYWORD_VALIDATION_MESSAGE),
                        response.getValidationErrors().get(0));
            }

            @Test
            @DisplayName("If the scenario example table is null there should be no validation errors")
            public void ifTheScenarioExampleTableIsNullThereShouldBeNoValidationErrors() {
                scenario.setExamples(null);
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(0, response.getValidationErrors().size());
            }

            @Test
            @DisplayName("If scenario example table is empty there should be no validation errors")
            public void ifTheScenarioExampleTableIsEmptyThereShouldBeNoValidationErrors() {
                scenario.setExamples(new ArrayList<>());
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(0, response.getValidationErrors().size());
            }

            @Test
            @DisplayName("If the scenario example table validation fails the validation error should include the scenario name")
            public void ifTheBackgroundScenarioTagsValidationFailsTheValidationErrorShouldIncludeTheScenarioName() {
                scenario.setExamples(List.of(new Example()));
                scenario.setName("Scenario Name");
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(
                        new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_HAS_EXAMPLES_NO_OUTLINE_KEYWORD_VALIDATION_MESSAGE, "Scenario Name"),
                        response.getValidationErrors().get(0));
            }
        }

        @Nested
        @DisplayName("Scenario Outline Example Table Tests")
        class ScenarioOutlineExampleTableTests {

            @Test
            @DisplayName("If the scenario outline example table is empty it should add an appropriate error to the list of validation errors")
            public void ifTheScenarioExampleTableIsEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
                scenario.setExamples(new ArrayList<>());
                scenario.setType("scenario_outline");
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_OUTLINE_NO_EXAMPLES_VALIDATION_MESSAGE),
                        response.getValidationErrors().get(0));
            }

            @Test
            @DisplayName("If the scenario outline example table is null it should add an appropriate error to the list of validation errors")
            public void ifTheScenarioExampleTableIsNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
                scenario.setExamples(null);
                scenario.setType("scenario_outline");
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_OUTLINE_NO_EXAMPLES_VALIDATION_MESSAGE),
                        response.getValidationErrors().get(0));
            }

            @Test
            @DisplayName("If scenario example table is not empty there should be no validation errors")
            public void ifTheScenarioExampleTableIsNotEmptyThereShouldBeNoValidationErrors() {
                scenario.setExamples(List.of(new Example()));
                scenario.setType("scenario_outline");
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(0, response.getValidationErrors().size());
            }

            @Test
            @DisplayName("If the scenario example table validation fails the validation error should include the scenario name")
            public void ifTheBackgroundScenarioTagsValidationFailsTheValidationErrorShouldIncludeTheScenarioName() {
                scenario.setExamples(null);
                scenario.setType("scenario_outline");
                scenario.setName("Scenario Name");
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(
                        new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_OUTLINE_NO_EXAMPLES_VALIDATION_MESSAGE, "Scenario Name"),
                        response.getValidationErrors().get(0));
            }

        }

        @Nested
        @DisplayName("Scenario Template Example Table Tests")
        class ScenarioTemplateExampleTableTests {

            @Test
            @DisplayName("If the scenario template example table is empty it should add an appropriate error to the list of validation errors")
            public void ifTheScenarioExampleTableIsEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
                scenario.setExamples(new ArrayList<>());
                scenario.setType("scenario_template");
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_OUTLINE_NO_EXAMPLES_VALIDATION_MESSAGE),
                        response.getValidationErrors().get(0));
            }

            @Test
            @DisplayName("If the scenario template example table is null it should add an appropriate error to the list of validation errors")
            public void ifTheScenarioExampleTableIsNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
                scenario.setExamples(null);
                scenario.setType("scenario_template");
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_OUTLINE_NO_EXAMPLES_VALIDATION_MESSAGE),
                        response.getValidationErrors().get(0));
            }

            @Test
            @DisplayName("If scenario example table is not empty there should be no validation errors")
            public void ifTheScenarioExampleTableIsNotEmptyThereShouldBeNoValidationErrors() {
                scenario.setExamples(List.of(new Example()));
                scenario.setType("scenario_template");
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(0, response.getValidationErrors().size());
            }

            @Test
            @DisplayName("If the scenario example table validation fails the validation error should include the scenario name")
            public void ifTheBackgroundScenarioTagsValidationFailsTheValidationErrorShouldIncludeTheScenarioName() {
                scenario.setExamples(null);
                scenario.setType("scenario_template");
                scenario.setName("Scenario Name");
                ScenarioValidator response = scenarioValidator.validateScenarioHasExamplesIfAppropriate();
                assertEquals(1, response.getValidationErrors().size());
                Assertions.assertEquals(
                        new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.SCENARIO_OUTLINE_NO_EXAMPLES_VALIDATION_MESSAGE, "Scenario Name"),
                        response.getValidationErrors().get(0));
            }

        }
    }

}