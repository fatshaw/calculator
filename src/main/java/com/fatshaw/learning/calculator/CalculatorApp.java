package com.fatshaw.learning.calculator;

import com.fatshaw.learning.calculator.controller.CalculatorController;
import com.fatshaw.learning.calculator.controller.ConsoleCalculatorController;

public class CalculatorApp {


    public static void main(String[] args) {
        CalculatorController calculatorApp = new ConsoleCalculatorController();
        calculatorApp.process();
    }
}

