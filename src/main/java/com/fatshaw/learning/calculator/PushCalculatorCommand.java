package com.fatshaw.learning.calculator;

import java.math.BigDecimal;

public class PushCalculatorCommand implements CalculatorCommand {

    Calculator c;
    private String p;

    public PushCalculatorCommand(Calculator c, String p) {
        this.c = c;
        this.p = p;
    }

    @Override
    public String exec() {
        c.addNumber(new BigDecimal(p));
        return null;
    }
}
