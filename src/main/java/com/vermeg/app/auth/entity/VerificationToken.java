package com.vermeg.app.auth.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.vermeg.app.auth.dto.SignUpRequest;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class VerificationToken {

    private static final int EXPIRATION_TIME_IN_MINUTES = 60 * 24;

    @Id
    private String id;

    @Column(nullable = false)
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userID")
    private SignUpRequest signUpRequest;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    public VerificationToken(SignUpRequest signUpRequest, String token) {
    	this.token = token;
		this.signUpRequest = signUpRequest;
        this.expiryDate = LocalDateTime.now().plusMinutes(EXPIRATION_TIME_IN_MINUTES);
    }


    

 
}

