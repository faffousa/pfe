
package com.vermeg.app.repository;

import java.util.List;

import com.vermeg.app.entity.Reaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReactionRepository extends MongoRepository<Reaction, Integer>{

	
	public List<Reaction> findByIdPostAndIduser(long idPost,long idUser);
	public Reaction findByIdReaction(long idReaction);
	public List<Reaction> findByIdPostAndReactionType(long idPost,String reactionType);
	
}
