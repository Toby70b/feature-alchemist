package com.tpeel.featurealchemist.models.mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DocString {
    private String line;
    @JsonProperty("content_type")
    private String contentType;
    private String value;
}
