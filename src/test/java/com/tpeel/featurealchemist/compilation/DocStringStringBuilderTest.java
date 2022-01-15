package com.tpeel.featurealchemist.compilation;

import static org.junit.jupiter.api.Assertions.*;

import com.tpeel.featurealchemist.models.mapping.DocString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DocStringStringBuilderTest {
    @Test
    @DisplayName("If a Docstring is provided it should be converted to a string as expected")
    public void ifADocstringIsProvidedItShouldBeConvertedToAStringAsExpected() {
        DocString docString = new DocString();
        docString.setContentType("json");
        docString.setValue("{\n"
                + "\"key1\": \"value1\",\n"
                + "\"key2\": true,\n"
                + "\"key3\": 1\n"
                + "}");
        String actualDocstring = DocStringStringBuilder.convertDocStringToString(docString);
        String expectedDocString = "\"\"\"json"+System.lineSeparator()
                + "{\n"
                + "\"key1\": \"value1\",\n"
                + "\"key2\": true,\n"
                + "\"key3\": 1\n"
                + "}"+System.lineSeparator()
                + "\"\"\"";
        assertEquals(expectedDocString,actualDocstring);
    }
}