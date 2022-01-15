package com.tpeel.featurealchemist.validation.example;

import com.tpeel.featurealchemist.models.mapping.Example;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationErrorMessages;
import com.tpeel.featurealchemist.models.validation.ValidationErrorSeverity;
import com.tpeel.featurealchemist.models.validation.Validator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.tpeel.featurealchemist.validation.example.ExampleValidationPredicates.hasKeyword;
import static com.tpeel.featurealchemist.validation.example.ExampleValidationPredicates.hasRows;

@Data
public class ExampleValidator {
    private Validator<Example> exampleValidator = new Validator<>();
    private List<ValidationError> validationErrors = new ArrayList<>();
    private Example example;

    public ExampleValidator(Example example) {
        this.example = example;
    }

    public ExampleValidator validateExampleHasKeyword() {
        if (!exampleValidator.validate(hasKeyword, example)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.EXAMPLE_NO_KEYWORD_VALIDATION_MESSAGE));
        }
        return this;
    }

    public ExampleValidator validateExampleHasRows() {
        if (!exampleValidator.validate(hasRows, example)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.EXAMPLE_NO_ROWS_VALIDATION_MESSAGE));
        }
        return this;
    }
}
