package com.tpeel.featurealchemist.compilation;

import static org.junit.jupiter.api.Assertions.*;

import com.tpeel.featurealchemist.models.mapping.Comment;
import com.tpeel.featurealchemist.models.mapping.Row;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RowStringBuilderTest {

    @Test
    @DisplayName("If a Row is provided it should be converted to a string as expected")
    public void ifARowIsProvidedItShouldBeConvertedToAStringAsExpected() {
        Row row = new Row();
        row.setCells(List.of("value1","value2","true","178"));
        String actualComment = RowStringBuilder.convertRowsToString(List.of(row));
        String expectedComment = "|value1|value2|true|178|";
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If a multiple Rows are provided it should be converted to a string as expected")
    public void ifMultipleRowsAreProvidedItShouldBeConvertedToAStringAsExpected() {
        Row row = new Row();
        row.setCells(List.of("value1","value2","true","178"));
        String actualComment = RowStringBuilder.convertRowsToString(List.of(row,row));
        String expectedComment = "|value1|value2|true|178|"+System.lineSeparator()+"|value1|value2|true|178|";
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("If the Row provided contains comments are they should be converted to a string as expected")
    public void IfTheRowProvidedContainsCommentsTheyShouldBeConvertedToAStringAsExpected() {
        Row row1 = new Row();
        Row row2 = new Row();
        row1.setCells(List.of("value1","value2","true","178"));

        Comment comment1 = new Comment();
        comment1.setValue("Comment1");
        row1.setComments(List.of(comment1));

        row2.setCells(List.of("value3","value4","false","52"));
        Comment comment2 = new Comment();
        comment2.setValue("Comment3");
        Comment comment3 = new Comment();
        comment3.setValue("Comment4");
        row2.setComments(List.of(comment2,comment3));

        String actualComment = RowStringBuilder.convertRowsToString(List.of(row1,row2));
        String expectedComment = "Comment1"+System.lineSeparator()
                + "|value1|value2|true|178|"+System.lineSeparator()
                + "Comment3"+System.lineSeparator()
                + "Comment4"+System.lineSeparator()
                + "|value3|value4|false|52|";
        assertEquals(expectedComment, actualComment);
    }

}