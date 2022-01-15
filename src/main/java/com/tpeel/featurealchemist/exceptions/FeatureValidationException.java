package com.tpeel.featurealchemist.exceptions;

import com.tpeel.featurealchemist.models.validation.ValidationResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FeatureValidationException extends Throwable {
    ValidationResponse validationResponse;

    public FeatureValidationException(String message, ValidationResponse validationResponse) {
        super(message);
        this.validationResponse = validationResponse;
    }
}
