package com.fatshaw.learning.calculator;

public class InsufficientParameterException extends RuntimeException {

    public InsufficientParameterException(String message) {
        super(message);
    }
}
