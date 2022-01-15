package com.tpeel.featurealchemist.models.mapping;

import lombok.Data;

import java.util.List;

@Data
public class Row {
    private List<Comment> comments;
    private String line;
    private String id;
    private List<String> cells;
}
