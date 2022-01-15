package com.tpeel.featurealchemist.compilation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tpeel.featurealchemist.models.mapping.Comment;
import com.tpeel.featurealchemist.models.mapping.Example;
import com.tpeel.featurealchemist.models.mapping.Row;
import com.tpeel.featurealchemist.models.mapping.Tag;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExampleStringBuilderTest {

    @Test
    @DisplayName("If an Example Table is provided it should be converted to a string as expected")
    public void ifAnExampleIsProvidedItShouldBeConvertedToAStringAsExpected() {
        Example example = new Example();
        example.setKeyword("Example");
        Row row = new Row();
        row.setCells(List.of("value1", "true", "10000"));
        example.setRows(List.of(row));
        String actualComment = ExampleStringBuilder.convertExampleTableToString(List.of(example));
        String expectedComment = "Example:" + System.lineSeparator() + "|value1|true|10000|";
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If an Example Table contains comments it should be converted to a string as expected")
    public void ifAnExampleContainsCommentsItShouldBeConvertedToAStringAsExpected() {
        Example example = new Example();
        example.setKeyword("Example");
        Comment comment = new Comment();
        comment.setValue("Comment");
        example.setComments(List.of(comment));
        Row row = new Row();
        row.setCells(List.of("value1", "true", "10000"));
        example.setRows(List.of(row));
        String actualComment = ExampleStringBuilder.convertExampleTableToString(List.of(example));
        String expectedComment =
                "Comment" + System.lineSeparator() + "Example:" + System.lineSeparator() + "|value1|true|10000|";
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If an Example Table contains tags it should be converted to a string as expected")
    public void ifAnExampleContainsTagsItShouldBeConvertedToAStringAsExpected() {
        Example example = new Example();
        example.setKeyword("Example");
        Tag tag = new Tag();
        tag.setName("@Tag");
        example.setTags(List.of(tag));
        Row row = new Row();
        row.setCells(List.of("value1", "true", "10000"));
        example.setRows(List.of(row));
        String actualComment = ExampleStringBuilder.convertExampleTableToString(List.of(example));
        String expectedComment =
                "@Tag" + System.lineSeparator() + "Example:" + System.lineSeparator() + "|value1|true|10000|";
        assertEquals(expectedComment, actualComment);
    }
}