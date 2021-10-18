package com.endava.garagesaleapplication.controller.exception.customexception;

public class ConstraintsNotRespected extends RuntimeException {

    public ConstraintsNotRespected(String message) {
        super(message);
    }
}