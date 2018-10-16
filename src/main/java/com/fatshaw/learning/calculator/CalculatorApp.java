package com.fatshaw.learning.calculator;

import java.util.Scanner;

public class CalculatorApp {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculatorCommandInvoker calculatorCommandInvoker = new CalculatorCommandInvoker();

        while (true) {
            String result = calculatorCommandInvoker.parse(scanner.nextLine());
            System.out.println(result);
        }
    }

}

