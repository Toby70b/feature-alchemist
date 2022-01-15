package com.tpeel.featurealchemist.compilation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tpeel.featurealchemist.models.mapping.Comment;
import com.tpeel.featurealchemist.models.mapping.Feature;
import com.tpeel.featurealchemist.models.mapping.Scenario;
import com.tpeel.featurealchemist.models.mapping.Tag;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeatureStringBuilderTest {

    @Test
    @DisplayName("If a Feature is provided it should be converted to a string as expected")
    public void ifAnFeatureIsProvidedItShouldBeConvertedToAStringAsExpected() {
        Feature Feature = new Feature();
        Feature.setKeyword("Feature");
        Feature.setName("Some Feature");
        String actualComment = FeatureStringBuilder.convertFeatureToString(Feature);
        String expectedComment =
                "Feature: Some Feature" + System.lineSeparator()
                        + System.lineSeparator();
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If a Feature contains tags it should be converted to a string as expected")
    public void ifAnFeatureContainsTagItShouldBeConvertedToAStringAsExpected() {
        Feature Feature = new Feature();
        Feature.setKeyword("Feature");
        Feature.setName("Some Feature");
        Tag tag = new Tag();
        tag.setName("@Tag");
        Feature.setTags(List.of(tag));

        String actualComment = FeatureStringBuilder.convertFeatureToString(Feature);
        String expectedComment =
                "@Tag" + System.lineSeparator() + "Feature: Some Feature" + System.lineSeparator()
                        + System.lineSeparator();
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If a Feature contains comments it should be converted to a string as expected")
    public void ifAnFeatureContainsCommentsItShouldBeConvertedToAStringAsExpected() {
        Feature Feature = new Feature();
        Feature.setKeyword("Feature");
        Feature.setName("Some Feature");
        Comment comment = new Comment();
        comment.setValue("Comment");
        Feature.setComments(List.of(comment));

        String actualComment = FeatureStringBuilder.convertFeatureToString(Feature);
        String expectedComment =
                "Comment" + System.lineSeparator() + "Feature: Some Feature" + System.lineSeparator()
                        + System.lineSeparator();
        assertEquals(expectedComment, actualComment);
    }


    @Test
    @DisplayName("If a Feature contains scenarios it should be converted to a string as expected")
    public void ifAnFeatureContainsScenariosItShouldBeConvertedToAStringAsExpected() {
        Feature Feature = new Feature();
        Feature.setKeyword("Feature");
        Feature.setName("Some Feature");
        Scenario scenario = new Scenario();
        scenario.setKeyword("Scenario");
        scenario.setName("Some Scenario");
        Feature.setScenarios(List.of(scenario));

        String actualScenario = FeatureStringBuilder.convertFeatureToString(Feature);
        String expectedScenario =
                "Feature: Some Feature" + System.lineSeparator() + System.lineSeparator() + "Scenario: Some Scenario"
                        + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedScenario, actualScenario);
    }

    @Test
    @DisplayName("If a Feature contains a description it should be converted to a string as expected")
    public void ifAnFeatureContainsADescriptionItShouldBeConvertedToAStringAsExpected() {
        Feature Feature = new Feature();
        Feature.setKeyword("Feature");
        Feature.setName("Some Feature");
        Feature.setDescription("Some Description");
        Scenario scenario = new Scenario();
        scenario.setKeyword("Scenario");
        scenario.setName("Some Scenario");
        Feature.setScenarios(List.of(scenario));

        String actualScenario = FeatureStringBuilder.convertFeatureToString(Feature);
        String expectedScenario =
                "Feature: Some Feature" + System.lineSeparator()
                        + "Some Description"
                        + System.lineSeparator() + System.lineSeparator() + "Scenario: Some Scenario"
                        + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedScenario, actualScenario);
    }


}