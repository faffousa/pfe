package com.vermeg.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Document(collection="Reaction")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reaction {
	   @Transient
	    public static final String SEQUENCE_NAME = "database_sequences";
	    @Id
	    @Field(value = "IdReaction")
	    private long idReaction;
	    
	    @Field(value = "IdPost")
	    private long idPost;
	    
	    @Field(value = "IdUser")
	    private long iduser;
	    
	    @Field(value="ReactionType")
	    private  String reactionType;

	

}
