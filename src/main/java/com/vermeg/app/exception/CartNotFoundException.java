package com.vermeg.app.exception;

public class CartNotFoundException extends RuntimeException{
	public CartNotFoundException(String message) {
        super(message);
    }
}
