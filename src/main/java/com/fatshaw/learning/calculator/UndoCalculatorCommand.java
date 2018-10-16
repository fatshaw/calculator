package com.fatshaw.learning.calculator;

public class UndoCalculatorCommand implements CalculatorCommand {

    private Calculator c;
    private String p;

    public UndoCalculatorCommand(Calculator c, String p) {
        this.c = c;
        this.p = p;
    }

    @Override
    public String exec() {
        c.undo();
        return null;
    }
}
