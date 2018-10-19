package com.fatshaw.learning.calculator.domain.trade;

public interface Trade<T> {

    /**
     * apply transactionOperator on the trade context
     * @param transactionOperator: operator
     * @param transactionContext: context
     * @return
     */
    Transaction<T> apply(TransactionOperator<T> transactionOperator, TransactionContext<T> transactionContext);
}

