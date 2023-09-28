package com.vermeg.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vermeg.app.entity.Post;
import com.vermeg.app.repository.LikeRepository;
import com.vermeg.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.vermeg.app.entity.Like;
import com.vermeg.app.auth.entity.User;
import com.vermeg.app.auth.service.SequenceGeneratorService;

@Service
public class LikeService {
	
	@Autowired
    LikeRepository likeRepository;
	
	@Autowired
    PostRepository postRepository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	private static final String GET_USER_BY_ID_API = "http://localhost:8082/api/auth/getUser/{id}";
	
	public User getUserByRestTemplate(long id){
		Map<String, Long> param = new HashMap<>();
		param.put("id", id);
		User user = restTemplateBuilder.build().getForObject(GET_USER_BY_ID_API, User.class, param);
		return user;
	}

	public int addLike(long idUser, long idPost){
		Like like = new Like();
		User user = getUserByRestTemplate(idUser);
		Post post = postRepository.findById(idPost).get();
		List<Like> likes = likeRepository.findAll();
		for(Like likee : likes) {
			if(likee.getUser().getId() == idUser && likee.getIdPost() == idPost){
				 likeRepository.deleteById(likee.getId());
				 post.setLikes(post.getLikes()-1);
				 postRepository.save(post);
				 return 1;
			}
		}
		like.setId(sequenceGeneratorService.generateSequence(Like.SEQUENCE_NAME));
		like.setUser(user);
		like.setIdPost(idPost);
		likeRepository.save(like);
		post.setLikes(post.getLikes()+1);
		postRepository.save(post);		
		return 0;
		
	}
	
	public boolean Liked(long idUser, long idPost){
		List<Like> likes = likeRepository.findAll();
		for(Like likee : likes) {
			if(likee.getUser().getId() == idUser && likee.getIdPost() == idPost){
				 return true;
			}
			else return false;
		}
		return false;
		
	}

	public List<Like> getAllLikes() {
	  return likeRepository.findAll();


	}

	public long countLikes() {
	  return likeRepository.count();

	}

}
