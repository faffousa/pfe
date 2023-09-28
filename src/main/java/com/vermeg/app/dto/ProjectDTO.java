package com.vermeg.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProjectDTO {

    private String id;
    private String title;
    private String description;
    private List<String> names;
    private List<String> types;
    private List<String> downloadurl;
    private List<CorrectionsProjectDTO> correctionsList;
    public ProjectDTO() {
        super();
    }
}
