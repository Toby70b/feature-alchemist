package com.tpeel.featurealchemist.models.mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Step {
    private List<Comment> comments;
    private String line;
    private String name;
    private String keyword;
    @JsonProperty("doc_string")
    private DocString docString;
    private List<Row> rows;
}
