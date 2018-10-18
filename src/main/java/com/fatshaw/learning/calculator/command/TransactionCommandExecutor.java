package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.TransactionCommand;
import com.fatshaw.learning.calculator.domain.TransactionResult;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class TransactionCommandExecutor {

    List<CommandTransaction> transactions = new ArrayList<>();

    public TransactionResult doTransaction(CalculatorCommandHandler command, TransactionCommand transactionCommand) {
        TransactionResult transactionResult = command.doCommand(transactionCommand);
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
        TransactionResult transactionResult;
    }
}
