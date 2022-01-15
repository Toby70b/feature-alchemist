package com.tpeel.featurealchemist.compilation;

import com.tpeel.featurealchemist.models.mapping.Step;

public class StepStringBuilder {

    /**
     * Converts a step to string including comments
     *
     * @param step Step object to convert into String
     * @return a String representation of the Step object
     */
    public static String convertStepToString(Step step) {
        StringBuilder stepStringBuilder = new StringBuilder();
        if (step.getComments() != null) {
            stepStringBuilder.append(CommentStringBuilder.convertCommentsToString(step.getComments()));
        }
        stepStringBuilder.append(step.getKeyword())
                .append(Constants.SPACE)
                .append(step.getName());

        //According to cucumber docs a step can either have a datatable or a docstring, not both.
        if (step.getRows() != null) {
            stepStringBuilder.append(System.lineSeparator())
                    .append(RowStringBuilder.convertRowsToString(step.getRows()));
        } else if (step.getDocString() != null) {
            stepStringBuilder.append(System.lineSeparator())
                    .append(DocStringStringBuilder.convertDocStringToString(step.getDocString()));

        }
        return stepStringBuilder.toString();
    }
}
