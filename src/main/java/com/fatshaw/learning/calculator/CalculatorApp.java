package com.fatshaw.learning.calculator;

import com.fatshaw.learning.calculator.command.TransactionCommand;
import com.fatshaw.learning.calculator.command.TransactionCommandExecutor;
import com.fatshaw.learning.calculator.domain.calculator.Calculator;
import com.fatshaw.learning.calculator.command.CommandResult;
import com.fatshaw.learning.calculator.exception.InsufficientParameterException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CalculatorApp {

    private static final int DISPLAY_SCALE = 10;

    Calculator calculator = new Calculator();

    TransactionCommandExecutor transactionCommandExecutor = new TransactionCommandExecutor();

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

    private String printStack(List<BigDecimal> bigDecimalList) {
        return String.format("stack: %s",
            bigDecimalList.stream().map(i -> displayNumber(i)).collect(Collectors.joining(" ")));
    }


    public String processLine(String line) {

        String[] tokens = line.split(" ");
        int pos = 1;
        CommandResult commandResult = null;

        for (int i = 0; i < tokens.length; i++) {
            TransactionCommand transactionCommand = TransactionCommand.builder().token(tokens[i]).pos(pos)
                .calculator(calculator).build();
            try {
                commandResult = transactionCommandExecutor.doExecute(transactionCommand);
                pos += tokens[i].length() + 1;
            } catch (InsufficientParameterException e) {
                return String.format("%s\n%s", e.getMessage(), printStack(e.getStack()));
            } catch (Exception e) {
                return String.format("wrong input token:%s at position:%d", transactionCommand.getToken(),
                    transactionCommand.getPos());
            }
        }

        return printStack(commandResult.getTransaction().getValuesSnapshot());
    }


    public void processFromConsole() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() > 0) {
                String result = processLine(line);
                System.out.println(result);
            }
        }
    }

    public static void main(String[] args) {
        CalculatorApp calculatorApp = new CalculatorApp();
        calculatorApp.processFromConsole();
    }
}

