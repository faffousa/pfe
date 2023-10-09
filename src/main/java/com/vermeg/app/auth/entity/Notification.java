package com.vermeg.app.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Notification {

    @Transient
    public static final String SEQUENCE_NAME = "notification";

    @Id
    private long idNotification;

    private String message;


}
