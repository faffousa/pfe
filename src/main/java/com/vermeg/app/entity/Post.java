package com.vermeg.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.vermeg.app.auth.entity.User;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Transient
    public static final String SEQUENCE_NAME = "post_sequence";
	
	@Id
	private long idPost;
	
	private String publication;
	@JsonIgnore
	private User user;

    @Field(value = "Date")
    private String date;

    @Field(value = "IdUser")
    private long iduser;
    
    @Field(value = "typefile")
    private String typefile;
    
    @Field(value = "test")
	public Boolean test;
    
    @Field(value = "StatePrivate")
    private Boolean stateprivate;
    
    @Field(value = "Comment")
    private Boolean comment;

    @Field(value = "important")
    private Boolean important;
    @JsonIgnore
    @Field(value = "IdFormation")
    private long idformation;

	private List<Comment> comments;
	
	private int likes;

}
