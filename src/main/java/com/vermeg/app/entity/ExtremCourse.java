package com.vermeg.app.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExtremCourse {
    private String name;
    private List<KolbPhase> phases;
    private String quizzId;
}
