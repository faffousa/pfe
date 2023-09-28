
package com.vermeg.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReactionDTO {
	private long idLike;
	private long idPost;
	private long iduser;
	public String reactionType;
	

}