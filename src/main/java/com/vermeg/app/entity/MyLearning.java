package com.vermeg.app.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class MyLearning {
	
	@Transient
    public static final String SEQUENCE_NAME = "mylearning_sequence";
	
	@Id
	private long idMyLearning;
	
	private User user;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private List<Course> courses;
	
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
}
