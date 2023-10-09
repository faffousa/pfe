package com.vermeg.app.auth.service;

import com.vermeg.app.auth.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service

public class NotificationService {
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendQuestionAddedNotification() {
        Notification notification = new Notification();
        notification.setMessage("Nouvelle question ajoutée !");
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }
}
