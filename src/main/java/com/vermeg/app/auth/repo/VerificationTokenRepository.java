package com.vermeg.app.auth.repo;

import com.vermeg.app.auth.entity.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VerificationTokenRepository extends MongoRepository<VerificationToken, Long>{
	
	VerificationToken findByToken(String token);

}
