package com.fatshaw.learning.calculator;

public class CalculatorCommandParser {

    private Calculator calculator = new Calculator();

    public String getMessage(String operator, int pos, String stackMessage) {
        return String.format("operator %s (position: %d): insufficient parameters\n%s", operator, pos, stackMessage);
    }

    /**
     *
     * @param line input string
     * @return calculate result
     */
    public String parse(String line){
        if (line == null || line.length() == 0) {
            return calculator.toString();
        }

        String[] words = line.split(" ");
        int pos = 1;
        for (int i = 0; i < words.length; i++) {
            try {
                CalculatorConsumer biConsumer = CalculatorControlCommander.create(words[i]);
                biConsumer.accept(calculator, words[i]);
            } catch (InsufficientParameterException e) {
                return getMessage(words[i], pos, calculator.toString());
            }

            pos += words[i].length() + 1;
        }

        return calculator.toString();
    }


}
