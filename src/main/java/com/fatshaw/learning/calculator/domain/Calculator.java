package com.fatshaw.learning.calculator.domain;

import com.fatshaw.learning.calculator.exception.InsufficientParameterException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class Calculator {

    private Deque<BigDecimal> numbers = new ArrayDeque<>();
    private Deque<Transaction> transactions = new ArrayDeque<>();

    private static int STORE_SCALE = 15;

    private void addNumberWithScale(BigDecimal number) {
        numbers.push(new BigDecimal(number.toPlainString()).setScale(STORE_SCALE, RoundingMode.FLOOR));
    }

    private Transaction logTransaction(List<BigDecimal> removedNumbers, List<BigDecimal> addedNumbers) {
        Transaction operation = Transaction.builder().removedNumbers(removedNumbers).addedNumbers(addedNumbers)
            .valuesSnapshot(convertToList(numbers)).build();
        transactions.push(operation);
        return operation;
    }

    private List<BigDecimal> convertToList(Deque<BigDecimal> a) {
        List<BigDecimal> b = new ArrayList<>(a);
        Collections.reverse(b);
        return b;
    }

    public List<BigDecimal> getCurrentStack() {
        return convertToList(numbers);
    }


    /**
     * add number to the top of numbers
     */
    public Transaction addNumber(BigDecimal number) {
        addNumberWithScale(number);
        return logTransaction(null, Arrays.asList(number));
    }

    public Transaction apply(String token) {
        return state(token).operate();
    }

    public State state(String token) {
        switch (token) {
            case "+":
                return this.new Add();
            case "-":
                return this.new Subtract();
            case "*":
                return this.new Multiply();
            case "/":
                return this.new Divide();
            case "sqrt":
                return this.new Sqrt();
            case "undo":
                return this.new Undo();
            case "clear":
                return this.new Clear();
        }
        return null;
    }

    abstract class State {

        abstract public Optional<Transaction> calculate();

        public Transaction operate() {
            Optional<Transaction> transaction = calculate();

            if (!transaction.isPresent()) {
                return Transaction.builder().valuesSnapshot(convertToList(numbers)).build();

            }

            Transaction t = transaction.get();
            if (t.getAddedNumbers() != null) {
                for (BigDecimal a : t.getAddedNumbers()) {
                    addNumberWithScale(a);
                }
            }
            t.setValuesSnapshot(convertToList(numbers));
            transactions.push(t);
            return t;
        }
    }

    class Add extends State {

        @Override
        public Optional<Transaction> calculate() {
            if (numbers.size() < 2) {
                throw new InsufficientParameterException();
            }
            BigDecimal a = numbers.pop();
            BigDecimal b = numbers.pop();

            BigDecimal result = a.add(b);

            return Optional.of(Transaction.builder().
                removedNumbers(Arrays.asList(b, a))
                .addedNumbers(Arrays.asList(result))
                .build());
        }
    }

    class Sqrt extends State {


        @Override
        public Optional<Transaction> calculate() {
            if (numbers.size() < 1) {
                throw new InsufficientParameterException();
            }
            BigDecimal a = numbers.pop();

            BigDecimal result = new BigDecimal(Math.sqrt(a.doubleValue()));

            return Optional.of(Transaction.builder().
                removedNumbers(Arrays.asList(a))
                .addedNumbers(Arrays.asList(result))
                .build());
        }
    }

    class Multiply extends State {


        @Override
        public Optional<Transaction> calculate() {
            if (numbers.size() < 2) {
                throw new InsufficientParameterException();
            }
            BigDecimal a = numbers.pop();
            BigDecimal b = numbers.pop();

            BigDecimal result = a.multiply(b);

            return Optional.of(Transaction.builder().
                removedNumbers(Arrays.asList(b, a))
                .addedNumbers(Arrays.asList(result))
                .build());
        }
    }

    class Divide extends State {


        @Override
        public Optional<Transaction> calculate() {
            if (numbers.size() < 2) {
                throw new InsufficientParameterException();
            }
            BigDecimal a = numbers.pop();
            BigDecimal b = numbers.pop();
            try {
                BigDecimal result = a.divide(b);
                return Optional.of(Transaction.builder().
                    removedNumbers(Arrays.asList(b, a))
                    .addedNumbers(Arrays.asList(result))
                    .build());
            } catch (ArithmeticException e) {
                numbers.push(b);
                numbers.push(a);
            }
            return Optional.of(Transaction.builder().build());
        }
    }

    class Subtract extends State {


        @Override
        public Optional<Transaction> calculate() {
            if (numbers.size() < 2) {
                throw new InsufficientParameterException();
            }
            BigDecimal a = numbers.pop();
            BigDecimal b = numbers.pop();

            BigDecimal result = a.subtract(b);

            return Optional.of(Transaction.builder().
                removedNumbers(Arrays.asList(b, a))
                .addedNumbers(Arrays.asList(result))
                .build());
        }
    }

    class Undo extends State {

        @Override
        public Optional<Transaction> calculate() {
            if (transactions.size() > 0) {
                Transaction operation = transactions.pop();
                if (operation.getAddedNumbers() != null) {
                    operation.getAddedNumbers().stream().forEach(i -> numbers.pop());
                }
                if (operation.removedNumbers != null) {
                    operation.removedNumbers.stream().forEach(numbers::push);
                }
            }
            return Optional.empty();
        }
    }

    class Clear extends State {

        @Override
        public Optional<Transaction> calculate() {
            List<BigDecimal> removedNumbers = new ArrayList<>();
            while (numbers.size() > 0) {
                removedNumbers.add(0, numbers.pop());
            }
            return Optional.of(Transaction.builder().
                removedNumbers(removedNumbers)
                .build());
        }
    }

}
