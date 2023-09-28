package com.vermeg.app.dto;




import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class SectionDTO {
	
	
	private int idSection ;
	private String title;
	private String postContent;
	private Date date;
	private long idformation;
	private byte[] file;
	
	
}   




 
 

 


