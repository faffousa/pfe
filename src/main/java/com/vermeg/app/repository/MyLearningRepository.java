package com.vermeg.app.repository;

import com.vermeg.app.entity.MyLearning;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vermeg.app.auth.entity.User;

public interface MyLearningRepository extends MongoRepository<MyLearning, Long>{
	
	MyLearning findMyLearningByUser(User user);
	
	boolean existsByUser(User user);
	
	MyLearning findMyLearningByUserId(long id);

}
