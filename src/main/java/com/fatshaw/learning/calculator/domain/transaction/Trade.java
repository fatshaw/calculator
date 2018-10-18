package com.fatshaw.learning.calculator.domain.transaction;

import java.util.List;

public interface Trade<T> {

    /**
     * current values of trader
     * @return
     */
    List<T> values();

    /**
     * apply transactionOperator on the trader
     * @param transactionOperator
     * @return
     */
    Transaction<T> apply(TransactionOperator<T> transactionOperator);
}

