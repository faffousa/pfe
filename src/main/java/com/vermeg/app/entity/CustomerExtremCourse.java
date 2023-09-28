package com.vermeg.app.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerExtremCourse {
    private String name;
    private List<CustomerPhase> phases;
    private String quizzId;
}
