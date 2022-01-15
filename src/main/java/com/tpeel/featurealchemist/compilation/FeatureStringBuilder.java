package com.tpeel.featurealchemist.compilation;

import com.tpeel.featurealchemist.models.mapping.Feature;
import com.tpeel.featurealchemist.models.mapping.Scenario;

public class FeatureStringBuilder {

    /**
     * Converts a feature to string, including any elements like scenarios, feature is formatted to look like a standard
     * cucumber file
     *
     * @param feature Feature object to convert to string
     * @return String representation of the feature object
     */
    public static String convertFeatureToString(Feature feature) {
        StringBuilder featureStringBuilder = new StringBuilder();
        if (feature.getComments() != null) {
            featureStringBuilder.append(CommentStringBuilder.convertCommentsToString(feature.getComments()));
        }
        if (feature.getTags() != null) {
            featureStringBuilder.append(TagStringBuilder.convertTagsListToString(feature.getTags()))
                    .append(System.lineSeparator());
        }

        String featureDescription = feature.getDescription() + System.lineSeparator();
        boolean featureHasDescription = feature.getDescription() != null && !feature.getDescription().isEmpty();

        featureStringBuilder
                .append(feature.getKeyword())
                .append(Constants.COLON)
                .append(Constants.SPACE)
                .append(feature.getName())
                .append(System.lineSeparator())
                .append(featureHasDescription ? featureDescription : "")
                .append(System.lineSeparator());

        if (feature.getScenarios() != null) {
            for (Scenario scenario : feature.getScenarios()) {
                featureStringBuilder.append(ScenarioStringBuilder.convertScenarioToString(scenario))
                        .append(System.lineSeparator());
            }
        }
        return featureStringBuilder.toString();
    }
}
