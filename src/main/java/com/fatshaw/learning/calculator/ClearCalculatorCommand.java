package com.fatshaw.learning.calculator;

public class ClearCalculatorCommand implements CalculatorCommand {

    private Calculator c;
    private String p;

    public ClearCalculatorCommand(Calculator c, String p) {
        this.c = c;
        this.p = p;
    }

    @Override
    public String exec() {
        c.clear();
        return null;
    }
}
