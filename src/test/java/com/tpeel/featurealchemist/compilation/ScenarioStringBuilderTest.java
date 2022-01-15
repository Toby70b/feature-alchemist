package com.tpeel.featurealchemist.compilation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tpeel.featurealchemist.models.mapping.Comment;
import com.tpeel.featurealchemist.models.mapping.Example;
import com.tpeel.featurealchemist.models.mapping.Row;
import com.tpeel.featurealchemist.models.mapping.Scenario;
import com.tpeel.featurealchemist.models.mapping.Step;
import com.tpeel.featurealchemist.models.mapping.Tag;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScenarioStringBuilderTest {

    @Test
    @DisplayName("If a Scenario is provided it should be converted to a string as expected")
    public void ifAnScenarioIsProvidedItShouldBeConvertedToAStringAsExpected() {
        Scenario scenario = new Scenario();
        scenario.setKeyword("Scenario");
        scenario.setName("Some Scenario");
        String actualComment = ScenarioStringBuilder.convertScenarioToString(scenario);
        String expectedComment = "Scenario: Some Scenario" + System.lineSeparator();
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If a Scenario contains tags they should be converted to a string as expected")
    public void ifAnScenarioContainsTagTheyShouldBeConvertedToAStringAsExpected() {
        Scenario scenario = new Scenario();
        scenario.setKeyword("Scenario");
        scenario.setName("Some Scenario");
        Tag tag = new Tag();
        tag.setName("@Tag");
        scenario.setTags(List.of(tag));

        String actualComment = ScenarioStringBuilder.convertScenarioToString(scenario);
        String expectedComment = "@Tag" + System.lineSeparator() + "Scenario: Some Scenario" + System.lineSeparator();
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If a Scenario contains comments they should be converted to a string as expected")
    public void ifAnScenarioContainsCommentsTheyShouldBeConvertedToAStringAsExpected() {
        Scenario scenario = new Scenario();
        scenario.setKeyword("Scenario");
        scenario.setName("Some Scenario");
        Comment comment = new Comment();
        comment.setValue("Comment");
        scenario.setComments(List.of(comment));

        String actualComment = ScenarioStringBuilder.convertScenarioToString(scenario);
        String expectedComment =
                "Comment" + System.lineSeparator() + "Scenario: Some Scenario" + System.lineSeparator();
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If a Scenario contains steps they should be converted to a string as expected")
    public void ifAnScenarioContainsStepsTheyShouldBeConvertedToAStringAsExpected() {
        Scenario scenario = new Scenario();
        scenario.setKeyword("Scenario");
        scenario.setName("Some Scenario");
        Step step = new Step();
        step.setKeyword("Given");
        step.setName("Some prerequisite");
        scenario.setSteps(List.of(step));

        String actualSteps = ScenarioStringBuilder.convertScenarioToString(scenario);
        String expectedSteps =
                "Scenario: Some Scenario" + System.lineSeparator() + "Given Some prerequisite" + System.lineSeparator();
        assertEquals(expectedSteps, actualSteps);
    }

    @Test
    @DisplayName("If a Scenario contains an example table it should be converted to a string as expected")
    public void ifAnScenarioContainsAnExampleTableItShouldBeConvertedToAStringAsExpected() {
        Scenario scenario = new Scenario();
        scenario.setKeyword("Scenario");
        scenario.setName("Some Scenario");
        Example example = new Example();
        example.setKeyword("Example");
        Row row = new Row();
        row.setCells(List.of("value1", "true", "10000"));
        example.setRows(List.of(row));
        scenario.setExamples(List.of(example));

        String actualSteps = ScenarioStringBuilder.convertScenarioToString(scenario);
        String expectedSteps =
                "Scenario: Some Scenario" + System.lineSeparator() + "Example:" + System.lineSeparator()
                        + "|value1|true|10000|";
        assertEquals(expectedSteps, actualSteps);
    }
}