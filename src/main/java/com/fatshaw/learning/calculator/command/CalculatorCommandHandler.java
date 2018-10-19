package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.trade.Transaction;
import java.math.BigDecimal;

public interface CalculatorCommandHandler {


    Transaction<BigDecimal> exec(TransactionCommand transactionCommand);

    default CommandResult doCommand(TransactionCommand transactionCommand) {
        return CommandResult.builder().transaction(exec(transactionCommand)).build();
    }
}
