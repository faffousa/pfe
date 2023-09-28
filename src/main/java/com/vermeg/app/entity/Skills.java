package com.vermeg.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Document(collection="Skills")
@Getter
@Setter
@AllArgsConstructor
public class Skills {
    @Id
    private String id;
    private String iduser;
    @Enumerated(EnumType.STRING)
    private Type type;
    private List<Macroskills> macroskills;

    public Skills() {
        super();
    }




}
