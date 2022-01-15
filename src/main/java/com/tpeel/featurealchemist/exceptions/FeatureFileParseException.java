package com.tpeel.featurealchemist.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.IOException;

/**
 * Exception to throw when an error occurs while attempting to parse a .feature file into a Feature object via the FeatureDecompiler
 * Thrown object should include a message indicating what went wrong, the causing exception and the filename of the feature the error occured on
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FeatureFileParseException extends IOException {
    public FeatureFileParseException(String message, Throwable cause, String featureFilename) {
        super(message, cause);
        this.featureFilename = featureFilename;
    }

    String featureFilename;
}
