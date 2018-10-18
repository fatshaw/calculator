package com.fatshaw.learning.calculator.domain.calculator;

import com.fatshaw.learning.calculator.domain.transaction.Transaction;
import com.fatshaw.learning.calculator.domain.transaction.TransactionContext;
import java.math.BigDecimal;
import java.util.Optional;

public class UndoOperator extends CalculatorOperator {

    @Override
    public Optional<Transaction<BigDecimal>> transact(TransactionContext<BigDecimal> calculatorContext) {
        if (calculatorContext.transactionSize() > 0) {
            Transaction<BigDecimal> operation = calculatorContext.recall();
            if (operation.getAddedNumbers() != null) {
                operation.getAddedNumbers().stream().forEach(i -> calculatorContext.pop());
            }
            if (operation.getRemovedNumbers() != null) {
                operation.getRemovedNumbers().stream().forEach(calculatorContext::push);
            }
        }
        return Optional.empty();
    }
}
