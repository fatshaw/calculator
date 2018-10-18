package com.fatshaw.learning.calculator.domain.calculator;

import com.fatshaw.learning.calculator.domain.transaction.Transaction;
import com.fatshaw.learning.calculator.domain.transaction.TransactionContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CalculatorContext implements TransactionContext<BigDecimal> {

    private Deque<BigDecimal> numbers = new ArrayDeque<>();
    private Deque<Transaction<BigDecimal>> transactions = new ArrayDeque<>();


    private static int STORE_SCALE = 15;

    private List<BigDecimal> convertToList(Deque<BigDecimal> a) {
        List<BigDecimal> b = new ArrayList<>(a);
        Collections.reverse(b);
        return b;
    }

    @Override
    public List<BigDecimal> values() {
        return convertToList(numbers);
    }

    /**
     * add number to the top of numbers
     */
    @Override
    public void push(BigDecimal number) {
        numbers.push(new BigDecimal(number.toPlainString()).setScale(STORE_SCALE, RoundingMode.FLOOR));
    }

    @Override
    public BigDecimal pop() {
        return numbers.pop();
    }

    @Override
    public int size() {
        return numbers.size();
    }

    @Override
    public int transactionSize() {
        return transactions.size();
    }

    @Override
    public Transaction recall() {
        return transactions.pop();
    }

    @Override
    public void saveTransaction(Transaction t) {
        transactions.push(t);
    }

}
