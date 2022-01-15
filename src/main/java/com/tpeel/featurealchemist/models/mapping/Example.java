package com.tpeel.featurealchemist.models.mapping;

import lombok.Data;

import java.util.List;

@Data
public class Example {
    private List<Comment> comments;
    private String line;
    private String name;
    private String keyword;
    private String id;
    private String description;
    private List<Row> rows;
    private List<Tag> tags;
}
