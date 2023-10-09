package com.vermeg.app.auth.entity;

import com.vermeg.app.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import java.util.List;

@Document(collection = "question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Transient
    public static final String SEQUENCE_NAME = "question_sequence";

    @Column(name = "QUESTION_ID")
    @Id
    private long idQuestion;

    private User user;
    @Field(value = "Title")
    private String title;

    @Field(value = "Content")
    private String content;

    @Field(value = "Category")
    private String category;


    @Field(value = "Date")
    private String date;

    @Field(value = "IdUser")
    private long iduser;

    @Field(value = "Reponse")
    private Boolean reponse;

    @Field(value = "Message")
    private Boolean message;

    @Field(value = "TotalQuestions")

    private long totalQuestions;




    @Field(value = "IsDeleted")
    private Boolean isDeleted;
    private List<Message> messages;
    private List<Reponse> reponses;
    private int likes;
    private int dislikes;
    private long votes;
    private double averageRating;


    private List<Long> likesByUsers;

    // Getters

}
