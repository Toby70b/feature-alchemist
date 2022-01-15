package com.tpeel.featurealchemist.exceptions;

import com.tpeel.featurealchemist.models.mapping.Feature;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.IOException;

/**
 * Exception to throw when an error occurs while attempting to write a Feature object into a .feature file via the FeatureCompiler
 * Thrown object should include a message indicating what went wrong, the causing exception and the Feature object that failed the write to file
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FeatureObjectParseException extends IOException {
    public FeatureObjectParseException(String message, Throwable cause, Feature featureObject) {
        super(message, cause);
        this.featureObject = featureObject;
    }

    Feature featureObject;
}
