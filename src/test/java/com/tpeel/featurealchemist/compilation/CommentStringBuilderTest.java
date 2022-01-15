package com.tpeel.featurealchemist.compilation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tpeel.featurealchemist.models.mapping.Comment;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentStringBuilderTest {

    @Test
    @DisplayName("If Comments are provided they should be converted to a string as expected")
    public void ifACommentIsProvidedItShouldBeConvertedToAStringAsExpected() {
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        comment1.setValue("Comment1");
        comment2.setValue("Comment2");
        List<Comment> comments = List.of(comment1,comment2);
        String actualComment = CommentStringBuilder.convertCommentsToString(comments);
        String expectedComment = "Comment1"+System.lineSeparator()+"Comment2"+System.lineSeparator();
        assertEquals(expectedComment, actualComment);
    }
}