package com.tpeel.featurealchemist.compilation;

import com.tpeel.featurealchemist.models.mapping.Example;

import java.util.List;

public class ExampleStringBuilder {
    /**
     * Converts a list of example tables into a String
     *
     * @param examples List of example to convert into String
     * @return String representation of the example tables
     */
    public static String convertExampleTableToString(List<Example> examples) {
        StringBuilder exampleTableStringBuilder = new StringBuilder();
        for (Example example : examples) {
            if (example.getComments() != null) {
                exampleTableStringBuilder.append(CommentStringBuilder.convertCommentsToString(example.getComments()));
            }
            if (example.getTags() != null) {
                exampleTableStringBuilder.append(TagStringBuilder.convertTagsListToString(example.getTags()))
                        .append(System.lineSeparator());
            }
            exampleTableStringBuilder.append(example.getKeyword())
                    .append(Constants.COLON)
                    .append(System.lineSeparator())
                    .append(RowStringBuilder.convertRowsToString(example.getRows()));
        }
        return exampleTableStringBuilder.toString();
    }
}
