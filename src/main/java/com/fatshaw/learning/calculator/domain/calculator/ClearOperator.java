package com.fatshaw.learning.calculator.domain.calculator;

import com.fatshaw.learning.calculator.domain.trade.Transaction;
import com.fatshaw.learning.calculator.domain.trade.TransactionContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClearOperator extends CalculatorOperator {

    @Override
    public Optional<Transaction<BigDecimal>> transact(TransactionContext<BigDecimal> calculatorContext) {
        List<BigDecimal> removedNumbers = new ArrayList<>();
        while (calculatorContext.size() > 0) {
            removedNumbers.add(0, calculatorContext.pop());
        }
        return Optional.of(Transaction.<BigDecimal>builder().
            removedNumbers(removedNumbers)
            .build());
    }
}
