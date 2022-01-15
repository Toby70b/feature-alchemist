package com.tpeel.featurealchemist.compilation;

import com.tpeel.featurealchemist.models.mapping.Row;

import java.util.Iterator;
import java.util.List;

public class RowStringBuilder {
    /**
     * Converts a list of Rows into a String
     *
     * @param rows List of Row objects to convert into String
     * @return a string representation of the rows, with | dividers included between columns
     */
    public static String convertRowsToString(List<Row> rows) {
        StringBuilder rowStringBuilder = new StringBuilder();
        for (Iterator<Row> iterator = rows.iterator(); iterator.hasNext(); ) {
            Row row = iterator.next();
            if (row.getComments() != null) {
                rowStringBuilder.append(CommentStringBuilder.convertCommentsToString(row.getComments()));
            }
            rowStringBuilder.append("|");
            for (String cell : row.getCells()) {
                //Some cells contain dividers which are meant to be escaped, this re-escapes them to produce the string as intended
                String newCell = cell.replace("|", "\\|");
                rowStringBuilder.append(newCell)
                        .append("|");
            }
            rowStringBuilder.append(iterator.hasNext()? System.lineSeparator():"");
        }
        return rowStringBuilder.toString();
    }
}
