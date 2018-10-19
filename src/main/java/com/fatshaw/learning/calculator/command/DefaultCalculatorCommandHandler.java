package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.calculator.AddOperator;
import com.fatshaw.learning.calculator.domain.calculator.Calculator;
import com.fatshaw.learning.calculator.domain.calculator.CalculatorOperator;
import com.fatshaw.learning.calculator.domain.calculator.ClearOperator;
import com.fatshaw.learning.calculator.domain.calculator.DivideOperator;
import com.fatshaw.learning.calculator.domain.calculator.MultiplyOperator;
import com.fatshaw.learning.calculator.domain.calculator.PushNumberOperator;
import com.fatshaw.learning.calculator.domain.calculator.SqrtOperator;
import com.fatshaw.learning.calculator.domain.calculator.SubtractOperator;
import com.fatshaw.learning.calculator.domain.calculator.UndoOperator;
import com.fatshaw.learning.calculator.domain.trade.Trade;
import com.fatshaw.learning.calculator.domain.trade.Transaction;
import java.math.BigDecimal;

public class DefaultCalculatorCommandHandler implements CalculatorCommandHandler {

    Trade<BigDecimal> trade = new Calculator();

    private static CalculatorOperator state(String token) {
        switch (token) {
            case "+":
                return new AddOperator();
            case "-":
                return new SubtractOperator();
            case "*":
                return new MultiplyOperator();
            case "/":
                return new DivideOperator();
            case "sqrt":
                return new SqrtOperator();
            case "undo":
                return new UndoOperator();
            case "clear":
                return new ClearOperator();
        }
        return new PushNumberOperator(token);
    }

    @Override
    public Transaction<BigDecimal> exec(TransactionCommand transactionCommand) {
        return trade.apply(state(transactionCommand.getToken()), transactionCommand.getTransactionContext());
    }
}
