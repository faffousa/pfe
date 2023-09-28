package com.vermeg.app.dto;




import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostDTO {
	
	
	private int idPost ;
	private String title;
	private Boolean stateprivate;
	private Boolean comment;
	private Boolean important;
	private String postContent;
	private Date date;
	private long idformation;
	private long iduser;
	private String typefile;
	private Boolean test;
	
}   




 
 

 


