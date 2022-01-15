package com.tpeel.featurealchemist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tpeel.featurealchemist.exceptions.FeatureFileParseException;
import com.tpeel.featurealchemist.models.mapping.Feature;
import gherkin.parser.ParseError;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FeatureDecompilerTest {

    private FeatureDecompiler featureDecompiler;

    @BeforeEach
    public void setup() {
        featureDecompiler = new FeatureDecompiler();
    }

    @Test
    @DisplayName("If the file provided is not a valid file a FileNotFoundException exception should be thrown with an appropriate message")
    public void ifTheFileProvidedIsNotAValidFileAnExceptionShouldBeThrown() {
        File file = new File("somepath");
        FileNotFoundException exception =
                assertThrows(FileNotFoundException.class, () -> featureDecompiler.convertFeatureFileIntoObject(file));
        assertEquals(".feature file [somepath] not found", exception.getMessage());
    }

    @Test
    @DisplayName("If an non feature file is provided a IllegalArgumentException exception should be thrown with an appropriate message")
    public void ifAnExceptionIsEncounteredDuringFileParsingAnExceptionShouldBeThrown() {
        File file = new File("src/test/resources/textFile.txt");
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> featureDecompiler.convertFeatureFileIntoObject(file));
        assertEquals("File provided is not a feature file", exception.getMessage());
    }

    @Test
    @DisplayName("If a .feature file contains invalid syntax a FeatureFileParseException exception should be thrown with an appropriate message")
    public void ifAnFeatureFileContainsInvalidSyntaxAFeatureFileParseExceptionShouldBeThrown() {
        File file = new File("src/test/resources/invalidFeature.feature");
        FeatureFileParseException exception =
                assertThrows(FeatureFileParseException.class,
                        () -> featureDecompiler.convertFeatureFileIntoObject(file));
        assertEquals("Error mapping .feature file json into Feature object", exception.getMessage());

    }

    @Test
    @DisplayName("If .feature file contains invalid syntax the "
            + "FeatureFileParseException thrown should contain the cause and the name of the offending feature file")
    public void ifAFeatureFileParseExceptionIsThrownItShouldContainTheExceptionAndFeatureFile() {
        File file = new File("src/test/resources/invalidFeature.feature");
        FeatureFileParseException exception =
                assertThrows(FeatureFileParseException.class,
                        () -> featureDecompiler.convertFeatureFileIntoObject(file));
        assertEquals("invalidFeature.feature",exception.getFeatureFilename());
        assertTrue(exception.getCause() instanceof ParseError);

    }

    @Test
    @DisplayName("If a .feature file contains valid syntax a deserialized object representing the file should be returned")
    public void ifAFeatureFileIsValidADeserializedObjectRepresentingTheFileShouldBeReturned() throws IOException {
        File file = new File("src/test/resources/validFeature.feature");
        Feature returnedFeature = featureDecompiler.convertFeatureFileIntoObject(file);
        assertNotNull(returnedFeature);
        assertEquals("Be a valid feature", returnedFeature.getName());
        assertEquals(1, returnedFeature.getScenarios().size());
    }


}