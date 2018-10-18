package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.transaction.Transaction;
import com.fatshaw.learning.calculator.exception.InsufficientParameterException;
import java.math.BigDecimal;

public interface CalculatorCommandHandler {


    Transaction<BigDecimal> exec(TransactionCommand transactionCommand);

    default CommandResult doCommand(TransactionCommand transactionCommand) {
        try {
            Transaction<BigDecimal> transaction = exec(transactionCommand);
            return CommandResult.builder().transaction(transaction).build();
        } catch (InsufficientParameterException e) {
            e.setStack(transactionCommand.getCalculator().values());
            e.setPos(transactionCommand.getPos());
            e.setToken(transactionCommand.getToken());
            throw e;
        }
    }
}
