package com.tpeel.featurealchemist.models.mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Feature {
    private List<Comment> comments;
    private String line;
    private String name;
    private String keyword;
    private String id;
    private String description;
    private String uri;
    @JsonProperty("elements")
    private List<Scenario> scenarios;
    private List<Tag> tags;


}
