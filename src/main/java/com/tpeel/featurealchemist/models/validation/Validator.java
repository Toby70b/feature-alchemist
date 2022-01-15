package com.tpeel.featurealchemist.models.validation;

import lombok.Data;

import java.util.function.Predicate;

@Data
public class Validator<T> {

    public boolean validate(Predicate<T> validationTest, T objectUnderValidation) {
        return validationTest.test(objectUnderValidation);
    }
}
