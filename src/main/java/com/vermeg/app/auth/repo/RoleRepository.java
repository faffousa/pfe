package com.vermeg.app.auth.repo;

import com.vermeg.app.auth.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {

	Role findByName(String name);
}
