package com.tpeel.featurealchemist.compilation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tpeel.featurealchemist.models.mapping.Comment;
import com.tpeel.featurealchemist.models.mapping.DocString;
import com.tpeel.featurealchemist.models.mapping.Row;
import com.tpeel.featurealchemist.models.mapping.Step;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StepStringBuilderTest {
    @Test
    @DisplayName("If a Step is provided it should be converted to a string as expected")
    public void ifAnStepIsProvidedItShouldBeConvertedToAStringAsExpected() {
        Step step = new Step();
        step.setKeyword("Given");
        step.setName("Some prerequisite");
        String actualComment = StepStringBuilder.convertStepToString(step);
        String expectedComment = "Given Some prerequisite";
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If a Step contains comments they should be converted to a string as expected")
    public void ifAnStepContainsCommentsTheyShouldBeConvertedToAStringAsExpected() {
        Step step = new Step();
        step.setKeyword("Given");
        step.setName("Some prerequisite");
        Comment comment = new Comment();
        comment.setValue("Comment");
        step.setComments(List.of(comment));
        String actualComment = StepStringBuilder.convertStepToString(step);
        String expectedComment = "Comment"+System.lineSeparator()+"Given Some prerequisite";
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If a Step contains rows they should be converted to a string as expected")
    public void ifAnStepContainsRowsTheyShouldBeConvertedToAStringAsExpected() {
        Step step = new Step();
        step.setKeyword("Given");
        step.setName("Some prerequisite");
        Row row = new Row();
        row.setCells(List.of("value1", "true", "10000"));
        step.setRows(List.of(row));
        String actualComment = StepStringBuilder.convertStepToString(step);
        String expectedComment = "Given Some prerequisite"+System.lineSeparator()+"|value1|true|10000|";
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If a Step contains a docstring it should be converted to a string as expected")
    public void ifAnStepContainsADocstringItShouldBeConvertedToAStringAsExpected() {
        Step step = new Step();
        step.setKeyword("Given");
        step.setName("Some prerequisite");

        DocString docString = new DocString();
        docString.setContentType("json");
        docString.setValue("{\n"
                + "\"key1\": \"value1\",\n"
                + "\"key2\": true,\n"
                + "\"key3\": 1\n"
                + "}");

        step.setDocString(docString);
        String actualComment = StepStringBuilder.convertStepToString(step);
        String expectedComment = "Given Some prerequisite"+System.lineSeparator()+"\"\"\"json"+System.lineSeparator()
                + "{\n"
                + "\"key1\": \"value1\",\n"
                + "\"key2\": true,\n"
                + "\"key3\": 1\n"
                + "}"+System.lineSeparator()
                + "\"\"\"";
        assertEquals(expectedComment, actualComment);
    }
}