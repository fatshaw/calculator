package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.Transaction;
import com.fatshaw.learning.calculator.domain.TransactionCommand;
import java.math.BigDecimal;

public class PushCalculatorCommandHandler extends CalculatorCommandHandler {

    @Override
    protected Transaction exec(TransactionCommand transactionCommand) {
        return transactionCommand.getCalculator().addNumber(new BigDecimal(transactionCommand.getToken()));
    }
}
