package com.fatshaw.learning.calculator.domain.calculator;

import com.fatshaw.learning.calculator.domain.transaction.Trade;
import com.fatshaw.learning.calculator.domain.transaction.Transaction;
import com.fatshaw.learning.calculator.domain.transaction.TransactionContext;
import com.fatshaw.learning.calculator.domain.transaction.TransactionOperator;
import java.math.BigDecimal;
import java.util.List;

public class Calculator implements Trade<BigDecimal> {

    private TransactionContext<BigDecimal> calculatorContext = new CalculatorContext();

    @Override
    public List<BigDecimal> values() {
        return calculatorContext.values();
    }

    @Override
    public Transaction<BigDecimal> apply(TransactionOperator<BigDecimal> transactionOperator) {
        return transactionOperator.doTransaction(calculatorContext);
    }

}
