package com.fatshaw.learning.refactoring.calculator;

import java.util.Scanner;

public class CalculatorApp {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculatorCommandParser calculatorCommandParser = new CalculatorCommandParser();

        while (true) {
            String result = calculatorCommandParser.parse(scanner.nextLine());
            System.out.println(result);
        }
    }

}

