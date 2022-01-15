package com.tpeel.featurealchemist.validation.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tpeel.featurealchemist.models.mapping.Example;
import com.tpeel.featurealchemist.models.mapping.Row;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationErrorMessages;
import com.tpeel.featurealchemist.models.validation.ValidationErrorSeverity;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

class ExampleValidatorTest {
    private Example example;
    private ExampleValidator exampleValidator;


    @BeforeEach
    public void setup() {
        example = new Example();
        exampleValidator = new ExampleValidator(example);
    }

    @Nested
    @DisplayName("Example Keyword Tests")
    class ExampleKeywordTests {

        @Test
        @DisplayName("If the example keyword is null it should add an appropriate error to the list of validation errors")
        public void ifTheExampleKeywordIsNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            example.setKeyword(null);
            ExampleValidator response = exampleValidator.validateExampleHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.EXAMPLE_NO_KEYWORD_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the example keyword is empty it should add an appropriate error to the list of validation errors")
        public void ifTheExampleKeywordIsEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            example.setKeyword("");
            ExampleValidator response = exampleValidator.validateExampleHasKeyword();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.EXAMPLE_NO_KEYWORD_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the example keyword is not empty there should be no validation errors")
        public void ifTheExampleKeywordIsNotEmptyThereShouldBeNoValidationErrors() {
            example.setKeyword("Example");
            ExampleValidator response = exampleValidator.validateExampleHasKeyword();
            assertEquals(0, response.getValidationErrors().size());
        }
    }

    @Nested
    @DisplayName("Example Row Tests")
    class ExampleRowTests {

        @Test
        @DisplayName("If the example row is null it should add an appropriate error to the list of validation errors")
        public void ifTheExampleRowIsNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            example.setRows(null);
            ExampleValidator response = exampleValidator.validateExampleHasRows();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.EXAMPLE_NO_ROWS_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the example row is empty it should add an appropriate error to the list of validation errors")
        public void ifTheExampleRowIsEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            example.setRows(new ArrayList<>());
            ExampleValidator response = exampleValidator.validateExampleHasRows();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.EXAMPLE_NO_ROWS_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the example row is not empty there should be no validation errors")
        public void ifTheExampleRowIsNotEmptyThereShouldBeNoValidationErrors() {
            example.setRows(List.of(new Row()));
            ExampleValidator response = exampleValidator.validateExampleHasRows();
            assertEquals(0, response.getValidationErrors().size());
        }
    }

}