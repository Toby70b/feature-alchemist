package com.tpeel.featurealchemist.validation.row;

import com.tpeel.featurealchemist.models.mapping.Row;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationErrorMessages;
import com.tpeel.featurealchemist.models.validation.ValidationErrorSeverity;
import com.tpeel.featurealchemist.models.validation.Validator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.tpeel.featurealchemist.validation.row.RowValidationPredicates.hasCells;

@Data
public class RowValidator {
    Validator<Row> rowValidator = new Validator<>();
    List<ValidationError> validationErrors = new ArrayList<>();
    private Row row;

    public RowValidator(Row row) {
        this.row = row;
    }

    public RowValidator validateRowHasCells() {
        if (!rowValidator.validate(hasCells, row)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.ROW_NO_CELLS_VALIDATION_MESSAGE));
        }
        return this;
    }
}
