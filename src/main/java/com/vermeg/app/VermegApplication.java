package com.vermeg.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling


public class VermegApplication {

	public static void main(String[] args) {
		SpringApplication.run(VermegApplication.class, args);
	}

}
