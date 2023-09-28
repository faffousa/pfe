package com.vermeg.app.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reponse {
    @Transient
    public static final String SEQUENCE_NAME = "reponse_sequence";

    @Id
    private long idReponse;

    private String reponse;

    @Field(value = "Date")
    private String date;

    @Field(value = "IdQuestion")
    private long idQuestion;

    private User user;
    private int likes;
    private int dislikes;

    @Field(value = "IsDeleted")
    private Boolean isDeleted;

    @Field(value = "IdUser")
    private long idUser;
    private List<Long> likedByUsers;




}
