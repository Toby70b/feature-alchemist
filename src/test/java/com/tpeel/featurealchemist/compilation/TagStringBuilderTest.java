package com.tpeel.featurealchemist.compilation;

import static org.junit.jupiter.api.Assertions.*;

import com.tpeel.featurealchemist.models.mapping.Tag;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TagStringBuilderTest {
    @Test
    @DisplayName("If a Tag is provided it should be converted to a string as expected")
    public void ifATagIsProvidedItShouldBeConvertedToAStringAsExpected() {
        Tag tag1 = new Tag();
        Tag tag2 = new Tag();
        tag1.setName("@Tag1");
        tag2.setName("@Tag2");
        List<Tag> tags = List.of(tag1,tag2);
        String actualTag = TagStringBuilder.convertTagsListToString(tags);
        String expectedTag = "@Tag1 @Tag2";
        assertEquals(expectedTag, actualTag);
    }
    
}