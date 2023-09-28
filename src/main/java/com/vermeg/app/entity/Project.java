package com.vermeg.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="Project")
@Getter
@Setter
@AllArgsConstructor
public class Project {
    @Id
    private String id;
    private String title;
    private String description;
    private List<String> names;
    private List<String> types;
    private List<String> downloadurl;
    private List<CorrectionsProject> correctionsList;
    public Project() {
        super();
    }
}

