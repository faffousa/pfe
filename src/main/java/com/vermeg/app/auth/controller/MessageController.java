package com.vermeg.app.auth.controller;

import com.vermeg.app.auth.entity.Message;
import com.vermeg.app.auth.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }



    @GetMapping("/getMessages/{receiverUserId}/{userId}")
    public List<Message> getMessagesForUser(@PathVariable Long receiverUserId , @PathVariable Long userId) {
        return messageService.getMessageForUser(receiverUserId,userId);
    }


    @PostMapping("/add/{idUser}/{receiverUserId}")
    public Message sendMessage(@RequestBody Message message, @PathVariable("idUser") long idUser, @PathVariable("receiverUserId") long receiverUserId) {
        Message newMessage = messageService.sendMessage(message, idUser , receiverUserId);
        return newMessage;
    }

    @GetMapping("/getAllMessages")
    public List<Message> getAllMessages() {
        return messageService.findAllMessages();
    }

}
