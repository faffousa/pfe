package com.vermeg.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Microskills {
    private String name;
    private float progress;



    public Microskills()
    {super();}

    @Override
    public String toString() {
        return "Micro_skills{" +
                "name='" + name + '\'' +
                ", progress=" + progress +
                '}';
    }


}