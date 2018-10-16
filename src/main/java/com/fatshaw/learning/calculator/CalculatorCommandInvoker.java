package com.fatshaw.learning.calculator;

public class CalculatorCommandInvoker {

    CalculatorCommander calculatorCommander = new CalculatorCommander();

    public String getMessage(String operator, int pos, String stackMessage) {
        return String.format("operator %s (position: %d): insufficient parameters\n%s", operator, pos, stackMessage);
    }

    /**
     * @param line input string
     * @return calculate result
     */
    public String parse(String line) {
        if (line == null || line.length() == 0) {
            return "";
        }

        String[] words = line.split(" ");
        int pos = 1;
        for (int i = 0; i < words.length; i++) {
            try {
                CalculatorCommand command = calculatorCommander.create(words[i]);
                command.exec();
            } catch (InsufficientParameterException e) {
                return getMessage(words[i], pos, e.getMessage());
            }

            pos += words[i].length() + 1;
        }

        return calculatorCommander.printCalculator().exec();
    }
}
