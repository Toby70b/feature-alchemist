package com.tpeel.featurealchemist.models.validation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ValidationError {
    private ValidationErrorSeverity severity;
    private String validationError;
    private String elementIdentifier;

    public ValidationError(ValidationErrorSeverity severity, String validationError, String elementIdentifier) {
        this.severity = severity;
        this.validationError = validationError;
        this.elementIdentifier = elementIdentifier;
    }

    public ValidationError(ValidationErrorSeverity severity, String validationError) {
        this.severity = severity;
        this.validationError = validationError;
    }

    @Override
    public String toString() {
        if (elementIdentifier != null && !elementIdentifier.isEmpty()) {
            return String.format("%s: %s - %s", severity, elementIdentifier, validationError);
        } else {
            return String.format("%s: %s", severity, validationError);
        }
    }
}
