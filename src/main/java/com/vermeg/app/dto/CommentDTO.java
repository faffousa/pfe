package com.vermeg.app.dto;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommentDTO {
	
	
	private int idComment ;
	private String commentContent;
	private Date date;
	private long idPost;
	private long idUser;
	
	
}   




 
 

 


