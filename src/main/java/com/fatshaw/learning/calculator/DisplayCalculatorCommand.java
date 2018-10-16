package com.fatshaw.learning.calculator;

public class DisplayCalculatorCommand implements CalculatorCommand {

    private Calculator c;
    private String p;

    public DisplayCalculatorCommand(Calculator c, String p) {
        this.c = c;
        this.p = p;
    }


    @Override
    public String exec() {
        return c.toString();
    }
}
