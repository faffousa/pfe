package com.vermeg.app.iserviceRest;

import com.vermeg.app.dto.*;
import com.vermeg.app.entity.*;
import com.vermeg.app.dto.ReactionDTO;
import com.vermeg.app.entity.Reaction;

public interface IServiceReaction {
	public ReactionDTO addreaction(long idUser, long idPost, String rt);
	public ReactionDTO updateReaction(long idReaction, String rt); 
	public void deleteReaction(long idReaction); 
	public int getReactionByIdPostAndIdUser(long idUser, long idPost); 
	public int countReactionsByPostAndType(long idPost ,String reactionType); 
	public Reaction getReactionToUpdate(long idUser, long idPost);

}
