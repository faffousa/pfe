package com.vermeg.app.auth.repo;

import com.vermeg.app.auth.entity.Role;
import com.vermeg.app.auth.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

	User findByEmail(String email);




	boolean existsByEmail(String email);

	User findByResetPasswordToken(String token);

	List<User> findByDisplayName(String displayName);
	List<User> findUsersByEmail(String email);
	List<User> findUsersByRolesEquals(Role role);
	List<User> findUsersByRoles(Role role);

	User findUserById(Long  id);
	User findOnByDisplayName(String displayName);

	  
}
