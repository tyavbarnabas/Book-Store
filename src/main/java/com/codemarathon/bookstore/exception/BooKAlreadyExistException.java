package com.codemarathon.bookstore.exception;

public class BooKAlreadyExistException extends RuntimeException{
    public BooKAlreadyExistException(String message) {
        super(message);
    }
}
