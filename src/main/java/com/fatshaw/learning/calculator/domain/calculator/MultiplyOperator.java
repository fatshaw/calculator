package com.fatshaw.learning.calculator.domain.calculator;

import com.fatshaw.learning.calculator.domain.trade.Transaction;
import com.fatshaw.learning.calculator.domain.trade.TransactionContext;
import com.fatshaw.learning.calculator.exception.InsufficientParameterException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public class MultiplyOperator extends CalculatorOperator {


    @Override
    public Optional<Transaction<BigDecimal>> transact(TransactionContext<BigDecimal> calculatorContext) {
        if (calculatorContext.size() < 2) {
            throw new InsufficientParameterException();
        }
        BigDecimal a = calculatorContext.pop();
        BigDecimal b = calculatorContext.pop();

        BigDecimal result = a.multiply(b);
        calculatorContext.push(result);

        return Optional.of(Transaction.<BigDecimal>builder().
            removedNumbers(Arrays.asList(b, a))
            .addedNumbers(Arrays.asList(result))
            .build());
    }
}
