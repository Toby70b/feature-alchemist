package com.tpeel.featurealchemist.compilation;

import static com.tpeel.featurealchemist.compilation.CommentStringBuilder.convertCommentsToString;
import static com.tpeel.featurealchemist.compilation.Constants.COLON;
import static com.tpeel.featurealchemist.compilation.Constants.SPACE;
import static com.tpeel.featurealchemist.compilation.ExampleStringBuilder.convertExampleTableToString;
import static com.tpeel.featurealchemist.compilation.StepStringBuilder.convertStepToString;
import static com.tpeel.featurealchemist.compilation.TagStringBuilder.convertTagsListToString;

import com.tpeel.featurealchemist.models.mapping.Scenario;
import com.tpeel.featurealchemist.models.mapping.Step;

public class ScenarioStringBuilder {

    /**
     * Convert a scenario into a String including steps
     *
     * @param scenario Scenario object to convert into string
     * @return String representation of the scenario Object
     */
    public static String convertScenarioToString(Scenario scenario) {
        StringBuilder scenarioStringBuilder = new StringBuilder();
        if (scenario.getComments() != null) {
            scenarioStringBuilder.append(convertCommentsToString(scenario.getComments()));
        }
        if (scenario.getTags() != null) {
            scenarioStringBuilder.append(convertTagsListToString(scenario.getTags()))
                    .append(System.lineSeparator());
        }
        scenarioStringBuilder
                .append(scenario.getKeyword())
                .append(COLON)
                .append(SPACE)
                .append(scenario.getName())
                .append(System.lineSeparator());

        if (scenario.getSteps() != null) {
            for (Step step : scenario.getSteps()) {
                scenarioStringBuilder.append(convertStepToString(step))
                        .append(System.lineSeparator());
            }
        }

        if (scenario.getExamples() != null) {
            scenarioStringBuilder.append(convertExampleTableToString(scenario.getExamples()));
        }
        return scenarioStringBuilder.toString();
    }
}
