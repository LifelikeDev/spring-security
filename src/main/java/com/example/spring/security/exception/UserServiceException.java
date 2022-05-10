package com.example.spring.security.exception;

public class UserServiceException extends Exception {

    public UserServiceException(String message) {
        super(message);
    }

    public static String NotFoundException(String userId) {
        return "User with ID " + userId + " was not found.";
    }
}
