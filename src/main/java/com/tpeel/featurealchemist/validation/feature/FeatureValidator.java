package com.tpeel.featurealchemist.validation.feature;

import com.tpeel.featurealchemist.models.mapping.Feature;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationErrorMessages;
import com.tpeel.featurealchemist.models.validation.ValidationErrorSeverity;
import com.tpeel.featurealchemist.models.validation.Validator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FeatureValidator {
    Validator<Feature> featureValidator = new Validator<>();
    List<ValidationError> validationErrors = new ArrayList<>();
    private Feature feature;

    public FeatureValidator(Feature feature) {
        this.feature = feature;
    }

    public FeatureValidator validateFeatureHasKeyword() {
        if (!featureValidator.validate(FeatureValidationPredicates.hasKeyword, feature)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.ERROR, ValidationErrorMessages.FEATURE_NO_KEYWORD_VALIDATION_MESSAGE,feature.getName()));
        }
        return this;
    }

    public FeatureValidator validateFeatureHasName() {
        if (!featureValidator.validate(FeatureValidationPredicates.hasName, feature)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_NAME_VALIDATION_MESSAGE));
        }
        return this;
    }

    public FeatureValidator validateFeatureHasDescription() {
        if (!featureValidator.validate(FeatureValidationPredicates.hasDescription, feature)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_DESC_VALIDATION_MESSAGE,feature.getName()));
        }
        return this;
    }

    public FeatureValidator validateFeatureHasScenarios() {
        if (!featureValidator.validate(FeatureValidationPredicates.hasScenarios, feature)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_SCENARIOS_VALIDATION_MESSAGE,feature.getName()));
        }
        return this;
    }

    public FeatureValidator validateFeatureHasTags() {
        if (!featureValidator.validate(FeatureValidationPredicates.hasTags, feature)) {
            validationErrors.add(new ValidationError(ValidationErrorSeverity.WARNING, ValidationErrorMessages.FEATURE_NO_TAGS_VALIDATION_MESSAGE,feature.getName()));
        }
        return this;
    }
}
