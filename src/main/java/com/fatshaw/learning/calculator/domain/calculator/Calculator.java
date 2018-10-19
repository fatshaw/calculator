package com.fatshaw.learning.calculator.domain.calculator;

import com.fatshaw.learning.calculator.domain.trade.Trade;
import com.fatshaw.learning.calculator.domain.trade.Transaction;
import com.fatshaw.learning.calculator.domain.trade.TransactionContext;
import com.fatshaw.learning.calculator.domain.trade.TransactionOperator;
import java.math.BigDecimal;

public class Calculator implements Trade<BigDecimal> {


    @Override
    public Transaction<BigDecimal> apply(TransactionOperator<BigDecimal> transactionOperator,
        TransactionContext<BigDecimal> transactionContext) {
        return transactionOperator.doTransaction(transactionContext);
    }
}
