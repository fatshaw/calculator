package com.fatshaw.learning.calculator.command;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class TransactionCommandExecutor {

    List<CommandTransaction> transactions = new ArrayList<>();

    public CommandResult doExecute(TransactionCommand transactionCommand) {

        CalculatorCommandHandler commandHandler = CalculatorCommandHandlerFactory.create();
        CommandResult transactionResult = commandHandler.doCommand(transactionCommand);
        CommandTransaction transaction = CommandTransaction.builder().transactionCommand(transactionCommand)
            .transactionResult(transactionResult).build();
        transactions.add(transaction);
        return transactionResult;
    }

    @Getter
    @Setter
    @Builder
    static class CommandTransaction {

        TransactionCommand transactionCommand;
        CommandResult transactionResult;
    }
}
