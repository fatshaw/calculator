package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.Transaction;
import com.fatshaw.learning.calculator.domain.TransactionCommand;
import com.fatshaw.learning.calculator.domain.TransactionResult;
import com.fatshaw.learning.calculator.exception.InsufficientParameterException;

public abstract class CalculatorCommandHandler {


    protected abstract Transaction exec(TransactionCommand transactionCommand);

    public TransactionResult doCommand(TransactionCommand transactionCommand) {
        try {
            Transaction transaction = exec(transactionCommand);
            return TransactionResult.builder().transaction(transaction).build();
        } catch (InsufficientParameterException e) {
            e.setStack(transactionCommand.getCalculator().getCurrentStack());
            e.setPos(transactionCommand.getPos());
            e.setToken(transactionCommand.getToken());
            throw e;
        }
    }
}
