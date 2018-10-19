package com.fatshaw.learning.calculator.controller;

import com.fatshaw.learning.calculator.command.TransactionCommand;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleCalculatorController extends CalculatorController {

    protected static final int DISPLAY_SCALE = 10;

    private String displayNumber(BigDecimal i) {
        String plain = i.toPlainString();
        int numberOfTrailingZero = 0;
        for (int k = plain.length() - 1; k > plain.indexOf('.'); k--) {
            if (plain.charAt(k) != '0') {
                break;
            }
            numberOfTrailingZero++;
        }
        return new BigDecimal(plain)
            .setScale(Math.min(i.scale() - numberOfTrailingZero, DISPLAY_SCALE), RoundingMode.FLOOR)
            .toPlainString();
    }

    @Override
    public void process() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() > 0) {
                String result = processLine(line);
                System.out.println(result);
            }
        }
    }

    @Override
    protected String transactionContextMessage() {
        return String.format("stack: %s",
            transactionContext.values().stream().map(i -> displayNumber(i)).collect(Collectors.joining(" ")));
    }

    @Override
    protected String insufficientParameterExceptionMessage(TransactionCommand transactionCommand) {
        return String.format("operator %s (position: %d): insufficient parameters\n%s", transactionCommand.getToken(),
            transactionCommand.getPosition(), transactionContextMessage());
    }

    @Override
    protected String defaultExceptionMessage(TransactionCommand transactionCommand) {
        return String.format("wrong input token:%s at position:%d", transactionCommand.getToken(),
            transactionCommand.getPosition());
    }

}
