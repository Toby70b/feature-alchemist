package com.tpeel.featurealchemist.models.validation;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Data
public class ValidationResponse {
    boolean valid;
    List<ValidationError> validationErrors;


    public ValidationResponse() {
        this.validationErrors = new ArrayList<>();
    }

    public boolean containsValidationErrors() {
        Predicate<ValidationError> errorValidationResponse =
                validationError -> validationError.getSeverity().equals(ValidationErrorSeverity.ERROR);
        return validationErrors.stream().anyMatch(errorValidationResponse);
    }

    public boolean containsValidationWarnings() {
        Predicate<ValidationError> warningValidationResponse =
                validationError -> validationError.getSeverity().equals(ValidationErrorSeverity.WARNING);
        return validationErrors.stream().anyMatch(warningValidationResponse);
    }
}
