package com.vermeg.app.auth.service;

import com.vermeg.app.auth.entity.Message;
import com.vermeg.app.auth.entity.User;
import com.vermeg.app.auth.repo.MessageRepository;
import com.vermeg.app.auth.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;


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



    public List<Message> getMessageForUser(Long receiverUserId , Long userId) {
        List<Message> a =  messageRepository.findMessagesByReceiverUserIdAndAndUserId(receiverUserId ,userId );
        List<Message> b =  messageRepository.findMessagesByUserIdAndReceiverUserId(receiverUserId ,userId );
        List<Message> concatenatedList = new ArrayList<>();

        concatenatedList.addAll(a);

        concatenatedList.addAll(b);

        return concatenatedList;
    }

    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }





    public Message sendMessage(Message message, long idUser, long receiverUserId) {
        User user = getUserByRestTemplate(idUser);
        User receiverUser = getUserByRestTemplate(receiverUserId);

        message.setId(sequenceGeneratorService.generateSequence(Message.SEQUENCE_NAME));
        message.setUserId(idUser);
        message.setReceiverUserId(receiverUserId);
        message.setTimestamp(new Date());
        message.setUser(user);

        return messageRepository.save(message);
    }
}

