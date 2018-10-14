package com.fatshaw.learning.calculator;

import java.math.BigDecimal;

public class CalculatorControlCommander {


    public static CalculatorConsumer<Calculator, String> UNDO = (i, j) -> i.undo();

    public static CalculatorConsumer<Calculator, String> CLEAR = (i, j) -> i.clear();

    public static CalculatorConsumer<Calculator, String> ADD_NUMBER = (i, j) -> i
        .addNumber(new BigDecimal(j).setScale(15));

    public static CalculatorConsumer<Calculator, String> CALCULATE = (i, j) -> {
        MathOperator operator = MathOperator.create(j);
        if (operator != null) {
            i.calculate(operator);
        }
    };

    private static boolean isNumber(String word) {
        try {
            new BigDecimal(word);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static CalculatorConsumer create(String word) {
        if (isNumber(word)) {
            return ADD_NUMBER;
        }
        switch (word) {
            case "undo":
                return UNDO;
            case "clear":
                return CLEAR;
        }
        return CALCULATE;
    }

}
