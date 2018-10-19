package com.fatshaw.learning.calculator.command;

public class CalculatorCommandHandlerFactory {

    static DefaultCalculatorCommandHandler defaultCalculatorCommandHandler = new DefaultCalculatorCommandHandler();

    public static CalculatorCommandHandler create() {
        return defaultCalculatorCommandHandler;
    }

}
