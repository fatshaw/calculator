package com.fatshaw.learning.calculator.domain.trade;

import java.util.List;

public interface TransactionContext<T> {


    /**
     * retrieve values
     */
    List<T> values();

    /**
     * add number to the top of numbers
     */
    void push(T number);

    /**
     * pop last value
     */
    T pop();

    /**
     * return size of values
     */
    int size();

    /**
     * return size of transactions already done
     */
    int transactionSize();

    /**
     * return last trade
     */
    Transaction<T> recall();

    /**
     * save latest trade
     */
    void saveTransaction(Transaction<T> t);

}
