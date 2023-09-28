package com.vermeg.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class Cart {
	
	@Transient
    public static final String SEQUENCE_NAME = "cart_sequence";
	
	@Id
	private long idCart;
	
	private Course course;
	
	private User user;
	
	private boolean state;
	
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	
	
	
	
	

}
