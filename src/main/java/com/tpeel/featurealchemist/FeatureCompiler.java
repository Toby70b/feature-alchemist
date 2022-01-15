package com.tpeel.featurealchemist;

import static com.tpeel.featurealchemist.compilation.FeatureStringBuilder.convertFeatureToString;

import com.tpeel.featurealchemist.exceptions.FeatureObjectParseException;
import com.tpeel.featurealchemist.exceptions.FeatureValidationException;
import com.tpeel.featurealchemist.models.mapping.Feature;
import com.tpeel.featurealchemist.models.validation.ValidationError;
import com.tpeel.featurealchemist.models.validation.ValidationResponse;
import java.io.File;
import java.io.FileWriter;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for converting a Feature object back into a .feature file
 */
@Slf4j
public class FeatureCompiler {

    /**
     * Converts a Feature object into a .feature file. There <b>must</b> only be one feature in the .feature file
     *
     * @param feature  Feature object that is to be converted
     * @param file file to be written to. This file should have the .feature extension.
     * @throws FeatureObjectParseException if the file exists but is a directory rather than a regular file, does not
     *                                     exist but cannot be created, or cannot be opened for any other reason. Or if
     *                                     an I/O exception occurs when appending to the file
     */
    public void convertObjectIntoFeatureFile(Feature feature, File file)
            throws FeatureObjectParseException, FeatureValidationException {
        GherkinValidator gherkinValidator = new GherkinValidator();
        ValidationResponse validationResponse = gherkinValidator.validate(feature);

        if (!validationResponse.isValid()) {
            log.error("Validation errors found in feature model, " +
                    "the compilation cannot proceed until these have been fixed as the compiled file would not be valid syntax");
            for (ValidationError error : validationResponse.getValidationErrors()) {
                log.error(error.toString());
            }
            throw new FeatureValidationException(
                    "Feature object contains validation errors, the compilation cannot proceed " +
                            "until these have been fixed as the compiled file would have invalid syntax. Please check the response "
                            + "returned and fix any errors before re-attempting compilation", validationResponse);
        }

        if (validationResponse.containsValidationWarnings()) {
            log.warn("Validation warnings found in feature model, " +
                    "these wont prevent compilation but should be fixed to adhere to best practises");
            for (ValidationError error : validationResponse.getValidationErrors()) {
                log.warn(error.toString());
            }
        }

        try (FileWriter fw = new FileWriter(file)) {
            fw.append(convertFeatureToString(feature).trim());
        } catch (Exception ex) {
            throw new FeatureObjectParseException("Error writing Feature Object to .feature file", ex, feature);
        }
    }

}
