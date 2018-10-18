package com.fatshaw.learning.calculator.domain.transaction;

public interface TransactionOperator<T> {

    Transaction<T> doTransaction(TransactionContext<T> calculatorContext);
}
