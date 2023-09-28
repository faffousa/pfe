package com.vermeg.app.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReponseDTO {
    private int idReponse ;
    private String reponseContent;
    private Date date;
    private long idQuestion;
    private long idUser;
}
