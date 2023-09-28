package com.vermeg.app.dto;







import com.vermeg.app.entity.Reaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class LikeDTO {
	private long idLike;
	private long idPost;
	private long iduser;
	private Reaction reactionType;


}




 
 

 


