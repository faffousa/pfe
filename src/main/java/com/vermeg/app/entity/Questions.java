package com.vermeg.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Questions {
    private String question;
    private float points;
    private float timerperquestion;
    private List<String> correctanswer ;
    private List<String> wronganswer;

    public Questions()
    {super();}


}
