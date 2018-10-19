package com.fatshaw.learning.calculator.domain.calculator;

import com.fatshaw.learning.calculator.domain.trade.Transaction;
import com.fatshaw.learning.calculator.domain.trade.TransactionContext;
import com.fatshaw.learning.calculator.domain.trade.TransactionOperator;
import java.math.BigDecimal;
import java.util.Optional;

public abstract class CalculatorOperator implements TransactionOperator<BigDecimal> {

    abstract public Optional<Transaction<BigDecimal>> transact(TransactionContext<BigDecimal> calculatorContext);

    public Transaction<BigDecimal> doTransaction(TransactionContext<BigDecimal> calculatorContext) {

        Optional<Transaction<BigDecimal>> transaction = transact(calculatorContext);

        if (!transaction.isPresent()) {
            return Transaction.<BigDecimal>builder().valuesSnapshot(calculatorContext.values()).build();

        }

        Transaction<BigDecimal> t = transaction.get();
        t.setValuesSnapshot(calculatorContext.values());
        calculatorContext.saveTransaction(t);
        return t;
    }
}
