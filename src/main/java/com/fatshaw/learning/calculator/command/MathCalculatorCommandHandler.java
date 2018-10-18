package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.Transaction;
import com.fatshaw.learning.calculator.domain.TransactionCommand;

public class MathCalculatorCommandHandler extends CalculatorCommandHandler {


    @Override
    protected Transaction exec(TransactionCommand transactionCommand) {
        return transactionCommand.getCalculator().apply(transactionCommand.getToken());
    }
}
