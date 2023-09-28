package com.vermeg.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Macroskills {
    private String name;
    private float totalprogress;


    private List<Microskills> microskills;

    public Macroskills()
    {super();}


    @Override
    public String toString() {
        return "Macro_skills{" +
                "name='" + name + '\'' +
                ", total_progress=" + totalprogress +
                ", micro_skills=" + microskills +
                '}';
    }




}
