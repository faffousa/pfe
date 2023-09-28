package com.vermeg.app.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MeetingDTO {
	private String id;
	private long idInstructor;
	private long idFormation;
	private Date date;
	private String link;
}
