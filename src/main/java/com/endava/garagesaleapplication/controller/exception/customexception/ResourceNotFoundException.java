package com.endava.garagesaleapplication.controller.exception.customexception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}