package com.fatshaw.learning.calculator.exception;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsufficientParameterException extends RuntimeException {

    List<BigDecimal> stack;

    private int pos;
    private String token;

    @Override
    public String getMessage() {
        return String.format("operator %s (position: %d): insufficient parameters", token, pos);
    }
}
