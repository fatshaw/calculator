package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.TransactionCommand;
import java.math.BigDecimal;

public class CalculatorCommandHandlerFactory {


    private static boolean isNumber(String word) {
        try {
            new BigDecimal(word);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static CalculatorCommandHandler create(TransactionCommand transactionCommand) {
        if (isNumber(transactionCommand.getToken())) {
            return new PushCalculatorCommandHandler();
        }
        switch (transactionCommand.getToken()) {
            case "undo":
                return new UndoCalculatorCommandHandler();
            case "clear":
                return new ClearCalculatorCommandHandler();
        }
        return new MathCalculatorCommandHandler();
    }

}
