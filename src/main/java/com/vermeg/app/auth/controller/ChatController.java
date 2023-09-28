package com.vermeg.app.auth.controller;

import com.vermeg.app.auth.entity.ChatMessage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/chat")
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MongoTemplate mongoTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate, MongoTemplate mongoTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.mongoTemplate = mongoTemplate;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessage chatMessage) {
        chatMessage.setTimestamp(new Date());
        mongoTemplate.save(chatMessage);
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }

    @SubscribeMapping("/chat.history")
    public List<ChatMessage> getChatHistory() {
        Query query = new Query()
                .with(Sort.by(Sort.Direction.DESC, "timestamp"))
                .with(PageRequest.of(0, 10));

        return mongoTemplate.find(query, ChatMessage.class);
    }
}
