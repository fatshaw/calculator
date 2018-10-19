package com.fatshaw.learning.calculator.domain.trade;

public interface TransactionOperator<T> {

    Transaction<T> doTransaction(TransactionContext<T> calculatorContext);
}
