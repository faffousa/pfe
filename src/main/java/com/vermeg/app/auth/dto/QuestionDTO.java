package com.vermeg.app.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class QuestionDTO {
    private int idQuestion ;
    private Boolean reponse;
    private String questionContent;
    private Date date;
    private long iduser;


}
