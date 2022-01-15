package com.tpeel.featurealchemist.models.mapping;

import lombok.Data;

import java.util.List;

@Data
public class Scenario {
    private List<Comment> comments;
    private String line;
    private String name;
    private String keyword;
    private String id;
    private String description;
    private String type;
    private List<Example> examples;
    private List<Step> steps;
    private List<Tag> tags;
}
