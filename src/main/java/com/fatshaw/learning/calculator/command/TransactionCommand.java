package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.trade.TransactionContext;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TransactionCommand {

    TransactionContext<BigDecimal> transactionContext;
    String token;
    int pos;
}
