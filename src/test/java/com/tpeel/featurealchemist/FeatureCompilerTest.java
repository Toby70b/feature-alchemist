package com.tpeel.featurealchemist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tpeel.featurealchemist.exceptions.FeatureValidationException;
import com.tpeel.featurealchemist.models.mapping.Comment;
import com.tpeel.featurealchemist.models.mapping.Feature;
import com.tpeel.featurealchemist.models.mapping.Row;
import com.tpeel.featurealchemist.models.mapping.Scenario;
import com.tpeel.featurealchemist.models.mapping.Step;
import com.tpeel.featurealchemist.models.mapping.Tag;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


class FeatureCompilerTest {

    @TempDir
    File tempDir;
    File tempFile;

    private FeatureCompiler featureCompiler;

    @BeforeEach
    void setUp() throws IOException {
        featureCompiler = new FeatureCompiler();
        tempFile = new File(tempDir, "tempFeature.feature");
    }

    @Test
    @DisplayName("If the feature object contains a validation error a FeatureValidationException exception should be thrown with an appropriate message")
    public void ifTheFeatureObjectContainsAValidationErrorAFeatureValidationExceptionShouldBeThrownWithAnAppropriateMessage() {
        Feature feature = new Feature();
        FeatureValidationException exception = assertThrows(FeatureValidationException.class,
                () -> featureCompiler.convertObjectIntoFeatureFile(feature, tempDir));
        assertEquals("Feature object contains validation errors, the compilation cannot proceed " +
                "until these have been fixed as the compiled file would have invalid syntax. Please check the response "
                + "returned and fix any errors before re-attempting compilation", exception.getMessage());
    }

    @Test
    @DisplayName("If the feature object contains a validation error the FeatureValidationException exception thrown should include the validation errors")
    public void ifTheFeatureObjectContainsAValidationErrorAFeatureValidationExceptionShouldBeThrownWithTheValidationErrorsIncluded() {
        Feature feature = new Feature();
        FeatureValidationException exception = assertThrows(FeatureValidationException.class,
                () -> featureCompiler.convertObjectIntoFeatureFile(feature, tempDir));
        assertFalse(exception.getValidationResponse().getValidationErrors().isEmpty());
    }

    @Test
    @DisplayName("If a valid feature object is provided the feature object should be written to a file as expected")
    public void ifAValidFeatureObjectIsProvidedTheFeatureObjectShouldBeWrittenToaFileAsExpected()
            throws FeatureValidationException, IOException {
        Feature feature = createValidFeature();
        featureCompiler.convertObjectIntoFeatureFile(feature, tempFile);
        assertEquals(
                FileUtils.readFileToString(new File("src/test/resources/featureCompilerExampleFeature.feature"),
                        StandardCharsets.UTF_8),
                FileUtils.readFileToString(tempFile, StandardCharsets.UTF_8));
    }

    /**
     * Create a valid feature object for testing purposes. It is deemed "valid" in that it triggers no errors or
     * warnings from the various gherkin element validators
     *
     * @return a valid feature object
     */
    private Feature createValidFeature() {
        Feature feature = new Feature();
        feature.setKeyword("Feature");
        feature.setName("Guess the word");
        feature.setScenarios(createValidScenarios());
        feature.setTags(List.of(createTag("@Feature"), createTag("@Example")));
        return feature;
    }

    /**
     * Create a list of valid scenario objects for testing purposes. It is deemed "valid" in that it triggers no errors
     * or warnings from the various gherkin element validators
     *
     * @return a list of valid scenario objects
     */
    private List<Scenario> createValidScenarios() {
        return List.of(createFirstScenario(),createSecondScenario(),createThirdScenario());
    }

    /**
     * Create a scenario object for testing purposes. It is deemed "valid" in that it triggers no errors or warnings
     * from the various gherkin element validators
     *
     * @return a valid scenario object
     */
    private Scenario createFirstScenario() {
        Scenario scenario = createScenario("Scenario","Maker starts a game");
        Comment scenario1Comment = new Comment();
        scenario1Comment.setValue("# The first example has two steps");
        scenario.setComments(List.of(scenario1Comment));
        Tag tag1 = new Tag();
        Tag tag2 = new Tag();
        tag1.setName("@Scenario1");
        tag2.setName("@Example");
        scenario.setTags(List.of(tag1, tag2));
        Step step1 = createStep("When", "the Maker starts a game");
        Step step2 = createStep("Then", "the Maker waits for a Breaker to join");
        scenario.setSteps(List.of(step1, step2));
        return scenario;
    }

    /**
     * Create a scenario object for testing purposes. It is deemed "valid" in that it triggers no errors or warnings
     * from the various gherkin element validators
     *
     * @return a valid scenario object
     */
    private Scenario createSecondScenario() {
        Scenario scenario = createScenario("Scenario","Breaker joins a game");
        Comment scenario1Comment = new Comment();
        scenario1Comment.setValue("# The second example has three steps");
        scenario.setComments(List.of(scenario1Comment));
        scenario.setTags(List.of(createTag("@Scenario2")));
        Step step1 = createStep("Given", "the Maker has started a game with the word \"silky\"");
        Step step2 = createStep("When", "the Breaker joins the Maker's game");
        Step step3 = createStep("Then", "the Breaker must guess a word with 5 characters");
        scenario.setSteps(List.of(step1, step2, step3));
        return scenario;
    }

    /**
     * Create a scenario object for testing purposes. It is deemed "valid" in that it triggers no errors or warnings
     * from the various gherkin element validators
     *
     * @return a valid scenario object
     */
    private Scenario createThirdScenario() {
        Scenario scenario = createScenario("Scenario","Correct non-zero number of books found by author");
        scenario.setTags(List.of(createTag("@ExampleTable")));
        Step step1 = createStep("Given", "I have the following books in the store");
        step1.setRows(createStepExampleTable());
        Step step2 = createStep("When", "I search for books by author Erik Larson");
        Step step3 = createStep("Then", "I find 2 books");
        scenario.setSteps(List.of(step1, step2, step3));
        return scenario;
    }

    /**
     * Creates a list of rows for a step with an example table
     * @return a list of valid Row objects
     */
    private List<Row> createStepExampleTable() {
        Row row1 = new Row();
        Row row2 = new Row();
        Row row3 = new Row();
        row1.setCells(List.of("The Devil in the White City","Erik Larson"));
        row2.setCells(List.of("The Lion, the Witch and the Wardrobe","C.S. Lewis"));
        row3.setCells(List.of("In the Garden of Beasts","Erik Larson"));
        return List.of(row1,row2,row3);
    }


    /**
     * Creates a step with a keyword and name
     *
     * @param keyword the steps keyword e.g. given, when, then etc...
     * @param name    name of the step
     * @return a Step object with keyword and name set
     */
    private Step createStep(String keyword, String name) {
        Step step1 = new Step();
        step1.setKeyword(keyword);
        step1.setName(name);
        return step1;
    }

    /**
     * Creates a valid tag with a name
     * @param name name of the tag
     * @return a Tag object with a name set
     */
    private Tag createTag(String name) {
        Tag tag1 = new Tag();
        tag1.setName(name);
        return tag1;
    }

    /**
     * Creates a new scenario with keyword and name
     * @param keyword keyword of the scenario e.g. scenario or scenario_outline
     * @param name name of the scenario
     * @return a Scenario object with keyword and name set
     */
    private Scenario createScenario(String keyword, String name) {
        Scenario scenario = new Scenario();
        scenario.setKeyword(keyword);
        scenario.setName(name);
        return scenario;
    }
}
