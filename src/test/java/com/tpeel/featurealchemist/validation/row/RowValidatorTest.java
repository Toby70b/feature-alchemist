package com.tpeel.featurealchemist.validation.row;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tpeel.featurealchemist.models.mapping.Row;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationErrorMessages;
import com.tpeel.featurealchemist.models.validation.ValidationErrorSeverity;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

class RowValidatorTest {
    private Row row;
    private RowValidator rowValidator;


    @BeforeEach
    public void setup() {
        row = new Row();
        rowValidator = new RowValidator(row);
    }

    @Nested
    @DisplayName("Row Cell Tests")
    class RowCellTests {
        @Test
        @DisplayName("If the row cells are null it should add an appropriate error to the list of validation errors")
        public void ifTheRowCellsAreNullItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            row.setCells(null);
            RowValidator response = rowValidator.validateRowHasCells();
            assertEquals(1, response.getValidationErrors().size());
            Assertions.assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.ROW_NO_CELLS_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If the row cells are empty it should add an appropriate error to the list of validation errors")
        public void ifTheRowCellsAreEmptyItShouldAddAnAppropriateErrorToTheListOfValidationErrors() {
            row.setCells(new ArrayList<>());
            RowValidator response = rowValidator.validateRowHasCells();
            assertEquals(1, response.getValidationErrors().size());
            assertEquals(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.ROW_NO_CELLS_VALIDATION_MESSAGE),
                    response.getValidationErrors().get(0));
        }

        @Test
        @DisplayName("If row cells are not empty there should be no validation errors")
        public void ifTheScenarioKeywordIsNotEmptyThereShouldBeNoValidationErrors() {
            row.setCells(List.of(""));
            RowValidator response = rowValidator.validateRowHasCells();
            assertEquals(0, response.getValidationErrors().size());
        }
    }

}