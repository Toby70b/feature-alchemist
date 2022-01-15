package com.tpeel.featurealchemist.compilation;

import com.tpeel.featurealchemist.models.mapping.Tag;

import java.util.List;

public class TagStringBuilder {
    /**
     * Converts a list of tags into a String
     *
     * @param tags list of tags to convert into String
     * @return a String representation of the list of Tags
     */
    public static String convertTagsListToString(List<Tag> tags) {
        StringBuilder tagsList = new StringBuilder();
        for (Tag tag : tags) {
            tagsList.append(tag.getName())
                    .append(Constants.SPACE);
        }
        return tagsList.toString().trim();
    }
}
