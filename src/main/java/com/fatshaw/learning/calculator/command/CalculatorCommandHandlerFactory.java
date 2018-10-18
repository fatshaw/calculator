package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.calculator.AddOperator;
import com.fatshaw.learning.calculator.domain.calculator.CalculatorOperator;
import com.fatshaw.learning.calculator.domain.calculator.ClearOperator;
import com.fatshaw.learning.calculator.domain.calculator.DivideOperator;
import com.fatshaw.learning.calculator.domain.calculator.MultiplyOperator;
import com.fatshaw.learning.calculator.domain.calculator.PushNumberOperator;
import com.fatshaw.learning.calculator.domain.calculator.SqrtOperator;
import com.fatshaw.learning.calculator.domain.calculator.SubtractOperator;
import com.fatshaw.learning.calculator.domain.calculator.UndoOperator;

public class CalculatorCommandHandlerFactory {

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

    public static CalculatorCommandHandler create(TransactionCommand transactionCommand) {
        return command -> command.getCalculator().apply(state(transactionCommand.getToken()));
    }

}
