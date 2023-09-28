package com.vermeg.app.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    private Long id;
    private String content;
    private Long userId;
    private Long receiverUserId;
    private Date timestamp;
    private User user;

    public static final String SEQUENCE_NAME = "message_sequence";



}
