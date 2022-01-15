package com.tpeel.featurealchemist.validation.step;

import com.tpeel.featurealchemist.models.mapping.Step;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationErrorMessages;
import com.tpeel.featurealchemist.models.validation.ValidationErrorSeverity;
import com.tpeel.featurealchemist.models.validation.Validator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.tpeel.featurealchemist.validation.step.StepValidationPredicates.hasKeyword;
import static com.tpeel.featurealchemist.validation.step.StepValidationPredicates.hasName;

@Data
public class StepValidator {
    Validator<Step> stepValidator = new Validator<>();
    List<ValidationError> validationErrors = new ArrayList<>();
    private Step step;

    public StepValidator(Step step) {
        this.step = step;
    }

    public StepValidator validateStepHasKeyword() {
        if (!stepValidator.validate(hasKeyword, step)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.STEP_NO_KEYWORD_VALIDATION_MESSAGE,step.getName()));
        }
        return this;
    }

    public StepValidator validateStepHasName() {
        if (!stepValidator.validate(hasName, step)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.STEP_NO_NAME_VALIDATION_MESSAGE));
        }
        return this;
    }
}
