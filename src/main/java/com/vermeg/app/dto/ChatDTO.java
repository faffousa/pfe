package com.vermeg.app.dto;

import java.util.Date;

import com.vermeg.app.auth.entity.User;

import lombok.Data;

@Data
public class ChatDTO {
	
	private String chatId;

	private User userId;

	private String message;

	private Date sendDate;
}
