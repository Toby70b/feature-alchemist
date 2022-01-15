package com.tpeel.featurealchemist.compilation;

import com.tpeel.featurealchemist.models.mapping.DocString;

public class DocStringStringBuilder {
    /**
     * Converts the docstring of a cucumber step to string
     *
     * @param docString docstring to convert to string
     * @return string representation of the docString
     */
    public static String convertDocStringToString(DocString docString) {
        return Constants.DOCSTRING_OPEN_CLOSED_QUOTES +
                docString.getContentType() +
                System.lineSeparator() +
                docString.getValue() +
                System.lineSeparator() +
                Constants.DOCSTRING_OPEN_CLOSED_QUOTES;
    }
}
