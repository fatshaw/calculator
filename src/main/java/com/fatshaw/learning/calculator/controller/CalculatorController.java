package com.fatshaw.learning.calculator.controller;

import com.fatshaw.learning.calculator.command.TransactionCommand;
import com.fatshaw.learning.calculator.command.TransactionCommandExecutor;
import com.fatshaw.learning.calculator.domain.calculator.CalculatorContext;
import com.fatshaw.learning.calculator.domain.trade.TransactionContext;
import com.fatshaw.learning.calculator.exception.InsufficientParameterException;
import java.math.BigDecimal;

public abstract class CalculatorController {


    TransactionContext<BigDecimal> transactionContext = new CalculatorContext();

    TransactionCommandExecutor transactionCommandExecutor = new TransactionCommandExecutor();

    public String processLine(String line) {

        String[] tokens = line.trim().split(" ");
        int pos = 1;

        for (int i = 0; i < tokens.length; i++) {
            TransactionCommand transactionCommand = TransactionCommand.builder().token(tokens[i]).pos(pos)
                .transactionContext(transactionContext).build();
            try {
                transactionCommandExecutor.doExecute(transactionCommand);
                pos += tokens[i].length() + 1;
            } catch (InsufficientParameterException e) {
                return insufficientParameterExceptionMessage(transactionCommand);
            } catch (Exception e) {
                return defaultExceptionMessage(transactionCommand);
            }
        }

        return transactionContextMessage();
    }


    public abstract void process();

    protected abstract String transactionContextMessage();

    protected abstract String insufficientParameterExceptionMessage(TransactionCommand transactionCommand);

    protected abstract String defaultExceptionMessage(TransactionCommand transactionCommand);

}
