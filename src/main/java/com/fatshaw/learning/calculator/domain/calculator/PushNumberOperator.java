package com.fatshaw.learning.calculator.domain.calculator;

import com.fatshaw.learning.calculator.domain.trade.Transaction;
import com.fatshaw.learning.calculator.domain.trade.TransactionContext;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public class PushNumberOperator extends CalculatorOperator {

    String token;

    public PushNumberOperator(String token) {
        this.token = token;
    }

    @Override
    public Optional<Transaction<BigDecimal>> transact(TransactionContext<BigDecimal> calculatorContext) {
        BigDecimal a = new BigDecimal(token);
        calculatorContext.push(a);

        return Optional.of(Transaction.<BigDecimal>builder()
            .addedNumbers(Arrays.asList(a))
            .build());
    }
}
