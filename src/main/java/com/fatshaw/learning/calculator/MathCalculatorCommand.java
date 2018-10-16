package com.fatshaw.learning.calculator;

public class MathCalculatorCommand implements CalculatorCommand {

    private Calculator c;
    private String p;

    public MathCalculatorCommand(Calculator c, String p) {
        this.c = c;
        this.p = p;
    }

    @Override
    public String exec() {
        c.calculate(MathOperator.create(p));
        return null;
    }
}
