package com.vermeg.app.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Document(collection = "Formation")

public class Formation {

	@Transient
	public static final String SEQUENCE_NAME = "database_sequences";

	@Id
	@Field(value = "IdFormation")
	private long idFormation;

	@Field(value = "Userr")
	private long idUserr;

	@Field(value = "Coursename")
	private String coursename;

	@Field(value = "Date")
	private Date date;

	@Field(value = "Category")
	private String category;

	@Field(value = "Difficultylevel")
	private String difficultylevel;

	@Field(value = "Coursesummary")
	private String coursesummary;

	@Field(value = "Instructorname")
	private String instructorname;

	@Field(value = "Descriptioncourse")
	private String descriptioncourse;

	@Field(value = "Descriptiontrainer")
	private String descriptiontrainer;

	@Field(value = "Price")
	private float price;

	@Field(value = "idQuiz")
	private List<String> idQuiz;

	@Field(value = "test")
	public Boolean test;
	
	 @Field(value = "typefile")
	    private String typefile;

	

}
