package com.vermeg.app.auth.repo;

import com.vermeg.app.auth.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface NotificationRepository extends MongoRepository<Notification , Long> {
}
