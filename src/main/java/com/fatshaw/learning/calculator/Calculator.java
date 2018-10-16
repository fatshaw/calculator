package com.fatshaw.learning.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator {

    private Stack<BigDecimal> stack = new Stack<>();
    private Stack<Operation> lastOperations = new Stack<>();

    private static int STORE_SCALE = 15;
    private static int DISPLAY_SCALE = 10;


    static class Operation {

        List<BigDecimal> removedNumbers;
        BigDecimal addedNumber;

        Operation(List<BigDecimal> removedNumbers, BigDecimal addedNumber) {
            this.removedNumbers = removedNumbers;
            this.addedNumber = addedNumber;
        }
    }


    private void addNumberWithScale(BigDecimal number) {
        stack.push(new BigDecimal(number.toPlainString()).setScale(STORE_SCALE, RoundingMode.FLOOR));
    }

    private String displayNumber(BigDecimal i) {
        String plain = i.toPlainString();
        int numberOfTrailingZero = 0;
        for (int k = plain.length() - 1; k >= plain.indexOf('.'); k--) {
            if (plain.charAt(k) == '0') {
                numberOfTrailingZero++;
            }
        }
        return new BigDecimal(plain)
            .setScale(Math.min(STORE_SCALE - numberOfTrailingZero, DISPLAY_SCALE), RoundingMode.FLOOR)
            .toPlainString();
    }

    @Override
    public String toString() {
        return String.format("stack: %s",
            stack.stream().map(i -> displayNumber(i)).collect(Collectors.joining(" ")));
    }

    /**
     * add number to the top of stack
     * @param number
     */
    public void addNumber(BigDecimal number) {
        addNumberWithScale(number);
        lastOperations.push(new Operation(null, number));
    }

    /**
     * undo last operation
     */
    public void undo() {
        if (lastOperations.size() > 0) {
            Operation operation = lastOperations.pop();
            if (operation.addedNumber != null) {
                stack.pop();
            }
            if (operation.removedNumbers != null) {
                operation.removedNumbers.stream().forEach(stack::push);
            }
        }
    }

    /**
     * clear the stack
     */
    public void clear() {
        List<BigDecimal> numbers = new ArrayList<>();
        while (stack.size() > 0) {
            numbers.add(0, stack.pop());
        }

        lastOperations.push(new Operation(numbers, null));
    }

    /**
     * @throws InsufficientParameterException if parameter is not enough.
     */
    public void calculate(MathOperator operator) {
        if (operator == null) {
            return;
        }
        List<BigDecimal> numbers = new ArrayList<>();
        int needParameterNumber = operator.needParameterNumber();
        BigDecimal result;
        if (stack.size() >= needParameterNumber) {
            for (int i = 0; i < needParameterNumber; i++) {
                numbers.add(stack.pop());
            }
            result = operator.operate(numbers);
            addNumberWithScale(result);
        } else {
            throw new InsufficientParameterException(toString());
        }

        Collections.reverse(numbers);
        lastOperations.add(new Operation(numbers, result));
    }


}
