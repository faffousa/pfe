package com.vermeg.app.auth.entity;

import java.util.List;

public class TextSimilarityRequest {
    private String text1;
    private String text2;
    private List<String> questions;
    // Constructors, getters, and setters

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
