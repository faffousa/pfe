package com.vermeg.app.entity;



import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection="Section")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Section {
	

	   @Transient
    public static final String SEQUENCE_NAME = "database_sequences";
	    
	    @Id
	    @Field(value = "IdSection")
	    private long idSection;

	    @Field(value = "PostContent")
	    private String postContent;

	    @Field(value = "Title")
	    private String title;

	    @Field(value = "Date")
	    private Date date;
	
	    @Field(value = "IdFormation")
	    private long idformation;
	      
	   
	    @Field(value = "File")
	    private byte[] file;


		
	    
	    

	    
}
