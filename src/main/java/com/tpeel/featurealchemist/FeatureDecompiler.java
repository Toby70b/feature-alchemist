package com.tpeel.featurealchemist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpeel.featurealchemist.exceptions.FeatureFileParseException;
import com.tpeel.featurealchemist.models.mapping.Feature;
import gherkin.formatter.JSONFormatter;
import gherkin.parser.Parser;
import gherkin.util.FixJava;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FilenameUtils;

/**
 * Utility class for decompiling .feature files into a Feature object
 */
public class FeatureDecompiler {
    private static final String FEATURE_FILE_EXTENSION = "feature";

    /**
     * Converts a .feature file into a Feature object, returns first feature in file.
     *
     * @param featureFile the .feature file to convert
     * @return Feature object representing the .feature file converted
     * @throws FeatureFileParseException if error occurs converting the json representation of the .feature file into a Feature object
     * @throws FileNotFoundException     if file specified in filepath param can't be found
     * @throws IllegalArgumentException if the file provided is not a feature file
     */
    public Feature convertFeatureFileIntoObject(File featureFile) throws IOException {
        if(!featureFile.isFile()){
            throw new FileNotFoundException(String.format(".feature file [%s] not found",featureFile.getPath()));
        }
        if(!FilenameUtils.getExtension(featureFile.getPath()).equals(FEATURE_FILE_EXTENSION)){
            throw new IllegalArgumentException("File provided is not a feature file");
        }
        ObjectMapper mapper = new ObjectMapper();
        Feature[] features;
        try {
            String featureJson = mapFileToJson(featureFile);
            features = mapper.readValue(featureJson, Feature[].class);
        } catch (Exception ex) {
            throw new FeatureFileParseException("Error mapping .feature file json into Feature object", ex, featureFile.getName());
        }
        return features[0];
    }


    /**
     * @param featureFile the .feature file to convert
     * @return String representing the JSON representation of the .feature file
     * @throws FileNotFoundException if file specified in filepath param can't be found
     */
    private String mapFileToJson(File featureFile) throws FileNotFoundException {
        String featureText = FixJava.readReader(new InputStreamReader(new FileInputStream(featureFile), StandardCharsets.UTF_8));
        StringBuilder featureJson = new StringBuilder();
        try (JSONFormatter formatter = new JSONFormatter(featureJson)) {
            Parser parser = new Parser(formatter);
            parser.parse(featureText, featureFile.getPath(), 0);
            formatter.done();
        }
        return featureJson.toString();
    }
}
