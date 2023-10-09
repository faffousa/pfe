package com.vermeg.app.auth.controller;

import com.vermeg.app.auth.entity.Notification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class WebSocketController {
    @MessageMapping("/sendNotification")
    @SendTo("/topic/notifications")
    public Notification sendNotification(Notification notification) {
        return notification;
    }
}

