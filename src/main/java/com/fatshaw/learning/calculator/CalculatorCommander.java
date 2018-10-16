package com.fatshaw.learning.calculator;

import java.math.BigDecimal;

public class CalculatorCommander {

    private Calculator c = new Calculator();

    private static boolean isNumber(String word) {
        try {
            new BigDecimal(word);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public CalculatorCommand printCalculator(){
        return create("printCalculator");
    }

    public CalculatorCommand create(String word) {
        if (isNumber(word)) {
            return new PushCalculatorCommand(c, word);
        }
        switch (word) {
            case "undo":
                return new UndoCalculatorCommand(c, word);
            case "clear":
                return new ClearCalculatorCommand(c, word);
            case "printCalculator":
                return new DisplayCalculatorCommand(c,"printCalculator");
        }
        return new MathCalculatorCommand(c, word);
    }

}
