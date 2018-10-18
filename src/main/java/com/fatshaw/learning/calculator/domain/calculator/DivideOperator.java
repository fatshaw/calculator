package com.fatshaw.learning.calculator.domain.calculator;

import com.fatshaw.learning.calculator.domain.transaction.Transaction;
import com.fatshaw.learning.calculator.domain.transaction.TransactionContext;
import com.fatshaw.learning.calculator.exception.InsufficientParameterException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.Optional;

public class DivideOperator extends CalculatorOperator {


    @Override
    public Optional<Transaction<BigDecimal>> transact(TransactionContext<BigDecimal> calculatorContext) {
        if (calculatorContext.size() < 2) {
            throw new InsufficientParameterException();
        }
        BigDecimal a = calculatorContext.pop();
        BigDecimal b = calculatorContext.pop();
        try {
            BigDecimal result = a.divide(b, MathContext.DECIMAL64);
            calculatorContext.push(result);

            return Optional.of(Transaction.<BigDecimal>builder().
                removedNumbers(Arrays.asList(b, a))
                .addedNumbers(Arrays.asList(result))
                .build());
        } catch (ArithmeticException e) {
            calculatorContext.push(b);
            calculatorContext.push(a);
        }
        return Optional.of(Transaction.<BigDecimal>builder().build());
    }
}
