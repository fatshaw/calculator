package com.fatshaw.learning.calculator.domain.trade;

public interface TransactionOperator<T> {

    /**
     * operate on transaction context and return transaction result
     */
    Transaction<T> doTransaction(TransactionContext<T> calculatorContext);
}
