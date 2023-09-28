package com.vermeg.app.auth.repo;

import com.vermeg.app.auth.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository extends MongoRepository<Message, Long> {
   // List<Message> findByReceiverUserId(Long userId ,Long receiverUserId);
    List<Message> findMessagesByReceiverUserIdAndAndUserId(Long userId ,Long receiverUserId);
    List<Message> findMessagesByUserIdAndReceiverUserId(Long userId ,Long receiverUserId);




}
