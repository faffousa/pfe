package com.vermeg.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MicroskillsDTO {
    private String name;
    private float progress;

    public MicroskillsDTO()
    {super();}
}
