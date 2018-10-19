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
        int position = 1;

        for (int i = 0; i < tokens.length; i++) {
            TransactionCommand transactionCommand = TransactionCommand.builder().token(tokens[i]).position(position)
                .transactionContext(transactionContext).build();
            try {
                transactionCommandExecutor.doExecute(transactionCommand);
                position += tokens[i].length() + 1;
            } catch (InsufficientParameterException e) {
                return insufficientParameterExceptionMessage(transactionCommand);
            } catch (Exception e) {
                return defaultExceptionMessage(transactionCommand);
            }
        }

        return transactionContextMessage();
    }


    /**
     * process calculator
     */
    public abstract void process();

    /**
     * display transaction context message
     */
    protected abstract String transactionContextMessage();

    /**
     * display error message when insufficient parameter exception occur
     */
    protected abstract String insufficientParameterExceptionMessage(TransactionCommand transactionCommand);

    /**
     * display default error exception message
     */
    protected abstract String defaultExceptionMessage(TransactionCommand transactionCommand);

}
