package com.vermeg.app.serviceRest;


import java.util.List;

import com.vermeg.app.iserviceRest.IServiceReaction;
import com.vermeg.app.dto.ReactionDTO;
import com.vermeg.app.entity.Reaction;
import com.vermeg.app.repository.ReactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vermeg.app.dto.*;
import com.vermeg.app.entity.*;
import com.vermeg.app.auth.service.SequenceGeneratorService;
import com.vermeg.app.iserviceRest.*;
import com.vermeg.app.repository.*;

@Service
public class ReactionServiceImpl implements IServiceReaction {
	@Autowired
    private  SequenceGeneratorService sequenceGeneratorService;
	@Autowired
    private ModelMapper mapper;
	@Autowired
    private ReactionRepository reactionRepository;
	@Override
	public ReactionDTO addreaction(long idUser, long idPost, String rt) {
		Reaction reaction = new Reaction();
		
		reaction.setIdReaction(sequenceGeneratorService.generateSequence(Reaction.SEQUENCE_NAME));
			
		reaction.setIduser(idUser);
		reaction.setIdPost(idPost);
		reaction.setReactionType(rt);
		
		Reaction newReaction=reactionRepository.save(reaction);
			return mapper.map(newReaction, ReactionDTO.class);
		
	}
	
	@Override
	public ReactionDTO updateReaction(long idReaction,String rt) {
		Reaction reaction = new Reaction();

		Reaction reactionOld=reactionRepository.findByIdReaction(idReaction);
		reaction.setIdReaction(reactionOld.getIdReaction());
		reaction.setIdPost(reactionOld.getIdPost());
		reaction.setIduser(reactionOld.getIduser());
		reaction.setReactionType(rt);

        Reaction newReaction = reactionRepository.save(reaction);
	         return mapper.map(newReaction, ReactionDTO.class);
					
			
	}
	@Override
	public void deleteReaction(long idReaction) {
		Reaction reaction=reactionRepository.findByIdReaction(idReaction);
		reactionRepository.delete(reaction);
		
	}
	@Override
	public int getReactionByIdPostAndIdUser(long idUser, long idPost) {
		List<Reaction> reactionList= reactionRepository.findByIdPostAndIduser(idPost, idUser);
		int nbr = 0 ;
		for(Reaction reaction :reactionList)
		{
			nbr= nbr + 1;
		}
		
		return nbr;
	}
	@Override
	public int countReactionsByPostAndType(long idPost, String reactionType) {
		
		List<Reaction> reactionList= reactionRepository.findByIdPostAndReactionType( idPost,reactionType);
		int nbr = 0 ;
		for(Reaction reaction :reactionList)
		{
			nbr= nbr + 1;
		}
		
		return nbr;
		
	}
	@Override
	public Reaction getReactionToUpdate(long idUser, long idPost){
		List<Reaction> reactionList= reactionRepository.findByIdPostAndIduser(idPost, idUser);
		Reaction r= new Reaction();
		r=reactionList.get(0);
		return r;
		
	}

}
