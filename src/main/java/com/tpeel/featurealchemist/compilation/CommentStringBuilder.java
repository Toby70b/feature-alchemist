package com.tpeel.featurealchemist.compilation;

import com.tpeel.featurealchemist.models.mapping.Comment;

import java.util.List;

public class CommentStringBuilder {
    /**
     * Converts the comments of a cucumber element into a string, can be appended to a file
     *
     * @param comments list of comments to convert to string
     * @return string representation of the comments
     */
    public static String convertCommentsToString(List<Comment> comments) {
        StringBuilder commentsStringBuilder = new StringBuilder();
        for (Comment comment : comments) {
            commentsStringBuilder.append(comment.getValue())
                    .append(System.lineSeparator());
        }
        return commentsStringBuilder.toString();
    }
}
